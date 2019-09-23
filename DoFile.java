package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoFile {
private static final String separator = System.getProperty("line.separator");

	
//保存文件
public static void saveFile(List<User> list,String args1) throws IOException {
		
	File f = new File(args1);
	FileWriter fw = null ;
	f.createNewFile();		
	fw = new FileWriter(f);
	for(User a : list) {
		fw.write("{");
		fw.write("\"" + "姓名" + "\"" + ":");
		fw.write("\"" + a.getName() + "\"" + ",");
		fw.write("\"" + "手机" + "\"" + ":");
		fw.write("\"" + a.getPhone() + "\"" + ",");
		fw.write("\"" + "地址" + "\"" + ":");
		fw.write(a.getAl().toString());
		fw.write("}");
		fw.write(separator);
	}
		fw.flush();
		fw.close();
}
	
public static List<String> readFile(String filepath) throws IOException {
		
	FileReader fr = new FileReader(filepath);
	BufferedReader br = new BufferedReader(fr);
	List<String> dataList = new ArrayList<>();
	String re ;
	while((re = br.readLine()) != null) {
		dataList.add(re);
	}
	br.close();
	fr.close();
		
	return dataList ;
}
	
}
