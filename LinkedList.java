/package tutorials;


public class Linkedlist1 {
    
    // A Node class
    //static class can be accessed from inside the main also
    
    
    static class Node    {
        Node next;
        int val;
        
        // constructor
        Node(int x)
        {
            val=x;
            next=null;
        }
    }
    
    // This method is being called inside main in order to sort the LinkedList and return //the head node to main
    
    static Node sort(Node head_ref)
    {
        
        // incase of empty list it will return null
        
        if(head_ref==null)
        { System.out.println("Empty List");
            return null;}
        
        // otherwise it will call selectionsort function in order to do sorting             //using selection sort
        
        else
            head_ref=selectionsort(head_ref);
        return head_ref;
    }
    
    // This method is being called inside the sort function in order to sort the
    //LinkedList using selection sort methodology and return the head to sort function
    
    static Node selectionsort(Node head)
    {
        //incase of only one node no need of sorting
        
        if(head.next==null)
            return head;
        
        //initializing min,prev and next node used here as pointers
        
        Node min=head;
        Node prev=null;
        Node next=head;
        
        // This for loop is used to traverse the list and find out node with min value
        //and then do the rearrangements of links.
        
        for(next=head;next.next!=null;next=next.next)
        {
            if(next.next.val<min.val)
                min=next.next;
            prev=next;
            
            // if the head node is not having the min value,then we will swap head
            //node with min node
            
            if(min!=head)
                head=Swapnode(head,head,min,prev);
        }
        
        head.next=selectionsort(head.next);
        
        
        return head;
    }
    
    
    // method sed to swap  nodes with min node and recreating the links
    static Node Swapnode(Node head_ref,Node headx,Node miny,Node prev)
    {
        head_ref=miny;
        prev.next=headx;
        Node temp=miny.next;
        
        miny.next=headx.next;
        headx.next=temp;
        return head_ref;
    }
    
    
    // method to display list nodes
    static void display(Node n)
    { // if the list ismpty it will return null
        if(n==null)
            return;
        // else it will display all  list nodes starting from head
        while(n!=null)
        {
            System.out.print(n.val+"  ");
            n=n.next;
            System.out.println("  ");
        }
    }
    
    //method to insert nodes in a Linkedist
    static Node insert(Node head,int data)
    {
        // create the new node with data
        Node node=new Node(data);
        node.next=null;
        
        // incase of empty list while inserting the first node we will make it as head
        if(head==null)
        {
            head=node;
            return head;
            
        }
        //otherwise we will add new node at the end of list
        else{
            Node n=head;
            while(n.next!=null)
            {
                n=n.next;
                
            }
            n.next=node;
        }
        return null;
    }
    
    
    
    
    public static void main(String[] args) {
        
        
        Node head=null;
        
        head=insert(head,20);
        insert(head,15);
        insert(head,25);
        insert(head,5);
        insert(head,40);
        insert(head,15);
        insert(head,25);
        insert(head,5);
        insert(head,40);
        insert(head,15);
        insert(head,25);
        insert(head,5);
        insert(head,40);
        insert(head,40);
        insert(head,15);
        insert(head,25);
        insert(head,5);
        insert(head,40);
        
        
        display(head);
        
        
        head=sort(head);
        System.out.println("#################");
        
        display(head);
        
    }
    
    
}
