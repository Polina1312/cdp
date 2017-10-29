package owncollection.arrayset;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class ArraySetTest {
    @Test
    public void add() throws Exception {

        ArraySet<String> ar2 = new ArraySetImpl<String>();
        ar2.add("1");
        ar2.add("2");
        ar2.add("3");
        ar2.add("3");
        ar2.add("4");
        ar2.add("4");

        Assert.assertTrue(ar2.size() == 4);
    }

    @Test
    public void remove() throws Exception {

        ArraySet<String> ar2 = new ArraySetImpl<String>();
        ar2.add("1");
        ar2.add("2");
        ar2.add("3");
        ar2.add("3");
        ar2.delete("2");
        Assert.assertEquals(2, ar2.size());

        ar2.delete(0);
        Assert.assertEquals("3", ar2.get(0));

    }

    @Test
    public void get() throws Exception {

        ArraySet<String> ar2 = new ArraySetImpl<String>();
        ar2.add("1");
        ar2.add("2");
        ar2.add("3");
        ar2.add("3");

        Assert.assertEquals("2", ar2.get(1));
    }

    @Test
    public void iter() throws Exception {

        ArraySet<String> ar2 = new ArraySetImpl<String>();
        ar2.add("1");
        ar2.add("2");
        ar2.add("3");
        ar2.add("3");


        Iterator it = ar2.iterator();
        while (it.hasNext())
        {
            it.next();
            it.remove();
        }

        Assert.assertTrue(ar2.size() == 0);
    }


}