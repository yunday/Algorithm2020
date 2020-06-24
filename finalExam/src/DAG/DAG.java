package DAG;

import java.util.*;

class Node{
    String data;
    boolean visiting;
    List<Node>edge;
    public Node(String data,boolean mission){
        this.data=data;
        edge= (mission)? new LinkedList<>():null;
        visiting=false;
    }
}

class Graph{
    ArrayList<Node>nodes;
    int length;

    public Graph(int length,List<Node> arr){
        this.length=length;
        nodes=(ArrayList<Node>)arr;
    }
    public int getLength(){return length;}
    public void insertGraph(int index, String data){
        Node node=nodes.get(index);
        node.edge.add(new Node(data,false));
    }
    public void topologicalSort2(){
        LinkedList<Node> list=new LinkedList<>();
        int i=0;
        for(Node n:nodes){
            for(Node n2:n.edge) n2.visiting=false;
        }
        for(Node n:nodes){
            if(!n.visiting) DFS_TS(n,list);
        }
        int max=list.size();
        for(Node n:list){
            if(i<max-1) System.out.print(n.data+" -> ");
            else System.out.println(n.data);
            i++;
        }
    }
    public void DFS_TS(Node n,LinkedList<Node>list){
        makeVisiting(n);
        for(Node n1:n.edge) {
            if (!n1.visiting) DFS_TS(findNode(n1.data), list);
        }
        list.addFirst(n);
    }
    public void makeVisiting(Node node){
        node.visiting=true;
        for(Node n1:nodes){
            for(Node n2:n1.edge){
                if(n2.data.equals(node.data))  n2.visiting=true;
            }
        }
    }
    public Node findNode(String data){//인접리스트안 배열에서 큐에서 빼낸 노드와 데이터가 같은 배열 요소를 찾음.
        Node node=null;
        for(Node n:nodes){
            if(n.data.equals(data)) {
                node=n; break;
            }
        }
        return node;
    }
}

public class DAG {
    public static Graph g;
    public static void main(String[] args) {
        ArrayList<Node> nodes=new ArrayList<>();
        makeGraph(nodes);
        g.topologicalSort2();
    }
    private static void makeGraph(List<Node>nodes){
        nodes.add(new Node("냄비에물붓기",true));
        nodes.add(new Node("점화",true));
        nodes.add(new Node("라면넣기",true));
        nodes.add(new Node("계란풀어넣기",true));
        nodes.add(new Node("수프넣기",true));
        nodes.add(new Node("라면봉지뜯기",true));
        makeEdge(nodes);
    }
    private static void makeEdge(List<Node>nodes) {
        g = new Graph(nodes.size(), nodes);
        g.insertGraph(findIndex(nodes,"냄비에물붓기"),"점화");
        g.insertGraph(findIndex(nodes,"점화"),"수프넣기");
        g.insertGraph(findIndex(nodes,"점화"),"계란풀어넣기");
        g.insertGraph(findIndex(nodes,"점화"),"라면넣기");
        g.insertGraph(findIndex(nodes,"라면넣기"),"계란풀어넣기");
        g.insertGraph(findIndex(nodes, "수프넣기"),"계란풀어넣기");
        g.insertGraph(findIndex(nodes,"라면봉지뜯기"),"라면넣기");
        g.insertGraph(findIndex(nodes,"라면봉지뜯기"),"수프넣기");
    }
    private static int findIndex(List<Node>nodes,String data){
        int i=0;
        for(i=0;i<nodes.size();i++){
            if(nodes.get(i).data.equals(data)) break;
        }
        return i;
    }
}