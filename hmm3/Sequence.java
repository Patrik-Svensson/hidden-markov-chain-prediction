public class Sequence<T> {
    public int length;
    private Object[] data;

    public Sequence(int length) {
        this.length = length;
        data = new Object[length];
    }

    public T getValue(int i) {
        return (T) data[i - 1];
    }

    public void setValue(int i, T value) {
        data[i - 1] = value;
    }
}
