package bloomfilter;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
    // m=16588594 n=2^20 f=0.0005 k=11
	private int size = 16588594;

	//determine the number of hash function (different seeds)
	private int[] seeds = new int[]{3, 5, 7, 11, 13, 17, 29, 31 ,37, 61, 97};

	private BitSet bits = new BitSet(size);

	public BloomFilter() {}

	//add an element to Bloom Filter
	public void add(String str) {
        if(str != null){
            for (int i = 0; i < 11; i++) {
                bits.set(hash(seeds[i],str),true);
            }
        }
	}

	//query whether Bloom Filter contains the element
	public boolean query(String str) {
        for (int i = 0; i < 11; i++) {
            if (bits.get(hash(seeds[i],str)))
                return false;
        }
        return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
	    int result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = seed * result + str.charAt(i);
        }
		return (size - 1) & result;
	}

	
}
