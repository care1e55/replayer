import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Watcher {
	
	String watchedDir = "d:\\!work\\!Study\\StarCraft Brood War\\maps\\watched\\";
	static ArrayList<Replay> replays = new ArrayList<>();
	static int currentReplayIndex = -1;
	Robot robot = new Robot();
	ReplayParser parser = new ReplayParser();
		
	Watcher () throws Exception {
		replays = parser.getReplays();
	}
	
	public void initLoadReplayScreen() throws Exception {
		
		//wait loading 20 sec	
		TimeUnit.SECONDS.sleep(5);
		
		//single player
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		TimeUnit.SECONDS.sleep(2);
		
		//expansion
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		TimeUnit.SECONDS.sleep(2);
		
		//ok 
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		TimeUnit.SECONDS.sleep(2);
		
	}
	
	public void watchNextReplay() throws Exception {

		Replay replay = NextReplay();
		double waitSeconds = replay.getDuration();
		System.out.println("Watching "+replay.getTitle()+". Duration: " + waitSeconds);
		//load reeplay 
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
		TimeUnit.SECONDS.sleep(2);
		
		//ok 
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		TimeUnit.SECONDS.sleep(2);
		
		//speedup 
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
		TimeUnit.SECONDS.sleep(1);
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
		TimeUnit.SECONDS.sleep(1);
		
		//f5
		//need to define how many times press f5 from parsed winning team
		robot.keyPress(KeyEvent.VK_F5);
	    robot.keyRelease(KeyEvent.VK_F5);
	    
	    //move out the window
	    robot.mouseMove(1000, 500);
	    
	    //wait game ends
	    TimeUnit.SECONDS.sleep((long) waitSeconds);
	    
	    //press x to exit
	    robot.keyPress(KeyEvent.VK_X);
	    robot.keyRelease(KeyEvent.VK_X);
	    TimeUnit.SECONDS.sleep(5);
	    	    
	    //press o for ok
	    robot.keyPress(KeyEvent.VK_O);
	    robot.keyRelease(KeyEvent.VK_O);
	    TimeUnit.SECONDS.sleep(5);
	    
	    //delete replay from stack and move to watched folder
	    replay = Watcher.CurrentReplay();
	    replays.remove(currentReplayIndex);
	    File file = new File(replay.getPath());
	    file.renameTo(new File( watchedDir + currentReplayIndex +".rep"));
	    
	    //then load again
	}
	
	//getNextReplay
		public static Replay NextReplay() {
			currentReplayIndex++;
			Replay replay = replays.get(currentReplayIndex);
			return replay;
		}
		
		public static Replay CurrentReplay() {
			Replay replay = replays.get(currentReplayIndex);
			return replay;
		}
	
}
