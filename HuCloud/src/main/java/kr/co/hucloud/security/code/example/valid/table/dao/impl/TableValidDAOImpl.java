package kr.co.hucloud.security.code.example.valid.table.dao.impl;

import kr.co.hucloud.security.code.example.valid.table.dao.TableValidDAO;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TableValidDAOImpl extends SqlSessionDaoSupport implements TableValidDAO {

	@Override
	public boolean isInitiatedTable() {
		int count = getSqlSession().selectOne("TableInvalidDAO.isInitiatedTable");
		return count > 0;
	}
	
	@Override
	public void dropAllTable() {
		getSqlSession().update("TableInvalidDAO.dropUsersTable");
		getSqlSession().update("TableInvalidDAO.dropBoardTable");
		getSqlSession().update("TableInvalidDAO.dropReplyTable");
		getSqlSession().update("TableInvalidDAO.dropBoardSeq");
		getSqlSession().update("TableInvalidDAO.dropReplySeq");
	}
	
	@Override
	public void initiateTable() {
		
		getSqlSession().update("TableInvalidDAO.createUserTable");
		getSqlSession().update("TableInvalidDAO.createUserTableIndex");
		getSqlSession().update("TableInvalidDAO.createUserTablePrimaryKey");
		
		getSqlSession().update("TableInvalidDAO.createBoardSeq");
		getSqlSession().update("TableInvalidDAO.createReplySeq");
		
		getSqlSession().update("TableInvalidDAO.createBoardTable");
		getSqlSession().update("TableInvalidDAO.createBoardTableIndex");
		getSqlSession().update("TableInvalidDAO.createBoardTablePrimaryKey");
		
		getSqlSession().update("TableInvalidDAO.createReplyTable");
		getSqlSession().update("TableInvalidDAO.createReplyTableIndex");
		getSqlSession().update("TableInvalidDAO.createReplyTablePrimaryKey");
		
		getSqlSession().insert("TableInvalidDAO.initiateUser");
		
		getSqlSession().insert("TableInvalidDAO.initiateArticle", "<script>alert(\"hello\");</script>");
		getSqlSession().insert("TableInvalidDAO.initiateArticle", "<script>alert(\"xss\");</script>");
				
	}
	
	@Override
	public void addSaltColumn() {
		getSqlSession().update("TableInvalidDAO.addSaltColumn");
	}
	
}
