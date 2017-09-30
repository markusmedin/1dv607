package model;

import java.util.LinkedList;

public class Member {
	
	private String m_name;
	private int m_personalNo;
	private int m_memberId;
	private LinkedList<Boat> m_boats = new LinkedList<Boat>();

	public Member(String a_name, int a_personalNo) {
		
		this.m_name = a_name;
		this.m_personalNo = a_personalNo;
		int id = 0;
		
		//Defining memberId
		for (char c : a_name.toCharArray()) {
			id += c;
		}
		if (a_personalNo != 0 ){
			this.m_memberId = id % a_personalNo;
		}else{
			this.m_memberId = id;
		}
		
	}

	public void changeName(String a_name) {
		
		this.m_name = a_name;
	}

	public void changePersonalNo(int a_personalNo) {
		
		this.m_personalNo = a_personalNo;
	}

	public void addBoat(Boat a_boat) {
		
		this.m_boats.add(a_boat);

	}

	public void removeBoat(Boat a_boat) {
		
		int i = 0;
		while (i < this.m_boats.size()) {
			if (a_boat.getBoatId() == this.m_boats.get(i).getBoatId()) {
				this.m_boats.remove(i);
				return;
			}

			i++;
		}

	}

	public int getMemberId() {
		
		return this.m_memberId;
	}

	public int getPersonalNo() {
		
		return this.m_personalNo;
	}

	public String getName() {
		
		return this.m_name;
	}

	public LinkedList<Boat> getBoats() {
		
		return this.m_boats;
	}
}
