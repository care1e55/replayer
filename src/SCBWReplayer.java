import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;

public class SCBWReplayer {

	public static void main(String[] args) throws Exception {
		ReplayParser repparse = new ReplayParser();
		JsonObject currep = repparse.execParser(repparse.getCurrentReplay());
		String duration = repparse.getDuration(currep);
		System.out.println(duration);
//		Mouser ms = new Mouser();
		
		
	}

}
