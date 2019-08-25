import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________");

        Scanner in = new Scanner (System.in);
        String s = in.nextLine();
        String stop = "bye";
        String show = "list";
        String mark = "done";
        String todo = "[✗] ";
        String finish = "[✓] ";
        Task[] item = new Task[100];
        int n = -1;
        while (!(s.equals(stop))) {
            if (s.equals(show)) {
                System.out.println("    ____________________________________________________________\n"
                        + "     Here are the tasks in your list:");
                for (int i = 1; i <= n + 1; i++) {
                    System.out.println("     " + i + ". " + item[i - 1]);
                }
                System.out.println("    ____________________________________________________________");
            }
            else if (s.contains(mark)) {
                String temp = s.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(temp);
                item[num - 1].markAsDone();
                System.out.println("    ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "       " + item[num - 1].getStatusIcon() + item[num - 1].description + "\n"
                        + "    ____________________________________________________________");
            }
            else {
                n++;
                Task t = new Task(s);
                item[n] = t;
                System.out.println("    ____________________________________________________________\n"
                        + "     added: " + s + "\n"
                        + "    ____________________________________________________________");
            }
            s = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}
