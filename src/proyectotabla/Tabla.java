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
public class Tabla implements InterfazTabla {
    
    private final ListaAleatoria tabla;
    
    private int sizeRow;
    private int sizeColumn;

    //Inicializa la tabla con una dimension de row x column
    public Tabla(int row, int column) {
        if (row < 0) {
            throw new IllegalArgumentException("init: row<0");
        } else if (column < 0) {
            throw new IllegalArgumentException("init: column<0");
        }
        tabla = new ListaAleatoria();
        sizeRow = row;
        sizeColumn = column;        
        for (int i = 0; i < row; i++) {
            tabla.add(tabla.length(), new ListaAleatoria());
            for (int j = 0; j < column; j++) {
                ListaAleatoria temp = (ListaAleatoria) tabla.get(i);
                temp.add(temp.length(), "");
            }
        }        
    }
    //Metodo auxiliar que genera una ListaAleatoria a partir de "n" parametros "o" que se ingrese
    //Ejemplo newRow(1,"a","b","c",...)
    public static ListaAleatoria newRow(Object... o) {
        ListaAleatoria temp = new ListaAleatoria();
        for (int i = 0; i < o.length; i++) {
            temp.add(temp.length(), o[i]);
        }
        return temp;
    }
    //Lo mismo que newRow pero adicionando al final un elemento extra con valor -1
    public static ListaAleatoria newRowExtra(Object... o) {
        ListaAleatoria temp = new ListaAleatoria();
        for (int i = 0; i < o.length; i++) {
            temp.add(temp.length(), o[i]);
        }
        temp.add(temp.length(), -1);
        return temp;
    }
    //Agrega una fila a la tabla y recibe como parametros un indice y una lista
    @Override
    public void addRow(int index, ListaAleatoria row) {
        if (index < 0 || index > sizeRow) {
            throw new IllegalArgumentException("addRow: index<0 || index>sizeRow");
        }
        if (row.length() != sizeColumn) {
            throw new IllegalArgumentException("addRow: row.length != sizeRow");
        }
        tabla.add(index, row);
        sizeRow++;        
    }
    //Agrega una columna con elementos vacios en el indice idicado
    @Override
    public void addColumn(int index) {
        if (index < 0 || index > sizeColumn) {
            throw new IllegalArgumentException("addColumn: index<0 || index>sizeColumn");
        }
        for (int i = 0; i < sizeRow; i++) {
            ((ListaAleatoria) tabla.get(i)).add(index, "");
        }
        sizeColumn++;
    }
    //Agrega una columna a partir de una lista dada en el indice indicado
    @Override
    public void addColumn(int index, ListaAleatoria column) {
        if (index < 0 || index > sizeColumn) {
            throw new IllegalArgumentException("addColumn: index<0 || index>sizeColumn");
        }
        if (column.length() != sizeColumn) {
            throw new IllegalArgumentException("addColumn: column.length != sizeColumn");
        }
        for (int i = 0; i < sizeRow; i++) {
            ((ListaAleatoria) tabla.get(i)).add(index, column.get(i));
        }
        sizeColumn++;
    }    
    //Elimina una fila de la tabla en el indice indicado
    @Override
    public void removeRow(int index) {
        if (index < 0 || index > (sizeRow - 1)) {
            throw new IllegalArgumentException("removeRow: index<0 || index>(sizeRow-1)");
        }
        tabla.remove(index);
        sizeRow--;
    }
    //Elimina una columna de la tabla en el indice indicado
    @Override
    public void removeColumn(int index) {
        if (index < 0 || index > (sizeColumn - 1)) {
            throw new IllegalArgumentException("removeColumn: index<0 || index>(sizeColumn-1)");
        }
        for (int i = 0; i < sizeRow; i++) {
            ((ListaAleatoria) tabla.get(i)).remove(index);
        }
        sizeColumn--;
    }
    //Obtiene una lista que representa una fila de la tabla en el indice indicado
    @Override
    public ListaAleatoria getRow(int indexRow) {
        if (indexRow < 0 || indexRow > (sizeRow - 1)) {
            throw new IllegalArgumentException("getRow: indexRow<0 || indexRow>(sizeRow-1)");
        }
        return (ListaAleatoria) tabla.get(indexRow);
    }
    //Obtiene un valor de una celda ubicada en los indices de fila y columna indicados
    @Override
    public Object getValueAt(int indexRow, int indexColumn) {
        if (indexRow < 0 || indexRow > (sizeRow - 1)) {
            throw new IllegalArgumentException("getValueAt: indexRow<0 || indexRow>(sizeRow-1)");
        } else if (indexColumn < 0 || indexColumn > (sizeColumn - 1)) {
            throw new IllegalArgumentException("getValueAt: indexColumn<0 || indexColumn>(sizeColumn-1)");
        }
        return ((ListaAleatoria) tabla.get(indexRow)).get(indexColumn);
    }
    //Modifica el valor de una celda ubicada en los indices de fila y columna indicados
    @Override
    public void setValueAt(int indexRow, int indexColumn, Object o) {
        if (indexRow < 0 || indexRow > (sizeRow - 1)) {
            throw new IllegalArgumentException("setValueAt: indexRow<0 || indexRow>(sizeRow-1)");
        } else if (indexColumn < 0 || indexColumn > (sizeColumn - 1)) {
            throw new IllegalArgumentException("setValueAt: indexColumn<0 || indexColumn>(sizeColumn-1)");
        }
        ((ListaAleatoria) tabla.get(indexRow)).remove(indexColumn);
        ((ListaAleatoria) tabla.get(indexRow)).add(indexColumn, o);
    }
    //Retorna la cantidad de filas de la tabla
    @Override
    public int getRowCount() {
        return sizeRow;
    }
    //Retorna la cantidad de columnas de la tabla
    @Override
    public int getColumnCount() {
        return sizeColumn;
    }
    //Verifica si la tabla esta vacia
    @Override
    public boolean isEmpty() {
        return tabla.isEmpty();
    }
    
    @Override
    public String toString() {
        String mensaje = "";
        for (int i = 0; i < tabla.length(); i++) {
            mensaje += tabla.get(i) + "\n";
        }
        return mensaje;
    }
    
}
