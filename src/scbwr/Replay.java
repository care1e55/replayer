package scbwr;
import com.google.gson.JsonParser;

public class Replay {
	
	double Duration;
	String Path;
	String Winner;
	int winnersSlot;
	String title;
	String repJSON;
	String speed;
	
	Replay(String repPath) throws Exception {
		Path = repPath;
		String repJSON = ReplayParser.getReplayJSON(Path);
		System.out.println(repJSON);
		if (repJSON != null ) {
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
		winnersSlot = Integer.parseInt(jp
				.parse(repJSON)
				.getAsJsonObject()
				.get("Computed")
				.getAsJsonObject()
				.get("WinnerTeam").getAsString());
		System.out.println("Initilized: " + title);
		} else throw new Exception();
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








