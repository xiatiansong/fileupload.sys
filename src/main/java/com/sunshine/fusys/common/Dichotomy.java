package com.sunshine.fusys.common;

import java.util.List;

import com.IP;


/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2014-2-11 <br>
 * 功能描述：用于对排序后的IP记录进行查找 <br>
 * 版本： <br>
 */
public class Dichotomy {
	/**
	 * 二分查找
	 */
	static int COMPARE_SUCCESS = 0;

	public static IP search(List<IP> list, Long ipLong) {
		if (list == null || ipLong == null) {
			return null;
		}
		IP sr = null;
		int iindex = 0; // 相当于指针的东西
		int istart = 0; //
		int iend = list.size() - 1;
		for (int i = 0; i < list.size(); i++) {
			if (list.size() <= 2) {
				iindex = i;
			} else {
				iindex = (istart + iend) / 2;
				if (iindex == istart && istart == (iend - 1) && iindex != 0) {
					iindex++;
				}
			}
			sr = list.get(iindex);
			// 使用对象中的key进行查询
			int compare = ipLong.compareTo(sr.getF());
			/*
			 * System.out.println("比较 次数:"+i+ " from:"+sr.getFrom_number() +
			 * " to:"+sr.getTo_number() + " ipLong:"+ipLong+
			 * " 比较 from: "+(sr.getFrom_number() <= ipLong )+ " 比较 to: "+
			 * (sr.getTo_number() >= ipLong)+ " listSize: "+list.size());
			 */
			if (compare > COMPARE_SUCCESS) {
				if (sr.getF() <= ipLong && sr.getT() >= ipLong) {
					return sr;
				}
				istart = iindex;
			} else if (compare < COMPARE_SUCCESS) {
				iend = iindex;
			} else {
				// sis.setSex(sr.getSex());
				// sis.setMobile(sr.getMobile());
				break;
			}
			if (istart == iend) {
				break;
			}
		}
		return new IP();
	}
	
	/*
	 * public static void main(String[] args) { List<IpArea> list = new
	 * ArrayList<IpArea>(); IpArea ip = new IpArea(); ip.setId(1L);
	 * ip.setFrom_number( 0L ); ip.setTo_number( 16777215L ); list.add(ip);
	 * 
	 * 
	 * ip = new IpArea(); ip.setId(2L); ip.setFrom_number( 16777216L );
	 * ip.setTo_number( 16777471L ); list.add(ip);
	 * 
	 * 
	 * ip = new IpArea(); ip.setId(3L); ip.setFrom_number( 16777472L );
	 * ip.setTo_number( 16778239L ); list.add(ip);
	 * 
	 * ip = new IpArea(); ip.setId(4L); ip.setFrom_number( 16778240L );
	 * ip.setTo_number( 16779263L ); list.add(ip);
	 * 
	 * ip = new IpArea(); ip.setId(5L); ip.setFrom_number( 16779264L );
	 * ip.setTo_number( 16781311L ); list.add(ip);
	 * 
	 * ip = new IpArea(); ip.setId(5L); ip.setFrom_number( 16781312L );
	 * ip.setTo_number( 16785407L ); list.add(ip);
	 * 
	 * //(4,16778240,16779263),(5,16779264,16781311),(6,16781312,16785407)
	 * 
	 * Dichotomy.search(list, 16781332L); }
	 */
}