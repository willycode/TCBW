package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Set;


/** @author Hibernate CodeGenerator */
public class CommonDtree implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private int sysid;

    /** nullable persistent field */
    private Long pid;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String url;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String target;

    /** nullable persistent field */
    private String icon;

    /** nullable persistent field */
    private String iconOpen;

    /** nullable persistent field */
    private String opened;

    /** nullable persistent field */
    private String btnRead;

    /** nullable persistent field */
    private String btnWrite;

    /** nullable persistent field */
    private Integer sorted;

    private String code;
    
    /** full constructor */
    public CommonDtree(Long id, int sysid, Long pid, String name, 
    		String url, String title, String target, String icon, 
    		String iconOpen, String opened, String btnRead, String 
    		btnWrite, Integer sorted,String code, Set commonAuths) {
        this.id = id;
        this.sysid = sysid;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.title = title;
        this.target = target;
        this.icon = icon;
        this.iconOpen = iconOpen;
        this.opened = opened;
        this.btnRead = btnRead;
        this.btnWrite = btnWrite;
        this.sorted = sorted;
        this.code = code;
    }

    /** default constructor */
    public CommonDtree() {
    }

    /** minimal constructor */
    public CommonDtree(Long id, int sysid, Set commonAuths) {
        this.id = id;
        this.sysid = sysid;
    }

    public Long getId() {
        return this.id;
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

	public int getSysid() {
        return this.sysid;
    }

    public void setSysid(int sysid) {
        this.sysid = sysid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconOpen() {
        return this.iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getOpened() {
        return this.opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getBtnRead() {
        return this.btnRead;
    }

    public void setBtnRead(String btnRead) {
        this.btnRead = btnRead;
    }

    public String getBtnWrite() {
        return this.btnWrite;
    }

    public void setBtnWrite(String btnWrite) {
        this.btnWrite = btnWrite;
    }

    public Integer getSorted() {
        return this.sorted;
    }

    public void setSorted(Integer sorted) {
        this.sorted = sorted;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonDtree [id=");
		builder.append(id);
		builder.append(", btnRead=");
		builder.append(btnRead);
		builder.append(", btnWrite=");
		builder.append(btnWrite);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", iconOpen=");
		builder.append(iconOpen);
		builder.append(", name=");
		builder.append(name);
		builder.append(", opened=");
		builder.append(opened);
		builder.append(", pid=");
		builder.append(pid);
		builder.append(", sorted=");
		builder.append(sorted);
		builder.append(", sysid=");
		builder.append(sysid);
		builder.append(", target=");
		builder.append(target);
		builder.append(", title=");
		builder.append(title);
		builder.append(", url=");
		builder.append(url);
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}

}
