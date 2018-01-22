package scbwr;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pageParser {
	
	ArrayList<String> Reps;
	static Pattern p1;  
	static Matcher m1;
	static Pattern p2;  
	static Matcher m2;
	
	pageParser(String page){
		p1 = Pattern.compile("<A href=\"download\\.php\\?replay=(.*?)\">");  
		m1 = p1.matcher(page);
		p2 = Pattern.compile("[0-9]+");  
		Reps = new ArrayList<String>();
	}
	
	public ArrayList<String> getRepsArr(){
		ArrayList<String> parsed = new ArrayList<String>();
		for(int i = 0; i < 20; i++) {
			parsed.add(getNextRep(i));
		}
		return parsed;
	}
	
	private static String getNextRep(int i) {
		m1.find();
		m2 = p2.matcher(m1.group());
		m2.find();
		m2.group();
		return m2.group();
	}
	
	
}
