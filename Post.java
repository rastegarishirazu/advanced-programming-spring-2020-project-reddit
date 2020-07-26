import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Comparator;

public class Post {
    private User userCreator = new User();
    private int vote = 0;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private String title;
    private boolean haveText;
    private String text;
    private boolean haveImage;
    private String imageUrl;
    private ArrayList<User> usersUpVoted = new ArrayList<User>();
    private ArrayList<User> usersDownVoted = new ArrayList<User>();

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHaveText() {
        return haveText;
    }

    public void setHaveText(boolean haveText) {
        this.haveText = haveText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHaveImage() {
        return haveImage;
    }

    public void setHaveImage(boolean haveImage) {
        this.haveImage = haveImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<User> getUsersUpVoted() {
        return usersUpVoted;
    }

    public void setUsersUpVoted(ArrayList<User> usersUpVoted) {
        this.usersUpVoted = usersUpVoted;
    }

    public ArrayList<User> getUsersDownVoted() {
        return usersDownVoted;
    }

    public void setUsersDownVoted(ArrayList<User> usersDownVoted) {
        this.usersDownVoted = usersDownVoted;
    }

    public Post(User userCreator, String title, boolean haveText, String text, boolean haveImage, String imageUrl) {
        this.userCreator = userCreator;
        this.title = title;
        this.haveText = haveText;
        this.text = text;
        this.haveImage = haveImage;
        this.imageUrl = imageUrl;
    }

    public void toggleUpVote(User user){
        for (User userVoted :
                this.usersUpVoted) {
            if(userVoted.equals(user)){
                this.usersUpVoted.remove(this.usersUpVoted.indexOf(userVoted));
                this.vote--;
                return;
            }
        }
        for (User userVoted :
                this.usersDownVoted) {
            if (userVoted.equals(user)){
                this.usersDownVoted.remove(this.usersDownVoted.indexOf(userVoted));
                this.usersUpVoted.add(user);
                this.vote += 2;
                return;
            }
        }
        this.usersUpVoted.add(user);
        this.vote ++;
    }
    public void toggleDownVote(User user){
        for (User userVoted :
                this.usersUpVoted) {
            if(userVoted.equals(user)){
                this.usersUpVoted.remove(this.usersUpVoted.indexOf(userVoted));
                this.usersDownVoted.add(user);
                this.vote -= 2;
                return;
            }
        }
        for (User userVoted :
                this.usersDownVoted) {
            if (userVoted.equals(user)){
                this.usersDownVoted.remove(this.usersDownVoted.indexOf(userVoted));
                this.vote ++;
                return;
            }
        }
        this.usersUpVoted.add(user);
        this.vote --;
    }






}
