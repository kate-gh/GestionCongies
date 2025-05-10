
package com;
import btn.TableActionCellEditor;
import btn.TableActionCellRender;
import btn.TableActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
//import net.proteanit.sql.DbUtils;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.MessageFormat;




import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lenovo
 */
public class admin1 extends javax.swing.JFrame {

   
  
   
    
    public admin1() {
        initComponents();
        
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void OnAccept(int row) {

    DefaultTableModel recordtable = (DefaultTableModel) table1.getModel();
    int selectedrow = table1.getSelectedRow();
    
    if (selectedrow == -1) {
        JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer.");
        return;
    }

    String nom = recordtable.getValueAt(selectedrow, 1).toString();
    String prenom = recordtable.getValueAt(selectedrow, 2).toString();
    String email = recordtable.getValueAt(selectedrow, 3).toString();
    String type = recordtable.getValueAt(selectedrow, 4).toString();
    String dd = recordtable.getValueAt(selectedrow, 5).toString();
    String df = recordtable.getValueAt(selectedrow, 6).toString();
    String duree = recordtable.getValueAt(selectedrow, 7).toString();
    

    // Configuration SMTP
    Properties p = new Properties();
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");

    // Authentification SMTP
    Session session = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            // mot de passe générer pa l'email
            return new PasswordAuthentication("leavesystem10@gmail.com", "johr lptn otcm wioh");
        }
    });

    try {
        // Création du message
        Message message = new MimeMessage(session);
        
        message.setFrom(new InternetAddress("leavesystem10@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Confirmation de la demande de congé acceptée");
        message.setText(
"Bonjour "+nom+" "+prenom+",\n" +
"\n" +
"Nous sommes heureux de vous informer que votre demande de congé a été bien acceptée. Veuillez trouver ci-dessous les détails de votre congé :\n" +
"\n" +
"- Date de début du congé : "+dd+"\n" +
"- Date de fin du congé : "+df+"\n" +
"- Type de congé : "+type+"\n" +
"- Durée du congé : "+duree+"\n"+
"\n" +
"Votre congé a été approuvé et est désormais enregistré dans notre système. Si vous avez des questions supplémentaires ou si vous avez besoin de plus d'informations, n'hésitez pas à nous contacter.\n" +
"\n" +
"Merci,\n" +
"\n" +
"LeaveSystem");
        

        // Envoi du message
        Transport.send(message);
        JOptionPane.showMessageDialog(null, "Email envoyé");

        // Suppression de la ligne sélectionnée
        recordtable.removeRow(selectedrow);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Echec de l'envoi de l'email");
        throw new RuntimeException(e);
    }
}
            
            
           /* public void OnAccept(int row) {
                DefaultTableModel recordtable = (DefaultTableModel)table1.getModel();
            int selectedrow= table1.getSelectedRow();
            String email=recordtable.getValueAt(selectedrow, 3).toString();
                
                
                //Simple Mail Transfer Protocol
                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                //le port standard pour les connexions SMTP sécurisées avec STARTTLS
                p.put("mail.smtp.port", "587");
                
                
                Session session = Session.getDefaultInstance(p,new javax.mail.Authenticator(){
                protected PasswordAuthentication  getPasswordAuthentification(){
                return new PasswordAuthentication("nada.xyz123@gmail.com","nada-123");

        }
                });
        try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("nada.xyz123@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));//
        message.setSubject("Réponse de demande");
        message.setText("Votre demande de congé a été accepté");
        Transport.send(message);
        JOptionPane.showMessageDialog(null, "Email envoyé");
        
 
 }catch (Exception e)   {
    System.out.println(e.getMessage());
    JOptionPane.showMessageDialog(null, "Echec");
    throw new RuntimeException(e);
}           
               
                
}*/

            @Override
            public void OnRejet(int row) {
               
                int dialoqueBtn = JOptionPane.YES_NO_OPTION;
        int dialoqueRes = JOptionPane.showConfirmDialog(null,"êtes-vous sûre de refuser la demande?","Rejeter La Demande",dialoqueBtn);
        
        if(dialoqueRes==0){
        
                         try {

            DefaultTableModel recordtable = (DefaultTableModel)table1.getModel();
            int selectedrow= table1.getSelectedRow();
            String id=recordtable.getValueAt(selectedrow, 0).toString();

            int vid = Integer.parseInt(id);

            

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
            String req="delete from demandeconges where id=?";
            PreparedStatement st = con.prepareStatement(req);

            st.setInt(1, vid);
            st.executeUpdate();
            
            updatedb();//refresh the table
            recordtable.removeRow(row); //supprimer la ligne

        } catch (Exception e) {
            System.err.println( e.getMessage());
        }
        }else{}
                
               

               
               
            }
        };
         table1.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
        
        
        
     
    }
    
    
    public void updatedb(){
        try {   
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
          String req="SELECT * FROM  gestionemploye";
          PreparedStatement st = con.prepareStatement(req);
          ResultSet res = st.executeQuery();
         
          ResultSetMetaData stdata = res.getMetaData();
          
          int q = stdata.getColumnCount();
          DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
          recordtable.setRowCount(0);  // Clear existing data first
         
          while(res.next()){
          Vector columndata = new Vector();
     
            for(int i=1;i<=q;i++ ){
             columndata.add(res.getString("id"));
              columndata.add(res.getString("nom"));
              columndata.add(res.getString("prenom"));
              columndata.add(res.getString("email"));
              columndata.add(res.getFloat("acquis"));
             
              columndata.add(res.getFloat("pris"));
              
              columndata.add(res.getFloat("solde"));
            }
          
          recordtable.addRow(columndata);
          }
          
    } catch (Exception e) {
        System.err.println( e.getMessage());
    }
    }
    
    
     public void updatedb1(){
        try {   
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
         String req="SELECT gestionemploye.id ,gestionemploye.nom, gestionemploye.prenom,"
                  + "gestionemploye.email,demandeconges.type,demandeconges.dd, demandeconges.df,demandeconges.duree "
                  + "FROM  gestionemploye join demandeconges ON  demandeconges.id=gestionemploye.id"; 
          PreparedStatement st = con.prepareStatement(req);
          ResultSet res = st.executeQuery();
         
          ResultSetMetaData stdata = res.getMetaData();
          
          int q = stdata.getColumnCount();
          DefaultTableModel recordtable = (DefaultTableModel)table1.getModel();
         recordtable.setRowCount(0);  // Clear existing data first
         
          while(res.next()){
          Vector columndata = new Vector();
    
            for(int i=1;i<=q;i++ ){
             columndata.add(res.getString("id"));
              columndata.add(res.getString("nom"));
              columndata.add(res.getString("prenom"));
              columndata.add(res.getString("email"));
              columndata.add(res.getString("type"));
              columndata.add(res.getDate("dd"));
               columndata.add(res.getDate("df"));
              columndata.add(res.getFloat("duree"));
               
               
            }
         
          recordtable.addRow(columndata);
          }
          
    } catch (Exception e) {
        System.err.println( e.getMessage());
    }
     }
    

     
     


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        gestionemploye = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        acquis = new javax.swing.JTextField();
        pris = new javax.swing.JTextField();
        ajouter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        prenom = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        imprimer = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        recherche = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        demande = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(198, 177, 219));

        jPanel2.setBackground(new java.awt.Color(198, 177, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel2.setPreferredSize(new java.awt.Dimension(177, 438));

        jButton1.setBackground(new java.awt.Color(225, 215, 233));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Gestion Employé");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(225, 215, 233));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Gestion Des Demandes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(225, 215, 233));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Déconnexion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sizelogo2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(198, 177, 219));
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        mainPanel.setPreferredSize(new java.awt.Dimension(642, 434));
        mainPanel.setLayout(new java.awt.CardLayout());

        gestionemploye.setBackground(new java.awt.Color(198, 177, 219));
        gestionemploye.setPreferredSize(new java.awt.Dimension(642, 430));
        gestionemploye.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Gestion Employé");
        gestionemploye.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prenom", "Email", "Acquis", "Pris", "Solde"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        gestionemploye.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 479, 86));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Acquis");
        gestionemploye.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 60, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pris");
        gestionemploye.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, 30));

        acquis.setBackground(new java.awt.Color(198, 177, 219));
        acquis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acquis.setBorder(null);
        acquis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquisActionPerformed(evt);
            }
        });
        gestionemploye.add(acquis, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 175, 20));

        pris.setBackground(new java.awt.Color(198, 177, 219));
        pris.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pris.setBorder(null);
        pris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prisActionPerformed(evt);
            }
        });
        gestionemploye.add(pris, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 175, 20));

        ajouter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        ajouter.setText("Ajouter");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });
        gestionemploye.add(ajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 120, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nom");
        gestionemploye.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        nom.setBackground(new java.awt.Color(198, 177, 219));
        nom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nom.setBorder(null);
        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });
        gestionemploye.add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 180, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Prénom");
        gestionemploye.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, 30));

        prenom.setBackground(new java.awt.Color(198, 177, 219));
        prenom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prenom.setBorder(null);
        prenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomActionPerformed(evt);
            }
        });
        gestionemploye.add(prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 170, 20));

        email.setBackground(new java.awt.Color(198, 177, 219));
        email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email.setBorder(null);
        gestionemploye.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 175, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        gestionemploye.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, 20));

        update.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-24.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        gestionemploye.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 120, -1));

        delete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-24.png"))); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        gestionemploye.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 120, -1));

        imprimer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        imprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-print-24.png"))); // NOI18N
        imprimer.setText("Imprimer");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });
        gestionemploye.add(imprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, 120, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("_____________________________________");
        gestionemploye.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 190, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("X");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        gestionemploye.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("_____________________________________");
        gestionemploye.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 180, 50));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("_____________________________________");
        gestionemploye.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 180, 50));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("_____________________________________");
        gestionemploye.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 180, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N
        gestionemploye.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, -1, -1));

        recherche.setBackground(new java.awt.Color(198, 177, 219));
        recherche.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });
        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });
        gestionemploye.add(recherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 180, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Rechercher");
        gestionemploye.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("_____________________________________");
        gestionemploye.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 190, 30));

        mainPanel.add(gestionemploye, "card3");

        demande.setBackground(new java.awt.Color(198, 177, 219));
        demande.setMaximumSize(new java.awt.Dimension(642, 434));
        demande.setPreferredSize(new java.awt.Dimension(642, 434));
        demande.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(198, 177, 219));
        jPanel5.setPreferredSize(new java.awt.Dimension(505, 300));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        demande.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 321, 567, 69));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prénom", "Email", "Type", "Date Début", "Date Fin", "Durée", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setRowHeight(40);
        table1.setSelectionBackground(new java.awt.Color(204, 204, 255));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table1);

        demande.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 170, 590, 90));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        demande.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 27, -1));

        jLabel18.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Les Demandes Des Employés");
        demande.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-form-100.png"))); // NOI18N
        demande.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        mainPanel.add(demande, "card2");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 580, 210));

        mainPanel.add(jPanel3, "card4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 850, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       mainPanel.removeAll() ;
       mainPanel.repaint();
       mainPanel.revalidate();
       
       mainPanel.add(demande);
       mainPanel.repaint();
       mainPanel.revalidate();
        //updatedb1();//refresh the table
      // updatedb();//refresh the table
       try {   
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
         Statement st = con.createStatement();
          String req="SELECT gestionemploye.id ,gestionemploye.nom, gestionemploye.prenom,"
                  + "gestionemploye.email,demandeconges.type,demandeconges.dd, demandeconges.df,demandeconges.duree "
                  + "FROM  gestionemploye join demandeconges ON  demandeconges.id=gestionemploye.id"; 
          ResultSet rs = st.executeQuery(req);
     DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
          recordtable.setRowCount(0);  // Clear existing data first
         
            while(rs.next()){
                String id=String.valueOf(rs.getInt("id"));
                String nom =rs.getString("nom");
                String prenom =rs.getString("prenom");
                String email=rs.getString("email");
               // String acquis=String.valueOf(rs.getFloat("acquis"));
               // String pris=String.valueOf(rs.getFloat("prix"));
               // String solde=String.valueOf(rs.getFloat("solde"));
                String t[]= {id,nom,prenom,email};
                DefaultTableModel tmodel =(DefaultTableModel)table.getModel();
                tmodel.addRow(t);
                updatedb1();
}

    } catch (Exception e) {
        System.err.println( e.getMessage());
    } 
        
        
        
       
       
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainPanel.removeAll() ;
       mainPanel.repaint();
       mainPanel.revalidate();
       
       mainPanel.add(gestionemploye);
       mainPanel.repaint();
       mainPanel.revalidate();
       updatedb();//refresh the table
       
       
     
    /*try {   
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
         Statement st = con.createStatement();
          String req="SELECT * FROM gestionemploye ;";
          ResultSet rs = st.executeQuery(req);
     DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
          recordtable.setRowCount(0);  // Clear existing data first
         
            while(rs.next()){
                String id=String.valueOf(rs.getInt("id"));
                String nom =rs.getString("nom");
                String prenom =rs.getString("prenom");
                String email=rs.getString("email");
                String acquis=String.valueOf(rs.getFloat("acquis"));
                String pris=String.valueOf(rs.getFloat("prix"));
                String solde=String.valueOf(rs.getFloat("solde"));
                String t[]= {id,nom,prenom,email,acquis,pris,solde};
                DefaultTableModel tmodel =(DefaultTableModel)table.getModel();
                tmodel.addRow(t);

}

    } catch (Exception e) {
        System.err.println( e.getMessage());
    } 
    */
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int dialoqueBtn = JOptionPane.YES_NO_OPTION;
        int dialoqueRes = JOptionPane.showConfirmDialog(this,"êtes-vous sûre de se déconnecter?","se déconnecter",dialoqueBtn);
        
        if(dialoqueRes==0){
        
         this.setVisible(false);
                        WelcomePage adm = new WelcomePage();
                        adm.setVisible(true);
                        this.dispose();
        }else{}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed

        MessageFormat header = new MessageFormat("Listes des employés");
        MessageFormat footer = new MessageFormat("Page {0, number, integer}");

        try{
            table.print(JTable.PrintMode.NORMAL,header,footer);

        }catch(java.awt.print.PrinterException e){
            System.err.format("No Printer Found", e.getMessage());
        }
    }//GEN-LAST:event_imprimerActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

        try {

            DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
            int selectedrow= table.getSelectedRow();
            String id=recordtable.getValueAt(selectedrow, 0).toString();

            int vid = Integer.parseInt(id);

            String vnom = nom.getText();
            String vprenom = prenom.getText();
            String vemail = email.getText();
            float vacquis=Float.parseFloat(acquis.getText());
            float vpris=Float.parseFloat(pris.getText());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
            String req="delete from gestionemploye where id=?";
            PreparedStatement st = con.prepareStatement(req);

            st.setInt(1, vid);
            st.executeUpdate();
            JOptionPane.showMessageDialog(this,"Suppression avec succés");
            updatedb();//refresh the table

            nom.setText("");
            nom.requestFocus();
            prenom.setText("");
            email.setText("");
            acquis.setText("");
            pris.setText("");

        } catch (Exception e) {
            System.err.println( e.getMessage());
        }

    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try {

            DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
            int selectedrow= table.getSelectedRow();
            String id=recordtable.getValueAt(selectedrow, 0).toString();

            int vid = Integer.parseInt(id);
            // vid = recordtable.getValueAt(selectedrow, 0).toString();
            //already rempli
            String vnom = nom.getText();
            String vprenom = prenom.getText();
            String vemail = email.getText();
            float vacquis=Float.parseFloat(acquis.getText());
            float vpris=Float.parseFloat(pris.getText());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
            String req="update gestionemploye set nom=?, prenom=?, email=?, acquis=?, pris=?, solde=? where id=?";
            PreparedStatement st = con.prepareStatement(req);

            st.setString(1, vnom);
            st.setString(2, vprenom);
            st.setString(3, vemail);
            st.setFloat(4,vacquis );
            st.setFloat(5,vpris );
            st.setFloat(6,vacquis - vpris );
            st.setInt(7, vid);

            st.executeUpdate();
            JOptionPane.showMessageDialog(this,"modifier avec succés");
            updatedb();//refresh the table

        } catch (Exception e) {
            System.err.println( e.getMessage());
        }

    }//GEN-LAST:event_updateActionPerformed

    private void prenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prenomActionPerformed

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        try {
            //     DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
            //int selectedrow= table.getSelectedRow();

            String vid;
            // vid = recordtable.getValueAt(selectedrow, 0).toString();
            String vnom = nom.getText();
            String vprenom = prenom.getText();
            String vemail = email.getText();
            float vacquis=Float.parseFloat(acquis.getText());
            float vpris=Float.parseFloat(pris.getText());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncongies", "root", "");
            String req="insert into gestionemploye(nom,prenom,email,acquis,pris,solde) values ( ?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(req);

            st.setString(1, vnom);
            st.setString(2, vprenom);
            st.setString(3, vemail);
            st.setFloat(4,vacquis );
            st.setFloat(5,vpris );
            st.setFloat(6,vacquis - vpris );

            st.executeUpdate();
            JOptionPane.showMessageDialog(this,"Ajout avec succés");
            // updatedb();//refresh the table

        }catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,"Erreur : Cet e-mail existe déjà dans la base de données.");

        } catch (Exception e) {
            System.err.println( e.getMessage());
        }
    }//GEN-LAST:event_ajouterActionPerformed

    private void prisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prisActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel recordtable = (DefaultTableModel)table.getModel();
        int selectedrow= table.getSelectedRow();
