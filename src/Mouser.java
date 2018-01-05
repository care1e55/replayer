import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Mouser {
	Mouser () throws Exception{
	Robot robot = new Robot();
	
	//wait loading 20 sec	
	TimeUnit.SECONDS.sleep(20);
	
	//single player 534 244
	robot.mouseMove(534, 244);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	TimeUnit.SECONDS.sleep(5);
	
	//expansion 708 443
	robot.mouseMove(708, 443);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	TimeUnit.SECONDS.sleep(5);
	
	//ok 862 533
	robot.mouseMove(862, 533);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	TimeUnit.SECONDS.sleep(5);
	
	//load reeplay 662 591
	robot.mouseMove(662, 591);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	TimeUnit.SECONDS.sleep(5);
	
	//ok 862 533
	robot.mouseMove(862, 533);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	TimeUnit.SECONDS.sleep(5);
	
	//speedup 955 601
	robot.mouseMove(955, 601);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
