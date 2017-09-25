package model;

import java.util.LinkedList;

public class Member {
	private String name;
	private int personalNo;
	private int memberId;
	private LinkedList<Boat> boats = new LinkedList<Boat>();

	public Member(String name, int personalNo) {
		this.name = name;
		this.personalNo = personalNo;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void changePersonalNo(int personalNo) {
		this.personalNo = personalNo;
	}

	public void addBoat(Boat boat) {
		/*	int i = 0;
		while (i < this.boats.size()) {
			if (boat.getBoatId() == this.boats.get(i).getBoatId()) {
				return;
			}
		}*/
		this.boats.add(boat);

	}

	public void removeBoat(Boat boat) {
		int i = 0;
		while (i < this.boats.size()) {
			if (boat.getBoatId() == this.boats.get(i).getBoatId()) {
				this.boats.remove(i);
				return;
			}

			i++;
		}

	}

	public int getMemberId() {
		return this.memberId;
	}

	public int getPersonalNo() {
		return this.personalNo;
	}

	public String getName() {
		return this.name;
	}
	
	public LinkedList<Boat> getBoats(){
			return this.boats;
			
		}
}
