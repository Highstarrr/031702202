package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoFile {
	private static final String path = "./test.json";
	private static final String dataPath = "./data.txt";
	private static final String separator = System.getProperty("line.separator");

	
	//保存文件
	public static void saveFile(List<User> list) {
		
		File file = new File(path);
		FileWriter fw = null ;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		try {
			fw = new FileWriter(file);
			for(User a : list) {
				fw.write("{");
				fw.write("\"" + "姓名" + "\"" + ":");
				fw.write("\"" + a.getName() + "\"" + ",");
				fw.write("\"" + "手机" + "\"" + ":");
				fw.write("\"" + a.getPhone() + "\"" + ",");
				fw.write("\"" + "地址" + "\"" + ":");
				fw.write("\"" + a.getAl().toString() + "\"");
				fw.write("}");
				fw.write(separator);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	public static List<String> readFile() {
		@SuppressWarnings("unused")
		File file = new File(dataPath);
		FileReader fd = null ;
		BufferedReader br = null ; 
		List<String> dataList = new ArrayList<>();
		try {
			 fd = new FileReader(dataPath);
			 br = new BufferedReader(fd);
			 String line ;
			 while((line = br.readLine()) != null) {
				 dataList.add(line);
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				fd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return dataList ;
	}
	
	//方法重载
	public static List<String> readFile(String filepath) {
		File file = null;
		if(filepath != null) {
			file = new File(filepath);
		}else {
			file = new File(dataPath);
		}
		
		
		FileReader fr = null ;
		BufferedReader br = null ; 
		List<String> dataList = new ArrayList<>();
		if(!file.exists()) {
			return null;  //说明没有存在data文件
		}
		
		try {
			 fr = new FileReader(dataPath);
			 br = new BufferedReader(fr);
			 String tt ;
			 while((tt = br.readLine()) != null) {
				 dataList.add(tt);
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dataList ;
	}
	
}
