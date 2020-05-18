package binarytree;

import java.io.*;
import java.util.*;

public class addressBook {
    static class address{
        String name;
        String company;
        String address;
        String zipcode;
        String phones;
        String email;
        public address(String name, String company, String address, String zipcode, String phones, String email){
            this.name=name;
            this.company=company;
            this.address=address;
            this.zipcode=zipcode;
            this.phones=phones;
            this.email=email;
        }
    }
    static class Node{
        address addressInfo;
        Node left;
        Node right;
        Node parents;
        public Node(address addressInfo){
            this.addressInfo=addressInfo;
            this.left=null;
            this.right=null;
            this.parents=null;
        }
    }
    static class BinaryTree{
        Node root = null;
        int size=0;
        void newInsert(ArrayList<String> fileset){
            for(int i=1;i<fileset.size();i++){
                StringTokenizer token = new StringTokenizer(fileset.get(i));
                String[] onePeople = new String[6];
                int j=0; String tok;
                while(token.hasMoreTokens()) {
                    tok = token.nextToken(",");
                    if (tok.charAt(0) == '\"') {
                        StringBuilder str = new StringBuilder(tok.substring(1));
                        tok = token.nextToken(",");
                        while (tok.charAt(tok.length() - 1) != '\"') {
                            str.append(tok);
                            tok = token.nextToken(",");
                        }
                        str.append(tok);
                        str = new StringBuilder(str.substring(0, str.length() - 1));
                        onePeople[j++] = str.toString();
                    } else
                        onePeople[j++] = tok;
                }
                Insert(root, new Node(new address(onePeople[0], onePeople[1], onePeople[2], onePeople[3], onePeople[4], onePeople[5])));
                this.size++;
            }
        }
        void whatSize(){
            System.out.println(this.size);
        }
        void Insert(Node root, Node node){
            Node y=null;
            Node x=root;
            while(x!=null){
                y=x;
                if(node.addressInfo.name.compareTo(x.addressInfo.name)<0)
                    x=x.left;
                else
                    x=x.right;
            }
            node.parents=y;
            if(y==null)
                this.root=node;
            else if(node.addressInfo.name.compareTo(y.addressInfo.name)<0)
                y.left=node;
            else
                y.right=node;
        }
        void list(Node node){
            if(node==null)
                return;
            list(node.left);
            print(node.addressInfo);
            list(node.right);
        }
        ArrayList<address> save=new ArrayList<>();
        void listSave(Node node){
            if(node==null)
                return;
            listSave(node.left);
            save.add(node.addressInfo);
            listSave(node.right);
        }
        Node find(String command, int state){
            int count=0;
            Node subRoot=root;
            while(subRoot!=null&&!command.equals(subRoot.addressInfo.name)){
                if(state==-1)
                    System.out.println(subRoot.addressInfo.name);
                if(command.compareTo(subRoot.addressInfo.name)<0)
                    subRoot=subRoot.left;
                else
                    subRoot=subRoot.right;
                count++;
            }
            if(state==1){
                if(subRoot==null||count==this.size)
                    System.out.println("Not found");
                else
                    print(subRoot.addressInfo);
                return null;
            }
            else if(state==0)
                return subRoot;
            else{
                if(subRoot!=null)
                    System.out.println(subRoot.addressInfo.name);
                else
                    System.out.println("Not found.");
                return null;
            }
        }
        void delete(String name){
            Node z= find(name, 0);
            Node y=null, x=null;
            if(z==null){
                System.out.println("Not found.");
                return;
            }
            if(z.left==null || z.right==null) y=z;
            else y=treeSuccessor(z);

            if(y.left!=null) x=y.left;
            else if(y.right!=null) x=y.right;

            if(x!=null) x.parents=y.parents;

            if(y.parents==null) root=x;
            else if(y==y.parents.left) y.parents.left=x;
            else y.parents.right=x;

            if(y!=z) z.addressInfo=y.addressInfo;
            System.out.println("Deleted successfully.");
            this.size--;
        }
        Node treeSuccessor(Node x){
            Node y=null;
            if(x.right!=null)
                return treeMinimum(x.right);
            y=x.parents;
            while(y!=null && x==y.right){
                x=y;
                y=y.parents;
            }
            return y;
        }
        Node treeMinimum(Node x){
            while(x.left!=null)
                x=x.left;
            return x;
        }
        void print(address addressInfo){
            System.out.println(addressInfo.name);
            System.out.println("   Company: "+addressInfo.company);
            System.out.println("   Address: "+addressInfo.address);
            System.out.println("   Zipcode: "+addressInfo.zipcode);
            System.out.println("   Phones: "+addressInfo.phones);
            System.out.println("   Email: "+addressInfo.email);
        }
    }
    public static int inputSet(BinaryTree tree){
        Scanner sc = new Scanner(System.in);
        String[] command= new String[2];
        int i=0;
        System.out.print("$ ");
        String input=sc.nextLine();
        StringTokenizer st=new StringTokenizer(input);
        while(st.hasMoreTokens())
            command[i++]=st.nextToken();
        switch (command[0]){
            case "read": fileOpen(command[1], tree); return 1;
            case "list": tree.list(tree.root); return 1;
            case "find": tree.find(command[1], 1); return 1;
            case "trace": tree.find(command[1], -1); return 1;
            case "delete": tree.delete(command[1]); return 1;
            case "save": saveFile(command[1], tree); return 1;
            case "size": tree.whatSize(); return 1;
            case "exit": return -1;
            default: return 1;
        }
    }
    public static void fileOpen(String input, BinaryTree tree){
        ArrayList<String> fileset=new ArrayList<>();
        try {
            String path=addressBook.class.getResource("").getPath();
            File file = new File(path+ input);
            BufferedReader bufReader=new BufferedReader(new FileReader(file));
            String line="";
            while((line=bufReader.readLine())!=null) {
                fileset.add(line);
            }
            bufReader.close();
            tree.newInsert(fileset);
        }catch(FileNotFoundException e) {
        }catch(IOException e) {
            System.out.println(e);
        }
    }
    public static void saveFile(String name, BinaryTree tree){
        try{
            FileWriter writer=new FileWriter(name);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            StringBuilder talFile= new StringBuilder();
            tree.listSave(tree.root);
            for(int i=0;i<tree.save.size();i++){
                address s=tree.save.get(i);
                talFile.append(s.name).append(",").append(s.company).append(",").append(s.address).append(",").append(s.zipcode).append(",").append(s.phones).append(",").append(s.email).append("\n");
            }
            bufferedWriter.write(talFile.toString());
            writer.flush();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println(name+" 파일을 열 수 없습니다.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BinaryTree tree= new BinaryTree();
        while(true){
            if(inputSet(tree)==-1)
                break;
        }
    }
}
