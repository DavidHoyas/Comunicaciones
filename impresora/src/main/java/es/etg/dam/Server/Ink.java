package es.etg.dam.Server;

import lombok.Data;

@Data
public class Ink {

    private static final String MSG_KO = "KO:0";
    private static final String MSG_OK = "OK:%s";
    private static final String MSG_ERR = "ERR invalido INK:0";

    private static final int MAX_PAGS = 100;

    private static final double PRICE_BW = 0.5;
    private static final double PRICE_COLOR = 1.0;

    private static final int POS_MSG = 0;
    private static final int POS_PAGS = 1;

    private int colorPaper;
    private int bwPaper;

    private boolean used;

    public Ink() {
        colorPaper = MAX_PAGS;
        bwPaper = MAX_PAGS;
        used = false;
    }

    private String[] calculate(int pags, int available, double pricePerPage) {
        String[] result = new String[2];

        if (pags <= 0) {
            result[0] = MSG_ERR;
            result[1] = Integer.toString(available);
            return result;
        }

        if (pags > available) {
            result[0] = MSG_KO;
            result[1] = Integer.toString(available);
            return result;
        }

        int remaining = available - pags;
        String price = String.format("%.2f", pags * pricePerPage);
        result[0] = String.format(MSG_OK, price);
        result[1] = Integer.toString(remaining);
        return result;
    }

    public synchronized String print(String mode, int pags) throws InterruptedException {

        if(used) {
            wait();
        }

        used = true;
        String[] data;
        String ret = MSG_ERR;
        switch (mode) {
            case "C":
                data = calculate(pags,colorPaper, PRICE_COLOR);
                colorPaper = Integer.parseInt(data[POS_PAGS]);
                ret = data[POS_MSG];
                break;

            case "B":
                data = calculate(pags,bwPaper, PRICE_BW);
                bwPaper = Integer.parseInt(data[POS_PAGS]);
                ret = data[POS_MSG];
                break;
        }
        used = false;
        notify();
        return ret;
    }
}
