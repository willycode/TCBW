/**
來自康大台北市政府免書證案
**/
function lpad(vstr,strlen,vfixstr)
{
  var vstrlen = vstr.length;
  var text = vstr;
  while (vstrlen < strlen)
  {
    if (vstrlen == strlen)
  	{
 	   break;
 	 }
  	else
	{
	  text =  vfixstr + text;
	  vstrlen = vstrlen + vfixstr.length;
	}
  }
  if (vstr.length == 0)
  {
    for (var i=0;i < strlen;i++)
	{
     text = text + vfixstr;
	}
  }
 return (text);
}

function rpad(vstr,strlen,vfixstr){
  var vstrlen = vstr.length;
  var text = vstr;
  if (typeof(vstr)=="undefined"){
	  vstr = '';
  }
  while (vstrlen < strlen){
    if (vstrlen == strlen){
 	   break;
 	}else {
	  text =   text + vfixstr ;
	  vstrlen = vstrlen + vfixstr.length;
	}
  }
  return (text);
}

function chnumbertype(vtype,vstr){
  var vstrlen = vstr.length;
  var text='';
  var textstr='';
  if (typeof(vstr)=='undefined')
  {
	  vstr = '';
  }
  for (var i=0;i<vstrlen;i++)
  {
	  if (vtype == '1')
	  {
		  textstr = vstr.substr(i,1);
		  if (textstr == '1')
		  {
			  textstr = '１';
		  }
		  if (textstr == '2')
		  {
			  textstr = '２';
		  }
		  if (textstr == '3')
		  {
			  textstr = '３';
		  }
		  if (textstr == '4')
		  {
			  textstr = '４';
		  }
		  if (textstr == '5')
		  {
			  textstr = '５';
		  }
		  if (textstr == '6')
		  {
			  textstr = '６';
		  }
		  if (textstr == '7')
		  {
			  textstr = '７';
		  }
		  if (textstr == '8')
		  {
			  textstr = '８';
		  }
		  if (textstr == '9')
		  {
			  textstr = '９';
		  }
		  if (textstr == '0')
		  {
			  textstr = '０';
		  }
	  }
	  if ((vtype == '2')||(vtype == '3'))
	  {
		  textstr = vstr.substr(i,1);
		  if (textstr == '1')
		  {
			  textstr = '一';
		  }
		  if (textstr == '2')
		  {
			  textstr = '二';
		  }
		  if (textstr == '3')
		  {
			  textstr = '三';
		  }
		  if (textstr == '4')
		  {
			  textstr = '四';
		  }
		  if (textstr == '5')
		  {
			  textstr = '五';
		  }
		  if (textstr == '6')
		  {
			  textstr = '六';
		  }
		  if (textstr == '7')
		  {
			  textstr = '七';
		  }
		  if (textstr == '8')
		  {
			  textstr = '八';
		  }
		  if (textstr == '9')
		  {
			  textstr = '九';
		  }
		  if (textstr == '0')
		  {
			  textstr = '０';
		  }
	  }
      text = text + textstr;
  }
  return(text);
}

