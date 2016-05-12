package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

public interface EducationBiz {
	
	boolean validCategoryId(CategoryVO categoryVO);

	public boolean modifyEduCost(CostVO cost);
	
	public CostVO getEduCostByCdId(String coId);

	public List<CostVO> getAllEduCost();

	public boolean deleteEduCost(String cdId);

	public boolean insertEduCost(CostVO cost);

	public boolean addNewCategory(CategoryVO categoryVO);

	public List<CategoryVO> getAllLargeCategory();

	public boolean isExistCost(CostVO cost);

	public boolean isExistCostNm(CostVO cost);

	public List<TimeVO> getAllEduTime();

	public boolean deleteEduTime(String cdId);

	public boolean isExistTimeNm(TimeVO time);

	public boolean modifyEduTime(TimeVO time);
	
	boolean validCategoryName(CategoryVO categoryVO);

	public boolean isExistTime(TimeVO time);

	public boolean insertEduTime(TimeVO time);

	List<CategoryVO> getChildCategory(CategoryVO categoryVO);

	boolean deleteCategory(CategoryVO categoryVO);

	boolean modifyCategory(CategoryVO categoryVO);
	
	boolean deleteLargeCategory(CategoryVO categoryVO);
	
	boolean deleteMediumCategory(CategoryVO categoryVO);
	
	boolean deleteSmallCategory(CategoryVO categoryVO);

}
