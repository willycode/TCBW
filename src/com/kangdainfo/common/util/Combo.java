package com.kangdainfo.common.util;

/**
 * 可以編輯的select box, 還不成熟
 * @author shark
 *
 */
public class Combo {

	private Combo() {}
	
	/**
	 * 可以編輯的select box, 還不成熟<br>
	 * 需搭配combo.js, combo.css才能運作<br>
	 * 
	 * @param textboxId
	 * @param textboxValue
	 * @param size
	 * @param maxLength
	 * @param uri : 若isFixOption為true時為textOption,請參照View.getTextOption格式 
	 * @param hql
	 * @param fdNameParam
	 * @param isFixOption
	 * @return
	 */
    public static String getComboBox(String textboxId, String textboxValue, Integer size, Integer maxLength, String uri, String hql, String fdNameParam, String fdFieldParam, boolean isFixOption) {
        StringBuilder sb = new StringBuilder(500);        
        sb.append("<table class='comboBoxControl_table' id=\"table_").append(textboxId).append("\" cellpadding='0' cellspacing='0'>");
        sb.append("<tr><td nowrap>");
        sb.append("<input class=\"comboBoxControl_textbox\" type=\"text\"");
        sb.append(" size=\"").append(size!=null?size:20).append("\"");
        if (maxLength!=null && maxLength>0) sb.append(" maxlength=\"").append(maxLength).append("\"");
        sb.append(" name=\"").append(textboxId).append("\" id=\"").append(textboxId).append("\"");
        sb.append(" value=\"").append(textboxValue).append("\"");
        sb.append(">");
        sb.append("<input class=\"comboBoxControl_btn\" type=\"button\" id=\"btn_").append(textboxId).append("\">");
        sb.append("</td></tr>");        
        //sb.append("<tr><td nowrap>");
        //sb.append("<select id=\"layer_").append(textboxId).append("\" multiple=\"multiple\" size=\"6\" style=\"display:none\"></select>");
        //sb.append("</td></tr>");        
        sb.append("</table>");
        
        //multiple=\"multiple\" size=\"6\" 
        sb.append("<select id=\"layer_").append(textboxId).append("\" style=\"display:none\">");
        if (isFixOption) sb.append(View.getTextOption(uri,"",2));
        sb.append("</select>");
                
        sb.append("<script type=\"text/javascript\">\n");
        //sb.append("new comboBoxControl(\"").append(textboxId).append("\",\"btn_").append(textboxId).append("\",\"layer_").append(textboxId).append("\",");
        sb.append("new comboBoxControl(\"").append(textboxId).append("\",");
        sb.append("\"").append(uri).append("\",\"").append(hql).append("\"");
    	sb.append(",\"").append(Common.get(fdNameParam)).append("\"");
    	sb.append(",\"").append(Common.get(fdFieldParam)).append("\"");
    	sb.append(",").append(isFixOption?"true":"false");
        sb.append(");\n");
        sb.append("</script>");
        
        return sb.toString();
    } 	
    
    public static String getComboBox(String textboxId, String textboxValue, Integer size, Integer maxLength, String textOption) {
    	return getComboBox(textboxId, textboxValue, size, maxLength, textOption, null, null, null, true);
    }     
}
