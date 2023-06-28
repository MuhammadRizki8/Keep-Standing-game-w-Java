//kelas representasi obstacle
package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

public class Obstacle extends GameObject {
    private Color color;
    private int skor;
    public boolean step=false;

    public Obstacle(int x, int y, ID id, int skor) {
        super(x, y, id);
        color = generateRandomColor();
        this.skor=skor;
    }

    private Color generateRandomColor() {
        Random random = new Random();
        // Generate random RGB values
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
    public void stepped() {
        step = true;
    }
    public boolean step_stat() {
        return this.step;
    }

    // Update Obstacle per Tick
    @Override
    public void tick() {
        y -= vel_y;
    }
    // Render Obstacle
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 800, 30);
        // Print the score in the middle of the obstacle
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        String scoreText = String.valueOf(skor);
        int numberWidth = fm.stringWidth(scoreText);
        int numberHeight = fm.getHeight();
        int numberX = x + (800 - numberWidth)-30; // Calculate the X position to center the number
        int numberY = y + (30 - numberHeight) / 2 + fm.getAscent(); // Calculate the Y position to center the number
        g.drawString(scoreText, numberX, numberY);
    }
}
