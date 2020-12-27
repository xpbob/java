import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class DomainClient {
     public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(StandardProtocolFamily.UNIX);
        UnixDomainSocketAddress of = UnixDomainSocketAddress.of("/");
        boolean connect = socketChannel.connect(of);

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }

    }
}
