package graph;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    String placeName;
    double longitude;
    double latitude;
    double weight;
    boolean isVisited;
    double d;
    Node pi;
    int index;

    public Node (String placeName, double longitude, double latitude, double weight, int index){
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.weight = weight;
        this.isVisited = false;
        this.index = index;
    }
    @Override
    public int compareTo(Node a){ return this.d>=a.d ? 1:-1; }
}

class Edge{ //크루스칼 노드
    int a;
    int b;
    double weight;
    boolean can;
    public Edge(int a, int b, double weight){
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
}

public class Graph {
    public static ArrayList<LinkedList<Node>> list = new ArrayList<>();
    public static void main(String[] args) {
        makeList(fileOpen("alabama.txt"));
        makeLink(fileOpen("roadList2.txt"));
        Input();
    }

    public static void Input(){
        System.out.print("command : ");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        
        if(command.equals("findHop")){
            String inputName = sc.nextLine();
            LinkedList<Node> l = circuit(inputName);
            findHop(l);
        }
        else if(command.equals("DFS")){
            String inputName = sc.nextLine();
            LinkedList<Node> l = circuit(inputName);
            dfsSave.append(l.getFirst().placeName).append("\t").append(l.getFirst().longitude).append("\t").append(l.getFirst().latitude).append("\n");
            DFS(l);
            saveFile();
        }
        else if(command.equals("Dijkstra")) {
            String place1 = sc.nextLine();
            String place2 = sc.nextLine();
            Dijkstra(place1, place2);
        }
        else if(command.equals("Kruskal")){
            Kruskal();
        }
        else System.out.println("Please try again");
    }

    public static ArrayList<Edge> edge = new ArrayList<>();
    public static int[] arr;
    public static int[] size;
    public static void Kruskal(){
        arr = new int[list.size()];
        size = new int[list.size()];
        for (int i = 0;i<list.size();i++) {
            LinkedList<Node> n = list.get(i);
            for (int j = 1; j < n.size(); j++){
                int index = n.get(j).index;
                if(i<index)
                    edge.add(new Edge(i, index, n.get(j).weight));
            }
        }
        edge.sort((o1, o2) -> o1.weight >= o2.weight ? 1 : -1);
        for(int i = 0;i<list.size();i++){
            arr[i] = i;
            size[i] = 1;
        }
        for (Edge value : edge) {
            if (findSet(value.a) != findSet(value.b)){
                value.can = true;
                weightedUnion(value.a, value.b);
            }
        }
        edge.sort((o1, o2) -> o1.a >= o2.a ? 1 : -1);
        kruskalPrint();
    }
    public static int findSet(int x){
        if(x != arr[x])
            arr[x] = findSet(arr[x]);
        return arr[x];
    }
    public static void weightedUnion(int u, int v){
        int x = findSet(u);
        int y = findSet(v);
        if(size[x]>size[y]){
            arr[x] = y;
            size[x] = size[x] + size[y];
        }
        else{
            arr[y] = x;
            size[y] = size[y] + size[x];
        }
    }
    public static void kruskalPrint(){
        int k=0;
        // 양방향 출력
        for(int i=0;i<list.size();i++){
            LinkedList<Node> n = list.get(i);
            ArrayList<Integer> lst = new ArrayList<>();
            int cnt = 0;
            System.out.print(i+" "+n.getFirst().longitude+" "+n.getFirst().latitude+" ");
            while(k<edge.size()&&edge.get(k).a==i){
                if(edge.get(k).can){
                    cnt++;
                    lst.add(edge.get(k).b);
                }
                k++;
            }
            System.out.print(cnt+" ");
            Collections.sort(lst);
            for (int integer : lst) System.out.print(integer + " ");
            System.out.println();
        }
    }

    public static void Dijkstra(String place1, String place2) {
        PriorityQueue<Node> Q = new PriorityQueue<>();
        for(LinkedList<Node> i : list){
            i.getFirst().d = Double.POSITIVE_INFINITY;
            i.getFirst().pi = null;
        }
        Node n = circuit(place1).getFirst();
        n.d = 0;
        Q.add(n);
        while(!Q.isEmpty()){
            Node u = Q.poll();
            System.out.println(u.placeName);
            if(u.placeName.equals(place2)){
                System.out.println(u.d);
                break;
            }
            LinkedList<Node> uLink = circuit(u.placeName);
            for(int i = 1 ;i <uLink.size();i++){
                LinkedList<Node> vLink = circuit(uLink.get(i).placeName);
                Node v = vLink.getFirst();
                if(v.d > u.d + uLink.get(i).weight){
                    Q.remove(v);
                    v.d = u.d + uLink.get(i).weight;
                    v.pi = u;
                    Q.add(v);
                }
            }
        }
    }

