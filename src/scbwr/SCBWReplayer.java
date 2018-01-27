package scbwr;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class SCBWReplayer {
	

	public static void main(String[] args) throws Exception {
		//TODO:
		//* Swing frame;
		//* The page have to be passed as parameter clicking radio button;
		//* The page has to be initial and the program has to go further;
		//* Add output console;
		//* Automate scbw and camrep execution;
		//* Multithreading
		//* close all on close
	
//		Runtime rt = Runtime.getRuntime();
//		System.out.println(bwexec);
//		rt.exec("cmd /C start "+bwexec);
//		rt.exec(canrepexec);

		scbwFrame frame = new scbwFrame();
		frame.setResizable(false);
		frame.setVisible(true);
//		frame.doc.insertString(frame.doc.getLength(), "Olololo\n", null);
		
//		Watcher watcher = new Watcher(16);
//		watcher.initLoadReplayScreen();
//		while(watcher.hasNext()) {
//			try {
//		watcher.watchNextReplay();			
//			} catch (Exception e) {
//				continue;
//			}
//		}
		
		
	}
}
