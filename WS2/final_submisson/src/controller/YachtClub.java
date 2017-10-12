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

	private LinkedList<Member> m_members;
	private IDataStorage m_dataStorage;

	public void startApplication(UIInterface a_ui) {
		//Start function of the controller

		this.m_members = new LinkedList<Member>();
		this.m_dataStorage = new GsonFiles();

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
		//Function to create a new member

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
		this.m_members.add(new Member(memberName, memberPersonalNo));

	}

	private void listMembers(UIInterface a_ui) {
		//Function to list all members

		// Display heading
		a_ui.listMembers();

		// Detailed or compact view
		int detailedList = a_ui.readUserInt();
		if (detailedList == 2) {

			this.showDetailedMemberList(a_ui, this.m_members);
		} else {

			this.showCompactMemberList(a_ui, this.m_members);
		}

	}

	private void deleteMember(UIInterface a_ui) {
		//Function to delete a member

		// Display heading
		a_ui.deleteMember();

		// Select member
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (int i = 0; i < this.m_members.size(); i++) {

				if (this.m_members.get(i).getMemberId() == memberId) {

					// Removal of member
					this.m_members.remove(i);
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
		//Function to update a member

		// Display heading
		a_ui.updateMember();

		// Select member
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();
		if (this.validMemberId(memberId)) {

			for (int i = 0; i < this.m_members.size(); i++) {

				if (this.m_members.get(i).getMemberId() == memberId) {

					// Update member
					a_ui.printMessage("Insert the new information for the member");
					a_ui.selectName();
					this.m_members.get(i).changeName(a_ui.readUserString());
					a_ui.selectPersonalNo();
					this.m_members.get(i).changePersonalNo(a_ui.readUserInt());
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
		//Function to view a member

		// Display heading
		a_ui.viewMember();

		// Select member
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member member : this.m_members) {

				if (member.getMemberId() == memberId) {
					// Print member information
					this.showDetailedMember(a_ui, member);
					return;
				}
			}

		} else {
			// Member not found
			a_ui.operationFailed();
		}

	}

	private void registerBoat(UIInterface a_ui) {
		//Function to register a boat to a member


		// Display heading
		a_ui.registerBoat();

		// Select member
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			// Select boat information
			BoatType a_type = this.getBoatTypes(a_ui);
			a_ui.selectBoatLength();
			int boatLength = a_ui.readUserInt();
			for (Member member : this.m_members) {

				if (member.getMemberId() == memberId) {

					for (Boat boat : member.getBoats()) {

						if (boat.getBoatLength() == boatLength && boat.getType() == a_type) {
							// Boat found and not unique
							a_ui.duplicateInformation();
							return;
						}

					}
					// Adding boat
					member.getBoats().add(new Boat(memberId, a_type, boatLength));
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
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member member : this.m_members) {

				if (member.getMemberId() == memberId) {

					//No boats stored on member
					if (member.getBoats().size() == 0){

						a_ui.noBoats();
						return;
					}

					// Select boat
					int boatId = this.getBoatId(a_ui, member);
					for (int i = 0; i < member.getBoats().size(); i++) {

						if (member.getBoats().get(i).getBoatId() == boatId) {

							// Removal of boat
							member.getBoats().remove(i);
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
		this.showCompactMemberList(a_ui, this.m_members);
		a_ui.selectMemberId();
		int memberId = a_ui.readUserInt();

		if (this.validMemberId(memberId)) {

			for (Member member : this.m_members) {

				if (member.getMemberId() == memberId) {

					if (member.getBoats().size() == 0){
						//No boats registered to member
						a_ui.noBoats();
						return;
					}
					// Select boat
					int boatId = this.getBoatId(a_ui, member);
					for (int i = 0; i < member.getBoats().size(); i++) {
						if (member.getBoats().get(i).getBoatId() == boatId) {

							// Update boat
							member.getBoats().get(i).setBoatType(this.getBoatTypes(a_ui));
							a_ui.selectBoatLength();
							int boatLength = a_ui.readUserInt();
							member.getBoats().get(i).setBoatLength(boatLength);
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

			this.m_members = this.m_dataStorage.getMembers();
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
			m_dataStorage.storeMembers(this.m_members);
		} catch (IOException e) {
			// Printing of errors
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			a_ui.printError(errors.toString());
		}

	}

	private void showDetailedMemberList(UIInterface a_ui, LinkedList<Member> a_members) {
		//Presents a detailed member list

		for (Member member : a_members) {

			this.showDetailedMember(a_ui, member);
		}
	}

	private void showCompactMemberList(UIInterface a_ui, LinkedList<Member> a_members) {
		//Presents a compact member list

		for (Member member : a_members) {

			this.showCompactMember(a_ui, member);
		}

	}

	private void showCompactMember(UIInterface a_ui, Member a_member) {

		// Printing compact member view
		a_ui.printMessage("ID: " + a_member.getMemberId() + "\tName: " + a_member.getName() + "\tBoats: " + a_member.getBoats().size());

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
			a_ui.noBoats();
			return;
		} else {

			a_ui.printMessage("\tBoats owned by the member");
			for (Boat boat : a_boats) {

				a_ui.printMessage("\t\t Boat id:" + boat.getBoatId() + "\tType: " + boat.getType() + "\t Length: "
						+ boat.getBoatLength());
			}

		}

	}

	private BoatType getBoatTypes(UIInterface a_ui) {
		//Generates a list of boattypes.

		// Selecting boattype
		a_ui.selectBoatType();
		int counter = 1;
		for (BoatType type : BoatType.values()) {
			a_ui.printMessage(counter + ". " + type.toString());
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
		return this.m_members.size() != 0;
	}

	private boolean memberExsists(String a_memberName, int a_memberPersonalNo) {
		// Checks is a member exists
		for (Member member : this.m_members) {
			if (member.getName().equalsIgnoreCase(a_memberName) && member.getPersonalNo() == a_memberPersonalNo) {
				return true;
			}
		}

		return false;

	}

	private boolean validMemberId(int a_memberIdNo) {
		// Validating a memberId
		for (Member member : this.m_members) {
			if (member.getMemberId() == a_memberIdNo) {
				return true;
			}
		}

		return false;
	}

	private int getBoatId(UIInterface a_ui, Member a_member) {
		//Reading a boatId

		this.listBoats(a_ui, a_member.getBoats());
		a_ui.selectBoat();
		return a_ui.readUserInt();
	}
}
