package apimodels.comments;

public class NewCommentRequest {
    private String text;
    private int post;

    public NewCommentRequest(String text, int post) {
        this.text = text;
        this.post = post;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

}
