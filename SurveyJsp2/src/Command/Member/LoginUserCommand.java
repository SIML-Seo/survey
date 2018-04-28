package Command.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;
import DataObject.SurveyUserDto;

public class LoginUserCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		int check = -1;
		String result = "";
		
		SurveyDao dao = SurveyDao.getInstance();
		check = dao.loginCheck(id, password);
		SurveyUserDto dto = dao.findUserById(id);
		int point = dto.getPoint();
		 
		if(check == 1) result = "success";
		else if (check == 0) result = "fail";
		else if (check == 2) result = "NoneID";
		
		request.setAttribute("point", point);
		
		return "loginResult.jsp?id=" + id + "&result=" + result;
	}
}
