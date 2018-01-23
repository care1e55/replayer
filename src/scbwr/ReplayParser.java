package scbwr;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import com.google.gson.JsonParser;

import scbwr.pageParser;
import scbwr.urlCrawler;


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
	
	public ArrayList<Replay> getReplaysOnPage (int pageNum) throws Exception {
		ArrayList<Replay> replays = new ArrayList<>();
		replays.clear();
		String page;
//	    for(int i = 2; i < 98; i++) {
	    	page = urlCrawler.getUrlContents("http://www.teamliquid.net/replay/index.php?currentpage="+pageNum);
//	    	System.out.println(page);
	    	pageParser pp = new pageParser(page);
	    	ArrayList<String> reps = pp.getRepsArr();
			StringBuilder urlString = new StringBuilder();
			for( String rep : reps ) {
				urlString.append("http://www.teamliquid.net/replay/"
						+ "download.php?replay="
						+ rep);
				File file = new File(rep + ".rep");
				URL url = new URL(urlString.toString());
				FileUtils.copyURLToFile(url, file);
				try {
					replays.add(new Replay(file.getPath().toString()));
					urlString.delete(0,urlString.length());
				} catch(Exception e) {
					file.delete();
					urlString.delete(0,urlString.length());
					continue;
				}
			}
		return replays;
	}
	
	
	
	
	
	
	
	
	
	public Replay getReplay(String rep) throws Exception {
		StringBuilder urlString = new StringBuilder();
		Replay replay;
		//download into replaydir and return initilized
		urlString.append("http://www.teamliquid.net/replay/"
				+ "download.php?replay="
				+ rep);
		File file = new File(rep + ".rep");
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
