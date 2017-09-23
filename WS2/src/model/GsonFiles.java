package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class GsonFiles implements IDataStorage {
	private final String FILENAME = "Members.json";

	@Override
	public Member[] getMembers() {
		
		
		try {
			Gson gson = new Gson();
			JsonReader jsonReader = new JsonReader(new FileReader(FILENAME));
			Member[] members = gson.fromJson(jsonReader, Member[].class);
			return members;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeMembers(Member[] members) {

		try (Writer writer = new FileWriter(FILENAME)){
			Gson gson = new GsonBuilder().create();
			gson.toJson(members, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
