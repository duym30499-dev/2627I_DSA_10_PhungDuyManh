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

    static void partition(int[] ar) {
        int pivot = ar[0];
        int[] res = new int[ar.length];
        int k = 0;

        for (int i = 1; i < ar.length; i++) {
            if (ar[i] < pivot) res[k++] = ar[i];
        }

        res[k++] = pivot;

        for (int i = 1; i < ar.length; i++) {
            if (ar[i] > pivot) res[k++] = ar[i];
        }

        for (int i = 0; i < ar.length; i++) {
            ar[i] = res[i];
        }
    }

    public static List<Integer> quickSort(List<Integer> arr) {
        int[] ar = new int[arr.size()];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = arr.get(i);
        }

        partition(ar);

        List<Integer> result = new ArrayList<>();
        for (int x : ar) {
            result.add(x);
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.quickSort(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}