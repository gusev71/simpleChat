package ru.rubiconepro.chatapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Application {


    private static class Connector2 {
        ServerSocket server = null;
        ServerSocketChannel serverChannel = null;
        Selector sel;

        List<SocketChannel> sockets = new CopyOnWriteArrayList<>();

        public Connector2 () {

            try {
                serverChannel = ServerSocketChannel.open();
                serverChannel.configureBlocking(false);
                server = serverChannel.socket();
                server.bind(new InetSocketAddress(60000));

                sel = Selector.open();
                serverChannel.register(sel, SelectionKey.OP_ACCEPT);

                while (true) {
                    int s = sel.select();
                    Set<SelectionKey> keys = sel.selectedKeys();
                    for (SelectionKey key: keys) {
                        if (key.isAcceptable()) {
                            SocketChannel ch = serverChannel.accept();
                            ch.configureBlocking(false);

                            ch.register(sel, SelectionKey.OP_READ);
                            sockets.add(ch);
                        } else if (key.isReadable()) {
                            ByteBuffer buf = ByteBuffer.allocateDirect(10240);
                            SocketChannel ch = (SocketChannel)key.channel();
                            int bytes = ch.read(buf);
                            if (bytes == -1) {
                                ch.close();
                            }
                            buf.flip();

                            for (SocketChannel soc : sockets) {
                                if (soc == key.channel()) continue;
                                ByteBuffer writebuf = buf.duplicate();
                                while (writebuf.remaining() > 0) {
                                    int writen = soc.write(writebuf);
                                    if (writen == 0) {
                                        soc.register(sel, SelectionKey.OP_WRITE, writebuf);
                                        break;
                                    }
                                }
                            }
                        } else if (key.isWritable()) {
                            ByteBuffer writebuf = (ByteBuffer)key.attachment();
                            while (writebuf.remaining() > 0) {
                                int writen = ((SocketChannel) key.channel()).write(writebuf);
                                if (writen == 0) {
                                    key.channel().register(sel, SelectionKey.OP_WRITE, writebuf);
                                    break;
                                }
                            }
                        }
                    }

                    keys.clear();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
    }


    private static class Connecter {
        ServerSocket server = null;

        List<Socket> sockets = new CopyOnWriteArrayList<>();

        public Connecter () {
            try {
                server = new ServerSocket(60000);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            startListen();
        }

        private void startListen() {
            while (true) {
                Socket s = null;
                try {
                    s = server.accept();
                    sockets.add(s);

                    startListenSocket(s);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void startListenSocket (final Socket s) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = s.getInputStream();
                        InputStreamReader inputReader = new InputStreamReader(is);
                        try (BufferedReader sReader = new BufferedReader(inputReader)) {
                            while (true) {
                                String message = sReader.readLine();
                                if (message == null) break;
                                sendToAll(message, s);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {
                        s.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    sockets.remove(s);
                }
            }).start();
        }

        private void sendToAll(String s, Socket source) {
            for (Socket outbound: sockets) {
                if (source == outbound) continue;

                OutputStream ostream = null;
                try {
                    ostream = outbound.getOutputStream();
                    ostream.write(s.getBytes(StandardCharsets.UTF_8));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    sockets.remove(outbound);
                }
            }
        }
    }

    public static void main(String[] args) {
        //new Application.Connector2();
        new Application.Connecter();
    }
}
