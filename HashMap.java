import java.util.LinkedList;
public class HashMap { 
    private LinkedList<Node>[] HashArr;
    public HashMap(int size){
        HashArr = new LinkedList[size];
    }  

    // public int hash_func(Node s){
    //     return s.hashCode() % HashArr.length;
    // }
    public int hash_func(String s){
        return s.hashCode() % HashArr.length;
    }
    public void add(String key, Node s){
        int index = hash_func(key);
        if(HashArr[index] == null){
            HashArr[index] = new LinkedList<Node>();
        }
        HashArr[index].add(s);
    }

    public Node find(String key, Node s){
        int index = hash_func(key);
        // Object does not exist and was not init
        if(HashArr[index] == null){
            return null;
        }
        int objIndex = HashArr[index].indexOf(s);
        // Spot init but did not find it
        if(objIndex != -1){
            return HashArr[index].get(objIndex);
        }
        return null;
    }

    public void remove(String key, Node s){
        int index = hash_func(key);
        if(HashArr[index] == null){
            return;
        }
        int objIndex = HashArr[index].indexOf(s);
        // Spot init but did not find it
        if(objIndex != -1){
            HashArr[index].remove(objIndex);
        }
    }
    public LinkedList<Node> getValues(String key){
        int index = hash_func(key);
        if(HashArr[index] == null){
            return null;
        } 
        return HashArr[index];
    }
    public static void main(String[] args) {
        HashMap hash = new HashMap(11901);

        Node n1 = new Node("T1", 10, null);
        Node n2 = new Node("T2", 10, null);
        Node n3 = new Node("T3", 10, null);
        Node n4 = new Node("T4", 10, null);


        hash.add("T1", n1);
        // hash.add("Hi");
        // hash.add("Carlos");
        // hash.add("Peter");
    }
}

