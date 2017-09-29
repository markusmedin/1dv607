package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;

import model.Member;
import model.Boat;
import model.Boat.BoatType;
import model.GsonFiles;
import model.IDataStorage;
import view.UIInterface;

public class YachtClub {
	private LinkedList<Member> members = new LinkedList<Member>();
	private IDataStorage dataStorage = new GsonFiles();

	public void startApplication(UIInterface a_ui) {

		int selectedMenuItem = 0;

		a_ui.displayWelcomeMessage();
		this.readStoredData(a_ui);

		// Main menu
		while (selectedMenuItem != -1) {
			a_ui.displayMenu();
			selectedMenuItem = a_ui.readUserInt();

			switch (selectedMenuItem) {
			case 1:
				this.createMember(a_ui);
				this.saveData(a_ui);
				break;
			case 2:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.listMembers(a_ui);
				this.saveData(a_ui);
				break;
			case 3:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.deleteMember(a_ui);
				this.saveData(a_ui);
				break;
			case 4:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.updateMember(a_ui);
				this.saveData(a_ui);
				break;
			case 5:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.viewMember(a_ui);
				this.saveData(a_ui);
				break;
			case 6:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.registerBoat(a_ui);
				this.saveData(a_ui);
				break;
			case 7:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.deleteBoat(a_ui);
				this.saveData(a_ui);
				break;
			case 8:
				if (this.anyMembers() == false) {
					a_ui.noMembers();
					break;
				}
				this.updateBoat(a_ui);
				this.saveData(a_ui);
				break;
			case -1:
				this.quit(a_ui);
				break;
			default:
				a_ui.notUnderstood();
				break;
			}

		}

	}

	private void createMember(UIInterface a_ui) {
		String memberName = "";
		int memberPersonalNo = 0;

		// Display heading
		a_ui.createMember();

		// Request name
		a_ui.selectName();
		memberName = a_ui.readUserString();

		// Request personal number
		a_ui.selectPersonalNo();
		memberPersonalNo = a_ui.readUserInt();

		// Validate unique member
		if (this.memberExsists(memberName, memberPersonalNo)) {
			a_ui.duplicateInformation();
			return;
		}

		// Add member
		this.members.add(new Member(memberName, memberPersonalNo));

	}

	private void listMembers(UIInterface a_ui) {
		// Display heading
		a_ui.listMembers();

		// Detailed or compact view
		int detailedList = a_ui.readUserInt();
		if (detailedList == 2) {

			this.showDetailedMemberList(a_ui, this.members);
		} else {

			this.showCompactMemberList(a_ui, this.members);
		}

	}

