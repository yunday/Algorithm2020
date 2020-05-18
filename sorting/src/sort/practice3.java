package sort;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import java.util.Arrays;

public class practice3 {
	
	
//	public static void main(String[] args) {
//
//		int[] A = new int[1000];
//		int[] B = new int[10000];
//		int[] C = new int[100000];
//
//		Print_all(A, B, C);
//	}
	
	static public void Print_all(int[] A, int[] B, int[] C) {
	    System.out.println(String.format("%13s","")+String.format("%13s","Random1000")+String.format("%13s","Random10000")+String.format("%13s","Random100000")+String.format("%13s","Reverse1000")+String.format("%13s","Reverse10000")+String.format("%13s","Reverse100000"));
		Print_bubble(A, B, C);
		Print_selectionSort(A, B, C);
		Print_insertionSort(A, B, C);
		Print_mergeSort(A, B, C);
		Print_quickSort(A, B, C);
		Print_quickSort_middle(A, B, C);
		Print_quickSort_rand(A, B, C);
		Print_heapSort(A, B, C);
		Print_java(A, B, C);
	}
	
	static public void Print_bubble(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Bubble")+String.format("%13d",bubbleSort(A, 1000))+String.format("%13s",bubbleSort(B, 10000))+String.format("%13s",bubbleSort(C, 100000)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13d",bubbleSort(A, 1000))+String.format("%13s",bubbleSort(B, 10000))+String.format("%13s",bubbleSort(C, 100000)));
	}
	static public void Print_selectionSort(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Selection")+String.format("%13d",selectionSort(A, 1000))+String.format("%13d",selectionSort(B, 10000))+String.format("%13d",selectionSort(C, 100000)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13d",selectionSort(A, 1000))+String.format("%13d",selectionSort(B, 10000))+String.format("%13d",selectionSort(C, 100000)));
	}
	static public void Print_insertionSort(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Insertion")+String.format("%13s",insertionSort(A, 1000))+String.format("%13s",insertionSort(B, 10000))+String.format("%13s",insertionSort(C, 100000)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",insertionSort(A, 1000))+String.format("%13s",insertionSort(B, 10000))+String.format("%13s",insertionSort(C, 100000)));
	}
	static public void Print_mergeSort(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Merge")+String.format("%13s",mergeSort(A, 0, 999))+String.format("%13s",mergeSort(B, 0, 9999))+String.format("%13s",mergeSort(C, 0, 99999)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",mergeSort(A, 0, 999))+String.format("%13s",mergeSort(B, 0, 9999))+String.format("%13s",mergeSort(C, 0, 99999)));
	}
	static public void Print_quickSort(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Quick1")+String.format("%13s",quickSort(A, 0, 999))+String.format("%13s",quickSort(B, 0, 9999))+String.format("%13s",quickSort(C, 0, 99999)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",quickSort(A, 0, 999))/*+String.format("%-13s",quickSort(B, 0, 9999))+String.format("%-13s",quickSort(C, 0, 99999))*/);
	}
	static public void Print_quickSort_middle(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Quick2")+String.format("%13s",quickSort_middle(A, 0, 999))+String.format("%13s",quickSort_middle(B, 0, 9999))+String.format("%13s",quickSort_middle(C, 0, 99999)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",quickSort_middle(A, 0, 999))+String.format("%13s",quickSort_middle(B, 0, 9999))+String.format("%13s",quickSort_middle(C, 0, 99999)));
	}
	static public void Print_quickSort_rand(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Quick3")+String.format("%13s",quickSort_rand(A, 0, 999))+String.format("%13s",quickSort_rand(B, 0, 9999))+String.format("%13s",quickSort_rand(C, 0, 99999)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",quickSort_rand(A, 0, 999))+String.format("%13s",quickSort_rand(B, 0, 9999))+String.format("%13s",quickSort_rand(C, 0, 99999)));
	}
	
	static public void Print_heapSort(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Heap")+String.format("%13s",heapSort(A))+String.format("%13s",heapSort(B))+String.format("%13s",heapSort(C)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",heapSort(A))+String.format("%13s",heapSort(B))+String.format("%13s",heapSort(C)));
	}
	
	static public void Print_java(int[] A, int[] B, int[] C) {
		arrayInsert_rand(A, B, C);
		System.out.print(String.format("%-13s","Library")+String.format("%13s",javaAlgorithm(A))+String.format("%13s",javaAlgorithm(B))+String.format("%13s",javaAlgorithm(C)));
		arrayInsert_reverse(A, B, C);
        System.out.println(String.format("%13s",javaAlgorithm(A))+String.format("%13s",javaAlgorithm(B))+String.format("%13s",javaAlgorithm(C)));
	}
	
	static public long bubbleSort(int[] A, int n) {
		Instant start=Instant.now();
		for(int last=n-1;last>=1;last--) {
			for(int i=0;i<last;i++) {
				if(A[i]>A[i+1]) {
					swap(A, i, i+1);
				}
			}
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	static public long selectionSort(int[] A, int n) {
		Instant start=Instant.now();
		for(int last=n-1;last>=1;last--) {
			int best=last;
			for(int i=last-1;i>=0;i--) {
				if(A[best]<=A[i])
					best=i;
			}
			swap(A, last, best);
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	static public long insertionSort(int[] A, int n) {
		Instant start=Instant.now();
		for(int i=1;i<n;i++) {
			int tmp=A[i];
			int j;
			for(j=i-1;j>=0;j--) {
				if(A[j]>tmp)
					A[j+1]=A[j];
				else
					break;
			}
			A[j+1]=tmp;
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	static public long mergeSort(int[] A, int p, int r) {
		Instant start=Instant.now();
		int q=r;
		if(p<r) {
			q=(p+q)/2;
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	static public void merge(int[] A, int p, int q, int r) {
		int i=p, j=q+1, k=p;
		int[] tmp=new int[r+1];
		while(i<=q&&j<=r) {
			if(A[i]<=A[j])
				tmp[k++]=A[i++];
			else
				tmp[k++]=A[j++];
		}
		while(i<=q)
			tmp[k++]=A[i++];
		while(j<=r)
			tmp[k++]=A[j++];
		
		for(int s=p;s<r+1;s++)
			A[s]=tmp[s];
	}
	static int num =0;
	public static long quickSort(int[] A, int p, int r) {
		Instant start=Instant.now();
		int q;
		if(num>2000)
			return -1;
		num++;
		if(p<r) {
			q=partition(A, p, r);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
		Instant end=Instant.now();
		
		if(num>2000)
			return -1000;
		num--;
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	public static int partition(int[] A, int p, int r) {
		int j=p, i=p-1;
		for(j=p;j<r;j++) {
			if(A[j]<=A[r]) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i+1, r);
		return i+1;
	}
	
	public static long quickSort_middle(int[] A, int p, int r) {
		Instant start=Instant.now();
		int q;
		if(p<r) {
			search_middle(A, p, r);
			q=partition(A, p+1, r-1);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	public static void search_middle(int[] A, int p, int r) {
		int q=(p+r)/2;
		if(A[p]>A[q])
			swap(A, p, q);
		if(A[p]>A[r])
			swap(A, p, r);
		if(A[q]>A[r])
			swap(A, q, r);
		swap(A, q, r-1);
	}
	
	public static long quickSort_rand(int[] A, int p, int r) {
		Instant start=Instant.now();
		int q;
		if(p<r) {
			search_rand(A, p, r);
			q=partition(A, p, r);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	public static void search_rand(int[] A, int p, int r) {
		Random rand= new Random();
		int k = rand.nextInt(r+1)+p;
		swap(A, r, k);
	}
	
	public static long heapSort(int[] A) {
		Instant start=Instant.now();
		Build_Max_Heap(A);
		int heap_size=A.length-1;
		for(int j=heap_size;j>0;j--) {
			swap(A, 0, j);
			heap_size--;
			Max_Heapify(A, 0, heap_size);
		}
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	public static void Max_Heapify(int[] A, int i, int heap_size) {
		if(2*i+1>heap_size)
			return;
		int k;
		if(2*i+1==heap_size) 
			k=2*i+1;
		else {
			if(A[2*i+1]>A[2*i+2])
				k=2*i+1;
			else
				k=2*i+2;
		}
		if(A[i]>=A[k])
			return;
		swap(A, i, k);
		Max_Heapify(A, k, heap_size);
	}
	
	public static void Build_Max_Heap(int[] A) {
		for(int i=A.length/2-1;i>=0;i--)
			Max_Heapify(A, i, A.length-1);
	}
	
	public static long javaAlgorithm(int[] A) {
		Instant start=Instant.now();
		Arrays.sort(A);
		Instant end=Instant.now();
		Duration between=Duration.between(start, end);
		return between.toMillis();
	}
	
	public static void swap(int[] A, int a, int b) {
		int tmp=A[a];
		A[a]=A[b];
		A[b]=tmp;
	}
	
	public static void arrayInsert_rand(int[] A, int[] B, int[] C) {
		Random rand = new Random();
		for(int i=0;i<1000;i++)
			A[i]=rand.nextInt(1000)+1;
		for(int i=0;i<10000;i++)
			B[i]=rand.nextInt(10000)+1;
		for(int i=0;i<100000;i++)
			C[i]=rand.nextInt(100000)+1;
	}
	
	public static void arrayInsert_reverse(int[] A, int[] B, int[] C) {
		int j=0;
		for(int i=1000;i>=1;i--)
			A[j++]=i;
		j=0;
		for(int i=10000;i>=1;i--)
			B[j++]=i;
		j=0;
		for(int i=100000;i>=1;i--)
			C[j++]=i;
	}
}
