import java.util.ArrayList;
import java.util.List;

/**
 * FibonacciHeap
 *
 * An implementation of fibonacci heap over integers.
 */
public class FibonacciHeap
{
	public HeapNode first_root;//list of the roots of the trees in the heap
	public int num_of_roots;
	public HeapNode min;//pointer to the minimum node
	public int size;
	public int total_cuts;
	public int total_links;
	public int marked_nodes;
	
	/**
	 * constructor of the heap
	 * creates a new list of roots
	 * O(1)
	 */	
	
	
	
	
	public FibonacciHeap() {
		this.num_of_roots=0;
		this.min=null;
		this.first_root=null;
		this.size=0;
		this.total_links=0;
		this.total_cuts=0;
		this.marked_nodes=0;
	}

   /**
    * public boolean isEmpty()
    *
    * precondition: none
    * 
    * The method returns true if and only if the heap
    * is empty.
    * O(1)
    */
    public boolean isEmpty()
    {
    	return (this.num_of_roots==0);
    }
		
   /**
    * public HeapNode insert(int key)
    *
    * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap. 
    */
    public HeapNode insert(int key)
    {   
    	HeapNode node = new HeapNode(key);
    	if(this.first_root==null) {
    		this.first_root=node;
    		this.min=node;
    		this.first_root.left=this.first_root;
    		this.first_root.right=this.first_root;
    	}
    	else {
    		if (key<this.min.key) {
    			this.min=node;
    		}
    		HeapNode last=this.first_root.left;
    		last.right=node;
    		node.left=last;
    		node.right=this.first_root;
    		this.first_root.left=node;
    		this.first_root=node;
    	}
    	this.num_of_roots++;
    	this.size++;
    	return node;
    }

   /**
    * public void deleteMin()
    *
    * Delete the node containing the minimum key.
    *
    */
    public void deleteMin()
    {
     	return; // should be replaced by student code
     	
    }

   /**
    * public HeapNode findMin()
    *
    * Return the node of the heap whose key is minimal. 
    *O(1)
    */
    public HeapNode findMin()
    {
    	return this.min;
    } 
    
   /**
    * public void meld (FibonacciHeap heap2)
    *
    * Meld the heap with heap2
    *
    */
    public void meld (FibonacciHeap heap2)
    {
    	  this.first_root.left=heap2.first_root;
    	  HeapNode last2=heap2.first_root.left;
    	  heap2.first_root.left=this.first_root.left;
    	  last2.right=this.first_root;
    	  this.first_root.left=last2;
    	  this.size+=heap2.size();
    	  this.num_of_roots+=heap2.num_of_roots;
    	  this.marked_nodes+=heap2.marked_nodes;
    	  if (heap2.findMin().getKey()<this.min.getKey()) {
    		  this.min=heap2.findMin();
    	  }
    	  
    }

   /**
    * public int size()
    *
    * Return the number of elements in the heap
    *   
    */
    public int size()
    {
    	return this.size; // should be replaced by student code
    }
    	
    /**
    * public int[] countersRep()
    *
    * Return a counters array, where the value of the i-th entry is the number of trees of order i in the heap. 
    * 
    */
    private int log2up(int x) {
    	return (int)(Math.log(x)/Math.log(2));
    }
    public int[] countersRep()
    {
    	int [] res=new int [(int)(1.44*log2up(this.size))+1];
    	if (this.isEmpty()) {
    		return res;
    	}
    	HeapNode cur=this.first_root;
    	do
    	{
    		res[cur.rank]++;
    		cur=cur.right;
    	}while (cur!=this.first_root);
        return res; //	 to be replaced by student code
    }
	
   /**
    * public void delete(HeapNode x)
    *
    * Deletes the node x from the heap. 
    *
    */
    public void delete(HeapNode x) 
    {    
    	this.decreaseKey(x, Integer.MIN_VALUE);
    	this.deleteMin();
    }

   /**
    * public void decreaseKey(HeapNode x, int delta)
    *
    * The function decreases the key of the node x by delta. The structure of the heap should be updated
    * to reflect this chage (for example, the cascading cuts procedure should be applied if needed).
    */
    public void decreaseKey(HeapNode x, int delta)
    {    
    	
    	return; // should be replaced by student code
    }

   /**
    * public int potential() 
    *
    * This function returns the current potential of the heap, which is:
    * Potential = #trees + 2*#marked
    * The potential equals to the number of trees in the heap plus twice the number of marked nodes in the heap. 
    */
    public int potential() 
    {    
    	return ((this.num_of_roots)+(2*this.marked_nodes));
    }

   /**
    * public static int totalLinks() 
    *
    * This static function returns the total number of link operations made during the run-time of the program.
    * A link operation is the operation which gets as input two trees of the same rank, and generates a tree of 
    * rank bigger by one, by hanging the tree which has larger value in its root on the tree which has smaller value 
    * in its root.
    */
    public static int totalLinks()
    {    
    	return 0; // should be replaced by student code
    }

   /**
    * public static int totalCuts() 
    *
    * This static function returns the total number of cut operations made during the run-time of the program.
    * A cut operation is the operation which diconnects a subtree from its parent (during decreaseKey/delete methods). 
    */
    public static int totalCuts()
    {    
    	return 0; // should be replaced by student code
    }

     /**
    * public static int[] kMin(FibonacciHeap H, int k) 
    *
    * This static function returns the k minimal elements in a binomial tree H.
    * The function should run in O(k(logk + deg(H)). 
    */
    public static int[] kMin(FibonacciHeap H, int k)
    {    
        int[] arr = new int[42];
        return arr; // should be replaced by student code
    }
    
   /**
    * public class HeapNode
    * 
    * If you wish to implement classes other than FibonacciHeap
    * (for example HeapNode), do it in this file, not in 
    * another file 
    *  
    */
    public class HeapNode{

	public int key; //node key
	public boolean marked; //marks if the node lost a child
	public HeapNode child, parent, left, right;//pointers to nodes neighbors
	public int rank;

  	public HeapNode(int key) {
  		//constructor' O(1)
	    this.key = key;
	    this.marked = false;
	    this.rank=0;
	    this.parent=null;
	    this.child=null;
      }

  	public int getKey() {//O(1)
	    return this.key;
      }
  	public void setKey(int k) {//O(1)
  		this.key = k;
  	}
  	public HeapNode getChild() {//O(1)
  		return this.child;
  	}
  	public HeapNode getParent() {//O(1)
  		return this.parent;
  	}
  	public HeapNode getLefy() {//O(1)
  		return this.left;
  	}
  	public HeapNode getRight() {//O(1)
  		return this.right;
  	}
  	public boolean getMarked() {//O(1)
  		return this.marked;
  	}
  	public void setChild(HeapNode node) {//O(1)
  		this.child = node;
  	}
  	public void setParent(HeapNode node) {//O(1)
  		this.parent = node;
  	}
  	public void setLeft(HeapNode node) {//O(1)
  		this.left = node;
  	}
  	public void setRight(HeapNode node) {//O(1)
  		this.right = node;
  	}
  	public void setMarked(boolean bool) {//O(1)
  		this.marked = bool;
  	}
    }
}
