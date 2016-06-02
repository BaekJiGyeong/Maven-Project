package com.ktds.jgbaek.mongo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.mongo.dao.MemoDAO;
import com.ktds.jgbaek.mongo.vo.MemoListVO;

@Controller
public class MemoController {
	
	private MemoDAO memoDAO;
	
	public void setMemoDAO(MemoDAO memoDAO) {
		this.memoDAO = memoDAO;
	}

	@RequestMapping("/write")
	public ModelAndView viewWritePage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("write");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView viewListPage(@RequestParam(required=false, defaultValue="0") int pageNo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("list");
		MemoListVO memoListVO = memoDAO.getMemoList(pageNo);
		view.addObject("memoListVO", memoListVO);
		return view;
	}
	
	@RequestMapping("/detail/{id}")
	public ModelAndView viewDetailPage(@PathVariable String id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("detail");
		view.addObject("memo", memoDAO.getMemo(id));
		return view;
	}
	
	@RequestMapping("/doWrite")
	public String doWriteAction(@RequestParam String memo) {
		memoDAO.insertMemo(memo);
		return "redirect:/list";
	}
	
	@RequestMapping("/doUpdate")
	public String doUpdateAction(@RequestParam String id, @RequestParam String memo) {
		memoDAO.updateMemo(id, memo);
		return "redirect:/list";
	}
	
	@RequestMapping("/doDelete/{id}")
	public String doDeleteAction(@PathVariable String id) {
		memoDAO.removeMemo(id);
		return "redirect:/list";
	}
}
