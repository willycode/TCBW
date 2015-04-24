/**
 * GSP2_RS_Service_01Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.cp.gsp2;

public interface GSP2_RS_Service_01Soap extends java.rmi.Remote {

    /**
     * 查詢使用者基本資料-以欄位清單
     */
    public tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns() throws java.rmi.RemoteException;

    /**
     * 查詢使用者基本資料-欄位清單
     */
    public tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns2() throws java.rmi.RemoteException;
}
