package com.ego.model.common.mo;


import com.ego.model.common.constant.CommonConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public  class PageableMO implements Pageable {
	private int pageNumber = CommonConstants.DEFAULT_PAGE_NUMBER;
	private int pageSize = CommonConstants.DEFAULT_PAGE_SIZE;
	private String direction;
	private String property;
	private int totalPages;
	private long total;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public int getPageNumber() {
		return this.pageNumber;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getOffset() {
		return this.pageNumber * this.pageSize;
	}

	@Override
	public Sort getSort() {
		if (StringUtils.hasText(direction) && StringUtils.hasText(property)) {
			return new Sort(Sort.Direction.fromString(this.direction), property);
		}
		return null;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	public boolean hasPrevious() {
		return (this.pageNumber > 0);
	}

	public Pageable next() {
		return new PageRequest(this.pageNumber + 1, this.pageSize, getSort());
	}

	public Pageable previousOrFirst() {
		return ((hasPrevious()) ? new PageRequest(this.pageNumber - 1, this.pageSize, getSort()) : this);
	}

	public Pageable first() {
		return new PageRequest(0, this.pageSize, getSort());
	}
}
