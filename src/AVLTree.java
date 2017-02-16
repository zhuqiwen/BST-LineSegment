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

        // after insert, we should check if the current tree is balanced
        if(!checkBalance())
        {
            rebalance();
        }


        return search(key);
    }

    private boolean checkBalance()
    {
        return true;
    }

    /**
     * calculate balance factor of a node; will be used in checkBalance()
     * return -1, 0, or 1 if balanced
     * return other values if not balanced; then need to rebalance the tree
     * @param node
     * @return int value that represent status of balance of the passed node
     */
    private int getBalanceFactor(Node node)
    {
        if(node != null)
        {
            return node.left.height - node.right.height;
        }

        return -1;
    }

    private void rebalance()
    {

    }
}
