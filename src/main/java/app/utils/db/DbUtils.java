package app.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import app.utils.GenericUtils;
import app.utils.Secrets;

public class DbUtils {

    private final static int DB_KEEP_OPEN_DURATION = 10 * 1000;

    private static Connection connection;
    private static Statement statement;
    private static ArrayList<ResultSet> resultSets = new ArrayList<>();
    private static Timer closeConnectionTimer;

    /** Base method to perform a database request, taking the raw sql query as a parameter */
    public static ResultSet makeRequest(String sqlQuery) {
        GenericUtils.log(sqlQuery);
        try {
            openConnection();
            try {
                ResultSet rs = statement.executeQuery(sqlQuery);
                resultSets.add(rs);
                return rs;
            } catch (SQLException e) {
                GenericUtils.log("DB: no response from query");
                e.printStackTrace();
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    /** Open a connection with the database */
    private static void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
                    e.printStackTrace();
                    return;
                }
                System.out.println("PostgreSQL JDBC Driver Registered!");

                String url = "jdbc:postgresql://" + Secrets.DATABASE_HOST + ":" + Secrets.DATABASE_PORT + "/"
                        + Secrets.DATABASE_NAME;
                Properties props = new Properties();
                props.setProperty("user", Secrets.DATABASE_USER);
                props.setProperty("password", Secrets.DATABASE_PASSWORD);
                props.setProperty("ssl", "true");
                props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
                try {
                    connection = DriverManager.getConnection(url, props);
                    statement = connection.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Close the database connection */
    private static void closeConnection() {
        GenericUtils.log("DB: start timer to close db connection");
        if (closeConnectionTimer != null)
            closeConnectionTimer.cancel();

        closeConnectionTimer = new Timer();
        closeConnectionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                GenericUtils.log("DB: try to close db connection");
                try {
                    for (ResultSet rs : resultSets) {
                        rs.close();
                    }
                    GenericUtils.log("DB: closed resultSets");
                    resultSets.clear();
                } catch (SQLException e) {
                    GenericUtils.log("DB: Couldnt close Statement");
                }
                try {
                    statement.close();
                    GenericUtils.log("DB: closed db statement");
                } catch (Exception e) {
                    GenericUtils.log("DB: Couldnt close Statement");
                }
                try {
                    connection.close();
                    GenericUtils.log("DB: closed db connection");
                } catch (Exception e) {
                    GenericUtils.log("DB: Couldnt close Connection");
                }
            }
        }, DB_KEEP_OPEN_DURATION);

    }

    /** Format an array to sql format */
    public static String getArrayForSql(ArrayList<String> arrayList) {
        return arrayList.toString().replace("[", "{") //remove the right bracket
                .replace("]", "}") //remove the left bracket
                .trim();
    }

}