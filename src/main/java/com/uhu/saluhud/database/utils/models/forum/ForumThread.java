package com.uhu.saluhud.database.utils.models.forum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * This class represents the forum threads in the database
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "forum_thread")
public class ForumThread implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "id_forum", nullable = false)
    private Forum forum;

    @OneToMany(mappedBy = "forumThread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForumMessage> forumMessages;

    /**
     * This a default constructor for the class, with no parameters
     */
    public ForumThread()
    {

    }

    /**
     * This is a parameterized constructor for the class.It takes, the title,
     * the author, the date of creation and the list of forum messages
     *
     * @param title the title of the thread
     * @param author the author of the thread
     * @param createdDate the date of creation of the thread
     * @param forum
     * @param forumMessages the list of messages of the thread
     */
    public ForumThread(String title, String author, LocalDateTime createdDate, Forum forum, List<ForumMessage> forumMessages)
    {
        this.forum = forum;
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
        this.forumMessages = forumMessages;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id of the thread
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "title"
     *
     * @return the title of the thread
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Setter for the parameter "title"
     *
     * @param title the new title of the thread
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Getter for the parameter "forum"
     *
     * @return the forum of the thread
     */
    public Forum getForum()
    {
        return forum;
    }

    /**
     * Setter for the parameter "forum"
     *
     * @param forum the new forum of the thread
     */
    public void setForum(Forum forum)
    {
        this.forum = forum;
    }

    /**
     * Getter for the parameter "author"
     *
     * @return the author of the thread
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Setter for the parameter "author"
     *
     * @param author the new author of the thread
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * Getter for the parameter "createdDate"
     *
     * @return the date of creation of the thread
     */
    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    /**
     * Setter for the parameter "createdDate"
     *
     * @param createdDate the new date of creation of the thread
     */
    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    /**
     * Getter for the parameter "forumMessages"
     *
     * @return the list of messages of the thread
     */
    public List<ForumMessage> getForumMessages()
    {
        return forumMessages;
    }

    /**
     * Setter for the parameter "forumMessages"
     *
     * @param forumMessages the new list of messages of the thread
     */
    public void setForumMessages(List<ForumMessage> forumMessages)
    {
        this.forumMessages = forumMessages;
    }
}
