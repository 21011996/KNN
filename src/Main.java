import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Main {
    int k = 10;
    public static void main(String[] args) {
        new Main().run();
    }

    void run() {
        ArrayList<Dot> data = new Reader().read("chips.txt");
        Collections.shuffle(data);
        double answer = 0;
        int size = 0;
        double answer2 = 0;
        int size2 = 0;
        int part = data.size()/k;
        ArrayList<ArrayList<Dot>> partitions = new ArrayList<>();
        for (int i = 0; i < data.size(); i += part) {
            partitions.add(new ArrayList<>(data.subList(i,
                    Math.min(i + part, data.size()))));
        }
        double accuracySum = 0;
        for (int i = 0; i<k; i++) {
            ArrayList<Dot> train = getTrain(i, partitions);
            ArrayList<Dot> test = new ArrayList<>(partitions.get(i));
            ArrayList<KNN.Label> knn = KNN.classifyKNN(train, partitions.get(i), 5);
            int correctCount = 0;
            for (int j = 0; j < test.size(); j++) {
                if (test.get(j).part == knn.get(j).part) {
                    correctCount++;
                }
            }
            accuracySum += (double) correctCount / test.size();
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
        System.out.println("Accuracy: " + (accuracySum/k));
        
        answer = answer / size;
        answer2 = answer2 / size2;
        System.out.println(answer);
        System.out.println(answer2);
        ArrayList<Dot> dot0 = new ArrayList<>();
        ArrayList<Dot> dot1 = new ArrayList<>();
        for (Dot dot : data) {
            if (dot.part == 0) {
                dot0.add(dot);

            } else {
                dot1.add(dot);
            }
        }
        new Draw("x","y").addGraphic(dot0,"dot0").addGraphic(dot1,"dot1").show();
    }

    ArrayList<Dot> getTrain(int p, ArrayList<ArrayList<Dot>> partitions) {
        ArrayList<Dot> answer = new ArrayList<>();
        for (int i = 0; i < partitions.size(); i++) {
            if (i!=p) {
                answer.addAll(partitions.get(i));
            }
        }
        return answer;
    }
}
