package Matrix;

import java.util.*;

class Point{
    int i,j,k;
    public Point(int i,int j,int k){
        this.i=i;
        this.j=j;
        this.k=k;
    }
}

public class MatrixChainMultiplication {
    public static int []p={1,3,2,4,6,1,3,2,5};
    public static int[][]m=new int[p.length-1][p.length-1];
    public static List<Point> list=new LinkedList<>();
    public static void main(String[] args) {
        for (int[] ints : m) Arrays.fill(ints, -1);
        System.out.println(matrixChain(p.length-1));
        printMatrix(0,7);
    }
    private static int matrixChain(int n){
        for(int i=0;i<n;i++) m[i][i]=0;
        for(int r=0;r<n-1;r++){//대각선의 총 갯수는 n-1개.
            for(int i=0;i<n-r-1;i++){//각 대각선이 가지는 값의 갯수.
                int j=i+r+1;
                int num=i;
                m[i][j]=m[i+1][j]+p[i]*p[i+1]*p[j+1];// 무조건 A(BCDE....Z) 이러한 형태.
                for(int k=i+1;k<=j-1;k++){
                    if(m[i][j]>m[i][k]+m[k+1][j]+p[i]*p[k+1]*p[j+1]) {
                        m[i][j] = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                        num=k;
                    }
                }
                list.add(new Point(i,j,num));
            }
        }
        return m[0][n-1];
    }
    private static void printMatrix(int i,int j){
        if(i==j)  System.out.print("A"+(j+1));
        else if(i==j-1) System.out.print("("+"A"+j+"A"+(j+1)+")");
        else{
            Point p=findK(i,j);
            if(j!=m.length-1) System.out.print("(");
            printMatrix(i,p.k);
            printMatrix(p.k+1,j);
            if(j!=m.length-1) System.out.print(")");
        }
    }
    private static Point findK(int i,int j){
        Point p1=null;
        for(Point p2:list){
            if(p2.i==i&&p2.j==j){
                p1=p2;
                break;
            }
        }
        return p1;
    }
}