package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;

public class MEDEX5103F extends MEDEX5102F
{
	public void doAutoSave() throws Exception
	{
		System.out.println("doAutoSave");
		ServiceGetter.getInstance().getTcbwService().getMedex2Dao().updateByMedEX5102F(this);
	}
}
