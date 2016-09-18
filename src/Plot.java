import com.xeiam.xchart.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ilya239 on 17.09.2016.
 */
public class Plot {

    private Chart chart;

    public Plot(String XAsis, String YAsis) {
        chart = new ChartBuilder().width(800).height(600).build();
        chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideNW);
        chart.setXAxisTitle(XAsis);
        chart.setYAxisTitle(YAsis);
    }

    public Plot addGraphic(List<Dot> points, String graphicName) {

        List<Double> xData = new LinkedList<>();
        List<Double> yData = new LinkedList<>();
        for (Dot point : points) {
            xData.add(point.x);
            yData.add(point.y);
        }
        chart.addSeries(graphicName, xData, yData).setLineColor(new Color(0, 0, 0, 0));

        return this;
    }

    public void show() {
        new SwingWrapper(chart).displayChart();
    }
}
