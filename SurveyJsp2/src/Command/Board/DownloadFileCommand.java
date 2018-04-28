package Command.Board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import DataObject.SurveyBoardDto;
import DataObject.SurveyDao;

public class DownloadFileCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		SurveyDao dao = SurveyDao.getInstance();
		SurveyBoardDto dto = dao.findArticle(num);
		
		String fileName = dto.getbFileSaveName();
		String uploadFile = request.getSession().getServletContext().getRealPath("/upload") + "/" + fileName;
		
		File downFile = new File(uploadFile);
		
		if(downFile.exists() && downFile.isFile()) {
			try {
				long fileSize = downFile.length();
				
				response.setContentType("application/x-msdownload");
				response.setContentLength((int)fileSize);
				response.setCharacterEncoding("UTF-8");
				String strClient = request.getHeader("user-agent");
				
				String downName = "";
				if(strClient.indexOf("MSIE 5.5") != -1) {
					downName = new String(fileName.getBytes("EUC-KR"), "8859_1");
					response.setHeader("Content-Disposition", "filename=" + downName + ";");
				}else {
					downName = new String(fileName.getBytes("UTF-8"), "8859_1");
					response.setHeader("Content-Disposition", "attachment; filename=" + downName + ";");
				}
				
				response.setHeader("Content-Length", String.valueOf(fileSize));
				response.setHeader("Content-Transfer-Encoding", "binary;");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "private");
				
				byte[] b = new byte[1024];
				
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				
				int read = 0;
				while((read = bis.read(b)) != -1) {
					bos.write(b, 0, read);
				}
				bos.flush();
				bos.close();
				bis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			response.setContentType("text/html; charset = UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        try {
				response.getWriter().write("<script>alert('실패');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return null;
		}
		return null;
	}
}
