package tables;

public class Review {

    int id, costumerid, shoeid, ratingid;

    public Review(int id, int costumerid, int shoeid, int ratingid) {
        this.id = id;
        this.costumerid = costumerid;
        this.shoeid = shoeid;
        this.ratingid = ratingid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCostumerid() {
        return costumerid;
    }

    public void setCostumerid(int costumerid) {
        this.costumerid = costumerid;
    }

    public int getShoeid() {
        return shoeid;
    }

    public void setShoeid(int shoeid) {
        this.shoeid = shoeid;
    }

    public int getRatingid() {
        return ratingid;
    }

    public void setRatingid(int ratingid) {
        this.ratingid = ratingid;
    }
}
