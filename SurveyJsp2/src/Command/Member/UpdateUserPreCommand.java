package Command.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.Command;
import DataObject.SurveyDao;
import DataObject.SurveyUserDto;

public class UpdateUserPreCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginID");
		
		SurveyDao dao = SurveyDao.getInstance();
		SurveyUserDto dto = null;
		dto = dao.findUserById(id);
		request.setAttribute("User", dto);
		
		return "updateUser.jsp";
	}
}
