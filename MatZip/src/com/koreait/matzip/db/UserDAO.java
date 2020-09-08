package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.matzip.vo.UserVO;

public class UserDAO 
{
	public int Join(UserVO param)
	{
		
		String sql = " insert into t_user(user_id, user_pw, nm, salt) values(?, ?, ?, ?) ";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface()
		{
			public void update(PreparedStatement ps) throws SQLException
			{
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getSalt());
			}
		});
	}
	
	public UserVO selUser(UserVO param)
	{
		UserVO result = new UserVO();
		
		String sql = " select i_user, user_id, user_pw, nm, profile_img, r_dt, m_dt "
				+ " from t_user where ";
		
		if(param.getI_user() > 0) 
		{
			sql += " i_user = " + param.getI_user();
		}
		
		if(param.getUser_id() != null && !"".contentEquals(param.getUser_id()))
		{
			sql += " user_id = '" + param.getUser_id() + "' "; 
		}
			
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface()
		{

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException 
			{
				if(rs.next())
				{
					result.setI_user(rs.getInt("i_user"));
					result.setUser_id(rs.getNString("user_id"));
					result.setUser_pw(rs.getNString("user_pw"));
					result.setSalt(rs.getNString("salt"));
					result.setNm(rs.getNString("nm"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setR_dt(rs.getNString("r_dt"));
					result.setM_dt(rs.getNString("m_dt"));
				} 
			}
		});
		
		return result;
	}
}
