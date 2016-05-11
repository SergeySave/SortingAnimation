package sergey.sorting.algorithm;

import sergey.sorting.util.ArrUtil;

public class SelectionSort implements Sorter {

	private int[] data;
	private int i, s;
	private int d;

	public SelectionSort(int[] arr) {
		data = arr;
		i = 1;
		s = 0;
		d = 0;
	}

	@Override
	public void performNextStep() {
		if (data[i] < data[s]) {
			s = i;
		}
		i++;
		if (i >= data.length) {
			ArrUtil.swap(data, d, s);
			d++;
			s = d;
			i=d;
		}
	}

	@Override
	public boolean isSelected(int index) {
		return index == i || index == s;
	}

	@Override
	public boolean isSorted(int index) {
		return index < d;
	}
	
	@Override
	public boolean isComplete() {
		return d >= data.length;
	}
}
