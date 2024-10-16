

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientView extends JFrame implements ViewIntarfase{
    private static final int WIDTH = 450;
    private static final int HEIGHT = 360;
    JTextField ipServer,portServer,login,password,message;
    JButton sendButton,loginButton;
    JTextArea messagArea;
    Client cli;
    ClientView(Client cliGUI){
        this.cli = cliGUI;
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Test Client Interfase");
        setResizable(false);

        JPanel headerJpanel = new JPanel(new  GridLayout(2,3));
        JPanel downJpanel = new JPanel(new GridLayout(1,2));
        messagArea = new JTextArea();
        messagArea.setEditable(false);

        ipServer = new JTextField("127.0.0.1");
        portServer = new JTextField("3086");
        login = new JTextField("Ilkin Rinat");
        password = new JTextField("123456");
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cli.getServerConnect()){
                    cli.disconnectFromServer();
                    return;
                }
                cli.connectToServer();
            }
        });
        headerJpanel.add(ipServer);
        headerJpanel.add(portServer);
        headerJpanel.add(login);
        headerJpanel.add(password);
        headerJpanel.add(loginButton);
        message = new JTextField();
        sendButton = new JButton("Send");
        downJpanel.add(message);
        downJpanel.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = message.getText();
                message.setText("");
                cli.messageUploadToServer(text);
            }
        });
        add(headerJpanel,BorderLayout.NORTH);
        add(downJpanel,BorderLayout.SOUTH);
        add(new JScrollPane(messagArea));
        setVisible(true);

    }

    @Override
    public void message(String mess) {
        messagArea.append(mess + "\n");
    }

    @Override
    public void connect() {
        ipServer.setEditable(false);
        portServer.setEditable(false);
        login.setEditable(false);
        password.setEditable(false);
        messagArea.setForeground(Color.green);
        messagArea.append("Вы успешно подключились!\n");
        messagArea.setForeground(Color.BLACK);
        loginButton.setText("Logout");
    }

    @Override
    public void disconnect() {
        ipServer.setEditable(true);
        portServer.setEditable(true);
        login.setEditable(true);
        password.setEditable(true);
        loginButton.setText("Login");
        message("Вы были отключены от сервера!"); 
    }
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            cli.disconnectFromServer();
        }
        super.processWindowEvent(e);
    }
}
