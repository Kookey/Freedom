package view.chenyu.com.freedom.bean;

/**
 * Created by wxl19 on 2017/6/22.
 */

public class News {

    private int id;
    private String title;
    private String content;
    private String conver;

    public News() {
    }

    public News(int id, String title, String content, String conver) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.conver = conver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConver() {
        return conver;
    }

    public void setConver(String conver) {
        this.conver = conver;
    }
}
