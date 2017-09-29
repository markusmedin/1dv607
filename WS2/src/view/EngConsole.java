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

	public void displayMenu() {
		System.out.println("\nYacht Club Main menu\n");
		System.out.println("1. Create member");
		System.out.println("2. List members");
		System.out.println("3. Delete member");
		System.out.println("4. Update member information");
		System.out.println("5. View member\n");
		System.out.println("6. Register boat");
		System.out.println("7. Delete boat");
		System.out.println("8. Update boat information\n");
		System.out.println("-1. Quit\n");
		System.out.print("Enter your choice and press enter:\n");

	}

	public void notUnderstood() {
		System.out.println("Sorry, this specified value is not understood by the application\n");

	}

	public void createMember() {
		System.out.println("Creation of a new member\n");

	}

	public void listMembers() {
		System.out.println("Listing members\n");
		System.out.println("Enter 1 for a compact list and 2 for a detailed list\n");

	}

	public void deleteMember() {
		System.out.println("Delete member\n");
	}

	public void updateMember() {
		System.out.println("Update an existing member\n");
	}

	public void viewMember() {
		System.out.println("View a member\n");
	}

	public void registerBoat() {
		System.out.println("Register a boat on a member\n");
	}

	public void deleteBoat() {
		System.out.println("Delete a boat from a member\n");
	}

	public void updateBoat() {
		System.out.println("Update a boat\n");
	}

	public void quit() {
		System.out.println("Terminating the Yacht Club application\n");
		
	}

	public void printError(String error) {
		System.out.println("Error:\n" + error + "\n");
	}

	public String readUserString() {
		return scanner.nextLine();
	}

	public int readUserInt() {
		try {
			return Integer.parseInt(this.readUserString());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void noMembers() {
		System.out.println("Not possible to perform operation, no members registred\n");
	}

	public void operationFailed() {
		System.out.println("The operation was not possible to exectue\n");
	}

	public void operationOK() {
		System.out.println("Operation completed\n");
	}

	public void duplicateInformation() {
		System.out.println("Information already exists\n");
	}

	public void selectBoat() {
		System.out.println("Select boat id:\n");
	}

	public void selectName() {
		System.out.println("Specify the name:\n");
	}

	public void selectPersonalNo() {
		System.out.println("Specify the personal no:\n");
	}

	public void selectMemberId() {
		System.out.println("Specify the member id:\n");

	}

	public void selectBoatType() {
		System.out.println("Chose one of the following boat types:\n");
	}

	public void selectBoatLength() {
		System.out.println("Enter the length of the boat:\n");
	};

}
