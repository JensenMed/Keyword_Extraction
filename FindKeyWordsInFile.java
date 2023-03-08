import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

//import AVLTree.Node;

public class FindKeyWordsInFile {
	
	private static String dictionary = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\test.txt";
	public static ArrayList<String> testList = new ArrayList<String>();
	
	FindKeyWordsInFile(String dictionaryTest){
		ArrayList<String> dummyList = DictonaryOrganizer(dictionaryTest);
		AVLTree testAvl = new AVLTree();
		AVLTree.Node dummyNode;
		
		
		 // displaying the occurrence of elements in the arraylist
		 Map<String, Integer> dummyMap = new HashMap<String, Integer>();
	        for (String i : dummyList) {
	            Integer j = dummyMap.get(i);
	            dummyMap.put(i, (j == null) ? 1 : j + 1);
	        }
	 
//	        System.out.println(dummyMap);
	        
	        for(int a = 0; a < testList.size(); a++) {
	        	// if the root node is null
	        	if(testAvl.root == null) {
	        		dummyNode = new AVLTree.Node(1, testList.get(a));
	        		
	        	}
	        }
		
	}
	
	
	// private helper dictionary organizer
    private static ArrayList<String> DictonaryOrganizer(String dictionary) {
		
		try {
			int id = 1;
			File myObj = new File(dictionary);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.next();
				
				
				// Remove commas from word
				int testData = data.indexOf(",");
				// if comma is found then remove it and add to dictionary
				if(testData == -1) {
					testList.add(data.toLowerCase());
					id ++;	
				}
				else {
					String newData = data.replace(",", "");
					testList.add(newData.toLowerCase());
					id ++;		
				}	
				
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		return testList;
		
		
		
	}
    
    
    
    
    
    
    
    // does all the AVL tree stuff
    
    
    
    
    

 // An AVL tree implementation
 public class AVLTree{

     private Node root;  // root of the AVL tree

     // A node of the AVL tree
     public class Node {
         private Integer key;           // key of the node
         private String value;       // value of the node
         private Node left, right;  // left and right subtrees of the node
         private int height;        // height of the node

         public Node(Integer key, String value) {
             this.key = key;
             this.value = value;
             this.height = 1;
         }
     }

     // Returns the height of the node (or 0 if node is null)
     private int height(Node node) {
         if (node == null) {
             return 0;
         }
         return node.height;
     }

     // Returns the balance factor of the node
     private int balanceFactor(Node node) {
         if (node == null) {
             return 0;
         }
         return height(node.left) - height(node.right);
     }

     // Rotates the node to the left
     private Node rotateLeft(Node x) {
         Node y = x.right;
         x.right = y.left;
         y.left = x;
         y.height = 1 + Math.max(height(y.left), height(y.right));
         x.height = 1 + Math.max(height(x.left), height(x.right));
         return y;
     }

     // Rotates the node to the right
     private Node rotateRight(Node x) {
         Node y = x.left;
         x.left = y.right;
         y.right = x;
         y.height = 1 + Math.max(height(y.left), height(y.right));
         x.height = 1 + Math.max(height(x.left), height(x.right));
         return y;
     }

     // Balances the subtree rooted at the given node
     private Node balance(Node node) {
         if (node == null) {
             return null;
         }
         if (balanceFactor(node) > 1) {
             if (balanceFactor(node.left) < 0) {
                 node.left = rotateLeft(node.left);
             }
             node = rotateRight(node);
         }
         else if (balanceFactor(node) < -1) {
             if (balanceFactor(node.right) > 0) {
                 node.right = rotateRight(node.right);
             }
             node = rotateLeft(node);
         }
         else {
             node.height = 1 + Math.max(height(node.left), height(node.right));
         }
         return node;
     }

     // Returns the value associated with the given key
//     public Value get(Key key) {
//         Node node = get(root, key);
//         if (node == null) {
//             return null;
//         }
//         return node.value;
//     }
     // Returns the node associated with the given key
//     private Node get(Node node, Key key) {
//         if (node == null) {
//             return null;
//         }
//         int cmp = key.compareTo(node.key);
//         if (cmp < 0) {
//             return get(node.left, key);
//         }
//         else if (cmp > 0) {
//             return get(node.right, key);
//         }
//         else {
//             return node;
//         }
//     }

     // Inserts the key-value pair into the AVL tree
//     public void put(Key key, Value value) {
//         root = put(root, key, value);
//     }

     // Inserts the key-value pair into the subtree rooted at the given node
//     private Node put(Node node, Key key, Value value) {
//         if (node == null) {
//             return new Node(key,value);
//         }
//
//         int cmp = key.compareTo(node.key);
//         if (cmp < 0) {
//             node.left = put(node.left, key, value);
//         } else if (cmp > 0) {
//             node.right = put(node.right, key, value);
//         } else {
//             node.value = value;
//             return node;
//         }
//
//         node.height = 1 + Math.max(height(node.left), height(node.right));
//         node = balance(node);
//         return node;
//     }

     private void inOrderTraversal(Node node) {
         if (node != null) {
             inOrderTraversal(node.left);
             System.out.println(node.key + " " + node.value);
             inOrderTraversal(node.right);
         }
     }

     // Call this method from main:
     public void inOrderTraversal() {
         inOrderTraversal(root);
     }

//     public static void main(String[] args) {
//         AVLTree<Integer, String> tree = new AVLTree<>();
//
//         tree.put(5, "apple");
//         tree.put(2, "banana");
//         tree.put(8, "orange");
//         tree.put(1, "pear");
//         tree.put(4, "grape");
//         tree.put(7, "kiwi");
//         tree.put(9, "pineapple");
//         tree.put(3, "mango");
//         tree.put(6, "peach");
//
//         System.out.println("Inorder traversal:");
//         tree.inOrderTraversal();
//
//         System.out.println("Get value at key 4: " + tree.get(4));
//     }
 }
    
    
    
    
	
	public static void main(String args[]) {
		
		FindKeyWordsInFile test = new FindKeyWordsInFile(dictionary);
		
	}

}
