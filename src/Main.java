import java.util.ArrayList;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Dot> tmp = new Reader().read("chips.txt");
        new Draw("x","y").addGraphic(tmp,"lul").show();

    }
}
