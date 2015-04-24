package com.kangdainfo.tcbw.view.cosex;

import com.kangdainfo.ServiceGetter;

public class COSEX0103F extends COSEX0102F{

	
	public void doAutoSave() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateByAutoCOSEX0102F(this);
	}
	
	
}
