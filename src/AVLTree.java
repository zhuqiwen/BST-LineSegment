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

        // after insert, do rebalance
        rebalance(q.parent, key);

        return search(key);
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
        return 0;
    }

    private void rebalance(Node node, K key)
    {
        int balanceFactor = getBalanceFactor(node);

        if(lessThan.test(key, node.data))
        {
            //key 在 node 的左边
        }

        if(lessThan.test(node.data, key))
        {
            //key 在 node 的右边
        }

    }

    private Node leftRotate(Node node)
    {
        return node;
    }

    private Node rightRotate(Node node)
    {
        return node;
    }
}
