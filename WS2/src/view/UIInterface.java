package view;

public interface UIInterface {

	public void displayWelcomeMessage();

	public String readUserString();

	public int readUserInt();

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

	public void noMembers();

	public void operationFailed();

	public void operationOK();

	public void duplicateInformation();

	public void selectBoat();

	public void selectName();
	
	public void selectPersonalNo();

	public void selectMemberId();
	
	public void selectBoatType();
	
	public void selectBoatLength();
}
