package javaAlgorithms;

public class BinaryTreeExample{
	
	////////////////////////////////////////////////////
	//                   Attributes                   //
	////////////////////////////////////////////////////
	Node root;
	
	////////////////////////////////////////////////////
	//                    Methods                     //
	////////////////////////////////////////////////////

	public void addNode(int key, String name){
		/*
		 * Create and initialize a new node.
		 *
		 * If there is no root, make one.
		 *
		 * If not, make a parent node and walk
		 * through each parent node until the 
		 * key value of the newNode is less
		 * than the parent's key. If and when 
		 * this is the case, check with the 
		 * left child first. If this node is 
		 * null then make newNode the parent's 
		 * left child. If not, make newNode the
		 * parent's right child.
		 */
	
		Node newNode = new Node(key, name);
		
		if (root == null){
			root = newNode;
		} else {
			
			Node nodeOfInterest = root;
			Node parent;
			
			while(true){
				
				parent = nodeOfInterest;
				
				if (key<nodeOfInterest.key){
					nodeOfInterest = nodeOfInterest.leftChild;
					
					if (nodeOfInterest == null){
						parent.leftChild = newNode;
						return;
					}
					
				} else {
				
					nodeOfInterest = nodeOfInterest.rightChild;
			
					if (nodeOfInterest == null){
			
						parent.rightChild = newNode;
						return;
					}
				}
			} // End while
		} 
			
	}
	
	public void inOrderTraversal(Node nodeOfInterest){
		/*
		 * This method goes through the binary tree and 
		 * prints out the nodes' info by ascending key 
		 * order.
		 */
		if (nodeOfInterest != null){
			
			inOrderTraversal(nodeOfInterest.leftChild);
			
			System.out.println(nodeOfInterest.toString());
			
			inOrderTraversal(nodeOfInterest.rightChild);
			
		}
	}
	
	public void preOrderTraversal(Node nodeOfInterest){
		/*
		 * Does a pre-order traversal.
		 */
		if (nodeOfInterest != null){
			
			System.out.println(nodeOfInterest.toString());

			inOrderTraversal(nodeOfInterest.leftChild);
			
			inOrderTraversal(nodeOfInterest.rightChild);
			
		}
	}
	
	public void postOrderTraversal(Node nodeOfInterest){
		/*
		 * Does a post order traversal.
		 */
		if (nodeOfInterest != null){
			
			inOrderTraversal(nodeOfInterest.leftChild);
			
			inOrderTraversal(nodeOfInterest.rightChild);
			
			System.out.println(nodeOfInterest.toString());
			
		}
	}
	
	private static String randStr(int count) {
		
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		StringBuilder strBuild = new StringBuilder();
		
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			strBuild.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return strBuild.toString();
		
	}
	
	public static void main(String[] args){
		
		
	
		BinaryTreeExample tree = new BinaryTreeExample();
		
		for (int i=0; i<10000; i++) {
			tree.addNode(
					     (int) (Math.random()
					     * Math.pow(
					    		    10, 
					    		    (int)(10*Math.random())-5
					    		    ))
					     , randStr((int)(Math.random()*10)));
		}
		tree.inOrderTraversal(tree.root);
	
	}

}
class Node{
	
	////////////////////////////////////////////////////
	//                   Attributes                   //
	////////////////////////////////////////////////////
	
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	
	////////////////////////////////////////////////////
	//                   Constructor                  //
	////////////////////////////////////////////////////
	
	Node(int key, String name){
		// Builds a node on this.tree
		this.key = key;
		this.name = name;
	}
	
	////////////////////////////////////////////////////
	//                    Methods                     //
	////////////////////////////////////////////////////
	
	public String toString(){
		return name +" has a key "+ key;
	}

}
