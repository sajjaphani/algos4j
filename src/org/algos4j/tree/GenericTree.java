package org.algos4j.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A generic tree, also knows as n-ary tree, is a tree data structure where each
 * node can have 0 to n nodes where n is the arity of the tree.
 * 
 * @author psajja
 *
 */
public class GenericTree<T> {

	private GenericNode<T> root;
	
	/**
	 * Get the root node
	 *
	 * @return the root node
	 */
	public GenericNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the root node of the tree.
	 *
	 * @param root
	 *            The root node to set.
	 */
	void setRoot(GenericNode<T> root) {
		this.root = root;
	}

	/**
	 * Checks WHETHER the tree is empty 
	 *
	 * @return 
	 * 		true if the tree is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
     * Get the current size of the tree.
     *
     * @return 
     * 		the number of nodes in the tree
     */
    public int size() {
    	if(isEmpty())
    		return 0;
    	
        return size(root) + 1;
    }

    /**
     * Recursively get the size of the tree rooted at this node.
     * 
     * @param node
     * 		current node
     * 
     * @return
     * 		size of this tree rooted at the given node
     */
    private int size(GenericNode<T> node) {
        int count = node.getChildren().size();
        for (GenericNode<T> child : node.getChildren())
            count += size(child);

        return count;
    }
    
	/**
	 * Searches for the given data item in the tree
	 *
	 * @param data
	 *   	the data item to search
	 *   
	 * @return true if the given item is found, false otherwise
	 */
	public boolean search(T data) {
		return search(root, data);
	}

	/**
	 * Recursively search for the given data item starting from the given node.
	 * 
	 * @param node
	 * 		the current subtree root
	 * @param data
	 * 		data item to search
	 * 
	 * @return
	 * 		true if the item is found, false otherwise
	 */
	private boolean search(GenericNode<T> node, T data) {
		if (node.getData().equals(data))
			return true;
		else {
			for (GenericNode<T> child : node.getChildren()) {
				if (search(child, data))
					return true;
			}
		}

		return false;
	}
   
	/**
	 * This method searches for the given data item in the subtree.
	 * 
	 * @param node
	 * 		subtree rooted at this node
	 * @param data
	 * 		data item to find
	 * 
	 * @return
	 * 		the corresponding node if item is found, false otherwise
	 */
	protected GenericNode<T> searchNode(GenericNode<T> node, T data) {
		if (node == null)
			return null;

		if (node.getData().equals(data))
			return node;
		else {
			GenericNode<T> foundNode = null;
			for (GenericNode<T> child : node.getChildren()) {
				if ((foundNode = searchNode(child, data)) != null)
					return foundNode;
			}
		}

		return null;
	}
	
	/**
	 * Get the preorder traversal of the tree.
	 *
	 * @return 
	 * 		list of nodes holding preorder traversal, empty list if no nodes exists
	 */
	public List<GenericNode<T>> preorder() {
		List<GenericNode<T>> preorderList = new ArrayList<GenericNode<T>>();
		preorder(root, preorderList);
		
		return preorderList;
	}

	/**
	 * Recursively traverse the subtree in preorder.
	 * 
	 * @param node
	 * 		the current subtree root
	 * 
	 * @param preorderList
	 * 		the list to add the traversed nodes
	 */
	private void preorder(GenericNode<T> node, List<GenericNode<T>> preorderList) {
		preorderList.add(node);
		
		for (GenericNode<T> child : node.getChildren())
			preorder(child, preorderList);
	}

	/**
	 *
	 * Get the postorder traversal of the tree.
	 *
	 * @return The list of nodes in the tree, arranged in the post-order
	 */
	public List<GenericNode<T>> postorder() {
		List<GenericNode<T>> postorderList = new ArrayList<GenericNode<T>>();
		postorder(root, postorderList);
	
		return postorderList;
	}

	/**
	 * Recursively traverse the subtree in postrder.
	 * 
	 * @param node
	 * 		the current subtree root
	 * 
	 * @param postorderList
	 * 		the list to add the traversed nodes
	 */
	private void postorder(GenericNode<T> node, List<GenericNode<T>> postorderList) {
		for (GenericNode<T> child : node.getChildren())
			postorder(child, postorderList);

		postorderList.add(node);
	}
	
