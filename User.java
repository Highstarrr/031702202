package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private static final String slash = "\"";
	private StringBuffer infor;
	private String name;
	private String phone;
	private List<String> al = new ArrayList<>();

	public User(String message) {
		setInfor(new StringBuffer(message));
		setName();
		setPhone();
		setAl();
	}

	public String getName() {
		return name;
	}

	private void setName() {
		String name = this.infor.toString().split(",")[0];
		int in = infor.indexOf(",") + 1;
		this.infor = new StringBuffer(this.infor.substring(in, this.infor.length()));
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}
	//取手机号
	public void setPhone() {
		String nPhone;
		String nMess = this.infor.toString();
		for (int i = 0; i < nMess.length(); i++) {
			if (Character.isDigit(nMess.charAt(i))) {
				int end = i + 11;
				if (end > nMess.length()) {
					end = nMess.length();
				}
				nPhone = nMess.substring(i, end);
				if (nPhone.matches("[0-9]{1,}")) {
					this.phone = nPhone;
					this.infor = this.infor.delete(i, end);

				}
			}
		}
	}

	public List<String> getAl() {
		return al;
	}

	
	public void setAl() {
		String regex="(?<province>[^省]+自治区|.*?省|.*?行政区)?(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市)?(?<dist>[^县]+县|.+?区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+镇|.+街道|.+乡|.+县)?(?<village>[^村]+路|.+街|.+巷|.+道|.+段|.+队|.+弄|.+胡同|.+村|.+委会|.+开发区)?(?<number>[^区号]+号)?(?<road>.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.infor);
		Map messageMap = initMap();
		if(matcher.find()) {
			messageMap.put("province", matcher.group("province"));
			messageMap.put("city", matcher.group("city"));
			messageMap.put("dist", matcher.group("dist"));
			messageMap.put("town", matcher.group("town"));
			messageMap.put("village", matcher.group("village"));
			messageMap.put("number", matcher.group("number"));
			messageMap.put("road", matcher.group("road"));
		}
		add(messageMap);
		
		

	}
	public StringBuffer getInfor() {
		return infor;
	}

	public void setInfor(StringBuffer message) {
		this.infor = message;

	}

	@Override
	public String toString() {
		return "User [name=" + name + ", phone=" + phone + ", address=" + al + "]";
	}

	private void changeMessage(int start, int end) {
		this.infor.delete(start, end);
	}
	
	private Map<String,String> initMap() {
		Map map = new HashMap<>();
		
		map.put("province", null);
		map.put("city", null);
		map.put("dist", null);
		map.put("town", null);
		map.put("village", null);
		map.put("number", null);
		map.put("road", null);
		
		return map ;
	}
	
	private void add(Map<String,String> map) {
		al.add(map.get("province") == null ? "" :map.get("province") );
		al.add(map.get("city") == null ? "" :map.get("city") );
		al.add(map.get("dist") == null ? "" :map.get("dist") );
		al.add(map.get("town") == null ? "" :map.get("town") );
		al.add(map.get("village") == null ? "" :map.get("village") );
		al.add(map.get("number") == null ? "" :map.get("number") );
		al.add(map.get("road") == null ? "" :map.get("road") );
		
		//排除直辖市与未加省市
		if(this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String t = this.al.get(1).substring(0,2);
			this.al.set(0, t);
		}else if(this.al.get(0).equals("") && this.al.get(1).equals("")){
			String t = this.al.get(2);
			if(t.length() > 4) {
				String province = t.substring(0,2);
				String city = t.substring(2,4) ;
				this.al.set(0, province + "省");
				this.al.set(1, city + "市");
				this.al.set(2, t.substring(4,t.length()));
			}
			
		}
		//将空字符串全部转化为""
		for(int i = 0 ; i < al.size() ; i++) {
			if(this.al.get(i).equals("") ) {
				this.al.set(i, slash + slash);
			}
		}
		
		
		
	}
}
