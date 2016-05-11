package sergey.sorting.enums;

import java.util.function.Function;

import sergey.sorting.algorithm.BubbleSort;
import sergey.sorting.algorithm.HeapSort;
import sergey.sorting.algorithm.InsertionSort;
import sergey.sorting.algorithm.MergeSort;
import sergey.sorting.algorithm.QuickSort;
import sergey.sorting.algorithm.SelectionSort;
import sergey.sorting.algorithm.Sorter;

public enum Algorithm {
	BUBBLE((arr)->new BubbleSort(arr)),
	INSERTION((arr)->new InsertionSort(arr)), 
	SELECTION((arr)->new SelectionSort(arr)), 
	MERGE((arr)->new MergeSort(arr)), 
	QUICK((arr)->new QuickSort(arr)),
	HEAP((arr)->new HeapSort(arr));
	
	private Function<int[], Sorter> func;
	
	private Algorithm(Function<int[], Sorter> f) {
		func = f;
	}
	
	public Function<int[], Sorter> getFunc() {
		return func;
	}
}
