package generics.wildcards;

public class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }

    public void putFrom(Box<? extends T> box) {
        this.put(box.get());
    }

    public void putTo(Box<? super T> dest) {
        dest.put(this.get());
    }
}