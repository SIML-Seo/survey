package Command.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;

public class WritePreCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int num = 0;
		try {
			if (request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("num", new Integer(num));
		return "board/writeForm.jsp";
	}

}
