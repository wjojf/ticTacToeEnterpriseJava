import java.util.Arrays;

public class TestFunctionArgument {

    private static void doChange(int[] arr) {
        arr[0] = 1;
    }

    public static void main(String[] args) {
        int[] arr = {0,2,3};
        doChange(arr);
        System.out.println(Arrays.toString(arr));
    }
}
