package proyectotabla;
/**
 *
 * @author Aguchintlv
 */
public interface InterfazLista {
    void add(int index, Object o);
    void remove(int index);
    int indexOf(Object o);
    Object get(int index);
    void clear();
    boolean isEmpty();
    int length();
}
