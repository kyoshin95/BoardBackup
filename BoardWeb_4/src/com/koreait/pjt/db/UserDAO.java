package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.pjt.vo.UserVO;

public class UserDAO 
{
	public static int insUser(UserVO param)
	{	
		String sql = " insert into t_user " + " (i_user, user_id, user_pw, nm, email) " + " values " + " (seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface()
		{
			public void update(PreparedStatement ps) throws SQLException
			{
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());
			}
		});
	}	
	
	public static int selUser(UserVO param)
	{
		String sql = " select i_user, user_id, user_pw, nm from t_user where user_id = ? ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface()
		{
			@Override
			public void prepared(PreparedStatement ps) throws SQLException 
			{
				ps.setNString(1, param.getUser_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException 
			{
				if(rs.next())
				{
					String dbPw = rs.getNString("user_pw");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
					
					if(dbPw.equals(param.getUser_pw()))
					{
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");
						
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);
						
						return 1;
					}
					else
					{
						return 2;
					}
				}
				else
				{
					return 3;
				}
			}
		});
	}
	
	public static int login(UserVO param) 
	{		
		String sql = " SELECT i_user, user_pw, nm "
				+ " FROM t_user "
				+ " WHERE user_id = ? ";
				
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() 
		{
			@Override
			public void prepared(PreparedStatement ps) throws SQLException 
			{
				ps.setNString(1, param.getUser_id());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException 
			{
				if(rs.next()) 
				{
					String dbPw = rs.getNString("user_pw");	
					
					if(dbPw.equals(param.getUser_pw())) //로그인 성공
					{ 
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");						
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);						
						return 1;
					} 
					else //비밀번호 틀림
					{ 
						return 2; 
					}
				} 
				else //아이디 없음
				{
					return 3;
				}
			}		
		});
	}
	
	public static UserVO selUser(int i_user)
	{
		String sql = " select user_id, nm, profile_img, email, r_dt "
				+ " from t_user where i_user = ? ";
		
		UserVO result = new UserVO();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException 
			{
				ps.setInt(1, i_user);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException 
			{
				if(rs.next())
				{
					result.setUser_id(rs.getNString("user_id"));
					result.setNm(rs.getNString("nm"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setEmail(rs.getNString("email"));
					result.setR_dt(rs.getNString("r_dt"));
				}
				return 1;
			}
		});
		return result;
	}
	
	public static int updUser(UserVO param)
	{
		StringBuilder sb = new StringBuilder(" update t_user set m_dt = sysdate "); // StringBuilder 퍼포먼스 때문에 사용 하지만 반복문(for문)에는 StringBuilder를 꼭 사용해야한다.
		
		if(param.getUser_pw() != null)
		{
			sb.append(" , user_pw = '");
			sb.append(param.getUser_pw());
			sb.append("' ");
		}
		if(param.getNm() != null)
		{
			sb.append(" , nm = '");
			sb.append(param.getNm());
			sb.append("' ");
		}
		if(param.getEmail() != null)
		{
			sb.append(" , email = '");
			sb.append(param.getEmail());
			sb.append("' ");
		}
		if(param.getProfile_img() != null)
		{
			sb.append(" , profile_img = '");
			sb.append(param.getProfile_img());
			sb.append("' ");
		}
		sb.append(" where i_user = ");
		sb.append(param.getI_user());
		
		return JdbcTemplate.executeUpdate(sb.toString(), new JdbcUpdateInterface() 
		{
			@Override
			public void update(PreparedStatement ps) throws SQLException {}
		});
	}
}
