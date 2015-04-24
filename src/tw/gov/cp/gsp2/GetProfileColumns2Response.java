/**
 * GetProfileColumns2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.cp.gsp2;

public class GetProfileColumns2Response  implements java.io.Serializable {
    private tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns2Result;

    public GetProfileColumns2Response() {
    }

    public GetProfileColumns2Response(
           tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns2Result) {
           this.getProfileColumns2Result = getProfileColumns2Result;
    }


    /**
     * Gets the getProfileColumns2Result value for this GetProfileColumns2Response.
     * 
     * @return getProfileColumns2Result
     */
    public tw.gov.cp.gsp2.CP2ResponseOfRSResult getGetProfileColumns2Result() {
        return getProfileColumns2Result;
    }


    /**
     * Sets the getProfileColumns2Result value for this GetProfileColumns2Response.
     * 
     * @param getProfileColumns2Result
     */
    public void setGetProfileColumns2Result(tw.gov.cp.gsp2.CP2ResponseOfRSResult getProfileColumns2Result) {
        this.getProfileColumns2Result = getProfileColumns2Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetProfileColumns2Response)) return false;
        GetProfileColumns2Response other = (GetProfileColumns2Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getProfileColumns2Result==null && other.getGetProfileColumns2Result()==null) || 
             (this.getProfileColumns2Result!=null &&
              this.getProfileColumns2Result.equals(other.getGetProfileColumns2Result())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetProfileColumns2Result() != null) {
            _hashCode += getGetProfileColumns2Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetProfileColumns2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", ">GetProfileColumns2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getProfileColumns2Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "GetProfileColumns2Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "CP2ResponseOfRSResult"));
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
