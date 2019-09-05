import java.text.ParseException;

public abstract class Command {
    public Command() { }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException, ParseException;
    public abstract boolean isExit();
}