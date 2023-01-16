import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StergereProdus extends JFrame{
    private JTextField textStergereIDProdus;
    private JButton stergeProdusButton;
    private JTable table1;
    private JPanel panelProdus;

    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
    String user = "adi";
    String pass = "12345";
    Connection conectare = DriverManager.getConnection(url,user,pass);


    public StergereProdus() throws SQLException {
        setTitle("Sterge Produs");
        setContentPane(panelProdus);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(630, 670));

        stergeProdusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "DELETE FROM Produse WHERE ProdusID = " + textStergereIDProdus.getText() ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    statement.execute();

                    String query3 = "select Nume, Producator, Pret, Cantitate from Produse";
                    PreparedStatement statement2 = conectare.prepareStatement(query3);
                    ResultSet resultSet = statement2.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Nu;
                    String Pro;
                    float Pr = 0;
                    String p = String.valueOf(Pr);
                    int Cantita = 0;
                    String can = String.valueOf(Cantita);

                    while (resultSet.next()) {
                        Nu = resultSet.getString(1);
                        Pro = resultSet.getString(2);
                        p = resultSet.getString(3);
                        can = resultSet.getString(4);
                        String[] row = {Nu, Pro, p, can};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        StergereProdus sterg = new StergereProdus();
    }
    }