/**************************************************************************
將數字轉中文
Mode in [1,2] >>123 to 一二三  or 壹貳參
Mode in [3,4] >>123 to 一百二十三 or 壹百貳拾參
Mode in [5,6] >>5,6 對應至 3,4 但是把零移除(除了十位數的零,如1004會變成一千零四而不會變成一千四)
**************************************************************************/
function toChNumber(sNum,Mode){
  var sNumber=sNum.toString();
  var cnnumArray=new Array('零','一','二','三','四','五','六','七','八','九');
  var cbnumArray=new Array('零','壹','貳','參','肆','伍','陸','柒','捌','玖');
  var cbdigiArray=new Array('','十','百','千','萬','十','百','千','億','十','百','千','兆','十','百','千','京','十','百','千','正','十','百','千');
  var cndigiArray=new Array('','拾','佰','仟','萬','拾','佰','仟','億','拾','佰','仟','兆','拾','佰','仟','京','拾','佰','仟','正','拾','佰','仟');
  var DialMode12=function(S,M){
    var Result="";    
    for(var i=0;i<S.length;i++){
      if(parseInt(M)==1){
      	if(S.charAt(i)=="0") Result+='○';
      	else Result+=cnnumArray[parseInt(S.substr(i,1))];      	
      }else Result+=cbnumArray[parseInt(S.substr(i,1))];
    }
    return Result;
  };
  var Digit,Str,Minus;
  var Frac="";
  var Result="";
  sNumber = sNumber.replace(/,/g,"")||"";
  Digit=0;
  if(sNumber=="")return;
  for(var i=0;i<sNumber.length;i++){
   if(sNumber.charAt(i)==".")Digit++;
   if(Digit>1){alert(sNumber+"不是合法的阿拉伯數字");throw "";}
   if(sNumber.charAt(i).match(/[-,\.,0-9]/)==null)return;
   Minus=sNumber.charAt(0)=="-";
   if(Minus)Str=sNumber.substr(1);
   else Str=sNumber;
   if(Str.indexOf(".")!=-1){
     Frac=Str.substr(Str.indexOf(".")+1);
     Str=Str.substring(0,Str.indexOf("."));
     if((Frac!="")&&(Mode==4))
       while((Frac!="")&&(Frac.charAt(Frac.length-1)=="0"))
         Frac=Frac.substring(0,Frac.length-2);
     if(Str=="") Str="0";
   }
  }
  Digit=Str.length;
  if((Str=="")&&(Frac=="")) return;
  switch(parseInt(Mode)){
    case 1:case 2:
      while(Str.length<Digit)Str="0"+Str;
      if(Minus) Result="負"+DialMode12(Str,Mode);
      else Result=DialMode12(Str,Mode);
      break;
    case 3:case 4:
     if((Str.charAt(Str.length-1)=="0")&&(Str.length>1))
       Str = Str.substr(0,Str.length-1)+'-';
     for(var i=Str.length-2;i>0;i--){
       if((Str.substr(i-1,2)=="00")&&((Str.length-i-5)%4!=0))
       	 Str = Insert(Delete(Str,i,1),"-",i);
     }
     for(var i=0;i<Str.length;i++){
       if(Str.charAt(i)!="-"){
       	 if(Str.charAt(i)!="0"){
       	   Result+=DialMode12(Str.charAt(i),Mode-2);
       	   if(Mode==3)
       	     Result+=cbdigiArray[Str.length-i-1];
       	   else
       	     Result+=cndigiArray[Str.length-i-1];
       	 }else{
       	   if((Str.length-i-5)%4==0){
       	     if(Result.charAt(Result.length-1)=="零")
       	       Result = Result.substr(0,Result.length-1);
       	     Result += cndigiArray[Str.length-i-1];
       	   }
           Result+=DialMode12(Str.charAt(i),2);
       	 }
       }
     }
     if(Minus) Result="負"+Result;
     if((Result.length>1)&&(Result.charAt(Result.length-1)=="零"))
       Result=Result.substring(0,Result.length-1);
     Result = Result.replace(/一十/g,"十")||"";
     if((Result.indexOf("萬零")>-1)&&
        (Result.charAt(Result.indexOf("萬零")+3).match(/[千,仟]/)!=null))
       Result = Delete(Result,Result.indexOf("萬零")+1,1);
     if(Result.indexOf("億萬")>-1)     
       Result = Delete(Result,Result.indexOf("億萬")+1,1);
     if(Result.indexOf("兆億")>-1)
       Result = Delete(Result,Result.indexOf("兆億")+1,1); 
     if(Result.indexOf("京兆")>-1)
       Result = Delete(Result,Result.indexOf("京兆")+1,1); 
     if(Result.indexOf("正京")>-1)
       Result = Delete(Result,Result.indexOf("正京")+1,1);
     while(Result.charAt(0).match(/[千,仟,萬,億,兆,京,正]/)!=null){
       Result = Result.substr(1);
       if(Result.charAt(0)=="零")
         Result = Result.substr(1);
     }
     break;
    case 5:case 6:
     Result = toChNumber(Str,Mode-2);
     if(Minus) Result = "負"+Result;
     while(Result.indexOf("零")>-1){
       if((Result.length-Result.indexOf("零"))==2)break;
       Result = Delete(Result,Result.indexOf("零"),1);
     }
     break;
    default:      
      alert("Mode必須為１－６");
      throw "";
  }
  if(Frac!="")
    Result += ("點"+toChNumber(Frac,2-(Mode%2)));
  if(Mode>2){
    Result = Result.replace(/○/g,"零")||"";
    while(Result.charAt(0)=="零")
      Result = Result.substr(1);
  }
  if((Result=="")||(Result.charAt(0)=="點"))
    Result = "零"+Result;
  return Result;
}