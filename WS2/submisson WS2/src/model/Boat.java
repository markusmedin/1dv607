package model;

public class Boat {

	public enum BoatType {
		Sailboat, Motorsailer, KayakCanoe, Other
	}

	private int m_owner;
	private int m_length;
	private int m_id;
	private BoatType m_type;

	public Boat(int a_owner, BoatType a_boatType, int a_length) {

		this.m_owner = a_owner;
		this.m_type = a_boatType;
		this.m_length = a_length;
		this.m_id = a_length + a_boatType.ordinal();
	}

	public int getOwner() {
		return this.m_owner;
	}

	public BoatType getType() {
		return this.m_type;
	}

	public int getBoatId() {
		return this.m_id;
	}

	public int getBoatLength() {
		return this.m_length;
	}

	public void setBoatLength(int a_length) {
		this.m_length = a_length;
	}

	public void setBoatType(BoatType a_type) {
		this.m_type = a_type;
	}

}
