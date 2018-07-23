import com.bezirk.middleware.messages.Event;


public class WarningUpdateEvent extends Event{

private static final long serialVersionUID = 1L;
	
	private String message;
	
	protected WarningUpdateEvent(String message){
		
		this.message = message;
	}
	
	protected String getWarningMessage(){
		
		return message;
	}
	
	@Override
	public String toString() {
		
		return String.format(message);
	}
}
