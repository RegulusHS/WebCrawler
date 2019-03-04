package Fastjson;

public class CommentModel {
    private String commentId;   //评论的id
    private String itemId;  //评论的菜品
    private String content; //评论的内容
    private String createTime;  //评论的时间
    private String openUserName;    //评论作者的名称

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setOpenUserName(String openUserName) {
        this.openUserName = openUserName;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getContent() {
        return content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getOpenUserName() {
        return openUserName;
    }
}
