package graph;

import java.util.*;
import java.io.*;

class Node{
    String placeName;
    double longitude;
    double latitude;
    double weight;
    boolean isVisited;
    public Node (String placeName, double longitude, double latitude, double weight){
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.weight = weight;
        this.isVisited = false;
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
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String inputName = sc.nextLine();
        LinkedList<Node> l = circuit(inputName);
        
        if(command.equals("findHop")) findHop(l, 0);
        else if(command.equals("DFS")) DFS(l);
        else System.out.println("Please try again");
    }

    public static void findHop(LinkedList<Node> link, int count){
        count++;
        link.getFirst().isVisited = true;
        if(count ==11) return;
        for(int i=1;i<link.size();i++){
            LinkedList<Node> e = circuit(link.get(i).placeName);
            if(!e.getFirst().isVisited){
                System.out.println(link.get(i).placeName+"\t"+link.get(i).longitude+"\t"+link.get(i).latitude);
                findHop(e, count);
            }
        }
    }

    public static void DFS(LinkedList<Node> link){
        link.getFirst().isVisited=true;
        for(int i=1;i<link.size();i++){
            LinkedList<Node> e = circuit(link.get(i).placeName);
            if(!e.getFirst().isVisited){
                System.out.println(link.get(i).placeName+"\t"+link.get(i).longitude+"\t"+link.get(i).latitude);
                DFS(e);
            }
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
        for(int i=0;i<fileset.size();i++){
            StringTokenizer st = new StringTokenizer(fileset.get(i), "\t");
            LinkedList<Node> l = new LinkedList<>();
            l.add(new Node(st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), 0));
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
                lst.add(new Node(n.placeName, n.longitude, n.latitude, calDistance(n2.latitude, n2.longitude, n.latitude, n.longitude)));
                twoLst.add(new Node(n2.placeName, n2.longitude, n.latitude, calDistance(n.latitude, n.longitude, n2.latitude, n2.longitude)));
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
        return (double)(deg*Math.PI/(double)100);
    }
    private static double rad2deg(double rad){
        return (double)(rad*(double)180/Math.PI);
    }
}