	/**
	 *This method computes and returns list of all the paths from root to leaf.
	 *
	 * @return 
	 * 		list of the paths
	 */
	public List<List<GenericNode<T>>> getAllPaths() {
		List<List<GenericNode<T>>> allPaths = new ArrayList<List<GenericNode<T>>>();
		List<GenericNode<T>> currentPath = new ArrayList<GenericNode<T>>();
	
		updatePaths(root, currentPath, allPaths);

		return allPaths;
	}

	/**
	 * Recursively traverse the nodes and update the paths.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param currentPath
	 * 		subtree path
	 * @param paths
	 * 		holds the paths
	 */
	private void updatePaths(GenericNode<T> node, List<GenericNode<T>> currentPath, List<List<GenericNode<T>>> paths) {
		if (currentPath == null)
			return;

		currentPath.add(node);

		// We are seeing a leaf node
		if (node.getChildren().size() == 0)
			paths.add(copyOf(currentPath));
	
		for (GenericNode<T> child : node.getChildren())
			updatePaths(child, currentPath, paths);

		// Remove processed node from current path
		int index = currentPath.indexOf(node);
		for (int i = index; i < currentPath.size(); i++)
			currentPath.remove(index);
	}

	/**
	 * Given a list of nodes, this method creates a copy of the list.
	 * This method does copy only the data items not the links.
	 * 
	 * @param list
	 * 		given node list
	 * 
	 * @return
	 * 		a copy of the given list
	 */
	private List<GenericNode<T>> copyOf(List<GenericNode<T>> list) {
		List<GenericNode<T>> newList = new ArrayList<GenericNode<T>>();
		for (GenericNode<T> node : list)
			newList.add(new GenericNode<T>(node.getData()));

		return newList;
	}
	
	/**
	 * Get the longest path from root to any leaf node.
	 * 
	 * @return
	 * 		list containing the longest path.
	 */
	public List<GenericNode<T>> getLongestPath() {
		List<GenericNode<T>> longestPath = new ArrayList<>();
		int pathLength = 0;

		for (List<GenericNode<T>> path : getAllPaths()) {
			if (path.size() > pathLength) {
				pathLength = path.size();
				longestPath = path;
			}
		}
		
		return longestPath;
	}
	
	/**
	 * Get the height of the tree
	 *
	 * @return 
	 * 		the height of the tree.
	 */
	public int height() {
		return getLongestPath().size();
	}
	
	/**
	 * Represents a generic node.
	 * 
	 * @author psajja
	 *
	 */
	static class GenericNode<T> {
	
		private T data;
	    private Collection<GenericNode<T>> children;
	    private GenericNode<T> parent;
	    
	    /**
	     * Create a generic node with the given data.
	     * 
	     * @param data
	     * 		given data
	     */
	    public GenericNode(T data) {
	        this.data = data;
	        this.children = new ArrayList<>();
	    }
	    
	    /**
	     * Get the node data.
	     * 
	     * @return
	     * 		the data
	     */
	    public T getData() {
	        return this.data;
	    }

	    /**
	     * Set the node data.
	     * 
	     * @param data
	     * 		the node data
	     */
	    public void setData(T data) {
	        this.data = data;
	    }
	    
	    /**
	     * Add a new child to the this node.
	     * 
	     * @param child
	     * 		child to add
	     */
	    public void addChild(GenericNode<T> child) {
	        child.parent = this;
	        children.add(child);
	    }
	    
	    /**
	     * Remove the given child.
	     * 
	     * @param child
	     * 		the child to remove
	     */
	    public void removeChild(GenericNode<T> child) {
	        children.remove(child);
	    }
	    
	    /**
	     * Get the parent of this node.
	     * 
	     * @return
	     * 		the node's parent
	     */
	    public GenericNode<T> getParent() {
	        return parent;
	    }

	    /**
	     * Get the children of this node.
	     * 
	     * @return
	     * 		collection of children
	     */
	    public Collection<GenericNode<T>> getChildren() {
	        return children;
	    }

	    /**
	     * Get the count of the children.
	     * 
	     * @return
	     * 		the children size
	     */
	    public int getChildCount() {
	        return getChildren().size();
	    }

	    /**
	     * Returns whether this node has any children.
	     * 
	     * @return
	     * 		true if it has any children, false otherwise
	     */
	    public boolean hasChildren() {
	        return getChildCount() > 0;
	    }
	    
	    @Override
	    public String toString() {
	    	return data.toString();
	    }
	}
}
