/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FahdElShaikh
 */
public class Inv_Line_Table_Model extends AbstractTableModel{

    private ArrayList<Invoice_Lines> invoicesLinesArray ;
    private String [] Columns = {"Item_Name" , "Unit_Price" , "Quantity","Line Total" };

    public Inv_Line_Table_Model(ArrayList<Invoice_Lines> invoicesLinesArray) {
        this.invoicesLinesArray = invoicesLinesArray;
    }
       
    @Override
    public int getRowCount() {
        
        return invoicesLinesArray == null ? 0 :invoicesLinesArray.size();
    }

    @Override
    public int getColumnCount() {
        
        return Columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        if(invoicesLinesArray == null ){
            return "";} 
        else {
   
        Invoice_Lines line =  invoicesLinesArray.get(rowIndex);
        switch (columnIndex){
            case 0 : return line.getItem_Name();
            case 1 : return line.getPrice();
            case 2 : return line.getItem_Count();
            case 3 : return line.getLine_Total();
            default: return"";
            }}
    }

    @Override
    public String getColumnName(int column) {
        
        return Columns[column];
    }
    
    
    
}
