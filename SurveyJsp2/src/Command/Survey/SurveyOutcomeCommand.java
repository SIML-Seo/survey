package Command.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;

public class SurveyOutcomeCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		int articleAnswerNum = 3;		//그래프로 변환하고 싶은 컬럼(응답문항)의 번호
		String surveyTableName = "surveyanswer";
		
		SurveyDao dao = SurveyDao.getInstance();
		List<HashMap<String, Integer>> surveyAnswer = dao.surveyAnswerCountingTotalByNum(surveyTableName, articleAnswerNum);
		String surveyTitle = dao.findSurveyTitle(num);
		
		HashMap<String, Integer> surveyCount = dao.surveyAnswerTotal(surveyTableName);
		List<ArrayList<String>> surveyAnswerCount = dao.surveyAnswerCountingByNum(surveyTableName, articleAnswerNum);
		
		request.setAttribute("surveyAnswer", surveyAnswer);
		request.setAttribute("surveyTitle", surveyTitle);
		request.setAttribute("surveyCount", surveyCount);
		request.setAttribute("surveyAnswerCount", surveyAnswerCount);
		
		return "surveyOutcome.jsp";
	}
}
