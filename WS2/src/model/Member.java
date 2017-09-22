package model;

public class Member {
	private String name;
	private int personalNo;
	private int memberId;
	private Boat[] boats;
	
	public Member(String name, int personalNo){
		this.name = name;
		this.personalNo = personalNo;
	}
	
	public void changeName(String name){
		this.name = name;
	}
	
	public void changePersonalNo(int personalNo){
		this.personalNo = personalNo;
	}
	
	public void addBoat(Boat boat){
		this.boats[this.boats.length] = boat;
	}
	
	public void removeBoat(Boat boat){
		if (this.boats.length == 0){
			return;
		}
		for (int i = 0; i < this.boats.length; i++){
			
		}
	}

}
