/**
 * GSP2_RS_Service_01Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.cp.gsp2;

public class GSP2_RS_Service_01Locator extends org.apache.axis.client.Service implements tw.gov.cp.gsp2.GSP2_RS_Service_01 {

/**
 * GSP 2.0 註冊服務_人員資料中介(必須完成服務註冊程序)
 */

    public GSP2_RS_Service_01Locator() {
    }


    public GSP2_RS_Service_01Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GSP2_RS_Service_01Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GSP2_RS_Service_01Soap
    private java.lang.String GSP2_RS_Service_01Soap_address = "https://www.cp.gov.tw/GSP2WS/RSMediator01.asmx";

    public java.lang.String getGSP2_RS_Service_01SoapAddress() {
        return GSP2_RS_Service_01Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GSP2_RS_Service_01SoapWSDDServiceName = "GSP2_RS_Service_01Soap";

    public java.lang.String getGSP2_RS_Service_01SoapWSDDServiceName() {
        return GSP2_RS_Service_01SoapWSDDServiceName;
    }

    public void setGSP2_RS_Service_01SoapWSDDServiceName(java.lang.String name) {
        GSP2_RS_Service_01SoapWSDDServiceName = name;
    }

    public tw.gov.cp.gsp2.GSP2_RS_Service_01Soap getGSP2_RS_Service_01Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GSP2_RS_Service_01Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGSP2_RS_Service_01Soap(endpoint);
    }

    public tw.gov.cp.gsp2.GSP2_RS_Service_01Soap getGSP2_RS_Service_01Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            tw.gov.cp.gsp2.GSP2_RS_Service_01SoapStub _stub = new tw.gov.cp.gsp2.GSP2_RS_Service_01SoapStub(portAddress, this);
            _stub.setPortName(getGSP2_RS_Service_01SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGSP2_RS_Service_01SoapEndpointAddress(java.lang.String address) {
        GSP2_RS_Service_01Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (tw.gov.cp.gsp2.GSP2_RS_Service_01Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                tw.gov.cp.gsp2.GSP2_RS_Service_01SoapStub _stub = new tw.gov.cp.gsp2.GSP2_RS_Service_01SoapStub(new java.net.URL(GSP2_RS_Service_01Soap_address), this);
                _stub.setPortName(getGSP2_RS_Service_01SoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GSP2_RS_Service_01Soap".equals(inputPortName)) {
            return getGSP2_RS_Service_01Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "GSP2_RS_Service_01");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "GSP2_RS_Service_01Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GSP2_RS_Service_01Soap".equals(portName)) {
            setGSP2_RS_Service_01SoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
