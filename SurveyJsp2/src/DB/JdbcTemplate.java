package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public void executeUpdate(String sql, PreparedStatementSetter pss) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void executeUpdate(String sql, Object... parameters) {
		executeUpdate(sql, createPreparedStatementSetter(parameters));
	}
	
	public <T> List<T> executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> result = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			rs = pstmt.executeQuery();
			result = new ArrayList<T>();
			while(rs.next()) {
				result.add(rm.mapRow(rs));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public <T> List<T> executeQuery(String sql, RowMapper<T> rm, Object... parameters) {
		return executeQuery(sql, rm, createPreparedStatementSetter(parameters));
	}	
	
	private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {
				@Override
				public void setParameters(PreparedStatement pstmt) throws SQLException {
					for(int i = 0; i < parameters.length; i++) {
						pstmt.setObject(i+1, parameters[i]);
					}
				}
			};
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rm, PreparedStatementSetter pss){
		List<T> result = executeQuery(sql, rm, pss);
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rm, Object... parameters){
		return queryForObject(sql, rm, createPreparedStatementSetter(parameters));
	}
}
