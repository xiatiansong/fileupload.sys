package com.sunshine.fusys.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sunshine.fusys.common.Constants;
import com.sunshine.fusys.models.Page;
import com.sunshine.fusys.models.UserFileDTO;
import com.sunshine.fusys.services.UserFileService;
import com.sunshine.fusys.util.DateTime;
import com.sunshine.fusys.util.FileOperateUtil;
import com.sunshine.fusys.util.IpTool;
import com.sunshine.fusys.util.JsonUtil;
import com.sunshine.fusys.util.NumUtil;
import com.sunshine.fusys.util.StringUtil;

/**
 * 
 * 创建人：夏天松 <br>
 * 创建时间：2013-12-6 <br>
 * 功能描述： 用户文件上传下载和文件列表<br>
 */
@Controller
@RequestMapping("/userFile")
public class UserFileController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserFileService userFileService;
	
	@RequestMapping(value = "/indexPage")
	public String indexPage(HttpServletRequest request) throws Exception {
		System.out.println("-----------userFile.index-------------");
		//文件列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", 1);
		String uploadType = request.getParameter("uploadType");
		String fileName = request.getParameter("fileName");
		if(StringUtil.isNotEmpty(uploadType)){
			map.put("upload_type", Integer.parseInt(uploadType));
			request.setAttribute("uploadType", uploadType);
		}
		if(StringUtil.isNotEmpty(fileName)){
			map.put("file_name", fileName);
			request.setAttribute("fileName", fileName);
		}
		Page page = userFileService.findPage(map);
		request.setAttribute("lst_file", page);
		return "main/index-userFile";
	}
	
	public String getFolder(MultipartHttpServletRequest request, HttpServletResponse response, Map<String, Object> result){
	    if(StringUtil.isEmpty(Constants.UPLOAD_FILE_PATH)){
	        return request.getSession().getServletContext().getRealPath("/");
	    }
	    return Constants.UPLOAD_FILE_PATH;
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		String folder = "";
		try {
			folder = getFolder(request, response, result);
		} catch (Exception ex) {
			result.put("message", "获取folder失败");
			return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
		}
		if (StringUtil.isEmpty(folder)) {// step-1 获得文件夹
			response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			if (!result.containsKey("message")) {
				result.put("message", "处理失败");
			}
			return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
		}
		if (handler(request, response, result, folder)) {
			result.put("status", "success");
			result.put("message", "上传成功");
		}
		return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
	}
	
	/**
	 * 处理文件上传
	 */
	public boolean handler(MultipartHttpServletRequest request, HttpServletResponse response, Map<String, Object> result, String folder) throws IOException{
		Date baseDate = new Date();
		String fileName = DateTime.toDate("yyyyMMddHHmmss", baseDate);
		MultipartFile file = request.getFile("file");
		if (file == null) {// step-2 判断file
			return getError("文件内容为空", HttpStatus.UNPROCESSABLE_ENTITY, result, response, null);
		}
		String orgFileName = file.getOriginalFilename();
		orgFileName = (orgFileName == null) ? "" : orgFileName;
		Pattern p = Pattern.compile("\\s|\t|\r|\n");
        Matcher m = p.matcher(orgFileName);
        orgFileName = m.replaceAll("_");
		String realFilePath = folder  + File.separator + "admin" + File.separator;
		if(!(new File(realFilePath).exists())){
			new File(realFilePath).mkdirs();
		}
		String bigRealFilePath = realFilePath  + File.separator + FilenameUtils.getBaseName(orgFileName).concat(".") + fileName.concat(".").concat(FilenameUtils.getExtension(orgFileName).toLowerCase());
		if (file.getSize() > 0) {
			File targetFile = new File(bigRealFilePath);
			file.transferTo(targetFile);//写入目标文件
		}
		//保存user file
		UserFileDTO fileDTO = new UserFileDTO(1, 
																		new Date(), 
																		IpTool.getClientAddress(request), 
																		orgFileName, 
																		bigRealFilePath, 
																		1);
		fileDTO.setFileSize(String.valueOf(NumUtil.divideNumber(file.getSize(), 1024*1024)));
		userFileService.save(fileDTO);
		return true;
	}
	
	boolean getError(String message, HttpStatus status, Map<String, Object> result, HttpServletResponse response, Exception ex) {
		response.setStatus(status.value());
		result.put("message", message);
		LOG.warn(message, ex);
		return false;
	}
	
	/**文件下载**/
    @RequestMapping("download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		String fileId = request.getParameter("fileId");
    		if(StringUtil.isEmpty(fileId)){
    			map.put("status", "error");
    			map.put("message", "下载错误");
    			return ajaxJson(JsonUtil.getJsonString4JavaPOJO(map), response);
    		}
        	map.put("file_id", fileId);
        	List<UserFileDTO> list = userFileService.find(map);
        	UserFileDTO file = list.get(0);
			FileOperateUtil.download(request, response, "application/octet-stream; charset=utf-8", file.getFilePath(), file.getFileName());
			return null;
		} catch (IOException e) {
			logger.error("文件下载出错");
			map.put("status", "error");
			map.put("message", "下载错误");
		}
        return ajaxJson(JsonUtil.getJsonString4JavaPOJO(map), response);
    }

    /**获取文件大小**/
    @RequestMapping(value = "/getfilesize")
	@ResponseBody
	public String getFileSize(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String fileId = request.getParameter("fileId");
    	map.put("file_id", fileId);
    	List<UserFileDTO> list = userFileService.find(map);
    	if(list.size() != 0){
    		UserFileDTO file = list.get(0);
        	Long fileLength = new File(file.getFilePath()).length();
        	return fileLength.toString();
    	}
    	return (new Long(0L)).toString();
	}
}