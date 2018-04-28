package Command.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;

public class DeleteUserCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean check = false; 
		
		SurveyDao dao = SurveyDao.getInstance();
		check = dao.deleteUser(id, password);
		request.setAttribute("result", check);
		
		return "deleteResult.jsp";
	}
}
