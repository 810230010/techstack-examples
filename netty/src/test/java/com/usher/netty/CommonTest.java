package com.usher.netty;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonTest {
    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        ReferenceQueue<WeakReference<Map>> referenceQueue = new ReferenceQueue();
        WeakReference<Map> weakReference = new WeakReference(map, referenceQueue);

        System.out.println("GC BEFORE, map: " + weakReference.get());
        map = null;
        System.gc();

        System.out.println("GC AFTER, map: " + weakReference.get() + ",queue: " + referenceQueue.poll());
    }

}
