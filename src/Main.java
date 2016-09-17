import java.util.ArrayList;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Dot> tmp = new Reader().read("chips.txt");
        ArrayList<Dot> dot0 = new ArrayList<>();
        ArrayList<Dot> dot1 = new ArrayList<>();
        for (Dot dot : tmp) {
            if (dot.lul == 0) {
                dot0.add(dot);
            } else {
                dot1.add(dot);
            }
        }
        new Draw("x","y").addGraphic(dot0,"dot0").addGraphic(dot1,"dot1").show();

    }
}
