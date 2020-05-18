package binaryTree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

class BinaryTree<E>{
    protected static class Node<E>{
        protected E word;
        protected E comment;
        protected Node<E> left;
        protected Node<E> right;
        public Node(E word, E comment){
            this.word=word;
            this.comment=comment;
            left=null;
            right=null;
        }
        public String toString(){
            return word.toString();
        }
    }
    protected Node<E> root;
    public BinaryTree(){
        root=null;
    }
    protected BinaryTree(Node<E> root){
        this.root=root;
    }
}

public class homework6 {
    public static void main(String[] args) {
        BinaryTree<String> tree=new BinaryTree<>();
        fileOpen("shuffled_dict.txt", tree ,1);
        while(true){
            if(inputSet(tree)==1)
                break;
        }
    }

    public static ArrayList<String> fileOpen(String input, BinaryTree<String> tree, int state){
        ArrayList<String> fileset=new ArrayList<>();
        try {
            String path=homework6.class.getResource("").getPath();
            File file = new File(path+ input);
            BufferedReader bufReader=new BufferedReader(new FileReader(file));
            String line="";
            while((line=bufReader.readLine())!=null) {
                fileset.add(line);
            }
            bufReader.close();
            if(state==1){
                newTreeInsert(fileset, tree, state);
                return null;
            }
            else
                return fileset;
        }catch(FileNotFoundException e) {

        }catch(IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static int inputSet(BinaryTree<String> tree){
        Scanner sc=new Scanner(System.in);
        String[] input_set=new String[2];
        int i=0;
        System.out.print("$ ");
        String input=sc.nextLine();
        StringTokenizer token=new StringTokenizer(input, " ");
        while(token.hasMoreTokens()) {
            input_set[i++]=token.nextToken();
        }
        switch (input_set[0]){
            case "add" : add(tree);return 0;
            case "find" : find(tree, input_set[1], 1); return 0;
            case "size" : whatSize(tree); return 0;
            case "delete" : delete(tree, input_set[1], 1); return 0;
            case "deleteall" : deleteall(tree, input_set[1]); return 0;
            case "exit" : return 1;
        }
        return 0;
    }

    public static int count=0;
    public static void whatSize(BinaryTree<String> tree){
        getSubtree(tree);
        System.out.println(count);
        count=0;
    }
    public static void getSubtree(BinaryTree<String> retree){
        if(retree.root!=null){
            count++;
            if(retree.root.left!=null)
                getSubtree(new BinaryTree<String>(retree.root.left));
            if(retree.root.right!=null)
                getSubtree(new BinaryTree<String>(retree.root.right));
        }
    }

    public static BinaryTree.Node<String> find(BinaryTree<String> tree, String word, int state){
        while(tree.root!=null&&!word.equals(tree.root.word)){
            if(word.compareTo(tree.root.word)<0)
                tree=new BinaryTree<>(tree.root.left);
            else
                tree=new BinaryTree<>(tree.root.right);
        }
        if(tree.root==null){
            if(state==1)
                System.out.println("Not Found");
            return null;
        }
        if(state==1)
            System.out.println(tree.root.comment);
        else
            return tree.root;
        return null;
    }

    public static void add(BinaryTree<String> tree){
        Scanner sc= new Scanner(System.in);
        String word, Class, meaning;
        System.out.print("word: ");
        word=sc.nextLine();
        System.out.print("class: ");
        Class=sc.nextLine();
        System.out.print("meaning: ");
        meaning=sc.nextLine();
        BinaryTree.Node<String> newnode= new BinaryTree.Node<>(word, meaning);
        insert(newnode, tree);
    }

    public static void delete(BinaryTree<String> tree, String word, int state){
        BinaryTree.Node<String> wordSubTree = find(tree, word, 0);
        if(wordSubTree==null){
            if(state==1)
                System.out.println("Not found.");
            return;
        }
        BinaryTree.Node<String> y=null;
        BinaryTree.Node<String> x=null;
        if(wordSubTree.left==null || wordSubTree.right==null)
            y=wordSubTree;
        else
            y=treeSuccessor(wordSubTree, tree);
        if(y.left!=null)
            x=y.left;
        else if(y.right!=null)
            x=y.right;
        BinaryTree.Node<String> para_x;
        BinaryTree.Node<String> para_y=findPar(y, tree);
        if(x!=null){
            para_x=findPar(x, tree);
            para_x=para_y;
        }
        if(para_y==null)
            tree.root=x;
        else if(y==para_y.left)
            para_y.left=x;
        else
            para_y.right=x;
        if(y!=wordSubTree){
            wordSubTree.word=y.word;
            wordSubTree.comment=y.comment;
        }
        if(state==1)
            System.out.println("Deleted successfully.");
    }

    public static BinaryTree.Node<String> treeSuccessor(BinaryTree.Node<String> x, BinaryTree<String> tree){
        BinaryTree.Node<String> y=null;
        if(x.right!=null)
            return treeMinimum(x.right);
        y=findPar(x, tree);
        while(y!=null&&x==y.right){
            x=y;
            y=findPar(y, tree);
        }
        return y;
    }

    public static BinaryTree.Node<String> findPar(BinaryTree.Node<String> x, BinaryTree<String> tree){
        BinaryTree.Node<String> y=null;
        while(tree.root!=null&&x!=tree.root){
            y=tree.root;
            if(x.word.compareTo(tree.root.word)<0)
                tree=new BinaryTree<>(tree.root.left);
            else
                tree=new BinaryTree<>(tree.root.right);
        }
        return y;
    }

    public static BinaryTree.Node<String> treeMinimum(BinaryTree.Node<String> x){
        while(x.left!=null)
            x=x.left;
        return x;
    }

    public static void deleteall(BinaryTree<String> tree, String fileName) {
        ArrayList<String> file = fileOpen(fileName, tree, 0);
        for(int i=0;i<file.size();i++)
            delete(tree, file.get(i), 0);
        System.out.println(file.size()+" words were deleted successfully.");
    }

    public static void newTreeInsert(ArrayList<String> fileset, BinaryTree<String> tree, int state) {
        for(int i=0;i<fileset.size();i++){
            StringTokenizer token=new StringTokenizer(fileset.get(i), "(");
            String word=token.nextToken();
            String Class=token.nextToken(")").substring(1);
            String meaning;
            if(token.hasMoreTokens())
                meaning=token.nextToken().substring(1);
            else
                meaning=null;
            BinaryTree.Node<String> new_node=new BinaryTree.Node<>(word.substring(0, word.length()-1), meaning);
            insert(new_node, tree);
        }
    }

    public static void insert(BinaryTree.Node<String> new_node, BinaryTree<String> tree){
        BinaryTree.Node<String> y=null;
        BinaryTree.Node<String> x=tree.root;
        while(x!=null){
            y=x;
            if(new_node.word.compareTo(x.toString())<0)
                x=x.left;
            else
                x=x.right;
        }
        if(y==null)
            tree.root=new BinaryTree.Node<>(new_node.word, new_node.comment);
        else if(new_node.word.compareTo(y.toString())<0)
            y.left=new_node;
        else
            y.right=new_node;
    }
}