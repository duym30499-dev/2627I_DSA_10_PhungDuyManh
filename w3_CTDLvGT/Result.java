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

    private static class Node {
        char item;
        Node next;
    }

    private static class Stack {
        private Node first = null;

        boolean isEmpty() {
            return first == null;
        }

        void push(char c) {
            Node old = first;
            first = new Node();
            first.item = c;
            first.next = old;
        }

        char pop() {
            char c = first.item;
            first = first.next;
            return c;
        }
    }

    public static String isBalanced(String s) {
        Stack stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return "NO";
                char open = stack.pop();
                if (c == ')' && open != '(') return "NO";
                if (c == ']' && open != '[') return "NO";
                if (c == '}' && open != '{') return "NO";
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}