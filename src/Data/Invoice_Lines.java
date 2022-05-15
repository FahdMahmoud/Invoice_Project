/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author FahdElShaikh
 */
public class Invoice_Lines {
    
    private Invoice_Header Header; //int Inv_Number;
    // take an object of invoice header which includes Inv_Number
    private String Item_Name;
    private Double Price;
    private int Item_Count;
    //private double Line_Total;

    public Invoice_Lines() {
    }

    public Invoice_Lines(String Item_Name, Double Price, int Item_Count,Invoice_Header Header ) {
        this.Item_Name = Item_Name;
        this.Price = Price;
        this.Item_Count = Item_Count;
        this.Header = Header;
    }
    
    public Invoice_Header getHeader() {
        return Header;
    }

    public void setHeader(Invoice_Header Header) {
        this.Header = Header;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getItem_Count() {
        return Item_Count;
    }

    public void setItem_Count(int Item_Count) {
        this.Item_Count = Item_Count;
    }
    
    public double getLine_Total(){
        return Price * Item_Count;       // method to calc line total
    }

    @Override
    public String toString() {
        return  Header.getInv_Number() + "," + Item_Name + "," + Price + "," + Item_Count;
    }
    
} 

