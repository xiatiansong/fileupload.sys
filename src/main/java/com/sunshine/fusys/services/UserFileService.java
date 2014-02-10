package com.sunshine.fusys.services;

import java.util.List;
import java.util.Map;

import org.noo.pagination.page.PageContext;
import org.noo.pagination.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.fusys.mappers.UserFileDao;
import com.sunshine.fusys.models.Page;
import com.sunshine.fusys.models.UserFileDTO;
import com.sunshine.fusys.util.LogUtil;

/**
 * 
 * 创建人：夏天松 <br>
 * 创建时间：2013-12-3 <br>
 * 功能描述： 用户上传下载文件操作DAO<br>
 */
@Service("userFileService")
public class UserFileService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserFileDao userFileDao;
	
	/**
	 * 保存上传文件
	 * 
	 * @param userFile
	 * @author 夏天松
	 * @date 2013-12-3
	 */
	public void save(UserFileDTO userFile){
		userFileDao.save(userFile);
		logger.info(LogUtil.getLogStr("saveUserFile","200",userFile,"",""));
	}

	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 * @author 夏天松
	 * @date 2013-12-3
	 */
	public List<UserFileDTO> find(Map<String, Object> map){
		logger.info(LogUtil.getLogStr("findUserFileList","200",map,"",""));
		return userFileDao.find(map);
	}
	
	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 * @author 夏天松
	 * @date 2013-12-3
	 */
	public Page findPage(Map<String, Object> map){
		logger.info(LogUtil.getLogStr("findUserFileList","200",map,"",""));
		Pagination pager = PageContext.getPageContext();
		List<UserFileDTO> list = userFileDao.findPage(map);
		Page page = new Page(pager.getCurrentPage(), pager.getPageSize(), pager.getTotalPages(), pager.getTotalRows(),list );
		return page;
	}
}