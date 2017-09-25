package view;

public interface UIInterface {
	
	public void displayWelcomeMessage();
	public int readUserInt();
	public int readUserInt2();
	public String readUserString();
	public void displayMenu();
	public void notUnderstood();
	public void createMember();
	public void listMembers();
	public void deleteMember();
	public void updateMember();
	public void viewMember();
	public void registerBoat();
	public void deleteBoat();
	public void updateBoat();
	public void quit();
	public void printError(String error);
	public void printMessage(String message);

}
