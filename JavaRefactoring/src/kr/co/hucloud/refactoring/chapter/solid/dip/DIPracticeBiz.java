package kr.co.hucloud.refactoring.chapter.solid.dip;

public class DIPracticeBiz {

	private DIPracticeDAO diPracticeDAO;
	
	public DIPracticeBiz() {
		diPracticeDAO = new DIPracticeDAO();
	}
	
	public String getSomeString() {
		return diPracticeDAO.getSomeString();
	}
	
}
