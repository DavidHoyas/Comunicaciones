package es.etg.dam.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.etg.dam.Common.Comun;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Carrera {

    @Data
    @AllArgsConstructor
    private static class Horse {
        String name;
        Socket socket;
        PrintWriter pw;
        int total;
    }

    private final List<Horse> horses = new ArrayList<>();

    public synchronized void register(String name, Socket socket, PrintWriter out) {
        horses.add(new Horse(name, socket, out, 0));
    }

    public synchronized int size() {
        return horses.size();
    }

    public void startRace() {
        try { Thread.sleep(Comun.SLEEP); } catch (InterruptedException ignored) {}

        Random rnd = new Random();
        Horse winner = null;

        while (winner == null) {
            for (Horse h : horses) {
                int add = rnd.nextInt(10) + 1;
                h.total += add;
                h.pw.println(add);
                if (h.total >= Comun.TARGET) {
                    winner = h;
                    break;
                }
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            }
        }

        for (Horse h : horses) {
            try {
                if (h == winner) h.pw.println(Comun.MSG_WIN);
                else h.pw.println(Comun.MSG_LOSE);
                h.socket.close();
            } catch (IOException ignored) {}
        }
    }
}