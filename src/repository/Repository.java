package repository;

import domain.Weather;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void createSchema(){

        try
        {
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS weatherdatabase(startHour int(100), endHour int(100), temp int(100), precipProb int(100), description varchar(100));");
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    public void AddInSchema(){
        try
        {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO weatherdatabase VALUES (?,?,?,?,?),(?,?,?,?,?),(?,?,?,?,?),(?,?,?,?,?),(?,?,?,?,?)");
            statement.setInt(1,12 );
            statement.setInt(2, 14);
            statement.setInt(3, 18);
            statement.setInt(4, 23);
            statement.setString(5, "sunny, wind 5km/h");
            statement.setInt(6, 6);
            statement.setInt(7, 9);
            statement.setInt(8, 10);
            statement.setInt(9, 13);
            statement.setString(10, "foggy");
            statement.setInt(11, 9);
            statement.setInt(12,12 );
            statement.setInt(13, 13);
            statement.setInt(14, 7);
            statement.setString(15, "cloudy");
            statement.setInt(16, 18);
            statement.setInt(17, 20);
            statement.setInt(18,20 );
            statement.setInt(19, 78);
            statement.setString(20, "rainy, heavy rain");
            statement.setInt(21, 14);
            statement.setInt(22, 18);
            statement.setInt(23,20);
            statement.setInt(24, 62);
            statement.setString(25, "rainy, wind 10km/h, real feel 18C");

            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Weather> getAll(){

        ArrayList<Weather> weathers= new ArrayList<>();;
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM weatherdatabase");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Weather w = new Weather(
                        rs.getInt("startHour"),
                        rs.getInt("endHour"),
                        rs.getInt("temp"),
                        rs.getInt("precipProb"),
                        rs.getString("description")
                );
                weathers.add(w);
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weathers;
    }
}
