package bonus.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    private final SimplePager dataSource;
    private final int pageSize;

    private int currentPage = 0;

    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<String> getNextPage() {
        if (!hasNextPage()) {
            throw new IllegalStateException();
        }
        currentPage++;
        return loadPage(currentPage);
    }

    public List<String> getCurrentPage() {
        return loadPage(currentPage);
    }

    public List<String> getPreviousPage() {
        if (!hasPreviousPage()) {
            throw new IllegalStateException();
        }
        currentPage--;
        return loadPage(currentPage);
    }

    public boolean hasNextPage() {
        return !loadPage(currentPage + 1).isEmpty();
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    public int getCurrentPageNo() {
        return currentPage;
    }

    private List<String> loadPage(int targetPage) {

        List<String> result = new ArrayList<>();

        int count = 0;
        int pageIndex = 0;

        int srcPage = 0;

        while (dataSource.hasPage(srcPage)) {

            List<String> page = dataSource.getPage(srcPage);

            for (String val : page) {

                if (val == null) {
                    continue;
                }

                if (pageIndex == targetPage) {
                    result.add(val);
                }

                count++;

                if (count == pageSize) {
                    count = 0;
                    pageIndex++;

                    if (pageIndex > targetPage) {
                        return result;
                    }
                }
            }

            srcPage++;
        }

        return result;
    }
}