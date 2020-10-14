import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hashing {

	private static int prime[]=new int[100];
	private static int size;
	private static String []keys;
	private static int value[];
	private static int collisions;
	private static int table_size;
	
	Hashing(int capacity){
		collisions=0;
		table_size=0;
		size=capacity;
		keys=new String[size];
		value=new int[size];
	}
	// Method for generating prime numbers
	void find_prime() {
		int k=0;
		for(int i=3;i<300;i+=2) {
			boolean flag=false;
			for(int j=3;j<=Math.sqrt(i);j++) {
				if(i%j==0) {
					flag=true;
					break;
				}
			}
			if(flag)
				prime[k++]=i;
		}
	}
	// To get the table size of the hashtable
	public int getSize() {
		return table_size;
	}
	//Function for calculating hash
	public int calculate_hash(String s) {
		
		int hash=0;
		for(int i=0;i<s.length();i++) 
			hash+=s.charAt(i);
		
		return hash%size;
	}
	
	// Function for checking linear probing
	public int linear_probing(String s) {
		
		int i=get_hash_code(s);
		if(i<0)
			i+=size;
		while(keys[i]!=null) {
			if(keys[i].equals(s))
				return i;
			i=(i+1)%size;
		}
		return -1;
	}
	private int get_hash_code(String s) {
		
		return s.hashCode()%size;
	}
	
	// Method for inserting into hash table using linear probing
	void linear_insert(String s,int val) {
		
		int pos=get_hash_code(s);
		if(pos<0)
			pos+=size;
		int p=linear_probing(s);
		if(p==-1) {
			while(true) {
				if(keys[pos]==null) {
					keys[pos]=s;
					value[pos]=val;
					table_size++;
					break;
				}
				else
					pos=(pos+1)%size;
				collisions++;
			}
		}
		else
			value[p]++;
		
	}
	
	// Method for inserting into hash table using quadratic probing
	
	public void quadratic_insert(String s,int val) {
		
		int pos=get_hash_code(s);
		if(pos<0)
			pos+=size;
		int p=quadratic_probing(s);
		int k=1;
		if(p==-1) {
			while(true) {
				if(keys[pos]==null) {
					keys[pos]=s;
					value[pos]=1;
					table_size++;
					break;
				}
				else
					pos=(pos+k*k)%size;
				k++;
				collisions++;
			}
		}
		else
			value[p]++;
		
		
	}
	
	// Method for searching into hash table using quadratic probing
	
	public int quadratic_probing(String s) {
		
		int i=get_hash_code(s);
		if(i<0)
			i+=size;
		int k=1;
		while(keys[i]!=null) {
			if(keys[i].equals(s))
				return i;
			i=(i+k*k)%size;
			k++;
		}
		return -1;
	}
	
	// Method for rehashing when load factor is greater than 0.5
	void rehash(int new_size) {
		collisions = 0;
		ArrayList<String> kal = new ArrayList<>();
		ArrayList<Integer> val = new ArrayList<>();
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				kal.add(keys[i]);
				val.add(value[i]);
			}
		}
		this.size = new_size;
		Hashing lb = new Hashing(size);
		for (int i = 0; i < kal.size(); i++) {
			lb.linear_insert(kal.get(i), val.get(i));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashing hs=new Hashing(53);
		hs.find_prime();
		File file = new File("src/Words.txt");

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String st;
		// String strval="";
		//int wordcount=0;
		System.out.println("Linear Probing\n");
		try {
			while ((st = br.readLine()) != null) {
				if (hs.getSize() + 1 > .5 * size) {
					int new_size=find_new_size();
					System.out.println("Number of Collision: " + collisions+" when size is "+size+"\n");	
					
					System.out.println("Size changed from "+size+" to "+new_size+"\n");
					collisions=0;
					hs.rehash(new_size);
				}
				hs.linear_insert(st,1);
				
				//wordcount++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of Collision: " + collisions+" when size is "+size+"\n");	
		
		Hashing quad=new Hashing(53);
		br=null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Quadratic Probing\n");
		try {
			while ((st = br.readLine()) != null) {
				if (quad.getSize() + 1 > .5 * size) {
					int new_size=find_new_size();
					System.out.println("Number of Collision: " + collisions+" when size is "+size+"\n");	
					
					System.out.println("Size changed from "+size+" to "+new_size+"\n");
					quad.rehash(new_size);
				}
				quad.quadratic_insert(st,1);
				
				//wordcount++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of Collision: " + collisions+" when size is "+size+"\n");	
		
		char ch;
		do {
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter key");
				String str=scan.next() ;
				int find=hs.linear_probing(str);
				int find1=quad.quadratic_probing(str);
				if(find1!=-1)
					System.out.println("Value " + str+" found \n");
				else
					System.out.println("Value not present");
				if(find!=-1)
					System.out.println("Value " + str+" found using Linear probing\n");
				

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
		
	}
	
	// Method for finding next prime number for the table size
	private static int find_new_size() {
		
		for(int i=0;i<prime.length;i++) {
			if(prime[i]>2*size)
				return prime[i];
		}
		
		return 0;
	}

}
