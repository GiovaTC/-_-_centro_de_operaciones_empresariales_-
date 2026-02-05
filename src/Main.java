import view.*;
import controller.*;

public class Main {
    public static void main(String[] args) {

        CentroOperacionView view = new CentroOperacionView();
        new CentroOperacionController(view);
        view.setVisible(true);
    }
}