package ui.model.api;

import java.util.Map;

public class CommentResp {
    private int commentId;

    private int authorId;
    private String comment;

    private int postId;

    public CommentResp() {
    }

    public CommentResp(int commentId, int authorId, String comment, int postId) {
        this.commentId = commentId;
        this.authorId = authorId;
        this.comment = comment;
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
