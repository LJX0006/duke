import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * add a new task to the list
 */

public class CmdAdd extends Command {

    private String cmdType;
    private String s;
    private SimpleDateFormat simpleDateFormat;

    /**
     * initialize a add command object
     * @param str the type of the command
     * @param cmd original command from the input
     */
    public CmdAdd(String str, String cmd) {
        cmdType = str;
        s = cmd;
        simpleDateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
    }

    /**
     * execute the command based on its type
     * @param list the tasklist object
     * @param ui to print on user interface
     * @param storage to edit local saved file
     * @throws ParseException when the parsing in wrong
     * @throws DukeException when the command line cannot be identified
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws ParseException, DukeException {
        switch (cmdType) {
            case "todo": {
                if (s.equals("todo")) {
                    throw new DukeException("    OOPS!!! The description of a todo cannot be empty.");
                }
                Todo t = new Todo(s.replaceFirst("todo ", ""));
                list.addTodo(t);
                break;
            }
            case "deadline": {
                if (s.equals("deadline")) {
                    throw new DukeException("    OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] getDate = s.split("/by ");
                Date date = simpleDateFormat.parse(getDate[getDate.length - 1]);
                String formatTime = simpleDateFormat.format(date);
                Deadline t = new Deadline(getDate[0].replaceFirst("deadline ", ""), formatTime);
                list.addDeadline(t);
                break;
            }
            case "event": {
                if (s.equals("event")) {
                    throw new DukeException("    OOPS!!! The description of a event cannot be empty.");
                }
                String[] getDate = s.split("/at ");
                Date date = simpleDateFormat.parse(getDate[getDate.length - 1]);
                String formatTime = simpleDateFormat.format(date);
                Event t = new Event(getDate[0].replaceFirst("event ", ""), formatTime);
                list.addEvent(t);
                break;
            }
        }
        storage.writeFile(list.getList());
        System.out.println("    Got it. I've added this task: \n");
        System.out.println("    " + list.getTask(list.getSize() - 1).toString() + "\n");
        System.out.println("    Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * check if the program needs to stop running
     * @return whether this command means stopping running
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
