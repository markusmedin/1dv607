import java.util.LinkedList;

import com.sun.media.sound.ModelAbstractChannelMixer;

import controller.YachtClub;
import model.Boat;
import model.GsonFiles;
import model.IDataStorage;
import model.Member;
import view.UIInterface;
import view.EngConsole;

public class Main {

	public static void main(String[] args) {
		
	UIInterface ui = new EngConsole();
		
	YachtClub club = new YachtClub();
		
	club.startApplication(ui);
		
	/*	
	Member[] newMembers = new Member[100];
	Member markus = new Member("Markus",331213);
	Boat testBoat = new Boat(12,Boat.BoatType.Other, 3432424);
	markus.addBoat(testBoat);
	newMembers[0] = markus;

	
	IDataStorage files = new GsonFiles();
	
	files.storeMembers(newMembers);
	
	Member[] readMembers;
	
	readMembers = files.getMembers();
	
	System.out.println(readMembers[0].getBoats().get(0).getBoatLength());
		*/

	}

}
