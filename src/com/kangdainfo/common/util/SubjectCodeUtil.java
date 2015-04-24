package com.kangdainfo.common.util;

import com.kangdainfo.common.util.Common;

/**
 * 拆解歲入/出科目全碼<br><br>用途別為6碼，歲入為8碼，歲出為10碼及機關10碼，其啟始層級為0，其各層長度均為2碼<br><br>
 * 
 * @param fullCode
 * @throws Exception
 */
public class SubjectCodeUtil {
	
	final int fullCodeLength = 6;
	
	private int level;
	private String code0;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String fullCode;
	private String fullCode0;
	private String fullCode1;
	private String fullCode2;
	private String fullCode3;	
	private String fullCode4;	
	private String parentFullCode;
	private String subjectCode;
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getParentFullCode() {
		return parentFullCode;
	}

	public void setParentFullCode(String parentFullCode) {
		this.parentFullCode = parentFullCode;
	}

	public String getFullCode() {
		return fullCode;
	}

	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getFullCode0() {
		return fullCode0;
	}

	public void setFullCode0(String fullCode0) {
		this.fullCode0 = fullCode0;
	}

	public String getFullCode1() {
		return fullCode1;
	}

	public void setFullCode1(String fullCode1) {
		this.fullCode1 = fullCode1;
	}

	public String getFullCode2() {
		return fullCode2;
	}

	public void setFullCode2(String fullCode2) {
		this.fullCode2 = fullCode2;
	}

	public String getFullCode3() {
		return fullCode3;
	}

	public void setFullCode3(String fullCode3) {
		this.fullCode3 = fullCode3;
	}
	
	
	/**
	 * 拆解歲入/出科目全碼<br><br>歲入為8碼，歲出為10碼，其啟始層級為0，其各層長度均為2碼<br><br>
	 * 
	 * @param fullCode
	 * @throws Exception
	 */
	public SubjectCodeUtil(String fullCode) throws Exception {
		if (fullCode!=null && fullCode.length()>=fullCodeLength) {
			this.fullCode = fullCode;
			this.code0 = fullCode.substring(0,2);
			this.code1 = fullCode.substring(2,4);
			this.code2 = fullCode.substring(4,6);
			
			this.fullCode0 = Common.formatRearZero(code0, fullCode.length());
			this.fullCode1 = Common.formatRearZero(code0+code1, fullCode.length());
			this.fullCode2 = Common.formatRearZero(code0+code1+code2, fullCode.length());
									
			if (fullCode.length()>8) {				
				this.code3 = fullCode.substring(6,8);
				this.code4 = fullCode.substring(8,fullCode.length());
				
				this.fullCode4 = Common.formatRearZero(code0+code1+code2+code3+code4, fullCode.length());
				this.level = 4;
				this.subjectCode = code4;
				
				this.fullCode3 = Common.formatRearZero(code0+code1+code2+code3, fullCode.length());				
				this.parentFullCode = this.fullCode3;				
			} else if (fullCode.length()>6){
				this.level = 3;
				this.code3 = fullCode.substring(6,8);		
				this.fullCode3 = Common.formatRearZero(code0+code1+code2+code3, fullCode.length());				
				this.subjectCode = code3;
				this.parentFullCode = this.fullCode2;				
			} else {
				this.level = 2;
				this.subjectCode = code2;
				this.parentFullCode = this.fullCode1;				
			}
			
			if (code4!=null && code4.equals("00")) {
				setLevel(3);
				this.subjectCode = code3;
				this.parentFullCode = this.fullCode2;				
			}
			if (code3!=null && code3.equals("00")) {
				setLevel(2);
				this.subjectCode = code2;
				this.parentFullCode = this.fullCode1;				
			}
			if (code2.equals("00")) {
				setLevel(1);
				this.subjectCode = code1;
				this.parentFullCode = this.fullCode0;				
			}
			if (code1.equals("00")) {
				setLevel(0);
				this.subjectCode = code0;
				this.parentFullCode = null;
			}

		} else throw new Exception("科目全碼不能為空值且長度至少需為" + fullCodeLength + "碼");
	}
	
	/**
	 * 
	 * @param childCode
	 * @return
	 */
	public String getTargetFullCode(String childCode) {
		return getChildFullCode(childCode);
	}
	
