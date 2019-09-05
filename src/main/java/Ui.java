import java.util.Scanner;
import java.io.IOException;

public class Ui {
    private Scanner scanner;

    public Ui () {
        scanner = new Scanner(System.in);
    }

    public String readCmd() {
        return scanner.nextLine();
    }

    public void error(String reaction) {
        System.out.println(reaction);
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("    ____________________________________________________________\n"
                + "    OOPS!!! This is not a valid input :-(\n"
                + "    ____________________________________________________________");
    }
}
