import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.novichikhin.MakingPairs;
import ru.vsu.cs.util.ListUtils;

import java.util.List;


public class MakingPairsTest {

    @Test
    public void testCreatePairsForFirstThreeCourse() {
        List<List<String>> students = ListUtils.readList2FromFile("testSrc/TestFiles/TestCase/inputForMakingPairsTest01.txt");
        List<List<String>> expectedList = ListUtils.readList2FromFile("testSrc/TestFiles/TestResult/outputForMakingPairsTest01.txt");

        assert students != null;

        MakingPairs makingPair = new MakingPairs();
        List<List<String>> pairs = makingPair.createPairs(students);

        Assert.assertEquals(expectedList, pairs);
    }

    @Test
    public void testCreatePairsForSecondAndThirdCourses() {
        List<List<String>> students = ListUtils.readList2FromFile("testSrc/TestFiles/TestCase/inputForMakingPairsTest02.txt");
        List<List<String>> expectedList = ListUtils.readList2FromFile("testSrc/TestFiles/TestResult/outputForMakingPairsTest02.txt");

        assert students != null;

        MakingPairs makingPair = new MakingPairs();
        List<List<String>> pairs = makingPair.createPairs(students);

        Assert.assertEquals(expectedList, pairs);
    }

    @Test
    public void testCreatePairsForPairOnAllCourses() {
        List<List<String>> students = ListUtils.readList2FromFile("testSrc/TestFiles/TestCase/inputForMakingPairsTest03.txt");
        List<List<String>> expectedList = ListUtils.readList2FromFile("testSrc/TestFiles/TestResult/outputForMakingPairsTest03.txt");

        assert students != null;

        MakingPairs makingPair = new MakingPairs();
        List<List<String>> pairs = makingPair.createPairs(students);

        Assert.assertEquals(expectedList, pairs);
    }

    @Test
    public void testCreatePairsForOneCourse() {
        List<List<String>> students = ListUtils.readList2FromFile("testSrc/TestFiles/TestCase/inputForMakingPairsTest04.txt");
        List<List<String>> expectedList = ListUtils.readList2FromFile("testSrc/TestFiles/TestResult/outputForMakingPairsTest04.txt");

        assert students != null;

        MakingPairs makingPair = new MakingPairs();
        List<List<String>> pairs = makingPair.createPairs(students);

        Assert.assertEquals(expectedList, pairs);
    }

    @Test
    public void testCreatePairsForFirstAndFoursCourses() {
        List<List<String>> students = ListUtils.readList2FromFile("testSrc/TestFiles/TestCase/inputForMakingPairsTest05.txt");
        List<List<String>> expectedList = ListUtils.readList2FromFile("testSrc/TestFiles/TestResult/outputForMakingPairsTest05.txt");

        assert students != null;

        MakingPairs makingPair = new MakingPairs();
        List<List<String>> pairs = makingPair.createPairs(students);

        Assert.assertEquals(expectedList, pairs);
    }
}