import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Employee employee = new Employee("DevAdmin", true, "Development");

        Menu menu = new Menu(employee);

        Scanner s = new Scanner(System.in);
        int input = 0;


        while (true) {
            menu.printMenu();
            input = s.nextInt();

            menu.chooseOption(input);
        }
    }
}
