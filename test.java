/** Test Driver Class
	Dan Daly
	Gmail Search and Sort
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Properties;

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
		ArrayList<Email> inboxList = new ArrayList<Email>();
		
		