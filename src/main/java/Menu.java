import java.lang.reflect.Array;
import java.util.ArrayList;

public class Menu {

    public Employee employee;

    public Menu(Employee employee) {
        this.employee = employee;
        welcomeUser();
    }

    private void welcomeUser() {
        System.out.println("\u001B[31mUser Logged in as: " + this.employee.getName() + "\nManager: " + this.employee.isManager() + "\u001B[0m\n");
    }

    private ArrayList<String> userMenuOptions = new ArrayList<>() {
        {
            add("Show Dashboard");
            add("Edit Deadline");
            add("Add Deadline");
        }
    };

    public void printMenu() {
        System.out.println("=================");

        int count = 1;
        for (String option : userMenuOptions) {
            System.out.println(String.format("%d. %s", count, option));
            count++;
        }

        System.out.println(String.format("%d. Exit Application", count));

        System.out.println("=================");
        System.out.println("\nPlease choose an option: ");
    }

    public static void editDeadlineMenu(ArrayList<Deadline> deadlines) {
        System.out.println("=================");

        int count = 1;
        for (Deadline deadline : deadlines) {
            System.out.println(String.format("%d. %d days - %s", count++, deadline.getDaysleft(), deadline.getName()));
        }

        System.out.println("=================");
        System.out.println("\nEnter the number of the deadline you want to edit: ");
    }

    public static void getAddDeadlineOptions(Employee employee) {

        ArrayList<String> options = new ArrayList<>();

        options.add("Task Deadline");

        if (employee.isManager()) {
            options.add("Final Deadline");
        }

        int count = 1;
        for(String option : options) {
            System.out.println(count++ + ". " + option);
        }
    }

    private int getUserOptionCount() {
        return userMenuOptions.size();
    }


    public void chooseOption(int input) {
        if (input > getUserOptionCount()) {
            System.exit(0);
        }

        switch (input) {
            case 1:
                optionShowDashboard();
                break;
            case 2:
                optionEditDeadline();
                break;
            case 3:
                optionAddDeadline();
                break;
        }
    }

    private void optionShowDashboard() {
        Dashboard dashboard = new Dashboard(this.employee);
        dashboard.showDashboard();
    }

    private void optionEditDeadline() {
        Deadline.editDeadlinesProcess(employee);
    }

    //only for managers
    private void optionAddDeadline() {
        Deadline.addDeadlineProcess(employee);
    }
}
