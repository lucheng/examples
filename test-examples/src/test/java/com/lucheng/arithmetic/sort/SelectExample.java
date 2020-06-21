package com.lucheng.arithmetic.sort;
/**
 * 选择排序
 * @author lucheng
 */
public class SelectExample {
	public static void main(String[] args) {
        //选择排序
        int[] source = {4,2,3,1,7,9};
        SelectSort(source);
    }

    /*
    选择排序改进了冒泡排序，将必要的交换次数从 O(n^2) 减少到 O(n)，但是比较次数仍保持为 O(n^2)。
    冒泡排序每比较一次就可能交换一次，但是选择排序是将一轮比较完后，把最小的放到最前的位置（或者把最大的放到最后）。
    选择排序为大记录量的排序提出了一个非常重要的改进，因为这些大量的记录需要在内存中移动，这就使交换的时间和比较的时间相比起来，交换的时间更为重要。
    （一般在 java 中不是这种情况，因为 java 中只是改变了引用位置，而实际对象的位置并没有发生改变。）
     */
    private static void SelectSort(int[] source) {
        int min;//记录最小值坐标
        int temp;
        for (int i = 0; i < source.length; i++) {
            min = i;
            for (int j = i+1; j < source.length; j++) {
                if(source[j]<source[min]){
                    min = j;
                }
            }
            temp = source[min];//交换i和min位置的数值
            source[min] = source[i];
            source[i] = temp;
        }
        System.out.println(source);
    }
}
