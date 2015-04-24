package com.kangdainfo.common.model.bo;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class CommonNews implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String subject;

    /** nullable persistent field */
    private String content;

    /** nullable persistent field */
    private String startDate;

    /** nullable persistent field */
    private String endDate;

    /** nullable persistent field */
    private String isHtml;
    
    /** nullable persistent field */
    private String isOpen;    

    /** nullable persistent field */
    private String itemPicture1;

    /** nullable persistent field */
    private String itemPicture2;

    /** nullable persistent field */
    private String itemPicture3;

    /** nullable persistent field */
    private String itemPicture4;

    /** nullable persistent field */
    private String itemPicture5;

    /** nullable persistent field */
    private String itemPicture6;

    /** nullable persistent field */
    private String itemPicture7;

    /** nullable persistent field */
    private String itemPicture8;

    /** nullable persistent field */
    private String itemPicture9;

    /** nullable persistent field */
    private String itemPicture10;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonCode commonCode;

    /** full constructor */
    public CommonNews(String subject, String content, String startDate, String endDate, String isHtml, String itemPicture1, String itemPicture2, String itemPicture3, String itemPicture4, String itemPicture5, String itemPicture6, String itemPicture7, String itemPicture8, String itemPicture9, String itemPicture10, String editId, String editDate, String editTime, com.kangdainfo.common.model.bo.CommonCode commonCode) {
        this.subject = subject;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHtml = isHtml;
        this.itemPicture1 = itemPicture1;
        this.itemPicture2 = itemPicture2;
        this.itemPicture3 = itemPicture3;
        this.itemPicture4 = itemPicture4;
        this.itemPicture5 = itemPicture5;
        this.itemPicture6 = itemPicture6;
        this.itemPicture7 = itemPicture7;
        this.itemPicture8 = itemPicture8;
        this.itemPicture9 = itemPicture9;
        this.itemPicture10 = itemPicture10;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonCode = commonCode;
    }

    /** default constructor */
    public CommonNews() {
    }

    /** minimal constructor */
    public CommonNews(com.kangdainfo.common.model.bo.CommonCode commonCode) {
        this.commonCode = commonCode;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsHtml() {
        return this.isHtml;
    }

    public void setIsHtml(String isHtml) {
        this.isHtml = isHtml;
    }

    public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getItemPicture1() {
        return this.itemPicture1;
    }

    public void setItemPicture1(String itemPicture1) {
        this.itemPicture1 = itemPicture1;
    }

    public String getItemPicture2() {
        return this.itemPicture2;
    }

    public void setItemPicture2(String itemPicture2) {
        this.itemPicture2 = itemPicture2;
    }

    public String getItemPicture3() {
        return this.itemPicture3;
    }

    public void setItemPicture3(String itemPicture3) {
        this.itemPicture3 = itemPicture3;
    }

    public String getItemPicture4() {
        return this.itemPicture4;
    }

    public void setItemPicture4(String itemPicture4) {
        this.itemPicture4 = itemPicture4;
    }

    public String getItemPicture5() {
        return this.itemPicture5;
    }

    public void setItemPicture5(String itemPicture5) {
        this.itemPicture5 = itemPicture5;
    }

    public String getItemPicture6() {
        return this.itemPicture6;
    }

    public void setItemPicture6(String itemPicture6) {
        this.itemPicture6 = itemPicture6;
    }

    public String getItemPicture7() {
        return this.itemPicture7;
    }

    public void setItemPicture7(String itemPicture7) {
        this.itemPicture7 = itemPicture7;
    }

    public String getItemPicture8() {
        return this.itemPicture8;
    }

    public void setItemPicture8(String itemPicture8) {
        this.itemPicture8 = itemPicture8;
    }

    public String getItemPicture9() {
        return this.itemPicture9;
    }

    public void setItemPicture9(String itemPicture9) {
        this.itemPicture9 = itemPicture9;
    }

    public String getItemPicture10() {
        return this.itemPicture10;
    }

    public void setItemPicture10(String itemPicture10) {
        this.itemPicture10 = itemPicture10;
    }

    public String getEditId() {
        return this.editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public String getEditDate() {
        return this.editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditTime() {
        return this.editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public com.kangdainfo.common.model.bo.CommonCode getCommonCode() {
        return this.commonCode;
    }

    public void setCommonCode(com.kangdainfo.common.model.bo.CommonCode commonCode) {
        this.commonCode = commonCode;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonNews [id=");
		builder.append(id);
		builder.append(", commonCode=");
		builder.append(commonCode);
		builder.append(", content=");
		builder.append(content);
		builder.append(", editDate=");
		builder.append(editDate);
		builder.append(", isHtml=");
		builder.append(isHtml);
		builder.append(", isOpen=");
		builder.append(isOpen);
		builder.append(", itemPicture1=");
		builder.append(itemPicture1);
		builder.append(", itemPicture10=");
		builder.append(itemPicture10);
		builder.append(", itemPicture2=");
		builder.append(itemPicture2);
		builder.append(", itemPicture3=");
		builder.append(itemPicture3);
		builder.append(", itemPicture4=");
		builder.append(itemPicture4);
		builder.append(", itemPicture5=");
		builder.append(itemPicture5);
		builder.append(", itemPicture6=");
		builder.append(itemPicture6);
		builder.append(", itemPicture7=");
		builder.append(itemPicture7);
		builder.append(", itemPicture8=");
		builder.append(itemPicture8);
		builder.append(", itemPicture9=");
		builder.append(itemPicture9);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", editId=");
		builder.append(editId);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", editTime=");
		builder.append(editTime);
		builder.append("]");
		return builder.toString();
	}

}
