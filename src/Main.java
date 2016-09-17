import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Main {
    static int k = 5;
    public static void main(String[] args) {
        ArrayList<Dot> tmp = new Reader().read("chips.txt");
        Collections.shuffle(tmp);
        double answer = 0;
        int size = 0;
        double answer2 = 0;
        int size2 = 0;
        int part = tmp.size()/k;
        ArrayList<ArrayList<Dot>> partitions = new ArrayList<>();
        for (int i = 0; i < tmp.size(); i += part) {
            partitions.add(new ArrayList<>(tmp.subList(i,
                    Math.min(i + part, tmp.size()))));
        }
        for (int i = 0; i<k; i++) {
            ArrayList<KNN.Label> knn = KNN.classifyKNN(getTrain(i, partitions),partitions.get(i),5);
            for (KNN.Label label : knn) {
                if (label.stat[0] > label.stat[1]){
                    answer += label.distance;
                size++;
                } else {
                    answer2 += label.distance;
                    size2++;
                }
            }
        }
        answer = answer / size;
        answer2 = answer2 / size2;
        System.out.println(answer);
        System.out.println(answer2);
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

    public static ArrayList<Dot> getTrain(int p, ArrayList<ArrayList<Dot>> partitions) {
        ArrayList<Dot> answer = new ArrayList<Dot>();
        for (int i = 0; i < partitions.size(); i++) {
            if (i!=p) {
                answer.addAll(partitions.get(i));
            }
        }
        return answer;
    }
}
