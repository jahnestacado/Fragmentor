package nl.cwi.bfd.algorithm;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Graphs {

	public static void getHistogram(List<Double> dataSet, String type) {
		int size = dataSet.size();
		double[] value = new double[size];
		for (int i = 1; i < size - 1; i++) {
			value[i] = dataSet.get(i);
		}
		int number = 80;
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.FREQUENCY);
		dataset.addSeries("Histogram", value, number);
		
		String plotTitle = type.toUpperCase()+" Individual Null Bytes Distribution";
		String xaxis = "Individual Null Bytes";
		String yaxis = "Frequency";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean toolTips = false;
		boolean urls = false;
		JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis,
				yaxis, dataset, orientation, show, toolTips, urls);
		
	      
		int width = 500;
		int height = 300;
		try {
			ChartUtilities.saveChartAsPNG(new File(
					"/home/jahn/Desktop/Null"+type+".png"), chart, width,
					height);
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
}