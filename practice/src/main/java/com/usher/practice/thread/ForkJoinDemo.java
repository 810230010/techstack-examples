package com.usher.practice.thread;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.concurrent.RecursiveTask;

/**
 * 计算1到100
 * Fork/Join采用“工作窃取模式”，当执行新的任务时他可以将其拆分成更小的任务执行，并将小任务加到线程队列中,该队列是双端队列，然后再从一个随即线程中偷一个并把它加入自己的队列中。
 *
 * 就比如两个CPU上有不同的任务，这时候A已经执行完，B还有任务等待执行，这时候A就会将B队尾的任务偷过来，加入自己的队列中，对于传统的线程，ForkJoin更有效的利用的CPU资源！
 *
 * 我们来看一下ForkJoin的实现：实现这个框架需要继承RecursiveTask 或者 RecursiveAction ，RecursiveTask是有返回值的，相反Action则没有
 */
public class ForkJoinDemo {
    public static class CountTask extends RecursiveTask<Integer>{
        private static final int THREASH_HOLD = 4;
        @Override
        protected Integer compute() {
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
