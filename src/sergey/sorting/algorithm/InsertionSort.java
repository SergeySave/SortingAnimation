package sergey.sorting.algorithm;

import sergey.sorting.util.ArrUtil;

public class InsertionSort implements Sorter {

	private int[] data;
	private int i, j;
	private int d;

	public InsertionSort(int[] arr) {
		data = arr;
		i = 1;
		j = 0;
		d = 1;
	}

	@Override
	public void performNextStep() {
		if (d >= data.length) {
			i = -1;
			j = -1;
			return;
		}
		if (i == j) {
			i++;
			d++;
			return;
		}
		if (j < 0 || data[j] <= data[j+1]) {
			j = i;
			return;
		}
		if (data[j] > data[j+1]) {
			ArrUtil.swap(data, j, j+1);
			j--;
			return;
		}
	}

	@Override
	public boolean isSelected(int index) {
		return index == i || index == j;
	}

	@Override
	public boolean isSorted(int index) {
		return index < d && index != j+1;
	}
	
	@Override
	public boolean isComplete() {
		return i == -1;
	}

}
