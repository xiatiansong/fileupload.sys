package com.sunshine.fusys.models;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-12-3 <br>
 * 功能描述： 用户上传文件<br>
 * 
 */
@Alias("userFileDTO")
public class UserFileDTO extends BaseDTO {

	private static final long serialVersionUID = 8100125356643639203L;

	/** ID **/
	private long id;
	/** 帐号 **/
	private long userId;
	/** 姓名 **/
	private String userName;
	/** 操作时间 **/
	private Date operateTime;
	/** IP地址 **/
	private String ipAddress;
	/** 文件名 **/
	private String fileName;
	/** 文件大小 M **/
	private String fileSize;
	/** 文件路径 **/
	private String filePath;
	/** 上传类别,1-内网上传,2-外网上传 **/
	private int uploadType;
	/** 备注 **/
	private String remark;

	public UserFileDTO() {
	}

	public UserFileDTO(long userId, Date operateTime, String ipAddress,
			String fileName, String filePath, int uploadType) {
		super();
		this.userId = userId;
		this.operateTime = operateTime;
		this.ipAddress = ipAddress;
		this.fileName = fileName;
		this.filePath = filePath;
		this.uploadType = uploadType;
	}
	
	public UserFileDTO(long userId, Date operateTime, String ipAddress,
			String fileName, String fileSize, String filePath, int uploadType) {
		super();
		this.userId = userId;
		this.operateTime = operateTime;
		this.ipAddress = ipAddress;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.uploadType = uploadType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getUploadType() {
		return uploadType;
	}

	public void setUploadType(int uploadType) {
		this.uploadType = uploadType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}