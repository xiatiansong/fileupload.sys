package com.sunshine.fusys.models;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * 创建人：fantasy 
 * 创建时间:2013/02/26 
 * 功能描述:IP区域字典Model
 */
@Alias("IpAreaDictionary")
public class IpAreaDictionary extends BaseDTO {

	private static final long serialVersionUID = -278697188207820594L;

	private Long id;
	private String from;
	private String to;
	private String country;
	private String area;
	private Long area_id;
	private Date created_at;
	private Date updated_at;
	private Long from_number;
	private Long to_number;

	public Long getArea_id() {
		return area_id;
	}

	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}

	public Long getFrom_number() {
		return from_number;
	}

	public void setFrom_number(Long from_number) {
		this.from_number = from_number;
	}

	public Long getTo_number() {
		return to_number;
	}

	public void setTo_number(Long to_number) {
		this.to_number = to_number;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}