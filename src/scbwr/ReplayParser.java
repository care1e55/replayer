package scbwr;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ReplayParser {
	static String scriptPath = "\"D:\\Users\\user\\go\\src\\github.com\\icza\\screp\\cmd\\screp\\screp.go\"";
	static String replayDirPath = "D:\\!work\\!Study\\StarCraft Brood War\\maps\\replays\\";
	static String goPath = "D:\\progz\\Go\\bin\\go.exe";
	
	
	ReplayParser() throws Exception {
	}
	
	public Replay getReplay(String rep) throws Exception {
		StringBuilder urlString = new StringBuilder();
		Replay replay;
		urlString.append("http://www.teamliquid.net/replay/"
				+ "download.php?replay="
				+ rep);
		File file = new File(replayDirPath + rep + ".rep");
		URL url = new URL(urlString.toString());
		FileUtils.copyURLToFile(url, file);
		try {
			replay = new Replay(file.getPath().toString());
			urlString.delete(0,urlString.length());
			return replay;
		} catch(Exception e) {
			file.delete();
			urlString.delete(0,urlString.length());
			return null;
		}		
	}
	
	public static String getReplayJSON(String replay) throws Exception {
		Runtime rt = Runtime.getRuntime();
		System.out.println(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		try {
		Process pr = rt.exec(goPath + " run " + scriptPath + " " + "\"" + replay + "\"");
		InputStream in = pr.getInputStream();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		System.out.println(result);
		return result;
		} catch(Exception e) {
			return null;
		}
	}
}
