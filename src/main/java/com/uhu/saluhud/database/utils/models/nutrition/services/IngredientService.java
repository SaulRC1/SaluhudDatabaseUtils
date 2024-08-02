package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.IngredientRepository;
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
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;
    private final Session session;

    public IngredientService(Session session) {
        this.session = session;
    }

    public List<Ingredient> findAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    public void saveIngredient(Ingredient ingredient) {
        Transaction tx = session.beginTransaction();
        try {
            this.ingredientRepository.save(ingredient);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public Ingredient getIngredientById(long id) {
        Ingredient selectedIngredient;
        Transaction tx = session.beginTransaction();
        try {
            selectedIngredient = this.ingredientRepository.findOne(id);
            tx.commit();
            if (selectedIngredient == null) {
                return null;
            } else {
                return selectedIngredient;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }

    public Ingredient getIngredientByName(String name) {
        Ingredient selectedIngredient;
        Transaction tx = session.beginTransaction();
        try {
            selectedIngredient = this.ingredientRepository.findByName(name);
            tx.commit();
            if (selectedIngredient == null) {
                return null;
            } else {
                return selectedIngredient;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }

    public void updateIngredient(Ingredient ingredient) {
        Transaction tx = session.beginTransaction();
        try {
            this.ingredientRepository.save(ingredient);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        Transaction tx = session.beginTransaction();
        try {
            this.ingredientRepository.delete(ingredient);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
