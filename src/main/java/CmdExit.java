import java.text.ParseException;

/**
 * stop running the program
 */
public class CmdExit extends Command {

    public CmdExit() { }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage)  throws DukeException, ParseException {
        System.out.println("     Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}