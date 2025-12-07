package DataIO;

import DataModel.User;
import java.io.*;
import java.util.StringTokenizer;

public class UserIO {

    // Save new user to file
    public static boolean add(User user, String filepath) {
        try {
            File file = new File(filepath);
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            out.println(user.getEmail() + "|" + user.getFirstName() + "|" +
                        user.getLastName() + "|" + user.getPassword());
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search user based on firstName + lastName + password
    public static User get(String firstName, String lastName, String password, String filepath) {
        try {
            File file = new File(filepath);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();

            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                if (t.countTokens() < 4) {
                    line = in.readLine();
                    continue;
                }

                String email = t.nextToken();
                String firstNameToken = t.nextToken();
                String lastNameToken = t.nextToken();
                String passwordToken = t.nextToken();

                if (firstNameToken.equalsIgnoreCase(firstName)
                        && lastNameToken.equalsIgnoreCase(lastName)
                        && passwordToken.equals(password)) {
                    User user = new User(firstNameToken, lastNameToken, email, passwordToken);
                    in.close();
                    return user;
                }
                line = in.readLine();
            }
            in.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
