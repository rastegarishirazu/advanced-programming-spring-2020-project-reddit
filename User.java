public class User {
    private String username;
    private String password;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Subreddit newSubreddit(String subRedditName, String description, String imageUrl){
        Subreddit newSubredit = new Subreddit(this,name,description,imageUrl);
        return newSubredit;
    }
    public Post newPost(Subreddit subreddit,String title, boolean haveImage,boolean haveText ,String text, String imageUrl ){
        for (User subredditUser :
                subreddit.getUsers()) {
            if (subredditUser.equals(this) || subreddit.getOwner().equals(this)){
                Post newPost = new Post(this,title,haveText,text,haveImage,imageUrl);
                return newPost;
            }
        }
        return null;
    }
    public void newCommentPost(Post post, String text){
        Comment newComment = new Comment(this,text,CommentType.POST);
        post.getComments().add(newComment);
        return;
    }

    public void newReplyComment(Comment source, String text){
        Comment newReplyComment = new Comment(this,text,CommentType.COMMENT);
        source.getCommenteds().add(newReplyComment);
    }

    public void joinSubreddit(Subreddit subreddit){
        subreddit.getUsers().add(this);
        return;
    }
}
