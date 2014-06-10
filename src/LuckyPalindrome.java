import java.util.ArrayList;
import java.util.List;

public class LuckyPalindrome {

    private static final String search = "lucky";

    private static class Pair {
        int start;
        int end;

        public Pair(int start) {
            end = start + search.length();
        }
    }

    private static class AnalysedInput {
        String output;
        int cost;

        public AnalysedInput(IntermediateString string) {
            this.output = string.input;
            this.cost = string.cost;
        }
    }

    private static class IntermediateString {
        String input;
        int cost;

        public IntermediateString(String input, int cost) {
            this.input = input;
            this.cost = cost;
        }
    }

    private List<AnalysedInput> intermediateVector = new ArrayList<AnalysedInput>();

    public String convertToLucky(String input) {
        if (input.length() < 9) {
            System.out.println("unlucky");
            return "unlucky";
        }
        for (int i = 0; i < input.length() - search.length() && (i + ((2 * search.length() - 1)) <= input.length()); i++) {
            Pair pair = new Pair(i);
            intermediateVector.add(new AnalysedInput(residue(reserve_cost(cost(new IntermediateString(input, 0), pair), pair), pair)));
        }
        int leastCost = Integer.MAX_VALUE;
        AnalysedInput least = null;
        for (AnalysedInput analysedInput : intermediateVector) {
            if (analysedInput.cost < leastCost) {
                leastCost = analysedInput.cost;
                least = analysedInput;
            }
        }
        if (leastCost == Integer.MAX_VALUE) {
            System.out.println("unlucky");
            return "unlucky";
        } else {
            System.out.println(least.output);
            System.out.println(least.cost);
            return least.output;
        }
    }

    private IntermediateString residue(IntermediateString string, Pair pair) {
        for (int i = pair.end, j = string.input.length() - search.length() - 1; i < string.input.length() - search.length() - 1; i++, j--) {
            if (string.input.charAt(i) != string.input.charAt(j)) {
                string.cost++;
                StringBuilder stringBuilder = new StringBuilder(string.input);
                stringBuilder.setCharAt(i, string.input.charAt(j));
                string.input = stringBuilder.toString();
            }
        }
        return string;
    }

    private IntermediateString reserve_cost(IntermediateString intermediateString, Pair pair) {
        for (int i = intermediateString.input.length() - 1, j = 0; i >= pair.end - 1 && j < search.length(); i--, j++) {
            if (intermediateString.input.charAt(i) != search.charAt(j)) {
                intermediateString.cost++;
                StringBuilder stringBuilder = new StringBuilder(intermediateString.input);
                stringBuilder.setCharAt(i, search.charAt(j));
                intermediateString.input = stringBuilder.toString();
            }
        }
        return intermediateString;
    }

    private IntermediateString cost(IntermediateString intermediateString, Pair pair) {
        for (int i = pair.start; i < pair.end; i++) {
            if (intermediateString.input.charAt(i) != search.charAt(i % search.length())) {
                intermediateString.cost++;
                StringBuilder stringBuilder = new StringBuilder(intermediateString.input);
                stringBuilder.setCharAt(i, search.charAt(i % search.length()));
                intermediateString.input = stringBuilder.toString();
            }
        }
        return intermediateString;
    }
}
