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

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Task[] item = new Task[100];
        int n = 0;
        while (!(s.equals("bye"))) {
            if (s.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= n; i++) {
                    System.out.print("     " + i + ". " + item[i - 1].toString() + "\n".toString());
                }
                System.out.println("    ____________________________________________________________");
            }
            else if (s.contains("done")) {
                String temp = s.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(temp);
                item[num - 1].markAsDone();
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Nice! I've marked this task as done:\n");
                System.out.println("       [✓] " + item[num - 1].description + "\n");
                System.out.println("    ____________________________________________________________\n");
            }
            else {
                try {
                    String[] type = s.split(" ");
                    if (!(type[0].equals("todo") || type[0].equals("deadline") || type[0].equals("event"))) {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    switch (type[0]) {
                        case "todo": {
                            if (s.equals("todo")) {
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            Task t = new Todo(s.replaceFirst("todo ", ""));
                            item[n++] = t;
                            break;
                        }
                        case "deadline": {
                            if (s.equals("deadline")) {
                                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String[] getDate = s.split("/by ");
                            Task t = new Deadline(getDate[0].replaceFirst("deadline ", ""), getDate[getDate.length - 1]);
                            item[n++] = t;
                            break;
                        }
                        case "event": {
                            if (s.equals("event")) {
                                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                            }
                            String[] getDate = s.split("/at ");
                            Task t = new Event(getDate[0].replaceFirst("event ", ""), getDate[getDate.length - 1]);
                            item[n++] = t;
                            break;
                        }
                    }
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.println("     " + item[n - 1].toString());
                    System.out.println("     Now you have " + n + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }
                s = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}