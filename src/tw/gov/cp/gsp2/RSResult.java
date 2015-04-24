/**
 * RSResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.gov.cp.gsp2;

public class RSResult  implements java.io.Serializable {
    private tw.gov.cp.gsp2.RSResultProfileCollection profileCollection;

    private byte[] csvStream;

    private tw.gov.cp.gsp2.Item[] profileColumns;

    public RSResult() {
    }

    public RSResult(
           tw.gov.cp.gsp2.RSResultProfileCollection profileCollection,
           byte[] csvStream,
           tw.gov.cp.gsp2.Item[] profileColumns) {
           this.profileCollection = profileCollection;
           this.csvStream = csvStream;
           this.profileColumns = profileColumns;
    }


    /**
     * Gets the profileCollection value for this RSResult.
     * 
     * @return profileCollection
     */
    public tw.gov.cp.gsp2.RSResultProfileCollection getProfileCollection() {
        return profileCollection;
    }


    /**
     * Sets the profileCollection value for this RSResult.
     * 
     * @param profileCollection
     */
    public void setProfileCollection(tw.gov.cp.gsp2.RSResultProfileCollection profileCollection) {
        this.profileCollection = profileCollection;
    }


    /**
     * Gets the csvStream value for this RSResult.
     * 
     * @return csvStream
     */
    public byte[] getCsvStream() {
        return csvStream;
    }


    /**
     * Sets the csvStream value for this RSResult.
     * 
     * @param csvStream
     */
    public void setCsvStream(byte[] csvStream) {
        this.csvStream = csvStream;
    }


    /**
     * Gets the profileColumns value for this RSResult.
     * 
     * @return profileColumns
     */
    public tw.gov.cp.gsp2.Item[] getProfileColumns() {
        return profileColumns;
    }


    /**
     * Sets the profileColumns value for this RSResult.
     * 
     * @param profileColumns
     */
    public void setProfileColumns(tw.gov.cp.gsp2.Item[] profileColumns) {
        this.profileColumns = profileColumns;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RSResult)) return false;
        RSResult other = (RSResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.profileCollection==null && other.getProfileCollection()==null) || 
             (this.profileCollection!=null &&
              this.profileCollection.equals(other.getProfileCollection()))) &&
            ((this.csvStream==null && other.getCsvStream()==null) || 
             (this.csvStream!=null &&
              java.util.Arrays.equals(this.csvStream, other.getCsvStream()))) &&
            ((this.profileColumns==null && other.getProfileColumns()==null) || 
             (this.profileColumns!=null &&
              java.util.Arrays.equals(this.profileColumns, other.getProfileColumns())));
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
        if (getProfileCollection() != null) {
            _hashCode += getProfileCollection().hashCode();
        }
        if (getCsvStream() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCsvStream());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCsvStream(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProfileColumns() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProfileColumns());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProfileColumns(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RSResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "RSResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "ProfileCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", ">RSResult>ProfileCollection"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("csvStream");
        elemField.setXmlName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "CsvStream"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileColumns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "ProfileColumns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "Item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://cp.gov.tw/gsp2", "Item"));
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
