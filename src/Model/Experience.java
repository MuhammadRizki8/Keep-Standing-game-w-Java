//kelas representasi dari pengalaman pemain
package Model;
public class Experience {
    private String username;
    private int standing;    
    private int score;   
    public Experience() {}

    public void setUsername(String username) {
        this.username = username;
    }    
    public String getUsername() {
        return this.username;
    }
    public void setstanding(int standing) {
        this.standing = standing;
    }
    public int getstanding() {
        return this.standing;
    }
    public void setscore(int score) {
        this.score = score;
    }
    public int getscore() {
        return this.score;
    }
}
