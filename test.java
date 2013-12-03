/** Test Driver Class
	Dan Daly
	Gmail Search and Sort
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Console;

import java.util.*;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class test
{
	public static void main(String[] args)
	{
		UnionFind uf;
		ArrayList<Email> inboxList = new ArrayList<Email>();
		String user, pass = "";
		
		Console console = System.console();
		if (console == null) 
		{
			System.out.println("Couldn't get console instance.");
			System.exit(0);
		}

		user = console.readLine(("[Enter email address]: "));
		char[] temp = console.readPassword("[Enter your password]: ");
		pass = new String(temp);
		
		Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try 
		{	
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			
			store.connect("imap.gmail.com", user, pass);
			System.out.println(store);
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			inboxList = InboxHandler.getMail(inbox);
		}
		catch(NoSuchProviderException e)
		{
			System.out.println(e.toString());
			System.exit(1);
		}
		catch (MessagingException e)
		{
			System.out.println(e.toString());
			System.exit(2);
		}
		
		String addr = console.readLine(("[Enter email address to find]: "));
		uf = InboxHandler.findSender(addr, inboxList);
		System.out.println(uf.count() + " emails from this sender.");

    }
}