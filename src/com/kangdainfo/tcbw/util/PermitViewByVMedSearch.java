package com.kangdainfo.tcbw.util;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：產品分類POP視窗
*<br>程式代號：NONE
*<br>程式日期：101/08/23
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  PermitViewByVMedSearch extends SuperBean{
	
	String q_permitKey;
	String q_permitNo;
	String q_product;
	String q_appName;
	String q_factName;

	String q_removeKey;
	
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {q_permitNo = checkSet(s);}
	public String getQ_product() {return checkGet(q_product);}
	public void setQ_product(String s) {this.q_product = checkSet(s);}
	public String getQ_appName() {return checkGet(q_appName);}
	public void setQ_appName(String s) {this.q_appName = checkSet(s);}
	public String getQ_factName() {return checkGet(q_factName);}
	public void setQ_factName(String s) {this.q_factName = checkSet(s);}	
	public String getQ_removeKey() {return checkGet(q_removeKey);}
	public void setQ_removeKey(String qRemoveKey) {q_removeKey = checkSet(qRemoveKey);}
	
	@Override
	public Object doQueryOne() throws Exception {
		PermitViewByVMedSearch obj = this;
		return obj;
	}
	@Override
	public Object doQueryAll() throws Exception 
	{
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		
		Database db = new Database("MLMS");
		try 
		{
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			
			String hql = " select a.LICEKID,a.LICID1,a.CHNAME,a.ENNAME,CONVERT(varchar(12) , a.EFDATE, 112 ) EFDATE," +
					    "  a.APPUNNO,a.APPNAME,a.FACTNAME,a.FACTCIDMA,b.MEDCLASS " + 
						"  from VW_ForADR_TBMLIC a left join VW_ForADR_BIGKND2 b"+
			            "  on a.LICEKID+a.LICID1=b.LICID where 1=1 ";
			
			if(null != getQ_removeKey() && !"".equals(getQ_removeKey()))
			{
				String vlaue="";
				
				String[] str=getQ_removeKey().split(",");
				for(int i=0;i<str.length;i++)
				{
					if(vlaue.length()>0)vlaue+=",";
					
					vlaue+="'"+str[i]+"'";
				}	
				
				hql += " and LICEKID+LICID1 not in ("+vlaue+")";
				//parameter.add(getQ_removeKey());
		
			}
			
			if(!"".equals(getLICEKID()))
				  hql += " and LICEKID in ("+getLICEKID()+")";
			
			if (!"".equals(getQ_permitKey()))
			{		
				hql += " and LICEKID = ?";
				parameter.add(getQ_permitKey());
			}
			
			if (!"".equals(getQ_permitNo()))
			{	
				hql += " and LICID1 = ?";
				parameter.add(getQ_permitNo());
			}
			
			if(null != getQ_product() && !"".equals(getQ_product()))
			{
				hql += " and ( chName like ? ";
				parameter.add("%"+getQ_product()+"%");
				
				hql += " or enName like ? )";
				parameter.add("%"+getQ_product()+"%");
			}
			
			if(null != getQ_appName() && !"".equals(getQ_appName()))
			{
				hql += " and appName like ? ";
				parameter.add("%"+getQ_appName()+"%");
			}
			
			if(null != getQ_factName() && !"".equals(getQ_factName()))
			{
				hql += " and factName like ? ";
				parameter.add("%"+getQ_factName()+"%");
			}
			
			System.out.println(hql);
			System.out.println(parameter.toString());
			
			ResultSet rs = db.querySQLByPrepareStatement(hql, parameter);
			while (rs.next()) {
				String rowArray[] = new String[10];
				rowArray[0] = Common.get(rs.getString("LICEKID"))+";"+Common.get(rs.getString("LICID1"));
				rowArray[1] = Common.get(View.getCommonCodeName("MEDPKID",  Common.get(rs.getString("LICEKID"))))+"第"+Common.get(rs.getString("LICID1"))+"號";
				rowArray[2] = Common.get(rs.getString("CHNAME")).replaceAll("\"", "");
				rowArray[3] = Common.get(rs.getString("ENNAME")).replaceAll("\"", "");
				rowArray[4] = Common.get(Datetime.getRocDateFromYYYYMMDD(Common.get(rs.getString("EFDATE"))));
				rowArray[5] = Common.get(rs.getString("APPUNNO"));
				rowArray[6] = Common.get(rs.getString("APPNAME"));
				rowArray[7] = Common.get(rs.getString("FACTNAME"));
				rowArray[8] = Common.get(rs.getString("FACTCIDMA"));
				rowArray[9] = Common.get(rs.getString("MEDCLASS"));
				
				arrList.add(rowArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}		
		return arrList;
	}
	
	public String getLICEKID()
	{
		String licekid="";
		
		String hql = " from CommonCode where (isStop is null or isStop = '' or isStop <> 'Y') and commonCodeKind.codeKindId='MEDPKID' ";

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				CommonCode o = (CommonCode) it.next();
				licekid+="'"+o.getCodeId()+"',";
			}
		}
		
		if(licekid.length()>0)
			licekid=licekid.substring(0, licekid.length()-1);
		
		
		return licekid;
		
		
	}
	
	/**
     * 功能說明：代碼輸入輔助函式
     * @param className
     * @param inputName
     * @param inputValue
     * @param closSize
     * @param rowsSize
     * @param codeKindId
     * @return
     */
    public static String getPopTextareaCode(String className, String inputName, String inputValue, String closSize, String rowsSize, String codeKindId) {
    	StringBuilder sb = new StringBuilder(); 
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<textarea class=\"").append( className ).append( "\" id=\"" ).append( inputName ).append( "\" name=\"" ).append( inputName ).append( "\" cols=\"" ).append( closSize ).append( "\" rows=\"" ).append( rowsSize ).append( "\" >").append(inputValue).append("</textarea> ");
    	if (!("".equals(ROStr))) { 
        	sb.append("<input class=\"" ).append( className ).append( "\" type=\"button\" id=\"btn_" ).append( inputName ).append( "\" name=\"btn_" ).append( inputName ).append( "\" onclick=\"popCode('" ).append( inputName ).append( "','','" ).append( codeKindId ).append( "','','" ).append( inputName ).append( "')\" value=\"...\" title=\"代碼輸入輔助視窗\">");         	
    	}    	
    	return sb.toString(); 
    }
    
    
    public static String getCheckboxQuerylist(boolean primaryArray[], boolean displayArray[], 
    		String[] arrAlign,ArrayList objList, String queryAllFlag, String checkboxName, 
    		boolean linkArray[], String target, String className, int column,
    		boolean disable, boolean checkHtml) 
    {
    	int i, counter=0;
    	boolean trFlag = false, targetFlag = false;

    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) 
    	{
			java.util.Iterator it=objList.iterator();
			while(it.hasNext()) 
			{
				String[] rowArray = (String[])it.next();
				counter++;

				sb.append(" <tr class='highLight' >");
				StringBuilder v = new StringBuilder().append("");
				for(i=0;i<primaryArray.length;i++)
				{
					if (primaryArray[i]) 
					{
						if (trFlag) 
						{
							v.append(";:;'").append(rowArray[i]).append("'");
						}
						else 
						{
							v.append("'").append(rowArray[i]).append("'");
						    trFlag = true;
						}
					}
				}
				

				//依照column判斷，disable為true代表有值時disable CheckBox，disable為false代表無值時disable CheckBox。
				String dc = "";
				String dcmsg = "";
				if(rowArray[1].equals("S"))
				{
					dc = "disabled";
					dcmsg = "(系統設定)";
				}
				//Checked
				String checked = "";
				if(rowArray[1].equals("Y"))
				{
					checked = "checked";
				}				
				
				//顯示TD
				if (className!=null && !"".equals(Common.get(className))) {
					sb.append(" <td nowrap class='listTD' >").append("<input type='checkbox' ").append(dc).append(" class='").append(className).append("' id=\"").append(checkboxName).append("\" name=\"").append(checkboxName).append("\" value=\"").append(v.toString().replaceAll("'", "")).append("\" "+checked+">"+dcmsg+"</td>\n");
				} else {
					sb.append(" <td nowrap class='listTD' >").append("<input type='checkbox' ").append(dc).append(" id=\"").append(checkboxName).append("\" name=\"").append(checkboxName).append("\" value=\"").append(v.toString().replaceAll("'", "")).append("\" "+checked+">"+dcmsg+"</td>\n");
				}				
				targetFlag = false;
				for(i=0;i<displayArray.length;i++)
				{
					if (displayArray[i]) 
					{
						if (targetFlag==false && target!=null && !"".equals(Common.get(target))) 
						{
							v.append(",'").append(target).append("'");
							targetFlag = true;
						}

						if (arrAlign!=null && arrAlign.length>0) {
							sb.append(" <td nowrap class='listTD' style=\"text-align:").append(arrAlign[i]).append("\">"); //.append(Common.get(rowArray[i])).append("</td>\n");
						} else {
							sb.append(" <td nowrap class='listTD' >");
						}
						if (linkArray!=null && linkArray[i]) {
							sb.append("<a href='#' class='sLink2' onClick=\"queryOne(").append(v).append(",").append(i).append(")\"");

							sb.append(">").append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i])).append("</a>");
						} else sb.append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i]));

						sb.append("</td>\n");
					}
				}
				sb.append("</tr>\n");
				trFlag = false;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td nowrap class='listTD' colspan='100'>查無資料，請您重新輸入查詢條件！</td></tr>");
    	}
		return sb.toString();
    }
	
	
	
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}