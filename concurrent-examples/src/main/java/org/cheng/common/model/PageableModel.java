package org.cheng.common.model;

import org.cheng.common.config.Const;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by whf on 8/6/16.
 */
public abstract class PageableModel {
    /**
     * 页数, 从1开始
     */
    @JsonIgnore
    private int page = 1;
    /**
     * 每页的条数
     */
    @JsonIgnore
    private int size = Const.DEFAULT_PAGE_SIZE;

    /**
     * 对应mysql中limit语句的第一个参数
     */
    @JsonIgnore
    private int skip;

    protected PageableModel() {
    }

    protected PageableModel(int page, int size) {
        if (page < 1) {
            this.page = 1;
        }

        if (size < 1) {
            this.size = Const.DEFAULT_PAGE_SIZE;
        }

        this.skip = this.size * (this.page - 1);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSkip() {
        return skip;
    }
}
