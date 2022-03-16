
package addressbook;

/*
RED-BLACK TREE
create a Node class that will represent the nodes in a red-black tree using
generics. 
*/
class Node<T, U> {
    T Name;
    U Office;
    Node<T, U> parent;
    Node<T, U> left;
    Node<T, U> right;

    int color;

    Node() {
        parent = null;
        left = null;
        right = null;
        color = 0;

    }

    Node(T Name, U Office) {
        this.Name = Name;
        this.Office = Office;
    }
}

/*
You will create an ADT Red-Black Tree class called AddressBookTree with Java
Generics (type T and type U). 
*/
public class AddressBookTree<T extends Comparable<T>, U> {

    private final Node<T, U> Tnil = new Node<>();
    private Node<T, U> root = Tnil;

    public AddressBookTree() {
        root.parent = Tnil;
        root.left = Tnil;
        root.right = Tnil;

    }

    //////////////////////////////////////////////////////////////////
    //just gets root
    public Node<T, U> getRoot() {
        return this.root;
    }

    //////////////////////////////////////////////////////////////////
    public void insert(T Name, U Office)// Parameter takes two arguments: T name, U office
    {
        //declaring varibles that are used, stuck with pseudo code variables

        Node<T, U> y = Tnil;
        Node<T, U> x = root;
        Node<T, U> z = new Node<>(Name, Office);
        //pseudo code
        //code key = name
        //p = parent
        y = Tnil;
        x = root;
        while (x != Tnil) {
            y = x;
            if (z.Name.compareTo(x.Name) < 0)
                x = x.left;
            else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == Tnil) {
            root = z;
        } else if (z.Name.compareTo(y.Name) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = Tnil;
        z.right = Tnil;
        z.color = 1;
        insertFix(z);
    }
//////////////////////////////////////////////////////////////////

    public void insertFix(Node<T, U> z)//insertion rules
    //Parameter takes one argument: Node<T, U>
    {
        //declaring varibles that are used, stuck with pseudo code variables

        Node<T, U> y;

        while (z.parent.color == 1) {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;

                if (y.color == 1) {
                    z.parent.color = 0;

                    y.color = 0;
                    z.parent.parent.color = 1;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                } else {
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    rightRotate(z.parent.parent);
                }

            } else {
                //pasted top code her and flipped left and right
                y = z.parent.parent.left;

                if (y.color == 1) {
                    z.parent.color = 0;

                    y.color = 0;
                    z.parent.parent.color = 1;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {
                    z = z.parent;
                    rightRotate(z);
                } else {
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    leftRotate(z.parent.parent);

                }
            }
        }
        root.color = 0;
    }

//////////////////////////////////////////////////////////////////

    public void deleteNode(T Name)

    //Parameter takes one argument: T name
    {
        //declaring varibles that are used
        Node<T, U> x = Tnil;
        Node<T, U> z = findNode(Name);
       
        int originalC;
        Node y = z;
        originalC = y.color;
        if (z.left == Tnil) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == Tnil) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = treeMin(z.right);
            originalC = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);

                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (originalC == 0) {
            deleteFix(x);
        }
    }

    //finds node
    private Node<T, U> findNode(T Name) {
        Node<T, U> point = root;
        //if node not there return point
        while (point != Tnil) {
            //if point found return
            if (point.Name.equals(Name)) {
                return point;
            }
            //check left
            if (point.Name.compareTo(Name) < 0) {
                point = point.right;
            } else
            //check right
            {
                point = point.left;
            }
        }
        return Tnil;
    }

//////////////////////////////////////////////////////////////////

    public void deleteFix(Node<T, U> x) //deletion rules
    // Parameter takes one argument: Node<T, U>
    {
        //pseudo code
        //declaring w as null
        Node<T, U> w = Tnil;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                w = x.parent.right;

                if (w.color == 1) {

                    w.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    x = x.parent;
                } else {
                    if (w.right.color == 0) {

                        w.left.color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }

            } else {
                //copy from code ablove but flipped
                w = x.parent.left;

                if (w.color == 1) {

                    w.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == 0 && w.left.color == 0) {
                    w.color = 1;
                    x = x.parent;
                } else {
                    if (w.left.color == 0) {

                        w.right.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }
//////////////////////////////////////////////////////////////////

    public void leftRotate(Node<T, U> x)// Parameter for both takes one argument: Node<T, U>
    {
        
        //p = parent
        Node<T, U> y = x.right;

        x.right = y.left;

        if (y.left != Tnil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == Tnil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    //////////////////////////////////////////////////////////////////
    public void rightRotate(Node<T, U> x)// Parameter for both takes one argument: Node<T, U>
    {
        //same as left rotate but flipped left and right
        Node<T, U> y = x.left;

        x.left = y.right;

        if (y.right != Tnil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == Tnil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y; 
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }

    //////////////////////////////////////////////////////////////////
    public void rbTransplant(Node<T, U> u, Node<T, U> v) {
        
        if (u.parent == Tnil)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    //////////////////////////////////////////////////////////////////
    //recursive in order traversal
    // no arguments needed
    public void display() {
        //calls for an in order print from the root
        inOrderPrint(getRoot());

    }
//handles printing
    private void inOrderPrint(Node<T,U> x)
    {
        if (x != Tnil) {
            inOrderPrint(x.left);
            //copied print format from runner file
            System.out.println(x.Name + ", " + x.Office);
            inOrderPrint(x.right);
        }
    }


//////////////////////////////////////////////////////////////////

    public int countRed(Node<T, U> point) //method to count black nodes in tree
    // Parameter takes Node to count from (the root)
    {
        //counter variable for the red node
        int count = 0;
        if (point == Tnil) {
            return 0;
        }
        count += countRed(point.left);
        count += countRed(point.right);

        if (point.color == 1) {
            count++;
        }
        return count;
    }
//////////////////////////////////////////////////////////////////

    public int countBlack(Node<T, U> point) //method to count black nodes in tree
    // Parameter takes Node to count from (the root)
    {
        //same as count red but flipped the red for black
        int count = 0;
        if (point == Tnil) {
            return 0;
        }
        count += countBlack(point.left);
        count += countBlack(point.right);

        if (point.color == 0) {
            count++;
        }
        return count;
    }

//////////////////////////////////////////////////////////////////
//finds the min
    public Node treeMin(Node<T, U> min) {
        while (min.left != Tnil) {
            min = min.left;
        }
        return min;
    }
//////////////////////////////////////////////////////////////////

}
