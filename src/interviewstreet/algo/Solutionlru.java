import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solutionlru {

    static class LRUCache<T, V> {
        Node<T, V> head;
        Node<T, V> tail;
        Map<T, Node<T, V>> cache;
        int maxSize;
        int currentSize;

        @Override
        public String toString() {
            return "LRUCache [head=" + head + ", tail=" + tail + ", cache=" + cache + ", maxSize=" + maxSize
                    + ", currentSize=" + currentSize + "]";
        }

        public StringBuilder dump() {
            StringBuilder builder = new StringBuilder();
            for (Entry<T, Node<T, V>> entry : cache.entrySet()) {
                builder.append(entry.getKey()).append(" ");
                builder.append(entry.getValue().getValue()).append("\n");
            }
            return builder;
        }

        public LRUCache() {
            cache = new TreeMap<>();
            head = tail = null;
            maxSize = 0;
            currentSize = 0;
        }

        private class Node<T, V> {
            private T key;
            private V value;
            private Node<T, V> next;
            private Node<T, V> prev;

            private Node(T key, V value) {
                this.setKey(key);
                this.setValue(value);
                setNext(null);
                setPrev(null);
            }

            @Override
            public String toString() {
                return "Node [key=" + key + ", value=" + value + ", next=" + next + "]";
            }

            public Node<T, V> getPrev() {
                return prev;
            }

            public void setPrev(Node<T, V> prev) {
                this.prev = prev;
            }

            public Node<T, V> getNext() {
                return next;
            }

            public void setNext(Node<T, V> next) {
                this.next = next;
            }

            public V getValue() {
                return value;
            }

            public void setValue(V value) {
                this.value = value;
            }

            public T getKey() {
                return key;
            }

            public void setKey(T key) {
                this.key = key;
            }
        }

        public void set(T key, V value) {
            Node<T, V> node = getNode(key);
            if (node == null) {
                node = new Node<>(key, value);
                insertNew(node);
                cache.put(key, node);
            } else {
                node.setValue(value);
            }
        }

        private void insertNew(Node<T, V> node) {
            if (this.head == null) {
                this.head = this.tail = node;
            } else {
                node.setNext(this.head);
                this.head.setPrev(node);
                this.head = node;
            }
            currentSize++;
            balance();
        }

        private void balance() {
            while (currentSize > maxSize) {
                cache.remove(this.tail.getKey());
                this.tail = this.tail.getPrev();
                --currentSize;
            }
            if (this.tail == null) {
                this.head = this.tail;
            } else if (this.tail.getNext() != null) {
                Node<T, V> tmp = this.tail.getNext();
                this.tail.setNext(null);
                tmp.setPrev(null);
            }
        }

//
//        private void balance() {
//            while (currentSize > maxSize) {
//
//                cache.remove(this.tail.getKey());
//                Node<T, V> tmp = this.tail;
//                this.tail = this.tail.getPrev();
//                if (this.tail == null) {
//                    this.head = this.tail;
//                } else {
//                    this.tail.setNext(null);
//                }
//                tmp.setPrev(null);
//                --currentSize;
//            }
//        }

        private Node<T, V> getNode(T key) {
            Node<T, V> node = cache.get(key);
            if (node == null) {
                return null;
            }
            if (node.getPrev() != null) {
                node.getPrev().setNext(node.getNext());
                if (node.getNext() != null) {
                    node.getNext().setPrev(node.getPrev());
                } else {
                    this.tail = node.getPrev();
                }
                node.setNext(this.head);
                this.head.setPrev(node);
                this.head = node;
                node.setPrev(null);
            }

            return node;
        }

        public V get(T key) {
            Node<T, V> node = getNode(key);
            if (node == null) {
                return null;
            }
            return node.getValue();
        }

        public V peek(T key) {
            Node<T, V> node = cache.get(key);
            if (node == null) {
                return null;
            }
            return node.getValue();
        }

        public void bound(int sz) {
            this.maxSize = sz;
            balance();
        }

    }

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        LRUCache<String, String> lruCache = new LRUCache<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            String read = br.readLine();
            String[] input = read.trim().split(" ");
            // System.out.print(read + "#");
            // System.out.println(lruCache);
            switch (input[0]) {
                case "BOUND":
                    lruCache.bound(Integer.parseInt(input[1]));
                    break;
                case "SET":
                    lruCache.set(input[1], input[2]);
                    break;
                case "GET":
                    String value1 = lruCache.get(input[1]);
                    builder.append((value1 == null ? "NULL" : value1)).append("\n");
                    break;
                case "PEEK":
                    String value2 = lruCache.peek(input[1]);
                    builder.append((value2 == null ? "NULL" : value2)).append("\n");
                    break;
                case "DUMP":
                    builder.append(lruCache.dump());
                default:
                    break;
            }
        }
        System.out.print(builder);
    }
}
