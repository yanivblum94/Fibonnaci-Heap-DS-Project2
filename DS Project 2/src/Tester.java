import java.util.Arrays;
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
			//System.out.println(arr[i].getRight().getKey());
		}
		//fh.decreaseKey(arr[15], 20);
		//System.out.println(Arrays.deepToString(arr));
		//FiboHeapPrinter.Print(fh);
		//System.out.println(fh.min.getKey());
		fh.deleteMin();
		
		FiboHeapPrinter.Print(fh);
		System.out.println(fh.min.getKey());
		FibonacciHeap fh2 = fh;
		System.out.println(Arrays.toString(FibonacciHeap.kMin(fh2, 10)));

}}