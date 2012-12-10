package com.serwylo.trivia;

import java.util.List;

public interface TreeNode<T extends TreeNode> {

	public List<T> getChildren();

	public T getParent();

}
