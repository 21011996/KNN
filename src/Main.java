import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Main {
                                        //75.33,78.6
    private static int foldNumber = 2; // 20,20
    private static int KNN_k = 5; // 15,27

    public static void main(String[] args) {
        new Main().run(foldNumber, KNN_k);
    }

    double run(int foldNumber, int KNN_k) {
        ArrayList<Dot> dataSet = new Reader().read("chips.txt");
        Collections.shuffle(dataSet);

        int partitionSize = dataSet.size() / foldNumber;
        ArrayList<ArrayList<Dot>> partitions = new ArrayList<>();
        for (int i = 0; i < dataSet.size(); i += partitionSize) {
            partitions.add(new ArrayList<>(dataSet.subList(i,
                    Math.min(i + partitionSize, dataSet.size()))));
        }

        double accuracySum = 0;
        for (int i = 0; i < foldNumber; i++) {
            ArrayList<Dot> trainingSet = getTrainingSet(i, partitions);
            ArrayList<Dot> testSet = new ArrayList<>(partitions.get(i));
            ArrayList<KNN.Label> knnResult = KNN.classifyKNN(trainingSet, testSet, partitionSize > KNN_k?KNN_k:partitionSize);

            int correctCount = 0;
            for (int j = 0; j < testSet.size(); j++) {
                if (testSet.get(j).type == knnResult.get(j).type) {
                    correctCount++;
                }
            }
            accuracySum += (double) correctCount / testSet.size();
        }

        //System.out.println("Accuracy: " + (accuracySum / foldNumber));

        ArrayList<Dot> dot0 = new ArrayList<>();
        ArrayList<Dot> dot1 = new ArrayList<>();
        for (Dot dot : dataSet) {
            if (dot.type == 0) {
                dot0.add(dot);

            } else {
                dot1.add(dot);
            }
        }
        //new Plot("x", "y").addGraphic(dot0, "dot0").addGraphic(dot1, "dot1").show();

        return (accuracySum / foldNumber);
    }

    private ArrayList<Dot> getTrainingSet(int excludeNumber, ArrayList<ArrayList<Dot>> partitions) {
        ArrayList<Dot> trainingSet = new ArrayList<>();
        for (int i = 0; i < partitions.size(); i++) {
            if (i != excludeNumber) {
                trainingSet.addAll(partitions.get(i));
            }
        }
        return trainingSet;
    }
}
