import java.util.Date;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class Warning extends Thread{
	
	private static Bezirk bezirk;
	private String message;
	private Date startDate;
	private Date endDate;
	private int time2Wait;
	
	static{
		BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Warning");
	}

	public Warning(String message, Date endDate, int time2Wait) {
		
		this.message = message;
		this.endDate = endDate;
		this.time2Wait = time2Wait;
	}
	
	@Override
	public void run() {
		
		this.startDate = new Date();
		
		while(new Date().before(endDate)){
			
			try {
				Thread.sleep(time2Wait);
				bezirk.sendEvent(new WarningUpdateEvent(message));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
