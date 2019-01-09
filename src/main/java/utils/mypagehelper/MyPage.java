package utils.mypagehelper;

public class MyPage {
    private Boolean isLimit;//是否分页
    private Integer currentPage = 1;//当前页
    private Integer pageSize = 30;//每页显示量
    private Integer totalCount;//总数据量
    private Integer totolPage;//总页数
    private Integer startPage;//开始页


    public void setFilter(Boolean isLimit, Integer currentPage, Integer pageSize, Integer totalCount) {
        this.isLimit = isLimit;
        //进行分页
        if (isLimit) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalCount = totalCount;
            //总页数
            if (this.pageSize > 0) {
                this.totolPage = this.totalCount % this.pageSize > 0 ? this.totalCount / this.pageSize + 1 : this.totalCount / this.pageSize;
                //   当前页
                this.startPage = (this.currentPage - 1) * this.pageSize;
            }
        }
    }

    public Boolean getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Boolean limit) {
        isLimit = limit;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotolPage() {
        return totolPage;
    }

    public void setTotolPage(Integer totolPage) {
        this.totolPage = totolPage;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }
}
