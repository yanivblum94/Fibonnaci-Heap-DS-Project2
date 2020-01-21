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
	public int num_of_roots; // num of trees in the heap. for the potential func
	public HeapNode min;//pointer to the minimum node
	public int size;//for the size func
	public static int total_cuts;//for total cuts func
	public static int total_links;// for total links func
	public int marked_nodes;// for the potential func
	
	/**
	 * constructor of the heap
	 * creates a new list of roots
	 * O(1)
	 */	

	public FibonacciHeap() {//puts defaultic values to all the fields
		this.num_of_roots=0;
		this.min=null;
		this.first_root=null;
		this.size=0;
		FibonacciHeap.total_links=0;
		FibonacciHeap.total_cuts=0;
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
    * O(1)
    */
    public HeapNode insert(int key)
    {   
    	HeapNode node = new HeapNode(key);
    	if(this.first_root==null) {//checks if the heap is empty
    		this.first_root=node;
    		this.min=node;
    		this.first_root.left=this.first_root;
    		this.first_root.right=this.first_root;
    	}
    	else {
    		if (key<this.min.key) {//updates the min pointer
    			this.min=node;
    		}
    		//pointers fix
    		HeapNode last=this.first_root.left;
    		last.right=node;
    		node.left=last;
    		node.right=this.first_root;
    		this.first_root.left=node;
    		this.first_root=node;
    	}
    	//fields update
    	this.num_of_roots++;
    	this.size++;
    	return node;
    }
    /**
     *  public static void merge_roots(HeapNode root1, HeapNode root2)
     *
     * Merges lists of roots of trees. being used when cutting nodes
     * @pre: roots are not null
     * O(1)
     */
    public static void merge_roots(HeapNode root1, HeapNode root2) {//pointers fixing
    	root1.left.right=root2;
  	  HeapNode last2=root2.left;
  	  root2.left=root1.left;
  	  last2.right=root1;
  	  root1.left=last2;
    }
    /**
     *  public static void link(HeapNode parent,HeapNode child)
     *
     * links 2 trees with rank K into one with rank K+1
     * @pre: Nodes are not null and both trees ranked K
     * O(1)
     */
    public static void link(HeapNode parent,HeapNode child) {
    	//needs to check which one is bigger
    	HeapNode original_child=parent.child;
    	HeapNode temp;
    	parent.child=child;
    	child.parent=parent;
    	while (original_child!=null) {
    		merge_roots(child, original_child);
    		temp=original_child;
    		original_child=temp.child;
    		temp.child=null;
    		child=child.child;
    	}
    	//fields update
    	parent.rank++;
    	
    	
    }

   /**
    * public void deleteMin()
    *
    * Delete the node containing the minimum key.
    * amort O(logn)
    */
    public void deleteMin()
    {
    	if (this.num_of_roots==0) {
    		return;
    	}
    	if (this.num_of_roots==1) {
    		this.first_root=null;
    		this.min=null;
    		this.size=0;
    	}
    	HeapNode min_child=min.child;
    	if (this.first_root==this.min) {
    		this.first_root=this.first_root.right;
    	}
    	//hop over
    	this.min.right.left=this.min.left;
    	this.min.left.right=this.min.right;
    	
    	this.size--;
    	if (min_child!=null)
    		merge_roots(this.first_root, min_child);
    	
    	
		
    	HeapNode [] consol= new HeapNode [fib_heap_size(this.size())];
    	HeapNode index=this.first_root;
    	HeapNode cur;
    	int current_rank;
    	do {
    		cur=index.clone();
    		while (consol[cur.rank]!=null) {
    			current_rank=cur.rank;
    			if (cur.getKey()<consol[cur.rank].getKey()) {
    				link(cur,consol[cur.rank]);//correct take minimum
    			}
    			else {
    				link(consol[cur.rank],cur);//correct take minimum
    				cur=consol[cur.rank];
    			}
    			consol[current_rank]=null;
    			
    			
    			FibonacciHeap.total_links++;
    		}
    		cur.left=cur;
    		cur.right=cur;
    		consol[cur.rank]=cur;
    		index=index.right;
    	}while (index!=first_root);
    	
    	this.num_of_roots=0;
    	boolean first=true;
    	for (int i=0;i<consol.length;i++) {
    		if (consol[i]!=null) {
    			if (first) {
    				this.min=consol[i];
    				this.first_root=consol[i];
    				first=false;
    				cur=consol[i];
    				this.num_of_roots++;
    			}
    			else {
    				if (consol[i].key<this.min.key) {
    					this.min=consol[i];
    				}
    				cur.right=consol[i];
    				cur.right.left=cur;
    				this.num_of_roots++;
    			}
    		}
    	}
    	cur.right=this.first_root;
    	this.first_root.left=cur;
     	
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
    * O(1)
    */
    public void meld (FibonacciHeap heap2)
    {
    	  //pointers fix
    	  this.first_root.left.right=heap2.first_root;
    	  HeapNode last2=heap2.first_root.left;
    	  heap2.first_root.left=this.first_root.left;
    	  last2.right=this.first_root;
    	  this.first_root.left=last2;
    	  //fields update
    	  this.size+=heap2.size();
    	  this.num_of_roots+=heap2.num_of_roots;
    	  this.marked_nodes+=heap2.marked_nodes;
    	  FibonacciHeap.total_cuts+=FibonacciHeap.total_cuts;
    	  FibonacciHeap.total_links+=FibonacciHeap.total_links;
    	  if (heap2.findMin().getKey()<this.min.getKey()) {//min pointer update
    		  this.min=heap2.findMin();
    	  }
    	  
    }

   /**
    * public int size()
    *
    * Return the number of elements in the heap
    *   O(1)
    */
    public int size()
    {
    	return this.size; 
    }
    /**
     * private int fib_heap_size(int x)
     * @pre: x is the size
     * returns the size of FIB heap according to the formula learned
     * O(1)
     */
    private int fib_heap_size(int x) {
    	return (int)(1.44*(Math.log(x)/Math.log(2)))+1;
    }
    /**
    * public int[] countersRep()
    *
    * Return a counters array, where the value of the i-th entry is the number of trees of order i in the heap. 
    * amort O(logn)
    */
    
    public int[] countersRep()
    {
    	int [] res=new int [fib_heap_size(this.size())];
    	if (this.isEmpty()) {
    		return res;
    	}
    	HeapNode cur=this.first_root;
    	do
    	{
    		res[cur.rank]++;
    		cur=cur.right;
    	}while (cur!=this.first_root);//runs on all the trees in the heap
        return res; 
    }
	
   /**
    * public void delete(HeapNode x)
    *
    * Deletes the node x from the heap. 
    * amort O(logn) 
    */
    public void delete(HeapNode x) 
    {    
    	this.decreaseKey(x, Integer.MIN_VALUE);//make the node the smallest in the heap
    	this.deleteMin();
    }

   /**
    * public void decreaseKey(HeapNode x, int delta)
    *
    * The function decreases the key of the node x by delta. The structure of the heap should be updated
    * to reflect this chage (for example, the cascading cuts procedure should be applied if needed).
    * O(1) amort
    */
    public void decreaseKey(HeapNode x, int delta)
    {    
    	if(x.getParent() == null) {// if the node is a root do nothing besides decreasing
    		x.setKey(x.getKey()-delta);
    		return;
    	}
    	int n = x.getKey();
    	x.setKey(n-delta);
    	if(x.getKey()<x.getParent().getKey()) {//see if a change needs to be done
    		return;
    	}
    	else {
    		cut(x);
    		cascadingCut(x);
    		if(x.getKey()<this.min.getKey()) {
    			this.min = x;
    		}
    	}
    }
    /**
     * public void cut(HeapNode node)
     * The function cuts the node node from its location.
     * @complexity O(1) 
     * @pre node has parent
     * @post the node is no longer linked to its parent
     */
    private void cut(HeapNode node){
    	if(node.marked ){
    		node.setMarked(false);
    		this.marked_nodes-= 1;
    	}
    	this.removeNodeFromNodesList(node);//cut the node from the list
    	FibonacciHeap.total_cuts ++;//update the cutting field
    	this.merge_roots(node, this.first_root);//add the node as a root
    }
    /**
     * public void cascadingCut(HeapNode node)
     * The function performs a cascading cut.
     * @complexity - O(log(n)) amort
     * @pre HeapNode node exists
     * @post cascading cut was performed, if needed.
     */
    
    public void cascadingCut(HeapNode node){
    	HeapNode par = node.getParent();
    	if(par!= null) { // node is not a root
    		if(node.getMarked()) {
    			node.setMarked(false);//mark the node we are cutting
    			this.marked_nodes--;//update the field
    			this.cut(node);//cut the node
    			cascadingCut(par);//recursive call the the parent
    		}
    		else {
    			node.setMarked(true);//mark the node
    			this.marked_nodes++;//update the field
    		}
    	}
    }
    /**
     * public void removeNodeFromNodesList(HeapNode node)
     * The function removes the node from siblings linked list and from parent.
     * @complexity - O(1)
     * @pre HeapNode node exists
     * @post HeapNode node is no longer linked to its siblings and parent.
     */
    public void removeNodeFromNodesList(HeapNode node){
    	if(node.getParent()!= null){//if the node isn't a root
    		node.getParent().rank --;
    		if(node.getParent().getChild() == node){
    			if(node.getRight() == node){
    				node.getParent().setChild(null);
    			}else{
    				node.getParent().setChild(node.getRight());
    			}
    		}
    	}else{
    		num_of_roots -= 1;
    	}
    	// fixing pointers of siblings
    	node.right.left = node.left;
    	node.left.right = node.right;
    	// link the node to the roots list
    	this.merge_roots(node, this.first_root);
    }
   /**
    * public int potential() 
    *
    * This function returns the current potential of the heap, which is:
    * Potential = #trees + 2*#marked
    * The potential equals to the number of trees in the heap plus twice the number of marked nodes in the heap. 
    * O(1)
    */
    public int potential() 
    {    
    	return ((this.num_of_roots)+(2*this.marked_nodes)); // according to the formula learned in class
    }

   /**
    * public static int totalLinks() 
    *
    * This static function returns the total number of link operations made during the run-time of the program.
    * A link operation is the operation which gets as input two trees of the same rank, and generates a tree of 
    * rank bigger by one, by hanging the tree which has larger value in its root on the tree which has smaller value 
    * in its root.
    * O(1)
    */
    public static int totalLinks()
    {    
    	return total_links;
    }

   /**
    * public static int totalCuts() 
    *
    * This static function returns the total number of cut operations made during the run-time of the program.
    * A cut operation is the operation which diconnects a subtree from its parent (during decreaseKey/delete methods).
    * O(1) 
    */
    public static int totalCuts()
    {    
    	return total_cuts;
    }

    /**
     * public static int[] kMin(FibonacciHeap H, int k) 
     * @pre: H is binomial tree with rank deg(H), k<2^deg(H)
     * This static function returns the k minimal elements in a binomial tree H.
     * Complextity: O(k(logk + deg(H)). 
     */
     public static int[] kMin(FibonacciHeap H, int k)
     {    
         int[] res = new int[k];
         FibonacciHeap temp = new FibonacciHeap();
         res[0] = H.min.getKey();
         addChildrenToHeap(temp, H.findMin());
         for(int i=1; i<k; i++) {
         	res[1]=temp.findMin().getKey();
         	HeapNode curr = temp.findMin().pointer;
         	temp.deleteMin();
         	addChildrenToHeap(temp, curr);
         }
         return res; 
     }
     
     /**
      * private static void addChildrenToHeap(FibonacciHeap hp, HeapNode node)
      * staitc func that receives a node and a heap and inserts all the children of the node to the heap
      * O(deg(node))
      */
     
    private static void addChildrenToHeap(FibonacciHeap hp, HeapNode node) {
 	   if(node.getChild()==null) {//the empty case
 		   return;
 	   }
 	   HeapNode child = node.getChild();
 	   HeapNode curr = node.getChild();
 	   do {
 		   hp.insert(curr.getKey()).pointer=curr;
 		   curr=curr.getRight();
 	   }
 	   while(curr.getRight()!=child);//check if I still have kids in the "level" not in the Heap
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
 	public HeapNode pointer;//for Kmin

   	public HeapNode(int key) {
   		//constructor' O(1)
 	    this.key = key;
 	    this.marked = false;
 	    this.rank=0;
 	    this.parent=null;
 	    this.child=null;
 	    this.pointer = null;
       }
  	
  	public HeapNode clone() {
  		HeapNode cloned =new HeapNode(this.key);
  		cloned.marked = this.marked;
  		cloned.rank=this.rank;
  		cloned.parent=this.parent;
  		cloned.child=this.child;
  		return cloned;
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
