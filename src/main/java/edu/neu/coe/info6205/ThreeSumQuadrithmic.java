package edu.neu.coe.info6205;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadrithmic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadrithmic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
    	
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
//        System.out.println(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        
        for(int i=j+1;i<length-1;i++) {
        	int sum=0-a[j]-a[j];
        	int l=i+1;
        	int h=length-1;
        	while(l<h) {
        		int mid=l+(h-l)/2;
        		if(a[mid]==sum) {
        			triples.add(new Triple(a[j], a[i],a[mid]));
        			break;
        		}
        		else if(sum>a[mid]) {
        			l=mid+1;
        		}else
        			h=mid-1;
        		
        	}
        }
        // END 
        return triples;
    }

    private final int[] a;
    private final int length;
}