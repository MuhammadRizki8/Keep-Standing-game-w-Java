package ViewModel;

import Model.GameObject;
import Model.ID;
import Model.Obstacle;
import Model.Player;
import View.Menu;
import View.Window;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.sound.sampled.Clip;

public class Game extends Canvas implements Runnable {
    // Window
    private Window window;
    private Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/bgFix.jpg"));
    // Screen Size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Thread
    private Thread thread;
    private boolean running = false;

    // Player Data
    private String username;
    private int standing;
    private int score;
    private int collideWithJurang = 0;
   
    // Game Var
    private int FPS;
    private int elapsed = 0;
    private int randXPos;

    // Object Handler
    private Handler handler;

    // BGM Clip
    private Clip clip;

    // Enum State in This Game
    public enum STATE {
        Play,
        GameOver
    }

    // Initial State
    public STATE state = STATE.Play;

    // Constructor
    public Game() {
        // Memanggil Window
        window = new Window(Game.WIDTH, Game.HEIGHT, "Keep Standing", this);

        // Memanggil Handler
        handler = new Handler();

        // KeyListener Untuk Input Keyboard Pemain
        this.addKeyListener(new KeyInput(handler, this));

        if (state == STATE.Play) {
            // Menambahkan Objek Player 
            handler.addObject(new Player(50, Game.HEIGHT/2-50, ID.Player));

            // Menambahkan Rintangan untuk Awal Permainan
            int tempY = Game.HEIGHT/2;
            for (int i = 0; i < 3; i++) {
                randXPos = (Game.WIDTH - makeRandom(50, 650));
                handler.addObject(new Obstacle(-randXPos, tempY, ID.Obstacle, randXPos/10));
                tempY += 100;
            }
        }
    }

    // Username Setter
    public void setUsername(String username) {
        this.username = username;
    }

    // standing Setter
    public void setstanding(int standing) {
        this.standing = standing;
    }

    // Fall Setter
    public void setscore(int score) {
        this.score = score;
    }

    // Thread Start
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // Thread Stop
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        int idx = 0;
        int tempY = Game.HEIGHT;
        BGM bgm = new BGM();
        // Memainkan BGM
        clip = bgm.playSound(this.clip, "life-of-a-wandering-wizard-15549.wav");
        // Game Loop
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (state == STATE.GameOver) {
                bgm.stopSound(this.clip);
            }

