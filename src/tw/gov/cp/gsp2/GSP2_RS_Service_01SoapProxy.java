package tw.gov.cp.gsp2;

public class GSP2_RS_Service_01SoapProxy implements tw.gov.cp.gsp2.GSP2_RS_Service_01Soap {
  private String _endpoint = null;
  private tw.gov.cp.gsp2.GSP2_RS_Service_01Soap gSP2_RS_Service_01Soap = null;
  
  public GSP2_RS_Service_01SoapProxy() {
    _initGSP2_RS_Service_01SoapProxy();
  }
  
  public GSP2_RS_Service_01SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initGSP2_RS_Service_01SoapProxy();
  }
  
  private void _initGSP2_RS_Service_01SoapProxy() {
    try {
      gSP2_RS_Service_01Soap = (new tw.gov.cp.gsp2.GSP2_RS_Service_01Locator()).getGSP2_RS_Service_01Soap();
      if (gSP2_RS_Service_01Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)gSP2_RS_Service_01Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)gSP2_RS_Service_01Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (gSP2_RS_Service_01Soap != null)
      ((javax.xml.rpc.Stub)gSP2_RS_Service_01Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public tw.gov.cp.gsp2.GSP2_RS_Service_01Soap getGSP2_RS_Service_01Soap() {
    if (gSP2_RS_Service_01Soap == null)
      _initGSP2_RS_Service_01SoapProxy();
    return gSP2_RS_Service_01Soap;
  }
  
  public tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns() throws java.rmi.RemoteException{
    if (gSP2_RS_Service_01Soap == null)
      _initGSP2_RS_Service_01SoapProxy();
    return gSP2_RS_Service_01Soap.getProfileColumns();
  }
  
  public tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns2() throws java.rmi.RemoteException{
    if (gSP2_RS_Service_01Soap == null)
      _initGSP2_RS_Service_01SoapProxy();
    return gSP2_RS_Service_01Soap.getProfileColumns2();
  }
  
  
}