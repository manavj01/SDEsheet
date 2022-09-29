package d14_StacksAndQueue2;
import java.util.*;


class LRUCache{
public class DLL {
    int[] v;
    DLL prev;
    DLL next;

    DLL(int[] v, DLL prev, DLL next) {
        this.v = new int[2];
        this.prev = prev;
        this.next = next;
    }
    
    DLL(int key, int val){
       v = new int[2];
        v[0] = key;
        v[1] =val;
    }

    DLL() {}

}

HashMap<Integer, DLL> map;
int cap;
DLL head;
DLL tail;

public LRUCache(int capacity) {
    map = new HashMap<>();
    cap = capacity;
    head = new DLL(-1,-1);
    tail = new DLL(-1,-1);
    head.next = tail;
    head.prev = null;
    tail.next = null;
    tail.prev = head;
}

public int get(int key) {
    if (map.get(key) == null) return -1;

    int val = map.get(key).v[1];
    DLL keyNode = map.get(key);
    putNodeInPlace(keyNode);
    return val;
}

public void put(int key, int value) {
    if (!map.containsKey(key)) {
        //DLL node = new DLL(value);
        if (map.size() == cap) {
            // delete node before tail;
            int k = tail.prev.v[0];
            DLL nodePrev = tail.prev.prev;
            DLL nodeNext = tail;
            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;
            map.remove(k);
        }
        // add node after head;
        DLL headNext = head.next;
        DLL tba = new DLL(key,value);
        tba.next = head.next;
        headNext.prev = tba;
        tba.prev = head;
        head.next = tba;
        map.put(key, tba);
    } else {
        DLL node = map.get(key);

        // delete node
        DLL nodePrev = node.prev;
        DLL nodeNext = node.next;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;
        map.remove(key);
        // add node
        DLL headNext = head.next;
        DLL tba = new DLL(key,value);
        tba.next = head.next;
        headNext.prev = tba;
        tba.prev = head;
        head.next = tba;
        map.put(key, tba);
    }
}

public void putNodeInPlace(DLL node) {
    // removing the node
    DLL nodePrev = node.prev;
    DLL nodeNext = node.next;
    nodePrev.next = nodeNext;
    nodeNext.prev = nodePrev;
    // put after head
    DLL headNext = head.next;
    node.prev = head;
    node.next = headNext;
    headNext.prev = node;
    head.next = node;
}
}