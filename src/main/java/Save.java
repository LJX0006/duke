import java.io.*;
import java.util.ArrayList;

public class Save {
    private static ArrayList<Task> item = new ArrayList<>();
    private static int n = 0;

    public Save() {
        try {
            FileReader fileReader = new FileReader("data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                str = str.replace('|', '/');
                String[] detail = str.split(" / ");
                Task t = new Task("waiting");
                if (detail[0].equals("T")) {
                    t = new Todo(detail[2]);
                }
                else if (detail[0].equals("D")) {
                    t = new Deadline(detail[2], detail[3]);
                }
                else {
                    t = new Event(detail[2], detail[3]);
                }
                if (detail[1].equals("1")) {
                    t.markAsDone();
                }
                item.add(t);
                n++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getDetail() {
        return item;
    }

    public int getNum() {
        return n;
    }

    public void writeFile(ArrayList<Task> List) {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            for (Task t : List) {
                if (t instanceof Todo) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + "\n");
                }
                else if (t instanceof Deadline) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + " | "
                                + ((Deadline) t).by + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + " | "
                                + ((Deadline) t).by + "\n");

                }
                else if (t instanceof Event) {
                    if (t.isDone)
                        bufferedWriter.write(t.symbol + " | 1 | " + t.description + " | "
                                + ((Event) t).at + "\n");
                    else
                        bufferedWriter.write(t.symbol + " | 0 | " + t.description + " | "
                                + ((Event) t).at + "\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
