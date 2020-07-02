package com.lucheng.arithmetic.sort;

public class FastSortExample {
	 /**
     * 左右两个哨兵
     *
     * @param left
     * @param right 每次都是这个先
     */
    public void quicksort(int[] a, int left, int right) {
        int i, j, t, temp;//哨兵i,哨兵j，交换ij用到t,基准数temp
        if (left > right) {//跳出
            return;
        }
        //传过来的参数进行赋值
        temp = a[left];//temp中存储的就是基准数
        i = left;//左边哨兵
        j = right;//右边哨兵
        while (i != j) {
            //顺序很重要，先从右边开始找
            while (a[j] >= temp && i < j) {
                j--;//记录哨兵j位置
            }
            //再从左边找：小于基准数的数
            while (a[i] <= temp && i < j) {
                i++;//记录哨兵i位置
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位 ： 就是基准数跟ij相遇位置的数进行交换
        a[left] = a[i];//a[i]给left的位置也就是0，就是基准数
        a[i] = temp;//基准数该a[i]

        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    public static void main(String[] args) {
        int a[] = {5,2,3,1,6,4,7,8,0,9};//数组//定义变量，这两个变量需要在子函数中使用
        FastSortExample f = new FastSortExample();
        f.quicksort(a, 0, a.length - 1);
        for (int aa : a) {
            System.out.print(aa);
        }
    }
}
