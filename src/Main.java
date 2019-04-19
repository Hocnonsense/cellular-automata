import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import MyUtil.OperateFrame;
import autocell.*;
import tmp.*;

class Main {
	public Main() {
		new OperateFrame(new autoCell().controlGOC());
	}
	public static void main(String[] args) {
		new Main();
	 }
}