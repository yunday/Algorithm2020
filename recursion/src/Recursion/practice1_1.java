package Recursion;

import java.util.Scanner;

public class practice1_1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] data=new int[n];
        readFrom(n, data, sc);
        int k=sc.nextInt();
        System.out.println(rankCount(n, data, k, 0));
    }
    public static void readFrom(int n, int[] data, Scanner sc){
        if(n==0)
            return;
        else{
            readFrom(n-1, data, sc);
            data[n-1]=sc.nextInt();
        }
    }
    public static int rankCount(int n, int[] data, int k, int count){
        if(n==0)
            return count+1;
        if(data[n-1]<k)
            count++;
        return rankCount(n-1, data, k, count);
    }
}
