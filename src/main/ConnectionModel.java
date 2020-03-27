package main;

public class ConnectionModel {

    private String hote;
    private String id;
    private String passwd;
    private int port;

    public ConnectionModel()
    {
        port=21;
    }

    public ConnectionModel(String hote, String id, String passwd, int port) {
        this.hote = hote;
        this.id = id;
        this.passwd = passwd;
        this.port = port;
    }

    public String getHote() {
        return hote;
    }

    public void setHote(String hote) {
        this.hote = hote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ConnectionModel{" +
                "hote='" + hote + '\'' +
                ", id='" + id + '\'' +
                ", passwd='" + passwd + '\'' +
                ", port=" + port +
                '}';
    }
}
