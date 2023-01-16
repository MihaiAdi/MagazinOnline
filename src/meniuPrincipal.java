import StergereClient.StergereClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class meniuPrincipal extends JFrame{
    private JPanel panel1;
    private JButton adaugaAngajatButton;
    private JButton stergeClientButton;
    private JButton updateClientButton;
    private JButton comenziJoinButton;
    private JButton comenziComplexeButton;
    private JButton adaugaProdusButton;
    private JButton stergeProdusButton;
    private JButton updateProdusButton;


    public meniuPrincipal(){
        setTitle("Meniu");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(630, 670));
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
        String user = "adi";
        String pass = "12345";
        adaugaAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    AdaugaClient adaugaClient = new AdaugaClient();
            }

        });
        adaugaProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdaugaProdus adaugaProdus = new AdaugaProdus();
            }
        });


        stergeProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StergereProdus stergereProdus = new StergereProdus();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        stergeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StergereClient stergereClient = new StergereClient();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        updateProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
                    String user = "adi";
                    String pass = "12345";
                    Connection conectare = DriverManager.getConnection(url,user,pass);

                    UpdateProdus updateProdus = new UpdateProdus();

                    String query3 = "select Nume, Stoc from Produse";
                    PreparedStatement statement2 = conectare.prepareStatement(query3);
                    ResultSet resultSet = statement2.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) updateProdus.table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Nu;
                    int Cantita = 0;
                    String can = String.valueOf(Cantita);

                    while (resultSet.next()) {
                        Nu = resultSet.getString(1);
                        can = resultSet.getString(2);
                        String[] row = {Nu, can};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });






        updateClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
                    String user = "adi";
                    String pass = "12345";
                    Connection conectare = DriverManager.getConnection(url,user,pass);

                    UpdateClient updateClient = new UpdateClient();

                    String query3 = "select Nume, Prenume, NrTelefon from Clienti";
                    PreparedStatement statement2 = conectare.prepareStatement(query3);
                    ResultSet resultSet = statement2.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) updateClient.table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Nu, nrTel;
                    int Cantita = 0;
                    String can = String.valueOf(Cantita);

                    while (resultSet.next()) {
                        Nu = resultSet.getString(1);
                        can = resultSet.getString(2);
                        nrTel = resultSet.getString(3);
                        String[] row = {Nu, can, nrTel};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });




        comenziJoinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComenziJoin comenziJoin = new ComenziJoin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        comenziComplexeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComenziComplexe comenziComplexe = new ComenziComplexe();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



    }


}
