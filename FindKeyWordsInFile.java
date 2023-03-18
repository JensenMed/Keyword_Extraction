

/**
 * This class will compute the frequency (I.e the amount of times a certain word appears in the given file) and uses the data to put the info
 * into a new txt file generated by the program.
 * CS 2210B
 * Jensen Medeiros
 * 251234023
 * jmedei27
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;




public class FindKeyWordsInFile {
	
	public static ArrayList<String> testList = new ArrayList<String>();
	private static String OutputFile = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\test2.txt";
	public static HashMap<String, Boolean> FrequentWords = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> errorMap = new HashMap<String, Boolean>();
	
	FindKeyWordsInFile(String dictionaryTest, Integer k, String MostFrequentEnglishWords) throws IOException{
		
		ArrayList<String> dummyList = DictonaryOrganizer(dictionaryTest);
		HashMap<String, Boolean> mostFrequentWords = MostFrequentWordsOragnizer(MostFrequentEnglishWords);
		AVLTree testAvl = new AVLTree();
		AVLTree.Node rootNode = null;
	    ArrayList<String> testList = new ArrayList<String>();
	    TreeMap<String, Integer> sorted = new TreeMap<>();
		
	
		 // displaying the occurrence of elements in the arraylist
		 	Map<String, Integer> dummyMap = new HashMap<String, Integer>();
	        for (String i : dummyList) {
	            Integer j = dummyMap.get(i);
	            dummyMap.put(i, (j == null) ? 1 : j + 1);
	        }
	        
	        // sorts all by key
	        sorted.putAll(dummyMap);
	        int i = 0;
	        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
	        		PrintWriter out = new PrintWriter(new FileWriter(OutputFile, true), true);
	        		if(FrequentWords.get(entry.getKey()) == null) {
	        			out.write(entry.getKey() + " " + entry.getValue() + "\n");
	        		    out.close();
		        		testList.add(entry.getKey() + " " + entry.getValue());
	        		}
	        }
	        
	        // sorts by value
	        Collections.sort(testList);
	        
	        //writes to file
	        try {
	            FileWriter myWriter = new FileWriter(OutputFile);
		        for(int p = 0; p < k; p++) {
		        	if(p < testList.size()) {
		        		myWriter.write(testList.get(p) + "\n");
		        	}
		        }
	            myWriter.close();
	          } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
	        
	        
	        
	        
	        for(Map.Entry<String, Integer> entry: dummyMap.entrySet()) {
	        	Integer key = entry.getValue();
	        	String value = entry.getKey();
	        	
	        	// if the root node is null
	        	if(rootNode == null) {
	        		rootNode = testAvl.new Node(key, value);
	        		testAvl.root = rootNode;
	        		
	        		
	        	}else {
	        		//dummyNode pointer to insert new nodes
	        		AVLTree.Node dummyNode = rootNode;
	        		AVLTree.Node newNode  = testAvl.new Node(key, value);
	        		
	        		// first check if it's already in the AVLtree
	        		if(testAvl.get(newNode, newNode.key) == null) {
	        			testAvl.put(newNode, key, value);
	        			testAvl.inOrderTraversal(testAvl.root);
	        			
	            		// if it's less than than it will go on left subtree of root node
		        		if(dummyNode.value.compareTo(newNode.value) > 1) {
		        			dummyNode.left = newNode;
		        			testAvl.put(dummyNode.left, key, value);
		        			// check balance and balance if nessisary
		        			testAvl.balance(dummyNode.left);
		        		}else {
		        			// else it is greater than
		        			dummyNode.right = newNode;
		        			//insert node
		        			testAvl.put(dummyNode.right, key, value);
		        			// check balance and balance if nessisary
		        			testAvl.balance(dummyNode.right);
		        		}
		 
	        		}else {
	        			// increment the frequency of that node
	        			testAvl.get(newNode, newNode.key).key = testAvl.get(newNode, newNode.key).key + 1;
	        		}
	        		
	        	}
	        }
	        // performs inordertraversal
	        testAvl.inOrderTraversal(rootNode);
		
	}
	
	
	// private helper dictionary organizer
    public static ArrayList<String> DictonaryOrganizer(String dictionary) {
		
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
			errorMap.put("Error. File not found", true);
			System.out.println("Error. File not found");
			
		}
		
		return testList;
		
		
		
	}
    
    
    
	// private helper MostFrequentWords organizer
    public static HashMap<String, Boolean> MostFrequentWordsOragnizer(String dictionary) {
		
    	
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
					FrequentWords.put(data.toLowerCase(), true);
					id ++;	
				}
				else {
					String newData = data.replace(",", "");
					FrequentWords.put(newData.toLowerCase(), true);
					id ++;		
				}	
				
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			errorMap.put("Error. File not found", true);
			System.out.println("Error. File not found");
		}
		
		
		return FrequentWords;
		
		
		
	}
    

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
     
     // sets rootNode
     private void setRoot(Node rootNode) {
    	 this.root = rootNode;
     }
     
     //returns root node
     
     private Node getRoot() {
    	 return root;
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
     public String get(Integer key) {
         Node node = get(root, key);
         if (node == null) {
             return null;
         }
         return node.value;
     }
     // Returns the node associated with the given key
     private Node get(Node node, Integer key) {
         if (node == null) {
             return null;
         }
         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
             return get(node.left, key);
         }
         else if (cmp > 0) {
             return get(node.right, key);
         }
         else {
             return node;
         }
     }

     // Inserts the key-value pair into the AVL tree
     public void put(Integer key, String value) {
         root = put(root, key, value);
     }

     // Inserts the key-value pair into the subtree rooted at the given node
     private Node put(Node node, Integer key, String value) {
         if (node == null) {
             return new Node(key,value);
         }

         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
             node.left = put(node.left, key, value);
         } else if (cmp > 0) {
             node.right = put(node.right, key, value);
         } else {
             node.value = value;
             return node;
         }

         node.height = 1 + Math.max(height(node.left), height(node.right));
         node = balance(node);
         return node;
     }

     private void inOrderTraversal(Node node) {
         if (node != null) {
             inOrderTraversal(node.left);
             inOrderTraversal(node.right);
         }
     }

     // Call this method from main:
     public void inOrderTraversal() {
         inOrderTraversal(root);
     }

 }
    
	
	public static void main(String args[]) throws IOException {
		String ktoInt = null;
		String file = null;
		String MostFrequentEnglishWords = null; 
		if(args.length != 0) {
        	for(String str : args){
            	ktoInt = args[0];
            	file = args[1];
            	MostFrequentEnglishWords = args[2];
            }

        }else {
        	System.out.println("Please input a dictionary file and file to check");
        }

		if(ktoInt != null) {
			Integer k = Integer.parseInt(ktoInt);
			FindKeyWordsInFile test = new FindKeyWordsInFile(file, k, MostFrequentEnglishWords);
		}else {
			System.out.println("Please enter a valid integer value for k");
		}

		
		
	}

}
