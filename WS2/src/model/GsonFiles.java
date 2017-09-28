package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class GsonFiles implements IDataStorage {
	private final String FILENAME = "Members.json";

	@Override
	public LinkedList<Member>  getMembers() throws IOException{
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader(FILENAME));
		Member[] members = gson.fromJson(jsonReader, Member[].class);
		if (members == null){
			return new LinkedList<Member>();
		}
		return new LinkedList<Member>(Arrays.asList(members));
	}

	@Override
	public void storeMembers(LinkedList<Member> members) throws IOException{
		Writer writer = new FileWriter(FILENAME);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Member[] array = members.toArray(new Member[members.size()]);


		
		gson.toJson(array, writer);
		writer.close();

	}

}
