package junit.sales;

public class TopSalesFinder {

    private SalesRecord[] records = new SalesRecord[0];

    public void registerSale(SalesRecord record) {
        SalesRecord[] newArray = new SalesRecord[records.length + 1];

        for (int i = 0; i < records.length; i++) {
            newArray[i] = records[i];
        }

        newArray[records.length] = record;
        records = newArray;
    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {

        String[] ids = new String[0];
        int[] totals = new int[0];

        for (int i = 0; i < records.length; i++) {

            String id = records[i].productId();
            int value = records[i].productPrice() * records[i].itemsSold();

            int index = indexOf(ids, id);

            if (index == -1) {
                ids = appendString(ids, id);
                totals = appendInt(totals, value);
            } else {
                totals[index] += value;
            }
        }

        SalesRecordResult[] results = new SalesRecordResult[0];

        for (int i = 0; i < ids.length; i++) {
            if (totals[i] > amount) {
                results = appendResult(results,
                        new SalesRecordResult(ids[i], totals[i]));
            }
        }

        return results;
    }

    public void removeSalesRecordsFor(String id) {

        SalesRecord[] temp = new SalesRecord[records.length];
        int size = 0;

        for (int i = 0; i < records.length; i++) {
            if (!records[i].productId().equals(id)) {
                temp[size++] = records[i];
            }
        }

        SalesRecord[] newArray = new SalesRecord[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = temp[i];
        }

        records = newArray;
    }

    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {

        int start = pageNumber * pageSize;
        if (start >= records.length) return new SalesRecord[0];

        int end = start + pageSize;
        if (end > records.length) end = records.length;

        int size = end - start;

        SalesRecord[] page = new SalesRecord[size];

        for (int i = 0; i < size; i++) {
            page[i] = records[start + i];
        }

        return page;
    }

    public int getRecordCount() {
        return records.length;
    }

    public void removeRecord(String id) {
        removeSalesRecordsFor(id);
    }

    // ===== helper methods =====

    private int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) return i;
        }
        return -1;
    }

    private String[] appendString(String[] array, String value) {
        String[] newArray = new String[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;
        return newArray;
    }

    private int[] appendInt(int[] array, int value) {
        int[] newArray = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;
        return newArray;
    }

    private SalesRecordResult[] appendResult(SalesRecordResult[] array,
                                             SalesRecordResult value) {

        SalesRecordResult[] newArray =
                new SalesRecordResult[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;
        return newArray;
    }
}