package Command.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class ContentCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String id = (String)session.getAttribute("loginID");
		boolean master = false;
		
		SurveyDao dao = SurveyDao.getInstance();
		
		SurveyBoardDto dto = dao.readArticle(num);
		master = dao.isMaster(id);
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", dto);
		request.setAttribute("master", master);
		
		return "board/content.jsp";
	}
	
}
