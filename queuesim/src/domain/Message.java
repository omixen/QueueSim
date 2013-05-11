package domain;

public class Message 
{
	public Message(long tick, String id, String queue, String customerType)
	{
		this.tick = tick;
		this.id = id;
		this.queue = queue;
		this.customerType = customerType;
	}
	
	long tick;
	String id, queue, customerType;
}
