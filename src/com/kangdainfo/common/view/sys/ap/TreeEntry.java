package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDtree;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

public class TreeEntry extends SuperBean {
	
    public static final int getSubSystemID(String s) {
    	String[] subSystemCode = {"default"};    	
    	int[] subSystemID = {-11};    	
    	for (int i=0; i<subSystemCode.length; i++) {
    		if (s.equals(subSystemCode[i])) return subSystemID[i];
    	}
    	return -11;    	
    }
    public static final String getSubSystemName(int s) {
    	String[] subSystemName = new String[] {"預設系統"};
    	int[] subSystemID = new int[]{-11};    	
    	for (int i=0; i<subSystemID.length; i++) {
    		if (s==subSystemID[i]) return subSystemName[i];
    	}
    	return "";
    }
	
	protected String sysid;
	protected String organID;
	protected String organIDName;	
	protected String icon;
	protected String iconOpen;
	protected String id;
	protected String name;
	protected String opened;
	protected String pid;
	protected String target;
	protected String title;
	protected String url;
	protected String btnRead;
	protected String btnWrite;
	protected String sorted;
	protected String includeChild;
	protected String pname;
	protected String hasChild;
	protected String oldid;
	protected String code;
	
	public String getSysid() { return checkGet(sysid); }
	public void setSysid(String s) { sysid = checkSet(s); }
	public String getOrganID(){ return checkGet(organID); }
	public void setOrganID(String s){ organID=checkSet(s); }
	public String getOrganIDName(){ return checkGet(organIDName); }
	public void setOrganIDName(String s){ organIDName=checkSet(s); }	
	public String getOldid() { return checkGet(oldid); }
	public void setOldid(String s) { oldid = checkSet(s); }
	public String getHasChild() { return checkGet(hasChild); }
	public void setHasChild(String s) { hasChild = checkSet(s); } 
	public String getPname() { return checkGet(pname); }
	public void setPname(String s) { pname = checkSet(s); }
	public String getIcon() { return checkGet(icon); }
	public void setIcon(String s) { icon = checkSet(s); }
	public String getIconOpen() { return checkGet(iconOpen); }
	public void setIconOpen(String s) { iconOpen = checkSet(s);}
	public String getId() {return checkGet(id);}
	public void setId(String s) { id = checkSet(s);}
	public String getName() { return checkGet(name); }
	public void setName(String s) {name = checkSet(s);}
	public String getOpened() {return checkGet(opened);}
	public void setOpened(String s) {opened = checkSet(s);}
	public String getPid() {return checkGet(pid);}
	public void setPid(String s) {pid = checkSet(s);}
	public String getTarget() {return checkGet(target);}
	public void setTarget(String s) {target = checkSet(s);}
	public String getTitle() {return checkGet(title);}
	public void setTitle(String s) {title = checkSet(s);}
	public String getUrl() {return url;}
	public void setUrl(String s) {url = s;}
	
	public void setBtnRead(String s) {btnRead = checkSet(s);}	
	public String getBtnRead() {return checkGet(btnRead);}	
	
	public void setBtnWrite(String s) {btnWrite = checkSet(s);}	
	public String getBtnWrite() {return checkGet(btnWrite);}
	
	public void setSorted(String s) {sorted = checkSet(s);}	
	public String getSorted() {return checkGet(sorted);}		
	
	public void setIncludeChild(String s) {includeChild = checkSet(s);}	
	public String getIncludeChild() {return checkGet(includeChild);}		

	public String getCode() {
		return checkGet(code);
	}
	public void setCode(String code) {
		this.code = checkSet(code);
	}
	@Override
	public void doCreate() throws Exception {
		try {
			String x = View.getLookupField("select max(id)+1 from " + CommonDtree.class.getCanonicalName());
			if (x!=null && !"".equals(x)) setId(x);
			else setId("1");			
		} catch (Exception e) {
			setId("1");
		}

		
		//setId(x);
		
		//setId(View.getLookupField("select max(id)+1 from " + DtreeNode.class.getCanonicalName()).toString());		
		//if (true) throw new Exception("對不起");
		//BeanUtil.getPropertyNames(TreeEntry.class);	
		CommonDtree obj = new CommonDtree();		
		obj.setSysid(Integer.parseInt(sysid));
		obj.setId(Long.parseLong(getId()));
		obj.setPid(Long.parseLong(getPid()));
		obj.setName(getName());
		obj.setUrl(getUrl());
		obj.setTarget(getTarget());
		obj.setIcon(getIcon());
		obj.setIconOpen(getIconOpen());
		obj.setOpened(getOpened());
		obj.setBtnRead(getBtnRead());
		obj.setBtnWrite(getBtnWrite());
		obj.setCode(getCode());
		obj.setSorted(Integer.parseInt(Common.getNumericString(getSorted())));	
		
		ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().save(obj);
		//ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().saveDtreeNode(obj);		
	}
	
