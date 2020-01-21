import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		int arg =5;
		int m = (int) Math.pow(2, arg);
		FibonacciHeap fh = new FibonacciHeap();
		System.out.println(m);
		FibonacciHeap.HeapNode[] arr = new FibonacciHeap.HeapNode[m+1];
		for(int i=0; i<m+1; i++) {
			arr[i] = fh.insert(i);
		}
		FiboHeapPrinter.Print(fh);
		System.out.println(fh.min.getKey());
		fh.deleteMin();
		
		FiboHeapPrinter.Print(fh);
		System.out.println(fh.min.getKey());
		
		
		Random rnd = new Random();
		for(int j=0; j<arg-1; j++) {
			int index = (int) (m*Math.pow(0.5, j) 
					);
			System.out.println(index);
			int delta = rnd.nextInt(m);
			fh.decreaseKey(arr[index], delta);
			System.out.println("Delta: "+ delta);
			System.out.println("m = " + index);
			System.out.println("total links: " + fh.totalLinks());
			System.out.println("total cuts: " + fh.totalCuts());
			System.out.println("potential: " + fh.potential());
			FiboHeapPrinter.Print(fh);
		}
		int delta = rnd.nextInt(m);
		fh.decreaseKey(arr[m-1], delta);
		System.out.println("m-1 = " + (m-1));
		System.out.println("total links" + fh.totalLinks());
		System.out.println("total cuts" + fh.totalCuts());
		System.out.println("potential" + fh.potential());
	}

}