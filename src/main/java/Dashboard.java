import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dashboard {
    Employee employee;
    public Dashboard(Employee employee){
        this.employee = employee;
    }

    public void showDashboard(){
        ArrayList<Deadline> deadlines = Deadline.getUserDeadline(this.employee.getName());

        Collections.sort(deadlines, Comparator.comparing(Deadline::getDaysleft));

        for(Deadline d : deadlines){
            System.out.println(d.getDeadlineString());
        }
    }
}
