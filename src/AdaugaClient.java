import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaugaClient extends JFrame{
    private JTextField clientIDTextfield;
    private JTextField numeTextfield;
    private JTextField prenumeTextfield;
    private JTextField adresaTextfield;
    private JTextField nrTelefonTextfield;
    private JTextField emailTextfield;
    private JTextField CNPTextfield;
    private JTextField dataNastereTextfield;
    private JButton adaugaButton;
    private JPanel panel1;
    private JTable table1;

    AdaugaClient(){
        setTitle("Adauga Client");
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
                String query = "INSERT INTO Clienti (ClientID, Nume, Prenume, Adresa, NrTelefon, Email, CNP, DataNastere) Values (?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conectare.prepareStatement(query);
                System.out.println("Maluma");
                int ClientID = Integer.valueOf(AdaugaClient.this.clientIDTextfield.getText());

                String nume  = numeTextfield.getText();
                String prenume = prenumeTextfield.getText();
                String adresa = adresaTextfield.getText();
                String nrTelefon = nrTelefonTextfield.getText();
                String email = emailTextfield.getText();
                String CNP = CNPTextfield.getText();
                String data = dataNastereTextfield.getText();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date data1 = formatter.parse(data);
                java.sql.Date sqlData = new java.sql.Date(data1.getTime());




                statement.setInt(1, ClientID);
                statement.setString(2, nume);
                statement.setString(3, prenume);
                statement.setString(4, adresa);
                statement.setString(5, nrTelefon);
                statement.setString(6, email);
                statement.setString(7, CNP);
                statement.setDate(8, sqlData);

                    statement.execute();

                    String query2 = " select Nume, Prenume, NrTelefon from Clienti";
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
                    String Prenum;
                    String NrTelefo;

                    while (resultSet.next()) {
                        Num = resultSet.getString(1);
                        Prenum = resultSet.getString(2);
                        NrTelefo = resultSet.getString(3);
                        String[] row = {Num, Prenum, NrTelefo};
                        model.addRow(row);}

                } catch (SQLException | ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }

});
}
}