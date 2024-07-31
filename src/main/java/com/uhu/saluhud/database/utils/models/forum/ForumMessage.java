package com.uhu.saluhud.database.utils.models.forum;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This class represents the forum messages in the database
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "forum_message")
public class ForumMessage implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "message_date", nullable = false)
    private LocalDateTime messageDate;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @ManyToOne
    @JoinColumn(name = "id_forum_thread", nullable = false)
    private ForumThread forumThread;

    /**
     * This a default constructor for the class, with no parameters
     */
    public ForumMessage()
    {

    }

    /**
     * This is a parameterized constructor for the class.It takes, the author,
     * the message date, the message content and the forum thread in which was
     * created
     *
     * @param author the author of the message
     * @param messageDate the date of creation of the message
     * @param messageContent the content of the message
     * @param forumThread the forum thread in which was created the message
     */
    public ForumMessage(String author, LocalDateTime messageDate, String messageContent, ForumThread forumThread)
    {
        this.author = author;
        this.messageDate = messageDate;
        this.messageContent = messageContent;
        this.forumThread = forumThread;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id of the message
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "author"
     *
     * @return the author of the message
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Setter for the parameter "author"
     *
     * @param author the new author of the message
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * Getter for the parameter "messageDate"
     *
     * @return the date of the message
     */
    public LocalDateTime getMessageDate()
    {
        return messageDate;
    }

    /**
     * Setter for the parameter "messageDate"
     *
     * @param messageDate the new creation date of the message
     */
    public void setMessageDate(LocalDateTime messageDate)
    {
        this.messageDate = messageDate;
    }

    /**
     * Getter for the parameter "messageContent"
     *
     * @return the message content
     */
    public String getMessageContent()
    {
        return messageContent;
    }

    /**
     * Setter for the parameter "messageContent"
     *
     * @param messageContent the new message content
     */
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    /**
     * Getter for the parameter "forumThread"
     *
     * @return the forum thread in which was created
     */
    public ForumThread getForumThread()
    {
        return forumThread;
    }

    /**
     * Setter for the parameter "forumThread"
     *
     * @param forumThread the new thread of the forum to which it belongs
     */
    public void setForumThread(ForumThread forumThread)
    {
        this.forumThread = forumThread;
    }
}
