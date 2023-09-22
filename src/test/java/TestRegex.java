import java.util.Arrays;
import java.util.regex.Pattern;

public class TestRegex {

    public static void TestRegexSplit() {
        String validInput = "A.1";
        String[] playerInputSplit = validInput.split(Pattern.quote("."));
        System.out.println(Arrays.toString(playerInputSplit));
    }
}
