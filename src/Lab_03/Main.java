package Lab_03;

import java.util.Random;

public class Main {
	static private final int N=500000;
	static private final int K=10
			;
	static private final int M=N*10;
	
	public static void main(String[] args){
		RandomizedBinaryTrees<Integer, Integer> bst=new RandomizedBinaryTrees<Integer, Integer>();//create an object of BST class
		Random r=new Random();//create an object of Random class
		StopWatch sw1=new StopWatch();//create an object of StopWatch class
		for (int i=0;i<K*N;i++){//create a tree
			Integer value=r.nextInt(M+1)-1;
			Integer key=r.nextInt(N*K+1)-1;
			bst.put(key,value);
			//System.out.println(i);
			}
		System.out.println("The time needed  for the execution of put operations is "+sw1.elapsedTime());
		System.out.println("The height of a tree is "+bst.height());
		System.out.println("The average depth of a tree is  "+bst.averageDepth());
		Integer arr[]=new Integer[N];//create an array of random keys
		for(int i=0;i<N;i++){
			arr[i]=r.nextInt(M+1)-1;
		}
		int numbOfOper=0;
		Integer val=0;

		StopWatch sw2=new StopWatch();
		while(numbOfOper<N){
			val=(Integer) bst.get(arr[numbOfOper]);
			//System.out.println(val);
			numbOfOper++;
		}
		System.out.println("The time needed  for the execution of get is "+sw2.elapsedTime());
		//System.out.println("The height of a tree is "+bst.height());
		//System.out.println("The average distance to a root is "+bst.averageDepth());
	}

}
