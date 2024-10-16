
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Server extends JFrame{
   
    private boolean isSerwerWorking = false;
    List<Client> clientList;
    ServerView view;
    ServerDB db;
    
    Server(){
        clientList = new ArrayList<>();
        view = new ServerView(this);
        db = new ServerDB();
        
    }
    public boolean getServerWorking(){
        return isSerwerWorking;
    }
    public void setServerWorking(boolean flag){
        this.isSerwerWorking = flag;
    }
    public boolean connectUser(Client clientGUI){
        if (!isSerwerWorking){
            return false;
        }
        for (Client elem : clientList) {
            if (clientGUI == elem){return true;}
        }
        clientList.add(clientGUI);
        return true;
    }

    public void disconnectUser(Client clientGUI){
        clientList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectFromServer();
        }
    }

    public void message(String mess){
        if (!isSerwerWorking){
            return;
        }
        mess += "";
        messToClientGUI(mess);
        saveInfile(mess);
    }

    private void saveInfile(String mess){
       db.saveInDB(mess);
    }

    private void messToClientGUI(String mess){
        for (Client clientGUI: clientList){
            clientGUI.answer(mess);
        }
    }

    public String getLog() {
       return db.loadINDB();
    }
}