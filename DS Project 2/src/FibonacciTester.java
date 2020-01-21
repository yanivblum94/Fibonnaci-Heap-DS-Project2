import java.util.Random;

public class FibonacciTester {

	public static void main(String[] args) {
		int arg =10;
		int m = (int) Math.pow(2, arg);
		FibonacciHeap fh = new FibonacciHeap();
		FibonacciHeap.HeapNode[] arr = new FibonacciHeap.HeapNode[m+1];
		for(int i=0; i<m+1; i++) {
			arr[i] = fh.insert(i);
		}
		fh.deleteMin();
		Random rnd = new Random();
		for(int j=0; j<arg-1; j++) {
			int index = (int) (m*Math.pow(0.5, j));
			int delta = rnd.nextInt(m);
			fh.decreaseKey(arr[index], delta);
			System.out.println("m = " + index);
			System.out.println("total links" + fh.totalLinks());
			System.out.println("total cuts" + fh.totalCuts());
			System.out.println("potential" + fh.potential());
		}
		int delta = rnd.nextInt(m);
		fh.decreaseKey(arr[m-1], delta);
		System.out.println("m-1 = " + (m-1));
		System.out.println("total links" + fh.totalLinks());
		System.out.println("total cuts" + fh.totalCuts());
		System.out.println("potential" + fh.potential());
	}

}
