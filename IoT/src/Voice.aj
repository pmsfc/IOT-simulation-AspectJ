import com.sun.speech.freetts.VoiceManager;

public aspect Voice {

	private VoiceManager freettsVM;
	private com.sun.speech.freetts.Voice freettsVoice;

	public Voice() {
		System.setProperty("mbrola.base", "D:\\mbrola");

		freettsVM = VoiceManager.getInstance();

		freettsVoice = freettsVM.getVoice("kevin");

		// Allocate your chosen voice
		freettsVoice.allocate();
	}

	pointcut speakUpdates() : call( * getString(i18n.Messages)) || call(* getWarningMessage());

	after() returning (String m): speakUpdates(){

		freettsVoice.speak(m);
	}
}
