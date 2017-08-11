/*
 * Copyright (c) 2006-2017 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.examples;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.search.AndTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SubjectTerm;

import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.DataWriter;
import com.northconcepts.datapipeline.core.LimitReader;
import com.northconcepts.datapipeline.core.StreamWriter;
import com.northconcepts.datapipeline.email.EmailReadDirection;
import com.northconcepts.datapipeline.email.EmailReader;
import com.northconcepts.datapipeline.email.GmailFolders;
import com.northconcepts.datapipeline.email.MailStore;
import com.northconcepts.datapipeline.excel.ExcelDocument;
import com.northconcepts.datapipeline.excel.ExcelWriter;
import com.northconcepts.datapipeline.file.FileReader;
import com.northconcepts.datapipeline.file.FileWriter;
import com.northconcepts.datapipeline.job.Job;
import com.northconcepts.datapipeline.transform.BasicFieldTransformer;
import com.northconcepts.datapipeline.transform.SelectFields;
import com.northconcepts.datapipeline.transform.TransformingReader;

public class ReadFromGmail {

    private static final String HOST = "imap.gmail.com";
    private static final String USER = "user@example.com";
    private static final String PASSWD = "password";
	
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void readLatest10Emails() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setDirection(EmailReadDirection.BACKWARD);
        reader = new LimitReader(reader, 10);
        DataWriter writer = StreamWriter.newSystemOutWriter();
        Job.run(reader, writer);
    }
    
    public void readLatest10SentEmails() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setDirection(EmailReadDirection.BACKWARD)
                .setFolderName(GmailFolders.SENT_MAIL);
        reader = new LimitReader(reader, 10);
        DataWriter writer = StreamWriter.newSystemOutWriter();
        Job.run(reader, writer);
    }
    
    public void searchEmailsBySubject() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setSearchTerm(new SubjectTerm("Trial License"))
                .setFolderName(GmailFolders.ALL_MAIL);
        reader = new LimitReader(reader, 10);
        DataWriter writer = StreamWriter.newSystemOutWriter();
        Job.run(reader, writer);
    }
    
    public void searchEmailsByDateAndSubject() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setSearchTerm(new AndTerm(
                        new ReceivedDateTerm(ReceivedDateTerm.GE, DATE_TIME_FORMAT.parse("2017-07-01 00:00:00")),
                        new SubjectTerm("Trial License")))
                .setFolderName(GmailFolders.ALL_MAIL)
                .setDirection(EmailReadDirection.BACKWARD)
                ;
        reader = new LimitReader(reader, 25);
        DataWriter writer = StreamWriter.newSystemOutWriter();
        Job.run(reader, writer);
    }
    
    public void debugEmailReader() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setSearchTerm(new SubjectTerm("Trial License"))
                .setFolderName(GmailFolders.ALL_MAIL)
                .setDebug(true);
        reader = new LimitReader(reader, 10);
        DataWriter writer = StreamWriter.newSystemOutWriter();
        Job.run(reader, writer);
    }

    public void exportEmailsToExcel() throws Throwable {
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setSearchTerm(new AndTerm(
                        new ReceivedDateTerm(ReceivedDateTerm.GE, DATE_TIME_FORMAT.parse("2017-06-01 00:00:00")),
                        new SubjectTerm("Trial License")))
                .setFolderName(GmailFolders.ALL_MAIL)
                .setDirection(EmailReadDirection.BACKWARD);
        
        reader = new LimitReader(reader, 100);
        
        reader = new TransformingReader(reader)
                .add(new SelectFields("from", "to", "sentDate", "subject", "content"))
                .add(new BasicFieldTransformer("content")
                        .flattenToString("")
                        .left(32767));
        
        
        ExcelDocument document = new ExcelDocument();
        DataWriter writer = new ExcelWriter(document).setSheetName("License Trials");

        Job.run(reader, writer);

        String date = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
        document.save(new File("scratch/data/output/trials-" + date + ".xlsx"));
    }

    public void dumpEmailsToFile() throws Throwable {
        System.setProperty("mail.mime.base64.ignoreerrors", "true");
        
        DataReader reader = new EmailReader(MailStore.IMAP_OVER_SSL, HOST, USER, PASSWD)
                .setSearchTerm(new AndTerm(
                        new ReceivedDateTerm(ReceivedDateTerm.GE, DATE_TIME_FORMAT.parse("2015-01-01 00:00:00")),
                        new SubjectTerm("Trial License")))
                .setFolderName(GmailFolders.ALL_MAIL)
                .setDirection(EmailReadDirection.BACKWARD);
        
        DataWriter writer = new FileWriter(new File("scratch/data/output/trials-" 
                + new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date()) 
                + ".bin"));
        
        Job.run(reader, writer);
        
        System.out.println(writer.getRecordCount() + " emails saved.");
    }

    public void exportDumpedEmailsToExcel() throws Throwable {
        DataReader reader = new FileReader(new File("scratch/data/output/trials-2017-08-09-183137.bin"));
        
        reader = new TransformingReader(reader)
                .add(new SelectFields("from", "to", "sentDate", "subject", "content"))
//                .add(new BasicFieldTransformer("sentDate").stringToDateTime("EEE MMM dd HH:mm:ss z yyyy"))
                .add(new BasicFieldTransformer("content")
                        .flattenToString("\n")
                        .left(32767));
        
        
        ExcelDocument document = new ExcelDocument();
        DataWriter writer = new ExcelWriter(document).setSheetName("License Trials");

        Job.run(reader, writer);

        String date = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
        document.save(new File("scratch/data/output/trials-" + date + ".xlsx"));
    }
    
    public static void main(String[] args) throws Throwable {
    	ReadFromGmail gmailReader = new ReadFromGmail();
    	
    	gmailReader.readLatest10Emails(); // change the method to try the other examples
    }
}