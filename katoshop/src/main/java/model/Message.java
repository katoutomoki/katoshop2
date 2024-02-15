package model;

public class Message {
	private String name;
	private String id;
	private String message;
	
	public Message(String name, String id, String message) {
		this.name = name;
		this.id = id;
		this.message = message;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

}
