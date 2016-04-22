package com.ktds.jgbaek.handler.socket.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.jgbaek.handler.socket.dao.PaintDAO;

public class PaintDAOImpl extends SqlSessionDaoSupport implements PaintDAO {

	   @Override
	   public String getAnswer() {
	      return getSqlSession().selectOne("PaintDAO.getAnswer");
	   }

	   @Override
	   public void insertAnswer(String answer) {
	      getSqlSession().insert("PaintDAO.insertAnswer");
	   }

	   @Override
	   public void deleteAnswer() {
	      getSqlSession().delete("PaintDAO.deleteAnswer");
	   }
}

