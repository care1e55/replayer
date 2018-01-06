import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Mouser {
	Mouser () throws Exception{
	Robot robot = new Robot();
	
	//wait loading 20 sec	
	TimeUnit.SECONDS.sleep(20);
	
	//single player
	robot.keyPress(KeyEvent.VK_S);
	robot.keyRelease(KeyEvent.VK_S);
	TimeUnit.SECONDS.sleep(5);
	
	//expansion
	robot.keyPress(KeyEvent.VK_E);
	robot.keyRelease(KeyEvent.VK_E);
	TimeUnit.SECONDS.sleep(5);
	
	//ok 
	robot.keyPress(KeyEvent.VK_O);
	robot.keyRelease(KeyEvent.VK_O);
	TimeUnit.SECONDS.sleep(5);
	
	//load reeplay 
	robot.keyPress(KeyEvent.VK_R);
	robot.keyRelease(KeyEvent.VK_R);
	TimeUnit.SECONDS.sleep(5);
	
	//ok 
	robot.keyPress(KeyEvent.VK_O);
	robot.keyRelease(KeyEvent.VK_O);
	TimeUnit.SECONDS.sleep(5);
	
	//speedup 
	robot.keyPress(KeyEvent.VK_U);
	robot.keyRelease(KeyEvent.VK_U);
	TimeUnit.SECONDS.sleep(5);
	
	//f5
	//need to define how many times press f5 from parsed winning team
	robot.keyPress(KeyEvent.VK_F5);
    robot.keyRelease(KeyEvent.VK_F5);
    
    //move out the window
    robot.mouseMove(1000, 500);
    
    //wait game ends
    //here compute waiting time from parsed
    
    //press x to exit
    
    //press o for ok
    
    //then load again
	}
 

}
