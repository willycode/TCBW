/**
 * RSMediatorSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.gsp.ws;

public interface RSMediatorSoap extends java.rmi.Remote {

    /**
     * 取得所有之一站服務與既有服務之服務清單
     */
    public java.lang.String getAllServices() throws java.rmi.RemoteException;

    /**
     * 取得使用者基本資料
     */
    public java.lang.String getUserProfile(java.lang.String token1) throws java.rmi.RemoteException;

    /**
     * 取得使用者基本資料
     */
    public java.lang.String getUserAccount(java.lang.String token1) throws java.rmi.RemoteException;
}
