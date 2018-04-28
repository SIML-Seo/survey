package DB;

import java.util.HashMap;
import java.util.Map;

import Command.Command;
import Command.Board.BoardListCommand;
import Command.Board.ContentCommand;
import Command.Board.DeleteArticleCommand;
import Command.Board.DeleteFileCommand;
import Command.Board.DeletePreCommand;
import Command.Member.DeleteUserCommand;
import Command.Board.DownloadFileCommand;
import Command.Member.JoinCommand;
import Command.Member.LoginUserCommand;
import Command.Survey.SurveyCommand;
import Command.Survey.SurveyListCommand;
import Command.Survey.SurveyOutcomeCommand;
import Command.Survey.SurveyResultCommand;
import Command.Board.UpdateArticleCommand;
import Command.Board.UpdatePreCommand;
import Command.Member.UpdateUserCommand;
import Command.Member.UpdateUserPreCommand;
import Command.Board.WriteCommand;
import Command.Board.WritePreCommand;

public class RequestMapping {
	private Map<String, Command> mappings = new HashMap<>();
	
	public void initMapping() {
		mappings.put("join.do", new JoinCommand());
		mappings.put("updateUserPre.do", new UpdateUserPreCommand());
		mappings.put("updateUser.do", new UpdateUserCommand());
		mappings.put("deleteUser.do", new DeleteUserCommand());
		mappings.put("login.do", new LoginUserCommand());
		
		mappings.put("list.do", new BoardListCommand());
		mappings.put("content.do", new ContentCommand());
		mappings.put("writePre.do", new WritePreCommand());
		mappings.put("write.do", new WriteCommand());
		mappings.put("updatePre.do", new UpdatePreCommand());
		mappings.put("updateArticle.do", new UpdateArticleCommand());
		mappings.put("deletePre.do", new DeletePreCommand());
		mappings.put("deleteArticle.do", new DeleteArticleCommand());
		mappings.put("downFile.do", new DownloadFileCommand());
		mappings.put("deleteFile.do", new DeleteFileCommand());
		
		mappings.put("surveyList.do", new SurveyListCommand());
		mappings.put("survey.do", new SurveyCommand());
		mappings.put("surveyResult.do", new SurveyResultCommand());
		mappings.put("surveyOutcome.do", new SurveyOutcomeCommand());
	}
	
	public Command findCommand(String url) {
		return mappings.get(url);
	}
}
