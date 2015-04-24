/**
 * RSMediator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.gsp.ws;

public interface RSMediator extends javax.xml.rpc.Service {

/**
 * e政府服務平台註冊服務模組介接服務
 */
    public java.lang.String getRSMediatorSoapAddress();

    public tw.gov.gsp.ws.RSMediatorSoap getRSMediatorSoap() throws javax.xml.rpc.ServiceException;

    public tw.gov.gsp.ws.RSMediatorSoap getRSMediatorSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
