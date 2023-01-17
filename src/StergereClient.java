package StergereClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StergereClient extends JFrame{
    private JTextField textStergereIDClient;
    private JButton stergeClientButton;
    private JTable table12;
    private JPanel panelClient;

    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
    String user = "adi";
    String pass = "12345";
    Connection conectare = DriverManager.getConnection(url,user,pass);


    public StergereClient() throws SQLException {
        setTitle("Sterge Client");
        setContentPane(panelClient);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(630, 670));

        stergeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "DELETE FROM Clienti WHERE ClientID = " + textStergereIDClient.getText() ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    statement.execute();

                    String query3 = "select Nume, Prenume, NrTelefon from Clienti";
                    PreparedStatement statement2 = conectare.prepareStatement(query3);
                    ResultSet resultSet = statement2.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table12.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Nume1;
                    String Prenume1;
                    String NrTelefon1;


                    while (resultSet.next()) {
                        Nume1 = resultSet.getString(1);
                        Prenume1 = resultSet.getString(2);
                        NrTelefon1= resultSet.getString(3);

                        String[] row = {Nume1, Prenume1, NrTelefon1};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        StergereClient sterg = new StergereClient();
    }
    }

