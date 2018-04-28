package Command.Board;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class DeleteArticleCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String check = request.getParameter("del");
		
		if(check.equals("yes")) {
			SurveyDao dao = SurveyDao.getInstance();
			
			SurveyBoardDto dto = dao.findArticle(num);
			
			String fileName = dto.getbFileSaveName();
			String uploadFile = request.getSession().getServletContext().getRealPath("/upload") + "/" + fileName;
			
			File file = new File(uploadFile);
			if(file.exists() && file.isFile()) {
				file.delete();
			}
			
			dao.deleteArticle(num);
		}
		
		return "list.do?pageNum="+pageNum;
	}

}
