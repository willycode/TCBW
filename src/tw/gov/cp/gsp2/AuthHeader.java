/**
 * AuthHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.cp.gsp2;

public class AuthHeader  extends tw.gov.cp.gsp2.ServiceHeader  implements java.io.Serializable {
    private java.lang.String token1;

    public AuthHeader() {
    }

    public AuthHeader(
           java.lang.String serviceID,
           java.lang.String token1) {
        super(
            serviceID);
        this.token1 = token1;
    }


    /**
     * Gets the token1 value for this AuthHeader.
     * 
     * @return token1
     */
    public java.lang.String getToken1() {
        return token1;
    }


    /**
     * Sets the token1 value for this AuthHeader.
     * 
     * @param token1
     */
    public void setToken1(java.lang.String token1) {
        this.token1 = token1;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthHeader)) return false;
        AuthHeader other = (AuthHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.token1==null && other.getToken1()==null) || 
             (this.token1!=null &&
              this.token1.equals(other.getToken1())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getToken1() != null) {
            _hashCode += getToken1().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "AuthHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "Token1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
