package com.koreait.board.common;

public class Utils 
{
	public static int parseStringToInt(String strI_board, int i) // 정수 판별식과 정수 변경 
	{
		try
		{
			int i_board = Integer.parseInt(strI_board); // 정수로 바꿔서 에러가 안뜨면
			return i_board; // 정수로 리턴
		}
		catch(Exception e)
		{
			e.printStackTrace(); // 오류가 먼지 보고 싶을때.
			return i;
		}
		
	}

	public static int parseStringToInt(String str) // 정수 변경
	{
		//int i_board = Integer.parseInt(str); // 정수로 변경
		return parseStringToInt(str,0);//i_board; // 정수로 리턴
	}
}
