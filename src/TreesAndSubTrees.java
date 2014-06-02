import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreesAndSubTrees {

    public static class Chef {

        private Chef left;
        private Chef right;
        private int age;

        public Chef(int age) {
            this.age = age;
        }

        public void addReportee(Chef chef, char time) {
            if (time == 'M') {
                this.left = chef;
            } else {
                this.right = chef;
            }
        }
    }

    public static String isSubTree(TreesAndSubTrees.Chef chef, TreesAndSubTrees.Chef anotherChef) {
        return match(chef, anotherChef) ? "YES" : "NO";
    }

    private static boolean match(Chef chef, Chef anotherChef) {
        Stack<Chef> allChefs = new Stack<Chef>();
        allChefs.push(chef);
        while (!allChefs.isEmpty()) {
            Chef curChef = allChefs.pop();
            boolean result = seek(curChef, anotherChef);
            if (!result) {
                if (curChef.left != null) {
                    allChefs.push(curChef.left);
                }
                if (curChef.right != null) {
                    allChefs.push(curChef.right);
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private static boolean seek(Chef chef, Chef anotherChef) {
        if (chef == null && anotherChef == null) {
            return true;
        } else if (chef == null || anotherChef == null) {
            return false;
        } else if (chef.age == anotherChef.age) {
            return seek(chef.left, anotherChef.left) && seek(chef.right, anotherChef.right);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader programInput = new BufferedReader(new InputStreamReader(System.in));
        Chef mainTree = getHierarchy(programInput);
        int nQueries = Integer.valueOf(programInput.readLine());
        for (int i = 0; i < nQueries; i++) {
            Chef otherTree = getHierarchy(programInput);
            System.out.println(isSubTree(mainTree, otherTree));
        }
    }

    private static Chef getHierarchy(BufferedReader programInput) throws IOException {
        int numberOfChefs = Integer.valueOf(programInput.readLine());
        List<Chef> allChefs = new ArrayList<Chef>(numberOfChefs < 1000 ? numberOfChefs : 1000 * (numberOfChefs / 1000));
        for (int i = 0; i < numberOfChefs; i++) {
            int age = Integer.valueOf(programInput.readLine());
            allChefs.add(new Chef(age));
        }
        for (int i = 0; i < numberOfChefs - 1; i++) {
            String line = programInput.readLine();
            List<String> split = tokenize(line);
            Chef senior = allChefs.get(Integer.valueOf(split.get(0)) - 1);
            Chef junior = allChefs.get(Integer.valueOf(split.get(1)) - 1);
            senior.addReportee(junior, split.get(2).charAt(0));
        }
        return allChefs.get(0);
    }

    private static List<String> tokenize(String line) {
        List<String> results = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length(); ) {
            char c = line.charAt(i);
            if (c != ' ') {
                builder.append(c);
            } else if (builder.length() > 0) {
                results.add(builder.toString());
                builder = new StringBuilder();
            }
            i++;
        }
        if (builder.length() > 0)
            results.add(builder.toString());
        return results;
    }
}
