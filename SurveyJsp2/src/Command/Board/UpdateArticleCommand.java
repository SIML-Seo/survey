package Command.Board;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class UpdateArticleCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SurveyDao dao = SurveyDao.getInstance();
		
		String pageNum = request.getParameter("pageNum");
		
		MultipartRequest multi = dao.fileUpload(request);
		
		SurveyBoardDto dto = new SurveyBoardDto();
		dto.setbNum(Integer.parseInt(multi.getParameter("num")));
		dto.setbName(multi.getParameter("name"));
		dto.setbTitle(multi.getParameter("title"));
		dto.setbContent(multi.getParameter("content"));
		dto.setbRegDate(new Timestamp(System.currentTimeMillis()));
		
		File file = multi.getFile("uploadfile");
		if(file == null) {
			dto.setbFileOriName("");
			dto.setbFileSaveName("");
			dto.setbFileFullPath("");
		}else {
			String fileOriName = multi.getOriginalFileName("uploadfile");
			dto.setbFileOriName(fileOriName);
			dto.setbFileSaveName(file.getName());
			dto.setbFileFullPath(file.getAbsolutePath());
		}
		
		dao.updateArticle(dto);
		
		return "list.do?pageNum="+pageNum;
		
	}
}
