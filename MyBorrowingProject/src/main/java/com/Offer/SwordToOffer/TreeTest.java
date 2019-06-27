package com.Offer.SwordToOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by 王彦鑫 on 2019/3/23
 * 二叉树操作题
 */
public class TreeTest {
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

/**
 * 1、根据前序遍历和中序遍历重建二叉树
 */
class RebuildBinaryTree {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre > endPre||startIn > endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }
        return root;
    }
}

/**
 * 2、输入两颗树A和B，判断B是不是A的子结构，约定空树不是任意树的子结构
 */
class HashSubTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null){
            return false;
        }
        boolean result = false;
        if(equal(root1.val,root2.val)){
            result = Tree1HasTree2(root1,root2);
        }
        if(!result)
            result = HasSubtree(root1.left,root2);
        if(!result)
            result = HasSubtree(root1.right,root2);
        return result;
    }
    boolean Tree1HasTree2(TreeNode root1,TreeNode root2){
        if(root2 == null){
            return true;
        }
        if(root1 == null){
            return false;
        }
        if(!equal(root1.val,root2.val)){
            return false;
        }
        return Tree1HasTree2(root1.left,root2.left) && Tree1HasTree2(root1.right,root2.right);
    }
    boolean equal(double val1,double val2){
        if((val1 - val2 > -0.0000001) && (val1 - val2 < 0.0000001)){
            return true;
        }else{
            return false;
        }
    }
}

/**
 * 3、二叉树的镜像
 */
class TreeMirror {
    public void Mirror(TreeNode root) {
        if(root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if(root.left != null){
            Mirror(root.left);
        }
        if(root.right != null){
            Mirror(root.right);
        }
    }
}

/**
 * 4、对称的二叉树
 * 如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
class isSymmetrical {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(pRoot.left);
        stack.push(pRoot.right);
        while(!stack.empty()){
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if(left == null && right == null)
                continue;
            if(left == null || right == null)
                return false;
            if(left.val != right.val)
                return false;
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
}

/**
 * 5、从上到下打印出二叉树的每个节点，同层节点从左往右打印
 * 队列实现
 */
class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() != 0){
            TreeNode temp = queue.poll();
            if(temp.left != null){
                queue.offer(temp.left);
            }
            if(temp.right != null){
                queue.offer(temp.right);
            }
            result.add(temp.val);
        }
        return result;
    }
}

/**
 * 6、把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
class PrintByLine {
    ArrayList<ArrayList<Integer> > Print(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        int j = 0;
        while(queue.size() != 0){
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if(temp.left != null){
                queue.offer(temp.left);
                j++;
            }
            if(temp.right != null){
                queue.offer(temp.right);
                j++;
            }
            i--;
            if(i == 0){
                result.add(new ArrayList<Integer>(list));
                list.clear();
                i = j;
                j = 0;
            }
        }
        return result;
    }
}

/**
 * 7、按之字形顺序打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
class PrintZ {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(pRoot == null){
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        int current = 1;   //指示当前是第几个栈
        while(stack1.size() != 0 || stack2.size() != 0){
            if(current == 1){
                while(stack1.size() != 0){
                    TreeNode temp = stack1.pop();
                    if(temp.left != null){
                        stack2.push(temp.left);
                    }
                    if(temp.right != null){
                        stack2.push(temp.right);
                    }
                    list.add(temp.val);
                }
                current = 2;
            }else{
                while(stack2.size() != 0){
                    TreeNode temp = stack2.pop();
                    if(temp.right != null){
                        stack1.push(temp.right);
                    }
                    if(temp.left != null){
                        stack1.push(temp.left);
                    }
                    list.add(temp.val);
                }
                current = 1;
            }
            result.add(new ArrayList<Integer>(list));
            list.clear();
        }
        return result;
    }
}

/**
 * 8、BST树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某BST二叉搜索树的后序遍历的结果
 */
class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int [] sequence) {
        int length = sequence.length;
        if(length == 0){
            return false;
        }
        return judge(sequence,0,length-1);
    }

    public static boolean judge(int[] sequence,int begin,int end){
        if(begin >= end){
            return true;
        }
        int last = sequence[end];
        int i = begin;
        while(sequence[i] < last){
            i++;
        }
        int j = i;
        while(j < end && sequence[j] >= last){
            j++;
        }
        if(j < end){
            return false;
        }
        return judge(sequence,begin,i-1) && judge(sequence,i,end-1);
    }
}

/**
 * 9、二叉树中和为某一值的路径
 * 输入二叉树根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径
 */
class FindPath {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return result;
        }
        find(root,target,result,list);
        return result;
    }

    public static void find(TreeNode root,int target,ArrayList<ArrayList<Integer>> result,ArrayList<Integer> list){
        if(root == null){
            return;
        }
        target -= root.val;
        list.add(root.val);
        if(target == 0 && root.left == null && root.right == null){
            result.add(new ArrayList<Integer>(list));
        }
        find(root.left,target,result,list);
        find(root.right,target,result,list);
        list.remove(list.size()-1);
    }
}

/**
 * 10、二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
class Convert {
    TreeNode head = null;
    TreeNode realHead = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return realHead;
    }

    private void ConvertSub(TreeNode root) {
        if(root==null) return;
        ConvertSub(root.left);
        if (head == null) {
            head = root;
            realHead = root;
        } else {
            head.right = root;
            root.left = head;
            head = root;
        }
        ConvertSub(root.right);
    }
}

/**
 * 11、二叉树的深度
 * 最长路径长度
 */
class TreeDepth {
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root != null && root.left == null && root.right == null){
            return 1;
        }
        return TreeDepth(root.left)>TreeDepth(root.right)?TreeDepth(root.left)+1:TreeDepth(root.right)+1;
    }
}

/**
 * BST
 * 12、判断二叉树是否是平衡二叉树
 */
class IsBalanced_Solution {
    public boolean flag = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        TreeDepth(root);
        return flag;
    }

    public int TreeDepth(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        if(Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) > 1){
            flag = false;
        }
        return TreeDepth(root.left)>TreeDepth(root.right)?TreeDepth(root.left)+1:TreeDepth(root.right)+1;
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

/**
 * 13、二叉树的下一个结点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
class  GetNext{
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null){
            return null;
        }
        if(pNode.right != null){
            pNode = pNode.right;
            while(pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
        }
        while(pNode.next != null){
            if(pNode.next.left == pNode){
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}

/**
 * 14、序列化、反序列化二叉树
 */
class SerializeTree {
    public int index = -1;
    String Serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if(root == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }
    TreeNode Deserialize(String str) {
        index++;
        int len = str.length();
        if(index >= len){
            return null;
        }
        String[] strr = str.split(",");
        TreeNode node = null;
        if(!strr[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }
}

/**
 * 15、BST树的第k小结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
class KthNode {
    private int count = 0;
    private TreeNode resultNode = null;
    TreeNode KthNode(TreeNode pRoot, int k)
    {        if(pRoot == null){
            return null;
        }
        KthNodeCore(pRoot,k);
        return resultNode;
    }
    void KthNodeCore(TreeNode pRoot, int k){
        if(pRoot == null){
            return;
        }
        if(pRoot.left != null){
            KthNodeCore(pRoot.left,k);
        }
        if(pRoot != null){
            count++;
            if(count == k){
                resultNode = pRoot;
            }
        }
        if(pRoot.right != null){
            KthNodeCore(pRoot.right,k);
        }
    }
}
