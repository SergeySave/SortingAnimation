package sergey.sorting.algorithm;

import sergey.sorting.util.ArrUtil;

public class QuickSort implements Sorter {

	private int[] data;
	private int oL, oH;
	private int low, high; //Inc, Exc
	private QuickSort sub;
	private int step = 0;
	private int pivotIndex = -1;

	public QuickSort(int[] arr) {
		this(arr, 0, arr.length-1);
	}

	public QuickSort(int[] arr, int min, int max) {
		data = arr;
		this.low = min;
		this.high = max;
		oL = min;
		oH = max;
	}

	@Override
	public void performNextStep() {
		if (sub != null && sub.step != 5) {
			sub.performNextStep();
			return;
		}

		if (step == 0) {
			if (low >= high) {
				step = 5;
				return;
			}

			step = 1;
			pivotIndex = (low+high)/2;
		}
		if (step == 1) {
			if (low < high) {
				if (data[low] < data[pivotIndex]) {
					low++;
					return;
				} else if (data[pivotIndex] < data[high]) {
					high--;
					return;
				} else {
					if (low < high) {
						if (pivotIndex == low) {
							pivotIndex = high;
						} else if (pivotIndex == high) {
							pivotIndex = low;
						}
						ArrUtil.swap(data, low, high);
						low++;
						high--;
						return;
					} else if (low == high) {
						low++;
						high--;
						return;
					}
				}
			} else {
				step = 2;
			}
		} else if (step == 2) {
			step = 3;
			if (high != oH && high>oL && high-oL > 0) {
				sub = new QuickSort(data, oL, high);
				return;
			}
		} else if (step == 3) {
			step = 4;
			if (low != oL && oH > low && oH-low > 0) {
				sub = new QuickSort(data, low, oH);
				return;
			}
		} else if (step == 4) {
			step = 5;
			return;
		}
	}

	@Override
	public boolean isSelected(int index) {
		return !isComplete() && (index == pivotIndex || index == low || index == high || (sub != null ? sub.isSelected(index) : false));
	}

	@Override
	public boolean isSorted(int index) {
		return isComplete();
	}

	@Override
	public boolean isComplete() {
		return step == 5;
	}
}
