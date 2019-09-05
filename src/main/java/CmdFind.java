import java.text.ParseException;

public class CmdFind extends Command {
    private String keyword;

    protected CmdFind(String s) {
        keyword = s;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException, ParseException {
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getTask(i).getDescription().contains(keyword)) {
                System.out.println("    " + (i + 1) + "." + list.getTask(i).toString() + "\n");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
