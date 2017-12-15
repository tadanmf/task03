package com.task03.page.vo;

public class PageVO {
	private int countList;		// 한 페이지에 출력될 게시물 수
	private int countPage;		// 한 화면에 출력될 페이지 수
	private int page;			// 현재 페이지 번호
	private int totalPage;		// 총 페이지 수
	private int startPage;		// 시작 페이지 번호
	private int endPage;		// 마지막 페이지 번호
	
	public PageVO() {
		countList = 10;
		countPage = 10;
		page = 1;
		totalPage = 1;
		startPage = 1;
	}
	
	public PageVO(int countList, int countPage, int page, int totalPage, int startPage, int endPage) {
		this.countList = countList;
		this.countPage = countPage;
		this.page = page;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getCountList() {
		return countList;
	}

	public void setCountList(int countList) {
		this.countList = countList;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageVO [countList=" + countList + ", countPage=" + countPage + ", page=" + page + ", totalPage="
				+ totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
