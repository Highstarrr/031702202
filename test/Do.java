package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Do {
 
	public Do() throws IOException {


		List<String> dataList = DoFile.readFile();
		List<User> addList = new ArrayList<>();
		for(String data : dataList) {

			addList.add(new User(data));

		}

		for(User add : addList) { System.out.println(add); }

		//for(User add : addList) { System.out.println(add); }
		DoFile.saveFile(addList); 

	}
}