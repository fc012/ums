package com.carme.common.framework.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({ "rawtypes", "serial" })
public class Page<T> implements Serializable {
    private int DEF_PAGE_VIEW_SIZE = 10;

    /** 当前页 */
    private int pageNO;
    /** 当前页显示记录条数 */
    private int pageSize;
    /** 取得查询总记录数 */
    private int recordCount;

    List<T>     resultList         = Collections.emptyList();

    private int pageRange          = 3;

    /**
     * (空)
     */
    public Page() {
    }

    /**
     * 根据当前显示页与每页显示记录数设置查询信息初始对象
     * @param pageNO 当前显示页号
     * @param pageSize 当前页显示记录条数
     */
    public Page(int pageNO, int pageSize) {
        this.pageNO = (pageNO <= 0) ? 1 : pageNO;
        this.pageSize = (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }

    /**
     * 取得当前显示页号
     * @return 当前显示页号
     */
    public int getPageNo() {
        return (pageNO <= 0) ? 1 : pageNO;
    }

    /**
     * 设置当前页
     * @param pageNO 当前页
     */
    public void setPageNo(int pageNO) {
        this.pageNO = pageNO;
    }

    /**
     * 取得当前显示页号最多显示条数
     * @return 当前显示页号最多显示条数
     */
    public int getPageSize() {
        return (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }

    /**
     * 设置当前页显示记录条数
     * @param pageSize 当前页显示记录条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 取得查询取得记录总数
     * @return 取得查询取得记录总数
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * 设置查询取得记录总数
     * @param recordCount 查询取得记录总数
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = (recordCount < 0) ? 0 : recordCount;
    }

    /**
     * 取得当前查询总页数
     * @return 当前查询总页数
     */
    public int getPageCount() {
        return (recordCount + getPageSize() - 1) / getPageSize();
    }

    /**
     * 取得起始显示记录号
     * @return 起始显示记录号
     */
    public int getStartNo() {
        return ((getPageNo() - 1) * getPageSize() + 1);
    }

    /**
     * 取得结束显示记录号
     * @return 结束显示记录号
     */
    public int getEndNo() {
        return Math.min(getPageNo() * getPageSize(), recordCount);
    }

    /**
     * 取得前一显示页码
     * @return 前一显示页码
     */
    public int getPrePageNo() {
        return Math.max(getPageNo() - 1, 1);
    }

    /**
     * 取得后一显示页码
     * @return 后一显示页码
     */
    public int getNextPageNo() {
        return Math.min(getPageNo() + 1, getPageCount());
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public List<Integer> getPageItems() {
        List<Integer> items = new ArrayList<Integer>();
        int startPage = getPageNo() - pageRange;
        int endPage = getPageNo() + pageRange;
        if (startPage <= 0) {
            endPage = endPage - startPage + 1;
        }
        if (endPage > getPageCount()) {
            startPage = startPage - (endPage - getPageCount());
            endPage = getPageCount();
        }
        if (startPage <= 0) {
            startPage = 1;
        }
        for (int i = startPage; i <= endPage; i++) {
            items.add(i);
        }
        return items;
    }
}