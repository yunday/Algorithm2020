package Fianl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node{
    int data;
    ArrayList<Node> edge;
    int state;
    boolean visit;
    public Node(int data){
        this.data = data;
        edge=new ArrayList<>();
    }
}

class Graph{
    public static ArrayList<Node> list;
    int n, m;
    public Graph(ArrayList<Node> list, int n, int m){
        this.list = list;
        this.n=n;
        this.m=m;
    }
    public void isPaint(){
        boolean a = BFS_TS();
        if(a) System.out.println("Yes");
        else System.out.println("No");

    }

    public boolean BFS_TS(){
        PriorityQueue<Node> Q = new PriorityQueue<>();
        for(Node i : list){
            i.state=-1;
        }
        int s=0;
        Q.offer(list.get(0));
        list.get(0).state=0;
        while(!Q.isEmpty()){
            Node u = Q.poll();
            u.visit = true;
            for(int i = 0;i <u.edge.size();i++){
                Node v = list.get(findNode(u.edge.get(i).data));
                if(!v.visit){
                    if(v.state==-1){
                        if(u.state==0) v.state=1;
                        else v.state=0;
                    }
                    else{
                        return false;
                    }
                    Q.offer(v);
                }
            }
        }
        return true;
    }

    public int findNode(int num){
        int i;
        for(i=0;i<list.size();i++){
            if(list.get(i).data==num) break;
        }
        return i;
    }
}

public class Main {
    public static Graph G;
    public static void input(){
        ArrayList<Node> node = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        makeGraph(n, node);
        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            makeEdge(u, v, node);
        }
        G = new Graph(node, n, m);
    }

    public static void makeGraph(int n,ArrayList<Node> node){
        for(int i=0;i<n;i++)
            node.add(new Node(i));
    }

    public static void makeEdge(int u, int v, ArrayList<Node> node){
            node.get(u).edge.add(new Node(v));
            node.get(v).edge.add(new Node(u));
    }

    public static void main(String[] args) {
        input();
        G.isPaint();
    }
}
