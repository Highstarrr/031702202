package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoFile {
	private static final String path = "./test.json";
	private static final String dataPath = "./data.txt";
	private static final String separator = System.getProperty("line.separator");
	
	
	public static void saveFile(List<User> list) throws IOException {
		
		File file = new File(path);
		FileWriter fw =  new FileWriter(file);
		for(User a : list) {
				fw.write("{");
				fw.write("\"" + "姓名" + "\"" + ":");fw.write("\"" + a.getName() + "\"" + ",");
				fw.write("\"" + "手机" + "\"" + ":");fw.write("\"" + a.getPhone() + "\"" + ",");
				fw.write("\"" + "地址" + "\"" + ":");fw.write("\"" + a.getAl().toString() + "\"");
				fw.write("}");
				fw.write(separator);
		}
	}

	public static List<String> readFile() throws IOException {
		File f = new File(dataPath);
		FileReader fr = new FileReader(dataPath) ;
		BufferedReader br = new BufferedReader(fr) ; 
		List<String> data = new ArrayList<>();
		
		String flag ;
		while((flag = br.readLine()) != null) {
				 data.add(flag);
		}
		br.close();
		fr.close();
		return data ;
	}
	
	public static List<String> readFile(String filepath) throws IOException {
		File file = null;
		if(filepath != null) {
			file = new File(filepath);
		}else {
			file = new File(dataPath);
		}
		
		List<String> data = new ArrayList<>();
		FileReader fr = new FileReader(dataPath) ;
		BufferedReader br = new BufferedReader(fr) ; 
			 String flag ;
			 while((flag = br.readLine()) != null) {
				 data.add(flag);
			 }
				br.close();
				fr.close();	
		return data ;
	}
	
}
