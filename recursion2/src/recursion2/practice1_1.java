package recursion2;

import java.util.Scanner;

public class practice1_1 {
    public static int n, k, count=0;
    static final int PATHWAY_COLOUR=0;
    static final int WALL_COLOUR =1;
    static final int PATH_COLOUR =2;
    public static int[][] maze;

  /*  public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n= sc.nextInt();

        maze=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                maze[i][j]=sc.nextInt();
        }
        k=sc.nextInt();
        find(0, 0, 0);
        System.out.println(count);
    }*/

    public static void find(int x, int y, int mov)
    {
        if(x==n-1 && y==n-1) {
            if(mov<=k)
                count++;
            return;
        }
        maze[x][y]=PATH_COLOUR;
        mov++;
        if(x>=1 && x<n) {
            if(maze[x-1][y]==0)
                find(x-1,y, mov);
        }

        if(y>=0 && y<n-1) {
            if(maze[x][y+1]==0) {
                find(x,y+1, mov);
            }
        }

        if(x>=0 && x<n-1) {
            if (maze[x + 1][y] == 0) {
                find(x + 1, y, mov);
            }
        }

        if(y>=1 && x<n) {
            if (maze[x][y - 1] == 0) {
                find(x, y - 1, mov);
            }
        }
        maze[x][y]=PATHWAY_COLOUR;
    }
}
