package sergey.sorting.enums;

import static sergey.sorting.Main.rand;

import java.util.function.BiFunction;

public enum Condition {
	SORTED((i,s)->i),
	RANDOM((i,s)->rand.nextInt(s)),
	SHUFFLE((i,s)->i),
	NEARLY_SHUFFLE((i,s)->i),
	NEARLY_SORTED((i,s)->Math.max(0,Math.min(s-1,i + rand.nextInt(4)-2))),
	REVERSE((i,s)->s-i-1),
	NEARLY_SORTED_REVERSE((i,s)->Math.max(0,Math.min(s-1,s-i-1 + rand.nextInt(4)-2))),
	FEW_UNIQUE((i,s)->((int)i/s*10)*s/10);
	
	private BiFunction<Integer, Integer, Integer> generator;
	
	private Condition(BiFunction<Integer, Integer, Integer> gen) {
		generator = gen;
	}
	
	public int getValue(int i, int size) {
		return generator.apply(i, size);
	}
}
