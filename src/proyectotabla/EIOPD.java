/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotabla;

/**
 *
 * @author Aguchintlv
 */
public class EIOPD {

    private final Tabla EIPrimario;
    private final Tabla ESIndexada;

    //Inicializa la cantidad de columnas que tendran las estructuras
    public EIOPD(int column) {
        EIPrimario = new Tabla(0, 2);
        ESIndexada = new Tabla(0, column + 1);
    }
    //Inserta un registro o fila en las estructuras segun corresponda
    public void insert(Object... e) {
        //Verifica si la cantidad de elementos e coincide con la cantidad de columnas que tiene la estrctura secuencial indexada
        if (e.length != ESIndexada.getColumnCount() - 1) {
            throw new IllegalArgumentException("insert: e.length != columnSize of Estructura Secuencial Indexada");
        }
        //Ingresa la nueva fila al final de la estructura de secuencial indexada
        ESIndexada.addRow(ESIndexada.getRowCount(), Tabla.newRowExtra(e));
        //Ingresa por primera vez la fila en la estructura de indices primarios
        if (EIPrimario.isEmpty()) {
            EIPrimario.addRow(0, Tabla.newRow(e[0], ESIndexada.getRowCount() - 1));
        } else {
            //Hace una busqueda si el siguiente indice ingresado existe y lo inserta en el orden ascendente que le corresponda
            for (int i = 0; i < EIPrimario.getRowCount(); i++) {
                //No inserta si el indice ya existe
                if (EIPrimario.getValueAt(i, 0).equals(e[0])) {
                    return;
                } else //Inserta en "i" si el indice es menor al elemento de esa posicion
                    if ((Integer.parseInt(e[0].toString().trim())) < (Integer.parseInt(EIPrimario.getValueAt(i, 0).toString().trim()))) {
                    EIPrimario.addRow(i, Tabla.newRow(e[0], ESIndexada.getRowCount() - 1));
                    return;
                } else //Inserta al final si ya no hay mas elementos con que comparar y si es el nuevo mayor
                        if (i == EIPrimario.getRowCount() - 1) {
                    EIPrimario.addRow(i + 1, Tabla.newRow(e[0], ESIndexada.getRowCount() - 1));
                }
            }
        }
    }
    //Genera los enlaces que unen los indices ordenados para usarlos en la busqueda secuencial
    public void generarEnlaces() {
        for (int i = 0, m = 0; i < EIPrimario.getRowCount(); i++) {
            m = (int) EIPrimario.getValueAt(i, 1);
            ListaAleatoria primario = EIPrimario.getRow(i);
            for (int j = (int) primario.get(1) + 1, n = (int) primario.get(1); j < ESIndexada.getRowCount(); j++) {
                ListaAleatoria temp = ESIndexada.getRow(j);
                if (primario.get(0).equals(temp.get(0))) {
                    ESIndexada.setValueAt(n, ESIndexada.getColumnCount() - 1, j);
                    n = j;
                    m = j;
                }
            }
            if (i < EIPrimario.getRowCount() - 1 && m < ESIndexada.getRowCount()) {
                ESIndexada.setValueAt(m, ESIndexada.getColumnCount() - 1, EIPrimario.getValueAt(i + 1, 1));
            }
        }
    }
    //Elimina 1 o varias ocurrencias de las extructuras que contengan el indice indicado
    public boolean remove(int id) {
        boolean existe = false;
        int index = -1;
        for (int i = 0; i < EIPrimario.getRowCount(); i++) {
            if (EIPrimario.getValueAt(i, 0).equals(id + "")) {
                index = Integer.parseInt(EIPrimario.getValueAt(i, 1).toString());
                EIPrimario.removeRow(i);
                existe = true;
                break;
            }
        }
        for (int i = index; i < ESIndexada.getRowCount();) {
            if (ESIndexada.getValueAt(i, 0).equals(id + "")) {
                ESIndexada.removeRow(i);
            } else {
                i++;
            }
        }
        return existe;
    }
    //Genera una tabla resultante conteniendo todos las ocurrencia que contengan el indice indicado
    public Tabla buscar(int id) {
        Tabla temp = new Tabla(0, ESIndexada.getColumnCount());
        int index = -1;
        for (int i = 0; i < EIPrimario.getRowCount(); i++) {
            if (EIPrimario.getValueAt(i, 0).equals(id + "")) {
                index = Integer.parseInt(EIPrimario.getValueAt(i, 1).toString());
                break;
            } else if (id < (Integer.parseInt(EIPrimario.getValueAt(i, 0).toString().trim()))) {
                index = Integer.parseInt(EIPrimario.getValueAt(i - 1, 1).toString());
                break;
            } else {

            }
        }
        while (index >= 0) {
            if (ESIndexada.getValueAt(index, 0).equals(id + "")) {
                temp.addRow(temp.getRowCount(), ESIndexada.getRow(index));
                index = Integer.parseInt(ESIndexada.getValueAt(index, ESIndexada.getColumnCount() - 1).toString());
            } else {
                index = -1;
            }
        }
        if (!temp.isEmpty()) {
            temp.removeColumn(temp.getColumnCount() - 1);
        }
        return temp;
    }

    public Tabla getEIPrimario() {
        return EIPrimario;
    }

    public Tabla getESIndexada() {
        return ESIndexada;
    }

    public boolean isEmpty() {
        return ESIndexada.isEmpty();
    }

    @Override
    public String toString() {
        return "EIOPD{" + "EIPrimario=\n" + EIPrimario + "ESIndexada=\n" + ESIndexada + '}';
    }

}
