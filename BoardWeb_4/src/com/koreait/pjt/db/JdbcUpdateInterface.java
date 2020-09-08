package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcUpdateInterface // 부모역할만 하겠다.
{
	void update(PreparedStatement ps) throws SQLException;
}
