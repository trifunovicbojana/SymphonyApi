package apimodels.posts;

import apimodels.User;

public class NewPostResponse {

    private Integer id;
    private boolean isLikedByUser;
    private User user;
    private Integer likesCount;
    private Integer commentsCount;
    private String image;
    private boolean isShare;
    private String originalPost;
    private String text;
    private String url;
    private String urlMeta;
    private String createdOn;

    public NewPostResponse(Integer id, boolean isLikedByUser, User user, Integer likesCount, Integer commentsCount, String image, boolean isShare, String originalPost, String text, String url, String urlMeta, String createdOn) {
        this.id = id;
        this.isLikedByUser = isLikedByUser;
        this.user = user;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.image = image;
        this.isShare = isShare;
        this.originalPost = originalPost;
        this.text = text;
        this.url = url;
        this.urlMeta = urlMeta;
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isLikedByUser() {
        return isLikedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        isLikedByUser = likedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isShare() {
        return isShare;
    }

    public void setShare(boolean share) {
        isShare = share;
    }

    public String getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(String originalPost) {
        this.originalPost = originalPost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlMeta() {
        return urlMeta;
    }

    public void setUrlMeta(String urlMeta) {
        this.urlMeta = urlMeta;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
