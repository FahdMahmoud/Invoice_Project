/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlls;

import Data.Inv_Line_Table_Model;
import Data.Invoice_Header;
import Data.Invoice_Lines;
import Interface.Inv_Frame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author FahdElShaikh
 */
public class Table_Selection_Listener implements ListSelectionListener{

    private Inv_Frame frame ;

    public Table_Selection_Listener(Inv_Frame frame) {
    this.frame = frame;
    }
       
       
       
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectedInvIndex = frame.getInv_Header_Tbl().getSelectedRow();
        System.out.println("Invoice Selected : " + selectedInvIndex );
        if(selectedInvIndex !=-1) {
            
        Invoice_Header selected_invoice = frame.getInvoicesHeadersArray().get(selectedInvIndex);
        ArrayList<Invoice_Lines> lines = selected_invoice.getLines();
        Inv_Line_Table_Model invLineTableModel = new Inv_Line_Table_Model(lines);
        frame.setInvoicesLinessArray(lines);
        frame.getInv_Lines_Tbl().setModel(invLineTableModel);
        frame.getCustNameLBL().setText(selected_invoice.getCus_Name());
        frame.getInvNumLBL().setText(""+selected_invoice.getInv_Number());
        frame.getInvTotalLBL().setText("" + selected_invoice.getInvoice_Total());
        frame.getInvDateLBL().setText(Inv_Frame.DateFormat.format(selected_invoice.getInv_Date()));
        
    
        } 
    }
    
}
