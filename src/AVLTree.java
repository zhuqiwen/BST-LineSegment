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
        if(q.parent != null && q.parent.parent != null)
        {
            rebalance(q.parent, key, q, q.parent.parent);
        }

        return search(key);
    }


    private void rebalance(Node parent, K key, Node q, Node grandParent)
    {
        int balanceFactor = grandParent.getBalanceFactor();

        if(lessThan.test(key, parent.data))
        {
            //key 在 node 的左边

            /*
            since balance factor = right.height - left.height,
            positive value means parent is on right

                     grandParent                    grandParent
                           \                             \
                            \                             \                            q
                          parent        ---->>             q          -->>            /  \
                            /                               \                        /    \
                           /                                 \              grandParent   parent
                          q                                parent

             */
            if(balanceFactor > 1)
            {
                // RL
            }

            /*
            since balance factor = right.height - left.height,
            negative value means parent is on left

                       grandParent
                        /
                       /                                   parent
                     parent            ---->>               /  \
                     /                                     /    \
                    /                                     q    grandParent
                   q
             */
            if(balanceFactor < -1)
            {
                //LL
            }
        }

        if(lessThan.test(parent.data, key))
        {
            //key 在 node 的右边

            /*

                       grandParent
                           \
                            \                       parent
                            parent   ---->>          /  \
                              \                     /    \
                               \          grandParent     q
                               q
             */

            if(balanceFactor > 1)
            {
                //RR
            }

            /*

                       grandParent            grandParent
                           /                        /
                          /                        /                           p
                        parent      ---->>        p         ---->>           /   \
                          \                      /                          /     \
                           \                    /                   grandParent  parent
                            q                 parent
             */
            if(balanceFactor < -1)
            {
                //LR
            }
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
