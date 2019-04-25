import MyUtil.OperateFrame;
import autocell.*;
import tmp.*;

class Main {
	public Main() {
		new OperateFrame(new AutoCell().controlGOL());
	}
	public static void main(String[] args) {
		new Main();
	 }
}
