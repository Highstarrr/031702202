package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Do {
	
	public Do(String args0,String args1) throws IOException {
		//∂¡»°
		List<String> dataList = DoFile.readFile(args0);
		
		List<User> addList = new ArrayList<>();
		for(String data : dataList) {
			addList.add(new User(data));
		}
		
		//for(User add : addList) { System.out.println(add); }
		//±£¥Ê
		DoFile.saveFile(addList,args1); 
	}
}