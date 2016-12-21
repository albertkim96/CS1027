import java.util.*;
import java.math.*;
import java.io.*;

public class PooPoo
{
	// private variable accessible to all methods
	private static BinaryTreeNode<String> root=null;
	private static BinaryTreeNode<String> node=null;
	private static final boolean DEBUG=false;
	
	public static void main(String[] args) {

		String listFileName = args[0];
		// Create listReader Object
		InStringFile listReader=new InStringFile(listFileName);
		StringTokenizer tokenizer;
		String line = listReader.read();
		tokenizer=new StringTokenizer(line);
		int numTokens=tokenizer.countTokens();
		System.out.println(numTokens + " token in " + listFileName);
		for(int i=0;i<numTokens;i++)
		{
			String element=tokenizer.nextToken();
			if(DEBUG) System.out.println("element read: " + element);
			node=new BinaryTreeNode<String>(element);
			root=insertInBinaryTree(root,node);
		}
		int n=countBinaryTreeNodes(root);
		System.out.println("Number of nodes in the tree:" + n);
	
		int h=findHeight(root);
		System.out.format("Height of tree: %d\n",h);
		System.out.format("Height density: %6.2f\n",h*100.0/n);
		int w=findWidth(root);
		System.out.format("Width of tree: %d\n",w);
		System.out.format("Width density: %6.2f\n",w*100.0/n);
		PrettyPrintTree.printNode(root);
	}
	
	/**
	 * insert a BinaryTreeNode<String> node in a binary tree rooted by BinaryTreetNode<String> root
	 * @param root	BinaryTreeNode objective which is the base of binary tree
	 * @param node	BinaryTreeNode objective which will be added to the tree
	 * @return		BinaryTreeNode object root will be returned
	 */
	public static BinaryTreeNode<String> insertInBinaryTree(BinaryTreeNode<String> root, BinaryTreeNode<String> node)
	{
		// At start, it checks if the root is null. If it is, then make the node equal to root
		if (root == null) {
			root = node;
		}
		
		// Otherwise, use compareTo function to get the result of comparing root element and node element. If result
		// is less or equal to 0, then it checks if right node of root is equal to null. If true, then set node to be
		// the right of root. Otherwise call the recursive function with right node of root as the root. If the result
		// is more than 0, then it does the same procedure as before but with left side of root.
		else {
			int result = root.getElement().compareTo(node.getElement());	// Compare root element and node element
			
			if (result <= 0){												
				if (root.getRight() == null){								// Set node to be right of root
					root.setRight(node);
				}
				else{														// Call recursive function
					insertInBinaryTree(root.getRight(), node);
				}
			}
			
			else {
				if (root.getLeft() == null){								// Set node to be left of root
					root.setLeft(node);
				}
				else{
					insertInBinaryTree(root.getLeft(), node);				// Call recursive function
				}
			}
		}
		
		return root; 
	}

	/**
	 * The height of a tree is the length of the path from the root to the deepest node in the tree. An empty tree 
	 * has height -1. A tree with just 1 node (the root) has height 0
	 * @param node	Binary tree objective which will be the base of binary tree node
	 * @return		Integer value which will be the height of the BinaryTreeNode
	 */
	public static int findHeight(BinaryTreeNode<String> node)
	{
		// Checks if there is no nodes, if it is, then it returns -1.
		if (node == null){
			return -1;
		}
		
		// Checks if right and left of node is null. Basically checking if it is the leaf of the binary tree.
		// If true, then return 0.
		else if (node.getRight() == null && node.getLeft() == null){
			return 0;			
		}
		
		// Otherwise, call the recursive function the get the height of the binary tree by checking left and right.
		// Find the higher value from both side, then add 1 to it and return to get the height of binary tree.
		else {
			return Math.max(findHeight(node.getLeft()), findHeight(node.getRight())) + 1;
			
		}
	}

	/**
	 * Compute the maximum width of the binary tree. Use a level order search with a queue
	 * @param root	BinaryTreeNode object, the base of the binary tree node
	 * @return		Integer value that will be the maximum width of the binary tree
	 */
	public static int findWidth(BinaryTreeNode<String> root)
	{
		// Declare a new queue for BinaryTreeNode object and variable for maximum width
		LinkedQueue<BinaryTreeNode> queue = new LinkedQueue<BinaryTreeNode>();
		int maximumWidth = 1;

		// If the element of root is null, then there is no node in binary tree. Hence, return 0
		if (root.getElement() == null){
			return 0;
		}
		
		// Enqueue the root
		queue.enqueue(root);
		
		// While queue isn't empty: dequeue, checks the left and right of the root. If they are not
		// equal to null, then enqueue them. If the size of queue is larger than maximum width, then
		// make size of queue new maximum width.
		while(!queue.isEmpty()){
			root = queue.dequeue();
			if(root.getLeft() != null){					// insert left node if not equal to null
				queue.enqueue(root.getLeft());	
			}
			if(root.getRight() != null){                // insert right node if not equal to null
				queue.enqueue(root.getRight());
			}
			int w = queue.size();
			if (maximumWidth < w){						// checks if maximumWidth is larger than w
				maximumWidth = w;
			}
		}
		return maximumWidth;
	}

	/**
	 * The number of nodes in a binary tree is the number of nodes in the root's left subtree plus
	 * the number of nodes in its right subtree plus one (for the root itself).
	 * @param root	BinaryTreeNode object, the base of the binary tree.
	 * @return		Integer value which will be the number of nodes in binary tree.
	 */
	public static int countBinaryTreeNodes(BinaryTreeNode<String> root)
	{
		// If there is no element in root, then return 0.
		if (root == null){
			return 0;
		}
		
		// Otherwise, call numChildren of root to get number of childrens root has and add 1.
		else {
			return root.numChildren() + 1;
		}
	}
	
}
