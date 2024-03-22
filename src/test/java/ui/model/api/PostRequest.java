package ui.model.api;

public class PostRequest {

    private String status;
    private String title;
    private int author;
    private String commentStatus;

    public PostRequest() {
    }

    public PostRequest(String status, String title, int author, String commentStatus) {
        this.status = status;
        this.title = title;
        this.author = author;
        this.commentStatus = commentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }
}
