package recursion2;

public class group {
    //2번
//    public static int n=3;
////    public static int[][] maze={{0, 0, 0, 0, 1, 0, 0, 0},
////                                {0, 1, 1, 0, 0, 0, 1, 0},
////                                {0, 1, 0, 1, 1, 0, 0, 1},
////                                {0, 0, 0, 0, 1, 1, 1, 0},
////                                {0, 1, 1, 0, 1, 0, 0, 0},
////                                {0, 0, 1, 0, 0, 0, 1, 0},
////                                {0, 0, 0, 1, 1, 0, 0, 0},
////                                {0, 1, 0, 0, 0, 0, 1, 0 }};
//    public static int[][] maze={{0,0,0},{0,0,0},{0,0,0}};
//    public static void main(String[] args){
//            System.out.println(MazePath(0, 0, 0));
//    }
//
//    public static int MazePath(int x, int y, int len){
//        if(x<0||y<0||x>=n||y>=n||maze[x][y]!=0)
//            return -1;
//        else if(x==n-1&&y==n-1){
//            return ++len;
//        }
//        else{
//            maze[x][y]=2;
//            len++;
//            int result1, result2;
//            result1=Math.max(MazePath(x-1, y, len), MazePath(x, y+1, len));
//            result2=Math.max(MazePath(x+1, y, len), MazePath(x, y-1, len));
//            maze[x][y]=0;
//            return Math.max(result1, result2);
//        }
//    }

    //3번
//        public static int[] cols;
//        public static int n=11;
//        public static void main(String[] args){
//            cols= new int[n];
//            System.out.println(queens(-1));
//        }
//        public static int queens(int level){
//            int result=0;
//            if(!promising(level))
//                return 0;
//            else if (level==n-1)
//                return 1;
//            for(int i=0;i<n;i++){
//                cols[level+1]=i;
//                result+=queens(level+1);
//            }
//            return result;
//        }
//        public static boolean promising(int level){
//            for(int i=0;i<level;i++){
//                if(cols[i]==cols[level])
//                    return false;
//                else if(level-i==Math.abs(cols[level]-cols[i]))
//                    return false;
//            }
//            return true;
//        }
}
