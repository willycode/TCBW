package com.kangdainfo.tcbw.view.drgex;

import com.kangdainfo.ServiceGetter;

public class DRGEX0303F extends DRGEX0302F
{
	public void doAutoSave() throws Exception
	{
		System.out.println("doAutoSave");
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().updateByDrgEX0302F(this);
	}
}
