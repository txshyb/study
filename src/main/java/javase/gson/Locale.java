/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package javase.gson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import entity.Person;


/**
 * 场所基础信息Entity
 * @author ycb
 * @version 2018-04-13
 */
public class Locale {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("SERVICE_CODE")
	private String netbarWacode;		// 上网服务场所编码
	@JsonProperty("SERVICE_NAME")
	private String placeName;		// 上网服务场所名称
	@JsonProperty("ADDRESS")
	private String siteAddress;		// 场所详细地址
	private Integer longitude;		// 场所经度
	private String latitude;		// 场所纬度
	private Person person;		// 安全厂商组织机构代码外键
	private Object o;

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNetbarWacode() {
		return netbarWacode;
	}

	public void setNetbarWacode(String netbarWacode) {
		this.netbarWacode = netbarWacode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Locale{" +
				"netbarWacode='" + netbarWacode + '\'' +
				", placeName='" + placeName + '\'' +
				", siteAddress='" + siteAddress + '\'' +
				", longitude=" + longitude +
				", latitude='" + latitude + '\'' +
				", person=" + person +
				", o=" + o +
				'}';
	}
}