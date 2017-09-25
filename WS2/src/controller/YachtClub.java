package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import model.Member;
import model.Boat;
import model.IDataStorage;
import model.GsonFiles;
import view.UIInterface;

public class YachtClub {
	private int menuItem = 0;
	private Member[] members = new Member[100];
	private IDataStorage dataStorage = new GsonFiles();
	private UIInterface ui;
	
	public void startApplication(UIInterface in_ui){
		this.ui = in_ui;
		ui.displayWelcomeMessage();
		this.readData();
		while (menuItem != -1){
		ui.displayMenu();
		
		menuItem = ui.readUserInt();
		switch (menuItem){
		case 1:
			this.createMember();
			break;
		case 2:
			this.listMembers();
			break;
		case 3:
			this.deleteMember();
			break;
		case 4:
			this.updateMember();
			break;
		case 5:
			this.viewMember();
			break;
		case 6:
			this.registerBoat();
			break;
		case 7:
			this.deleteBoat();
			break;
		case 8:
			this.updateBoat();
			break;
		case -1:
			this.quit();
			break;
		default:
			this.ui.notUnderstood();
			break;
		}
		
		}
		this.saveData();
		
	}
private void createMember(){
	String name = "";
	int personalNo = 0;
	this.ui.createMember();
	this.ui.printMessage("Specify the name:");;
	name = this.ui.readUserString();
	this.ui.printMessage("Specify the personal no:");
	personalNo = this.ui.readUserInt2();
	
	
}
private void listMembers(){
	this.ui.listMembers();
}
private void deleteMember(){
	this.ui.deleteMember();
	
}
private void updateMember(){
	this.ui.updateMember();
	
}
private void viewMember(){
	this.ui.viewMember();
	
}
private void registerBoat(){
	this.ui.registerBoat();
	
}
private void deleteBoat(){
	this.ui.deleteBoat();
	
}
private void updateBoat(){
	this.ui.updateBoat();
}
private void quit(){
	this.ui.quit();
}
private void readData(){
	try {
		this.members = this.dataStorage.getMembers();
	} catch (IOException e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		this.ui.printError(errors.toString());
	}
}
private void saveData(){
try {
		dataStorage.storeMembers(this.members);
	} catch (IOException e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		this.ui.printError(errors.toString());
	}
	
}


	

}
