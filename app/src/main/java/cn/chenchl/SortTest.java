package cn.chenchl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * created by ccl on 2019/11/2
 * 排序算法测试
 **/
public class SortTest {

    private static int[] array = new int[]{23, 22, 4, 11, 34, 55, 1, 6, 3, 26, 77, 9, 19, 88};
    private static String testStr = "asdasdadsasdadssskkfklqqq";

    public static void main(String[] args) {
        System.out.println("before = " + Arrays.toString(array));
        /*optimizeSort(array, true);
        optimizeSort(array, false);
        selectSort(array, true);
        selectSort(array, false);
        insertSort(array, true);
        insertSort(array, false);
        quickSort(array, true);
        quickSort(array, false);*/
        heapSort(array, true);
        heapSort(array, false);
        System.out.println("爬楼梯 20层" + climbStairs(20));
        System.out.println("search" + search(array, 34));
        findChar(testStr);
        System.out.println("reverseString " + reverseString(testStr));
        ThirdWay(10, 3);
    }

    /**
     * 冒泡
     *
     * @param array
     * @param isDESC
     */
    private static void optimizeSort(int[] array, boolean isDESC) {
        if (array == null || array.length == 0) {
            return;
        }
        int sum = array.length;
        for (int i = 0; i < sum; i++) {
            boolean success = true;
            for (int j = 0; j < sum - i - 1; j++) {
                if (!isDESC) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        success = false;
                    }
                } else {
                    if (array[j] < array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        success = false;
                    }
                }

            }
            if (success)
                break;
        }
        System.out.println("optimizeSort isDESC " + isDESC + " = " + Arrays.toString(array));
    }

    /**
     * 选择排序
     *
     * @param array
     * @param isDESC
     */
    private static void selectSort(int[] array, boolean isDESC) {
        if (array == null || array.length == 0) {
            return;
        }
        int sum = array.length - 1;
        for (int i = 0; i < sum; i++) {
            int index = i;
            for (int j = i + 1; j <= sum; j++) {
                if (isDESC) {
                    if (array[j] > array[i]) {
                        index = j;
                    }
                } else {
                    if (array[j] < array[i]) {
                        index = j;
                    }
                }
            }
            if (index != i) {
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
        System.out.println("selectSort isDESC " + isDESC + " = " + Arrays.toString(array));
    }

    /**
     * 插入排序
     *
     * @param array
     * @param isDESC
     */
    private static void insertSort(int[] array, boolean isDESC) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (isDESC) {
                    if (array[j] > array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                    } else {
                        break;
                    }
                } else {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println("insertSort isDESC " + isDESC + " = " + Arrays.toString(array));
    }

    /**
     * 快排(分治)
     *
     * @param array
     * @param isDESC
     */
    private static void quickSort(int[] array, boolean isDESC) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSortMain(array, 0, array.length - 1, isDESC);
        System.out.println("quickSort isDESC " + isDESC + " = " + Arrays.toString(array));
    }

    private static void quickSortMain(int[] array, int l, int r, boolean isDESC) {
        if (l > r) {
            return;
        }
        int i = l;
        int j = r;
        int temp = array[l];
        int temp1;
        while (i < j) {
            if (isDESC) {
                while (temp >= array[j] && i < j) {
                    j--;
                }
                while (temp <= array[i] && i < j) {
                    i++;
                }
            } else {
                while (temp <= array[j] && i < j) {
                    j--;
                }
                while (temp >= array[i] && i < j) {
                    i++;
                }
            }

            if (i < j) {
                temp1 = array[i];
                array[i] = array[j];
                array[j] = temp1;
            }
        }
        //此时i==j 写哪个都可以
        array[l] = array[j];
        array[j] = temp;
        quickSortMain(array, j + 1, r, isDESC);
        quickSortMain(array, l, j - 1, isDESC);
    }

    /**
     * 堆排序
     *
     * @param array
     * @param isDESC
     */
    private static void heapSort(int[] array, boolean isDESC) {
        for (int i = 0; i < array.length; i++) {
            buildHeapify(array, array.length - i, isDESC);//构建堆
            //首尾节点交换
            int temp = array[0];
            array[0] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        System.out.println("heapSort isDESC " + isDESC + " = " + Arrays.toString(array));
    }
    //      23
    //   22          4
    // 11    34    55    1
    //6  3  26 77 9 19  88

    /**
     * 构建最大/最小堆
     *
     * @param array
     * @param size
     * @param isDESC
     */
    private static void buildHeapify(int[] array, int size, boolean isDESC) {
        if (array == null || array.length == 0)
            throw new NullPointerException();
        for (int i = size - 1; i >= 0; i--) {//从最后一个节点依次从下到上构建
            heapify(array, i, size, isDESC);
        }
    }

    private static void heapify(int[] array, int index, int size, boolean isDESC) {
        if (index < size) {
            int left = index * 2 + 1;//左子树节点
            int right = index * 2 + 2;//右子树节点
            int max = index;
            if (!isDESC) {//升序最大堆
                if (left < size && array[max] < array[left]) {//先和左子树比
                    max = left;
                }
                if (right < size && array[max] < array[right]) {//和右子树比
                    max = right;
                }
            } else {//降序最小堆
                if (left < size && array[max] > array[left]) {//先和左子树比
                    max = left;
                }
                if (right < size && array[max] > array[right]) {//和右子树比
                    max = right;
                }
            }
            //需要交换
            if (max != index) {
                int temp = array[max];
                array[max] = array[index];
                array[index] = temp;
                //由于交换了 因此需要继续比较子树和他的子节点的大小
                heapify(array, max, size, isDESC);
            }
        }
    }

    /**
     * 爬楼梯 （斐波那契数）
     *
     * @param n
     * @return
     */
    private static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**
     * 二分查找 有序
     *
     * @param array
     * @param value
     * @return
     */
    private static int search(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (value > array[mid]) {
                low = mid + 1;
            } else if (value < array[mid]) {
                high = mid - 1;
            } else
                return mid;
        }
        return -1;
    }

    /**
     * 在字符串中找出第一个只出现一次的字符。
     *
     * @param str
     */
    private static void findChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                System.out.println(str.charAt(i));
                break;
            }
        }
    }

    /**
     * 字符串反转
     *
     * @param str
     * @return
     */
    private static String reverseString(String str) {
        String str1 = "";
        for (char c : str.toCharArray()) {
            str1 = c + str1;
        }
        return str1;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode leftNode;
        public BinaryTreeNode rightNode;
    }

    /**
     * 根据前序和中序遍历结果重构二叉树
     * 思路 前序遍历的第一个值肯定是父节点 那么当中序遍历到和这个父节点相同的节点时 中序遍历的左边必然是左子树的节点 右边必然是右子数的节点 则根据这一个规律递归
     *
     * @param preorder
     * @param inorder
     * @return
     * @throws Exception
     */
    public static BinaryTreeNode constructCore(int[] preorder, int[] inorder) throws Exception {
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length != inorder.length) {//长度必然是一致的这是前提
            throw new IllegalArgumentException("长度不一样，非法的输入");
        }
        BinaryTreeNode root = new BinaryTreeNode();
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                root.value = inorder[i];
                System.out.println(root.value);
                root.leftNode = constructCore(Arrays.copyOfRange(preorder, 1, i + 1),
                        Arrays.copyOfRange(inorder, 0, i));
                root.rightNode = constructCore(Arrays.copyOfRange(preorder, i + 1, preorder.length),
                        Arrays.copyOfRange(inorder, i + 1, inorder.length));
            }
        }
        return root;

    }

    /**
     * 约瑟夫环 linkedlist实现
     */
    public static void ThirdWay(int total, int num) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < total; i++) {
            linkedList.add(i + 1);
        }
        int index = 0;
        while (linkedList.size() > 1) {//循环到剩下最后一个人
            for (int i = 1; i < num; i++) {
                if (index == linkedList.size() - 1) {//当数到最后一个人时回到第一个人的位置
                    index = 0;
                } else {
                    index++;//数数++;
                }
            }
            linkedList.remove(index);//数到num的人移除
        }
        System.out.println("约瑟夫环 总数：" + total + " 数的数：" + num + " 最后一位的序号" + linkedList.get(0));
    }

    /**
     * 镜像二叉树
     *
     * @param node
     */
    public static void mirror(BinaryTreeNode node) {
        if (node == null)
            return;

        BinaryTreeNode temp;
        temp = node.leftNode;
        node.leftNode = node.rightNode;
        node.leftNode = temp;

        mirror(node.leftNode);
        mirror(node.rightNode);

    }


}