	private void deleteMember(UIInterface a_ui) {

		// Display heading
		a_ui.deleteMember();

		// Select member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (int i = 0; i < this.members.size(); i++) {

				if (this.members.get(i).getMemberId() == memberId) {

					// Removal of member
					this.members.remove(i);
					a_ui.operationOK();
					return;
				}
			}
		} else {

			// Member not found
			a_ui.operationFailed();
		}

	}

	private void updateMember(UIInterface a_ui) {
		// Display heading
		a_ui.updateMember();

		// Select member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();
		if (this.validMemberId(memberId)) {

			for (int i = 0; i < this.members.size(); i++) {

				if (this.members.get(i).getMemberId() == memberId) {

					// Update member
					a_ui.selectName();
					this.members.get(i).changeName(a_ui.readUserString());
					a_ui.selectPersonalNo();
					this.members.get(i).changePersonalNo(a_ui.readUserInt());
					a_ui.operationOK();
					return;
				}
			}
		} else {
			// Member not found
			a_ui.operationFailed();
		}

	}

	private void viewMember(UIInterface a_ui) {
		// Display heading
		a_ui.viewMember();

		// Select member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member a_member : this.members) {

				if (a_member.getMemberId() == memberId) {
					// Print member information
					this.showDetailedMember(a_ui, a_member);
					return;
				}
			}

		} else {
			// Member not found
			a_ui.operationFailed();
		}

	}

	private void registerBoat(UIInterface a_ui) {
		// Display heading
		a_ui.registerBoat();

		// Select  member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			// Select boat information
			BoatType a_type = this.getBoatTypes(a_ui);
			a_ui.selectBoatLength();
			int boatLength = a_ui.readUserInt();
			for (Member a_member : this.members) {

				if (a_member.getMemberId() == memberId) {

					for (Boat a_boat : a_member.getBoats()) {

						if (a_boat.getBoatLength() == boatLength && a_boat.getType() == a_type) {
							// Boat found and not unique
							a_ui.duplicateInformation();
							return;
						} 

					}
					// Adding boat
					a_member.getBoats().add(new Boat(memberId, a_type, boatLength));
					a_ui.operationOK();
				}

			}

		} else {

			// Member not found
			a_ui.operationFailed();
		}

	}

	private void deleteBoat(UIInterface a_ui) {
		// Display heading
		a_ui.deleteBoat();

		// Select member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member a_member : this.members) {

				if (a_member.getMemberId() == memberId) {

					// Select boat
					int boatId = this.getBoatId(a_ui, a_member);
					for (int i = 0; i < a_member.getBoats().size(); i++) {

						if (a_member.getBoats().get(i).getBoatId() == boatId) {

							// Removal of boat
							a_member.getBoats().remove(i);
							a_ui.operationOK();
							return;
						}
					}

					// Boat not found
					a_ui.operationFailed();
				} else {

					// Member not found
					a_ui.operationFailed();
				}

			}
		}

	}

	private void updateBoat(UIInterface a_ui) {

		// Display heading
		a_ui.updateBoat();

		// Select member
		this.showCompactMemberList(a_ui, this.members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member a_member : this.members) {

				if (a_member.getMemberId() == memberId) {

					// Select boat
					int boatId = this.getBoatId(a_ui, a_member);
					for (int i = 0; i < a_member.getBoats().size(); i++) {
						if (a_member.getBoats().get(i).getBoatId() == boatId) {

							// Update boat
							a_member.getBoats().get(i).setBoatType(this.getBoatTypes(a_ui));
							a_ui.selectBoatLength();
							int boatLength = a_ui.readUserInt();
							a_member.getBoats().get(i).setBoatLength(boatLength);
							a_ui.operationOK();
							return;
						}
					}

					// Boat not found
					a_ui.operationFailed();
				} else {

					// Member not found
					a_ui.operationFailed();
				}

			}
		}
	}

	private void quit(UIInterface a_ui) {
		// Display quit information
		a_ui.quit();
	}

	private void readStoredData(UIInterface a_ui) {
		// Reading of persistent date

		try {

			this.members = this.dataStorage.getMembers();
		} catch (IOException e) {
			// Printing of errors
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			a_ui.printError(errors.toString());
		}
	}

	private void saveData(UIInterface a_ui) {
		// Saving data
		try {
			dataStorage.storeMembers(this.members);
		} catch (IOException e) {
			// Printing of errors
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			a_ui.printError(errors.toString());
		}

	}

	private void showDetailedMemberList(UIInterface a_ui, LinkedList<Member> a_members) {

		for (Member a_member : a_members) {

			this.showDetailedMember(a_ui, a_member);
		}
	}

	private void showCompactMemberList(UIInterface a_ui, LinkedList<Member> a_members) {

		for (Member a_member : a_members) {

			this.showCompactMember(a_ui, a_member);
		}

	}

	private void showCompactMember(UIInterface a_ui, Member a_member) {

		// Printing compact member view
		a_ui.printMessage("ID: " + a_member.getMemberId() + "\tName: " + a_member.getName() + "\tPersonal No: "
				+ a_member.getPersonalNo() + "\tBoats: " + a_member.getBoats().size());

	}

	private void showDetailedMember(UIInterface a_ui, Member a_member) {

		// Printing detailed member view
		a_ui.printMessage("ID: " + a_member.getMemberId() + "\tName: " + a_member.getName() + "\tPersonal No: "
				+ a_member.getPersonalNo());
		this.listBoats(a_ui, a_member.getBoats());

	}

	private void listBoats(UIInterface a_ui, LinkedList<Boat> a_boats) {
		// Printing boats

		if (a_boats.size() < 1) {

			a_ui.printMessage("\tNo boats registred to the member");
		} else {

			a_ui.printMessage("\tBoats owned by the member");
			for (Boat a_boat : a_boats) {

				a_ui.printMessage("\t\t Boat id:" + a_boat.getBoatId() + "\tType: " + a_boat.getType() + "\t Length: "
						+ a_boat.getBoatLength());
			}

		}

	}

	private BoatType getBoatTypes(UIInterface a_ui) {
		// Selecting boattype
		a_ui.selectBoatType();
		int counter = 1;
		for (BoatType a_type : BoatType.values()) {
			a_ui.printMessage(counter + ". " + a_type.toString());
			counter++;
		}

		counter = a_ui.readUserInt() - 1;

		// Preventing illegal values
		if (counter > 3)
			counter = 0;

		return BoatType.values()[counter];

	}

	private boolean anyMembers() {

		// Checks if there are any members registered
		return this.members.size() != 0;
	}

	private boolean memberExsists(String memberName, int memberPersonalNo) {
		// Checks is a member exists
		for (Member a_member : this.members) {
			if (a_member.getName().equalsIgnoreCase(memberName) && a_member.getPersonalNo() == memberPersonalNo) {
				return true;
			}
		}

		return false;

	}

	private boolean validMemberId(int memberIdNo) {
		// Validating a memberId
		for (Member a_member : this.members) {
			if (a_member.getMemberId() == memberIdNo) {
				return true;
			}
		}

		return false;
	}

	private int getBoatId(UIInterface a_ui, Member a_member) {

		this.listBoats(a_ui, a_member.getBoats());
		a_ui.selectBoat();
		return a_ui.readUserInt();
	}
}
