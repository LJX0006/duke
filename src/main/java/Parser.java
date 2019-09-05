public class Parser {
    public Parser() {}

    public static Command parse(String cmd) throws DukeException {
        Command command;
        if (cmd.equals("bye")) {
            command = new CmdExit();
        }
        else if (cmd.equals("list")) {
            command = new CmdList();
        }
        else if(cmd.contains("done")) {
            String temp = cmd.replaceAll("[^0-9]", "");
            int n = Integer.parseInt(temp);
            command = new CmdDone(n);
        }
        else if (cmd.contains("delete")) {
            String temp = cmd.replaceAll("[^0-9]", "");
            int n = Integer.parseInt(temp);
            return new CmdDelete(n);
        }
        else if (cmd.contains("find")) {
            if (cmd.equals("find"))
                throw new DukeException("    OOPS!!! The description of a find cannot be empty.");
            String keyword = cmd.split(" ")[1];
            command = new CmdFind(keyword);
        }
        else {
            String keyword = cmd.split(" ")[0];
            if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))) {
                throw new DukeException("    OOPS!!! I'm sorry, but I don't know what that means");
            }
            command = new CmdAdd(keyword, cmd);
        }
        return command;
    }
}