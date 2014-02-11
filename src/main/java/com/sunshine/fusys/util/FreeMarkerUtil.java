package com.sunshine.fusys.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sunshine.fusys.common.CommonConstant;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 创建人：User <br>
 * 创建时间：2013-8-8 <br>
 * 功能描述：生成静态文件 <br>
 */
public class FreeMarkerUtil {

	private static final Logger LOG = Logger.getLogger(FreeMarkerUtil.class);

	private String encoding = CommonConstant.UTF_8;

	public FreeMarkerUtil() {

	}

	public FreeMarkerUtil(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 生成静态文件
	 * 
	 * @param templatePath
	 * @param templateFileName
	 * @param templateParams
	 * @param staticFile
	 */
	public void createStaticFile(String templatePath, String templateFileName, Map<String, Object> templateParams,
			String staticFile) {
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			Template template = getTemplate(cfg, templateFileName);
			doCreateStaticFile(template, templateParams, staticFile);
		} catch (Exception e) {
			LOG.error("createStaticFile error : " + e.getMessage());
		}
	}

	/**
	 * 获取模板
	 * 
	 * @param cfg
	 * @param name
	 */
	private Template getTemplate(Configuration cfg, String name) throws IOException {
		cfg.setDefaultEncoding(encoding);
		cfg.setNumberFormat("#");
		return cfg.getTemplate(name);
	}

	/**
	 * 生成文件
	 * 
	 * @param template
	 * @param templateParams
	 * @param staticFileName
	 */
	private void doCreateStaticFile(Template template, Map<String, Object> templateParams, String staticFile) {
		BufferedWriter writer = null;
		try {
			File staticFilePath = new File(staticFile);
			if (!staticFilePath.exists()) {
				staticFilePath.createNewFile();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticFilePath), encoding));
			template.setEncoding(encoding);
			template.process(templateParams, writer);
		} catch (Exception e) {
			LOG.error("doCreateStaticFile error : " + e.getMessage());
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
}