import java.util.ArrayList;
public class StableHeap {
    private final ArrayList<Task> heap;

    public StableHeap() {
        heap = new ArrayList<>();
    }
    public void insert(Task t) {
        heap.add(t);
        int index = heap.size() - 1;
        t.index = index;
        siftUp(index);
    }

    public Task extractMax() {
        if (heap.isEmpty()) return null;
        Task result = heap.get(0);

        Task last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return result;
    }

    public void updatePriority(Task t, int newPriority) {
        int oldPriority = t.prioLvl;
        t.prioLvl = newPriority;

        // Determine whether to sift up or down
        if (newPriority > oldPriority) {
            siftUp(t.index);
        } else if (newPriority < oldPriority) {
            siftDown(t.index);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index * 2 + 1 < size) {
            int left = index * 2 + 1;
            int right = left + 1;
            int maxChild = left;
            if (right < size && compare(heap.get(right), heap.get(left)) > 0) {
                maxChild = right;
            }
            if (compare(heap.get(maxChild), heap.get(index)) > 0) {
                swap(maxChild, index);
                index = maxChild;
            } else break;
        }
    }

    private int compare(Task a, Task b) {
        if (a.prioLvl != b.prioLvl)
            return Integer.compare(a.prioLvl, b.prioLvl);
        return Long.compare(b.order, a.order); // Earlier = higher for stability
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        heap.get(i).index = i;
        heap.get(j).index = j;
    }
}
