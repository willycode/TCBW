package com.kangdainfo.tcbw.view.drgex;

import com.kangdainfo.ServiceGetter;

public class DRGEX0103F extends DRGEX0102F
{
	public void doAutoSave() throws Exception
	{
		System.out.println("doAutoSave");
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().updateByDrgEX0102F(this);
	}
}
