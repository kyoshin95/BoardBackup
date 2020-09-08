package com.koreait.board.db;

import java.sql.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board.vo.BoardVO;

public class BoardDAO 
{
	public static List<BoardVO> selBoardList()
	{
		List<BoardVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_board ORDER BY i_board DESC "; // detail창까지 해주는 것 아님 리스트만.
		
		try
		{
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				int i_student = rs.getInt("i_student");
				
				BoardVO vo = new BoardVO();
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setI_student(i_student);
				
				list.add(vo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbCon.close(con, ps, rs);
		}
		return list;
	}
	
	public static BoardVO selBoard(BoardVO param) // 수정이 덜 할 수 있는 구조
	{
		BoardVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_board WHERE i_board = ? "; // detail창까지 해주는 것 아님 리스트만.
		
		try
		{
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String cont = rs.getNString("cont");
				int i_student = rs.getInt("i_student");
				
				vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setCont(cont);
				vo.setI_student(i_student);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbCon.close(con, ps, rs);
		}
		return vo;
	}
	
	public static int insBoard(BoardVO param) // 수정이 덜 할 수 있는 구조
	{
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_board(i_board, title, cont, i_student) " + " values(seq_board.nextval, ?, ?, ?) ";  // detail창까지 해주는 것 아님 리스트만.
	
		try 
		{
			// 순서 2
			con = DbCon.getCon();
			ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
			
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCont());
			ps.setInt(3, param.getI_student());
			
			result = ps.executeUpdate(); // !! Update문의 리턴반환형이 int문이라서 int문으로 rs를 선언
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DbCon.close(con, ps);
		}
		
		return result;
	}
	
	public static int delBoard(BoardVO param)
	{
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_board WHERE i_board = ? ";  // detail창까지 해주는 것 아님 리스트만.
	
		try 
		{
			// 순서 2
			con = DbCon.getCon();
			ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
			
			ps.setInt(1, param.getI_board());
			
			result = ps.executeUpdate(); // !! Update문의 리턴반환형이 int문이라서 int문으로 rs를 선언
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DbCon.close(con, ps);
		}
		
		return result;
	}
	
	public static int modBoard(BoardVO param)
	{
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " Update t_board set title=?, cont=? where i_board=? ";
	
		try 
		{
			// 순서 2
			con = DbCon.getCon();
			ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사

			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCont());
			ps.setInt(3,  param.getI_board());
			
			result = ps.executeUpdate(); // !! Update문의 리턴반환형이 int문이라서 int문으로 rs를 선언
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			DbCon.close(con, ps);
		}
		
		return result;
	}
}

