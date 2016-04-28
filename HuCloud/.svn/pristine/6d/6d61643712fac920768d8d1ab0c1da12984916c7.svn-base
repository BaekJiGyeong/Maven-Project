package kr.co.hucloud.security.code.example.common.util;

public class Paging {

	private int totalArticleCount;

	private int printArticle;
	private int printPage;

	private int startArticleNumber;
	private int endArticleNumber;

	private int totalPage;
	private int totalGroup;

	private int nowGroupNumber;

	private int groupStartPage;

	private int nextGroupPageNumber;
	private int prevGroupPageNumber;

	private int pageNo;

	public Paging() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Paging(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		this.startArticleNumber = (this.pageNo * this.printArticle) + 1;
		this.endArticleNumber = this.startArticleNumber + this.printArticle - 1;

		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}

	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}

	public int getTotalCount() {
		return this.totalArticleCount;
	}

	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}

	public int getEndArticleNumber() {
		return this.endArticleNumber;
	}

	public String getPagingList(String link, String pageFormat, String prev,
			String next, String moreParams) {

		StringBuffer buffer = new StringBuffer();

		if (this.nowGroupNumber > 0) {
			buffer.append("<a href=\"?" + link + "=" + this.prevGroupPageNumber
					+ "&" + moreParams + "\">" + prev + "</a>");
		}

		int nextPrintPage = this.groupStartPage + this.printPage;
		if (nextPrintPage > this.totalPage) {
			nextPrintPage = this.totalPage + 1;
		}

		String pageNumber = "";

		for (int i = this.groupStartPage; i < nextPrintPage; i++) {
			pageNumber = pageFormat.replaceAll("@", i + "");
			if ((i - 1) == this.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			buffer.append("<a href=\"?" + link + "=" + (i - 1) + "&"
					+ moreParams + "\">" + pageNumber + "</a>");
		}

		if (this.nowGroupNumber < (this.totalGroup - 1)) {
			buffer.append("<a href=\"?" + link + "=" + this.nextGroupPageNumber
					+ "&" + moreParams + "\">" + next + "</a>");
		}

		return buffer.toString();
	}

}
