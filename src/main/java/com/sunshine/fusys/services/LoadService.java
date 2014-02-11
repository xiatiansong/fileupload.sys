package com.sunshine.fusys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IP;
import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import com.sunshine.fusys.common.SystemCache;
import com.sunshine.fusys.util.LogUtil;
/**
*
* 创建人：fantasy
* 创建时间:2013/04/24
* 功能描述:加载IP区
*/
@Service("loadService")
public class LoadService {
	
	private final Logger LOGGER=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IpAreaDictionaryService ipAreaDictionaryService;
	
	@Autowired
	private CacheFactory userCache;
	
	public synchronized void onMessage(){

		List<IP> list = (List<IP>) SystemCache.getCache(SystemCache.IP_AREA_LIST);
		if(list == null){
			Cache cache = userCache.getCache();
			list = new ArrayList<IP>();
			try {
				for(int i = 1;;i++){
					List<IP> subareaList = cache.get("IP_AREA_LIST_"+i, SerializationType.JSON);
					if(subareaList == null || subareaList.size() <1){
						break;
					}else{
						list.addAll( subareaList );
					}
				}
				if( list == null || list.size() < 1 ){
					long start = System.currentTimeMillis();
					System.out.println("============start");
					list = ipAreaDictionaryService.findAll();
					long end = System.currentTimeMillis();
					System.out.println("============"+(end - start) + "ms");
					List<IP> subareaList = new ArrayList<IP>();
					int subarea = 1;
					for (int i = 0;i<list.size() ;i++) {
						subareaList.add( list.get(i) );
						if((i!=0 && i%4999 == 0 )|| (i+1) == list.size() ){
							cache.add(SystemCache.IP_AREA_LIST+"_"+subarea, 0, subareaList , SerializationType.JSON);
							subarea++;
							subareaList = new ArrayList<IP>();
						}
					}
				}
				SystemCache.setCache( SystemCache.IP_AREA_LIST, list );
				
			} catch (TimeoutException e) {
				LOGGER.error(LogUtil.getLogStr("LoadTimeOut","500","","",e.getMessage()),e);
			} catch (CacheException e) {
				LOGGER.error(LogUtil.getLogStr("LoadException","500","","",e.getMessage()),e);
			}
		}
	}
}