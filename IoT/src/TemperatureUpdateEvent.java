import com.bezirk.middleware.messages.Event;

public class TemperatureUpdateEvent extends Event{

	private static final long serialVersionUID = 1L;
	
	private double degrees;
	
	protected TemperatureUpdateEvent(double degrees){
		
		this.degrees = degrees;
	}
	
	protected double getDegress(){
		
		return degrees;
	}
	
	@Override
	public String toString() {
		
		return String.format("temperature: %s", degrees);
	}
}
