//untuk representasi objek player
package Model;
import java.awt.Graphics;
import ViewModel.Game;
import java.awt.Color;

public class Player extends GameObject {
    // Constructor
    public Player(int x, int y, ID id) {
        super(x, y, id);
        jumping = false;
        vel_x = 0;
    }
    
    // Update Player per Tick
    @Override
    public void tick() {
        // When Player is Jumping
        if (jumping && vel_y < 30) {
            vel_y = -15;
            vel_x = 0;
        }

        // When Player is Moving to Right
        if (right) {
            vel_x += 5;
        }

        // When Playing is Moving to Left
        if (left) {
            vel_x -= 5;
        }

        x += vel_x;
        y += vel_y;
        
        // Gravity System
        gravity = 5;
        if (falling && vel_y < gravity) {
            vel_y += 3;
        } else if (!falling && vel_y > 0) {
            vel_y = 0;
        }

        // When Player Reach Screen Size Limit
        if (x < 0) {
            x = 0;
        }
        
        if (x >= Game.WIDTH-45) {
            x = Game.WIDTH-45;
        }
        if (y > Game.HEIGHT - 45) {
            y = Game.HEIGHT - 45;
        }

        if (y < 0) {
            y = 0;
        }

        vel_x = 0;
    }
    
    // Render Player
    @Override
    public void render(Graphics g) {
        g.setColor(new Color(139, 69, 19));
        g.fillOval(x, y, 30, 30);
    }
}
