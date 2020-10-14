//package BSTInsertDelete{
// This program will do insert,insert, delete and ignorer traversal in a BST.
// instruction to compile and run the program
//javac BSTInsertDelete.java
// java BSTInsertDelete


public class BSTInsertDelete {
    
    //defining Node class for AVL tree
    
    static class Node {
        int val;
        Node left,right;
        
        public Node(int v)
        {
            val=v;
            left=null;
            right=null;
        }
    }
    
    // method to insert element into an AVL tree
    static Node BSTInsert(Node tmp,int key)
    {
        //if the tree is empty it will insert the new node as root
        
        if(tmp==null)
        {
            tmp=new Node(key);
            
            return tmp;
        }
        
        else
            
            rBSTInsert(tmp,key);
        
        
        return tmp;
    }
    
    
    // function to insert into an AVL tree incase the tree is not empty
    
    static void rBSTInsert(Node tmp,int key)
    {
        // if the new value is greater than root then it will be inserted to the right subtree
        if(tmp.val<key)
        {
            if(tmp.right==null)
            {
                tmp.right=new Node(key);
                
                
                return;
            }
            
            else  rBSTInsert(tmp.right,key);
            
            
        }
        // otherwise the new value will be inserted to the left subtree
        else
        {
            if(tmp.left==null)
            {
                tmp.left=new Node(key);
                
                return;
            }
            
            rBSTInsert(tmp.left,key);
        }
        return;
    }
    
    // Method to delete value from AVL tree
    
    static Node deleteBst(Node tmp,int v)
    {  // if the tree is empty nothing to delete
        
        if(tmp==null)
        {
            System.out.println("Tree is empty");
            return null;
        }
        // otherwise it will find out the node needs to be deleted
        
        if(tmp.val<v)
            tmp.right=deleteBst(tmp.right,v);
        else if(tmp.val>v)
            tmp.left=deleteBst(tmp.left,v);
        
        else {
            //deleting leaf node
            
            if(tmp.left==null && tmp.right==null)
            {tmp=null;
                return tmp;
            }
            // the target node has only one child
            
            else if(tmp.left!=null && tmp.right==null)
                return tmp.left;
            else if(tmp.left==null && tmp.right!=null)
                return tmp.right;
            
            else //the target node has two children replace the node value with it's predecessor //and then delete the predessor node
            {
                tmp.val=findpredecessor(tmp.left);
                tmp.left=deleteBst(tmp.left,tmp.val);
            }
        }
        return tmp;
        
    }
    
    
    // function to find the predessor of a target node,which is nothing but the max from the left
    //subtree of that node
    
    static int findpredecessor(Node tmp)
    {
        int max=tmp.val;
        while(tmp.right!=null)
        {
            max=tmp.right.val;
            tmp=tmp.right;
        }
        return max;
    }
    
    // method to do inorder traversal
    
    static  void inorderTraversal(Node tmp)
    {
        if(tmp!=null) {
            inorderTraversal(tmp.left);
            System.out.print(tmp.val+"  ");
            inorderTraversal(tmp.right);
        }
        
        
        
    }
    public static void main(String[] args) {
        
        Node root=null;
        
        root=BSTInsert(root,50);
        root=BSTInsert(root,40);
        root=BSTInsert(root,80);
        root=BSTInsert(root,20);
        root=BSTInsert(root,45);
        root=BSTInsert(root,60);
        root=BSTInsert(root,100);
        root=BSTInsert(root,70);
        root=BSTInsert(root,65);
        root=BSTInsert(root,42);
        root=BSTInsert(root,44);
        root=BSTInsert(root,30);
        root=BSTInsert(root,25);
        root=BSTInsert(root,35);
        root=BSTInsert(root,33);
        
        System.out.println("Print the value of inorder traversal");
        inorderTraversal(root);
        
        System.out.println();
        
        System.out.println("we are going to delete 50");
        root=deleteBst(root,50);
        
        System.out.println();
        
        System.out.println("Print the value of inorder traversal");
        inorderTraversal(root);
        
        System.out.println("we are going to delete 40");
        root=deleteBst(root,40);
        
        System.out.println();
        
        System.out.println("Print the value of inorder traversal");
        inorderTraversal(root);
        
        System.out.println("we are going to delete 65");
        root=deleteBst(root,65);
        
        System.out.println();
        
        System.out.println("Print the value of inorder traversal");
        inorderTraversal(root);
        
        System.out.println("we are going to delete 35");
        root=deleteBst(root,35);
        
        System.out.println();
        
        System.out.println("Print the value of inorder traversal");
        inorderTraversal(root);
        
        
        
    }
    
}

