import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Replay {
	
	double Duration;
	String Path;
	String Winner;
	int winnersSlot;
	String title;
	String repJSON;
	String speed;
	
//	Replay(String repJSON) {
	Replay(String repPath) throws Exception {
		Path = repPath;
		String repJSON = ReplayParser.getReplayJSON(Path);
		JsonParser jp = new JsonParser();
		String frames = 	jp.parse(repJSON)
				.getAsJsonObject()
				.get("Header")
				.getAsJsonObject()
				.get("Frames").getAsString();
		String result = String.valueOf(Math.round(Double.parseDouble(frames)/23.81));
		Duration = Double.parseDouble(result);		
		title = jp
				.parse(repJSON)
				.getAsJsonObject()
				.get("Header")
				.getAsJsonObject()
				.get("Title").getAsString();
		System.out.println("Initilized: " + title);
		
	}
	
	public double getDuration() throws Exception {
		return Duration;
	}
	
	public String getPath() {
		return Path;
	}
	
	public String getTitle() throws Exception {
		return title;
	}
	
	
}








