package com.kangdainfo.tcbw.view.hfrex;

import com.kangdainfo.ServiceGetter;

public class HFREX0105F extends HFREX0104F{
	public void doAutoSave() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateByHFREX0104F(this, true);
	}
}
