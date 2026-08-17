public final class ManualArray<T> {
    
    private Object[] data;
    
    private int size;

    public ManualArray() {
        this(8);
    }

    public ManualArray(int capacity) {
        data = new Object[Math.max(1, capacity)];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        insertAt(size, value);
    }

    public void insertAt(int index, T value) {
        checkPosition(index); 
        ensureCapacity(size + 1); 

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkIndex(index);
        T removed = (T) data[index];
    
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        data[size - 1] = null;
        size--;
        return removed;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity(int needed) {
        if (needed <= data.length) {
            return;
        }
        
        Object[] bigger = new Object[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            bigger[i] = data[i];
        }
       
        data = bigger;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
