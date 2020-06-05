/**
 * 
 */
package com.gks.itcast;

import java.util.List;

/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��3��28��
 */
public class PageBean {
	
	
	private Integer currentPage; //��ǰҳ
	
	private Integer pageSize=2; //��ҳ��ʾ��¼��
	
	private Integer totalPage ; //��ҳ��
	
	private Integer totalCount; //�ܼ�¼��
	
	private List items; //��ҳ��ʾ��¼

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public PageBean(Integer currentPage ,Integer totalCount) {
		this.totalPage= totalCount%this.pageSize == 0 ? (totalCount/this.pageSize ) :(totalCount/this.pageSize +1);
		this.totalCount=totalCount;
		if(currentPage<1) {
			this.currentPage=1;
		
		}
		if(currentPage>this.totalPage) {
			this.currentPage=this.totalPage;
			
		}
		this.currentPage=currentPage;
		
		
		
		
	 
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", items=" + items + "]";
	}
	
	
	

}
