package ui.model.api;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CommentReq {
    private int author;
    private String content;
    private int post;

    public CommentReq() {
    }

    public CommentReq(int authorId, String comment, int postId) {
        this.author = authorId;
        this.content = comment;
        this.post = postId;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }
}
