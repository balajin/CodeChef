import org.junit.Test;

import static org.junit.Assert.*;

public class TreesAndSubTreesTest {

    @Test
    public void singleNodeWhichIsASubTree() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, chef));
    }

    @Test
    public void singleNodeWhichIsASubTreePassingADifferentInstance() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void singleNodeWhichIsNotASubTreeDueToDifferenceInAge() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(11);
        assertEquals("NO", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootsWithSameAgeButLeftChildHavingDifferentAgeIsNotSubTree() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'M');
        assertEquals("NO", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootsWithSameAgeButRightChildHavingDifferentAgeIsNotSubTree() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'E');
        assertEquals("NO", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedAtLeftOfRoot() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        chef.addReportee(anotherChef, 'M');
        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedAtRightOfRoot() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        chef.addReportee(anotherChef, 'E');
        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedTwoLevelsDownToTheLeft() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef childOfChef = new TreesAndSubTrees.Chef(12);
        chef.addReportee(childOfChef, 'M');

        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'M');
        childOfChef.addReportee(anotherChef, 'M');

        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedTwoLevelsDownToTheRight() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef childOfChef = new TreesAndSubTrees.Chef(12);
        chef.addReportee(childOfChef, 'E');

        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'E');
        childOfChef.addReportee(anotherChef, 'E');

        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedAtRightOfTheLeftChild() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef childOfChef = new TreesAndSubTrees.Chef(12);
        chef.addReportee(childOfChef, 'M');

        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'E');
        childOfChef.addReportee(anotherChef, 'E');

        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }

    @Test
    public void rootedAtLeftOfTheRightChild() {
        TreesAndSubTrees.Chef chef = new TreesAndSubTrees.Chef(10);
        TreesAndSubTrees.Chef childOfChef = new TreesAndSubTrees.Chef(12);
        chef.addReportee(childOfChef, 'E');

        TreesAndSubTrees.Chef anotherChef = new TreesAndSubTrees.Chef(10);
        anotherChef.addReportee(new TreesAndSubTrees.Chef(11), 'E');
        childOfChef.addReportee(anotherChef, 'M');

        assertEquals("YES", TreesAndSubTrees.isSubTree(chef, anotherChef));
    }
}