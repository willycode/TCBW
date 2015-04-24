package tw.gov.gsp.ws;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.kangdainfo.common.util.Common;

public class UserProfile {
	
	String type;
	String uid;
	String name;
	String engName;
	String homePhone;
	String mail;
	String postalAddress;
	String telephoneNumber;
	String mobile;	
	
	public String getEngName() {
		return Common.get(engName);
	}


	public void setEngName(String engName) {
		this.engName = Common.set(engName);
	}


	public String getHomePhone() {
		return Common.get(homePhone);
	}


	public void setHomePhone(String homePhone) {
		this.homePhone = Common.set(homePhone);
	}


	public String getMail() {
		return Common.get(mail);
	}


	public void setMail(String mail) {
		this.mail = Common.set(mail);
	}


	public String getMobile() {
		return Common.get(mobile);
	}


	public void setMobile(String mobile) {
		this.mobile = Common.set(mobile);
	}


	public String getName() {
		return Common.get(name);
	}


	public void setName(String name) {
		this.name = Common.set(name);
	}


	public String getPostalAddress() {
		return Common.get(postalAddress);
	}


	public void setPostalAddress(String postalAddress) {
		this.postalAddress = Common.set(postalAddress);
	}


	public String getTelephoneNumber() {
		return Common.get(telephoneNumber);
	}


	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = Common.set(telephoneNumber);
	}


	public String getType() {
		return Common.get(type);
	}


	public void setType(String type) {
		this.type = Common.set(type);
	}


	public String getUid() {
		return Common.get(uid);
	}


	public void setUid(String uid) {
		this.uid = Common.set(uid);
	}

	public UserProfile() {}
	
	public UserProfile(String s) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//		sb.append("<CPWSResponse>");
//		sb.append("<Code>0</Code>");
//		sb.append("<Message></Message>");
//		sb.append("<Result>");
//		sb.append("<UserProfile>");
//		sb.append("	<Profile>");
//		sb.append("	<Type>自然人</Type>");
//		sb.append("	<Name>張宇華</Name>");
//		sb.append("	<EngName>Clive Chang</EngName>");
//		sb.append("	<UID>A1234567890</UID>");
//		sb.append("	</Profile>");
//		
//		sb.append("	<ContactInfo>");
//		sb.append("	<PostalAddress>高雄市三民區</PostalAddress>");
//		sb.append("	<HomePhone>07-5329559:</HomePhone>");
//		sb.append("	<TelephoneNumber>:</TelephoneNumber>");
//		sb.append("	<Mobile></Mobile>");
//		sb.append("	<Mail>imclive@gmail.com</Mail>");
//		sb.append("	</ContactInfo>");
//		sb.append("</UserProfile>");
//		sb.append("<SecureLevel>PlatformX509</SecureLevel>");
//		sb.append("</Result>");
//		sb.append("</CPWSResponse>");
//		
//		parseXmlString(sb.toString());
		
		parseXmlString(s);
	}
	
	
	private void parseXmlString(String s){
		try {
			java.io.ByteArrayInputStream f = new java.io.ByteArrayInputStream(s.getBytes("UTF-8")); 
			Document dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
			parseDocument(dom);
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void parseDocument(Document dom){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <employee> elements
		NodeList nl = docEle.getElementsByTagName("UserProfile");		
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {				
				//get the employee element
				Element el = (Element)nl.item(i);
				this.setType(getTextValue(el,"Type"));
				this.setName(getTextValue(el,"Name"));
				this.setUid(Common.escapeBlank(getTextValue(el,"UID")));
				this.setEngName(getTextValue(el,"EngName"));
				this.setPostalAddress(getTextValue(el,"PostalAddress"));				
				this.setHomePhone(getTextValue(el,"HomePhone"));
				this.setTelephoneNumber(getTextValue(el,"TelephoneNumber"));				
				this.setMobile(getTextValue(el,"Mobile"));	
				this.setMail(getTextValue(el,"Mail"));				
			}
		}	
	}	
	
	
	private String getTextValue(Element ele, String tagName) {
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			if (el!=null && el.getFirstChild()!=null) {
				return Common.get(el.getFirstChild().getNodeValue());
			}
		}
		return "";
	}	
}
