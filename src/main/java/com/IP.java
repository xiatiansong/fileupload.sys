package com;

import java.io.Serializable;

/**
 * 
 * 创建人：fantasy
 * 创建时间:2013/02/26 
 * 功能描述:IP区 ，这个类建得 com下是为了减少字符患，存缓存节约内存。
 *  版本：1.0
 */
public class IP implements Serializable {
	private Long id;
	private Long f;// IP段开始 原始字段from_number
	private Long t;// IP段结束 原始字段to_number
	private Long a_id;// 区域ID area_id

	public Long getT() {
		return t;
	}

	public void setT(Long t) {
		this.t = t;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getA_id() {
		return a_id;
	}

	public void setA_id(Long a_id) {
		this.a_id = a_id;
	}

	public Long getF() {
		return f;
	}

	public void setF(Long f) {
		this.f = f;
	}
}
