package model;

public class Message {
	private String number;
	private String sender_name;
	private String recipient_name;
	private String message;
	private String update_date;
	private String read_number;
	
	public Message(String number, String sender_name, String recipient_name, String message, String update_date, String read_number) {
		this.number = number;
		this.sender_name = sender_name;
		this.recipient_name = recipient_name;
		this.message = message;
		this.update_date = update_date;
		this.read_number = read_number;
	}

	public String getNumber(){
		return number;
	}
	
	public String getSenderName() {
		return sender_name;
	}
	
	public String getRecipientName() {
		return recipient_name;
	}

	public String getMessage() {
		return message;
	}

	public String getDate() {
		return update_date;
	}
	
	public String getReadNumber(){
		return read_number;
	}
}