import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ComenziJoin extends JFrame{
    private JTable table1;
    private JButton a6Button;
    private JButton furnizorProduseButton;
    private JButton a4Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a5Button;
    private JPanel panelJoin;

    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
    String user = "adi";
    String pass = "12345";
    public ComenziJoin() throws SQLException {
        Connection conectare = DriverManager.getConnection(url,user,pass);
        setTitle("Update Produs");
        setContentPane(panelJoin);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(630, 670));
        furnizorProduseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT p.Nume,F.NumeFurnizor\n" +
                        "from Produse p inner join Departamente d  on p.DepartamentID = d.DepartamentID\n" +
                        "    inner join Furnizori F on d.FurnizorID = F.FurnizorID" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
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
                String query = "SELECT c.Nume,SUM(CO.PretTotal) AS TOTAL FROM Comenzi CO INNER JOIN Clienti C on C.ClientID = Co.ClientID\n" +
                        "group by c.Nume order by SUM(CO.PretTotal) DESC" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
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
                String query = "SELECT CO.ComandaID,SUM(PC.Cantitate) AS NrProduseComandate FROM Comenzi CO INNER JOIN ProduseComandate PC on CO.ComandaID = PC.ComandaID\n" +
                        "group by CO.ComandaID" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);
                    int num1=0, num2 = 0;
                    String n1 = String.valueOf(num1);
                    String n2 = String.valueOf(num2);


                    while (resultSet.next()) {
                        n1 = resultSet.getString(1);
                        n2 = resultSet.getString(2);
                        String[] row = {n1, n2};
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
                String query = "SELECT F.NumeFurnizor, F.CategorieFurnizor, d.NumeDepartament FROM Departamente D inner join Furnizori F on F.FurnizorID = D.FurnizorID" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);
                    int num1=0, num2 = 0, num3 = 0;
                    String n1 = String.valueOf(num1);
                    String n2 = String.valueOf(num2);
                    String n3 = String.valueOf(num3);

                    while (resultSet.next()) {
                        n1 = resultSet.getString(1);
                        n2 = resultSet.getString(2);
                        n3 = resultSet.getString(3);
                        String[] row = {n1, n2, n3};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT d.NumeDepartament  , sum(p.Stoc) as Stoc FROM Departamente d inner join Produse P on d.DepartamentID = P.DepartamentID\n" +
                        "group by d.NumeDepartament order by sum(p.Stoc) desc" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);
                    int num1=0, num2 = 0;
                    String n1 = String.valueOf(num1);
                    String n2 = String.valueOf(num2);


                    while (resultSet.next()) {
                        n1 = resultSet.getString(1);
                        n2 = resultSet.getString(2);
                        String[] row = {n1, n2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT c.Nume, count(C2.ComandaID) AS 'NrComenzi' FROM Clienti C inner join Comenzi C2 on C.ClientID = C2.ClientID\n" +
                        "group by c.Nume order by count(C2.ComandaID) desc" ;
                try {
                    PreparedStatement statement = conectare.prepareStatement(query);
                    statement.execute();

                    ResultSet resultSet = statement.executeQuery();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(1);
                    int cols = resultSetMetaData.getColumnCount();
                    String[] colName = new String[cols];

                    for (int i = 0; i < cols; i++) {
                        colName[i] = resultSetMetaData.getColumnName(i + 1);
                    }
                    model.setColumnIdentifiers(colName);
                    int num1=0, num2 = 0;
                    String n1 = String.valueOf(num1);
                    String n2 = String.valueOf(num2);


                    while (resultSet.next()) {
                        n1 = resultSet.getString(1);
                        n2 = resultSet.getString(2);
                        String[] row = {n1, n2};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });






    }

    public static void main(String[] args) throws SQLException {
        ComenziJoin comenziJoin = new ComenziJoin();
    }
}


