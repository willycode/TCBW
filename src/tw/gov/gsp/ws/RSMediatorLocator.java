/**
 * RSMediatorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.gsp.ws;

public class RSMediatorLocator extends org.apache.axis.client.Service implements tw.gov.gsp.ws.RSMediator {

/**
 * e政府服務平台註冊服務模組介接服務
 */

    public RSMediatorLocator() {
    }


    public RSMediatorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RSMediatorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RSMediatorSoap
    private java.lang.String RSMediatorSoap_address = "http://www.cp.gov.tw/SEWebApplication/RSMediator.asmx";

    public java.lang.String getRSMediatorSoapAddress() {
        return RSMediatorSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RSMediatorSoapWSDDServiceName = "RSMediatorSoap";

    public java.lang.String getRSMediatorSoapWSDDServiceName() {
        return RSMediatorSoapWSDDServiceName;
    }

    public void setRSMediatorSoapWSDDServiceName(java.lang.String name) {
        RSMediatorSoapWSDDServiceName = name;
    }

    public tw.gov.gsp.ws.RSMediatorSoap getRSMediatorSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RSMediatorSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRSMediatorSoap(endpoint);
    }

    public tw.gov.gsp.ws.RSMediatorSoap getRSMediatorSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            tw.gov.gsp.ws.RSMediatorSoapStub _stub = new tw.gov.gsp.ws.RSMediatorSoapStub(portAddress, this);
            _stub.setPortName(getRSMediatorSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRSMediatorSoapEndpointAddress(java.lang.String address) {
        RSMediatorSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (tw.gov.gsp.ws.RSMediatorSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                tw.gov.gsp.ws.RSMediatorSoapStub _stub = new tw.gov.gsp.ws.RSMediatorSoapStub(new java.net.URL(RSMediatorSoap_address), this);
                _stub.setPortName(getRSMediatorSoapWSDDServiceName());
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
        if ("RSMediatorSoap".equals(inputPortName)) {
            return getRSMediatorSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "RSMediator");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "RSMediatorSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RSMediatorSoap".equals(portName)) {
            setRSMediatorSoapEndpointAddress(address);
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
