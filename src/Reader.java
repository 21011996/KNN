import java.io.*;
import java.util.ArrayList;

/**
 * Created by shambala on 17/09/16.
 */

public class Reader {
    ArrayList<Dot> read(String file) {
        ArrayList<Dot> dots = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] spl = line.split(",");
                dots.add(new Dot(Double.parseDouble(spl[0]), Double.parseDouble(spl[1]), Integer.parseInt(spl[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dots;
    }

    public static void main(String[] args) {
        ArrayList<Dot> dots = new Reader().read("chips.txt");
    }
}