//remplir les champs
        nom.setText(recordtable.getValueAt(selectedrow, 1).toString());
        prenom.setText(recordtable.getValueAt(selectedrow, 2).toString());
        email.setText(recordtable.getValueAt(selectedrow, 3).toString());
        acquis.setText(recordtable.getValueAt(selectedrow, 4).toString());
        pris.setText(recordtable.getValueAt(selectedrow, 5).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        DefaultTableModel recordtable = (DefaultTableModel)table1.getModel();
    }//GEN-LAST:event_table1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         for (double i = 0.0; i <= 1.0; i=i+0.1) {
            String val = i+"";
            float f=Float.valueOf(val);
            this.setOpacity(f);
            try {
                Thread.sleep(40);
                
            } catch (Exception e) {
            }
 
        }
    }//GEN-LAST:event_formWindowOpened

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void acquisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acquisActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheActionPerformed

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
       DefaultTableModel obj = (DefaultTableModel)table.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        table.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(recherche.getText()));
        
    }//GEN-LAST:event_rechercheKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField acquis;
    private javax.swing.JButton ajouter;
    private javax.swing.JButton delete;
    private javax.swing.JPanel demande;
    private javax.swing.JTextField email;
    private javax.swing.JPanel gestionemploye;
    private javax.swing.JButton imprimer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField prenom;
    private javax.swing.JTextField pris;
    private javax.swing.JTextField recherche;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
