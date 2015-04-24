package com.kangdainfo.tcbw.view.hfrex;

import com.kangdainfo.ServiceGetter;

public class HFREX0103F extends HFREX0102F {

	
	public void doAutoSave() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateByHFREX0102F(this, true);
	}
	
	
}
