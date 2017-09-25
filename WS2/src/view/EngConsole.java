package view;
import java.util.Scanner; 

public class EngConsole implements UIInterface {
	private Scanner scanner;
	
	public EngConsole(){
		 scanner = new Scanner(System.in);
	}

	public void displayWelcomeMessage() {
		System.out.println("Welcome to the Yacht Club!\n\n");
	}


	public int readUserInt() {
		String input;
		while (scanner.hasNext() == true){
			input = scanner.nextLine();
			
			if (input.compareToIgnoreCase("q") == 0){
				return -1;} 
			else{
				try {
					return Integer.parseInt (input);
				}
				catch (NumberFormatException e){
					return 0;
				}
				
			}
			

		}


		
		return 0;
	}

	@Override
	public void displayMenu() {
		System.out.println("Yacht Club Main menu\n");
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
		String input = null;
		while (scanner.hasNext() == false){
			input = scanner.nextLine();	
		}
		return input;
	}
	public int readUserInt2() {
		String input = null;
		while (scanner.hasNext() == false){
			input = scanner.nextLine();	
		}
		try {
			return Integer.parseInt (input);
		}
		catch (NumberFormatException e){
			return 0;
		}
	}

	@Override
	public void printMessage(String message) {
		System.out.println(message);
		
	}

}
