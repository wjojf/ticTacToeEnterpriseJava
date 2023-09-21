package etc.helpers;

public class SortHelpers {
    public static <T extends Comparable<T>>  void bubbleSort(T[] arr) {
        for (int j = arr.length - 1; j >= 0; j--) {
            for (int i = 1; i <= j; i++) {
                if (arr[i-1].compareTo(arr[i]) > 0) {
                    T tmp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

}
