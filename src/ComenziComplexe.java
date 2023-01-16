import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ComenziComplexe extends JFrame{
    private JButton a4Button;
    private JPanel panel1;
    private JButton a1Button;
    private JButton a3Button;
    private JButton a2Button;
    private JTable table1;


    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
    String user = "adi";
    String pass = "12345";


    public ComenziComplexe() throws SQLException {
        Connection conectare = DriverManager.getConnection(url,user,pass);
        setTitle("Update Produs");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(630, 670));


        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT C.Nume, C.Prenume\n" +
                        "FROM Clienti C\n" +
                        "ORDER BY (SELECT avg(CO.PretTotal)from Comenzi CO\n" +
                        "     where co.ClientID = C.ClientID  )DESC" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Num1, Num2;

                    while (resultSet.next()) {
                        Num1 = resultSet.getString(1);
                        Num2 = resultSet.getString(2);
                        String[] row = {Num1, Num2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT C.Nume, C.Prenume\n" +
                        "FROM Clienti C, Comenzi CO , ProduseComandate PC , Produse P\n" +
                        "WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID  AND PC.ProdusID = P.ProdusID AND P.DepartamentID IN\n" +
                        "                                     (SELECT DepartamentID FROM Departamente WHERE P.DepartamentID = 10001 )" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Num1, Num2;

                    while (resultSet.next()) {
                        Num1 = resultSet.getString(1);
                        Num2 = resultSet.getString(2);
                        String[] row = {Num1, Num2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT C.Nume, C.Prenume\n" +
                        "FROM Clienti C, Comenzi CO , ProduseComandate PC , Produse P,Departamente d\n" +
                        "WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID  AND PC.ProdusID = P.ProdusID AND p.DepartamentID = d.DepartamentID and D.FurnizorID IN\n" +
                        "                                                                                                 (SELECT FurnizorID FROM Furnizori WHERE d.FurnizorID = 1003)" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Num1, Num2;

                    while (resultSet.next()) {
                        Num1 = resultSet.getString(1);
                        Num2 = resultSet.getString(2);
                        String[] row = {Num1, Num2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT C.Nume, C.Prenume\n" +
                        "FROM Clienti C, Comenzi CO , ProduseComandate PC\n" +
                        "WHERE C.ClientID = CO.ClientID AND CO.ComandaID = PC.ComandaID AND PC.ProdusID IN (SELECT ProdusID FROM Produse WHERE PC.ProdusID = 100003 )" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);

                    String Num1, Num2;

                    while (resultSet.next()) {
                        Num1 = resultSet.getString(1);
                        Num2 = resultSet.getString(2);
                        String[] row = {Num1, Num2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }

    public static void main(String[] args) throws SQLException {
        ComenziComplexe comenziComplexe = new ComenziComplexe();
    }


}
