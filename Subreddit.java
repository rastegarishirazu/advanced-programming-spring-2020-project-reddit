import java.util.ArrayList;

public class Subreddit {
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<User> users = new ArrayList<User>();
    private User owner = new User();
    private String name;
    private String description;
    private String pictureUrl;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Subreddit(User owner, String name, String description, String pictureUrl) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }
}
