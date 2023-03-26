package com.driver;

import java.lang.annotation.Target;
import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    List<Mail>inbox=new ArrayList<>();

    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    List<Mail>trash=new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        if(!sender.equals(super.getEmailId())) {
            if (inbox.size() == inboxCapacity) {
                Mail mail = inbox.get(0);
                trash.add(mail);
                inbox.remove(mail);
            }
            // It is guaranteed that:
            // 1. Each mail in the inbox is distinct.
            // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
            Mail mail = new Mail();
            mail.setSender(sender);
            mail.setDate(date);
            mail.setMessage(message);
            inbox.add(mail);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail m:inbox){
            if(m.getMessage().equals(message)){
                trash.add(m);
                inbox.remove(m);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        if(inbox.size()==0)return null;
        return inbox.get(inbox.size()-1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if(inbox.size()==0)return null;
        // Else, return the message of the oldest mail present in the inbox
        return inbox.get(0).getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        int count=0;
        for(int i=0;i<inbox.size();i++){
            if(inbox.get(i).getDate().equals(start)
                    ||inbox.get(i).getDate().after(start)
                    ||inbox.get(i).getDate().before(end)
                    ||inbox.get(i).getDate().equals(end)){
                count++;

            }
        }
        //It is guaranteed that start date <= end date

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity-inbox.size();
    }
}
