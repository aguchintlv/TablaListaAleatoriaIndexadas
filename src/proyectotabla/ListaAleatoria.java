package proyectotabla;

/**
 *
 * @author Aguchintlv
 */
public class ListaAleatoria implements InterfazLista{  
        
    private Object[] elArreglo;
    private int size;

    public ListaAleatoria(int capacidad){
        elArreglo = new Object[capacidad];
        size=0;
    }

    public ListaAleatoria(){
        this(1024);
    }

    //implementacion de los operadores

    public void add(int index, Object o){
        if(index<0 || index>size){
            throw new IllegalArgumentException("add: index<0 || index>size");
        }
        if(isEmpty()){//la elArregloesta vacio
            elArreglo[index]=o;
        }else if(index==0){//insertar al comienzo
            moveR(index);
            elArreglo[index]=o;
        }else if(index==size){//insertar al final
            elArreglo[index]=o;                
        }else{//insertar por el medio
            moveR(index);
            elArreglo[index]=o;
        }
        size++;
    }

    public void remove(int index){
        if(index<0 || index>(size-1)){
            throw new IllegalArgumentException("remove: index<0 || index>(size-1)");
        }
        if(index==0){//eliminar al comienzo
            moveL(index);
        }else if(index==size-1){//eliminar al final
            //nada
        }else{//eliminar por el medio
            moveL(index);
        }
        size--;
    }
    
    public int indexOf(Object o){//un bucle para encontrar la primera ocurrencia
        int i;
        for (i = 0;  i< size; i++) {
            if(o.toString().equals(elArreglo[i].toString())) {//comparar la version string del sigueinte elemento
                break;
            }
        }
        return i==size ? -1 : i;
    }
    
    public Object get(int index){
        if(index<0 || index>(size-1)){//la misma condicion de remove
            throw new IllegalArgumentException("get: index<0 || index>(size-1)");
        }
        return elArreglo[index];
    }
    
    public void clear(){
        size=0;
    }
    
    public boolean isEmpty(){
    return size==0;
    }
    
    public int length(){
        return size;
    }

    //------------------------
    
    //publicos que no son operadores
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if(i!=0){
                sb.append("," + elArreglo[i]);
            }else{
                sb.append(elArreglo[i] + "");
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    //--------------------
    
    private void moveR(int top){
        for (int i = size; i > top; i--) {
            elArreglo[i] = elArreglo[i-1];
        }
    }
    
    private void moveL(int top){
        for (int i = top; i < (size-1); i++) {
            elArreglo[i] = elArreglo[i+1];
        }
    }
}
