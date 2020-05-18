package Recursion;

import java.util.Scanner;

public class practice1_3 {
//    public static void main(String[] args){
//        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();
//        int[] data=new int[n];
//        readFrom(n, data, sc);
//        int k=sc.nextInt();
//        System.out.println(sumCount(n, data, k, 0));
//    }
    public static void readFrom(int n, int[] data, Scanner sc){
        if(n==0)
            return;
        else{
            readFrom(n-1, data, sc);
            data[n-1]=sc.nextInt();
        }
    }
    public static int sumCount(int n, int[] data, int k, int count){
        if(n==2)
            return count;
        return sumCount(n-1, data, k, getSecond(n-2, data, k, getThird(n-2, data, k, count, n-1, n-2),n-1));
    }
    public static int getSecond(int n, int[] data, int k, int count, int first){
        if(n==1)
            return count;
        return getSecond(n-1, data, k, getThird(n-1, data, k, count, first, n-1), first);
    }
    public static int getThird(int n, int[] data, int k, int count, int first, int second){
        if(n==0)
            return count;
        if(data[n-1]+data[first]+data[second]==k)
            count++;
        return getThird(n-1, data, k, count, first, second);
    }
}
