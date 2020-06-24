package floyd;

import java.util.*;


public class FloydWarshallAlgorithm {
    static int INF=10000;
    public static void main(String[] args) {
        int [][]arr={{0,1,INF,4},
                {INF,0,3,2},
                {1,INF,0,5},
                {INF,INF,INF,0}};

        for(int k=0;k<arr.length;k++){//거쳐가는 꼭짓점.
            for(int i=0;i<arr.length;i++){//출발하는 꼭짓점.
                for(int j=0;j<arr.length;j++){//도착하는 꼭짓점.
                    if(arr[i][j]>arr[i][k]+arr[k][j]) arr[i][j]=arr[i][k]+arr[k][j];
                }
            }
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(arr[i][j]==INF) System.out.printf("%4s","INF");
                else System.out.printf("%4d",arr[i][j]);
            }
            System.out.println();
        }
    }
}