package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
//        System.out.println("connected");
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! the connection is already established");
    }

    @Override
    public void disconnect() {
        TcpConnection con = this.tcpConnection;
        con.setCurrentState(new Disconnected(con));
        System.out.println("a connection has been disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
// END
