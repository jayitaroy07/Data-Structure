// instruction to compile and run the program
//javac Heap.java
// java Heap



    public class Heap {
        
        static void heapsort(int[] arr,int size)
        //This method will sort the array in descending order
        {
            int start=1;
            //we are taking the start index as 1 since the 0th element is nothing but the
            //count of total elements
            int end=size-1;
            
            while(arr[0]>2)
            {
                
                swap(arr,start,end);
                arr[0]=arr[0]-1;
                end=end-1;
                heapify(arr,end,start);
                
                
                
            }
            
            
            
            
            
            
        }
        
        static void swap(int[] arr,int start,int end)
        {
            int temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
        }
        
        static void heapify(int[] arr,int size,int i)
        
        { if(i>0){
            int smallest=i;
            int leftchild=2*i;
            int rightchild=2*i+1;
            
            if(leftchild<size && arr[leftchild]<arr[smallest])
                smallest=leftchild;
            if(rightchild<size && arr[rightchild]<arr[smallest])
                smallest=rightchild;
            
            if(smallest!=i)
            {
                swap(arr,smallest,i);
                
                heapify(arr,size,smallest);
            }
            
        }
        }
        
        
        
        
        static void buildheap(int[] arr,int size)
        { if(size!=0)
            //if the array is not empty do the following
            
        {int lastparent=(int)(arr[0]/2);
            //we are running the for loop untill 1st index of our array as the 0th index holds the count
            for(int i=lastparent;i>0;i--)
                heapify(arr,size,i);
        }
        }
        
        static void print(int[] arr)
        {
            if(arr.length>0){
                //if you want you can print the value from 1st index
                //till the last since the 0th element holds the count
                for(int i=0;i<arr.length;i++)
                    
                    System.out.print(arr[i]+" ");
                
            }
            System.out.println();
            
        }
        
        public static void main(String[] args) {
            // the very first element in the array contains the count of total elements,here it's 15
            int[] arr=new int[16];
            arr[0]=15;
            arr[1]=50;
            arr[2]=10;
            arr[3]=5;
            arr[4]=45;
            arr[5]=15;
            arr[6]=150;
            arr[7]=550;
            arr[8]=100;
            arr[9]=1;
            arr[10]=75;
            arr[11]=30;
            arr[12]=105;
            arr[13]=0;
            arr[14]=350;
            arr[15]=115;
            
            print(arr);
            
            int size=arr.length;
            
            buildheap(arr,size);
            
            print(arr);
            
            heapsort(arr,size);
            
            print(arr);
            
            
        }
    }