package hash;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Suffix{
    String sword;
    int freq;
    public Suffix(String sword){
        this.sword = sword;
        this.freq = 1;
    }
}
class Prefix{
    String pword1;
    String pword2;
    LinkedList<Suffix> suf;
    int sumFreq;
    public Prefix(String pword1, String pword2, Suffix suf){
        this.pword1 = pword1;
        this.pword2 = pword2;
        this.suf = new LinkedList<>();
        this.suf.addFirst(suf);
        this.sumFreq = 1;
    }
}

public class Hash {
    static final int NHASH = 4093;
    static LinkedList<Prefix>[] htable = new LinkedList[NHASH];

    public static void main(String[] args) {
        MarkovChain(fileOpen());
    }

    public static void MarkovChain(Prefix start){
        Random rand = new Random();
        System.out.print(start.pword1 + " "+start.pword2 +" ");
        Prefix n = start;
        int count = 2;
        while(!n.suf.getFirst().sword.equals("[end]")&&count<=1000) {
            String result = getWeightedRandom(expec(n), rand);
            System.out.print(result+" ");
            count++;
            n = searchPre(n.pword2, result, hash(n.pword2, result));
        }
    }

    public static Map<String, Double> expec(Prefix t){
        Map<String, Double> w = new HashMap<String, Double>();
        for(Suffix s : t.suf)
            w.put(s.sword,(100*((double)s.freq/(double)t.sumFreq)));
        return w;
    }

    public static <E> E getWeightedRandom(Map<E, Double> weights, Random random) {
        E result = null;
        double bestValue = Double.MAX_VALUE;
        for (E element : weights.keySet()) {
            double value = -Math.log(random.nextDouble()) / weights.get(element);
            if (value < bestValue) {
                bestValue = value;
                result = element;
            }
        }
        return result;
    }

    public static Prefix fileOpen(){
        ArrayList<String> fileset=new ArrayList<>();
        try {
            String path=Hash.class.getResource("").getPath();
            File file = new File(path+ "hash/test8.txt");
            BufferedReader bufReader=new BufferedReader(new FileReader(file));
            String line="";
            while((line=bufReader.readLine())!=null) {
                StringTokenizer token = new StringTokenizer(line);
                while(token.hasMoreTokens())
                    fileset.add(token.nextToken(" "));
            }
            bufReader.close();
            newTable(fileset);
            return htable[hash(fileset.get(0), fileset.get(1))].get(0);
        }catch(FileNotFoundException e) {
        }catch(IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void newTable(ArrayList<String> fileset){
        for(int i=0;i<fileset.size()-2;i++){
            String a = fileset.get(i),  b= fileset.get(i+1), c = fileset.get(i+2);
            int hashing = hash(a, b);
            LinkedList<Prefix> node = htable[hashing];
            if(node == null){
                node = new LinkedList<Prefix>();
                htable[hashing] = node;
            }
            Prefix n = searchPre(a, b, hashing);
            if(n==null) node.addLast(new Prefix(a, b, new Suffix(c)));
            else{
                Suffix s = searchSuf(n.suf, c);
                if(s == null) n.suf.addLast(new Suffix(c));
                else s.freq++;
                n.sumFreq++;
            }
        }
    }

    public static Prefix searchPre(String a, String b, int hashing){
        for(Prefix n : htable[hashing]){
            if(n.pword1.equals(a)&&n.pword2.equals(b))
                return n;
        }
        return null;
    }

    public static Suffix searchSuf(LinkedList<Suffix> s, String c){
        for(Suffix e : s){
            if(e.sword.equals(c))
                return e;
        }
        return null;
    }

    public static int hash(String key1, String key2){
        final int MULTIPLIER = 31;
        BigInteger h = BigInteger.ZERO;
        for(int p = 0; p < key1.length(); p++)
            h = h.multiply(BigInteger.valueOf(MULTIPLIER)).add(BigInteger.valueOf(key1.charAt(p)));
        for(int p = 0; p < key2.length(); p++)
            h = h.multiply(BigInteger.valueOf(MULTIPLIER)).add(BigInteger.valueOf(key2.charAt(p)));
        return h.mod(BigInteger.valueOf(NHASH)).intValue();
    }
}
