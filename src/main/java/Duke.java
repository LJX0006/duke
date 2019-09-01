import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

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
        int n = 0;
        Save save = new Save();
        ArrayList<Task> item = save.getDetail();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        while (!(s.equals("bye"))) {
            if (s.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Here are the tasks in your list:");
                int i = 1;
                for (Task t : item) {
                    System.out.println("    " + i + "." + t.toString() + "\n");
                    i = i + 1;
                }
                System.out.println("    ____________________________________________________________");
            }
            if (s.contains("done")) {
                String temp = s.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(temp);
                item.get(num - 1).markAsDone();
                save.writeFile(item);
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Nice! I've marked this task as done:\n");
                System.out.println("   [✓] " + item.get(num - 1).description + "\n");
                System.out.println("    ____________________________________________________________\n");
            }
            if (s.contains("delete")) {
                String temp = s.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(temp);
                save.writeFile(item);
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Noted. I've removed this task:\n");
                System.out.println("    " + item.get(num - 1).toString() + "\n");
                System.out.println("    Now you have " + (item.size() - 1) + " tasks in the list.");
                System.out.println("    ____________________________________________________________\n");
                item.remove(num - 1);
                save.writeFile(item);
                if (s.contains("find")) {
                    String keyword = s.split(" ")[1];
                    int i = 1;
                    System.out.println("____________________________________________________________\n");
                    for (Task t : item) {
                        if (t.description.contains(keyword))
                            System.out.println(" " + i++ + "." + t.toString() + "\n");
                    }
                    System.out.println("____________________________________________________________\n");
                } else {
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
                                item.add(t);
                                break;
                            }
                            case "deadline": {
                                if (s.equals("deadline")) {
                                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                                }
                                String[] getDate = s.split("/by ");
                                Date date = simpleDateFormat.parse(getDate[getDate.length - 1]);
                                String formatTime = simpleDateFormat.format(date);
                                Task t = new Deadline(getDate[0].replaceFirst("deadline ", ""), formatTime);
                                item.add(t);
                                break;
                            }
                            case "event": {
                                if (s.equals("event")) {
                                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                                }
                                String[] getDate = s.split("/at ");
                                Date date = simpleDateFormat.parse(getDate[getDate.length - 1]);
                                String formatTime = simpleDateFormat.format(date);
                                Task t = new Event(getDate[0].replaceFirst("event ", ""), formatTime);
                                item.add(t);
                                break;
                            }
                        }
                        save.writeFile(item);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("     " + item.get(item.size() - 1).toString());
                        System.out.println("     Now you have " + item.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } catch (DukeException | ParseException e) {
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
}
