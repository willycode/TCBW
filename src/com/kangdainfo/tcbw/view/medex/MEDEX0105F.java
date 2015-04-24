package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;

public class MEDEX0105F extends MEDEX0104F
{
	public void doAutoSave() throws Exception
	{
		System.out.println("doAutoSave");
		ServiceGetter.getInstance().getTcbwService().getMedex1Dao().updateByMedEX0104F(this);
	}
}
