package sergey.sorting.algorithm;

import sergey.sorting.util.ArrUtil;

public class HeapSort implements Sorter {

	private int[] data;
	private int lastIndex;
	private int heapifyIndex;
	private int toHeapify;
	private int step = 0;
	private int indexRepairHeap = -1;

	public HeapSort(int[] arr) {
		data = arr;
		lastIndex = arr.length-1;
		heapifyIndex = 1;
		toHeapify = -1;
	}

	@Override
	public void performNextStep() {
		if (lastIndex <= 0) {
			lastIndex = -1;
			return;
		}
		if (step == 0) {
			doHeapifyStep();
			return;
		} else if (step == 1) {
			heapifyIndex = -1;
			toHeapify = -1;
			ArrUtil.swap(data, 0, lastIndex--);
			step = 2;
			indexRepairHeap = 0;
			return;
		} else if (step == 2) {
			int child = getLargestChild(indexRepairHeap);
			if (child == -1) {
				step = 1;
				indexRepairHeap = -1;
			} else {
				if (data[indexRepairHeap] > data[child]) {
					step = 1;
					indexRepairHeap = -1;
				} else {
					ArrUtil.swap(data, indexRepairHeap, child);
					indexRepairHeap = child;
				}
			}
			return;
		}
	}

	private boolean doHeapifyStep() {
		if ((heapifyIndex <= lastIndex || toHeapify != -1) && heapifyIndex > 0) {
			int check;
			if (toHeapify != -1) {
				check = toHeapify;
			} else {
				check = heapifyIndex++;
			}
			int parent = getParent(check);
			if (data[check] > data[parent]) {
				ArrUtil.swap(data, check, parent);
				if (parent == 0) {
					toHeapify = -1;
				} else {
					toHeapify = parent;
				}
			} else {
				toHeapify = -1;
			}
			return true;
		}
		heapifyIndex = -1;
		step = 1;
		return false;
	}

	private int getParent(int index) {
		if (index % 2 == 1) {
			index--;
		} else {
			index -= 2;
		}
		
		return index/2;
	}

	private int getLargestChild(int index) {
		int child1 = 2 * index + 1;
		int child2 = child1+1;
		if (child1 > lastIndex) {
			return -1;
		}
		if (child2 > lastIndex || data[child1] > data[child2]) {
			return child1;
		} else {
			return child2;
		}
	}

	@Override
	public boolean isSelected(int index) {
		return !isComplete() && (index == heapifyIndex || index == indexRepairHeap || index == toHeapify);
	}

	@Override
	public boolean isSorted(int index) {
		return index > lastIndex;
	}

	@Override
	public boolean isComplete() {
		return lastIndex < 0;
	}
}
