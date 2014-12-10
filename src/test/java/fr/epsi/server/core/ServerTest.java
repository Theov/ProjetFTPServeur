package fr.epsi.server.core;

import fr.epsi.server.thread.ListeningThread;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.net.ServerSocket;

public class ServerTest extends TestCase {

    private Server server;
    private ServerSocket mockedServerSocket;
    private ListeningThread mockedListeningThread;

    @Before
    public void setUp() throws Exception {
        server = new Server();

        mockedServerSocket = Mockito.mock(ServerSocket.class);
        mockedListeningThread = Mockito.mock(ListeningThread.class);
    }

    @After
    public void tearDown() throws Exception {
        server.stopServer();

        assertTrue(server.getServerSocket().isClosed());
    }

    public void testListensToClients() throws Exception {
        assertFalse(server.getServerSocket().isClosed());

        server.setListeningThread(mockedListeningThread);
        server.startServer();

        Mockito.verify(mockedListeningThread).start();

        assertFalse(server.getServerSocket().isClosed());
    }
}