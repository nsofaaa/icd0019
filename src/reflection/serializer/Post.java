package reflection.serializer;

public class Post {

    @Stored
    private String title;

    @Stored("content")
    private String text;

    @Stored
    private int replyCount;

    public Post() {
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
        this.replyCount = 0; // начально значение
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", replyCount=" + replyCount +
                '}';
    }
}