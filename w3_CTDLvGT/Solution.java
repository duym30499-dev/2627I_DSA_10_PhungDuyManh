import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int s1 = 0, s2 = 0, s3 = 0; // tong chieu cao moi chong
        for (int x : h1) s1 += x;
        for (int x : h2) s2 += x;
        for (int x : h3) s3 += x;

        int i1 = 0, i2 = 0, i3 = 0; // vi tri dinh cua moi chong

        while (!(s1 == s2 && s2 == s3)) {
            // lay dia tren cung cua chong cao nhat
            if (s1 >= s2 && s1 >= s3) {
                s1 -= h1.get(i1++);
            } else if (s2 >= s1 && s2 >= s3) {
                s2 -= h2.get(i2++);
            } else {
                s3 -= h3.get(i3++);
            }
        }
        return s1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}