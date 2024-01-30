package com.uhu.saluhud.database.utils.models.forum;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.forum.DAO.ForumDAO;
import com.uhu.saluhud.database.utils.models.forum.DAO.ForumMessageDAO;
import com.uhu.saluhud.database.utils.models.forum.DAO.ForumThreadDAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudForumTest
{

    @Test
    public void testForumCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            ForumDAO forumDAO = new ForumDAO(session);
            ForumMessageDAO forumMessageDAO = new ForumMessageDAO(session);
            ForumThreadDAO forumThreadDAO = new ForumThreadDAO(session);

            List<ForumMessage> forumMessages = new ArrayList<>();
            List<ForumThread> forumThreads = new ArrayList<>();

            LocalDateTime now = LocalDateTime.now();

            Forum forum = new Forum("Forum 1", "This is a example for CRUD operations on DB");
            ForumThread forumThread_1 = new ForumThread("Title 1", "Juan", now, forum, forumMessages);
            forumThreads.add(forumThread_1);
            ForumMessage forumMessage_1 = new ForumMessage("Juan", now, "This a example message for CRUD operations", forumThread_1);
            forumMessages.add(forumMessage_1);           

            forumDAO.saveForum(forum);
            forumThreadDAO.saveForumThread(forumThread_1);
            forumMessageDAO.saveForumMessage(forumMessage_1);
            

            adminBootstrapper.closeSessionFactory();
        }
    }
}
