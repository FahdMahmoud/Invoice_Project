/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class Inv_Line_Diag extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public Inv_Line_Diag(Inv_Frame frame) {
        itemNameField = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");
        itemCountField = new JTextField(20);
        itemCountLbl = new JLabel("Item Count");
        itemPriceField = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        okBtn.setActionCommand("NewLineOK");
        cancelBtn.setActionCommand("NewLineCancel");
        
        okBtn.addActionListener(frame.getAction_Listener());
        cancelBtn.addActionListener(frame.getAction_Listener());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLbl);
        add(itemNameField);
        add(itemCountLbl);
        add(itemCountField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
