package com.kangdainfo.tcbw.view.sdrg;



import java.util.Iterator;
import java.util.List;

import org.hibernate.mapping.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg7001Db;
import com.kangdainfo.tcbw.model.bo.Drg7003Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;
import com.kangdainfo.tcbw.model.bo.Drg8005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;




public class SDRG0101F extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)	
	private String doType;	
	private String caseType ;
	private String tabId ;
	private String isUpload;
	/**
	 *   updateType
	 *   0.暫存;  1.送出審核;  2.審核完成; 3.審核退件; 4.廠商回收; 5.廠商回覆; 6.廠商回收+回覆;
	 *   7.廠商回收審核完成;  8.廠商回收審核退件 ;  9.回收確認;   10.回收確認審核完成;  11.回收確認審核退件 
	 *   12.案件評估完成              13.案件分析完成
	 **/
	private String updateType;
	      
    private String applNo;              //案件編號
    private String status;              //案件狀態
    private String chProduct;           //商品名稱中文
    private String enProduct;           //商品名稱英文
    private String permitKey;           //許可證字
    private String permitNo;            //許可證號
    private String ingredient;          //主成分
    private String medModel;            //劑型
    private String manufactorName;      //製造商/製造廠
    private String manufactorAddr;      //製造廠地址
    private String manufactorCountry;   //製造商/製造廠國別
    private String appID;               //許可證持有商統編
    private String appName;             //許可證持有商
    private String appAddr;             //許可證持有商地址
    private String orirecyclereason;    //案件登錄-回收原因
    private String danger;              //可能產生之危險
    private String prerecycledate;      //預計完成回收日期
    private String isabroad;            //是否輸出國外
    private String abroadCountry;       //輸出國別
    private String abroadCountryName;   //輸出國別名稱顯示
    private String abroadCountryOther;  //輸出國別其他
    private String popCodeName;         //pop輸出國別名稱
    private String popCode;             //pop輸出國別代碼
    private String drugsupplier;        //通知藥物供應者方式
    private String precaution;          //擬採取之相關動作
    private String lotnumStockcity;     //回收批號產品最終儲存縣市
    private String lotnumStockarea;     //回收批號產品最終儲存區域
    private String lotnumStock;         //回收批號產品最終儲存地點
    private String contactman;          //聯絡窗口
    private String contacttel;          //聯絡電話
    private String postDate;            //發文日期
    private String postNo;              //回收文號
    private String recycleclass;        //回收分級
    private String msgsource;           //訊息來源
    private String msgsourcedesc;       //訊息來源說明
    private String cureapplno;          //通報編號
    private String quaapplno;           //警訊編號
    private String recycledeadline;     //回收期限
    
	// 回收清單List
	private String lotNo[];           //批號
	private String reservesNum[];     //案件登錄-庫存量
	private String reservesUnit[];    //案件登錄-庫存量單位
	private String sellnum[];         //案件登錄-銷售數量
	private String sellunit[];        //案件登錄-銷售數量單位
	private String drg8002DbType[];
	private String drg8002DbTypeId[];

	private String reportType;         //報表種類
	
	private String commonUser;
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	

	

	// FOR 新增登入該頁面時，自動新增一筆資料
	public void doInsert()throws Exception
	{		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(c == null)
		{
			c = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			c.setCommonDepartment(d);
			System.out.println("[TCBW]-[SDRG0101F]-[新增]-[無法辨別登入的使用者]");
		}

		Drg8001Db obj = new Drg8001Db();
		obj.setStatus("00");
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0101F(this);
	}	
    
	@Override
	public Object doQueryOne() throws Exception {
		SDRG0101F obj = this;	
		
		Drg8001Db c = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0101F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {
			obj.setStatus(c.getStatus());			
			obj.setApplNo(Common.get(c.getApplNo()));
        	obj.setChProduct(Common.get(c.getChProduct()));
        	obj.setEnProduct(Common.get(c.getEnProduct())); 
        	obj.setPermitKey(Common.get(c.getPermitKey()));
        	obj.setPermitNo(Common.get(c.getPermitNo()));
        	obj.setIngredient(Common.get(c.getIngredient()));
        	obj.setMedModel(Common.get(c.getMedModel()));
        	obj.setManufactorName(Common.get(c.getManufactorName()));
        	obj.setManufactorAddr(Common.get(c.getManufactorAddr()));
        	obj.setManufactorCountry(Common.get(c.getManufactorCountry()));
        	obj.setAppID(Common.get(c.getAppID()));
        	obj.setAppName(Common.get(c.getAppName()));
        	obj.setAppAddr(Common.get(c.getAppAddr()));
        	obj.setOrirecyclereason(Common.get(c.getOrirecyclereason()));
        	obj.setDanger(Common.get(c.getDanger()));
        	obj.setPrerecycledate(Common.get(c.getPrerecycledate()));
        	obj.setIsabroad(Common.get(c.getIsabroad()));
        	obj.setAbroadCountry(Common.get(c.getAbroadCountry()));
            String abroadCountryName="";			
	        if(!"".equals(Common.get(c.getAbroadCountry())))
	        {	        	
	        	String[] tmp = c.getAbroadCountry().split(",");
	        	for(int j=0;j<tmp.length;j++)
	        	{	        		
	        		String name = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId='COUC' and codeId="+Common.sqlChar(tmp[j]) );
	        		abroadCountryName += (tmp[j]+"-"+name);
	        		if(j!=0 && j%4==0) 
	        			abroadCountryName +="\n";
	        		else 
	        			abroadCountryName += ", ";
	        	}		
	        }			
			if(abroadCountryName.length()>0)
				obj.setAbroadCountryName(abroadCountryName);
			else
				obj.setAbroadCountryName("");
			obj.setAbroadCountryOther(Common.get(c.getAbroadCountryOther()));
        	obj.setDrugsupplier(Common.get(c.getDrugsupplier()));
        	obj.setPrecaution(Common.get(c.getPrecaution()));
        	obj.setLotnumStockcity(Common.get(c.getLotnumStockcity()));
        	obj.setLotnumStockarea(Common.get(c.getLotnumStockarea()));
        	obj.setLotnumStock(Common.get(c.getLotnumStock()));
        	obj.setContactman(Common.get(c.getContactman()));
        	obj.setContacttel(Common.get(c.getContacttel()));
        	obj.setPostDate(Common.get(c.getPostDate()));
        	obj.setPostNo(Common.get(c.getPostNo()));
        	obj.setRecycleclass(Common.get(c.getRecycleclass()));
        	obj.setMsgsource(Common.get(c.getMsgsource()));
        	obj.setMsgsourcedesc(Common.get(c.getMsgsourcedesc()));
        	obj.setCureapplno(Common.get(c.getCureapplno()));
        	obj.setQuaapplno(Common.get(c.getQuaapplno()));
        	obj.setRecycledeadline(Common.get(c.getRecycledeadline()));			
			
		}
		return obj;
	}

	
	public Object doQueryAll() throws Exception {		
		return null;
	}

	public void doDelete() throws Exception {
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			deleteCon0001DbBySdrg0101(obj.getId());//刪除檔案上傳資料
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}		
	}
	
	public String getDrg8002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		if(!"".equals(Common.get(getId()))){		
			String hql = " from Drg8002Db where 1=1 and drg8001Db.id="+Common.get(getId());		
			//System.out.println("[TCBW]-[SDRG0101F]-[doQueryOne]-[回收清單明細] : " + hql);        
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");	
		
			if(objList!=null && objList.size()>0){			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext()) {				
					Drg8002Db o = (Drg8002Db) it.next();				
					sb.append("addDrg8002Db('").append(Common.get(o.getId())).append("'");				
					sb.append(",'").append(o.getLotNo()).append("'");            //批號				
					sb.append(",'").append(o.getReservesNum()).append("'");      //案件登錄-庫存量				
					sb.append(",'").append(o.getReservesUnit()).append("'");     //案件登錄-庫存量單位				
					sb.append(",'").append(o.getSellnum()).append("'");          //案件登錄-銷售數量				
					sb.append(",'").append(o.getSellunit()).append("');\n");     //案件登錄-銷售數量單位			
				}
			}
		}
		return sb.toString(); 
	}

    
	public String deleteCon0001DbBySdrg0101(Long id) throws Exception {
		//刪除全部檔案
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG04%' and dbName='Drg8001Db' and upLoadId="+Common.getLong(id);		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
		if(objList!=null && objList.size()>0)		
		{			
			java.util.Iterator it = objList.iterator();			
			while (it.hasNext())			
			{				
				Con0001Db o = (Con0001Db) it.next();				
				ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());			
			}		
		}		
		return null;
	}
	
	//附件(回收清單)
	public String getAddFileDrg0401() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040001' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(運銷紀錄)
	public String getAddFileDrg0402() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040002' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(回收通知函)
	public String getAddFileDrg0403() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040003' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");	
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(回收計劃書)
	public String getAddFileDrg0404() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040004' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());        
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");	
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");				
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	
	public Object[] getDefaultTableModelForWord() throws Exception
	{
		Object[] rowArray = null;		//word 替換欄位數
		List list = ServiceGetter.getInstance().getCommonService().load(" from Drg8001Db where id = " + getId());
		if(list!=null && list.size() > 0){
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			java.util.Map<String, String> unitMap = TCBWCommon.getCommonCodeMap("DRGRECUNIT");
			java.util.Map<String, String> recrsMap = TCBWCommon.getCommonCodeMap("DRGRECRS");
			java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN");
			Iterator it = list.iterator();
			while(it.hasNext()){
				Drg8001Db o = (Drg8001Db) it.next();
				//回收通知函
				if("01".equals(getReportType())){   
					rowArray = new String[7];		//word 替換欄位數
					rowArray[0] = Common.formatYYYMMDD(Datetime.getYYYMMDD(),2);		 //##1##日期
					rowArray[1] = Common.get(o.getAppName());						     //##2##許可證持有商
					rowArray[2] = Common.get(o.getChProduct());						     //##3##商品名稱中文					
					rowArray[3] = Common.get(o.getEnProduct());				             //##4##商品名稱英文					
					rowArray[4] = Common.get(!"".equals(Common.get(o.getPermitKey()))?   //##5##許可證字號
							(pkidMap.get(o.getPermitKey())+"-"):"")+o.getPermitNo();					
					String lotNo ="";					
					if(o.getDrg8002Dbs() != null && !o.getDrg8002Dbs().isEmpty()){
						for(Object drg8002Db:o.getDrg8002Dbs()){
							Drg8002Db dtl = (Drg8002Db)drg8002Db;
                            if(lotNo.length() > 0) 
                            	lotNo +=",";
                            lotNo += Common.get(dtl.getLotNo());                            
						}
					}
					rowArray[5] = Common.get(lotNo);					                 //##6##批號
					rowArray[6] = Common.get(o.getAppName());						     //##7##許可證持有商
				}
				//回收計劃書
				else if("02".equals(getReportType())){
					rowArray = new String[14];		//word 替換欄位數
					rowArray[0] = Common.get(o.getApplNo());						     //##1##案件編號
					rowArray[1] = Common.get(o.getAppName());						     //##2##許可證持有商
					rowArray[2] = Common.get(o.getAppAddr());						     //##3##許可證持有商地址					
					rowArray[3] = Common.get(o.getManufactorName());				     //##4##製造廠名稱
					rowArray[4] = Common.get(o.getManufactorAddr());	                 //##5##製造廠地址
					rowArray[5] = Common.get(o.getChProduct());						     //##6##商品名稱中文
					rowArray[6] = Common.get(o.getEnProduct());				             //##7##商品名稱英文
					rowArray[7] = Common.get(!"".equals(Common.get(o.getPermitKey()))?   //##8##許可證字號
							(pkidMap.get(o.getPermitKey())+"-"):"")+o.getPermitNo();				
					rowArray[8] = Common.get(o.getIngredient());					     //##9##主成分
					String lotNo ="";
					String sellnum = "";
					String reservesNum = "";					
					if(o.getDrg8002Dbs() != null && !o.getDrg8002Dbs().isEmpty()){
						for(Object drg8002Db:o.getDrg8002Dbs()){
							Drg8002Db dtl = (Drg8002Db)drg8002Db;
                            if(lotNo.length() > 0) lotNo +=",";
                            if(sellnum.length() > 0) sellnum +=",";                            
                            if(reservesNum.length() > 0) reservesNum +=",";                            
                            lotNo += Common.get(dtl.getLotNo());
                            sellnum += Common.get(dtl.getSellnum())+(!"".equals(Common.get(dtl.getSellunit()))?"("+(unitMap.get(Common.get(dtl.getSellunit()))+")"):"()");    
                            reservesNum += Common.get(dtl.getReservesNum())+(!"".equals(Common.get(dtl.getReservesUnit()))?"("+(unitMap.get(Common.get(dtl.getReservesUnit()))+")"):"()");                      
                        }
					}
					rowArray[9] = Common.get(lotNo);					                 //##10##批號
					rowArray[10] = Common.get(reservesNum);					             //##11##庫存數量(單位)
					rowArray[11] = Common.get(sellnum);				                     //##12##銷售數量(單位)
					rowArray[12] = Common.get(sellnum);		                             //##13##預計回收數量(單位)
					rowArray[13] = Common.formatYYYMMDD(o.getPrerecycledate(),1);		 //##14##預計完成回收日期
				}
				//回收報告書
				else if("03".equals(getReportType())){
					rowArray = new String[15];		//word 替換欄位數
					rowArray[0] = Common.get(o.getApplNo());						     //##1##案件編號
					rowArray[1] = Common.get(o.getAppName());						     //##2##許可證持有商
					rowArray[2] = Common.get(o.getAppAddr());						     //##3##許可證持有商地址					
					rowArray[3] = Common.get(o.getManufactorName());				     //##4##製造廠名稱
					rowArray[4] = Common.get(o.getManufactorAddr());	                 //##5##製造廠地址
					rowArray[5] = Common.get(o.getChProduct());						     //##6##商品名稱中文
					rowArray[6] = Common.get(o.getEnProduct());				             //##7##商品名稱英文
					rowArray[7] = Common.get(!"".equals(Common.get(o.getPermitKey()))?   //##8##許可證字號
							(pkidMap.get(o.getPermitKey())+"-"):"")+o.getPermitNo();				
					rowArray[8] = Common.get(o.getIngredient());					     //##9##主成分
					String applotNo ="";
					String appsellnum = "",appprerecyclenum= "";
					if(o.getDrg8005Dbs() != null && !o.getDrg8005Dbs().isEmpty()){
						for(Object drg8005Db:o.getDrg8005Dbs()){
							Drg8005Db dtl = (Drg8005Db)drg8005Db;
                            if(applotNo.length() > 0) applotNo +=",";
                            if(appsellnum.length() > 0) appsellnum +=",";
                            if(appprerecyclenum.length() > 0) appprerecyclenum +=",";
                            applotNo += Common.get(dtl.getLotNo());
                            appsellnum += Common.get(dtl.getAppsellnum())+(!"".equals(Common.get(dtl.getAppsellunit()))?("("+unitMap.get(Common.get(dtl.getAppsellunit()))+")"):"()");                            
                            appprerecyclenum += Common.get(dtl.getAppprerecyclenum())+(!"".equals(Common.get(dtl.getAppprerecycleunit()))?("("+unitMap.get(Common.get(dtl.getAppprerecycleunit()))+")"):"()");
						}
					}
					rowArray[9] = Common.get(applotNo);					                         //##10##批號
					rowArray[10] = Common.get(appsellnum);		                                 //##11##銷售總數量(單位)
					rowArray[11] = !"".equals(Common.get(o.getApprecyclereason()))?recrsMap.get(Common.get(o.getApprecyclereason())):""+                
							       Common.get(o.getApprecyclersdesc());                          //##12##回收原因
					rowArray[12] = Common.get(appsellnum);		                                 //##13##銷售總數量(單位)
					rowArray[13] = Common.get(appprerecyclenum);	                             //##14##銷售總數量(單位)
					rowArray[14] = Common.formatYYYMMDD(o.getPrerecycledate(),1);		         //##15##完成回收日期
				}
				//廠商"調查報告"及"預防矯正措施"評估表 (狀態為評估之後才可印出)
				else if("04".equals(getReportType())){
					
					Drg8004Db drg8004 = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id="+Common.getLong(o.getId()));
					
					rowArray = new String[25];//word 替換欄位數
					rowArray[0] = Common.formatYYYMMDD(drg8004.getAssessdate(),2);						                     //##1##評估日期
					rowArray[1] = Common.get("中文："+o.getChProduct()+" \\ 英文："+o.getEnProduct());	                     //##2##藥品名					
					rowArray[2] = Common.get(!"".equals(Common.get(o.getPermitKey()))?(pkidMap.get(o.getPermitKey())):"")+   //##3##許可證字號
					                   Common.get(!"".equals(Common.get(o.getPermitNo()))?("第"+o.getPermitNo()+"號"):"");					              					
					rowArray[3] = Common.get(o.getIngredient());				                                             //##4##主成分
					rowArray[4] = !"".equals(Common.get(o.getMedModel()))?flnMap.get(Common.get(o.getMedModel())):"";	     //##5##劑型
					rowArray[5] = Common.get(o.getAppName());						                                         //##6##申請商
					rowArray[6] = Common.get(o.getManufactorName());				                                         //##7##製造廠
					String applotNo ="";					
					if(o.getDrg8005Dbs() != null && !o.getDrg8005Dbs().isEmpty()){
						for(Object drg8005Db:o.getDrg8005Dbs()){
							Drg8005Db dtl = (Drg8005Db)drg8005Db;
                            if(applotNo.length() > 0) 
                            	applotNo +=",";
                            applotNo += Common.get(dtl.getLotNo());                            
						}
					}
					rowArray[7] = Common.get(applotNo);                                                                      //##8##批號				
					rowArray[8] = Common.get(o.getOrirecyclereason());					                                     //##9##回收原因(登錄)					
					rowArray[9] = getPaperCodeName(drg8004.getAnarecyclereason(),"DRGRECRS");		                         //##10##回收原因(分析)
					rowArray[10] = !"".equals(Common.get(drg8004.getAnarecyclersdesc()))?
							Common.get(drg8004.getAnarecyclersdesc()):"　　　　";	                                         //##11##回收原因其他(分析)
					rowArray[11] = Common.get(o.getApplNo());                                                                //##12##通報編號
					String subHql = " from Drg0001Db where applNo is not null and notifierRepDate<="+Common.sqlChar(o.getPostDate());
					if(!"".equals(Common.get(o.getPermitKey())))
						subHql += " and permitKey="+Common.sqlChar(o.getPermitKey());
					if(!"".equals(Common.get(o.getPermitNo())))
						subHql += " and permitNo="+Common.sqlChar(o.getPermitNo());					               
					int drgCount = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(subHql);
					rowArray[12] = Common.get("歷年本藥品之通報件數："+drgCount+"件");                                         //##13##歷年類似不良品通報情形
					rowArray[13] = Common.get(drg8004.getAssessrecyclereason());                                             //##14##本案原由
					rowArray[14] = Common.get(o.getAppsurveyresult());                                                       //##15##清查結果
					rowArray[15] = Common.get(drg8004.getAssesssurveyreport());                                              //##16##調查報告(評估)
					rowArray[16] = getPaperCodeName(drg8004.getAnasurvey(),"DRG0109");                                       //##17##調查結果(分析)
					rowArray[17] = !"".equals(Common.get(drg8004.getAnasurveyOther()))?
							Common.get(drg8004.getAnasurveyOther()):"　　　　";                                              //##18##調查結果其他(分析)
					rowArray[18] = Common.get(drg8004.getAssessprecaution());                                                //##19##預防矯正措施(評估)
					rowArray[19] = getPaperCodeName(drg8004.getAnaprecaution(),"DRG0110");                                  //##20##預防矯正措施(分析)
					rowArray[20] = !"".equals(Common.get(drg8004.getAnaprecautionOther()))?
							Common.get(drg8004.getAnaprecautionOther()):"　　　　";                                          //##21##預防矯正措施其他(分析)
					rowArray[21] = Common.get(drg8004.getAssessresult());                                                   //##22##評估結果
					rowArray[22] = getPaperCodeName(drg8004.getAssessdealWith(),"DRG0111");                                 //##23##本署估及擬辦事項(評估)
					
				}
			}
		}
		return rowArray;
	}
	
	//報表帶入代碼名稱用
	public String getPaperCodeName(String codeId,String codeKind) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList(codeKind);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);				
				String[] tmp = codeId.split(",");
				String checkbox = "";
				for(int j=0; j< tmp.length; j++){									
					if(obj.getCodeId().equals(tmp[j])){
						checkbox = "■" + obj.getCodeName() + "　";
					}else{
						checkbox = "□" + obj.getCodeName() + "　";
					}
				}
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	
	//國家多選清單
	public java.util.ArrayList<String[]> queryAll_prodCountry() throws Exception 
	{
		StringBuilder sb = new StringBuilder(300).append("from CommonCode where commonCodeKind.codeKindId='COUC' ");
		if(!"".equals(Common.get(getPopCode())))
			sb.append(" and codeId ="+Common.sqlChar(getPopCode()));
		if(!"".equals(Common.get(getPopCodeName())))
			sb.append(" and codeName like "+Common.sqlChar("%"+getPopCodeName()+"%"));
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(sb.toString()));

		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query") < 0)
			{	
			  ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getHibernateTemplate().clear();
			}
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(sb.toString() + " order by codeId");

			try 
			{
				if (objList != null && objList.size() > 0) 
				{
					for (int i = 0; i < objList.size(); i++) 
					{
						CommonCode o = (CommonCode) objList.get(i);
						String rowArray[] = new String[4];
						rowArray[0] = Common.get(o.getId());
						rowArray[1] = Common.get(i+1);
						rowArray[2] = Common.get(o.getCodeId());
						rowArray[3] = Common.get(o.getCodeName());
						arrList.add(rowArray);
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return arrList;
	}
	
	public String getId() {		return id;	}
	public void setId(String id) {		this.id = id;	}
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getUpdateType() {		return checkGet(updateType);	}
	public void setUpdateType(String updateType) {		this.updateType = checkSet(updateType);	}	
	public String getIsUpload() {	return checkGet(isUpload);	}
	public void setIsUpload(String isUpload) {	this.isUpload = checkSet(isUpload);	}
	
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	public String getStatus() {
		return checkGet(status);
	}
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	public String getChProduct() {
		return checkGet(chProduct);
	}
	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}
	public String getEnProduct() {
		return checkGet(enProduct);
	}
	public void setEnProduct(String enProduct) {
		this.enProduct = checkSet(enProduct);
	}
	public String getPermitKey() {
		return checkGet(permitKey);
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}
	public String getPermitNo() {
		return checkGet(permitNo);
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}
	public String getIngredient() {
		return checkGet(ingredient);
	}
	public void setIngredient(String ingredient) {
		this.ingredient = checkSet(ingredient);
	}
	public String getMedModel() {
		return checkGet(medModel);
	}
	public void setMedModel(String medModel) {
		this.medModel = checkSet(medModel);
	}
	public String getManufactorName() {
		return checkGet(manufactorName);
	}
	public void setManufactorName(String manufactorName) {
		this.manufactorName = checkSet(manufactorName);
	}
	public String getManufactorAddr() {
		return checkGet(manufactorAddr);
	}
	public void setManufactorAddr(String manufactorAddr) {
		this.manufactorAddr = checkSet(manufactorAddr);
	}
	public String getManufactorCountry() {
		return checkGet(manufactorCountry);
	}
	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = checkSet(manufactorCountry);
	}	
	public String getAppID() {
		return checkGet(appID);
	}
	public void setAppID(String appID) {
		this.appID = checkSet(appID);
	}
	public String getAppName() {
		return checkGet(appName);
	}
	public void setAppName(String appName) {
		this.appName = checkSet(appName);
	}
	public String getAppAddr() {
		return checkGet(appAddr);
	}
	public void setAppAddr(String appAddr) {
		this.appAddr = checkSet(appAddr);
	}
	public String getOrirecyclereason() {
		return checkGet(orirecyclereason);
	}
	public void setOrirecyclereason(String orirecyclereason) {
		this.orirecyclereason = checkSet(orirecyclereason);
	}
	public String getDanger() {
		return checkGet(danger);
	}
	public void setDanger(String danger) {
		this.danger = checkSet(danger);
	}
	public String getPrerecycledate() {
		return checkGet(prerecycledate);
	}
	public void setPrerecycledate(String prerecycledate) {
		this.prerecycledate = checkSet(prerecycledate);
	}
	public String getIsabroad() {
		return checkGet(isabroad);
	}
	public void setIsabroad(String isabroad) {
		this.isabroad = checkSet(isabroad);
	}
	public String getAbroadCountry() {
		return checkGet(abroadCountry);
	}
	public void setAbroadCountry(String abroadCountry) {
		this.abroadCountry = checkSet(abroadCountry);
	}
	public String getAbroadCountryOther() {
		return checkGet(abroadCountryOther);
	}
	public void setAbroadCountryOther(String abroadCountryOther) {
		this.abroadCountryOther = checkSet(abroadCountryOther);
	}
	public String getDrugsupplier() {
		return checkGet(drugsupplier);
	}
	public void setDrugsupplier(String drugsupplier) {
		this.drugsupplier = checkSet(drugsupplier);
	}
	public String getPrecaution() {
		return checkGet(precaution);
	}
	public void setPrecaution(String precaution) {
		this.precaution = checkSet(precaution);
	}
	public String getLotnumStockcity() {
		return checkGet(lotnumStockcity);
	}
	public void setLotnumStockcity(String lotnumStockcity) {
		this.lotnumStockcity = checkSet(lotnumStockcity);
	}
	public String getLotnumStockarea() {
		return checkGet(lotnumStockarea);
	}
	public void setLotnumStockarea(String lotnumStockarea) {
		this.lotnumStockarea = checkSet(lotnumStockarea);
	}
	public String getLotnumStock() {
		return checkGet(lotnumStock);
	}
	public void setLotnumStock(String lotnumStock) {
		this.lotnumStock = checkSet(lotnumStock);
	}
	public String getContactman() {
		return checkGet(contactman);
	}
	public void setContactman(String contactman) {
		this.contactman = checkSet(contactman);
	}
	public String getContacttel() {
		return checkGet(contacttel);
	}
	public void setContacttel(String contacttel) {
		this.contacttel = checkSet(contacttel);
	}
	public String getPostDate() {
		return checkGet(postDate);
	}
	public void setPostDate(String postDate) {
		this.postDate = checkSet(postDate);
	}
	public String getPostNo() {
		return checkGet(postNo);
	}
	public void setPostNo(String postNo) {
		this.postNo = checkSet(postNo);
	}
	public String getRecycleclass() {
		return checkGet(recycleclass);
	}
	public void setRecycleclass(String recycleclass) {
		this.recycleclass = checkSet(recycleclass);
	}
	public String getMsgsource() {
		return checkGet(msgsource);
	}
	public void setMsgsource(String msgsource) {
		this.msgsource = checkSet(msgsource);
	}
	public String getMsgsourcedesc() {
		return checkGet(msgsourcedesc);
	}
	public void setMsgsourcedesc(String msgsourcedesc) {
		this.msgsourcedesc = checkSet(msgsourcedesc);
	}
	public String getCureapplno() {
		return checkGet(cureapplno);
	}
	public void setCureapplno(String cureapplno) {
		this.cureapplno = checkSet(cureapplno);
	}
	public String getQuaapplno() {
		return checkGet(quaapplno);
	}
	public void setQuaapplno(String quaapplno) {
		this.quaapplno = checkSet(quaapplno);
	}
	public String getRecycledeadline() {
		return checkGet(recycledeadline);
	}
	public void setRecycledeadline(String recycledeadline) {
		this.recycledeadline = checkSet(recycledeadline);
	}
	public String getCommonUser() {
		return checkGet(commonUser);
	}
	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}
	public String[] getLotNo() {
		return lotNo;
	}
	public void setLotNo(String[] lotNo) {
		this.lotNo = lotNo;
	}
	public String[] getReservesNum() {
		return reservesNum;
	}
	public void setReservesNum(String[] reservesNum) {
		this.reservesNum = reservesNum;
	}
	public String[] getReservesUnit() {
		return reservesUnit;
	}
	public void setReservesUnit(String[] reservesUnit) {
		this.reservesUnit = reservesUnit;
	}
	public String[] getSellnum() {
		return sellnum;
	}
	public void setSellnum(String[] sellnum) {
		this.sellnum = sellnum;
	}
	public String[] getSellunit() {
		return sellunit;
	}
	public void setSellunit(String[] sellunit) {
		this.sellunit = sellunit;
	}
	public String[] getDrg8002DbType() {
		return drg8002DbType;
	}
	public void setDrg8002DbType(String[] drg8002DbType) {
		this.drg8002DbType = drg8002DbType;
	}
	public String[] getDrg8002DbTypeId() {
		return drg8002DbTypeId;
	}
	public void setDrg8002DbTypeId(String[] drg8002DbTypeId) {
		this.drg8002DbTypeId = drg8002DbTypeId;
	}	
	public String getReportType() {
		return checkGet(reportType);
	}
	public void setReportType(String reportType) {
		this.reportType = checkSet(reportType);
	}

	public String getPopCodeName() {return checkGet(popCodeName);}
	public void setPopCodeName(String popCodeName) {this.popCodeName = checkSet(popCodeName);}

	public String getPopCode() {return checkGet(popCode);}
	public void setPopCode(String popCode) {this.popCode = checkSet(popCode);}

	public String getAbroadCountryName() {return checkGet(abroadCountryName);}
	public void setAbroadCountryName(String abroadCountryName) {this.abroadCountryName = checkSet(abroadCountryName);}
	
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
