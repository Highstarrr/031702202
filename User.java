package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	int level=0;//�Ѷȵȼ�����ʼ��
	String name = null;//��������ʼ��
	String phone = null;//�ֻ��ţ���ʼ��
	List<String> al = new ArrayList<>();//��ַ��Ϣ
	StringBuffer infor;

	public User(String mes) {
		setInfor(new StringBuffer(mes));
		setName();//��������
		setPhone();//�����ֻ���
		setAl();//���õ�ַ
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
		String name=this.infor.toString();//�ַ���
		/*
		 * for(int i=0;i<name.length();i++) { if(i == 0)
		 */
		//�ж��Ƿ��еȼ�����
		boolean status=name.contains("!");
		if(status)
			//���еȼ����֣���x!�޳�
				this.infor = this.infor.delete(0, 2);
			/*
			 * if(i == 1) this.infor = this.infor.deleteCharAt(i);
			 
		}*/
		name = this.infor.toString().split(",")[0];
		//�ҳ���������ʼλ��
		int start = infor.indexOf(",") + 1;
		this.infor = new StringBuffer(this.infor.substring(start, this.infor.length()));//��ȡ����
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone() {

		String nInfor = this.infor.toString();
		for (int i = 0; i < nInfor.length(); i++) {
			if (i == nInfor.length() - 1)
				//����Ϣ�����һ����.��ȥ��
				this.infor = this.infor.deleteCharAt(i);
		}
		String nPhone;
		//�ֻ���λ����ʼ��0��ǰ���Ѿ��������޳�
		for (int start = 0; start < nInfor.length(); start++) {
			if (Character.isDigit(nInfor.charAt(start))) {
				//�ֻ��ų���Ϊ11λ
				int end = start + 11;
				if (end > nInfor.length()) {
					end = nInfor.length();
				}
				//��ȡ�ֻ�����Ϣ
				nPhone = nInfor.substring(start, end);
				if (nPhone.matches("[0-9]{1,}")) {
					this.phone = nPhone;
					// ���绰����ӵ�ַ��ɾ��
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
		String regex = "(?<province>[^ʡ]+������|.*?ʡ|.*?������)?(?<city>[^��]+������|.*?����|.*?������λ|.+��|��Ͻ��|.*?��)?(?<dist>[^��]+��|.+?��|.+��|.+��|.+����|.+��)?(?<town>[^��]+��|.+�ֵ�|.+��|.+��)?(?<village>[^��]+·|.+��|.+��|.+��|.+��|.+��|.+Ū|.+��ͬ|.+��|.+ί��|.+������)?(?<number>[^����]+��)?(?<road>.*)";//������ʽ����̫�������⣬����
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.infor);
		@SuppressWarnings("rawtypes")
		//����
		Map mesM = new HashMap<>();
		
		//����ֵ
		
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
		return "{\"����\":" + name + ",\"�ֻ�\":" + phone + ",\"��ַ\":" + al + "}";
	}
	
	//���ָ������ʡ������Ϣ����
	private void add(Map<String, String> m) {
		al.add(m.get("province") == null ? "" : m.get("province"));
		al.add(m.get("city") == null ? "" : m.get("city"));
		al.add(m.get("dist") == null ? "" : m.get("dist"));
		al.add(m.get("town") == null ? "" : m.get("town"));
		al.add(m.get("village") == null ? "" : m.get("village"));
		al.add(m.get("number") == null ? "" : m.get("number"));
		al.add(m.get("road") == null ? "" : m.get("road"));

		// ��������ֱϽ��
		if (this.al.get(0).equals("") && !this.al.get(1).equals("")) {
			String tr = this.al.get(1).substring(0, 2);//����
			this.al.set(0, tr);
		} else if (this.al.get(0).equals("") && this.al.get(1).equals("")) {
			@SuppressWarnings("unused")
			String tr = this.al.get(2);
		}
		//�����ĳһ����ַȱʧ���������ַ���
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
