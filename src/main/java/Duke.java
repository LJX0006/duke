import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * The control module
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCmd = ui.readCmd();
                Command cmd = Parser.parse(userCmd);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
                System.out.println("    ____________________________________________________________\n");
            } catch (DukeException | ParseException e) {
                ui.error(e.getMessage());
            } finally {
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }

    /**
     * initialize a duke object and run it
     *
     * @param args the input
     * @throws DukeException some error messages
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}
