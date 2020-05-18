package redblack;

public class Main {
    static class Node{
        int key;
        int color;
        Node left;
        Node right;
        Node parents;
        public Node(int key){
            this.key=key;
            this.color=1;
            this.left=null;
            this.right=null;
            this.parents=null;
        }
    }
    static class redBlackTree{
        Node root = null;
        void left_Rotate(Node x){
            Node y=null;
            y = x.right;
            if(y.left!=null){
                x.right = y.left;
                y.left.parents = x;
            }
            else
                x.right=null;
            y.parents = x.parents;
            if(x.parents == null)
                this.root = y;
            else if(x == x.parents.left)
                x.parents.left = y;
            else
                x.parents.right = y;
            y.left = x;
            x.parents = y;
        }
        void right_Rotate(Node y){
            Node x=null;
            x=y.left;
            y.left=x.right;
            x.right.parents = y;
            x.parents = y.parents;
            if(y.parents == this.root)
                this.root = x;
            else if(y == y.parents.right)
                y.parents.right= y;
            else
                y.parents.left = y;
            x.left = y;
            y.parents = x;
        }
        void rb_Insert(Node z){
            Node y= null;
            Node x= this.root;
            while(x!=null){
                y=x;
                if(z.key < x.key)
                    x=x.left;
                else
                    x=x.right;
            }
            z.parents = y;
            if(y == null)
                this.root = z;
            else if(z.key < y.key)
                y.left = z;
            else
                y.right = z;
            z.left = null;
            z.right = null;
            z.color = 1;
            rb_Insert_Fixup(z);
        }
        void rb_Insert_Fixup(Node z){
            Node y = null;
            while(z.parents!=null && z.parents.color == 1){
                if(z.parents == z.parents.parents.left){
                    y = z.parents.parents.right;
                    if(y!=null && y.color == 1){
                        z.parents.color = 0;
                        y.color = 0;
                        z.parents.parents.color = 1;
                        z=z.parents.parents;
                    }
                    else if(z == z.parents.right){
                        z = z.parents;
                        left_Rotate(z);
                        z.parents.color = 0;
                        z.parents.parents.color = 1;
                        right_Rotate(z.parents.parents);
                    }
                    else{
                        z.parents.color = 0;
                        z.parents.parents.color = 1;
                        right_Rotate(z.parents.parents);
                    }
                }
                else{
                    y = z.parents.parents.left;
                    if(y!=null && y.color == 1){
                        z.parents.color = 0;
                        y.color = 0;
                        z.parents.parents.color = 1;
                        z=z.parents.parents;
                    }
                    else if(z == z.parents.left){
                        z = z.parents;
                        right_Rotate(z);
                        z.parents.color = 0;
                        z.parents.parents.color = 1;
                        left_Rotate(z.parents.parents);
                    }
                    else{
                        z.parents.color = 0;
                        z.parents.parents.color = 1;
                        left_Rotate(z.parents.parents);
                    }
                }
            }
            this.root.color = 0;
        }
        Node rb_Delete(Node z){
            Node y;
            Node x;
            if(z.left == null || z.right == null)
                y = z;
            else
                y = treeSuccessor(z);
            if(y.left != null)
                x = y.left;
            else
                x = y.right;
            x.parents = y.parents;
            if(y.parents == null)
                this.root = x;
            else if(y == y.parents.left)
                y.parents.left = x;
            else
                y.parents.right = x;
            if(y != z)
                z.key = y.key;
            if(y.color == 0)
                rb_Delete_Fixup(x);
            return y;
        }
        void rb_Delete_Fixup(Node x){
            Node w;
            while(x!=this.root && x.color == 0){
                if(x == x.parents.left){
                    w = x.parents.right;
                    if(x.color == 1){
                        w.color = 0;
                        x.parents.color = 1;
                        left_Rotate(x.parents);
                        w = x.parents.right;
                    }
                    if(w.left.color == 0 && w.right.color == 0){
                        w.color = 1;
                        x = x.parents;
                    }
                    else if(w.right.color == 0){
                        w.left.color = 0;
                        w.color = 1;
                        right_Rotate(w);
                        w = x.parents.right;
                    }
                    else{
                        x.color = x.parents.color;
                        x.parents.color = 0;
                        w.right.color = 0;
                        left_Rotate(x.parents);
                        x = this.root;
                    }
                }
                else{
                    w = x.parents.left;
                    if(x.color == 1){
                        w.color = 0;
                        x.parents.color = 1;
                        right_Rotate(x.parents);
                        w = x.parents.left;
                    }
                    if(w.left.color == 0 && w.right.color == 0){
                        w.color = 1;
                        x = x.parents;
                    }
                    else if(w.left.color == 0){
                        w.right.color = 0;
                        w.color = 1;
                        left_Rotate(w);
                        w = x.parents.left;
                    }
                    else{
                        x.color = x.parents.color;
                        x.parents.color = 0;
                        w.left.color = 0;
                        right_Rotate(x.parents);
                        x = this.root;
                    }
                }
            }
            x.color = 0;
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
    }

    public static void main(String[] args) {
        redBlackTree tree = new redBlackTree();
        for(int i=0;i<10;i++)
            tree.rb_Insert(new Node(i));
        tree.rb_Delete(tree.root.right.right.right);
    }

}