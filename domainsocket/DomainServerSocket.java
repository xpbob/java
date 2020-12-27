import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class DomainServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX);

        UnixDomainSocketAddress of = UnixDomainSocketAddress.of("./test.sock");

        serverSocketChannel.bind(of);

        while (true) {

            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = socketChannel.read(buf);
            while (bytesRead > 0) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                System.out.println();
                buf.clear();
                bytesRead = socketChannel.read(buf);
            }
        }

    }

}
