public class Email
{
	private String date, from, subject, content;

	public Email(String date, String from, String subject, String content)
	{
		this.date = date;
		this.from = from;
		this.subject = subject;
		this.content = content;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public String getFrom()
	{
		return this.from;
	}
	
	public String getSubject()
	{
		return this.subject;
	}
	
	public String getContent()
	{
		return this.content;
	}
}