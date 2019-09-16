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
		String regex="(?<province>[^ʡ]+������|.*?ʡ|.*?������)?(?<city>[^��]+������|.*?����|.*?������λ|.+��|��Ͻ��|.*?��)?(?<dist>[^��]+��|.+?��|.+��|.+��|.+����|.+��)?(?<town>[^��]+��|.+�ֵ�|.+��|.+��)?(?<village>[^��]+·|.+��|.+��|.+��|.+��|.+��|.+Ū|.+��ͬ|.+��|.+ί��|.+������)?(?<number>[^����]+��)?(?<road>.*)";
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
		
		//��������ֱϽ��
		if(this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String tr = this.al.get(1).substring(0,2);
			this.al.set(0, tr);
		}else if(this.al.get(0).equals("") && this.al.get(1).equals("")){
			String tr = this.al.get(2);
			if(tr.length() > 4) {
				String province = tr.substring(0,2);
				String city = tr.substring(2,4) ;
				this.al.set(0, province + "ʡ");
				this.al.set(1, city + "��");
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
