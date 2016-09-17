import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class KNN {
    double distance(Dot a, Dot b) {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    ArrayList<Pair> culcDist(Dot testPoint, ArrayList<Dot> trainData) {
        ArrayList<Pair> answer = new ArrayList<Pair>();
        for (int i = 0; i< trainData.size(); i++) {
            answer.add(new Pair(trainData.get(i),distance(testPoint, trainData.get(i))));
        }
        return answer;
    }

    ArrayList<Label> classifyKNN(ArrayList<Dot> trainData, ArrayList<Dot> testData, int k) {
        ArrayList<Label> testLables = new ArrayList<>();
        for (Dot testPoint : testData) {
            ArrayList<Pair> testDist = culcDist(testPoint, trainData);
            Collections.sort(testDist);
            int[] stat = new int[2];
            for (int i = 0; i<k; i++) {
                stat[testDist.get(i).dot.lul]++;
            }
            testLables.add(new Label(testPoint, stat));
        }
        return testLables;
    }

    public class Label{
        Dot dot;
        int[] stat;

        public Label(Dot dot, int[] stat) {
            this.dot = dot;
            this.stat = stat;
        }
    }

    public class Pair implements Comparable<Pair>{
        Dot dot;
        double distance;

        public Pair(Dot dot, double distance) {
            this.dot = dot;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
