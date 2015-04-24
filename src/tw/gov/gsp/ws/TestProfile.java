package tw.gov.gsp.ws;

import java.io.FileInputStream;

import org.apache.commons.digester.Digester;

public class TestProfile {
	private java.util.List<UserProfile> userProfilebList;
	
	public java.util.List<UserProfile> getUserProfilebList() {
		return userProfilebList;
	}
	public void addUserProfile(UserProfile obj) {
		if (userProfilebList==null) userProfilebList = new java.util.ArrayList<UserProfile>();
		if (obj!=null) userProfilebList.add(obj);
	}	
	
	public String getTestData() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<CPWSResponse>\n");
		sb.append("<Code>0</Code>\n");
		sb.append("<Message></Message>\n");
		sb.append("<Result>\n");
		sb.append("<UserProfile>\n");
		sb.append("	<Profile>\n");
		sb.append("	<Type>自然人</Type>\n");
		sb.append("	<Name>張宇華</Name>\n");
		sb.append("	<EngName>Clive Chang</EngName>\n");
		sb.append("	<UID>A1234567890 　　　</UID>\n");
		sb.append("	</Profile>\n");
		
		sb.append("	<ContactInfo>\n");
		sb.append("	<PostalAddress>高雄市三民區</PostalAddress>\n");
		sb.append("	<HomePhone>07-5329559:</HomePhone>\n");
		sb.append("	<TelephoneNumber>:</TelephoneNumber>\n");
		sb.append("	<Mobile></Mobile>\n");
		sb.append("	<Mail>imclive@gmail.com</Mail>\n");
		sb.append("	</ContactInfo>\n");
		sb.append("</UserProfile>\n");
		sb.append("<SecureLevel>PlatformX509</SecureLevel>\n");
		sb.append("</Result>\n");
		sb.append("</CPWSResponse>\n");
		
		return sb.toString();
	}

	
	public void testXmlDom() {
		UserProfile obj = new UserProfile(getTestData());
		System.out.println(obj.getType());
		System.out.println(obj.getName());
		System.out.println(obj.getUid());
		System.out.println(obj.getPostalAddress());		
	}
	
	public void testXmlBean(java.io.InputStream xmlFile) throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("CPWSResponse", TestProfile.class );
        digester.addObjectCreate("CPWSResponse/Result/UserProfile", UserProfile.class );
        digester.addCallMethod("CPWSResponse/Result/UserProfile/Profile/Type", "setType",0 );
        digester.addCallMethod("CPWSResponse/Result/UserProfile/Profile/Name", "setName",0 );
        digester.addCallMethod("CPWSResponse/Result/UserProfile/Profile/EngName", "setEngName",0 );
        digester.addCallMethod("CPWSResponse/Result/UserProfile/Profile/UID", "setUid",0);
        digester.addCallMethod("CPWSResponse/Result/UserProfile/ContactInfo/PostalAddress", "setPostalAddress", 0);
        digester.addCallMethod("CPWSResponse/Result/UserProfile/ContactInfo/HomePhone", "setHomePhone",0);        
        digester.addCallMethod("CPWSResponse/Result/UserProfile/ContactInfo/TelephoneNumber", "setTelephoneNumber", 0);
        digester.addCallMethod("CPWSResponse/Result/UserProfile/ContactInfo/Mobile", "setMobile", 0);
        digester.addCallMethod("CPWSResponse/Result/UserProfile/ContactInfo/Mail", "setMail", 0);        
        
        digester.addSetNext("CPWSResponse/Result/UserProfile", "addUserProfile"); 
        
        TestProfile profile = (TestProfile) digester.parse(xmlFile);      
        if (profile!=null && profile.getUserProfilebList()!=null && profile.getUserProfilebList().size()>0) {
        	for (UserProfile obj : profile.getUserProfilebList()) {
        		System.out.println(obj.getType());
        		System.out.println(obj.getName());
        		System.out.println(obj.getUid());
        		System.out.println(obj.getPostalAddress());		
        	}
        }
	}	
	
	public void testXmlBean(java.io.File xmlFile) throws Exception {
		FileInputStream fis = null;
		try{
			fis = new java.io.FileInputStream(xmlFile);
			testXmlBean(fis);
		}catch(Exception e){
			throw e;
		}finally{
			if(fis!=null)fis.close();
		}
	}
	
	public static void main(String args[]) throws Exception {
		TestProfile obj = new TestProfile();
		//obj.testXmlDom();
		//obj.getTestData().getBytes("UTF-8")
		
		//java.io.ByteArrayInputStream f = new java.io.ByteArrayInputStream(new java.io.FileInputStream(new java.io.File("d:/userProfile.xml"))); 
		obj.testXmlBean(new java.io.FileInputStream(new java.io.File("d:/userProfile.xml")));
	}

}
