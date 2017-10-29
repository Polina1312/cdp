package owncollection.arrayset;


public interface ArraySet<T> extends Iterable<T> {

    boolean add(T t);

    T delete(int index);

    T delete(T obj);

    T get(int index);

    int size();

    void update(int index, T t);


}
