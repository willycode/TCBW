package com.kangdainfo.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckIdNum {

	public static Boolean validate(String id)
	{
		Boolean confrimId = Boolean.TRUE;
			if( id.length()!=10)	//throw new IdentityValidateException("長度不正確");
				return Boolean.FALSE;
				
			String idWord = id.substring(0,1);
			String idNum = id.substring(1,10);

			if( !getCities().contains(idWord.toUpperCase()) ) 
				return Boolean.FALSE;
				//throw new IdentityValidateException("縣市不正確");
			if( !(idNum.substring(0,1).equals("1") || idNum.substring(0,1).equals("2")) ) 
				return Boolean.FALSE;
				//throw new IdentityValidateException("性別不正確");
			
			try{
				Integer.parseInt(idNum);
			}catch(Exception e){
				return Boolean.FALSE;
				//throw new IdentityValidateException("數字不正確");
			}

			if( !countCheckNum(idWord,idNum.substring(0,8)).equals(new Integer(idNum.substring(8))) )	
				return Boolean.FALSE;
				//throw new IdentityValidateException("檢查碼不正確");
			
			return confrimId;
	}
	
	private static Integer countCheckNum(String idWord,String idNum)
	{
		Integer cal = ( 10 - ( ( calIdWord(idWord)+calIdNum(idNum) ) % 10 ) ) % 10;
		return cal;
	}
	
	private static Integer calIdWord(String idWord)
	{
		String num = citiesNum().get(idWord);
		return (new Integer(num.substring(0,1)))+((new Integer(num.substring(1,2)))*9);
	}
	
	private static Integer calIdNum(String idNum)
	{
		Integer num = new Integer(0);
		char[] ch = idNum.toCharArray();
		for(int i=0;i<ch.length;i++)
		{
			num += ((8-i)*( new Integer(String.valueOf(ch[i])) ));
		}
		return num;
	}
	
	private static Map<String,String> citiesNum()
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("A", "10");
		map.put("B", "11");
		map.put("C", "12");
		map.put("D", "13");
		map.put("E", "14");
		map.put("F", "15");
		map.put("G", "16");
		map.put("H", "17");
		map.put("I", "34");
		map.put("J", "18");
		map.put("K", "19");
		map.put("L", "20");
		map.put("M", "21");
		map.put("N", "22");
		map.put("O", "35");
		map.put("P", "23");
		map.put("Q", "24");
		map.put("R", "25");
		map.put("S", "26");
		map.put("T", "27");
		map.put("U", "28");
		map.put("V", "29");
		map.put("W", "32");
		map.put("X", "30");
		map.put("Y", "31");
		map.put("Z", "33");
		return map;
		
	}
	
	private static List<String> getCities()
	{
		List<String> list = new LinkedList<String>();
		list.add("A");//臺北市(A)
		list.add("B");//臺中市(B)
		list.add("C");//基隆市(C)
		list.add("D");//臺南市(D)
		list.add("E");//高雄市(E)
		list.add("F");//臺北縣(F)
		list.add("G");//宜蘭縣(G)
		list.add("H");//桃園縣(H)
		list.add("I");//嘉義市(I)
		list.add("J");//新竹縣(J)
		list.add("K");//苗栗縣(K)
		list.add("L");//臺中縣(L)
		list.add("M");//南投縣(M)
		list.add("N");//彰化縣(N)
		list.add("O");//新竹市(O)
		list.add("P");//雲林縣(P)
		list.add("Q");//嘉義縣(Q)
		list.add("R");//臺南縣(R)
		list.add("S");//高雄縣(S)
		list.add("T");//屏東縣(T)
		list.add("U");//花蓮縣(U)
		list.add("V");//臺東縣(V)
		list.add("W");//金門縣(W)
		list.add("X");//澎湖縣(X)
		list.add("Y");//陽明山(Y)
		list.add("Z");//連江縣(Z)
		return list;
	}
}
