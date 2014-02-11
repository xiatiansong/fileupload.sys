package com.sunshine.fusys.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-5-15 <br>
 * 功能描述：操作FPT工具类 <br>
 * 
 */
public class FtpUtil {

	private final static Logger LOG = Logger.getLogger(FtpUtil.class);

	/**
	 * 上传文件
	 * 
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param remoteFileName
	 * @param input
	 * @return boolean
	 */
	public static boolean uploadFile(String ip, int port, String username, String password, String remotePath,
			String remoteFileName, InputStream input) {
		Long start = System.currentTimeMillis();
		boolean result = false;
		FTPClient ftp = null;
		try {
			ftp = new FTPClient();

			// 连接FTP服务器, 如果采用默认端口，可以使用ftp.connect(ip)的方式直接连接
			ftp.connect(ip, port);

			// 登录
			ftp.login(username, password);
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				return result;
			}

			// 更改目录
			ftp.changeWorkingDirectory(remotePath);

			// 设置文件类型（二进制）
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.setBufferSize(1024000);
			ftp.storeFile(remoteFileName, input);

			result = true;
			Long end = System.currentTimeMillis();
			LOG.info(LogUtil.getLogStr("上传游戏图片", "0", "上传时间:" + (end - start), "", ""));
		} catch (IOException e) {
			LOG.error("uploadFile failed : " + e.getMessage());
		} finally {
			IOUtils.closeQuietly(input);
			if (ftp != null) {
				try {
					ftp.logout();
				} catch (IOException e) {

				}
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {

					}
				}
			}
		}
		return result;
	}
}