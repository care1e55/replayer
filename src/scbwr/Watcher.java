package scbwr;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Watcher {
	
	String watchedDir = "d:\\!work\\!Study\\StarCraft Brood War\\maps\\watched\\";
	String page;
	String currentReplay;
	static int currentReplayIndex = -1;
	static int ReplayCounter = 0;
	Robot robot = new Robot();
	ReplayParser parser = new ReplayParser();
	public ArrayList<String> replaysS = new ArrayList<>();
	pageParser pparser;
	
	Watcher (int PageNum) throws Exception {
		page = urlCrawler.getUrlContents("http://www.teamliquid.net/replay/index.php?currentpage="+PageNum);
		pparser = new pageParser(page);
		replaysS = pparser.getRepsArr();
	}
	
	public void initLoadReplayScreen() throws Exception {
		
		//wait loading 20 sec	
		TimeUnit.SECONDS.sleep(5);
		
		//single player
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		TimeUnit.SECONDS.sleep(2);
		
		//expansion
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		TimeUnit.SECONDS.sleep(2);
		
		//ok 
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		TimeUnit.SECONDS.sleep(2);
		
	}
	
	public void watchNextReplay() throws Exception {
		currentReplay = replaysS.get(currentReplayIndex++);
		Replay replay = parser.getReplay(currentReplay);
		ReplayCounter++;
		if (replay==null) {
			throw new Exception("replay is null");
		}
		double waitSeconds = replay.getDuration();
		System.out.println("Watching "+replay.getTitle()+". Duration: " + waitSeconds + " WinnerSlot: " + replay.winnersSlot );
		//load reeplay 
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
		TimeUnit.SECONDS.sleep(2);
		
		//ok 
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		TimeUnit.SECONDS.sleep(2);
		
		//speedup 
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
		TimeUnit.SECONDS.sleep(1);
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
		TimeUnit.SECONDS.sleep(1);
		
		//f5
		//need to define how many times press f5 from parsed winning team
		WindowFinder.findWindow();
		robot.keyPress(KeyEvent.VK_F5);
	    robot.keyRelease(KeyEvent.VK_F5);
	    
	    //move out the window
	    robot.mouseMove(1000, 500);
	    
	    //wait game ends
	    TimeUnit.SECONDS.sleep((long) waitSeconds+30);
	    
	    //press x to exit
		WindowFinder.findWindow();
	    robot.keyPress(KeyEvent.VK_X);
	    robot.keyRelease(KeyEvent.VK_X);
	    TimeUnit.SECONDS.sleep(5);
	    	    
	    //press o for ok
		WindowFinder.findWindow();
	    robot.keyPress(KeyEvent.VK_O);
	    robot.keyRelease(KeyEvent.VK_O);
	    TimeUnit.SECONDS.sleep(5);
	    
	    //delete replay from stack and move to watched folder
	    File file = new File(replay.getPath());
	    file.renameTo(new File( watchedDir + currentReplay +".rep"));
	    //then load again
	}
	
	public boolean hasNext() {
		if (ReplayCounter > replaysS.size()) return false;
		return true;
	}
}
