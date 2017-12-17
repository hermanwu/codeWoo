import java.util.Arrays;
import java.util.List;

/**
 * Created by hermanwu on 12/7/17.
 */
public class Printer {
    public static void print(Object input) {
        System.out.println(input);
    }

    public static void printIntegerList(List<Integer> list) {
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static void printBooleanMatrix(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
