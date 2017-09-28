package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;

import model.Member;
import model.Boat;
import model.Boat.BoatType;
import model.IDataStorage;
import model.GsonFiles;
import view.UIInterface;

public class YachtClub {
	private LinkedList<Member> members = new LinkedList<Member>();
	private IDataStorage dataStorage = new GsonFiles();
	private UIInterface ui;

	public void startApplication(UIInterface in_ui) {
		int menuItem = 0;
		this.ui = in_ui;
		this.ui.displayWelcomeMessage();
		this.readData();
		while (menuItem != -1) {
			this.ui.displayMenu();
			menuItem = this.ui.readUserInt();

			switch (menuItem) {
			case 1:
				this.createMember();
				this.saveData();
				break;
			case 2:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.listMembers();
				this.saveData();
				break;
			case 3:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.deleteMember();
				this.saveData();
				break;
			case 4:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.updateMember();
				this.saveData();
				break;
			case 5:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.viewMember();
				this.saveData();
				break;
			case 6:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.registerBoat();
				this.saveData();
				break;
			case 7:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.deleteBoat();
				this.saveData();
				break;
			case 8:
				if (this.anyMembers() == false) {
					this.ui.noMembers();
					break;
				}
				this.updateBoat();
				this.saveData();
				break;
			case -1:
				this.quit();
				break;
			default:
				this.ui.notUnderstood();
				break;
			}

		}

	}

	private void createMember() {
		String name = "";
		int personalNo = 0;
		this.ui.createMember();
		this.ui.selectName();
		name = this.ui.readUserString();
		this.ui.printMessage("Specify the personal no:");
		personalNo = this.ui.readUserInt();
		for (Member mem : this.members) {
			if (mem.getName().equalsIgnoreCase(name) && mem.getPersonalNo() == personalNo) {
				return;
			}
		}
		this.members.add(new Member(name, personalNo));

	}

	private void listMembers() {

		this.ui.listMembers();
		int memberDetails = this.ui.readUserInt();
		if (memberDetails == 2) {
			this.showDetailedUserList(this.members);
		} else {
			this.showCompactUserList(this.members);
		}

	}

	private void deleteMember() {
		this.listMembers();

		this.ui.deleteMember();
		int memberToDelete = this.ui.readUserInt();
		System.out.println(memberToDelete);
		for (int i = 0; i < this.members.size(); i++) {
			if (this.members.get(i).getMemberId() == memberToDelete) {
				this.members.remove(i);
				this.ui.operationOK();
				return;
			}
		}
		this.ui.operationFailed();
	}

	private void updateMember() {
		this.ui.updateMember();
		this.listMembers();
		int memberToUpdate = this.ui.readUserInt();
		for (int i = 0; i < this.members.size(); i++) {
			if (memberToUpdate == this.members.get(i).getMemberId()) {
				this.members.get(i).changeName(this.ui.readUserString());
				this.members.get(i).changePersonalNo(this.ui.readUserInt());
				this.ui.operationOK();
				return;
			}
		}
		this.ui.operationFailed();

	}

	private void viewMember() {
		this.ui.viewMember();
			this.showCompactUserList(this.members);
			this.ui.printMessage("Select a member to view the deatils on by entering the id");
			int memberToLookAt = this.ui.readUserInt();

			for (Member mem : this.members) {
				if (mem.getMemberId() == memberToLookAt) {
					this.ui.printMessage("ID: " + mem.getMemberId() + "\tName: " + mem.getName() + "\tPersonal No: "
							+ mem.getPersonalNo());
					if (mem.getBoats().size() < 1) {
						this.ui.printMessage("\tNo boats registred to " + mem.getName());
					} else {
						this.ui.printMessage("\tBoats owned by " + mem.getName());
						for (Boat boat : mem.getBoats()) {
							this.ui.printMessage("\t\t Boat id:" + boat.getBoatId() + "\tType: " + boat.getType()
									+ "\t Length: " + boat.getBoatLength());
						}
					}
					return;
				}
				this.ui.operationFailed();
			}
	}

	private void registerBoat() {
		this.ui.registerBoat();
		this.showCompactUserList(this.members);
		this.ui.printMessage("Select a member to register a boat to");
		int memberToRegisterAt = this.ui.readUserInt();
		for (Member mem : this.members) {
			if (memberToRegisterAt == mem.getMemberId()) {
				this.ui.printMessage("Enter a boat type: ");
				this.listBoatTypes();
				BoatType type = BoatType.values()[this.ui.readUserInt()-1];
				this.ui.printMessage("Enter boat length length:");
				int length = this.ui.readUserInt();
				for (Boat a_boat : mem.getBoats()) {
					if (a_boat.getBoatLength() == length && a_boat.getType() == type) {
						this.ui.duplicateInformation();
						return;
					}
				}
				mem.getBoats().add(new Boat(memberToRegisterAt, type, length));
				this.ui.operationOK();

			}
		}

	}

