package tables;

public class Rating {

    int id;
    String comment;
    int level;

    public Rating(int id, String comment, int level) {
        this.id = id;
        this.comment = comment;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
