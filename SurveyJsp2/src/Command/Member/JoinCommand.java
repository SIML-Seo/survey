package Command.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;

public class JoinCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email1") + "@" +request.getParameter("email2");
		
		SurveyDao dao = SurveyDao.getInstance();
		dao.addUser(id, password, email);
		
		return "joinResult.jsp";
	}

}