    public static void findHop(LinkedList<Node> link){
        PriorityQueue<Node> Q = new PriorityQueue<>();
        int count =0;
        for(LinkedList<Node> i : list){
            i.getFirst().d = 0;
            i.getFirst().pi = null;
        }
        Q.offer(link.getFirst());
        while(!Q.isEmpty()){
            Node u = Q.poll();
            count++;
            System.out.println(u.placeName+"\t"+u.longitude+"\t"+u.latitude);
            u.isVisited = true;
            if(u.d==10) continue;
            LinkedList<Node> uLink = circuit(u.placeName);
            for(int i = 1 ;i <uLink.size();i++){
                LinkedList<Node> vLink = circuit(uLink.get(i).placeName);
                Node v = vLink.getFirst();
                if(!v.isVisited){
                    v.isVisited = true; v.d = u.d+1; v.pi = u;
                    Q.offer(v);
                }
            }
        }
        System.out.println(count);
    }

    public static StringBuilder dfsSave = new StringBuilder();
    public static void DFS(LinkedList<Node> link){
        link.getFirst().isVisited=true;
        for(int i=1;i<link.size();i++){
            LinkedList<Node> e = circuit(link.get(i).placeName);
            if(!e.getFirst().isVisited){
                dfsSave.append(link.get(i).placeName).append("\t").append(link.get(i).longitude).append("\t").append(link.get(i).latitude).append("\n");
                DFS(e);
            }
        }
    }

    public static void saveFile(){
        try{
            FileWriter writer=new FileWriter("DFS_file.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(dfsSave.toString());
            writer.flush();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("DFS_file.txt 파일을 열 수 없습니다.");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> fileOpen(String filename){
        ArrayList<String> fileset=new ArrayList<>();
        try {
            String path=Graph.class.getResource("").getPath();
            File file = new File(path+ filename);
            BufferedReader bufReader=new BufferedReader(new FileReader(file));
            String line="";
            while((line=bufReader.readLine())!=null)
                fileset.add(line);
            bufReader.close();
            return fileset;
        }catch(FileNotFoundException e) {
        }catch(IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void makeList(ArrayList<String> fileset){
        for(String i : fileset){
            StringTokenizer st = new StringTokenizer(i, "\t");
            LinkedList<Node> l = new LinkedList<>();
            l.add(new Node(st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), 0, list.size()));
            list.add(l);
        }
    }

    public static void makeLink(ArrayList<String> fileset){
        for(int i=0;i<fileset.size();) {
            StringTokenizer st = new StringTokenizer(fileset.get(i), "\t");
            LinkedList<Node> lst = circuit(st.nextToken());
            int j=i;
            while(j<fileset.size()){
                StringTokenizer token = new StringTokenizer(fileset.get(j), "\t");
                if(!lst.getFirst().placeName.equals(token.nextToken())) break;
                LinkedList<Node> twoLst = circuit(token.nextToken());
                Node n = twoLst.getFirst();
                Node n2 = lst.getFirst();
                lst.add(new Node(n.placeName, n.longitude, n.latitude, calDistance(n2.latitude, n2.longitude, n.latitude, n.longitude), list.indexOf(twoLst)));
                twoLst.add(new Node(n2.placeName, n2.longitude, n.latitude, calDistance(n.latitude, n.longitude, n2.latitude, n2.longitude), list.indexOf(lst)));
                j++;
            }
            i=j;
        }
    }

    public static LinkedList<Node> circuit(String name){
        for(LinkedList<Node> e : list){
            if(e.getFirst().placeName.equals(name))
                return e;
        }
        return null;
    }

    public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        return ((rad2deg(Math.acos(dist)) * 60 * 1.1515) * 1.609344) * 1000.0;
    }
    private static double deg2rad(double deg){
        return (double)(deg*Math.PI/(double)180);
    }
    private static double rad2deg(double rad){
        return (double)(rad*(double)180/Math.PI);
    }
}
