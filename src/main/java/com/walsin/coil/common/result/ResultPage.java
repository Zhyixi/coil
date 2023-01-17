package com.walsin.coil.common.result;

/**
 * @author Jane
 * @date 2022/11/21 下午 01:00
 */
public class ResultPage<T> extends Result<T> {
    private static final long serialVersionUID = 1L ;
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    private Integer pageNum ;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    private Integer totalPage ;
    private Integer totalCount ;
}
