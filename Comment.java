import java.util.ArrayList;

public class Comment {
    private User user = new User();
    private String text;
    private CommentType commentType;
    private ArrayList<Comment> commenteds = new ArrayList<Comment>();
    private int vote = 0;
    private ArrayList<User> usersUpVoted = new ArrayList<User>();
    private ArrayList<User> usersDownVoted = new ArrayList<User>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public ArrayList<Comment> getCommenteds() {
        return commenteds;
    }

    public void setCommenteds(ArrayList<Comment> commenteds) {
        this.commenteds = commenteds;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
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

    public Comment(User user, String text, CommentType commentType) {
        this.user = user;
        this.text = text;
        this.commentType = commentType;
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


    public void printComment( int indentate){
        if (this.getCommenteds().size() == 0){
            for (int i = 0; i < indentate; i++) {
                System.out.println("\t");
            }
            System.out.println(this.getText());
            return;
        }
        else{
            for (int i = 0; i < indentate; i++) {
                System.out.println("\t");
            }
            System.out.println(this.getText());
            for (Comment subComment :
                    this.getCommenteds()) {
                subComment.printComment( indentate+1);
            }
            return;
        }

    }

    public  void printSubComment(int indent){
        for (Comment comment :
                this.getCommenteds()) {
            for (int i = 0; i < indent; i++) {
                System.out.println("\t");
            }
            System.out.println(comment.getText());
            comment.printSubComment(++indent);
        }
    }
}
