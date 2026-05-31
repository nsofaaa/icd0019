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
        return getCurrentPage();
    }

    public List<String> getCurrentPage() {
        List<String> result = new ArrayList<>();
        int count = 0;
        int targetStart = currentPage * pageSize;
        int currentElement = 0;
        int srcPage = 0;

        while (dataSource.hasPage(srcPage)) {
            for (String val : dataSource.getPage(srcPage)) {
                if (val != null) {
                    if (currentElement >= targetStart) {
                        if (count < pageSize) {
                            result.add(val);
                            count++;
                        }
                    }
                    currentElement++;
                }
            }
            srcPage++;
        }
        return result;
    }

    public List<String> getPreviousPage() {
        if (!hasPreviousPage()) {
            throw new IllegalStateException();
        }
        currentPage--;
        return getCurrentPage();
    }

    public boolean hasNextPage() {
        int targetStart = (currentPage + 1) * pageSize;
        int currentElement = 0;
        int srcPage = 0;

        while (dataSource.hasPage(srcPage)) {
            for (String val : dataSource.getPage(srcPage)) {
                if (val != null) {
                    if (currentElement >= targetStart) {
                        return true;
                    }
                    currentElement++;
                }
            }
            srcPage++;
        }
        return false;
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    public int getCurrentPageNo() {
        return currentPage;
    }
}