package Command.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class UpdatePreCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		SurveyDao dao = SurveyDao.getInstance();
		SurveyBoardDto dto = dao.findArticle(num);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", dto);
		
		return "board/updateForm.jsp";
	}

}