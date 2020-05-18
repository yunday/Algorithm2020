package Recursion;

import java.util.Scanner;
import java.lang.Math;

public class practice1_4 {
//    public static void main(String[] args){
//        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();
//        int[] data=new int[n];
//        readFrom(n, data, sc);
//        int k=sc.nextInt();
//        System.out.println(findNearest(0, n, data, k));
//    }
    public static void readFrom(int n, int[] data, Scanner sc){
        if(n==0)
            return;
        else{
            readFrom(n-1, data, sc);
            data[n-1]=sc.nextInt();
        }
    }
    public static int findNearest(int start, int end, int[] data, int k){
        if(end-start==1)
            return data[start];
        if(Math.abs(k-data[(start+end)/2])<Math.abs(k-data[(start+end)/2-1]))
            return findNearest((start+end)/2, end, data, k);
        else
            return findNearest(start, (start+end)/2, data, k);
    }
}
