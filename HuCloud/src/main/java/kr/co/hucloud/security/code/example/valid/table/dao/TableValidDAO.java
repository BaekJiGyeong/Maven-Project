package kr.co.hucloud.security.code.example.valid.table.dao;

public interface TableValidDAO {

	public boolean isInitiatedTable();

	public void initiateTable();

	public void dropAllTable();

	public void addSaltColumn();

}
