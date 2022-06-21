import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static Login singleton;
    private ArrayList<User> users;
    private User user;

    private Login() {
        users = Company.getUsers();
        user = null;
    }

    public static Login getInstance() {
        return singleton == null ? new Login() : singleton;
    }

    public boolean userIsAuthenticated() {
        return user != null;
    }

    private boolean userExists (String name) {
        for (User user : users) {
            if (name.equals(user.getName())) {
                this.user = user;
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated() {

        //return true if the user is Authenticated
        if (userIsAuthenticated()) {
            return true;
        }


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {

            System.out.println("=================");
            System.out.print("Please enter your username: ");
            String userName = scanner.nextLine();

            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            System.out.println("=================");

            if (userExists(userName) && user.checkPassword(password)) {
                System.out.println();
                return true;
            }

            System.out.println("Login failed! Try again.");
        }

        System.out.println("=================");
        System.out.println();
        return false;
    }
}
