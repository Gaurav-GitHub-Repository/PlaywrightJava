package Configuration;

import java.sql.*;

public class DatabaseConfiguration extends PropertiesFile{

    public void executeQuery() throws SQLException {
        String host = "localhost";
        String port = "3306";

        Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/QADB", "root", "root");
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("select * from EmployeeInfo where name = 'Gau'");
        while(result.next()) {
            System.out.println(result.getString("name"));
        }
    }

}
