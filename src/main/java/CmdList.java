import java.text.ParseException;

/**
 * print all the tasks
 */
public class CmdList extends Command{

    public CmdList() { }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException, ParseException {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println("    " + (i + 1) + "." + list.getTask(i).toString() + "\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}