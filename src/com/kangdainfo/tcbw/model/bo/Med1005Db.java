package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Med1005Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    private String code;//	代碼
    private String level1code;//	Level1代碼
    private String name;//	名稱
    private String term;//	term
    private String definition;//	Definition
    private String fdacode;//	FDA代碼
    private String fdaname;//	FDA名稱
    private String codelevel;//	層級
    private String isstop;//	是否停用
    private String receivesystem;//介接異動系統
    private String receivedate;//介接異動日期
    private String receivetime;//介接異動時間
    private String creator;//建檔者
    private String createdate;//建檔日期
    private String createtime;//建檔時間
    private String modifier;//最後異動者
    private String modifydate;// 最後異動日期
    private String modifytime;//最後異動時間

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel1code() {
		return level1code;
	}

	public void setLevel1code(String level1code) {
		this.level1code = level1code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getFdacode() {
		return fdacode;
	}

	public void setFdacode(String fdacode) {
		this.fdacode = fdacode;
	}

	public String getFdaname() {
		return fdaname;
	}

	public void setFdaname(String fdaname) {
		this.fdaname = fdaname;
	}

	public String getCodelevel() {
		return codelevel;
	}

	public void setCodelevel(String codelevel) {
		this.codelevel = codelevel;
	}

	public String getIsstop() {
		return isstop;
	}

	public void setIsstop(String isstop) {
		this.isstop = isstop;
	}

	public String getReceivesystem() {
		return receivesystem;
	}

	public void setReceivesystem(String receivesystem) {
		this.receivesystem = receivesystem;
	}

	public String getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	public String getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	/** default constructor */
    public Med1005Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med1005Db [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", level1code=");
		builder.append(level1code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", term=");
		builder.append(term);
		builder.append(", definition=");
		builder.append(definition);
		builder.append(", fdacode=");
		builder.append(fdacode);
		builder.append(", fdaname=");
		builder.append(fdaname);
		builder.append(", codelevel=");
		builder.append(codelevel);
		builder.append(", isstop=");
		builder.append(isstop);
		builder.append(", receivesystem=");
		builder.append(receivesystem);
		builder.append(", receivedate=");
		builder.append(receivedate);
		builder.append(", receivetime=");
		builder.append(receivetime);	
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", createdate=");
		builder.append(createdate);
		builder.append(", createtime=");
		builder.append(createtime);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", modifydate=");
		builder.append(modifydate);
		builder.append(", modifytime=");
		builder.append(modifytime);
		builder.append("]");
		return builder.toString();
	}    
    

	
}
