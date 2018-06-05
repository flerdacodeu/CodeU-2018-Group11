package Assignment2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Q1 {
	/**
	 * Method that gets all possible paths in a Binarty Tree, then traverses the
	 * paths one by one and tries to find our target value in the lists ( since
	 * there are no duplicates) once our target value was found we return a sublist
	 * of that path from root till target.
	 * 
	 * @param root
	 * @param target
	 *            val to get it's ancestors
	 * @return a list of all ancestors for given target
	 * @throws IllegalStateException
	 */
	public static List<Integer> getAncestors(TreeNode root, int target) throws IllegalStateException {
		List<List<Integer>> paths = new ArrayList<>();
		getPaths(root, paths, new ArrayList<>());
		List<Integer> targetList = null;
		for (List<Integer> l : paths) {
			if (isTarget(l, target)) {
				targetList = l;
			}
		}
		if (targetList == null) {
			throw new IllegalStateException("Target was not found in the tree.");
		} else {
			int index = 0;
			for (int i : targetList) {
				if (i == target) {
					targetList = new ArrayList<>(targetList.subList(0, index));
				}
				index++;
			}
			return targetList;
		}
	}

	public static void printAncestors(List<Integer> list) {
		for (int i : list) {
			System.out.print(i + " ");
		}
	}

	private static boolean isTarget(List<Integer> l, int target) {
		for (int i : l)
			if (i == target)
				return true;
		return false;
	}

	/**
	 * Method that gets all paths of a Binary Tree and stores each path in a list.
	 * 
	 * @param root
	 * @param paths
	 * @param curr
	 */
	private static void getPaths(TreeNode root, List<List<Integer>> paths, ArrayList<Integer> curr) {
		curr.add(root.val);
		if (root.left == null && root.right == null) {
			paths.add(new ArrayList<>(curr));
		}

		if (root.left != null) {

			getPaths(root.left, paths, new ArrayList(curr));
		}
		if (root.right != null) {
			getPaths(root.right, paths, new ArrayList(curr));
		}
	}
}
