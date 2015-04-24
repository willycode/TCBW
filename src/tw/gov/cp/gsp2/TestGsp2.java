package tw.gov.cp.gsp2;

import javax.xml.soap.SOAPException;

import org.apache.axis.message.MessageElement;
import org.apache.axis.message.SOAPHeaderElement;
import org.w3c.dom.Element;

public class TestGsp2 {

	public static void main(String[] args) throws Exception {
		String sid = "";
		String token1 = "";
		GSP2_RS_Service_01SoapProxy proxy = new GSP2_RS_Service_01SoapProxy();				
        GSP2_RS_Service_01Soap soap = proxy.getGSP2_RS_Service_01Soap();        
        GSP2_RS_Service_01SoapStub stub = (GSP2_RS_Service_01SoapStub) soap;
      
        AuthHeader authHeader = new AuthHeader();
        authHeader.setServiceID(sid);
        authHeader.setToken1(token1);
        stub.setAuthHeader(authHeader);
//        SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement("http://cp.gov.tw/gsp2", "AuthHeader");
//        soapHeaderElement.setPrefix("");
//        try {
//            soapHeaderElement.addChildElement("Token1").setValue(token1);
//            soapHeaderElement.addChildElement("ServiceID").setValue(sid);
//        } catch (SOAPException e) {
//            e.printStackTrace();
//        }
//        
//        stub.setHeader(soapHeaderElement);        
        
        CP2ResponseOfRSResult result = proxy.getProfileColumns();        
        System.out.printf("returned message : %s%n", result.getMessage());
        System.out.printf("returned code : %d%n", result.getCode());
        System.out.println();
        RSResult rs = result.getResult();  
        if (rs != null) {            
            Item[] arrItem = rs.getProfileColumns();
            for (Item item : arrItem) {
            	System.out.println(item.getKey() + "  =  " + item.getValue());
            }
            
            MessageElement[] messages = rs.getProfileCollection().get_any();
            for (MessageElement msg : messages) {
                // key
                Element keyNode = ((MessageElement) msg.getFirstChild()).getAsDOM();
                // value
                Element valueNode = ((MessageElement) msg.getLastChild()).getAsDOM();
                // Print all return value
                System.out.printf("%s = %s%n", keyNode.getNodeValue(), valueNode.getNodeValue());
            }
        }        
        
		
	}
}
