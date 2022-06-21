public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Employee employee = new Employee("Siddhart", false);

        System.out.println("User Logged in as: " + employee.getName() + "\nManager: " + employee.isManager());
    }
}
