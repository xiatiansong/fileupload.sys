package com.sunshine.fusys.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.IP;
import com.sunshine.fusys.models.IpAreaDictionary;

/**
*
* 创建人：fantasy
* 创建时间:2013/03/6
* 功能描述:IP信息表
*/
public interface IpAreaDictionaryDao {
	/**
	 * 查询单个IP区域
	 */
	public IpAreaDictionary find(@Param("from") Long from);
	
	/**
	 * 查询所有
	 */
	public List<IP> findAll(Map<String,Integer> map);
	
	/**
	 * 获取总记录数
	 */
	public int findCount();

}
