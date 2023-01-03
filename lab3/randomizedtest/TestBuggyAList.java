package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public  void testThreeAddThreeRemove(){

        AListNoResizing<Integer> AListNoResizing = new AListNoResizing<>();
        BuggyAList BuggyAList =new BuggyAList<>();

        AListNoResizing.addLast(4);
        BuggyAList.addLast(4);

        AListNoResizing.addLast(5);
        BuggyAList.addLast(5);

        AListNoResizing.addLast(6);
        BuggyAList.addLast(6);

        assertEquals(AListNoResizing.size(),BuggyAList.size());


        assertEquals(AListNoResizing.removeLast(),BuggyAList.removeLast());
        assertEquals(AListNoResizing.removeLast(),BuggyAList.removeLast());
        assertEquals(AListNoResizing.removeLast(),BuggyAList.removeLast());
  }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken =new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 3);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast correct (" + randVal + ")");
                System.out.println("addLast broken (" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = correct.size();
                int sizeBroken =  broken.size();
                assertEquals(correct.size(),broken.size());

//                System.out.println("correct size: " + size);
//                System.out.println("broken size: " + sizeBroken);


                if(correct.size()>0 && broken.size()>0){
                    int getLast = correct.getLast();
                    int getLastB = broken.getLast();

//                    System.out.println("correct the last: " + getLast);
//                    System.out.println("remove the last: " + getLastB);
                    assertEquals(getLast,getLastB);



                    int removed = correct.removeLast();
                    int removed2 = broken.removeLast();

                    assertEquals(removed,removed2);

//                    System.out.println("correct removed: " + removed);
//                    System.out.println("broken removed: " + removed2);





                }



            }
        }
    }



    public  void main(String[] args) {
//        testThreeAddThreeRemove();

        randomizedTest();
    }
}
