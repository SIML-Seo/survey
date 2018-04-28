package Command.Survey;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyDao;

public class SurveyCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		SurveyDao dao = SurveyDao.getInstance();
		HashMap<String, String> surveyItemMap = dao.findSurveyItems(num);
		List<HashMap<String, String>> surveyArticle = dao.findSurveyArticle();
		String surveyTitle = dao.findSurveyTitle(num);
		
		Iterator<String> it = surveyItemMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = surveyItemMap.get(key);
			if(value == null) {
				it.remove();
			}
		}
		
		for(HashMap<String, String> map : surveyArticle) {
			Iterator<String> itt = map.keySet().iterator();
			while(itt.hasNext()) {
				String key = itt.next();
				String value = map.get(key);
				
				if(value == null) {
					itt.remove();
				}
			}
		}
		
		int surveyItemCount = surveyItemMap.keySet().size();
		
		request.setAttribute("surveyNum", num);
		request.setAttribute("surveyTitle", surveyTitle);
		request.setAttribute("surveyItems", surveyItemMap);
		request.setAttribute("surveyArticle", surveyArticle);
		request.setAttribute("surveyItemCount", surveyItemCount);
		
		return "survey.jsp";
	}
}
