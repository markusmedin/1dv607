package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class GsonFiles implements IDataStorage {
	private final String M_FILENAME = "Members.json";

	public LinkedList<Member> getMembers() throws IOException {
		//Reading members from JSON file
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader(M_FILENAME));
		Member[] members = gson.fromJson(jsonReader, Member[].class);
		
		//No members stored
		if (members == null) {
			return new LinkedList<Member>();
		}
		
		return new LinkedList<Member>(Arrays.asList(members));
	}

	public void storeMembers(LinkedList<Member> members) throws IOException {
		//Writing member to JSON file
		Writer writer = new FileWriter(M_FILENAME);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Member[] newArray = members.toArray(new Member[members.size()]);
		
		gson.toJson(newArray, writer);
		
		writer.close();

	}

}
