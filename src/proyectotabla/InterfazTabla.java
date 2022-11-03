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
public interface InterfazTabla {
    
    void addRow(int index, ListaAleatoria row);
    void addColumn(int index);
    void addColumn(int index, ListaAleatoria column);
    void removeRow(int index);
    void removeColumn(int index);
    ListaAleatoria getRow(int index);
    Object getValueAt(int indexRow, int indexColumn);
    void setValueAt(int indexRow, int indexColumn, Object o);
    int getRowCount();
    int getColumnCount();
    boolean isEmpty();
}
