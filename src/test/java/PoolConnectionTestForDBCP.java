import com.jim.db.PoolConnectionDBCP2;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jim on 2017/7/12.
 * This class is ...
 */
@Test
public class PoolConnectionTestForDBCP {

	private Connection connection;
	private ResultSet users;

	@BeforeTest
	public void getConnection() throws SQLException {
		connection = PoolConnectionDBCP2.getInstance().getConnection();
	}

	@Test
	public void getUsers() throws SQLException {
		Assert.assertNotEquals(connection, null);
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `utf_user` AS t1");
		users = preparedStatement.executeQuery();
	}

	@Test(dependsOnMethods = "getUsers")
	public void printUser() throws SQLException {
		while (users.next()) {
			System.out.println(users.getString("id"));
			System.out.println(users.getString("account"));
			System.out.println(users.getString("name"));
		}
	}
}
