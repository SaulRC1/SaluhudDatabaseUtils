package com.uhu.saluhud.database.utils.models.forum.DAO;

import com.uhu.saluhud.database.utils.models.forum.ForumMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class ForumMessageDAO
{

    private final Session session;

    public ForumMessageDAO(Session session)
    {
        this.session = session;
    }

    public void saveForumMessage(ForumMessage forumMessage)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(forumMessage);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public ForumMessage getForumMessageById(long id)
    {
        ForumMessage selectedForumMessage;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedForumMessage = session.get(ForumMessage.class, id);
            tx.commit();
            return selectedForumMessage;
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateForumMessage(ForumMessage forumMessage)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(forumMessage);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteForumMessage(ForumMessage forumMessage)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(forumMessage);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
