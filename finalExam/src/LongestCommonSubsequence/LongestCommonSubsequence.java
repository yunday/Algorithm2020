package LongestCommonSubsequence;

public class LongestCommonSubsequence {
    public static String x="ASJFGHSJKDFGSDJFGSDJFGKJSDGFKSDJGFHSKKASJFGWEUYFGSDJHFGSJSJDHGFDSJGFSDJKFGJHSFDGJHSDGFJSDHGFJHSDGFHJSKDGFIWUEOHFLSDHFJLSDKHFKLSDHFKJSDHFKJHSDSDJHFGJHSDGFJSDHFGJSDHFGAJDHQIHDWIOURYIUWETRIWERYOQIWJDWLSKCBNMXNVB";
    public static String y="KDFJGHKSDJGFJASFDUYWERTFDIUSGFKJDGHODIRYTFKUWFGJHASGFDJHAGSJHFGASJNMXVBSDGFIWUGRIWEUFKSAFGSHJDGFEUWGRIUWQREQOHFWOQFIHKSDHFKJSDGVJKDMNBZXBVCIUGFIQWUYRIWUQFGIUWETRIUQWYEIQUWYEOIQHFJSDKFGHWIEUGFIWEUGFIQWUFUIWQEFGWGFJSDHFGSFUEOQIYWRE";
    public static int [][]c=new int[x.length()+1][y.length()+1];
    public static void main(String[] args) {
        System.out.println(lcs(x.length(),y.length()));
        printWords(x.length(),y.length());//단어 출력.
    }
    private static int lcs(int m,int n){
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(x.charAt(i-1)==y.charAt(j-1)) c[i][j]=c[i-1][j-1]+1;
                else c[i][j]=Math.max(c[i-1][j],c[i][j-1]);
            }
        }
        return c[m][n];
    }
    private static void printWords(int i,int j){
        if(c[i][j]==0) return;
        else if(c[i][j]>c[i-1][j]&&c[i][j]>c[i][j-1]){
            printWords(i-1,j-1);
            System.out.print(x.charAt(i-1));
        }
        else {
            if(c[i][j]==c[i-1][j]) printWords(i-1,j);
            else if(c[i][j]==c[i][j-1]) printWords(i,j-1);
        }
    }
}