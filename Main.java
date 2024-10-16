

public class Main {
    public static void main(String[] args) {
        Server serv =  new Server();
        new Client(serv);
        new Client(serv);
    }
    
}
