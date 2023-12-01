package Model;

public class Slider {

    private String Image, title;

    // empty constructor which is
    // required when using Firebase.
    public Slider() {
    }

    // Constructor
    public Slider(String imgUrl) {
        this.Image = Image;
    }

    // Getter method.
    public String getImage() {
        return Image;
    }

    // Setter method.
    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
