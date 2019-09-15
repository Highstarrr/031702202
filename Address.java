package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Address {
	
	private StringBuffer message;
	private String name;
	private String phone;
	private List<String> address = new ArrayList<>();

	public Address(String message) {
		setMessage(new StringBuffer(message));
		setName();
		setPhone();
		setAddress();
	}

	public String getName() {
		return name;
	}
	//将名字取出来
	private void setName() {
		String name = this.message.toString().split(",")[0];
		int index = message.indexOf(",") + 1;
		this.message = new StringBuffer(this.message.substring(index, this.message.length()));
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone() {
		String newPhone;
		String newMessage = this.message.toString();
		for (int i = 0; i < newMessage.length(); i++) {
			if (Character.isDigit(newMessage.charAt(i))) {
				int end = i + 11;
				if (end > newMessage.length()) {
					end = newMessage.length();
				}
				newPhone = newMessage.substring(i, end);
				if (newPhone.matches("[0-9]{1,}")) {
					this.phone = newPhone;
					this.message = this.message.delete(i, end);

				}
			}
		}
	}

	public List<String> getAddress() {
		return address;
	}

	
	public void setAddress() {
		String regex="(?<province>[^省]+自治区|.*?省|.*?行政区)?(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市)?(?<dist>[^县]+县|.+?区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+镇|.+街道|.+乡|.+县)?(?<village>[^村]+路|.+街|.+巷|.+道|.+段|.+队|.+弄|.+胡同|.+村|.+委会|.+开发区)?(?<number>[^区号]+号)?(?<road>.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.message);
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
	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;

	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}

	private void changeMessage(int start, int end) {
		this.message.delete(start, end);
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
		address.add(map.get("province") == null ? "" :map.get("province") );
		address.add(map.get("city") == null ? "" :map.get("city") );
		address.add(map.get("dist") == null ? "" :map.get("dist") );
		address.add(map.get("town") == null ? "" :map.get("town") );
		address.add(map.get("village") == null ? "" :map.get("village") );
		address.add(map.get("number") == null ? "" :map.get("number") );
		address.add(map.get("road") == null ? "" :map.get("road") );
		
		//排除直辖市与未加省市
		if(this.address.get(0).equals("") && !this.address.get(1).equals("")) {
			String t = this.address.get(1).substring(0,2);
			this.address.set(0, t);
		}else if(this.address.get(0).equals("") && this.address.get(1).equals("")){
			String t = this.address.get(2);
			if(t.length() > 4) {
				String province = t.substring(0,2);
				String city = t.substring(2,4) ;
				this.address.set(0, province + "省");
				this.address.set(1, city + "市");
				this.address.set(2, t.substring(4,t.length()));
			}
			
		}
		//将空字符串全部转化为""
		for(int i = 0 ; i < address.size() ; i++) {
			if(this.address.get(i).equals("") ) {
				this.address.set(i, "\"" + "\"");
			}
		}
		
		
		
	}

}
