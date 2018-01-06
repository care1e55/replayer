import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import com.google.gson.JsonParser;


public class ReplayParser {
	static String scriptPath = "\"D:\\Users\\user\\go\\src\\github.com\\icza\\screp\\cmd\\screp\\screp.go\"";
	static String replayDirPath = "D:\\!work\\!Study\\StarCraft Brood War\\maps\\replays";
	static String goPath = "D:\\progz\\Go\\bin\\go.exe";
	
	
	ReplayParser() throws Exception {
	}
	
	public ArrayList<Replay> getReplays () throws Exception {
		ArrayList<Replay> replays = new ArrayList<>();
		replays.clear();
		try (Stream<Path> paths = Files.walk(Paths.get(replayDirPath))) {
			paths
			.filter(Files::isRegularFile)
			.forEach(s -> {
				try {
					replays.add(new Replay(s.toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {}
		return replays;
	}
	
	public static String getReplayJSON(String replay) throws Exception {
		Runtime rt = Runtime.getRuntime();
//		System.out.println(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		Process pr = rt.exec(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		InputStream in = pr.getInputStream();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		return result;
	}
}
