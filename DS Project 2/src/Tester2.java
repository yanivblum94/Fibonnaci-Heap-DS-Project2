import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Tester2 {

	public static void main(String[] args) {
		double start = System.currentTimeMillis();
		int arg =12;
		int m = 1000;
		FibonacciHeap fh = new FibonacciHeap();
		System.out.println("running for m=" +m);
		Map<Integer,FibonacciHeap.HeapNode> nodes = new HashMap<Integer,FibonacciHeap.HeapNode>();
		for(int i=0; i<m+1; i++) {
			nodes.put(i,fh.insert(i));
		}
		fh.decreaseKey(nodes.get(15), 20);
		//System.out.println(Arrays.deepToString(arr));
		FiboHeapPrinter.Print(fh);
		System.out.println(fh.min.getKey());
		nodes.remove(fh.findMin().getKey());
		fh.deleteMin();
		
		//FiboHeapPrinter.Print(fh);
		System.out.println(fh.min.getKey());
		/**
		
		//Random rnd = new Random();
		for(int j=0; j<((m/2) +1); j++) {
			fh.deleteMin();
			System.out.print(j+1 + "  ");
		}
		//fh.decreaseKey(nodes.get(m-1), 5000);
		System.out.println("total links   " + fh.totalLinks());
		System.out.println("total cuts   " + fh.totalCuts());
		System.out.println("potential   " + fh.potential());
		double finish = System.currentTimeMillis();
		double timeElapsed = finish - start;
		System.out.println("time elapsed:  "+ timeElapsed);
		**/
	}
}


