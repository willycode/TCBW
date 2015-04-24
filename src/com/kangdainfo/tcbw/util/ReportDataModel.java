package com.kangdainfo.tcbw.util;

/**
 * 出報表用的暫存物件 - 可依需要自行加東西, 但原有的程式請勿更動
 *
 */
public class ReportDataModel implements Comparable<Object> {
	
	private String idx;
	private String code0;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String subjectCode;	
	private String subjectName;
	
    private String desc;
    
    java.util.Map child1;
    java.util.Map child2;       
    
	private double[] d1, d2;

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getCode0() {
		return code0;
	}

	public void setCode0(String code0) {
		this.code0 = code0;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getCode4() {
		return code4;
	}

	public void setCode4(String code4) {
		this.code4 = code4;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public java.util.Map getChild1() {
		return child1;
	}

	public void setChild1(java.util.Map child1) {
		this.child1 = child1;
	}

	public java.util.Map getChild2() {
		return child2;
	}

	public void setChild2(java.util.Map child2) {
		this.child2 = child2;
	}

	public double[] getD1() {
		return d1;
	}

	public void setD1(double[] d1) {
		this.d1 = d1;
	}

	public double[] getD2() {
		return d2;
	}

	public void setD2(double[] d2) {
		this.d2 = d2;
	}    
	
	public int compareTo(Object o) {
        if(o!=null && o instanceof ReportDataModel){
            ReportDataModel obj = (ReportDataModel)o;
            int compareResult = this.getIdx().toUpperCase().compareTo(obj.getIdx().toUpperCase());
            if(compareResult != 0) return compareResult;
        }else {
        	return 0;
        }            
        return -1;
    }	
	
}
