
import controller.YachtClub;
import view.UIInterface;
import view.EngConsole;

public class Main {

	public static void main(String[] args) {

		UIInterface ui = new EngConsole();

		YachtClub controller = new YachtClub();

		controller.startApplication(ui);

	}

}