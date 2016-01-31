package epitech.intratek.beans;

import android.text.Spanned;

/**
 * Created by Dardaxe on 23/01/2016.
 */
public class Message {
    private String title;
    private String senderName;
    private String senderPicture;
    private String senderUrl;
    private String content;
    private String date;
    private android.text.Spanned contentHtml;

    public Spanned getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(Spanned contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPicture() {
        return senderPicture;
    }

    public void setSenderPicture(String senderPicture) {
        this.senderPicture = senderPicture;
    }

    public String getSenderUrl() {
        return senderUrl;
    }

    public void setSenderUrl(String senderUrl) {
        this.senderUrl = senderUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
