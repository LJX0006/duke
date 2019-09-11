import java.text.ParseException;

/**
 * The command to delete a task from the list
 */
public class CmdDelete extends Command {
    private int num;

    /**
     * the constructor
     * @param n the index number of the task to be deleted
     */
    protected CmdDelete(int n) {
        num = n;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException, ParseException {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Noted. I've removed this task:\n");
        System.out.println("    " + list.getTask(num - 1).toString() + "\n");
        System.out.println("    Now you have " + (list.getSize() - 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
        list.deleteTask(num-1);
        storage.writeFile(list.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
