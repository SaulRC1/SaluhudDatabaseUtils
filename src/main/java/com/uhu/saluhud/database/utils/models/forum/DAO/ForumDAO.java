package com.uhu.saluhud.database.utils.models.forum.DAO;

import com.uhu.saluhud.database.utils.models.forum.Forum;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class ForumDAO
{
    private final Session session;

    public ForumDAO(Session session)
    {
        this.session = session;
    }

    public void saveForum(Forum forum)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(forum);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public Forum getForumById(long id)
    {
        Forum selectedForum;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedForum = session.get(Forum.class, id);
            tx.commit();
            return selectedForum;
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateForum(Forum forum)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(forum);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteForum(Forum forum)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(forum);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
