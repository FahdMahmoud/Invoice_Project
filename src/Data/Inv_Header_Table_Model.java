
package Data;

import Interface.Inv_Frame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FahdElShaikh
 */

public class Inv_Header_Table_Model extends AbstractTableModel{
    
    private ArrayList<Invoice_Header> invoicesHeadersArray ;
    
    private String [] Columns = {"Invoice_Num" , "Invoice_Date" , "Customer_Name" , "Ivoice_Total"};

    public Inv_Header_Table_Model(ArrayList<Invoice_Header> invoicesHeadersArray) {
        this.invoicesHeadersArray = invoicesHeadersArray;
    }


    @Override
    public int getRowCount() {                // to drow the table rows  
        
        return invoicesHeadersArray.size() ;
    }

    @Override
    public int getColumnCount() {              // to drow the table column   

        return Columns.length ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
         Invoice_Header inv = invoicesHeadersArray.get(rowIndex);
        switch (columnIndex ) {
            case 0 : return inv.getInv_Number();
            case 1 : return Inv_Frame.DateFormat.format(inv.getInv_Date());
            case 2 : return inv.getCus_Name();
            case 3 : return inv.getInvoice_Total();
        } 
        return "";
    }

    @Override
    public String getColumnName(int column) {
        
        return Columns [column];
    }
    
}
