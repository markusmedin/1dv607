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
	public Member[] getMembers() throws IOException{
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader(FILENAME));
		Member[] members = gson.fromJson(jsonReader, Member[].class);
		return members;
	}

	@Override
	public void storeMembers(Member[] members) throws IOException{
		Writer writer = new FileWriter(FILENAME);
		Gson gson = new GsonBuilder().create();
		gson.toJson(members, writer);

	}

}
