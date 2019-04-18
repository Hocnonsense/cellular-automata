import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import autocell.*;

class Main {
	public static void main(String[] args) {
		GameOfLife life = new GameOfLife();
		Utils.launchFrame(life); // parent generation ??what @haor
		Executors.newScheduledThreadPool(10).scheduleAtFixedRate(life::tick, 0, 100, TimeUnit.MILLISECONDS);
		// scheduleAtFixedRate do the job for given relay
	 }
}