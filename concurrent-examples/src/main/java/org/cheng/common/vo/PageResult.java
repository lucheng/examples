package org.cheng.common.vo;

import java.util.List;

/**
 * Created by whf on 8/8/16.
 */
public class PageResult<T> {
    /**
     * 总结果数
     */
    private long count;
    /**
     * 每页数据条数
     */
    private int size;

    private List<T> data;

    public PageResult(int count, int size, List<T> data) {
        this.count = count;
        this.size = size;
        this.data = data;
    }

    public PageResult(long count, List<T> data) {
        this.count = count;
        this.size = data.size();
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        return size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
