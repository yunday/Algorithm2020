package recursion2;

import java.util.Scanner;

public class practice1_2 {
    public static int[][] maze;
    public static int n, result=0;
    static final int PATH_COLOUR =2;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        maze=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                maze[i][j]=sc.nextInt();
        }
        find(0, 0);
        if(result==1)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
    public static void find(int x, int y){
        if(x==n-1 && y==n-1) {
            result=1;
        }
        int sub_x, sub_y;
        if(x>=1&&x<n){
            if(maze[x-1][y]!=1){
                sub_x=x;
                while(sub_x>0&&maze[sub_x-1][y]!=1)
                    sub_x--;
                marking(sub_x, y);
            }
        }
        if(y>=0&&y<n-1){
            if(maze[x][y+1]!=1){
                sub_y=y;
                while(sub_y<n-1&&maze[x][sub_y+1]!=1)
                    sub_y++;
                marking(x, sub_y);
            }
        }
        if(x>=0&&x<n-1){
            if(maze[x+1][y]!=1){
                sub_x=x;
                while(sub_x<n-1&&maze[sub_x+1][y]!=1)
                    sub_x++;
                marking(sub_x, y);
            }
        }
        if(y>=1&&y<n){
            if(maze[x][y-1]!=1){
                sub_y=y;
                while(sub_y>0&&maze[x][sub_y-1]!=1)
                    sub_y--;
                marking(x, sub_y);
            }
        }
    }
    public static void marking(int x, int y){
        if(maze[x][y]!=2){
            maze[x][y]=PATH_COLOUR;
            find(x, y);
        }
    }
}
