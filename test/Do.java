package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Do {
	
	public Do(String args0,String args1) throws IOException {
		//读取
		List<String> dataList = DoFile.readFile(args0);
		
		List<User> addList = new ArrayList<>();
		for(String data : dataList) {
			addList.add(new User(data));
		}
		
		//for(User add : addList) { System.out.println(add); }
		//保存
		DoFile.saveFile(addList,args1); 
	}
}