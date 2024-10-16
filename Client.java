

import java.awt.event.WindowEvent;

public class Client {
    Server server;
    private boolean serverConnect = false;
    private String name;
    ClientView view;

    Client(Server server){
        this.server = server;
        this.view = new ClientView(this);

    }

    public boolean getServerConnect() {
        return serverConnect;
    }
    
    public void answer(String text){
        appendMessage(text);
    }
    private void appendMessage(String mess){
        view.message(mess);
    }
    public void connectToServer(){
        if (server.connectUser(this)){
            name = view.login.getText();
            view.connect();
            serverConnect = true;
            String mess = server.getLog();
            if (mess != null){
                view.message(mess);
            }
        } else {
            view.message("Подключение не удалось");
        }

    }
    public void disconnectFromServer() {
        if (serverConnect) {
            serverConnect = false;
            view.disconnect();
            server.disconnectUser(this);
        }
    }

    public void messageUploadToServer(String text){
        if (serverConnect){
            if (!text.equals("")){ 
                server.message(name + ": " + text);
            }
        } else {
            view.message("Нет подключения к серверу");
        }
    }
}
