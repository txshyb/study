package sortingAlgorithm;

import java.util.Arrays;

/**
 * @author tangxiaoshuang
 * @date 2018/7/11 11:09
 * @desc 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int [] a = {1,100,234,44,3,2,4,5};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(int[] a) {
        for (int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                if (a[i] > a[j]){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
}
