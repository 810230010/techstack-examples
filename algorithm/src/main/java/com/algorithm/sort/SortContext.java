package com.algorithm.sort;

/**
 * 策略模式
 */
public class SortContext extends AbstractSort{
    private AbstractSort sortMethod;

    public AbstractSort getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(AbstractSort sortMethod) {
        this.sortMethod = sortMethod;
    }

    public SortContext(AbstractSort abstractSort){
        this.sortMethod = abstractSort;
    }
    public static void main(String[] args) {
        SortContext sortContext = new SortContext(new BubbleSort());
        sortContext.sort(new int[]{3,1,2,6,4,4});
    }

    @Override
    protected void sort(int[] arr) {
        sortMethod.sort(arr);
    }
}
