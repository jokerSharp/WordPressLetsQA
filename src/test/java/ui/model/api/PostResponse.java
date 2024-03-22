package ui.model.api;

public class PostResponse {

    private int postId;
    private int authorId;
    private String status;
    private String title;
    private String commentStatus;
    private String format;

    public PostResponse() {
    }

    public PostResponse(int postId, int authorId, String status, String title, String commentStatus, String format) {
        this.postId = postId;
        this.authorId = authorId;
        this.status = status;
        this.title = title;
        this.commentStatus = commentStatus;
        this.format = format;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
