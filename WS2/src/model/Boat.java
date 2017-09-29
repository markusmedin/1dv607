package model;

public class Boat {

	public enum BoatType {
		Sailboat, Motorsailer, KayakCanoe, Other
	}

	private int owner;
	private int length;
	private int id;
	private BoatType type;

	public Boat(int owner, BoatType boatType, int length) {

		this.owner = owner;
		this.type = boatType;
		this.length = length;
		this.id = length + boatType.ordinal();
	}

	public int getOwner() {
		return this.owner;
	}

	public BoatType getType() {
		return this.type;
	}

	public int getBoatId() {
		return this.id;
	}

	public int getBoatLength() {
		return this.length;
	}

	public void setBoatLength(int length) {
		this.length = length;
	}

	public void setBoatType(BoatType type) {
		this.type = type;
	}

}
