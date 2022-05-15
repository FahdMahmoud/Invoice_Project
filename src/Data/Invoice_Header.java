/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FahdElShaikh
 */
public class Invoice_Header {
    
    private int Inv_Number; 
    private String Cus_Name;
    private Date Inv_Date;
   // private Double Inv_Total;  
    private ArrayList<Invoice_Lines> Lines ;
    // array list of objects to hold invoice lines , each header hols its lines
   private DateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");

   private Inv_Header_Table_Model header_Table_Model;
   private Date Date;
            
    public Invoice_Header() {
    }

    public Invoice_Header(int Inv_Number, String Cus_Name , Date Inv_Date) {
        this.Inv_Number = Inv_Number;
        this.Cus_Name = Cus_Name;
        this.Inv_Date = Inv_Date;
    } 
    
    public int getInv_Number() {
        return Inv_Number;
    }

    public void setInv_Number(int Inv_Number) {
        this.Inv_Number = Inv_Number;
    }

    public String getCus_Name() {
        return Cus_Name;
    }

    public void setCus_Name(String Cus_Name) {
        this.Cus_Name = Cus_Name;
    }

    public void setLines(ArrayList<Invoice_Lines> Lines) {
        this.Lines = Lines;
    }

    public Date getInv_Date() {
        return Inv_Date;
    }

    public void setInv_Date(Date Inv_Date) {
        this.Inv_Date = Inv_Date;
    }
      
    public ArrayList<Invoice_Lines> getLines(){
        if ( Lines == null ){
            Lines = new ArrayList<>();
        }
        return Lines;
    }

    public Inv_Header_Table_Model getHeader_Table_Model() {
        return header_Table_Model;
    }

    public void setHeader_Table_Model(Inv_Header_Table_Model header_Table_Model) {
        this.header_Table_Model = header_Table_Model;
    }

   /* @Override
    public String toString() {
        return "Invoice_Header{" + "Inv_Number=" + Inv_Number + ", Cus_Name=" + Cus_Name + ", Inv_Date=" + Inv_Date + '}';
    } */
    
    
    // method to calc total invoice price
    public double getInvoice_Total(){  
        double Inv_total = 0.0;
        for( int i=0 ; i<getLines().size() ;i++)
        {
            Inv_total += getLines().get(i).getLine_Total();
            
        } return Inv_total;
    }
    
      @Override
        public String toString() {
        return Inv_Number + "," + dformat.format(Inv_Date) + "," + Cus_Name;
    }
    
}
