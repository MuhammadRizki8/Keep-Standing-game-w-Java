//kode ini ini digunakan untuk melakukan operasi pada tabel "texperiences" dalam database.
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ExperienceTable extends DB {
    public ExperienceTable() throws Exception, SQLException {
        super();
    }
    public void getData() {
        try {
            String query = "SELECT * FROM texperiences";
            createQuery(query);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    public void getDetailData(String username) {
        try {
            String query = "SELECT * FROM texperiences WHERE username='" + username + "'"; 
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    } 
    public void insertData(Experience exp) {
        try {
            String query = "INSERT INTO texperiences VALUES ('" + exp.getUsername() + "', '" + exp.getstanding() + "', '" + exp.getscore() + "')";
            createUpdate(query);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    public void updateData(Experience exp) {
        try {
            String query = "UPDATE texperiences SET standing=" + exp.getstanding() + ", score=" + exp.getscore() + " WHERE username='" + exp.getUsername() + "'";
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
