package Command.Survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.Command;
import DataObject.SurveyDao;

public class SurveyResultCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginID");
		int point = 1000;
		String surveyTableName = "surveyanswer";
		int surveyNum = Integer.parseInt(request.getParameter("surveyNum"));
		int surveyItemCount = Integer.parseInt(request.getParameter("surveyItemCount"));
		ArrayList<Object> answerList = new ArrayList<Object>();
		
		for(int i = 1; i < surveyItemCount+1; i++) {
			answerList.add(request.getParameter("Answer" + Integer.toString(i)));
		}
		
		SurveyDao dao = SurveyDao.getInstance();
		dao.insertAnswer(surveyTableName, id, surveyNum, answerList);
		dao.insertPoint(id, point);
		dao.finishSurvey(id, surveyNum);
		
		int repoint = dao.findUserById(id).getPoint();
		
		request.setAttribute("point", repoint);
		
		return "surveyResult.jsp";
	}
}
