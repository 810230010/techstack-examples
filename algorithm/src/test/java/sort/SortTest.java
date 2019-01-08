package sort;

import com.algorithm.sort.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SortTest {
    private int[] arr;
    @Before
    public void init(){
        arr = new int[]{1, 5,2,3,56};
        System.out.println("排序前: " + Arrays.toString(arr));
    }
    @Test
    public void bubbleSort(){
        SortContext sortContext = new SortContext(new BubbleSort());
        sortContext.sort(arr);
        assertEquals(Arrays.toString(new int[]{1,2,3,5,56}), Arrays.toString(arr));
    }
    @Test
    public void selectionSort(){
        SortContext sortContext = new SortContext(new SelectionSort());
        sortContext.sort(arr);
        assertEquals(Arrays.toString(new int[]{1,2,3,5,56}), Arrays.toString(arr));
    }

    @Test
    public void insertSort(){
        SortContext sortContext = new SortContext(new InsertSort());
        sortContext.sort(arr);
        assertEquals(Arrays.toString(new int[]{1,2,3,5,56}), Arrays.toString(arr));
    }

    @Test
    public void shellSort(){
        SortContext sortContext = new SortContext(new ShellSort());
        sortContext.sort(arr);
        assertEquals(Arrays.toString(new int[]{1,2,3,5,56}), Arrays.toString(arr));
    }

    @Test
    public void quickSort(){
        SortContext sortContext = new SortContext(new QuickSort());
        sortContext.sort(arr);
        assertEquals(Arrays.toString(new int[]{1,2,3,5,56}), Arrays.toString(arr));
    }
    @After
    public void getResult(){
        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
