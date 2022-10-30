package newproj;

/**
 *
 * @author Tyler Costa & Shambhavi Bhadauria
 */
public class Destinations {

    private int index;
    private String destName;
    private int price;
    private int numSeatsAvailable;


    //Constructor for Destination objects
    public Destinations(int index, String destName, int price, int numSeatsAvailable) {
        this.index = index;
        this.destName = destName;
        this.price = price;
        this.numSeatsAvailable = numSeatsAvailable;
        
    }


    
    //Getters and setters for Destination object properties
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setNumSeatsAvailable(int num) {
        this.numSeatsAvailable = num;

    }

    public int getNumSeatsAvailable() {
        return this.numSeatsAvailable;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getDestName() {
        return this.destName;
    }

    public void removeSeats() {
        this.numSeatsAvailable--;
    }

    public String toString() {
        return "\n" + this.index + ") " + this.destName + ", $" + this.price + ", Seats Available: " + this.numSeatsAvailable;

    }

}
