import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Replay {
	
	double Duration;
	String Path;
	String Winner;
	int winnersSlot;
	
//	Replay(String repJSON) {
	Replay(String repPath) throws Exception {
		Path = repPath;
	}
	
	public double getDuration() throws Exception {
		JsonParser jp = new JsonParser();
		String frames = 	jp.parse(ReplayParser.getReplayJSON(Path))
				.getAsJsonObject()
				.get("Header")
				.getAsJsonObject()
				.get("Frames").getAsString();
		String result = String.valueOf(Math.round(Double.parseDouble(frames)/23.81));
		Duration = Double.parseDouble(result);
		
		return Duration;
	}
	
}
