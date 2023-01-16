import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logingui extends JFrame{

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel Login;

    public logingui() {
        setTitle("Login");
        setContentPane(Login);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(630, 670));


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField1.getText();
                String password = passwordField1.getText();

                if ((user.equalsIgnoreCase("admin") && password.equals("admin"))||(user.equalsIgnoreCase("test") && password.equals("test"))) {
                    System.out.println("Conectarea a reusit");
                   // JOptionPane.showMessageDialog(null, "E ce trebuie...");
                    meniuPrincipal men = new meniuPrincipal();
                    setVisible(false);
                } else {
                   // success.setText("!!!! Try it again !!!!");\
                    //System.out.println("Nu merge");

                    JOptionPane.showMessageDialog(null, "Parola sau user incorecte");
                }
            }
        });
    }



    public static void main(String[] args) {
        logingui log = new logingui();
        log.setVisible(true);
    }


}
