import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class TemperatureSensorZirk {

	private Bezirk bezirk;
	
	public TemperatureSensorZirk() {
		
		BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Temperature Sensor Zirk");
        System.err.println("Got Bezirk instance");
	}
	
	public void sendTemperatureQualityUpdate(double t) {
    	//produces some  values since this is a mock
        final double temperature = t;
        final TemperatureUpdateEvent temperatureUpdateEvent = new TemperatureUpdateEvent(temperature);

        //sends the event
        bezirk.sendEvent(temperatureUpdateEvent);
        System.err.println("Published temperature update: " + temperatureUpdateEvent.toString());
    }
}
