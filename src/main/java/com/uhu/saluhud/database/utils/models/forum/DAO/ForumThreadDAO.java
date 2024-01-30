package com.uhu.saluhud.database.utils.models.forum.DAO;

import com.uhu.saluhud.database.utils.models.forum.ForumThread;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class ForumThreadDAO
{
    private final Session session;

    public ForumThreadDAO(Session session)
    {
        this.session = session;
    }

    public void saveForumThread(ForumThread forumThread)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(forumThread);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public ForumThread getForumThreadById(long id)
    {
        ForumThread selectedForumThread;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedForumThread = session.get(ForumThread.class, id);
            tx.commit();
            return selectedForumThread;
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateForumThread(ForumThread forumThread)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(forumThread);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteForumThread(ForumThread forumThread)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(forumThread);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
