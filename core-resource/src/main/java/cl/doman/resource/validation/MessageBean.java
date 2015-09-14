package cl.doman.resource.validation;

public class MessageBean {
	static public enum MessageType {SUCCESS,FAIL};
	
	private MessageType messageType;
	private String backurl;
	
	
	public MessageBean(MessageType messageType,String backurl ){
		this.messageType = messageType;
		this.backurl = backurl;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getBackurl() {
		return backurl;
	}
	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}
}
