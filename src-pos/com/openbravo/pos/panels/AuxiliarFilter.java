//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2008 Openbravo, S.L.
//    http://sourceforge.net/projects/openbravopos
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin Street, Fifth floor, Boston, MA  02110-1301  USA

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.inventory.AuxiliarEditor;
import com.openbravo.pos.reports.ReportEditorCreator;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionListener;


/**
 *
 * @author jaroslawwozniak
 */
public class AuxiliarFilter extends javax.swing.JPanel implements ReportEditorCreator{

    private ProductInfoExt m_product;
    private DataLogicSales m_dlSales;
    private AuxiliarEditor m_jEditor;

    /** Creates new form AuxiliarFilter */
    public AuxiliarFilter() {
        initComponents();
    }

    @Override
    public void init(AppView app) {   
         m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         m_jReference1.setText("");
         m_product = new ProductInfoExt();
    }

    @Override
    public void activate() throws BasicException {
    }

    @Override
    public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(new Datas[] {Datas.STRING});
    }

    public void addActionListener(ActionListener l){
        m_jReference1.addActionListener(l);      
        Enter1.addActionListener(l);
        Enter2.addActionListener(l);
    }

    @Override
    public Component getComponent() {
        return this;
    }

    public void forwardEditor(AuxiliarEditor editor){
        m_jEditor = editor;
    }

    @Override
    public Object createValue() throws BasicException {
        return new Object[] {
            m_product.getID()
        };
    }

    private void assignProduct(ProductInfoExt prod) {

        if (m_jSearch.isEnabled()) {
            if (prod == null) {
                m_jSearch.setText(null);
                m_jBarcode1.setText(null);
                m_jReference1.setText(null);
            } else {
                m_product = prod;
                m_jSearch.setText(getM_product().toString());
                m_jBarcode1.setText(getM_product().getCode());
                m_jReference1.setText(getM_product().getReference());

            }            
        m_jEditor.refresh();
        }
    }

    private void assignProductByCode() {
        try {
            ProductInfoExt oProduct = m_dlSales.getProductInfoByCode(m_jBarcode1.getText());
            if (oProduct == null) {
                assignProduct(null);
                Toolkit.getDefaultToolkit().beep();
            } else {
               // Se anade directamente una unidad con el precio y todo
                    assignProduct(oProduct);
            }
        } catch (BasicException eData) {
            assignProduct(null);
            MessageInf msg = new MessageInf(eData);
            msg.show(this);
        }
    }

    private void assignProductByReference() {
        try {
            ProductInfoExt oProduct = m_dlSales.getProductInfoByReference(m_jReference1.getText());
            if (oProduct == null) {
                assignProduct(null);
                Toolkit.getDefaultToolkit().beep();
            } else {
                // Se anade directamente una unidad con el precio y todo
                assignProduct(oProduct);
            }
        } catch (BasicException eData) {
            assignProduct(null);
            MessageInf msg = new MessageInf(eData);
            msg.show(this);
        }
    }

    /**
     * @return the m_product
     */
    public ProductInfoExt getM_product() {
        return m_product;
    }

   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        m_jReference1 = new javax.swing.JTextField();
        m_jBarcode1 = new javax.swing.JTextField();
        Enter2 = new javax.swing.JButton();
        search = new javax.swing.JButton();
        Enter1 = new javax.swing.JButton();
        m_jSearch = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("By product"));

        jLabel6.setText(AppLocal.getIntString("label.prodref")); // NOI18N

        jLabel7.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N

        m_jReference1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jReference1ActionPerformed(evt);
            }
        });

        m_jBarcode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jBarcode1ActionPerformed(evt);
            }
        });

        Enter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        Enter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter2ActionPerformed(evt);
            }
        });

        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search.png"))); // NOI18N
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        Enter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        Enter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter1ActionPerformed(evt);
            }
        });

        m_jSearch.setEditable(false);
        m_jSearch.setFocusable(false);
        m_jSearch.setRequestFocusEnabled(false);
        m_jSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                m_jSearchPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(m_jReference1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(m_jBarcode1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                    .addComponent(m_jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Enter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Enter1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(152, 152, 152))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(m_jReference1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Enter1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Enter2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(m_jBarcode1)
                            .addComponent(jLabel7))
                        .addGap(13, 13, 13)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_jReference1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jReference1ActionPerformed
        this.assignProductByReference();
    }//GEN-LAST:event_m_jReference1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        assignProduct(JProductFinder.showMessage(this, m_dlSales));       
}//GEN-LAST:event_searchActionPerformed

    private void Enter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter2ActionPerformed
        this.assignProductByCode();
    }//GEN-LAST:event_Enter2ActionPerformed

    private void Enter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter1ActionPerformed
        this.assignProductByReference();
    }//GEN-LAST:event_Enter1ActionPerformed

    private void m_jBarcode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBarcode1ActionPerformed
        this.assignProductByCode();
    }//GEN-LAST:event_m_jBarcode1ActionPerformed

    private void m_jSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_m_jSearchPropertyChange

    }//GEN-LAST:event_m_jSearchPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enter1;
    private javax.swing.JButton Enter2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField m_jBarcode1;
    private javax.swing.JTextField m_jReference1;
    private javax.swing.JTextField m_jSearch;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables

}
