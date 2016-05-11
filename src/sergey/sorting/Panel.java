package sergey.sorting;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import sergey.sorting.algorithm.Sorter;
import sergey.sorting.enums.Condition;
import sergey.sorting.util.ArrUtil;

public class Panel extends JPanel {
	private static final long serialVersionUID = -4295858378060952627L;

	private int[] data;
	private Sorter sorter;

	private int count = 0;

	public Panel() {
		init();
	}
	
	private void init() {
		data = new int[Main.elements];
		for (int i = 0; i<data.length; i++) {
			data[i] = Main.condition.getValue(i, data.length);
		}
		
		if (Main.condition == Condition.SHUFFLE) {
			for (int i = 0; i<data.length; i++) {
				ArrUtil.swap(data, i, Main.rand.nextInt(data.length));
			}
		} else if (Main.condition == Condition.NEARLY_SHUFFLE) {
			for (int i = 0; i<data.length; i++) {
				ArrUtil.swap(data, i, Math.max(0, Math.min(data.length-1, i - 4 + Main.rand.nextInt(8))));
			}
		}

		sorter = Main.algorithm.getFunc().apply(data);
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;

		g.setColor(Main.background);
		g.fillRect(0, 0, getWidth(), getHeight());

		double w = getWidth()/(double)data.length;
		double h = getHeight()/((double)data.length+1);

		for (int i = 0; i<data.length; i++) {
			int x = (int) (i * w);
			int y = getHeight() - (int) ((data[i]+1) * h);

			if (sorter.isSelected(i)) {
				g.setColor(Main.dataPointSelect);
			} else if (sorter.isSorted(i)) {
				g.setColor(Main.dataPointSort);
			} else {
				g.setColor(Main.dataPointNormal);
			}

			switch (Main.display){
				case BARS:
					g.fillRect(x, y, (int)Math.ceil(w), (int)Math.ceil((data[i]+1) * h));
					break;
				case DOTS:
					g.fillRect(x, y, (int)Math.ceil(w), (int)Math.ceil(h));
					break;
			}
		}

		if (sorter.hasExtraData()) {
			int[] extraData = sorter.getExtraData();
			int offset = sorter.getExtraOffset();
			System.out.println(sorter.getExtraLength());
			
			for (int i = sorter.getExtraStart(); i<sorter.getExtraLength(); i++) {
				int x = (int) ((i+offset) * w);
				int y = getHeight() - (int) ((extraData[i]+1) * h);

				g.setColor(Main.extraDataColor);

				switch (Main.display){
					case BARS:
						g.fillRect(x, y, (int)Math.ceil(w), (int)Math.ceil((extraData[i]+1) * h));
						break;
					case DOTS:
						g.fillRect(x, y, (int)Math.ceil(w), (int)Math.ceil(h));
						break;
				}
			}
		}

		if (sorter.isComplete()) {
			if (count >= Main.updatePerRestart) {
				if (Main.restart) {
					init();
				}
				count = 0;
			} else {
				count++;
			}
		} else {
			sorter.performNextStep();
		}
	}
}
