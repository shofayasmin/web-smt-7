package DataIO;

import DataModel.User;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserIO {

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        DBUtil db = new DBUtil();

        try {
            String sql = "SELECT * FROM user";
            ResultSet rs = db.QueryData(sql);

            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setFirstName(rs.getString("FirstName"));
                u.setLastName(rs.getString("LastName"));
                u.setEmail(rs.getString("Email"));
                list.add(u);
            }

            db.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean add(User user) {
        DBUtil db = new DBUtil();

        try {
            String sql =
                "INSERT INTO `user` (FirstName, LastName, Email) VALUES (" +
                "'" + user.getFirstName() + "', " +
                "'" + user.getLastName() + "', " +
                "'" + user.getEmail() + "')";

            int result = db.QueryUpdate(sql);
            db.Close();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static User get(String firstName, String lastName) {
        DBUtil db = new DBUtil();

        try {
            String sql =
                "SELECT * FROM `user` WHERE FirstName='" + firstName +
                "' AND LastName='" + lastName + "'";

            ResultSet rs = db.QueryData(sql);

            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setFirstName(rs.getString("FirstName"));
                u.setLastName(rs.getString("LastName"));
                u.setEmail(rs.getString("Email"));
                db.Close();
                return u;
            }

            db.Close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
