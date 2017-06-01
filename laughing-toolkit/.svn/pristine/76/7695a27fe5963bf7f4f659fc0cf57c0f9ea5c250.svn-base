package com.laughing2b.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 5385418073421356566L;
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public String id;
	public Date createDate;
	public Date updateDate;
	public String orderBy;
	public int limit;
	public String delFlag = "0";

	public void preInsert() {
		Date now = Calendar.getInstance().getTime();
		setCreateDate(now);
		setUpdateDate(now);
	}

	public void preUpadate() {
		Date now = Calendar.getInstance().getTime();
		setUpdateDate(now);
	}

	public BaseEntity() {
	}

	public BaseEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}