	private void deleteBoat() {
		this.ui.deleteBoat();
		this.showCompactUserList(this.members);
		this.ui.printMessage("Select a member to delete a boat from");
		int selectedMember = this.ui.readUserInt();
		for (Member mem : this.members) {
			if (selectedMember == mem.getMemberId()) {
				this.ui.selectBoat();
				this.listBoats(mem.getBoats());
				int boatToDelete = this.ui.readUserInt();
				for (int i = 0; i < mem.getBoats().size(); i++) {
					if (mem.getBoats().get(i).getBoatId() == boatToDelete) {
						mem.getBoats().remove(i);
						this.ui.operationOK();
						return;
					}
				}
				this.ui.operationFailed();

			}

		}
		this.ui.operationFailed();

	}

	private void updateBoat() {
		this.ui.updateBoat();
		this.showCompactUserList(this.members);
		this.ui.printMessage("Select a member to delete a boat for");
		int selectedMember = this.ui.readUserInt();
		for (Member mem : this.members) {
			if (selectedMember == mem.getMemberId()) {
				this.ui.selectBoat();
				this.listBoats(mem.getBoats());
				int boatToUpdate = this.ui.readUserInt();
				for (int i = 0; i < mem.getBoats().size(); i++) {
					if (mem.getBoats().get(i).getBoatId() == boatToUpdate) {
						this.ui.printMessage("Which type should the boat have?");
						this.listBoatTypes();
						mem.getBoats().get(i)
								.setBoatType(BoatType.values()[this.ui.readUserInt()]);
						this.ui.printMessage("What's the new length of the boat?");
						mem.getBoats().get(i).setBoatLength(this.ui.readUserInt());
						this.ui.operationOK();
						return;

					}
				}
				this.ui.operationFailed();

			}

		}
		this.ui.operationFailed();
	}

	private void quit() {
		this.ui.quit();
	}

	private void readData() {
		try {
			this.members = this.dataStorage.getMembers();
		} catch (IOException e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			this.ui.printError(errors.toString());
		}
	}

	private void saveData() {
		try {
			dataStorage.storeMembers(this.members);
		} catch (IOException e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			this.ui.printError(errors.toString());
		}

	}
	private void showDetailedUserList(LinkedList<Member> members) {
		for (Member mem : members) {
			this.ui.printMessage(
					"ID: " + mem.getMemberId() + "\tName: " + mem.getName() + "\tPersonal No: " + mem.getPersonalNo());
			if (mem.getBoats().size() < 1) {
				this.ui.printMessage("\tNo boats registred to " + mem.getName());
			} else {
				this.ui.printMessage("\tBoats owned by " + mem.getName());
				for (Boat boat : mem.getBoats()) {
					this.ui.printMessage("\t\t Boat id:" + boat.getBoatId() + "\tType: " + boat.getType()
							+ "\t Length: " + boat.getBoatLength());
				}

			}

		}
	}

	private void showCompactUserList(LinkedList<Member> members) {
		for (Member mem : members) {
			this.showDetailedMember(mem);
		}

	}

	private void showDetailedMember(Member mem) {
		this.ui.printMessage("ID: " + mem.getMemberId() + "\tName: " + mem.getName() + "\tPersonal No: "
				+ mem.getPersonalNo() + "\tBoats: " + mem.getBoats().size());

	}

	private void listBoats(LinkedList<Boat> boats) {
		if (boats.size() < 1) {
			this.ui.printMessage("\tNo boats registred to the member");
		} else {
			this.ui.printMessage("\tBoats owned by the member");
			for (Boat boat : boats) {
				this.ui.printMessage("\t\t Boat id:" + boat.getBoatId() + "\tType: " + boat.getType() + "\t Length: "
						+ boat.getBoatLength());
			}

		}

	}

	private void listBoatTypes() {
		int counter = 1;
		for (BoatType type : BoatType.values()) {
			this.ui.printMessage(counter + ". " + type.toString());
			counter++;
		}

	}

	private boolean anyMembers() {
		return this.members.size() != 0;
	}
}
