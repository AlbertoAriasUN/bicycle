import java.sql.*;

public class QueryDB {
	Connection conn = null;
	public QueryDB() throws SQLException{
		conn = JdbcSing.getInstance().getConnection();
	}
	
	
}
