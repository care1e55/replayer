package scbwr;

public class SCBWReplayer {

	public static void main(String[] args) throws Exception {
		Watcher watcher = new Watcher(23);
		watcher.initLoadReplayScreen();
		while(watcher.hasNext()) {
			try {
		watcher.watchNextReplay();			
			} catch (Exception e) {
				continue;
			}
		}
	}
}
