package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	int level=0;//难度等级，初始化
	String name = null;//姓名，初始化
	String phone = null;//手机号，初始化
	List<String> al = new ArrayList<>();//地址信息
	StringBuffer infor;

	public User(String mes) {
		setInfor(new StringBuffer(mes));
		setName();//设置姓名
		setPhone();//设置手机号
		setAl();//设置地址
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	private void setName() {
		String name=this.infor.toString();//字符串
		/*
		 * for(int i=0;i<name.length();i++) { if(i == 0)
		 */
		//判断是否有等级划分
		boolean status=name.contains("!");
		if(status)
			//若有等级划分，将x!剔除
				this.infor = this.infor.delete(0, 2);
			/*
			 * if(i == 1) this.infor = this.infor.deleteCharAt(i);
			 
		}*/
		name = this.infor.toString().split(",")[0];
		//找出姓名的起始位置
		int start = infor.indexOf(",") + 1;
		this.infor = new StringBuffer(this.infor.substring(start, this.infor.length()));//获取姓名
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone() {

		String nInfor = this.infor.toString();
		for (int i = 0; i < nInfor.length(); i++) {
			if (i == nInfor.length() - 1)
				//将信息的最后一个“.”去掉
				this.infor = this.infor.deleteCharAt(i);
		}
		String nPhone;
		//手机号位置起始于0，前面已经将姓名剔除
		for (int start = 0; start < nInfor.length(); start++) {
			if (Character.isDigit(nInfor.charAt(start))) {
				//手机号长度为11位
				int end = start + 11;
				if (end > nInfor.length()) {
					end = nInfor.length();
				}
				//获取手机号信息
				nPhone = nInfor.substring(start, end);
				if (nPhone.matches("[0-9]{1,}")) {
					this.phone = nPhone;
					// 将电话号码从地址中删除
					this.infor = this.infor.delete(start, end);
				}
			}
		}
	}

	public List<String> getAl() {
		return al;
	}

	@SuppressWarnings("unchecked")
	public void setAl() {
		String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区)?(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市)?(?<dist>[^县]+县|.+?区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+镇|.+街道|.+乡|.+县)?(?<village>[^村]+路|.+街|.+巷|.+道|.+段|.+队|.+弄|.+胡同|.+村|.+委会|.+开发区)?(?<number>[^区号]+号)?(?<road>.*)";//正则表达式，不太符合题意，待改
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.infor);
		@SuppressWarnings("rawtypes")
		//创建
		Map mesM = new HashMap<>();
		
		//赋初值
		
		mesM.put("province", null);
		mesM.put("city", null);
		mesM.put("dist", null);
		mesM.put("town", null);
		mesM.put("village", null);
		mesM.put("number", null);
		mesM.put("road", null);
		
		if (matcher.find()) {
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

	public void setInfor(StringBuffer mes) {
		this.infor = mes;
	}

	public String toString() {
		return "{\"姓名\":" + name + ",\"手机\":" + phone + ",\"地址\":" + al + "}";
	}
	
	//将分割出来的省市区信息放入
	private void add(Map<String, String> m) {
		al.add(m.get("province") == null ? "" : m.get("province"));
		al.add(m.get("city") == null ? "" : m.get("city"));
		al.add(m.get("dist") == null ? "" : m.get("dist"));
		al.add(m.get("town") == null ? "" : m.get("town"));
		al.add(m.get("village") == null ? "" : m.get("village"));
		al.add(m.get("number") == null ? "" : m.get("number"));
		al.add(m.get("road") == null ? "" : m.get("road"));

		// 单独考虑直辖市
		if (this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String tr = this.al.get(1).substring(0, 2);//返回
			this.al.set(0, tr);
		} else if (this.al.get(0).equals("") && this.al.get(1).equals("")) {
			@SuppressWarnings("unused")
			String tr = this.al.get(2);
		}
		//如果有某一级地址缺失，保留空字符串
		for (int i = 0; i < al.size(); i++) {
			if (this.al.get(i).equals("")) {
				this.al.set(i, "\"" + "\"");
			}

			/*
			 * if(this.al.get(i).equals(".")){ this.al= }
			 */

		}
	}
}
