package DataObject;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DB.ConnUtil;
import DB.JdbcTemplate;
import DB.PreparedStatementSetter;
import DB.RowMapper;

public class SurveyDao {
	private SurveyDao(){}                             
    private static class SurveyDao2{                 
         private static final SurveyDao instance = new SurveyDao();
    }
    public static SurveyDao getInstance(){
         return SurveyDao2.instance;
    }
	
    /*회원가입 시 ID 중복확인을 위한 메소드*/
	public boolean idCheck(String id){
    	boolean result = false;
    	RowMapper<SurveyUserDto> rm = rs ->
				new SurveyUserDto(rs.getString("id"));
    	
    	JdbcTemplate template = new JdbcTemplate();	
    	String sql = "select id from surveymember where id = ?";
    	List<SurveyUserDto> list = template.executeQuery(sql, rm, id);
    	
    	if(list.isEmpty()) result = true;
    	
    	return result;
    }
	
	/*설문조사 완료 후 포인트 적립을 위한 메소드*/
	public void insertPoint(String id, int point) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "update surveymember set point = point + ? where id = ?";
		template.executeUpdate(sql, point, id);
	}
	
	/*게시판 글쓰기 등의 권리를 가진 마스터계정인지를 확인하기 위한 메소드*/
	public boolean isMaster(String id){
		boolean result = false;
		if(id != null) {
			SurveyUserDto dto = findUserById(id);
			if(dto.getMaster() == 1) {
				result = true;
			}
		}
		return result;
	}

	/*회원가입을 위한 메소드*/
	public void addUser(String id, String password, String email){
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into surveymember (id, password, email) values(?, ?, ?)";
		template.executeUpdate(sql, id, password, email);
	}
	
	/*회원정보 수정을 위한 메소드*/
	public void updateUser(String id, String password, String email){
		JdbcTemplate template = new JdbcTemplate();
		String sql = "update surveymember set password = ?, email = ? where id = ?";
		template.executeUpdate(sql, password, email, id);
	}
	
	/*회원탈퇴를 위한 메소드*/
	public boolean deleteUser(String id, String password){
		boolean check = false;
		SurveyUserDto dto = findUserById(id);
		
		if(dto.getPassword().equals(password)) {
			JdbcTemplate template = new JdbcTemplate();
			
			String sql = "delete from surveymember where id = ?";
			template.executeUpdate(sql, id);
			check = true;
		}
		return check;
	}
	
	/*정상적인 로그인인지 확인하기 위한 메소드*/
	public int loginCheck(String id, String password){
		int check = -1;
		SurveyUserDto dto = findUserById(id);
		
		if(dto == null) {
			check = 2;
		}else if (dto.getPassword().equals(password)) {
			check = 1;
		}else {
			check = 0;
		}
		return check;
	}
	
	/*모든 회원정보를 불러오기 위한 메소드*/
	public List<SurveyUserDto> findAllUser(){
		RowMapper<SurveyUserDto> rm = rs ->
			new SurveyUserDto(rs.getString("id"), rs.getString("password"), rs.getString("email"), rs.getInt("master"), rs.getInt("point"));
			
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from surveymember";
		return template.executeQuery(sql, rm);
	}
	
	/*ID로 특정 회원정보를 불러오기 위한 메소드*/
	public SurveyUserDto findUserById(String id){
		RowMapper<SurveyUserDto> rm = rs ->
				new SurveyUserDto(rs.getString("id"), rs.getString("password"), rs.getString("email"), rs.getInt("master"), rs.getInt("point"));
			
		JdbcTemplate template = new JdbcTemplate();
		
		String sql = "select * from surveymember where id = ?";
		return template.queryForObject(sql, rm, id);
	}
	

	/* =========================  Board 관련 메소드  ========================== */
	  
	/* 게시판 리스트에서 항목번호를 생성하기 위한 메소드*/
	public int getArticleCount() {
		int count = 0;
		RowMapper<Integer> rm = new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs) throws SQLException {
				int count = rs.getInt(1);
				return count;
			}
		};
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select count(*) from surveyboard";
		count = template.executeQuery(sql, rm).get(0);
		return count;
	}
	
	/*게시물 목차를 불러오기 위한 메소드*/
	public List<SurveyBoardDto> findAllArticle(int start, int end){
		RowMapper<SurveyBoardDto> rm = rs ->
				new SurveyBoardDto(rs.getInt("bNum"), rs.getString("bName"), rs.getString("bTitle"), rs.getString("bContent"), 
						rs.getInt("bHit"), rs.getTimestamp("bRegDate"));
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from (select @rownum := @rownum+1 as rNum, bNum, bName, bTitle, bContent, bHit, bRegDate "
				+ "from (select * from surveyboard order by bNum desc) as D, (select @rownum := 0) as R) as A where rNum >= ? and rNum <= ?";
		return template.executeQuery(sql, rm, start, end);
	}
	
	/*메인페이지에 올라갈 공지사항 퀵뷰를 위한 메소드*/
	public List<SurveyBoardDto> findArticleRecently(){
		RowMapper<SurveyBoardDto> rm = rs ->
		new SurveyBoardDto(rs.getInt("bNum"), rs.getString("bTitle"));
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select bNum, bTitle from surveyboard order by bNum desc";
		return template.executeQuery(sql, rm);
	}
	
	/*게시물을 읽기 위한 메소드*/
	public SurveyBoardDto readArticle(int num) {
		String sql = "update surveyboard set bHit=bHit+1 where bNum = ?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, num);
		
		return findArticle(num);
	}
	
	/*게시물을 쓰기 위한 메소드*/
	public void insertArticle(SurveyBoardDto article) {
		PreparedStatementSetter pss = pstmt -> {
				pstmt.setString(1, article.getbName());
				pstmt.setString(2, article.getbTitle());
				pstmt.setString(3, article.getbContent());
				pstmt.setTimestamp(4, article.getbRegDate());
				pstmt.setString(5, article.getbFileOriName());
				pstmt.setString(6, article.getbFileSaveName());
				pstmt.setString(7, article.getbFileFullPath());
		};
			
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into surveyboard (bName, bTitle, bContent, bRegDate, bFileOriName, bFileSaveName, bFileFullPath) values(?, ?, ?, ?, ?, ?, ?)";
		template.executeUpdate(sql, pss);
	}
	
	/*특정 게시물을 불러오기 위한 메소드*/
	public SurveyBoardDto findArticle(int num) {
		RowMapper<SurveyBoardDto> rm = rs ->
			new SurveyBoardDto(rs.getInt("bNum"), rs.getString("bName"), rs.getString("bTitle"),
				rs.getString("bContent"), rs.getInt("bHit"), rs.getTimestamp("bRegDate"), 
				rs.getString("bFileOriName"), rs.getString("bFileSaveName"), rs.getString("bFileFullPath"));
			
		String sql = "select * from surveyboard where bNum = ?";
		JdbcTemplate template = new JdbcTemplate();
		SurveyBoardDto dto = template.queryForObject(sql, rm, num);
				
		return dto;
	}
	
	/*게시물을 수정하기 위한 메소드*/
	public void updateArticle(SurveyBoardDto article) {
		PreparedStatementSetter pss = pstmt ->{
			pstmt.setString(1, article.getbName());
			pstmt.setString(2, article.getbTitle());
			pstmt.setString(3, article.getbContent());
			pstmt.setString(4, article.getbFileOriName());
			pstmt.setString(5, article.getbFileSaveName());
			pstmt.setString(6, article.getbFileFullPath());
			pstmt.setInt(7, article.getbNum());
		};

		String sql = "update surveyboard set bName=?, bTitle=?, bContent=?, bFileOriName=?, bFileSaveName=?, bFileFullPath=? where bNum=?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, pss);
	}
	
	/*게시물을 삭제하기 위한 메소드*/
	public void deleteArticle(int num) {
		String sql = "delete from surveyboard where bNum = ?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, num);
	}
	
	/*파일을 업로드하기 위한 메소드
	 * 원래 파일업로드는 DB 보단 서버 쪽에 있어야할 기능이지만, 쓰기, 수정 때 중복적으로 multi객체가 이용되므로 Dao에 입력 */
	public MultipartRequest fileUpload(HttpServletRequest request) {
		MultipartRequest multi = null;
		
		String savePath = "/upload";
		String path = request.getSession().getServletContext().getRealPath(savePath);
		int size = 1024 * 1024 * 30;
		
		try {
			multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return multi;
	}
	
	/* =========================  Survey 관련 메소드  ========================== */
	
	/*설문조사 목차를 불러오기 위한 메소드*/
	public List<SurveyTitleDto> findAllSurvey() {
		RowMapper<SurveyTitleDto> rm = rs ->
			new SurveyTitleDto(rs.getInt("num"), rs.getDate("surveyDate"), rs.getString("surveyTitle"), rs.getString("finishMember"));
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from surveytitle";
		
		return template.executeQuery(sql, rm);
	}
	
	/*특정 설문조사의 제목을 구하기 위한 메소드*/
	public String findSurveyTitle(int num) {
		RowMapper<String> rm = rs -> new String(rs.getString(1));
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select surveyTitle from surveytitle where num = ?";
		
		return template.queryForObject(sql, rm, num);
	}
	
	/*특정 설문조사의 조사내용을 구하기 위한 메소드*/
	public HashMap<String,String> findSurveyItems(int num){
		RowMapper<HashMap<String, String>> rm = surveyConvertMap();
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from surveyitem where num = ?";
		
		return template.queryForObject(sql, rm, num);
	}
	
	/*특정 설문조사 특정 조사내용의 각 항목내용을 구하기 위한 메소드*/
	public List<HashMap<String, String>> findSurveyArticle(){
		RowMapper<HashMap<String, String>> rm = surveyConvertMap();
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from surveyarticle";
		
		return template.executeQuery(sql, rm);
	}
	
	/*조사내용, 항목을 최대개수에 맞춰 Map에 넣기위한 메소드 */
	public RowMapper<HashMap<String, String>> surveyConvertMap() {
		RowMapper<HashMap<String, String>> rm = new RowMapper<HashMap<String, String>>(){
			@Override
			public HashMap<String, String> mapRow(ResultSet rs) throws SQLException {
				HashMap<String, String> map = new HashMap<String, String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int countOfQuestion = rsmd.getColumnCount();		
				for(int i = 0; i < countOfQuestion-1; i++) {
					String ii = Integer.toString(i);
					map.put(ii, rs.getString(i+2));
				}
				return map;
			}
		};
		return rm;
	}
	
	/*항목에 해당하는 응답횟수의 총합을 구하는 메소드*/
	public List<HashMap<String, Integer>> surveyAnswerCountingTotalByNum(String surveyTableName, int num){
		RowMapper<HashMap<String, Integer>> rm = new RowMapper<HashMap<String, Integer>>(){
			@Override
			public HashMap<String, Integer> mapRow(ResultSet rs) throws SQLException {
				HashMap<String, Integer> articleCount = new HashMap<String, Integer>();
				articleCount.put(rs.getString("surveyarticle"), rs.getInt("surveycount"));
				return articleCount;
			}
		};
		JdbcTemplate template = new JdbcTemplate();
		
		ArrayList<String> list = listForOrderByField(num);
		
		String articleNum = "q" + num;
		
		String sql = "select " + articleNum + " as 'surveyarticle', "
				+ "count(*) as 'surveycount' "
				+ "from "+ surveyTableName+" group by " + articleNum 
				+ " order by field(surveyarticle, '" + list.get(0) + "', '" + list.get(1) + "', '" + list.get(2) + "', '" + list.get(3) + "', '" + list.get(4) + "')";
				
		return template.executeQuery(sql, rm);
	}
	
	/*해당 메소드는 List를 통해 얻기 보다 Map으로 바로 받는 편이 효율적이므로 template 미사용
	 * 총 응답횟수를 성별, 연령별, 총원으로 구하는 메소드*/
	public HashMap<String, Integer> surveyAnswerTotal(String surveyTableName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, Integer> countingMap = null;
		
		try {
			conn = ConnUtil.getConnection();
			String sql = "select q1 as 'surveyvalue', count(*) as 'surveycount' from " + surveyTableName + " group by q1 "
					+ "union all "
					+ "select q2 as 'surveyvalue', count(*) as 'surveycount' from " + surveyTableName + " group by q2 "
					+ "union all "
					+ "select '총원' as 'surveyvalue', count(*) as 'surveycount' from " + surveyTableName;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			countingMap = new HashMap<String, Integer>();
			while(rs.next()) {
				countingMap.put(rs.getString("surveyvalue"), rs.getInt("surveycount"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return countingMap;
	}
	
	/*특정 설문조사 항목에 해당하는 성별, 연령별 응답횟수를 구하기 위한 메소드*/
	public List<ArrayList<String>> surveyAnswerCountingByNum(String surveyTableName, int num){
		RowMapper<ArrayList<String>> rm = new RowMapper<ArrayList<String>>(){
			@Override
			public ArrayList<String> mapRow(ResultSet rs) throws SQLException {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("article"));
				list.add(rs.getString("전체"));
				list.add(rs.getString("남성"));
				list.add(rs.getString("여성"));
				list.add(rs.getString("10대 이하"));
				list.add(rs.getString("20대"));
				list.add(rs.getString("30대"));
				list.add(rs.getString("40대"));
				list.add(rs.getString("50대 이상"));
				return list;
			}
		};
		
		ArrayList<String> list = listForOrderByField(num);
		
		JdbcTemplate template = new JdbcTemplate();
		String articleNum = "q" + num;
		String sql = "select "+ articleNum + " as article, "
				+ "count(*) as '전체', "
				+ "sum(if(q2 = '남성', 1, 0)) as '남성', "
				+ "sum(if(q2 = '여성', 1, 0)) as '여성', "
				+ "sum(if(q1 = '10대 이하', 1, 0)) as '10대 이하', "
				+ "sum(if(q1 = '20대', 1, 0)) as '20대', "
				+ "sum(if(q1 = '30대', 1, 0)) as '30대', "
				+ "sum(if(q1 = '40대', 1, 0)) as '40대', "
				+ "sum(if(q1 = '50대 이상', 1, 0)) as '50대 이상' "
				+ "from " + surveyTableName + " group by " + articleNum 
				+ " order by field(article, '" + list.get(0) + "', '" + list.get(1) + "', '" + list.get(2) + "', '" + list.get(3) + "', '" + list.get(4) + "')";
		
		return template.executeQuery(sql, rm);
	}
	
	/*테이블의 정렬기준을 설문양식 순서에 맞게 재배치하기 위한 리스트를 반환하는 메소드*/
	public ArrayList<String> listForOrderByField(int num){
		RowMapper<ArrayList<String>> rm = new RowMapper<ArrayList<String>>(){
			@Override
			public ArrayList<String> mapRow(ResultSet rs) throws SQLException {
				ArrayList<String> list = new ArrayList<String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i = 2; i < rsmd.getColumnCount()+1; i++) {
					list.add(rs.getString(i));
				}
				return list;
			}	
		};
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from surveyarticle where num = ?";
		ArrayList<String> list = template.queryForObject(sql, rm, num);
		
		return list;
	}
	
	/*조사 항목 체크 값들을 데이터베이스에 넣기 위한 메소드*/
	public void insertAnswer(String surveyTableName, String user, int Num, ArrayList<Object> answerList) {
		String questionMark = "(?, ?, ?";				
		for(int i = 0; i< answerList.size(); i++) {		//설문조사마다 응답할 항목 개수가 다르므로 유동적으로 처리
			questionMark += ", ?";
		}
		questionMark += ")";
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into " + surveyTableName + " values" + questionMark;
		
		answerList.add(0, 0);
		answerList.add(1, user);
		answerList.add(2, Num);
		
		template.executeUpdate(sql, answerList.toArray());
	}

	/*설문조사 리스트에서 결과보기 버튼을 표시하기 위한 플래그처리를 위해 필요한, 설문조사완료 유저를 데이터베이스에 넣기 위한 메소드*/
	public void finishSurvey(String id, int num) {
		RowMapper<String> rm = rs -> new String(rs.getString(1));
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select finishMember from surveytitle where num = ?";
		String finishUser = template.queryForObject(sql, rm, num);
		String[] finishList = finishUser.split(";");
		
		for(int i = 0; i < finishList.length; i++) {
			if(id.equals(finishList[i])) {
				return;
			}
		}
		id += ";";
		String sql2 = "update surveytitle set finishMember = concat(finishMember, ?) where num = ?";
		template.executeUpdate(sql2, id, num);
	}
	
	/*설문조사 결과보기 플래그처리를 위해 설문조사 완료 유저를 구하기 위한 메소드*/
	public List<String[]> findFinishSurveyAllUser() {
		RowMapper<String[]> rm = new RowMapper<String[]>() {
			@Override
			public String[] mapRow(ResultSet rs) throws SQLException {
				String[] finishUserList = rs.getString(1).split(";"); 
				return finishUserList;
			}
		};
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select finishMember from surveytitle";
		
		return template.executeQuery(sql, rm); 
	}
}
