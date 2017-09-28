package view;

import java.util.Scanner;

public class EngConsole implements UIInterface {
	private Scanner scanner;

	public EngConsole() {
		scanner = new Scanner(System.in);
	}

	public void displayWelcomeMessage() {
		System.out.println("Welcome to the Yacht Club!\n\n");
	}

	@Override
	public void displayMenu() {
		System.out.println("\nYacht Club Main menu\n");
		System.out.println("1. Create member");
		System.out.println("2. List members");
		System.out.println("3. Delete member");
		System.out.println("4. Update member information");
		System.out.println("5. View member");
		System.out.println("6. Register boat");
		System.out.println("7. Delete boat");
		System.out.println("8. Update boat information");
		System.out.println("Q. Quit\n");
		System.out.print("Enter your choice and press enter:");

	}

	@Override
	public void notUnderstood() {
		System.out.println("Sorry, this specified value is not understood by the application\n");

	}

	@Override
	public void createMember() {
		System.out.println("Creation of a new member\n");

	}

	@Override
	public void listMembers() {
		System.out.println("Listing members\n");
		System.out.println("Enter 1 for a compact list and 2 for a detailed list\n");

	}

	@Override
	public void deleteMember() {
		System.out.println("Delete member\n");
	}

	@Override
	public void updateMember() {
		System.out.println("Update an existing member\n");
	}

	@Override
	public void viewMember() {
		System.out.println("View a member\n");
	}

	@Override
	public void registerBoat() {
		System.out.println("Register a boat on a member\n");
	}

	@Override
	public void deleteBoat() {
		System.out.println("Delete a boat from a member\n");
	}

	@Override
	public void updateBoat() {
		System.out.println("Update a boat\n");
	}

	@Override
	public void quit() {
		System.out.println("Terminating the application\n");
	}

	@Override
	public void printError(String error) {
		System.out.println("Error:\n" + error);
	}

	@Override
	public String readUserString() {
		return scanner.nextLine();
	}
	
	public int readUserInt(){
		try {
			return Integer.parseInt(this.readUserString());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}

	public void noMembers() {
		System.out.println("Not possibel to perform operation, no members registred");
	}

	public void operationFailed() {
		System.out.println("The operation was not possible to exectue");
	}

	public void operationOK() {
		System.out.println("Operation completed");
	}

	public void duplicateInformation() {
		System.out.println("Information already exists");
	}
	public void selectBoat(){
		System.out.println("Select which boat, enter the id");
	}
	public void selectName(){
		System.out.println("Specify a name");
	}

}
