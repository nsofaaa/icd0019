package icd0019types.src.types;

public class Pmd {

    public static void main(String[] args) {
        boolean[][] table = getSampleTable();

        printTable(table);

        System.out.println(containsTrueCell(table));
        System.out.println(countTrueRow(table));
    }

    private static void printTable(final boolean[][] table) {
        for (final boolean[] row : table) {
            for (final boolean element : row) {
                System.out.print(element ? "X" : "O");
            }
            System.out.println();
        }
    }

    public static boolean containsTrueCell(final boolean[][] matrix) {
        for (final boolean[] row : matrix) {
            for (final boolean cell : row) {
                if (cell) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int countTrueRow(final boolean[][] matrix) {
        for (final boolean[] row : matrix) {
            int count = 0;
            for (final boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
            if (count > 0) {
                return count;
            }
        }
        return -1;
    }

    private static boolean[][] getSampleTable() {
        final boolean[][] matrix = new boolean[3][3];
        matrix[2][1] = true;
        matrix[2][2] = true;
        return matrix;
    }

}
