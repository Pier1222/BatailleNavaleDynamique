package model;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe permet d'envoyer des messages aux autres Requester
 *
 */

public class StreamPool {
    private HashMap<Bataille_Server_Requester, PrintStream> stream; 

    public StreamPool() {
        stream = new HashMap<>();
    }

    public synchronized void addStream(Bataille_Server_Requester r, PrintStream p) {
        stream.put(r, p);
        System.out.println("Nb Threads: "  + stream.size());
    }

    public synchronized void removeStream(Bataille_Server_Requester r) {
        stream.remove(r);
    }

    /**
     * 
     * @param s
     * @param self
     * @param src
     * @throws IOException
     */
    public synchronized void broadcast(String s, boolean self, Bataille_Server_Requester src) throws IOException {
        Set<Bataille_Server_Requester> set = stream.keySet();
        for(Bataille_Server_Requester r: set) {
            if((!self && (src == r)))
                continue;

            PrintStream ps = stream.get(r);
            ps.println(s);
        }
    }
}
