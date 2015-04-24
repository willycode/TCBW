package com.kangdainfo.tcbw.view.drgex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;

public class DRGEX0108F extends SuperBean{
	
	
	private String applNo;
	private String lotNo05;	//批號	VARCHAR(50)
	private String replyDate05;	//回覆日期	VARCHAR(7)
	private String beforeOrLater05;	//前次CAPA執行前或執行後製造	VARCHAR(1)
	private String capaDate05; //前次CAPA執行日期
	
	private String hisApplNoY; //一年內本藥品同此次瑕疵案件之同批號的各案件編號
	private String hisApplNoN; //一年內本藥品同此次瑕疵案件之不同批號的各案件編號
	
	public String getApplNo() {return applNo;}
	public void setApplNo(String applNo) {this.applNo = applNo;}
	public String getLotNo05() {return checkGet(lotNo05);}
	public void setLotNo05(String lotNo05) {this.lotNo05 = checkSet(lotNo05);}
	public String getReplyDate05() {return checkGet(replyDate05);}
	public void setReplyDate05(String replyDate05) {this.replyDate05 = checkSet(replyDate05);}
	public String getBeforeOrLater05() {return checkGet(beforeOrLater05);}
	public void setBeforeOrLater05(String beforeOrLater05) {this.beforeOrLater05 = checkSet(beforeOrLater05);}
	public String getCapaDate05() {return checkGet(capaDate05);}
	public void setCapaDate05(String capaDate05) {this.capaDate05 = checkSet(capaDate05);}
	
	public String getHisApplNoY() {return checkGet(hisApplNoY);}
	public void setHisApplNoY(String hisApplNoY) {this.hisApplNoY = checkSet(hisApplNoY);}
	public String getHisApplNoN() {return checkGet(hisApplNoN);}
	public void setHisApplNoN(String hisApplNoN) {this.hisApplNoN = checkSet(hisApplNoN);}

	@Override
	public Object doQueryOne() throws Exception {
		DRGEX0108F obj = this;
		Drg0005Db c = (Drg0005Db) View.getObject(" from Drg0005Db where applNo="+Common.sqlChar(getApplNo()));		
		if(c != null){
			obj.setLotNo05(Common.get(c.getLotNo()));
			obj.setBeforeOrLater05(Common.get(c.getBeforeOrLater()));
			obj.setCapaDate05(Common.get(c.getCapaDate()));
			obj.setReplyDate05(Common.get(c.getReplyDate()));
		}
		//取得不良品缺陷資料
		Drg0001Db drg01 = (Drg0001Db) View.getObject(" from Drg0001Db where applNo="+Common.sqlChar(getApplNo()));
		if(drg01 != null){		
			String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(drg01.getId());		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");        
			String subCodeList =""; 
		
			if(objList!=null && objList.size()>0){			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext())		
				{				
					Drg0002Db o = (Drg0002Db) it.next();				
					String[] subList = o.getSubCode().split(",");				
					for(int j=0; j<subList.length; j++){					
						if(Common.get(subList[j]) != ""){				        
							subCodeList += "subCode like ('%"+subList[j]+"%') or ";					
						}				
					}			
				}		
			}		
			if(subCodeList.length()>0) subCodeList = subCodeList.substring(0, subCodeList.length()-3);       
			//一年內本藥品同此次瑕疵案件「同批號的各案件編號、不同批號的各案件編號」       
			String sql8 = " select manufactorNo,applNo from Drg0001Db where permitKey="+Common.sqlChar(drg01.getPermitKey())+" and permitNo="+Common.sqlChar(drg01.getPermitNo())+                      
        	              " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+ 
        	              " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(getApplNo())+") "+
        	              " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";		
            java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(sql8+" order by id asc");		
            String hisApplNoY="",hisApplNoN="";		
            if(objList2!=null && objList2.size()>0)		
            {        	
            	java.util.Iterator it = objList2.iterator();       	
            	while (it.hasNext())       	
            	{       		
            		Object[] itobj = (Object[])it.next();       		
            		if(Common.get(itobj[0]).equals(drg01.getManufactorNo())) hisApplNoY = itobj[1]+",";        		
            		else hisApplNoN = itobj[1]+",";        	
            	}       
            }        
            if(hisApplNoY!="") hisApplNoY = hisApplNoY.substring(0,hisApplNoY.length()-1);       
            if(hisApplNoN!="") hisApplNoN = hisApplNoN.substring(0,hisApplNoN.length()-1);        
            obj.setHisApplNoY(hisApplNoY);       
            obj.setHisApplNoN(hisApplNoN);		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		return null;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception
	{
		Drg0005Db obj = (Drg0005Db)View.getObject(" from Drg0005Db where applNo = " + Common.sqlChar(getApplNo()));
		if(obj!=null)
		{
			//obj.setBeforeOrLater(Common.get(getBeforeOrLater05()));
			obj.setCapaDate(Common.get(getCapaDate05()));
			obj.setReplyDate(Datetime.getYYYMMDD());
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}	
		
	}

	@Override
	public void doDelete() throws Exception{}

	
}


