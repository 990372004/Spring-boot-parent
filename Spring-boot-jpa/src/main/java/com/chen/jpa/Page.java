package com.chen.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chen.jpa.enums.SequencerDirectionEnum;
import com.chen.jpa.filter.Filter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  分页
 * @author chen
 * @date 2019-01-03 03:10:07
 * @param <T>
 */
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
public class Page<T> implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 5384212692354556792L;

	/** 分页信息 */
	private Pageable pageable;

	/** 内容 */
	private List<T> cont;

	/** 总记录数 */
	private Long total;

	/** 总页数 */
	private Integer totalPages;

	public Page() {
		this.pageable = new Pageable();
		this.cont = new ArrayList<T>();
		this.total = 0L;
		this.totalPages = 0;
	}

	/**
	 * @param pageable
	 *            分页信息
	 * @param cont
	 *            内容
	 * @param total
	 *            总记录数
	 */
	public Page(Pageable pageable, List<T> cont, Long total) {
		this.pageable = pageable;
		this.cont = cont;
		this.total = total;
		this.totalPages = (int) Math.ceil((double) total / (double) getPageSize());
		if (getTotalPages() < getPageable().getPageNumber()) {
			getPageable().setPageNumber(getTotalPages());
		}
	}

	public Pageable getPageable() {
		return pageable;
	}

	public Integer getPageNumber() {
		return getPageable().getPageNumber();
	}

	@JsonProperty
	public Integer getPageSize() {
		return getPageable().getPageSize();
	}

	public String getSearchProperty() {
		return getPageable().getSearchProperty();
	}

	public String getSearchValue() {
		return getPageable().getSearchValue();
	}

	public List<Filter> getFilters() {
		return getPageable().getFilters();
	}

	public String getSortProperty() {
		return getPageable().getSortProperty();
	}

	public SequencerDirectionEnum getSortDirection() {
		return getPageable().getSortDirection();
	}

	public List<Sequencer> getSequencers() {
		return getPageable().getSequencers();
	}

	@JsonProperty
	public List<T> getCont() {
		return cont;
	}

	@JsonProperty
	public Long getTotal() {
		return total;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

}