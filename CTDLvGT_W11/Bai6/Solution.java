import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    static class Item implements Comparable<Item> {
        int value;
        int order;

        Item(int value, int order) {
            this.value = value;
            this.order = order;
        }

        @Override
        public int compareTo(Item other) {
            if (value != other.value) {
                return Integer.compare(value, other.value);
            }
            return Integer.compare(order, other.order);
        }
    }

    public static List<Double> runningMedian(List<Integer> a) {
        TreeSet<Item> set = new TreeSet<>();
        List<Double> result = new ArrayList<>();
        Item mid = null;

        for (int i = 0; i < a.size(); i++) {
            Item x = new Item(a.get(i), i);
            set.add(x);

            if (mid == null) {
                mid = x;
            } else if (x.compareTo(mid) < 0 && set.size() % 2 == 0) {
                mid = set.lower(mid);
            } else if (x.compareTo(mid) > 0 && set.size() % 2 == 1) {
                mid = set.higher(mid);
            }

            if (set.size() % 2 == 1) {
                result.add((double) mid.value);
            } else {
                result.add(((double) mid.value + set.higher(mid).value) / 2.0);
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Double> result = Result.runningMedian(a);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}