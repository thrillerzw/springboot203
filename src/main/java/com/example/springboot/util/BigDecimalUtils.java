package com.example.springboot.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalUtils {
	//double加法,不要用 new BigDecimal(d1); 使用这种方式BigDecimal.valueOf(d1);
	public static BigDecimal add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		//b1.add(b2).doubleValue();
		return b1.add(b2);
	}
	//double加法2
	public static BigDecimal add2(double d1, double d2) {
		BigDecimal b1 = BigDecimal.valueOf(d1);
		BigDecimal b2 = BigDecimal.valueOf(d2);
		return b1.add(b2);
	}
	//double加法3
	public static BigDecimal addByString(String d1, String d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2);
	}
	//double减法
	public static BigDecimal sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2);
	}
	//double乘法
	public static BigDecimal mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2);
	}
    /**
     * double除法运算，四舍五入后保留小数点指定位数
     * @param d1 除数
     * @param d2 被除数
     * @param scale 小数点后保留几位
     * @return
     */
	public static BigDecimal div(double d1, double d2, int scale) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	//double四舍五入操作
	public static BigDecimal round(double d, int len) {
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP表示四舍五入的操作。RoundingMode.HALF_UP包装了BigDecimal.ROUND_HALF_UP，一样的
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP);
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
			String d="10002222.1254567";
			String e="10.005";
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

			//加法
			System.out.println(add(a,b)); //10002232.1304567001014955707205444923602044582366943359375
			System.out.println(add2(a,b)); //10002232.1304567
			System.out.println(addByString(d,e));//10002232.1304567
	}
}
