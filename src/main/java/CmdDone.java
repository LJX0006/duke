import java.text.ParseException;

public class CmdDone extends Command {
    private int num;

    public CmdDone(int n) {
        num = n;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        tasks.doneTask(num);
        storage.writeFile(tasks.getList());
        System.out.println("    Nice! I've marked this task as done:\n");
        System.out.println("    [✓] " + tasks.getTask(num).description + "\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