            if (System.currentTimeMillis() - timer > 1000) {
                elapsed++;
                timer += 1000;
                FPS = frames;
                randXPos = (Game.WIDTH - makeRandom(50, 650));
//              buat objek obstacle
                handler.addObject(new Obstacle(-randXPos, tempY, ID.Obstacle, randXPos/10));
//                jaran antar obstacle
                tempY += 50;

                // Menjalankan Semua Rintangan ke atas
                GameObject obs = null;
                for (int i = 0; i < handler.countObject(); i++) {
                    if (handler.obj.get(i).getId() == ID.Obstacle) {
                        obs = handler.obj.get(i);
                        double velocity = 1;
                        obs.setVel_y(velocity);
                    }
                }

                frames = 0;
            }
        }
        stop();
    }


    // Updating Object Every Tick
    private void tick() {
        handler.tick();

        if (state == STATE.Play) {
            GameObject player = null;
            GameObject obs = null;

            // Mengambil Objek Player
            for (int i = 0; i < handler.countObject(); i++) {
                if (handler.obj.get(i).getId() == ID.Player) {
                    player = handler.obj.get(i);
                }
                if (player != null) {
                    break;
                }
            }

            // Ketika Player tidak null
            // Kemudian Memeriksa Tabrakan Player dengan Rintangan
            if (player != null) {
                for (int i = 0; i < handler.countObject(); i++) {
                    if (handler.obj.get(i).getId() == ID.Obstacle) {
                        player.setFalling(true);

                        // Ketika Player Menabrak Rintangan
                        if (isCollide(player, (Obstacle)handler.obj.get(i))) {
                            // Ketika Player Menyentuh Bagian Atas Rintangan
                            if (elapsed >= 1) {
                                // Ketika Player Menyentuh Bagian Atas Jurang
                                if (collideWithJurang == 1) {
//                                    game over
                                    state = STATE.GameOver;
                                    storeData();
                                    new Menu().setVisible(true);
                                    close();
                                }
                                break;
                            }
                        }
                    }
                }
            }

            // Ketika Rintangan Sudah Keluar dari Layar
            // Maka Hapus Rintangan dari Daftar Objek
            for (int i = 0; i < handler.countObject(); i++) {
                if (handler.obj.get(i).getId() == ID.Obstacle) {
                    obs = handler.obj.get(i);
                    if (obs.getY() <= -30) {
                        handler.removeObject(obs);
                        i--;
                    }
                }
            }
        }
    }


    // Checking Collision
    public boolean isCollide(GameObject player, Obstacle obstacle) {
        boolean result = false;

        // Variabel GameObject
        int playerSize = 30;
        int obstacleWidth = 800;
        int obstacleHeight = 30;

        // Sisi Player
        int playerLeft = player.x;
        int playerRight = player.x + playerSize;
        int playerTop = player.y;
        int playerBottom = player.y + playerSize;

        // Sisi Rintangan
        int obstacleLeft = obstacle.x;
        int obstacleRight = obstacle.x + obstacleWidth;
        int obstacleTop = obstacle.y;
        int obstacleBottom = obstacle.y + 50;

        // Ketika Player Bersinggungan dengan Rintangan
        if (new Rectangle(player.x, player.y, playerSize, playerSize).intersects(obstacle.x, obstacle.y, obstacleWidth, obstacleHeight)) {
            // Ketika Player Jatuh di Atas Rintangan
            // Maka Set Nilai Falling menjadi False
            // Dan Ketika Kecepatan Gerak pada Sumbu Y Masih Positif (Jatuh karena Gravitasi)
            // Maka Set Kecepatan pada Sumbu Y menjadi Nol
            if (playerBottom >= obstacleTop && playerTop < obstacleTop) {
                player.setY(obstacleTop - playerSize);
                player.setFalling(false);
                if (player.getVel_y() > 0) {
                    player.setVel_y(0);
                    // Ketika Player Tidak Jatuh Lagi
                    if (!player.getFalling()) {
//                      hitung skornya  
                        if (obstacle.step == false) {
                            obstacle.stepped();
                            standing++;
                            score += (obstacle.getX() * -1 / 10);
                        }
                    }
                }
                // Jika player tebawa arus ketika masih diatas rintangan, maka game over
                if (playerTop <= 0) {
                    state = STATE.GameOver;
                    storeData();
                    new Menu().setVisible(true);
                    close();
                }
            }
            // Ketika Player Bersinggungan dengan Sisi Kanan Rintangan
            if (playerLeft <= obstacleRight && playerLeft >= obstacleLeft && playerTop >= obstacleTop && playerRight > obstacleRight) {
                player.setLeft(false);
                player.setX(obstacleRight++);
            }
            // Ketika Player Bersinggungan dengan Sisi Bawah Rintangan
            if (playerTop <= obstacleBottom && playerBottom > obstacleBottom) {
                player.setVel_y(0);
                player.setY(obstacleBottom);
            }
            // Ketika Player Melompat
            // Maka Set Nilai Falling Player menjadi true dan Reset collideWithObstacles
            if (player.getVel_y() < 0 && playerBottom < obstacleTop) {
                player.setFalling(true);
            }
            result = true;
        }

        // Ketika Player Menyentuh Jurang
        if (player.getY() >= HEIGHT - 50) {
            collideWithJurang++;
            result = true;
        }

        return result;
    }

    // Random Number Generator
    public static int makeRandom(int min, int max) {
        Random random = new Random();
        int result = random.nextInt((max - min) + 1) + min;
        return result;
    }

    // render semua objek
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(bg, -180, -60, null);

        // Render Game Objects
        if (state == STATE.Play) {
            handler.render(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 14));
            g.drawString("standing: " + Integer.toString(standing), 10, 60);
            g.drawString("Score: " + Integer.toString(score), 10, 80);
        }
        g.dispose();
        bs.show();  
    }

    // Insert Data to Database
    public void storeData() {
        try {
            ExperienceProcessing process = new ExperienceProcessing();
            process.storeData(username, standing, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Username: " + this.username + "\nstanding: " + this.standing + "\nscore: " + this.score, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }

    // Close Game
    public void close() {
        window.closeWindow();
    }
}
