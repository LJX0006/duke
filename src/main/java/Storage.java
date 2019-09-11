import java.io.*;
import java.util.ArrayList;

/**
 * the class to read from or write to the local file
 */
public class Storage {
    private String file;

    /**
     * the constructor to initialize the storage tool
     * @param filePath the path of the file which contains the data of the task list
     */
    public Storage(String filePath) {
        file = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> item = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                s = s.replace('|', '/');
                String[] detail = s.split(" / ");
                Task t = new Task("empty");
                if (detail[0].equals("T")) {
                    t = new Todo(detail[2]);
                } else if (detail[0].equals("D")) {
                    t = new Deadline(detail[2], detail[3]);
                } else {
                    t = new Event(detail[2], detail[3]);
                }
                if (detail[1].equals("1")) {
                    t.markAsDone();
                }
                item.add(t);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void writeFile(ArrayList<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            for (Task t : list) {
                if (t instanceof Todo) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + "\n");
                }
                else if (t instanceof Deadline) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + " | " + ((Deadline) t).by + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + " | " + ((Deadline) t).by + "\n");
                }
                else if (t instanceof Event) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + " | " + ((Event) t).at + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + " | " + ((Event) t).at + "\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}