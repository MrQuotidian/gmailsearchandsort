import java.util.*;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class InboxHandler
{
	public static ArrayList<Email> getMail(Folder inbox)
	{
		double n = 0, emailCount;
		int percent = 0;
		String date, from, subject, content;
		ArrayList<Email> emails = new ArrayList<Email>();
		try
		{
			FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message msg[] = inbox.search(ft);
			emailCount = msg.length;
            System.out.println("Unread: " + (int)emailCount);
			//Message msg[] = inbox.getMessages();
			for(Message message:msg)
			{
				try
				{
					date = message.getSentDate().toString();
					from = message.getFrom()[0].toString();           
					subject = message.getSubject().toString();
					content = message.getContent().toString();
					Email email = new Email(date, from, subject, content);
					emails.add(email);
					
					StringBuilder bar = new StringBuilder("[");
					percent = (int)(n*100/emailCount);
					
					for(int i = 0; i < 50; i++)
					{
						if(i < (percent/2))
							bar.append("=");
						else if(i == (percent/2))
							bar.append(">");
						else
							bar.append(" ");
					}

					bar.append("]   " + percent + "%     ");
					System.out.print("\r" + bar.toString());
					
					n++;
                }
				catch (Exception e)
				{
					System.out.println("No Information");
				}
			}
		}
		catch (MessagingException e)
		{
			System.out.println(e.toString());
		}
		
		return emails;
	}
	
	public boolean createFolder(Folder parent, String folderName)   
	{   
		boolean isCreated = true;   

		try  
		{   
			Folder newFolder = parent.getFolder(folderName);   
			isCreated = newFolder.create(Folder.HOLDS_MESSAGES);   
			System.out.println("created: " + isCreated);   
	
		} 
		catch (Exception e)   
		{   
			System.out.println("Error creating folder: " + e.getMessage());   
			e.printStackTrace();   
			isCreated = false;   
		} 	  	
		return isCreated;
	}
}