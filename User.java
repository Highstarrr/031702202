package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
	String name;
	String phone;
	List<String> al = new ArrayList<>();
	StringBuffer infor;

	public User(String mes) {
		setInfor(new StringBuffer(mes));
		setName();
		setPhone();
		setAl();
	}

	public String getName() {
		return name;
	}

	private void setName() {
		String name = this.infor.toString().split(",")[0];
		int pl = infor.indexOf(",") + 1;
		this.infor = new StringBuffer(this.infor.substring(pl, this.infor.length()));
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone() {
		
		String nInfor = this.infor.toString();
		String nPhone;
		for (int i = 0; i < nInfor.length(); i++) {
			if (Character.isDigit(nInfor.charAt(i))) {
				int end = i + 11;
				if (end > nInfor.length()) {
					end = nInfor.length();
				}
				nPhone = nInfor.substring(i, end);
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
		Map mesM = new HashMap<>();
		mesM.put("province", null);
		mesM.put("city", null);
		mesM.put("dist", null);
		mesM.put("town", null);
		mesM.put("village", null);
		mesM.put("number", null);
		mesM.put("road", null);
		if(matcher.find()) {
			mesM.put("province", matcher.group("province"));
			mesM.put("city", matcher.group("city"));
			mesM.put("dist", matcher.group("dist"));
			mesM.put("town", matcher.group("town"));
			mesM.put("village", matcher.group("village"));
			mesM.put("number", matcher.group("number"));
			mesM.put("road", matcher.group("road"));

		}
		add(mesM);
	
	}
	public StringBuffer getInfor() {
		return infor;
	}

	public void setInfor(StringBuffer message) {
		this.infor = message;

	}

	public String toString() {
		return "Address [name=" + name + ", phone=" + phone + ", address=" + al + "]";
	}
	
	private void add(Map<String,String> m) {
		al.add(m.get("province") == null ? "" :m.get("province") );
		al.add(m.get("city") == null ? "" :m.get("city") );
		al.add(m.get("dist") == null ? "" :m.get("dist") );
		al.add(m.get("town") == null ? "" :m.get("town") );
		al.add(m.get("village") == null ? "" :m.get("village") );
		al.add(m.get("number") == null ? "" :m.get("number") );
		al.add(m.get("road") == null ? "" :m.get("road") );
		
		//单独考虑直辖市
		if(this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String tr = this.al.get(1).substring(0,2);
			this.al.set(0, tr);
		}else if(this.al.get(0).equals("") && this.al.get(1).equals("")){
			String tr = this.al.get(2);
			if(tr.length() > 4) {
				String province = tr.substring(0,2);
				String city = tr.substring(2,4) ;
				this.al.set(0, province + "省");
				this.al.set(1, city + "市");
				this.al.set(2, tr.substring(4,tr.length()));
			}
			
		}
		
		for(int i = 0 ; i < al.size() ; i++) {
			if(this.al.get(i).equals("") ) {
				this.al.set(i, "\"" +" "+ "\"");
			}
		}
	}

}
