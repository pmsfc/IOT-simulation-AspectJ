import java.text.MessageFormat;
import java.util.Date;
import java.util.Random;

import i18n.I18N;
import static i18n.Messages.INCREASE_TEMPERATURE_MSG;
import static i18n.Messages.DECREASE_TEMPERATURE_MSG;
import static i18n.Messages.MAINTAIN_TEMPERATURE_MSG;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class IntelligentHouseZirk {
	public IntelligentHouseZirk() {
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware
				.registerZirk("Asthma Assistant Zirk");
		
		final EventSet temperatureEvents = new EventSet(
				TemperatureUpdateEvent.class);
		final EventSet warningEvents = new EventSet(
				WarningUpdateEvent.class);

		temperatureEvents.setEventReceiver(new EventSet.EventReceiver() {

			@Override
			public void receiveEvent(Event event, ZirkEndPoint arg1) {
				if (event instanceof TemperatureUpdateEvent) {
					final TemperatureUpdateEvent up = (TemperatureUpdateEvent) event;

					if (up.getDegress() < 20.0) {
						System.out.println(I18N.getString(INCREASE_TEMPERATURE_MSG));
					} else if (up.getDegress() > 30.0) {
						System.out.println(I18N.getString(DECREASE_TEMPERATURE_MSG));
					} else {
						System.out.println(I18N.getString(MAINTAIN_TEMPERATURE_MSG));
					}
				}
			}
		});
		
		warningEvents.setEventReceiver(new EventSet.EventReceiver() {

			@Override
			public void receiveEvent(Event event, ZirkEndPoint arg1) {
				if (event instanceof WarningUpdateEvent) {
					final WarningUpdateEvent up = (WarningUpdateEvent) event;
						System.out.println(up.getWarningMessage());
				}
			}
		});

		bezirk.subscribe(temperatureEvents);
		bezirk.subscribe(warningEvents);

		TemperatureSensorZirk tsz = new TemperatureSensorZirk();
		
		
		//Warning aviso = new Warning("Take your drugs", new Date(2016, 12, 22), 5000);
		
		//aviso.start();
		
		
		while (true) {
			Random rd = new Random();
			double sortes = 10 + (40 - 10) * rd.nextDouble();

			tsz.sendTemperatureQualityUpdate(sortes);

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		new IntelligentHouseZirk();
	}
}
