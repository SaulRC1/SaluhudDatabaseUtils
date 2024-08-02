package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.AllergenicRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class AllergenicService {

    @Autowired
    private AllergenicRepository allergenicRepository;

    private final Session session;

    public AllergenicService(Session session) {
        this.session = session;
    }
    
    public List<Allergenic> findAllAllergenics() {
        return this.allergenicRepository.findAll();
    }

    public void saveAllergenic(Allergenic allergenic) {
        Transaction tx = session.beginTransaction();
        try {
            this.allergenicRepository.save(allergenic);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public Allergenic getAllergenicById(long id) {
        Allergenic selectedAllergenic;
        Transaction tx = session.beginTransaction();
        try {
            selectedAllergenic = this.allergenicRepository.findOne(id);
            tx.commit();
            if (selectedAllergenic == null) {
                return null;
            } else {
                return selectedAllergenic;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }
    
    public Allergenic getAllergenicByName(String name)
    {
        Allergenic selectedAllergenic;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedAllergenic = this.allergenicRepository.findByName(name);
            tx.commit();
            if (selectedAllergenic == null)
            {
                return null;
            } else
            {              
                return selectedAllergenic;
            }

        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateAllergenic(Allergenic allergenic) {
        Transaction tx = session.beginTransaction();
        try {
            this.allergenicRepository.save(allergenic);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void deleteAllergenic(Allergenic allergenic) {
        Transaction tx = session.beginTransaction();
        try {
            this.allergenicRepository.delete(allergenic);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