	private String getChildFullCode(String s) {
		switch (getLevel()) {
		case 0:
			return Common.formatRearZero(getCode0()+Common.formatFrontZero(s, 2), fullCode.length());
		case 1:
			return Common.formatRearZero(getCode0()+getCode1()+Common.formatFrontZero(s, 2), fullCode.length());
		case 2:
			return Common.formatRearZero(getCode0()+getCode1()+getCode2()+Common.formatFrontZero(s, 2), fullCode.length());
		case 3:
			return Common.formatRearZero(getCode0()+getCode1()+getCode2()+getCode3()+Common.formatFrontZero(s, 2), fullCode.length());
		case 4:
			return Common.formatRearZero(getCode0()+getCode1()+getCode2()+getCode3()+getCode4()+Common.formatFrontZero(s, 2), fullCode.length());			
		}
		return s;
	}
	
	/**
	 * 組出查詢時需要用的like 'XXXX________' 
	 * @return
	 */
	public String getSqlFullCode() {
		switch (getLevel()) {
		case 0:
			return Common.formatRearString(getCode0(),fullCode.length(), '_');
		case 1:
			return Common.formatRearString(getCode0()+getCode1(),fullCode.length(), '_');
		case 2:
			return Common.formatRearString(getCode0()+getCode1()+getCode2(),fullCode.length(), '_');
		case 3:
			return Common.formatRearString(getCode0()+getCode1()+getCode2()+getCode3(),fullCode.length(), '_');
		case 4:
			return Common.formatRearString(getCode0()+getCode1()+getCode2()+getCode3()+getCode4(),fullCode.length(), '_');			
		}
		return getFullCode();
	}
	
	public String getSqlChildFullCode() {
		//2,2,2,2,2		
		switch (getLevel()) {
		case 0:
			return Common.formatRearString(getCode0()+Common.formatRearString("",2,'_'), fullCode.length(), '0');
		case 1:
			return Common.formatRearString(getCode0()+getCode1()+Common.formatRearString("",2,'_'), fullCode.length(), '0');
		case 2:
			return Common.formatRearString(getCode0()+getCode1()+getCode2()+Common.formatRearString("",2,'_'), fullCode.length(), '0');		
		case 3:
			if (fullCode.length()==8) return getFullCode();	
			else return Common.formatRearString(getCode0()+getCode1()+getCode2()+getCode3()+Common.formatRearString("",2,'_'), fullCode.length(), '0');
		case 4:
			return Common.formatRearString(getCode0()+getCode1()+getCode2()+getCode3()+getCode4()+Common.formatRearString("",2,'_'), fullCode.length(), '0');			
		}
		return getFullCode();
	}	
	
	public String getFullCodeWithDash() {
		switch (getLevel()) {
		case 0:
			return getCode0();
		case 1:
			return getCode0()+"-"+getCode1();
		case 2:
			return getCode0()+"-"+getCode1()+"-"+getCode2();		
		case 3:
			return getCode0()+"-"+getCode1()+"-"+getCode2()+"-"+getCode3();
		case 4:
			return getCode0()+"-"+getCode1()+"-"+getCode2()+"-"+getCode3()+"-"+getCode4();			
		}
		return getFullCode();
	}
	
	public String getShortCode() {
		switch (getLevel()) {
		case 0:
			return getCode0();
		case 1:
			return getCode0()+getCode1();
		case 2:
			return getCode0()+getCode1()+getCode2();
		case 3:
			return getCode0()+getCode1()+getCode2()+getCode3();
		case 4:
			return getCode0()+getCode1()+getCode2()+getCode3()+getCode4();			
		}
		return getFullCode();
	}	
	
	public static void main(String[] args) {
		try {
			//SubjectCodeUtil deptCodeUtil = new SubjectCodeUtil("1234569988");
			SubjectCodeUtil deptCodeUtil = new SubjectCodeUtil("1234560000");
			System.out.println(deptCodeUtil.getCode0());
			System.out.println(deptCodeUtil.getCode1());
			System.out.println(deptCodeUtil.getCode2());
			System.out.println(deptCodeUtil.getCode3());
			System.out.println(deptCodeUtil.getCode4());
			System.out.println(deptCodeUtil.getLevel());
			System.out.println(deptCodeUtil.getFullCode());
			System.out.println(deptCodeUtil.getSqlFullCode());
			System.out.println(deptCodeUtil.getShortCode());
			System.out.println("childFullCode : "+deptCodeUtil.getSqlChildFullCode());
			System.out.println(deptCodeUtil.getParentFullCode());
			System.out.println(deptCodeUtil.getSubjectCode());
			System.out.println(deptCodeUtil.getFullCodeWithDash());
			//System.out.println(deptCodeUtil.getFullCode("012"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCode4() {
		return code4;
	}

	public void setCode4(String code4) {
		this.code4 = code4;
	}

	public String getFullCode4() {
		return fullCode4;
	}

	public void setFullCode4(String fullCode4) {
		this.fullCode4 = fullCode4;
	}

	public int getFullCodeLength() {
		return fullCodeLength;
	}
	
}
