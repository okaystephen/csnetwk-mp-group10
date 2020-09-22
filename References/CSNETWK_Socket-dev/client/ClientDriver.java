

public class ClientDriver {

    public static void main(String[] args) {
        ClientGui cg = new ClientGui();
        Chatbox cb = new Chatbox();
        ClientUsername cu = new ClientUsername();
        ClientModel cm = new ClientModel();
        ClientController cc = new ClientController(cg, cu, cb, cm);
    }
    
}