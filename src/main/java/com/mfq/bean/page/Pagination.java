package com.mfq.bean.page;

import java.io.Serializable;
import java.util.List;

public class Pagination extends SimplePage implements Serializable, Paginable {

    private static final long serialVersionUID = 6911618514313068769L;

    private List<?> list;

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}