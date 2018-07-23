
public aspect LightSignalization {
	
	private SmartBulb bulb;
	
	public LightSignalization(){
		bulb = new SmartBulb();
		
	}
	
	pointcut lightSignalizationUpdates() : call( * getString(i18n.Messages)) || call(* getWarningMessage());
	
	after() returning (String m): lightSignalizationUpdates(){

		bulb.setAlertStateOn();
	}
			
		
	}


