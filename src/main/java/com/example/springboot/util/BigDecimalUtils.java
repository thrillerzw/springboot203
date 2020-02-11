package com.example.springboot.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalUtils {
	//double加法
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	//double减法
	public static double sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}
	//double乘法
	public static double mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}
    /**
     * double除法运算，四舍五入后保留小数点指定位数
     * @param d1 除数
     * @param d2 被除数
     * @param len 小数点后保留几位
     * @return
     */
	public static double div(double d1, double d2, int len) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	//double四舍五入操作
	public static double round(double d, int len) {
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP表示四舍五入的操作。RoundingMode.HALF_UP包装了BigDecimal.ROUND_HALF_UP，一样的
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	//BigDecimal比较大小,不要用equals
	public static int compare(BigDecimal d1 ,BigDecimal d2){
		return d1.compareTo(d2) ;
	}

	public static final String DECIMALFORMAT = "####0.00";

	/**
	 * 十进制数值格式化
	 * @param decimalFormat
	 * @return
	 */
	public static DecimalFormat getDecimalFormat(String decimalFormat){
		if(null == decimalFormat || decimalFormat.trim().length() == 0){
			decimalFormat = DECIMALFORMAT;
		}
		DecimalFormat df = new DecimalFormat(decimalFormat);
		//四舍五入
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.setGroupingUsed(false);
		return df;
	}

	/**
	 * 格式化输出 十进制数值
	 * @param num 默认值=0
	 * @param decimalFormat 默认值=####0.00
	 * @return
	 */
	public static String fomart(Number num, String decimalFormat) {
		if (num == null) {
			num = 0;
		}

		String fomartStr = getDecimalFormat(decimalFormat).format(num);
		return fomartStr;
	}


	public static void main(String[] args) {
		  	double a=10002222.1254567;
		  	double b=10.005;
			System.out.println("a>b="+(a>b));
			System.out.println(round(a,2));
			System.out.println(round(b,2));
			System.out.println("除法运算：" + BigDecimalUtils.div(10, 3, 2));
			System.out.println(BigDecimalUtils.fomart(a,null));

			//超过long的大数运算
			System.out.println(Long.MAX_VALUE);//9223372036854775807
			BigInteger bi=new BigInteger("9223372036854775808");
			BigInteger bi2=new BigInteger("2");
			System.out.println(bi.add(bi2));
	}
}
