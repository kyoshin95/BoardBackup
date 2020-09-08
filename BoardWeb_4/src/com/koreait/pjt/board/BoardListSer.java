package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.SecurityUtil;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;


@WebServlet("/board/list")

public class BoardListSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession hs = (HttpSession)request.getSession();
		
		if(SecurityUtil.isLogout(request))
		{
			response.sendRedirect("/login");
			return;
		}
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null ? "a" : searchType);
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "" : searchText);
		
		int page = SecurityUtil.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		int recordCnt = SecurityUtil.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
	
		BoardDomain param = new BoardDomain();
		param.setSearchType(searchType);
		param.setSearchText("%" + searchText + "%");
		param.setRecord_cnt(recordCnt);
		int pagingCnt = BoardDAO.selPagingCnt(param);

		if(page > pagingCnt) 
		{ 
			page = pagingCnt;
		}
		request.setAttribute("searchType", searchType);
		request.setAttribute("page", page);

		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;

		param.setsIdx(sIdx);
		param.seteIdx(eIdx);
		
		request.setAttribute("pagingCnt", pagingCnt);
		
		List<BoardDomain> list = BoardDAO.selBoardList(param);
		//하이라이트 처리
		if(!"".equals(searchText) && ("a".equals(searchType) || "c".equals(searchType))) 
		{
			for(BoardDomain item : list) 
			{
				String title = item.getTitle();
				title = title.replace(searchText, "<span class=\"highlight\">" + searchText +"</span>");
				item.setTitle(title);
			}
		}

		request.setAttribute("data", list);
		
		ViewResolver.forwardLoginChk("/board/list", request, response);
	}
}
