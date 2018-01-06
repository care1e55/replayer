import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
	
	public String execParser(String replay) throws Exception {
		Runtime rt = Runtime.getRuntime();
		System.out.println(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		Process pr = rt.exec(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		InputStream in = pr.getInputStream();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		return result;
	}
	
	public String getDuration(String repJSON) {
		JsonParser jp = new JsonParser();
		String frames = 	jp.parse(repJSON)
							.getAsJsonObject()
							.get("Header")
							.getAsJsonObject()
							.get("Frames").getAsString();
		String result = String.valueOf(Math.round(Double.parseDouble(frames)/23.81));
		return result;
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
