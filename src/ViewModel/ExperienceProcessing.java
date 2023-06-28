//kode untuk memproses data dari database ke tabel view
package ViewModel;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Model.ExperienceTable;
import Model.Experience;

public class ExperienceProcessing {
    private String error; // untuk menyimpan pesan kesalahan
    private ExperienceTable table; // kelas untuk mengakses query
    private ArrayList<Experience> data; // untuk menyimpan hasil dari query
    
    // Konstruktor
    public ExperienceProcessing() {
        try {
            // Menginisialisasi objek Tabel dan ArrayList
            table = new ExperienceTable();
            data = new ArrayList<Experience>();
        } catch(Exception e) {
            error = e.toString();
        }
    }
    
    // Membaca Data Dari Database dan Mengembalikannya sebagai DefaultTableModel
    public DefaultTableModel readData() {
        DefaultTableModel dataTbl = null;
        try {
            // Mendapatkan semua data dari tabel Experience
            Object[] column = {"Username", "Skor", "Prestasi"};
            dataTbl = new DefaultTableModel(null, column);
            table.getData();
            while (table.getResult().next()) {
                // Mengambil semua hasil query
                Experience exp = new Experience();
                exp.setUsername(table.getResult().getString(1));
                exp.setstanding(table.getResult().getInt(2));
                exp.setscore(table.getResult().getInt(3));
                
                Object[] row = new Object[3];
                row[0] = exp.getUsername();
                row[1] = exp.getscore();
                row[2] = exp.getstanding();
                
                // Menambahkan data ke ArrayList
                dataTbl.addRow(row);
                data.add(exp);
            }
            // Menutup hasil query
            table.closeResult();

            // Menutup koneksi database
            table.closeConnection();
        } catch(Exception e) {
            error = e.toString();
        }

        return dataTbl;
    }

    // Memeriksa apakah username sudah ada dalam database
    public boolean isDataExist(String username) {
        boolean result = false;
        try {
            table.getData();
            while (table.getResult().next()) {
                if (table.getResult().getString(1).equals(username)) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            error = e.toString();
        }

        return result;
    }

    // Mengambil data
    public void getData(String username) {
        try {
            table.getDetailData(username);
            Experience exp = new Experience();
            table.getResult().next();
            exp.setUsername(table.getResult().getString(1));
            exp.setstanding(table.getResult().getInt(2));
            exp.setscore(table.getResult().getInt(3));

            data.add(exp);
            
            table.closeResult();
            table.closeConnection();
        } catch (Exception e) {
            error = e.toString();
        }
    }

    // Menyimpan data
    public void storeData(String username, int standing, int score) {
        try {
            Experience exp = new Experience();
            exp.setUsername(username);
            exp.setstanding(standing);
            exp.setscore(score);
            
            // Memeriksa apakah username sudah ada dalam database atau belum
            if (isDataExist(username)) {
                table.updateData(exp);
            } else {
                table.insertData(exp);
            }
            table.closeConnection();
        } catch (Exception e) {
            error = e.toString();
        }
    }

    // Mengambil data
    public String getUsername(int i) {
        return data.get(i).getUsername();
    }

    public int getstanding(int i) {
        return data.get(i).getstanding();
    }

    public int getscore(int i) {
        return data.get(i).getscore();
    }

    public int getSize() {
        return data.size();
    }

    public String getError() {
        return this.error;
    }
}
