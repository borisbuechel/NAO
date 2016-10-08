package de.hamburg.ibm.nao.helloworld;

import java.io.File;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;

/**
 * NAO Libraries https://github.com/hbxu/hello-nao/raw/master/lib/java-naoqi-sdk-2.1.4.13-win32-vs2010.jar
 * Watson Library https://github.com/watson-developer-cloud/java-sdk/releases/download/parent-3.4.0/java-sdk-3.4.0-jar-with-dependencies.jar
 * 
 * @author DE152691
 *
 */
public class NAOSayHelloSpeechToText {

	public static void main(String[] args) {

		try {
			String robotUrl = "tcp://127.0.0.1:50097";
			
			// Create a new application
			Application application = new Application(args, robotUrl);
			// Start your application
			application.start();

			SpeechToText service = new SpeechToText();
			service.setUsernameAndPassword("3cd51759-484d-4872-a715-c1070dd0fXXX", "IcIdnJhvXXX");
			service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");

			// Erzeugt durch Start->search programs and files-> type in
			// 'soundrecorder /file test.wav'
			File audio = new File("C:/Users/XXXX/Desktop/test.wav");

			SpeechResults transcript = service.recognize(audio).execute();

			Transcript transcript2 = transcript.getResults().get(0);
			String myText = transcript2.getAlternatives().get(0).getTranscript();

			ALTextToSpeech ttsAnswer = new ALTextToSpeech(application.session());
			ttsAnswer.say(myText);

			// Spracherkennung, hat bei mir Lokal nicht funktioniert
			// ALSpeechRecognition sd = new ALSpeechRecognition (session);
			// sd.setLanguage("English");
			//
			// ArrayList<String> vocabulary = new ArrayList<String>();
			// vocabulary.add("yes");
			// vocabulary.add("no");
			// vocabulary.add("listen");
			//
			// sd.setVocabulary(vocabulary, true);
			//
			// // Start the speech recognition engine with user Test_ASR
			// sd.subscribe("Test_ASR");
			//
			// ReactToEventSpeechDetected reactor = new
			// ReactToEventSpeechDetected();
			// reactor.run(application.session());
			// 2 Minuten Spracherkennung
			// Thread.sleep(1000*60*2);
			//
			// sd.unsubscribe("Test_ASR");

			application.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
