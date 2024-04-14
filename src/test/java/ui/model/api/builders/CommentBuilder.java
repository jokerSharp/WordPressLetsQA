package ui.model.api.builders;

import org.apache.commons.lang3.RandomStringUtils;
import ui.model.api.pojo.CommentPOJO;

public class CommentBuilder {
    public static CommentPOJO getRandomComment(int postId, int authorId) {
        return CommentPOJO.builder()
                .content(RandomStringUtils.randomAlphabetic(20))
                .post(postId)
                .author(authorId)
                .build();
    }
}
