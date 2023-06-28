//kode untuk handle objek dalam game

package ViewModel;

import Model.GameObject;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> obj = new LinkedList<>();
    
    // Memperbarui Game Object pada setiap Tick
    public void tick() {
        for (int i = 0; i < obj.size(); i++) {
            GameObject tmpObj = obj.get(i);
            tmpObj.tick();
        }
    }
    
    // Merender Game Object
    public void render(Graphics g) {
        for (int i = 0; i < obj.size(); i++) {
            GameObject tmpObj = obj.get(i);
            tmpObj.render(g);
        }
    }
    
    // Menambahkan Objek ke dalam List
    public void addObject(GameObject obj) {
        this.obj.add(obj);
    }
    
    // Menghapus Objek dari List
    public void removeObject(GameObject obj) {
        this.obj.remove(obj);
    }
    
    // Menghitung Jumlah Objek dalam List
    public int countObject() {
        return this.obj.size();
    }
}