	@Override
	public void doUpdate() throws Exception {	
		CommonDtree obj = new CommonDtree();		
		obj.setSysid(Integer.parseInt(getSysid()));
		obj.setId(Long.parseLong(getId()));
		obj.setPid(Long.parseLong(getPid()));
		obj.setName(getName());
		obj.setUrl(getUrl());
		obj.setTarget(getTarget());
		obj.setIcon(getIcon());
		obj.setIconOpen(getIconOpen());
		obj.setOpened(getOpened());
		obj.setBtnRead(getBtnRead());
		obj.setBtnWrite(getBtnWrite());
		obj.setSorted(Integer.parseInt(Common.getNumericString(getSorted())));
		obj.setCode(getCode());
		//ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().update(obj);
		ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().saveOrUpdate(obj);
		
	}
	
	protected String[][] getDeleteCheckSQL(){
	    String[][] checkSQLArray = new String[1][4];
	    checkSQLArray[0][0]=" select count(*) from CommonAuth where commonDtree.id=" + Common.getLong(getId()); 
	    checkSQLArray[0][1]=">";
	    checkSQLArray[0][2]="0";
	    checkSQLArray[0][3]="該功能已經有賦予使用者，請先取消授權後再刪除！";
	    
	    return checkSQLArray;
	}
	
	@Override
	public void doDelete() throws Exception {
		Long x = new Long(Long.parseLong(getId()));
		ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().deleteDtreeNode(x);
	}
	
