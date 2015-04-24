package com.kangdainfo.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MathUtil {

	// 預設除法運算精度
	private static final int DEF_DIV_SCALE = 5;

	private MathUtil() {
		// avoid instantiation...
	}

	/**
	 * @param v1
	 *            被加數
	 * @param v2
	 *            加數
	 * @return 兩個參數的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供（相對）精確的減法運算。
	 * 
	 * @param v1
	 *            被減數
	 * @param v2
	 *            減數
	 * @return 兩個參數的差
	 */
	public static double minus(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供（相對）精確的乘法運算。
	 * 
	 * @param v1
	 *            被乘數
	 * @param v2
	 *            乘數
	 * @return 兩個參數的積
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相對）精確的除法運算，當發生除不盡的情況時，精確到 小數點以後10位元，以後的數字四捨五入。
	 * 
	 * @param v1
	 *            被除數
	 * @param v2
	 *            除數
	 * @return 兩個參數的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相對）精確的除法運算。當發生除不盡的情況時，由scale參數指 定精度，以後的數字四捨五入。
	 * 
	 * @param v1
	 *            被除數
	 * @param v2
	 *            除數
	 * @param scale
	 *            表示表示需要精確到小數點以後幾位。
	 * @return 兩個參數的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供（相對）精確的小數位四捨五入處理。
	 * 
	 * @param v
	 *            需要四捨五入的數位
	 * @param scale
	 *            小數點後保留幾位
	 * @return 四捨五入後的結果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 將兩個相同長度數值陣列加總<br>
	 * 要注意 d 的數值會被改變後回傳
	 * 
	 * @param d
	 * @param s
	 * @return
	 */
	public static double[] merge(double[] d, double[] s) {
		for (int i = 0; i < d.length; i++)
			d[i] += s[i];
		return d;
	}

	/**
	 * 檢查數值陣列裡是否都是0
	 * 
	 * @param d
	 * @return
	 */
	public static boolean isAllZero(double[] d) {
		for (int i = 0; i < d.length; i++) {
			if (d[i] != 0.0)
				return false;
		}
		return true;
	}

	/**
	 * 獲取百分比
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static String percent(double p1, double p2, int scale) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(scale);
		str = nf.format(p3);
		return Common.get(str);
	}

}
