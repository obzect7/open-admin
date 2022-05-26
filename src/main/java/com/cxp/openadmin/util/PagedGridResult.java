package com.cxp.openadmin.util;

import java.util.List;

public class PagedGridResult {

    private int page;			// 현재 페이지 번호
    private int total;			// 총 페이지
    private long records;		// 총
    private List<?> rows;		// 각 줄에 표시할 내용

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public long getRecords() {
        return records;
    }
    public void setRecords(long records) {
        this.records = records;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
