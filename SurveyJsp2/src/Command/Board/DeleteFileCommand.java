package Command.Board;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class DeleteFileCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num")); 
		
		SurveyDao dao = SurveyDao.getInstance();
		SurveyBoardDto dto = dao.findArticle(num);
	
		String fileName = dto.getbFileSaveName();
		String uploadFile = request.getSession().getServletContext().getRealPath("/upload") + "/" + fileName;
		
		File file = new File(uploadFile);
		if(file.exists() && file.isFile()) {
			file.delete();
		}
		
		response.setContentType("text/html; charset = UTF-8");
        response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("<script>alert('삭제완료');</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
