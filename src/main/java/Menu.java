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
        }
    };

    private ArrayList<String> managerMenuOptions = new ArrayList<>() {
        {
            add("Add Deadline");
        }
    };

    public void printMenu() {
        if (this.employee.isManager()) {
            printManagerMenu();
            return;
        }

        printUserMenu();
    }

    private void printUserMenu() {
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

    private void printManagerMenu() {
        System.out.println("=================");

        ArrayList<String> combinedOptions = new ArrayList<>();
        combinedOptions.addAll(userMenuOptions);
        combinedOptions.addAll(managerMenuOptions);

        int count = 1;
        for (String option : combinedOptions) {
            System.out.println(String.format("%d. %s", count, option));
            count++;
        }

        System.out.println(String.format("%d. Exit Application", count));

        System.out.println("=================");
        System.out.println("\nPlease choose an option: ");
    }

    private int getUserOptionCount() {
        if (this.employee.isManager()) {
            return userMenuOptions.size() + managerMenuOptions.size();
        }

        return userMenuOptions.size();
    }

    public void chooseOption(int input) {
        if (input > getUserOptionCount()) {
            System.exit(0);
        }

        switch (input){
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

    private void optionShowDashboard(){
        Dashboard dashboard = new Dashboard(this.employee);
        dashboard.showDashboard();
    }

    private void optionEditDeadline(){

    }

    //only for managers
    private void optionAddDeadline(){

    }
}
