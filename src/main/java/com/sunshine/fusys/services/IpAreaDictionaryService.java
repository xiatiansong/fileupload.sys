package com.sunshine.fusys.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IP;
import com.sunshine.fusys.mappers.IpAreaDictionaryDao;
import com.sunshine.fusys.models.IpAreaDictionary;
import com.sunshine.fusys.util.IpTool;

/**
*
* 创建人：fantasy
* 创建时间:2013/03/6
* 功能描述:IP信息表
*/
@Service("ipAreaDictionaryService")
public class IpAreaDictionaryService {
	
	@Autowired
	private IpAreaDictionaryDao ipAreaDictionaryDao;

	public IpAreaDictionary find(String ipaddr) {
		IpAreaDictionary dictionary = null;
		if (StringUtils.isNotEmpty(ipaddr)) {
			dictionary = ipAreaDictionaryDao.find(IpTool.setIP(ipaddr));
		}
		return dictionary;
	}

	public List<IP> findAll() {
		//查询总记录数
		int count = ipAreaDictionaryDao.findCount();
		int size = 10000;
		List<IP> retList = new ArrayList<IP>(count);
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("size", size);
		for(int i = 0; i < count;i = i + size){
			map.put("start_num", i);
			retList.addAll(ipAreaDictionaryDao.findAll(map));
		}
		return retList;
	}
}