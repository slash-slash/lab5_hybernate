package exception;

import org.hibernate.HibernateError;

public class ShowException {
    public static void showNotice(HibernateError e) {
        System.out.println(e.getMessage());
    }
}
