import java.util.*;
import java.util.ArrayList;

public class TaskList {
    protected static  ArrayList<Task> list;

    public TaskList(ArrayList<Task> initialList) {
        list = initialList;
    }

    public TaskList(){
        list = new ArrayList<>();
    }

    public Task getTask(int num) {
        return list.get(num);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public int getSize(){
        return list.size();
    }

    public void doneTask(int num) {
        list.get(num - 1).markAsDone();
    }

    public Task deleteTask(int num) {
        Task temp = list.get(num);
        list.remove(num);
        return temp;
    }

    public void addTodo(Todo t) {
        list.add(t);
    }

    public void addDeadline(Deadline d) {
        list.add(d);
    }

    public void addEvent(Event e) {
        list.add(e);
    }
}
