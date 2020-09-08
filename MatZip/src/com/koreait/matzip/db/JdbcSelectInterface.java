package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcSelectInterface // 인터페이스 : 부모역할
{
	void prepared(PreparedStatement ps) throws SQLException;
	void executeQuery(ResultSet rs) throws SQLException;

}
