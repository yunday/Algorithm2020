package graph;

import java.util.*;
import java.io.*;

class Node{
    String placeName;
    double longitude;
    double latitude;
    double weight;
    public Node (String placeName, double longitude, double latitude, double weight){
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
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
        Scanner sc = new Scanner(System.in);
        String inputName = sc.nextLine();
        LinkedList<Node> l = circuit(inputName);
        for(int i=0;i<10;i++)
            System.out.println(l.get(i).placeName+"\t" +l.get(i).latitude+"\t"+ l.get(i).longitude);
    }

    public static ArrayList<String> fileOpen(String filename){
        ArrayList<String> fileset=new ArrayList<>();
        try {
            String path=Graph.class.getResource("").getPath();
            File file = new File(path+ "filename");
            BufferedReader bufReader=new BufferedReader(new FileReader(file));
            String line="";
            while((line=bufReader.readLine())!=null) {
                StringTokenizer token = new StringTokenizer(line);
                while(token.hasMoreTokens())
                    fileset.add(token.nextToken(" "));
            }
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
            list.add(i, l);
        }
    }

    public static void makeLink(ArrayList<String> fileset){
        for(int i=0;i<fileset.size();) {
            StringTokenizer st = new StringTokenizer(fileset.get(i), "\t");
            LinkedList<Node> lst = circuit(st.nextToken());
            int j=i;
            while(j<fileset.size()){
                StringTokenizer token = new StringTokenizer(fileset.get(j++), "\t");
                if(!lst.getFirst().placeName.equals(token.nextToken())){
                    i=j; break;
                }
                Node n = circuit(token.nextToken()).getFirst();
                lst.add(new Node(n.placeName, n.longitude, n.latitude, calDistance(lst.getFirst().latitude, lst.getFirst().longitude, n.latitude, n.longitude)));
            }
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
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        dist = dist * 1000.0;
        return dist;
    }
    private static double deg2rad(double deg){
        return (double)(deg*Math.PI/(double)100);
    }
    private static double rad2deg(double rad){
        return (double)(rad*(double)180/Math.PI);
    }
}
