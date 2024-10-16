
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerView extends JFrame implements ViewIntarfase{
    private static final int WIDTH = 250;
    private static final int HEIGHT = 350;
    JPanel panBottom, labelServerSec;
    JLabel isSerwerWorkerLabel;
    JButton btnStart,btnExit;

    ServerView(Server serv){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Test Server Interfase");
        setResizable(false);

        panBottom = new JPanel(new GridLayout(3, 1));
        labelServerSec = new JPanel(new GridBagLayout());
        isSerwerWorkerLabel = new JLabel("Сервер отключен");
        isSerwerWorkerLabel.setForeground(Color.RED);
        btnStart = new JButton("Запуск сервера");
        btnExit = new JButton("Отключение сервера");
        labelServerSec.add(isSerwerWorkerLabel);
        panBottom.add(labelServerSec);
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!serv.getServerWorking()) {return;}
                disconnect();
                serv.setServerWorking(false);
                while (!serv.clientList.isEmpty()){
                    serv.disconnectUser(serv.clientList.get(serv.clientList.size()-1));
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(serv.getServerWorking()){return;}
                connect();
                serv.setServerWorking(true); 
            }
        });
        setVisible(true);
    }

    @Override
    public void message(String mess) {
  
    }

    @Override
    public void connect() {
        isSerwerWorkerLabel.setForeground(Color.green);
        isSerwerWorkerLabel.setText("Сервер включен");
    }

    @Override
    public void disconnect() {
        isSerwerWorkerLabel.setForeground(Color.RED);
        isSerwerWorkerLabel.setText("Сервер отключен");
    }
    
}
