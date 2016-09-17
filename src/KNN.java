import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class KNN {
    //TODO add more metrics
    static double distance(Dot a, Dot b) {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    static ArrayList<Pair> culcDist(Dot testPoint, ArrayList<Dot> trainData) {
        ArrayList<Pair> answer = new ArrayList<Pair>();
        for (int i = 0; i< trainData.size(); i++) {
            answer.add(new Pair(trainData.get(i),distance(testPoint, trainData.get(i))));
        }
        return answer;
    }

    public static ArrayList<Label> classifyKNN(ArrayList<Dot> trainData, ArrayList<Dot> testData, int k) {
        ArrayList<Label> testLables = new ArrayList<>();
        for (Dot testPoint : testData) {
            ArrayList<Pair> testDist = culcDist(testPoint, trainData);
            Collections.sort(testDist);
            int[] stat = new int[2];
            for (int i = 0; i<k; i++) {
                stat[testDist.get(i).dot.lul]++;
            }
            double kek = 0;
            if (stat[0]>stat[1]) {
                for (int i = k-1; i>=0; i--) {
                    if (testDist.get(i).dot.lul == 0) {
                        kek = testDist.get(i).distance;
                    }
                }
            } else {
                for (int i = k-1; i>=0; i--) {
                    if (testDist.get(i).dot.lul == 1) {
                        kek = testDist.get(i).distance;
                    }
                }
            }
            testLables.add(new Label(testPoint, stat, kek));
        }
        return testLables;
    }

    public static class Label{
        Dot dot;
        int[] stat;
        double distance;

        public Label(Dot dot, int[] stat, double distance) {
            this.dot = dot;
            this.stat = stat;
            this.distance = distance;
        }
    }

    public static class Pair implements Comparable<Pair>{
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
