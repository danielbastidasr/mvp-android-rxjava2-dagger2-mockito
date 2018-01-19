package co.gosalo.androidreview.api;

import java.util.List;

public class PagedResponseBody<T> {

    private T content;
    private Integer totalPages;
    private Integer totalElements;
    private boolean last;
    private Integer numberOfElements;
    private boolean first;
    private Integer size;
    private Integer number;
    private List<SortCriteria> sort;

    public T getContent() {
        return content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getNumber() {
        return number;
    }

    public List<SortCriteria> getSort() {
        return sort;
    }

    public static class SortCriteria {

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public boolean isAscending() {
            return ascending;
        }

        public boolean isDescending() {
            return descending;
        }
    }
}
