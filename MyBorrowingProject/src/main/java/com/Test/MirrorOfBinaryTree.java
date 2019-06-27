package com.Test;


import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by 王彦鑫 on 2018/11/25
 */
public class MirrorOfBinaryTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = scanner.next();
            }
            TreeNode root = createBinaryTreeByArray(strings,0);
            layerOrder(Mirror(root));
        }
    }

    //二叉树
    public static class TreeNode {
        String val = "";
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(String val) {
            this.val = val;
        }
    }

    //将String数组转为二叉树
    private static TreeNode createBinaryTreeByArray(String[] strings, int index)
    {
        TreeNode tn = null;
        if (index<strings.length) {
            String value = strings[index];
            if (value == null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(strings, 2*index+1);
            tn.right = createBinaryTreeByArray(strings, 2*index+2);
            return tn;
        }
        return tn;
    }

    //翻转二叉树
    public static TreeNode Mirror(TreeNode root) {
        if(root!=null){
            //左右子结点交换
            TreeNode tempNode = root.left;
            root.left=root.right;
            root.right=tempNode;

            Mirror(root.left);
            Mirror(root.right);
        }
        return root;
    }

    //二叉树层次遍历
    public static void layerOrder(TreeNode root){
        if(root==null) return;
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        TreeNode currentNode;
        while(!list.isEmpty()){
            currentNode=list.poll();
            System.out.print(currentNode.val+" ");
            if(currentNode.left!=null){
                list.add(currentNode.left);
            }
            if(currentNode.right!=null){
                list.add(currentNode.right);
            }
        }
    }

}
