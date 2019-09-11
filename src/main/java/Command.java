import java.text.ParseException;

/**
 * the abstract class for all types of commands from inputs
 */
public abstract class Command {
    public Command() { }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException, ParseException;
    public abstract boolean isExit();
}