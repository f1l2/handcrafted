package at.f1l2.lab.curosity;

import java.sql.SQLException;

public class NoCheckedException {


    // No throws clause here
    public static void main(String[] args) {
        doThrow(new SQLException());
    }

    static void doThrow(Exception e) {
        NoCheckedException.<RuntimeException> doThrow0(e);
    }

    @SuppressWarnings("unchecked")
    static <E extends Exception> void doThrow0(Exception e) throws E {
        throw (E) e;
    }
}
