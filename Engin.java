import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Engin {

    Comparator<Post> voteComp = new Comparator<Post>() {
        @Override
        public int compare(Post post, Post t1) {
            return t1.getVote() - post.getVote();
        }
    };
    ArrayList<Subreddit> mainSubreddits = new ArrayList<Subreddit>();
    ArrayList<User> users = new ArrayList<User>();
    User login;
    boolean isLogin = false;

    public void registerUser() {
        Scanner input = new Scanner(System.in);
        String username;
        String password;
        String name;
        System.out.println("Enter username:");
        username = input.nextLine();
        System.out.println("Enter password:");
        password = input.nextLine();
        for (User user :
                users) {
            if (user.getUsername().equals(username)) {
                System.out.println("this username existed!");
                return;
            }
        }
        System.out.println("Enter your name:");
        name = input.nextLine();
        this.users.add(new User(username, password, name));
        this.isLogin = true;
        mainMenu();
        return;
    }

    public void login() {
        Scanner input = new Scanner(System.in);
        String username;
        String password;
        System.out.println("Enter your username:");
        username = input.nextLine();
        System.out.println("Enter your password:");
        password = input.nextLine();
        for (User user :
                users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.login = user;
                System.out.println("Success!");
                this.isLogin = true;
            }
        }
        System.out.println("wrong!Your username or password are incorrect!");
    }

    public void logout() {
        this.isLogin = false;
    }
    public void openComment(Comment comment){
        int j =1;
        System.out.println(comment.getText());
        for (Comment subComment:
                comment.getCommenteds() ) {
            System.out.println("      " + j++ + ". " + subComment.getText());
            subComment.printSubComment(1);
        }
        System.out.println("Enter (0) to back");
        System.out.println("Enter number of comment to open");
        System.out.println("Enter (R) to reply");
        System.out.println("Enter (U) to Up vote");
        System.out.println("Enter (D) to Down vote");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        if (str.equals("0")){
            mainMenu();
            return;
        }
        if (str.equals("R")){
            String text = input.nextLine();
            comment.getCommenteds().add(new Comment(this.login,text,CommentType.COMMENT));
            openComment(comment);
            return;
        }
        if (str.equals("U")){
            comment.toggleUpVote(this.login);
            openComment(comment);
            return;
        }
        if (str.equals("D")){
            comment.toggleDownVote(this.login);
            openComment(comment);
            return;
        }
        else{
            int i = Integer.parseInt(str);
            if (i>0 && i< comment.getCommenteds().size()+1);
            openComment(comment.getCommenteds().get(i-1));
            return;
        }

    }
    public void openPost(Post post) {
        System.out.println("***   ***   ***   ***");
        System.out.println(post.getTitle());
        System.out.println(post.getText());
        for (Comment comment :
                post.getComments()) {
            comment.printComment(0);
        }
        if (this.isLogin) {
            System.out.println("Enter (0) to back");
            System.out.println("Enter (1) to comment on this post");
            System.out.println("Enter (2) to Up vote");
            System.out.println("Enter (3) to Down vote");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            switch (i) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    String text = input.nextLine();
                    post.getComments().add(new Comment(this.login, text, CommentType.POST));
                    openPost(post);
                    break;
                case 2:
                    post.toggleUpVote(this.login);
                    openPost(post);
                    break;
                case 3:
                    post.toggleDownVote(this.login);
                    openPost(post);
                    break;
            }
        } else {
            System.out.println("Enter (0) to back");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            if (i == 0) {
                mainMenu();
                return;
            } else {
                openPost(post);
                return;
            }

        }
    }

    public void openSubreddit(Subreddit subreddit) {
        ArrayList<Post> posts = new ArrayList<Post>();

        for (Post post :
                subreddit.getPosts()) {
            posts.add(post);
        }

        posts.sort(this.voteComp);
        int j = 1;
        for (Post post :
                posts) {

            System.out.println(j++ + ". " + post.getTitle());
            System.out.println(post.getText());
            System.out.println();
            System.out.println("==========================================");
            if (j > 7) {
                break;
            }
        }
        System.out.println("pres (0) to back.");
        System.out.println("press (N) to creat new post on this subreddit");
        System.out.println("pres number of post to open the post.");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        int i = Integer.parseInt(str);
        if (str.equals("0")) {
            mainMenu();
            return;
        }else if (str.equals("N")){
            Scanner strInput = new Scanner(System.in);
            String title;
            String text;
            System.out.println("Enter title of new post:");
            title = strInput.nextLine();
            System.out.println("Enter text of new post:");
            text = strInput.nextLine();
            subreddit.getPosts().add(new Post(this.login,title,true,text,false,""));
        }

        else if (i > 0 && i < posts.size() + 1) {
            openPost(posts.get(i - 1));
            return;
        } else
            openSubreddit(subreddit);
            return;

    }

    public void userMenu() {
        System.out.println("Enter (0) to back");
        System.out.println("Enter (1) to logout");
        System.out.println("Enter (2) to creat new subreddit");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        switch (i){
            case 0:
                mainMenu();
                break;
            case 1:
                logout();
                mainMenu();
                break;
            case 2:
                Scanner inputName = new Scanner(System.in);
                System.out.println("Enter name of new subreddit:");
                String name = inputName.nextLine();
                System.out.println("Enter description:");
                String description = inputName.nextLine();
                this.mainSubreddits.add(new Subreddit(login,name,description,""));
                userMenu();
                break;
        }
    }

    public void homePage() {
        ArrayList<Post> posts = new ArrayList<Post>();
        for (Subreddit subreddit :
                mainSubreddits) {
            for (Post post :
                    subreddit.getPosts()) {
                posts.add(post);
            }
        }

        posts.sort(voteComp);
        int j = 1;
        for (Post post :
                posts) {

            System.out.println(j++ + ". " + post.getTitle());
            System.out.println(post.getText());
            System.out.println();
            System.out.println("==========================================");
            if (j > 7) {
                break;
            }
        }
        System.out.println("pres (0) to back.");
        System.out.println("pres number of post to open the post.");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        if (i == 0) {
            mainMenu();
            return;
        } else if (i > 0 && i < posts.size() + 1) {
            openPost(posts.get(i - 1));
            return;
        } else
            return;
    }

    public void allSubreddit(){
        int i = 1;
        for (Subreddit subreddit :
                this.mainSubreddits) {
            System.out.println(i++ +". " + subreddit.getName());
            System.out.println(subreddit.getDescription());
        }
        System.out.println("Enter (0) to back");
        System.out.println("Enter number of subreddit to open:");
        Scanner input = new Scanner(System.in);
        i = input.nextInt();
        if(i == 0){
            mainMenu();
            return;
        }
        if (i>0 && i<mainSubreddits.size()+1){
            openSubreddit(mainSubreddits.get(i-1));
            return;
        }
        else {
            allSubreddit();
            return;
        }
    }

    public void mainMenu() {
        System.out.println("Enter menu:");
        System.out.println("0. All subreddit");
        System.out.println("1. Home page");
        if(this.isLogin){
            System.out.println("2. User menu");
        }
        if (this.isLogin == false) {
            System.out.println("2. Register");
            System.out.println("3. Login");
        }
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        if(i == 0){
            allSubreddit();
        }
        if (i == 1) {
            homePage();
            return;
        }
        else if(i == 2 && this.isLogin){
            userMenu();
        }
        else if (i == 2 && !this.isLogin) {
            registerUser();
        } else if (i == 3 && !this.isLogin) {
            login();
        } else {
            mainMenu();
        }
    }

}
