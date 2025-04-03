public class LeftistHeap {
    private Task root;

    public LeftistHeap(){
        this.root = null;
    }
    public void insert(Task n){
        this.root = merge(this.root, n);
    }

    public Task extractMin(){
        if(this.root == null){return null;}
        Task min = this.root;
        // Return the original value that was inputted.
        min.prioLvl *= -1;
        this.root = merge(this.root.left, this.root.right);
        return min;
    }
    public void merge (LeftistHeap h1, LeftistHeap h2){
        this.root = merge(h1.root, h2.root);
    }
    public Task merge(Task h1, Task h2){
        if(h1 == null){return h2;}
        if(h2 == null){return h1;}
        // Make sure h1 has the "smaller" priority
        if(h1.prioLvl > h2.prioLvl || h1.prioLvl == h2.prioLvl && h1.order > h2.order){
            Task temp = h2;
            h2 = h1;
            h1 = temp;
        }
        if(h1.left == null) {
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if(h1.left.npl < h1.right.npl){
                Task temp = h1.left;
                h1.left = h1.right;
                h1.right = temp;
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }
    public void delete(Task n){
        this.root = deleteHelper(this.root, n);
        n.npl = 0;
    }

    public Task deleteHelper(Task r, Task n){
        if(r == null){return null;}
        if(r.id.equals(n.id)){
            return merge(r.left, r.right);
        }
        r.left = deleteHelper(r.left, n);
        r.right = deleteHelper(r.right, n);
        return r;
    }
    // public static void main(String[] args){
    //     String[] dep = new String[1];
    //     dep[0] = "T1";
    //     Task n1 = new Task("T1", 10, null);
    //     Task n2 = new Task("T2", 10, dep);
    //     Task n3 = new Task("T3", 10, null);
    //     Task n4 = new Task("T4", 10, null);
    //     LeftistHeap heap = new LeftistHeap();
    //     heap.insert(n1);
    //     heap.insert(n3);
    //     // heap.insert(n4);
    //     heap.insert(n2);
    //     Task n5 = heap.extractMin();
    //     Task n6 = heap.extractMin();
    //     Task n7 = heap.extractMin();
    // }   
}

// This should suffice for the priority queue.