	@Override
	public TreeEntry doQueryOne() throws Exception {
		TreeEntry obj = this;
		if(Common.getInt(getId())>0){
			Long x = new Long(Long.parseLong(getId()));		
			CommonDtree dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getNode(x);	
			obj.setSysid(""+dt.getSysid());
			obj.setId(""+dt.getId());
			obj.setPid(dt.getPid()==null?obj.getSysid():dt.getPid()+"");
			obj.setName(dt.getName());
			obj.setUrl(dt.getUrl());
			obj.setTarget(dt.getTarget());
			obj.setIcon(dt.getIcon());
			obj.setIconOpen(dt.getIconOpen());
			obj.setOpened(dt.getOpened());
			obj.setBtnRead(dt.getBtnRead());
			obj.setBtnWrite(dt.getBtnWrite());
			obj.setSorted(dt.getSorted()==null?"1000":dt.getSorted()+"");
	        
			obj.setCode(dt.getCode());
			
			/**
			 * 取得父節點名稱
			 */
			Long q = new Long(Long.parseLong(obj.getPid()));		
			CommonDtree parent = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getNode(q);
			if (parent!=null) obj.setPname(parent.getName());
			else obj.setPname(getSubSystemName(Integer.parseInt(obj.getSysid())));
		}else{
			obj.setId(getSysid());
			obj.setName("功能選單");
		}
		return obj;
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		return null;
	}
	
	
	/**
	 * 建立功能選單維護作業的樹(可按滑鼠右鍵那一支)<br>要記得先setSysid的值
	 * 
	 * @return 已經組合的dTree Javascript
	 * @throws Exception
	 */
	public String buildManageTree() throws Exception {
		//id, pid, name, url, title, target, icon, iconOpen, open
		CommonDtree[] dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysid));
		if (dt!=null && dt.length>0) {
			StringBuilder sb = new StringBuilder();			
			for (int i=0; i<dt.length; i++) {
				sb.append("d.add(").append(dt[i].getId()).append(",");
								
				if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
				else sb.append(getSysid()).append(",");
				
				sb.append("'").append(dt[i].getName()).append("',");					
				sb.append("'").append("dTreeForm.jsp?sid=").append(dt[i].getId()).append("&fid=").append(dt[i].getPid()==null?getSysid():dt[i].getPid()).append("',");
				sb.append(Common.sqlChar(dt[i].getTitle())).append(",");
				sb.append(Common.sqlChar(dt[i].getTarget())).append(",");
				sb.append(Common.sqlChar(dt[i].getIcon())).append(",");
				sb.append(Common.sqlChar(dt[i].getIconOpen()));
				sb.append(Common.get(dt[i].getOpened()).equals("Y")?",true":"");
				sb.append(");\n");
			}
			return sb.toString();
		}
		return "";
	}
	
	
	
	/**
	 * 建立功能選單維護作業中有checkbox的選單<br>要記得先setSysid的值
	 * 
	 * @param treeName : e.g. TreeMenu
	 * @param jsFunctionName : e.g. getNode
	 * @param bUrl : e.g. false
	 * @param bRootCheckBox : e.g. true
	 * @return 已經組合好的dTree Javascript
	 */
	public String buildCheckBoxTree(String treeName, String jsFunctionName, boolean bUrl, boolean bRootCheckBox) throws Exception {		
		StringBuffer sb = new StringBuffer(1024).append("");
		if (Common.get(treeName).equals("")) treeName = "功能選單";
		
		//建立根節點
		sb.append("d.add(");					
		sb.append(getSysid()).append(",-1,'");		
		if (bRootCheckBox) {
			sb.append("<input type=checkbox id=").append(getSysid()).append(" name=auth class=checkbox onclick=");
			sb.append(jsFunctionName).append("(this,\"").append(treeName).append("\") value=").append(getSysid()).append(">");			
		}		
		sb.append(treeName).append("'");
		if (bUrl) sb.append(",'").append("dTreeForm.jsp?sid=").append(getSysid()).append("&fid=-1'");
		sb.append(");\n");

		//id, pid, name, url, title, target, icon, iconOpen, open
		CommonDtree[] dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysid));
		if (dt!=null && dt.length>0) {			
			for (int i=0; i<dt.length; i++) {
				
				sb.append("d.add(").append(dt[i].getId()).append(",");
				
				if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
				else sb.append(getSysid()).append(",");
				
				sb.append("'<input type=checkbox id=").append(dt[i].getId()).append(" name=auth class=checkbox onclick=").append(jsFunctionName).append("(this,\"").append(dt[i].getName()).append("\") value=").append(dt[i].getId()).append(">").append(dt[i].getName()).append("',");
				if (bUrl) sb.append("'").append("dTreeForm.jsp?sid=").append(dt[i].getId()).append("&fid=").append(dt[i].getPid()).append("',");
				else sb.append("'',");
				
				sb.append(Common.sqlChar(dt[i].getTitle())).append(",");
				sb.append(Common.sqlChar(dt[i].getTarget())).append(",");
				sb.append(Common.sqlChar(dt[i].getIcon())).append(",");
				sb.append(Common.sqlChar(dt[i].getIconOpen()));
				sb.append(Common.get(dt[i].getOpened()).equals("Y")?",true":"");
				sb.append(");\n");
			}
			return sb.toString();
		}		
		
		return sb.toString();
	}	
	
	/**
	 * 建立某個子系統的功能選單<br>要記得先setSysid的值
	 * 
	 * @return 已經組合的Menu Javascript
	 * @throws Exception
	 */
	public String buildMenuTree() throws Exception {		
		try {
			//id, pid, name, url, title, target, icon, iconOpen, open
			CommonDtree[] dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysid));
			if (dt!=null && dt.length>0) {
				StringBuilder sb = new StringBuilder();			
				for (int i=0; i<dt.length; i++) {
					sb.append("d.add(").append(dt[i].getId()).append(",");
									
					if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
					else sb.append(getSysid()).append(",");
					
					sb.append(Common.sqlChar(dt[i].getName())).append(",'");										
					if (Common.get(dt[i].getUrl()).indexOf('?')!=-1) {
						sb.append(Common.get(dt[i].getUrl())).append("&progID=").append(dt[i].getId());
					} else {
						sb.append(Common.get(dt[i].getUrl())).append("?progID=").append(dt[i].getId());
					}
					sb.append("',");					
					
					sb.append(Common.sqlChar(dt[i].getTitle())).append(",");
					sb.append(Common.sqlChar(dt[i].getTarget())).append(",");
					sb.append(Common.sqlChar(dt[i].getIcon())).append(",");
					sb.append(Common.sqlChar(dt[i].getIconOpen()));
					sb.append(Common.get(dt[i].getOpened()).equals("Y")?",true":"");
					sb.append(");\n");
				}
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}	
	



}
