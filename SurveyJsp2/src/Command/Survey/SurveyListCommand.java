package Command.Survey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;
import DataObject.SurveyTitleDto;

public class SurveyListCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		SurveyDao dao = SurveyDao.getInstance();
		List<SurveyTitleDto> surveyList = dao.findAllSurvey();
		List<String[]> finishUser = dao.findFinishSurveyAllUser();
		
		request.setAttribute("surveyList", surveyList);
		request.setAttribute("finishUser", finishUser);
		return "surveyList.jsp";
	}
}
