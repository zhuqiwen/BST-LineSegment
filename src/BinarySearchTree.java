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
//        protected int balanceFactor; // maintains balance factor of current node

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
            this.fixHeight();

        }


        /**
         * calculate balance factor for current node
         * @return int
         */
        protected int getBalanceFactor()
        {
            // has both children
            if(this.left != null && this.right != null)
            {
                return  this.right.height - this.left.height;
            }

            // has onlly left child, then the balance factor of current is the height (negative int) of left child
            if(this.left != null)
            {
                return 0 - this.left.height;
            }

            // has only right child, then the balance factor of current is the height (positive int) of right child
            if(this.right != null)
            {
                return this.right.height;
            }

            return 0;
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
            else if(this.left == null)
            {
                this.height = this.right.height + 1;
            }
            else if(this.right == null)
            {
                this.height = this.left.height + 1;
            }
            else
            {
                this.height = 1 + Math.max(this.left.height, this.right.height);
            }
        }

        /**
         *  return data of current node
         * @return
         */
        public K get()
        {
            return data;
        }


        /**
         *  Returns the location of the node containing the inorder predecessor
         * of this node.
         * left -> root -> right
         * @return Node
         */
        public Node getBefore()
        {
            if(left != null)
            {
                return maxInLeft(left);
            }

            if(this.parent != null && this.equals(this.parent.right))
            {
                return this.parent;
            }

            Node currParent = this.parent;
            Node current = this;
            while(currParent != null && current.equals(currParent.left))
            {
                current = currParent;
                currParent = currParent.parent;
            }

            return currParent;
        }


        /**
         *
         * Returns the location of the node containing the inorder successor
         * of this node.
         * left -> root -> right
         * @return Node
         */
        public Node getAfter()
        {
            if(right != null)
            {
                return minInRight(right);
            }

            if(this.parent != null && this.equals(this.parent.left))
            {
                return this.parent;
            }

            Node currParent = this.parent;
            Node current = this;
            while(currParent != null && current.equals(currParent.right))
            {
                current = currParent;
                currParent = currParent.parent;
            }

            return currParent;

        }


        /**
         * find the max node in left sub tree
         * @param node
         * @return Node
         */
        private Node maxInLeft(Node node)
        {
            while(node.right != null)
            {
                node = node.right;
            }

            return node;
        }

        /**
         * find the min node in right sub tree
         * @param node
         * @return Node
         */
        private Node minInRight(Node node)
        {
            while (node.left != null)
            {
                node = node.left;
            }
            return node;
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
     *
     * Looks up the key in this tree and, if found, returns the (possibly dirty)
     * location containing the key.
     */
    public Node search(K key)
    {

        return searchHelper(key, root);
    }

    /**
     * recursively follow a path to search in the BST
     * @param key
     * @param node
     * @return Node
     */
    private Node searchHelper(K key, Node node)
    {
        if(node == null)
        {
            return null;
        }

        if(lessThan.test(key, node.data))
        {
            return searchHelper(key, node.left);
        }

        if(lessThan.test(node.data, key))
        {
            return searchHelper(key, node.right);
        }

        return node;
    }

    /**
     * TODO
     *
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height()
    {
        return heightHelper(root);
    }

    /**
     * the height of a certain node is max(left, right) + 1
     * @param node
     * @return
     */
    private int heightHelper(Node node)
    {
        if(node == null)
        {
            return 0;
        }

        if(node.isLeaf())
        {
            return 1;
        }

        return Math.max(
                node.left == null ? 0 : node.left.height,
                node.right == null? 0 : node.right.height)
                + 1;
    }

    /**
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
        Node searchNode = search(key);

        // if the key is new
        if(searchNode == null)
        {
            n++;
            root = insertHelper(key, root);

        }
        //if the key is already in bst
        else
        {
            //if the key is soft deleted, meaning marked as dirty.
            if(searchNode.dirty)
            {
                undoDirty(searchNode);
            }
        }

        return search(key);
    }


    /**
     * a helper to set dirty to false for existing keys
     * @param node
     */
    private void undoDirty(Node node)
    {
        n++;
        node.dirty = false;
    }


    /**
     * by comparing key with a node.data, determines where the a new node of key should be placed
     * @param key
     * @param node
     * @return Node
     */
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

//            System.out.println(node.data);
        }
        else
        {
            node.right = insertHelper(key, node.right);
            node.right.parent = node;

//            System.out.println(node.data);
        }

        // don't forget to fix current node's height!
        node.fixHeight();

        return node;
    }

    /**
     *
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node p = search(key);

        if (p != null && !p.dirty)
        {
            return true;
        }

        return false;
    }

    /**
     *
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens. Implement the removal using lazy deletion.
     */
    public void remove(K key)
    {
        if(contains(key))
        {
            this.search(key).dirty = true;
            n--;
        }

    }

    /**
     *
     * Clears out all dirty nodes from this BST.
     *
     * Use the following algorithm:
     * (1) Let ks be the list of keys in this tree.
     * (2) Clear this tree.
     * (2) For each key in ks, insert it into this tree.
     */
    public void rebuild()
    {
        List<K> ks = keys();
        clear();
        for(K k : ks)
        {
            insert(k);
        }
    }

    /**
     *
     * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys()
    {
        List<K> keysList = new LinkedList<>();
        keysHelper(keysList, root);

        return keysList;
    }

    /**
     * return all non-dirty keys on the path following a certain node
     * @param keysList
     * @param node
     */
    private void keysHelper(List<K> keysList, Node node)
    {
        if(node == null)
        {
            return;
        }

        if(node.left != null)
        {
            keysHelper(keysList,node.left);
        }
        if(!node.dirty)
        {
            keysList.add(node.data);
        }
        if(node.right != null)
        {
            keysHelper(keysList, node.right);
        }
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
