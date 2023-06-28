// Kode untuk memproses inputan keyboard pemain

package ViewModel;

import Model.GameObject;
import Model.ID;
import View.Menu;
import ViewModel.Game.STATE;
import java.awt.event.*;

public class KeyInput extends KeyAdapter {
    // Object Handler
    private Handler handler;

    // Game
    Game game;

    // Konstruktor
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    // Ketika tombol ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Ketika keadaan permainan adalah Game
        if (game.state == STATE.Play) {
            for (int i = 0; i < handler.obj.size(); i++) {
                // Ketika ID objek adalah Player
                if (handler.obj.get(i).getId() == ID.Player) {
                    GameObject tempObj = handler.obj.get(i);
                    
                    // Ketika pemain menekan tombol W atau panah atas
                    if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !tempObj.getFalling()) {
                        tempObj.setJump(true);
                    }

                    // Ketika pemain menekan tombol A atau panah kiri
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                        tempObj.setLeft(true);
                    }

                    // Ketika pemain menekan tombol D atau panah kanan
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                        tempObj.setRight(true);
                    }

                    // Ketika pemain menekan tombol Spasi
                    if (key == KeyEvent.VK_SPACE) {
                        game.state = STATE.GameOver;
                        game.storeData();
                        Menu menu = new Menu();
                        menu.setVisible(true);
                        game.close();
                    }
                    
                    break;
                }
            }
        }
    }

    // Ketika tombol dilepas
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Ketika keadaan permainan adalah Game
        if (game.state == STATE.Play) {
            for (int i = 0; i < handler.obj.size(); i++) {
                // Ketika ID objek adalah Player
                if (handler.obj.get(i).getId() == ID.Player) {
                    GameObject tempObj = handler.obj.get(i);
                    
                    // Ketika pemain melepaskan tombol W atau panah atas
                    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                        tempObj.setJump(false);
                    }

                    // Ketika pemain melepaskan tombol A atau panah kiri
                    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                        tempObj.setLeft(false);
                    }

                    // Ketika pemain melepaskan tombol D atau panah kanan
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                        tempObj.setRight(false);
                    }

                    break;
                }
            }
        }
    }
}
