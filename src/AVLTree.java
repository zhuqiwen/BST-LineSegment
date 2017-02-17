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


//        System.out.println("root: " + root.data);
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
                if(lessThan.test(low.data, middle.data))
                {
                    //RL
//                    return;
                    rightLeftRotate(high);
                }

                if(lessThan.test(middle.data, low.data))
                {
                    //RR
                    rightRotate(high);
//                    return;
                }
            }

            // left overweight
            if(balanceFactor < -1)
            {

                if(lessThan.test(low.data, middle.data))
                {

                    //LL
                    leftRotate(high);

                }

                if(lessThan.test(middle.data, low.data))
                {
                    //LR
//                    return;
                    leftRightRotate(high);
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

    private void leftRightRotate(Node pivot)
    {
        Node middle = pivot.left;
        Node low = middle.right;

        if(pivot == root)
        {
            root = low;
            low.parent = null;
        }
        else
        {
            if(pivot.parent.left == pivot)
            {
                pivot.parent.left = low;
            }
            else
            {
                pivot.parent.right = low;
            }

            low.parent = pivot.parent;
        }

        pivot.left = low.right;
        if(low.right != null)
        {
            low.right.parent = pivot;

        }

        middle.right = low.left;
        if(low.left != null)
        {
            low.left.parent = middle;
        }

        low.left = middle;
        middle.parent = low;

        low.right = pivot;
        pivot.parent = low;

        pivot.fixHeight();
        middle.fixHeight();
        low.fixHeight();


    }

    private void rightLeftRotate(Node pivot)
    {
        Node middle = pivot.right;
        Node low = middle.left;

        if(pivot == root)
        {
            root = low;
            low.parent = null;
        }
        else
        {
            if(pivot.parent.left == pivot)
            {
                pivot.parent.left = low;
            }
            else
            {
                pivot.parent.right = low;
            }

            low.parent = pivot.parent;
        }

        pivot.right = low.left;
        if(low.left != null)
        {
            low.left.parent = pivot;

        }

        middle.left = low.right;
        if(low.right != null)
        {
            low.right.parent = middle;
        }

        low.left = pivot;
        pivot.parent = low;

        low.right = middle;
        middle.parent = low;

        pivot.fixHeight();
        middle.fixHeight();
        low.fixHeight();

    }
}
