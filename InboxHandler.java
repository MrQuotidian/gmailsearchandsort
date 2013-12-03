public class InboxHandler
{
	public static ArrayList<Email> getMail(Folder inbox)
	{
		String date, from, subject, content;
		ArrayList<Email> emails = new ArrayList<Email>();
		try
		{
			Message msg[] = inbox.getMessages();
			for(Message message:msg)
			{
				try
				{
					date = message.getSentDate().toString());
					form = message.getFrom()[0].toString());           
					subject = message.getSubject().toString());
					content = message.getContent().toString());
					Email email = new Email(date, from, subject, content);
					emails.add(email);
                }
				catch (Exception e)
					System.out.println("No Information");
			}
		}
		catch (MessagingException e)
			System.out.println(e.toString());
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