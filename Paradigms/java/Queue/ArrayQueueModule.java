package queue;

/**
 * Inv: a[0], a[1], ..., a[n - 1]
 * n >= 0
 * \forall i: 0 <= i < n : a[i] != null
 */
public class ArrayQueueModule {
    private static Object[] data = new Object[2];
    private static int head;
    private static int size;

    /**
     * Pre:
     * Post: a' = a && n' = n
     */
    private static void expand() {
        if (size != data.length) {
            return;
        }
        Object[] newData = new Object[2 * data.length];
        System.arraycopy(data, head, newData, 0, data.length - head);
        System.arraycopy(data, 0, newData, data.length - head, head);
        head = 0;
        data = newData;
    }

    /**
     * Removes first element from queue and returns its value
     * Pre: n > 0
     * Post: n' = n - 1 && a' = [a[1], ... a[n - 1]]
     * @return {Object} a[0]
     */
    public static Object dequeue() {
        assert size > 0;

        Object result = element();
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return result;
    }

    /**
     * Pushes new element in queue
     * Pre: val != null
     * Post: n' = n + 1 && a' = [a[0]..a[n - 1], val]
     * @param val {Object} value to push into queue
     */
    public static void enqueue(Object val) {
        assert val != null;

        if (size == data.length) {
            expand();
        }
        data[(head + size) % data.length] = val;
        size++;
    }

    /**
     * Adds given element to the beginning of the queue
     * Pre: val != null
     * Post: n' = n + 1 && a' = [val, a[0], ..., a[n - 1]]
     * @param val {Object} Value to add into queue
     */
    public static void push(Object val) {
        assert val != null;

        expand();
        data[(head + data.length - 1) % data.length] = val;
        head = (head + data.length - 1) % data.length;
        size++;
    }

    /**
     * Returns last element of the queue
     * Pre: n > 0
     * Post: Immutable
     * @return {Object} a[n-1]
     */
    public static Object peek() {
        assert size > 0;

        return data[(head + size - 1) % data.length];
    }

    /**
     * Removes last element of the queue and returns its value
     * Pre: n > 0
     * Post: n' = n - 1 && a' = [a[0], ..., a[n - 2]]
     * @return {Object} a[n-1]
     */
    public static Object remove() {
        assert size > 0;

        Object result = peek();
        data[(head + size - 1) % data.length] = null;
        size--;
        return result;
    }

    /**
     * Returns element of the queue by index
     * Pre: 0 <= index < n
     * Post: immutable
     * @param index {Integer} Index of element to get
     * @return {Object} a[index]
     */
    public static Object get(int index) {
        assert 0 <= index && index < size;

        return data[(head + index) % data.length];
    }

    /**
     * Sets element of the queue to value y index
     * Pre: 0 <= index < n && val != null
     * Post: n' = n && a' = [a[0], ..., a[index - 1], val, a[index + 1], ... a[n - 1]]
     * @param index {Integer} Index of element to set
     * @param val {Object} Object to set element to
     */
    public static void set(int index, Object val) {
        assert 0 <= index && index < size && val != null;

        data[(head + index) % data.length] = val;
    }

    /**
     * Returns value of first element in queue
     * Pre: n > 0
     * Post: immutable
     * @return {Object} a[0]
     */
    public static Object element() {
        assert size > 0;

        return data[head];
    }

    /**
     * Returns size of queue
     * Post: immutable
     * @return n
     */
    public static int size() {
        return size;
    }

    /**
     * Returns true if queue is empty
     * Post: immutable
     * @return n == 0
     */
    public static boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the queue
     * Post: n = 0 && a' = []
     */
    public static void clear() {
        data = new Object[2];
        head = size = 0;
    }
}