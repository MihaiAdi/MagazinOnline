import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateProdus extends JFrame{

    private JButton buttonUpdate;
    private JTextField textFieldCantitate;
    private JPanel panelUpdate;
    public JTable table1;
    private JTextField textFieldNumeProdus;


    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
    String user = "adi";
    String pass = "12345";

    public UpdateProdus() throws SQLException {
    Connection conectare = DriverManager.getConnection(url,user,pass);
    setTitle("Update Produs");
    setContentPane(panelUpdate);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(true);
    setMinimumSize(new Dimension(630, 670));
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "update Produse  set Stoc =" + textFieldCantitate.getText()  + " where Nume = " + "'"+ textFieldNumeProdus.getText() + "'" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    String query3 = "select Nume, Stoc from Produse";
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
    }

    public static void main(String[] args) throws SQLException {
        UpdateProdus updateProdus = new UpdateProdus();
    }
}

