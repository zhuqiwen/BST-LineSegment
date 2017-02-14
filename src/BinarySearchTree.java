import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 *
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements Tree<K> {

    /**
     * A Node is a Location, which means that it can be the return value
     * of a search on the tree.
     */

    class Node implements Location<K> {
        protected K data;
        protected Node left, right;
        protected Node parent;     // the parent of this node
        protected int height;      // the height of the subtree rooted at this node
        protected boolean dirty;   // true iff the key in this node has been removed

        /**
         * Constructs a leaf node with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         *
         * Constructs a new node with the given values for fields.
         */
        public Node(K data, Node left, Node right)
        {
            this.data = data;
            this.right = right;
            this.left = left;
            //make sure a new node is not a deleted-node.
            this.dirty = false;

            //update the height of the current node.
            fixHeight();

        }

        /**
         * Return true iff this node is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         *
         * Performs a local update on the height of this node. Assumes that the
         * heights in the child nodes are correct. This function *must* run in
         * O(1) time.
         */
        protected void fixHeight() {
            if(this.isLeaf())
            {
                this.height = 1;
            }
            else if(this.left != null && this.right != null)
            {
                this.height = Math.max(this.left.height, this .right.height) + 1;
            }
            else if(this.left != null)
            {
                this.height = this.left.height + 1;
            }
            else
            {
                this.height = this.right.height + 1;
            }

//            System.out.println("current node -- " + this.get() + ": height --" + this.height);

        }

        /**
         * TODO
         *
         * Returns the data in this node.
         */
        public K get()
        {
            return data;
        }

        /**
         * TODO
         *
         * Returns the location of the node containing the inorder predecessor
         * of this node.
         * left -> root -> right
         */
        public Node getBefore()
        {
            //三种情况?
            //如果是leaf, return null
            //如果有left,则返回left max
            //否则返回自己
            return null;
        }

        /**
         * TODO
         *
         * Returns the location of the node containing the inorder successor
         * of this node.
         * left -> root -> right
         */
        public Node getAfter() {
            return null;
        }


        private Node maxInLeft()
        {
            return null;
        }

        private Node minInRight()
        {
            return null;
        }
    }

    protected Node root;
    protected int n;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     *
     * Looks up the key in this tree and, if found, returns the (possibly dirty)
     * location containing the key.
     */
    public Node search(K key) {
        return null;
    }

    /**
     * TODO
     *
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        return 0;
    }

    /**
     * TODO
     *
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear()
    {
        root = null;
        n = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return n;
    }

    /**
     * TODO
     *
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new node and
     * the heights in all node along the path to the root are adjusted
     * accordingly.
     *
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     *
     * Returns the location where the insert occurred (i.e., the leaf
     * node containing the key).
     */
    public Node insert(K key)
    {
        n++;
        root = insertHelper(key, root);
        return root;
    }


    private Node insertHelper(K key, Node node)
    {
        // if root is empty
        if(node == null)
        {
            return new Node(key);
        }



        // if key < node.data
        if(lessThan.test(key, node.data))
        {
            node.left = insertHelper(key, node.left);
            node.left.parent = node;

            System.out.println(node.data);
            return node.left;
        }
        else
        {
            node.right = insertHelper(key, node.right);
            node.right.parent = node;
            System.out.println(node.data);

            return  node.right;
        }

//        return node;
    }

    /**
     * TODO
     *
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node p = search(key);
        return p != null;
    }

    /**
     * TODO
     *
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens. Implement the removal using lazy deletion.
     */
    public void remove(K key) {
    }

    /**
     * TODO
     *
     * Clears out all dirty nodes from this BST.
     *
     * Use the following algorithm:
     * (1) Let ks be the list of keys in this tree.
     * (2) Clear this tree.
     * (2) For each key in ks, insert it into this tree.
     */
    public void rebuild() {
    }

    /**
     * TODO
     *
     * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {
        return null;
    }

    /**
     * TODO
     *
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return "";
    }
}
