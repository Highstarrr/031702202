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
	//ȡ�ֻ���
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
		String regex="(?<province>[^ʡ]+������|.*?ʡ|.*?������)?(?<city>[^��]+������|.*?����|.*?������λ|.+��|��Ͻ��|.*?��)?(?<dist>[^��]+��|.+?��|.+��|.+��|.+����|.+��)?(?<town>[^��]+��|.+�ֵ�|.+��|.+��)?(?<village>[^��]+·|.+��|.+��|.+��|.+��|.+��|.+Ū|.+��ͬ|.+��|.+ί��|.+������)?(?<number>[^����]+��)?(?<road>.*)";
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
		
		//�ų�ֱϽ����δ��ʡ��
		if(this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String t = this.al.get(1).substring(0,2);
			this.al.set(0, t);
		}else if(this.al.get(0).equals("") && this.al.get(1).equals("")){
			String t = this.al.get(2);
			if(t.length() > 4) {
				String province = t.substring(0,2);
				String city = t.substring(2,4) ;
				this.al.set(0, province + "ʡ");
				this.al.set(1, city + "��");
				this.al.set(2, t.substring(4,t.length()));
			}
			
		}
		//�����ַ���ȫ��ת��Ϊ""
		for(int i = 0 ; i < al.size() ; i++) {
			if(this.al.get(i).equals("") ) {
				this.al.set(i, slash + slash);
			}
		}
		
		
		
	}
}
