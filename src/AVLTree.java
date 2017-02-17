import java.util.function.BiPredicate;

/**
 * TODO: This is your second major task.
 *
 * This class implements a generic height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * method needs to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    /**
     * TODO
     *
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */

    public Node insert(K key) {
        Node q = super.insert(key);

//        System.out.println(q.data);

        if(q.parent != null && q.parent.parent != null)
        {
            rebalance(q);
        }


        System.out.println("root: " + root.data);
        return search(key);
    }

    private void rebalance(Node low)
    {
        Node middle = low.parent;
        Node high = middle.parent;
        int balanceFactor;

        while (high != null)
        {
            balanceFactor = high.getBalanceFactor();

            //if there is a pivot

            // right overweight
            if(balanceFactor > 1)
            {
                System.out.println("R");
                if(lessThan.test(low.data, middle.data))
                {
                    //RL
                    return;
                }

                if(lessThan.test(middle.data, low.data))
                {
                    //RR
                    rightRotate(high);
                    return;
                }
            }

            // left overweight
            if(balanceFactor < -1)
            {
                System.out.println("L");

                if(lessThan.test(low.data, middle.data))
                {
                    System.out.println("LL");

                    //LL
                    leftRotate(high);
//                    System.out.println("root: ");
//                    System.out.println(root.data);
////                    System.out.println("root.left: ");
////                    System.out.println(this.root.left.data);
//                    System.out.println("root.right: ");
//                    System.out.println(root.right.data);


                    return;
                }

                if(lessThan.test(middle.data, low.data))
                {
                    //LR
                    return;
                }

            }

            //walk up
            low = middle;
            middle = high;
            high = high.parent;
        }

    }

    private void leftRotate(Node pivot)
    {

        Node middle = pivot.left;

        if(pivot == root)
        {
            root = middle;
            middle.parent = null;
        }
        else
        {
            if(pivot.parent.left == pivot)
            {
                pivot.parent.left = middle;
            }
            else
            {
                pivot.parent.right = middle;
            }

            middle.parent = pivot.parent;
        }

        pivot.left = middle.right;
        if(middle.right != null)
        {
            middle.right.parent = pivot;
        }

        middle.right = pivot;
        pivot.parent = middle;

        pivot.fixHeight();
        middle.fixHeight();

    }

    private void rightRotate(Node pivot)
    {
        Node middle = pivot.right;

        if(pivot == root)
        {
            root = middle;
            middle.parent = null;
        }
        else
        {
            if(pivot.parent.right == pivot)
            {
                pivot.parent.right = middle;
            }
            else
            {
                pivot.parent.left = middle;
            }

            middle.parent = pivot.parent;

        }

        pivot.right = middle.left;
        if(middle.left != null)
        {
            middle.left.parent = pivot;
        }

        middle.left = pivot;
        pivot.parent = middle;

        pivot.fixHeight();
        middle.fixHeight();
    }
}
