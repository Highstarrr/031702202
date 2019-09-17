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
	/*
	 * private static final String separator = System.getProperty("line.separator");
	 */
	
	//�����ļ�
	public static void saveFile(List<User> list) throws IOException {
		
		File file = new File(path);
		@SuppressWarnings("resource")
		FileWriter fw =  new FileWriter(file);
		//��������a������list
		for(User a : list) {
				fw.write("{");
				fw.write("\"" + "����" + "\"" + ":");
				fw.write("\"" + a.getName() + "\"" + ",");
				fw.write("\"" + "�ֻ�" + "\"" + ":");
				fw.write("\"" + a.getPhone() + "\"" + ",");
				fw.write("\"" + "��ַ" + "\"" + ":");
				fw.write("\"" + a.getAl().toString() + "\"");
				fw.write("}");
			/*
			 * fw.write(separator);//
			 */		}
	}

	public static List<String> readFile() throws IOException {
		
		FileReader fr = new FileReader(dataPath) ;
		BufferedReader br = new BufferedReader(fr) ; 
		List<String> data = new ArrayList<>();
		
		String flag ;
		while((flag = br.readLine()) != null) {
				 data.add(flag);
		}
		//�мǹر�
		br.close();
		fr.close();
		return data ;
	}
	
	//��ȡ�ļ�
	public static List<String> readFile(String filepath) throws IOException {
		
		List<String> data = new ArrayList<>();
		FileReader fr = new FileReader(dataPath) ;
		BufferedReader br = new BufferedReader(fr) ; 
			 String flag ;
			 while((flag = br.readLine()) != null) {
				 data.add(flag);
			 }
				br.close();
				fr.close();	//�м�Ҫ�ر�
		return data ;
	}
	
}
