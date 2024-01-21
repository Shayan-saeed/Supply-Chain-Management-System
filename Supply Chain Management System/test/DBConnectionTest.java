import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ADEAL COMPUTER 'S
 */
public class DBConnectionTest {

    @Test
    public void testMakeDatabaseConnection() {
        DBConnection dbConnection = new DBConnection();
        try {
            Connection connection = dbConnection.mkDataBase();
            System.out.println(connection);
        } catch (SQLException ex) {
            fail("SQLException: " + ex.getMessage());
        }
    }
}