package com.hlsofttech.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ChineseCharToEnUtil {
	private final static int[] li_SecPosValue = { 1601, 1637, 1833, 2078, 2274,
			2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
			4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };
	private final static String[] lc_FirstLetter = { "a", "b", "c", "d", "e",
			"f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "w", "x", "y", "z" };

	/**
	 * 取得给定汉字串的首字母串,即声母串
	 * 
	 * @param str
	 *            给定汉字串
	 * @return 声母串
	 */
	public static String getAllFirstLetter(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}

		String _str = "";
		for (int i = 0; i < str.length(); i++) {
			_str = _str + getFirstLetter(str.substring(i, i + 1));
		}

		return _str;
	}

	/**
	 * 取得给定汉字的首字母,即声母
	 * 
	 * @param chinese
	 *            给定的汉字
	 * @return 给定汉字的声母
	 */
	public static String getFirstLetter(String chinese) {
		if (chinese == null || chinese.trim().length() == 0) {
			return "";
		}
		chinese = conversionStr(chinese, "GB2312", "ISO8859-1");

		if (chinese.length() > 1) // 判断是不是汉字
		{
			int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
			int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
			li_SectorCode = li_SectorCode - 160;
			li_PositionCode = li_PositionCode - 160;
			int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
			if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
				for (int i = 0; i < 23; i++) {
					if (li_SecPosCode >= li_SecPosValue[i]
							&& li_SecPosCode < li_SecPosValue[i + 1]) {
						chinese = lc_FirstLetter[i];
						break;
					}
				}
			} else // 非汉字字符,如图形符号或ASCII码
			{
				chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
				chinese = chinese.substring(0, 1);
			}
		}

		return chinese;
	}

	/**
	 * 字符串编码转换
	 * 
	 * @param str
	 *            要转换编码的字符串
	 * @param charsetName
	 *            原来的编码
	 * @param toCharsetName
	 *            转换后的编码
	 * @return 经过编码转换后的字符串
	 */
	private static String conversionStr(String str, String charsetName,
			String toCharsetName) {
		try {
			str = new String(str.getBytes(charsetName), toCharsetName);
		} catch (UnsupportedEncodingException ex) {
			System.out.println("字符串编码转换异常：" + ex.getMessage());
		}
		return str;
	}
	/**
	 * 按字母分类，将不同字母开头的城市，放到不同的集合中，并将这些集合存放到以A-Z为key的map集合中 ,因为取出来的时候要按照A-Z的顺序取出，因此，用TreeMap集合存放数据
	 */
	public static Map<String,List<Map<String,Object>>>  getStringGroup(List<String> list){
		Map<String,List<String>> map=new TreeMap<String,List<String>>();//分组后的城市集合
		String arr[]={"A","B","C","D","E","F","G","H","I","J","K","L","M",
					"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		//创建不同key的map集合,Map("A",list1),Map("B",list2)
		for(int i=0;i<arr.length;i++){
			map.put(arr[i],new ArrayList<String>());
		}
		
		
		for(String c:list){
			String pinyin=ChineseCharToEnUtil.getAllFirstLetter(c).substring(0, 1).toUpperCase();
			if(map.containsKey(pinyin)){
				map.get(pinyin).add(c);
			}
		}
 
		//遍历打印出集合中的值
		Set<Map.Entry<String, List<String>>> set=map.entrySet();
		Map<String,List<Map<String,Object>>> result=new TreeMap<String,List<Map<String,Object>>>();
		for(Map.Entry<String, List<String>> m:set){
			List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
			if(m.getValue()!=null && m.getValue().size()>0){
				//key下面的集合不为空，才显示首字母以及下面的城市
				//System.out.println("========="+m.getKey()+"========");
				Map<String,Object> d=new HashMap<String, Object>();
				d.put("key", m.getKey());
				d.put("value", m.getValue());
				l.add(d);
				/*for(String c:m.getValue()){
					System.out.println(c);
				}*/
			}
			result.put(m.getKey(), l);
		}
		return result;
	}
	public static void main(String[] args) {
		ChineseCharToEnUtil cte = new ChineseCharToEnUtil();
		System.out.println("获取拼音首字母：" + cte.getAllFirstLetter("广州"));
	}

}
