package sergey.sorting;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import sergey.sorting.enums.Algorithm;
import sergey.sorting.enums.Condition;
import sergey.sorting.enums.DisplayMode;

public class Main {
	
	public static final int elements = 1024;
	public static final DisplayMode display = DisplayMode.BARS;
	public static final Algorithm algorithm = Algorithm.MERGE;
	public static final Condition condition = Condition.SHUFFLE;
	public static final int updatePerRestart = 180;
	public static final int updatePerSecond = 500;
	public static final boolean restart = true;
	
	public static final Color background = Color.BLACK;
	public static final Color dataPointNormal = Color.WHITE;
	public static final Color dataPointSelect = Color.RED;
	public static final Color dataPointSort = Color.GREEN;
	
	public static final Color extraDataColor = new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), 150);
	
	
	public static final Random rand = new Random();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting algorithms");
		
		Panel pan = new Panel();
		
		frame.add(pan);
		
		frame.setSize(1000, 1000);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Thread render = new Thread(()->{
			while (true) {
				try {
					Thread.sleep(1000/updatePerSecond);
				} catch (Exception e) {
					e.printStackTrace();
				}
				pan.repaint();
			}
		},"Render Thread");
		render.start();
	}
}
