/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlls;

import Data.Inv_Header_Table_Model;
import Data.Inv_Line_Table_Model;
import Data.Invoice_Header;
import Data.Invoice_Lines;
import Interface.Inv_Frame;
import Interface.Inv_Header_Diag;
import Interface.Inv_Line_Diag;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author FahdElShaikh
 */
public class Inv_Action_Listener implements ActionListener{

    private Inv_Frame frame;                     // variable to be available in this class 
    private Inv_Header_Diag H_Diag ;
    private Inv_Line_Diag L_Diag ; 
    
    public Inv_Action_Listener(Inv_Frame frame){             // constructor to link between Frame & action listener take an object from frame as parameter 
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Load Files":       // from Action command 
                //System.out.println("Load Files");
                Load_Files();
                break;
                
            case "Save Files ":     
                //System.out.println("Save Files");
                Save_Files();
                break;
                
            case "Create New Invoice":
                //System.out.println("Create Invoice");
                Create_New_Inv();
                break;
                
            case "Delete Invoice":
                //System.out.println("Delete Invoice");
                Delete_Inv();
                break;
                
            case "Create New Line":
                //System.out.println("Save Line");
                New_Line();
                break;
                
            case "Delete Line":
                //System.out.println("Cancel Line");
                Delete_Line();
                break;    
                
            case "CreatenewInvOK" : 
                NewInvoiceDialogOK();
                break;
                
            case "CreatenewInvCancel" :
                NewInvoiceDialogCancel(); 
                break;
                
             case "NewLineOK" :
                NewLineDiagOK();
                break;
                
              case "NewLineCancel" :
                NewLineDiagCancel();
                break; 
        }
        
    }

    private void Load_Files()   {
        
        JFileChooser file_chooser = new JFileChooser();        // to open the Dialg 
        try {
        int result = file_chooser.showOpenDialog(frame);      // to open the dialg to choose the file and result will hold the pressed buttion ( open,cancel )
        if (result == JFileChooser.APPROVE_OPTION ){          // if user press on open -- approve )
            File header = file_chooser.getSelectedFile();     // create object of class File and assign the selected file to it 
            Path headerPath = Paths.get(header.getAbsolutePath());   // hold the file path into headerPath object of type Path
            List <String> headerLines = Files.readAllLines(headerPath);
            ArrayList<Invoice_Header> invoiceheaders = new ArrayList<>();  // array of objects
            for (String headerLine : headerLines ){                  // loop for each header line in the herderlines
                String[] HedArr = headerLine.split(",");              // split each line in comma and put the value inside HedArr 
                String Code_str = HedArr[0];                      // allocate each element in the arry to variables
                String Date_str = HedArr[1];
                String Name_str = HedArr[2];
                
                int code = Integer.parseInt(Code_str);           // to convert the stored string value inside array[0] to integer value 
                Date Invoice_Date = Inv_Frame.DateFormat.parse(Date_str);  // convert the received string to date using date format which clarified in DateFormat object
                
                Invoice_Header header_Con = new Invoice_Header(code, Name_str,Invoice_Date);
                invoiceheaders.add(header_Con);
            }
            
                frame.setInvoicesHeadersArray (invoiceheaders);   // read the array invoiceheaders and assign to the frame
                
                result = file_chooser.showOpenDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION){
                   File Line = file_chooser.getSelectedFile();
                   Path linePath = Paths.get(Line.getAbsolutePath());
                   List <String> Arr_lines = Files.readAllLines(linePath);     // contains array list of strings
                   ArrayList<Invoice_Lines> invoiceLines = new ArrayList<>();
                   
                   for ( String Arr_line : Arr_lines ){
                       String [] LinArr = Arr_line.split(",");
                       String Code_str = LinArr[0];                      // allocate each element in the arry to variables invoice ID ( int )
                       String ITname_str = LinArr[1];                    // Item Name ( String )
                       String Price_str = LinArr[2];                     // Price     ( double )
                       String Quantity_str = LinArr[3];                      // quantity  ( int ) 
                       
                       int inv_code = Integer.parseInt(Code_str);
                       double price = Double.parseDouble(Price_str);
                       int quantity = Integer.parseInt(Quantity_str);
                       Invoice_Header Inv = frame.getInvObject(inv_code);
                       Invoice_Lines line_Con = new Invoice_Lines(ITname_str, price, quantity, Inv );
                       Inv.getLines().add(line_Con);
                   }
                   
                } 
                Inv_Header_Table_Model header_Table_Model = new Inv_Header_Table_Model(invoiceheaders);
                frame.getInv_Header_Tbl().setModel(header_Table_Model);
                frame.setHeaderTableModel(header_Table_Model);
        }
    }  catch(IOException ex){
        JOptionPane.showMessageDialog(frame, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }  catch (ParseException ex) {  
        JOptionPane.showMessageDialog(frame, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }  
        
    }

    private void Save_Files() {
        ArrayList<Invoice_Header> invoicesArray = frame.getInvoicesHeadersArray();
       JFileChooser fchoos = new JFileChooser();
        try {
            int result = fchoos.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION && invoicesArray !=null) {
              File headFile = fchoos.getSelectedFile();
              FileWriter headerfw = new FileWriter(headFile);
              String headers = "";
              String lines = "";
              for (Invoice_Header invc :invoicesArray) {
                headers += invc.toString();
                headers += "\n";
                for (Invoice_Lines ln : invc.getLines()) {
                     lines += ln.toString();
                     lines += "\n";
                }
            }
            
            headers = headers.substring(0, headers.length()-1);
            lines = lines.substring(0, lines.length()-1);
            result = fchoos.showSaveDialog(frame);
            File lineFile = fchoos.getSelectedFile();
            FileWriter linefw = new FileWriter(lineFile);
            headerfw.write(headers);
            linefw.write(lines);
            headerfw.close();
            linefw.close();
            }
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void Create_New_Inv() {
        H_Diag = new Inv_Header_Diag(frame);   // Dialog creation
        H_Diag.setVisible(true);

    }

    private void Delete_Inv() {

        int selectedInvIn = frame.getInv_Header_Tbl().getSelectedRow();
        if (selectedInvIn !=-1){
            frame.getInvoicesHeadersArray().remove(selectedInvIn);
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInv_Lines_Tbl().setModel(new Inv_Line_Table_Model(new ArrayList<Invoice_Lines>()));
            frame.setInvoicesLinessArray(null);
            frame.getInv_C_name_Lab().setText("");
            frame.getInv_Num_Lab().setText("");
            frame.getInv__Total_Lab().setText("");
            frame.getInv_Date_Lab().setText("");
        }
    }

    private void New_Line() {
        
            L_Diag = new Inv_Line_Diag(frame);   // Dialog creation
            L_Diag.setVisible(true);
    }

    private void Delete_Line() {

            int selectedLineIn = frame.getInv_Lines_Tbl().getSelectedRow();
            int selectedInvIn = frame.getInv_Header_Tbl().getSelectedRow();
        if (selectedLineIn !=-1){
            frame.getInvoicesLinessArray().remove(selectedLineIn);
            Inv_Line_Table_Model lineTableModel = (Inv_Line_Table_Model) frame.getInv_Lines_Tbl().getModel();
            lineTableModel.fireTableDataChanged();
           // frame.getInv__Total_Lab().setText(""+frame.getInvoicesHeadersArray().get(selectedInvIn).get));
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInv_Header_Tbl().setRowSelectionInterval(selectedInvIn, selectedInvIn);
        }
    }

    private void NewInvoiceDialogOK() {
        H_Diag.setVisible(false);
        String customer_name = H_Diag.getCustNameField().getText();
        String date = H_Diag.getInvDateField().getText();
        Date DA = new Date();
        
        try {
        DA = Inv_Frame.DateFormat.parse(date);
        } catch(ParseException ex) {
                JOptionPane.showMessageDialog(frame, "parse not allowed , returned to Today Date", "Invalid input date", JOptionPane.ERROR_MESSAGE);
        }
        int Inv_num = 0 ;
        for (Invoice_Header invv : frame.getInvoicesHeadersArray()){
            if(invv.getInv_Number()>Inv_num) Inv_num = invv.getInv_Number();
        }
        Inv_num++;
        Invoice_Header inv = new Invoice_Header(Inv_num, customer_name, DA);
        frame.getInvoicesHeadersArray().add(inv);
        frame.getHeaderTableModel().fireTableDataChanged();
        H_Diag.dispose();
        H_Diag = null;

    }

    private void NewInvoiceDialogCancel() {
        H_Diag.setVisible(false);
        H_Diag.dispose();
        H_Diag  = null; 
    }

      private void NewLineDiagOK(){
          
        L_Diag.setVisible(false);
        String name = L_Diag.getItemNameField().getText();
        String str1 = L_Diag.getItemCountField().getText();
        String str2 = L_Diag.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            price = Double.parseDouble(str2);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = frame.getInv_Header_Tbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            Invoice_Header invHeader = frame.getInvoicesHeadersArray().get(selectedInvHeader);
            Invoice_Lines line = new Invoice_Lines(name, price, count, invHeader);
            frame.getInvoicesLinessArray().add(line);
            Inv_Line_Table_Model lineTableModel = (Inv_Line_Table_Model) frame.getInv_Lines_Tbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
        }
        frame.getInv_Header_Tbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        L_Diag.dispose();
        L_Diag = null;
    }
    
    private void NewLineDiagCancel(){
        L_Diag.setVisible(false);
        L_Diag.dispose();
        L_Diag  = null; 
    }
    
    }
