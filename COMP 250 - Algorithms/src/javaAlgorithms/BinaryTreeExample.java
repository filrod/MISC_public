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
	
	public Node delete(int key) {
		
		Node nodeOfInterest = root;
		Node parent = root;
		Node deletedNode;
		
		boolean isLeftChild = true;
		
		while(nodeOfInterest.key != key) {
			
			parent = nodeOfInterest;
			
			if (key<nodeOfInterest.key) {
				
				isLeftChild = true;
				nodeOfInterest = nodeOfInterest.leftChild;
				
			}else {
				
				isLeftChild = false;
				nodeOfInterest = nodeOfInterest.rightChild;
			}
			
			if (nodeOfInterest == null)
				return null;
		}
		
		if (nodeOfInterest.leftChild == null && nodeOfInterest.rightChild == null) {
			
			if (nodeOfInterest == root) {
				deletedNode = root;
				root = null;
			} else if (isLeftChild) {
				deletedNode = parent.leftChild;
				parent.leftChild = null;
			} else {
				deletedNode = parent.rightChild;
				parent.rightChild = null;
			}
		} 
		
		else if (nodeOfInterest.rightChild == null) {
			
			if (nodeOfInterest == root)
				root = nodeOfInterest.leftChild;
			
			else if (isLeftChild) 
				parent.leftChild = nodeOfInterest.leftChild;
			
			else parent.rightChild = nodeOfInterest.leftChild;
				
		}
		
		else if(nodeOfInterest.leftChild == null) {
			
			if (nodeOfInterest == root)
				root = nodeOfInterest.rightChild;
			
			else if (isLeftChild)
				parent.leftChild = nodeOfInterest.leftChild;
			
			else parent.rightChild = nodeOfInterest.leftChild;
			
		}
		
		else {
			Node sub = getSubstitutionNode(nodeOfInterest);
			
			if (nodeOfInterest == root)
				root = sub;
			
			else if (isLeftChild)
				parent.leftChild = sub;
			
			else parent.rightChild = sub;
				
			sub.leftChild = nodeOfInterest.leftChild;
		}
		return this.root;
	}
	
	private Node getSubstitutionNode(Node tosub) {
		Node tosubParent = tosub;
		Node sub = tosub;
		Node nodeOfInterest = tosub.rightChild;
		
		while (nodeOfInterest != null) {
			
			tosubParent = tosub;
			sub = nodeOfInterest;
			nodeOfInterest = nodeOfInterest.leftChild;
		
		}
		
		if (sub != tosub.rightChild) {
			tosubParent.leftChild = sub.rightChild;
			sub.rightChild = tosub.rightChild;
		}
		return sub;
	}

	public Node search(int key) {
		
		Node nodeOfInterest = root;
		
		while(nodeOfInterest.key != key) {
			
			if (key<nodeOfInterest.key) {
				
				nodeOfInterest = nodeOfInterest.leftChild;
				
			}else {
				
				nodeOfInterest = nodeOfInterest.rightChild;
			}
			
			if (nodeOfInterest==null)
				return null;
		}
		
		return nodeOfInterest;
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
		
		System.out.println("Search for key=700: \n" +tree.search(700));
	
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
