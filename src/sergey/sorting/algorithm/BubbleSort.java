package sergey.sorting.algorithm;

import sergey.sorting.util.ArrUtil;

public class BubbleSort implements Sorter {

	private int[] data;
	private int i;
	private int lI;

	public BubbleSort(int[] arr) {
		data = arr;
		i = 0;
		lI = arr.length-1;
	}

	@Override
	public void performNextStep() {
		if (i < lI) {
			if (data[i] > data[i+1]) {
				ArrUtil.swap(data, i, i+1);
			}
			i++;
		} else {
			if (lI < 0) {
				i = -1;
			} else {
				i = 0;
				lI--;
			}
		}
	}

	@Override
	public boolean isSelected(int index) {
		return index == i;
	}

	@Override
	public boolean isSorted(int index) {
		return index > lI;
	}
	
	@Override
	public boolean isComplete() {
		return lI < 0;
	}

}
