package DB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
	void setParameters(PreparedStatement pstmt) throws SQLException;
}
