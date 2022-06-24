import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Deadline {
    //Template Method Pattern

    //variables
    protected String name;
    protected String username;
    protected String teamName;
    protected Date deadline;

    protected int reminderDays;

    //Getters
    public String getDeadlineUser() {
        return this.username;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getDeadlineString();

    public abstract String getType();

    //methodes
    public String getAssigned() {
        return this.username != null ? this.username : this.teamName;
    }

    public int getDaysleft() {
        long ms1 = new Date().getTime();
        long ms2 = deadline.getTime();
        long timeDiff;

        if (ms1 >= ms2) {
            timeDiff = ms1 - ms2;
        } else if (ms1 <= ms2) {
            timeDiff = ms2 - ms1;
        } else {
            timeDiff = 0;
        }

        return (int) (timeDiff / (1000 * 60 * 60 * 24));
    }

    public static ArrayList<Deadline> getUserDeadline(Employee employee) {
        ArrayList<Deadline> returnList = new ArrayList<>();

        //add individual deadlines
        for (Deadline deadline : Company.deadlines) {

            //return individual deadlines
            if (employee.getName().equals(deadline.getDeadlineUser())) {
                returnList.add(deadline);
            }


            //return team deadlines
            if (deadline.getTeamName() != null) {
                Team team = Company.getTeam(deadline.getTeamName());
                String[] teamUsers = team.getTeamMemberTags();

                if(employee.getName().equals(team.getManagerTag())){
                    returnList.add(deadline);
                }

                for (String user : teamUsers) {

                    //check if the user is present in the team
                    if (user.equals(employee.getName())) {
                        returnList.add(deadline);
                    }
                }
            }
        }

        return returnList;
    }

    public static void editDeadlinesProcess(Employee employee) {
        ArrayList<Deadline> editableDeadlines = new ArrayList<>();

        //get deadlines that the user can edit
        for (Deadline deadline : getUserDeadline(employee)) {
            //check if the user is the manager of the deadline

            //task deadline
            if (employee.getName().equals(deadline.getDeadlineUser())) {
                editableDeadlines.add(deadline);
            }

            //if the user is the manager of the team
            String teamName = deadline.getTeamName();
            if (teamName != null) {
                if (employee.getName().equals(Company.getTeam(teamName).getManagerTag())) {
                    editableDeadlines.add(deadline);
                }
            }
        }

        Menu.editDeadlineMenu(editableDeadlines);

        Scanner s = new Scanner(System.in);

        int input = s.nextInt();
        if (input > editableDeadlines.size()) {
            System.out.println("Please enter a valid number!");
        }

        try {
            editDeadline(editableDeadlines.get(input - 1));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void editDeadline(Deadline deadline) throws ParseException {
        //The user will be able to change the name, deadline date and the reminderDays
        //this method will only run with final deadlines and task deadlines that have been added to the user in the editDeadlinesProcess method
        Scanner scanner = new Scanner(System.in);

        //change the name of the deadline
        System.out.printf("(%s) Change name (Enter to continue)? ", deadline.getName());
        String nameInput = scanner.nextLine();
        if (!nameInput.equals("")) {
            deadline.name = nameInput;
        }

        //change the deadline date
        System.out.printf("(%s) Change date dd-mm-yyyy (Enter to continue)? ", deadline.deadline);
        String dateInput = scanner.nextLine();
        if (!dateInput.equals("")) {
            try {
                Date newDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateInput);
                deadline.deadline = newDate;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        //change the reminder days variable
        System.out.printf("(%s) Change reminder days (Enter to continue)? ", deadline.reminderDays);
        String reminderInput = scanner.nextLine();
        if (!reminderInput.equals("")) {
            deadline.reminderDays = Integer.parseInt(reminderInput);
        }
    }
}
