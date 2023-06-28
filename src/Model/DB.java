//kode ini digunakan untuk mengelola koneksi dan melakukan operasi pada database MySQL. 
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class DB {
    private String url = "jdbc:mysql://localhost:3306/db_gamedpbo?user=root&password=";
    private Statement stm = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public DB() throws Exception, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(url);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createQuery(String query) throws Exception, SQLException {
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            if (stm.execute(query)) {
                rs = stm.getResultSet();
            }
        } catch(SQLException e) {
            throw e;
        }
    }

    public void createUpdate(String query) throws Exception, SQLException {
        try {
            stm = conn.createStatement();
            int result = stm.executeUpdate(query);
        } catch(SQLException e) {
            throw e;
        }
    }

    public ResultSet getResult() throws Exception {
        ResultSet temp = null;
        try {
            return rs;
        } catch(Exception e) {
            return temp;
        }
    }

    public void closeResult() throws Exception, SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch(SQLException e) {
                rs = null;
                throw e;
            }
        }

        if (stm != null) {
            try {
                stm.close();
            } catch(SQLException e) {
                stm = null;
                throw e;
            }
        }
    }

    public void closeConnection() throws Exception, SQLException {
        if (conn != null) {
            try {
                conn.close();
            } catch(SQLException e) {
                conn = null;
                throw e;
            }
        }
    }
}

