import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdaugaProdus extends JFrame {
    private JTextField textProdusID;
    private JTextField textNume;
    private JTextField textProducator;
    private JTextField textPret;
    private JTextField textFCantitate;
    private JTextField textDescriere;
    private JTextField textDepartamentID;
    private JButton adaugaButton;
    private JPanel panel1;
    private JTable table1;
    private JComboBox comboBox1;

    AdaugaProdus(){
    setTitle("Adauga Produs");
    setContentPane(panel1);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(true);
    setMinimumSize(new Dimension(630, 670));

        adaugaButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
                String user = "adi";
                String pass = "12345";
                Connection conectare = DriverManager.getConnection(url,user,pass);
                String query = "INSERT INTO Produse (ProdusID, Nume, Producator, Pret, Stoc, Descriere, DepartamentID) Values (?,?,?,?,?,?,?)";
                PreparedStatement statement = conectare.prepareStatement(query);
                int ProdusID = Integer.valueOf(AdaugaProdus.this.textProdusID.getText());

                String Nume = textNume.getText();
                String Producator = textProducator.getText();
                Float Pret = Float.parseFloat(textPret.getText());
                int Cantitate = Integer.valueOf(textFCantitate.getText());
                String Descriere = textDescriere.getText();
                String DepartamentID = (String) comboBox1.getItemAt(comboBox1.getSelectedIndex());





                statement.setInt(1, ProdusID);
                statement.setString(2, Nume);
                statement.setString(3, Producator);
                statement.setFloat(4, Pret);
                statement.setInt(5, Cantitate);
                statement.setString(6, Descriere);
                statement.setString(7, DepartamentID);

                statement.execute();

                String query2 = "select Nume, Producator, Pret, Stoc, DepartamentID from Produse";
                PreparedStatement statement1 = conectare.prepareStatement(query2);
                ResultSet resultSet = statement1.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(1);
                int cols = resultSetMetaData.getColumnCount();
                String[] colName = new String[cols];



                for (int i = 0; i < cols; i++) {
                    colName[i] = resultSetMetaData.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(colName);

                String Num;
                String Prod;
                float Pre = 0;
                String p = String.valueOf(Pre);
                int Cantitat = 0;
                String cant = String.valueOf(Cantitat);
                String depID;
                while (resultSet.next()) {
                    Num = resultSet.getString(1);
                    Prod = resultSet.getString(2);
                    p = resultSet.getString(3);
                    cant = resultSet.getString(4);
                    depID = resultSet.getString(5);
                    String[] row = {Num, Prod,p, cant, depID};
                    model.addRow(row);
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    });

    }
}
