package com.kangdainfo.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public abstract class BarcodeUtil
{
	public static Image getCode39BarcodeImage(Integer value)
	{	return getCode39BarcodeImage(value.toString(), false, false);	}
	public static Image getCode39BarcodeImage(String value)
	{	return getCode39BarcodeImage(value, false, false);	}
	public static Image getCode39BarcodeImage(Integer value, boolean requiresChecksum, boolean drawingText)
	{	return getCode39BarcodeImage(value.toString(), requiresChecksum, drawingText);	}
	public static Image getCode39BarcodeImage(String value, boolean requiresChecksum, boolean drawingText)
	{
		Barcode barcode = getCode39Barcode(value, requiresChecksum, drawingText);
		return getBarcodeImage(barcode);
	}

	public static Image getCode128BarcodeImage(Integer value)
	{	return getCode128BarcodeImage(value.toString(), false);	}
	public static Image getCode128BarcodeImage(String value)
	{	return getCode128BarcodeImage(value.toString(), false);	}
	public static Image getCode128BarcodeImage(Integer value, boolean drawingText)
	{	return getCode128BarcodeImage(value.toString(), drawingText);	}
	public static Image getCode128BarcodeImage(String value, boolean drawingText)
	{
		Barcode barcode = getCode128Barcode(value, drawingText);
		return getBarcodeImage(barcode);
	}

	private static Image getBarcodeImage(Barcode barcode)
	{
		BufferedImage image = null;
		try {
			image = BarcodeImageHandler.getImage(barcode);
		} catch (OutputException e) {
			e.printStackTrace();
		}
		return image;
	}

	private static Barcode getCode39Barcode(String value, boolean requiresChecksum, boolean drawingText)
	{
		Barcode barcode = null;
		try {
			barcode = BarcodeFactory.create3of9(value, requiresChecksum);
			barcode.setDrawingText(drawingText);
		} catch (BarcodeException e) {
			e.printStackTrace();
		}
		return barcode;
	}

	private static Barcode getCode128Barcode(String value, boolean drawingText)
	{
		Barcode barcode = null;
		try {
			barcode = BarcodeFactory.createCode128(value);
			barcode.setDrawingText(drawingText);
		} catch (BarcodeException e) {
			e.printStackTrace();
		}
		return barcode;
	}


}
