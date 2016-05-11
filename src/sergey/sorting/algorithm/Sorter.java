package sergey.sorting.algorithm;

public interface Sorter {
	public void performNextStep();
	
	public boolean isSelected(int index);
	public boolean isSorted(int index);
	
	public boolean isComplete();

	public default boolean hasExtraData() {
		return false;
	}

	public default int[] getExtraData() {
		return null;
	}

	public default int getExtraOffset() {
		return 0;
	}
	
	public default int getExtraLength() {
		return 0;
	}

	public default int getExtraStart() {
		return 0;
	}
}
