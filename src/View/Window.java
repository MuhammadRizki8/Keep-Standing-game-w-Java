//kode untuk tampilan window game

package View;
import ViewModel.Game;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {
    JFrame jFrame;

    public Window(int width, int height, String title, Game game) {
        jFrame = new JFrame(title);

        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setMaximumSize(new Dimension(width, height));
        jFrame.setMinimumSize(new Dimension(width, height));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(game);
        jFrame.setVisible(true);

        game.start();
    }

    public void closeWindow() {
        jFrame.setVisible(false);
    }
}
