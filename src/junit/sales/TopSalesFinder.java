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

        for (SalesRecord record : records) {

            String id = record.productId();
            int value = record.productPrice() * record.itemsSold();

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

        int count = 0;

        for (SalesRecord record : records) {
            if (!record.productId().equals(id)) {
                count++;
            }
        }

        SalesRecord[] newArray = new SalesRecord[count];
        int index = 0;

        for (SalesRecord record : records) {
            if (!record.productId().equals(id)) {
                newArray[index] = record;
                index++;
            }
        }

        records = newArray;
    }

    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {

        int start = pageNumber * pageSize;

        if (start >= records.length) {
            return new SalesRecord[0];
        }

        int end = start + pageSize;

        if (end > records.length) {
            end = records.length;
        }

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

    private int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
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

    private SalesRecordResult[] appendResult(
            SalesRecordResult[] array,
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