package model;

import java.util.LinkedList;

public interface IDataStorage {
	
	public Member[] getMembers();
	
	public void storeMembers(Member[] members);

}
