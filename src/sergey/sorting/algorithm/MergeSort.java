package sergey.sorting.algorithm;

public class MergeSort implements Sorter {

	private int[] data;
	private int[] mergeBuffer;
	private int min, max; //Inc, Exc
	private int merge1, merge2, mergeI;
	private int copyI;
	private MergeSort subMerge;
	private int step = 0;


	public MergeSort(int[] arr) {
		this(arr, 0, arr.length);
	}

	public MergeSort(int[] arr, int min, int max) {
		data = arr;
		this.min = min;
		this.max = max;
		merge1 = -1;
		merge2 = -1;
		copyI = -1;
	}

	@Override
	public void performNextStep() {
		if (step == 4) {
			return;
		}

		if (subMerge != null && subMerge.step != 4) {
			subMerge.performNextStep();
			return;
		}

		if (max-min <= 1) {
			step = 4;
			return;
		}

		if (step == 0) {
			step = 1;
			subMerge = new MergeSort(data, min, (max+min)/2);
			return;
		} else if (step == 1) {
			step = 2;
			subMerge = new MergeSort(data, (max+min)/2, max);
			merge1 = min;
			merge2 = (max+min)/2;
			return;
		} else if (step == 2) {
			subMerge = null;
			if (mergeBuffer == null) {
				mergeBuffer = new int[max-min];
			}
			if (merge1 < (max+min)/2 && merge2 < max) {
				if (data[merge1] < data[merge2]) {
					mergeBuffer[mergeI++] = data[merge1++];
				} else if (data[merge2] <= data[merge1]) {
					mergeBuffer[mergeI++] = data[merge2++];
				}
			} else {
				if (merge1 < (max+min)/2) {
					mergeBuffer[mergeI++] = data[merge1++];
				} else if (merge2 < max) {
					mergeBuffer[mergeI++] = data[merge2++];
				} else {
					step = 3;
					merge1 = -1;
					merge2 = -1;
					mergeI = -1;
					copyI = 0;
					return;
				}
			}

		} else if (step == 3) {
			if (copyI >= max-min) {
				step = 4;
				mergeBuffer = null;
				return;
			}
			data[copyI+min] = mergeBuffer[copyI++];
		} else if (step == 4) {
			//Done
			return;
		}
	}

	@Override
	public boolean isSelected(int index) {
		return !isComplete() && ((subMerge == null ? false : subMerge.isSelected(index)) || index == min || index == max || index == merge1 || index == merge2 || index == copyI+min);
	}

	@Override
	public boolean isSorted(int index) {
		return isComplete();
	}

	@Override
	public boolean isComplete() {
		return step == 4;
	}
	
	@Override
	public boolean hasExtraData() {
		return mergeBuffer != null || (subMerge != null && subMerge.hasExtraData());
	}
	
	@Override
	public int[] getExtraData() {
		return (mergeBuffer != null) ? mergeBuffer : subMerge.getExtraData();
	}
	
	@Override
	public int getExtraOffset() {
		return (mergeBuffer != null) ? min : subMerge.getExtraOffset();
	}
	
	@Override
	public int getExtraLength() {
		return (mergeBuffer != null) ? (step == 2 ? mergeI : mergeBuffer.length) : subMerge.getExtraLength();
	}
	
	@Override
	public int getExtraStart() {
		return ((step == 3) ? copyI : ((subMerge == null) ? 0 : subMerge.getExtraStart()));
	}
}
