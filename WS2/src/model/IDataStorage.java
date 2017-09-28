package model;

import java.io.IOException;
import java.util.LinkedList;

public interface IDataStorage {
	
	public LinkedList<Member> getMembers() throws IOException;
	
	public void storeMembers(LinkedList<Member> members) throws IOException;

}
