package Command.Board;

import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class BoardListCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		int pageSize = 5;	
		int currentPage = Integer.parseInt(pageNum); 
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<SurveyBoardDto> articleList = null;
		boolean master = false;
		
		String id = (String)session.getAttribute("loginID");
		
		SurveyDao dao = SurveyDao.getInstance();
		
		master = dao.isMaster(id);
		
		count = dao.getArticleCount();
		if(count > 0) {
			articleList = dao.findAllArticle(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("master", master);
		
		return "board/list.jsp";
	}
	
}
