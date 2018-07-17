package sortingAlgorithm;

import java.util.Arrays;

/**
 * @author tangxiaoshuang
 * @date 2018/7/11 11:12
 * @desc 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int A[] = {1,6,9, 2, 3, 1, 5, 4 };
        quickSort(A, 0, 7);
        System.out.println(Arrays.toString(A));
    }

    public static void quickSort(int[] A, int left, int right) {

        if (left < right) {
            // 一次划分
            int mid = partion(A, left, right);
            quickSort(A, 0, mid - 1);
            quickSort(A, mid + 1, right);
        }
    }

    public static void swap(int[] A, int l, int r) {
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;

    }

    public static int partion(int[] a, int left, int right) {
        // 轴值，默认选取数组的第一个数字
        while (left < right) {
            while (left < right && a[left] <= a[right]) {
                right--;
            }
            if (left<right){
                swap(a, left, right);
            }
            while (left < right && a[left] <= a[right]) {
                left++;
            }
            if (left<right){
                swap(a, left, right);
            }
        }
        return left;
    }

}
