package kr.co.hucloud.security.code.example.valid.table.service.impl;

import kr.co.hucloud.security.code.example.valid.table.dao.TableValidDAO;
import kr.co.hucloud.security.code.example.valid.table.service.TableValidService;

public class TableValidServiceImpl implements TableValidService {

	private TableValidDAO tableValidDAO;

	public void setTableValidDAO(TableValidDAO tableValidDAO) {
		this.tableValidDAO = tableValidDAO;
	}
	
	@Override
	public boolean isInitiatedTable() {
		return tableValidDAO.isInitiatedTable();
	}
	
	@Override
	public void initiateTable() {
		tableValidDAO.initiateTable();
	}
	
	@Override
	public void dropAllTable() {
		tableValidDAO.dropAllTable();
	}
}
