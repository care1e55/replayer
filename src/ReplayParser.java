import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ReplayParser {
//parse at runtime using go parser
	
	static ArrayList<String> replays = new ArrayList<>();
	static int currentReplayIndex = 0;
	String scriptPath = "\"D:\\Users\\user\\go\\src\\github.com\\icza\\screp\\cmd\\screp\\screp.go\"";
	String replayDirPath = "D:\\!work\\!Study\\StarCraft Brood War\\maps\\replays";
	String goPath = "D:\\progz\\Go\\bin\\go.exe";
	
	
	public ReplayParser() throws Exception {
		try (Stream<Path> paths = Files.walk(Paths.get(replayDirPath))) {
			paths
			.filter(Files::isRegularFile)
			.forEach(s -> replays.add(s.toString()));
		} 
	}
	
	
//	JsonObject jsonObject = new JsonParser().parse("{\"name\": \"John\"}").getAsJsonObject();
//	System.out.println(jsonObject.get("name").getAsString());
	
	
	public String execParser(String replay) throws Exception {
		String jsonString = null;
		
		Runtime rt = Runtime.getRuntime();
		System.out.println(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		Process pr = rt.exec(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		
		   InputStream in = pr.getInputStream();
		   String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		   System.out.println(result);
		   
//		   in.read()
//		    int c;
//		    while ((c = in.read()) != -1) {
//		      System.out.println((char) c);
//		    }
//		    in.close();
		
		return jsonString;
	}
	
	//getNextReplay
	public String getNextReplay() {		
		currentReplayIndex++;
		String replay = replays.get(currentReplayIndex);
		return replay;
	}
	
	public String getCurrentReplay() {		
		String replay = replays.get(currentReplayIndex);
		return replay;
	}
	
	
}
