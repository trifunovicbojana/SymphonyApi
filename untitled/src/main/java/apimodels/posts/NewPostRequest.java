package apimodels.posts;

public class NewPostRequest {
    private String text;

    public NewPostRequest(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
