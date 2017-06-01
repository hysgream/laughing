package com.laughing2b.common;

import org.apache.commons.lang3.StringUtils;

public class PageParam {
	private String pageNum = "1";
	private Integer pageSize = Integer.valueOf(10);
	private String orderBy;
	private String keyword;
	private String keywordType;

	public Integer getPageNum() {
		if (StringUtils.isNumeric(this.pageNum)) {
			if (this.pageNum.indexOf(".") > 0) {
				return Integer.valueOf(1);
			}
			return Integer.valueOf(Math.abs(Integer.valueOf(this.pageNum).intValue()));
		}
		return Integer.valueOf(1);
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeywordType() {
		return this.keywordType;
	}

	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}

	public String toString() {
		return "PageParam [pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", orderBy=" + this.orderBy + "]";
	}
}