package com.sunshine.fusys.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sunshine.fusys.models.UserFileDTO;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-12-3 <br>
 * 功能描述： 用户上传下载文件操作DAO<br>
 */
public interface UserFileDao {

	/**
	 * 保存上传文件
	 * 
	 * @param userFile
	 * @author fantasy
	 * @date 2013-12-3
	 */
	public void save(@Param("userFile") UserFileDTO userFile);

	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 * @author fantasy
	 * @date 2013-12-3
	 */
	public List<UserFileDTO> find(Map<String, Object> map);

	/**
	 * 查询用户上传文件列表
	 * 
	 * @param map
	 * @return List<UserFileDTO>
	 * @author fantasy
	 * @date 2013-12-3
	 */
	public List<UserFileDTO> findPage(Map<String, Object> map);
}