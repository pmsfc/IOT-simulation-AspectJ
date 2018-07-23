import i18n.I18N;
import static i18n.Messages.BULB_ON_MSG;
import static i18n.Messages.BULB_ADVICE_MSG;


public class SmartBulb {


	public SmartBulb() {

		System.out.println(I18N.getString(BULB_ON_MSG));
	}

	public void setAlertStateOn() {
		
		System.out.println(I18N.getString(BULB_ADVICE_MSG));
		
	}

	







}
