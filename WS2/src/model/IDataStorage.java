package model;

import java.io.IOException;
import java.util.LinkedList;

public interface IDataStorage {
	
	public Member[] getMembers() throws IOException;
	
	public void storeMembers(Member[] members) throws IOException;

}
