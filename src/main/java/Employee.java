public class Employee {
    private Login login;

    private String name;
    private boolean manager;

    public Employee(String tag, boolean manager) {

        login = Login.getInstance();

        if (login.isAuthenticated()) {
            this.name = tag;
            this.manager = manager;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isManager() {
        return manager;
    }
}