package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.RecipeRepository;
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
public class RecipeService
{
    @Autowired
    private RecipeRepository recipeRepository;

    private final Session session;

    public RecipeService(Session session)
    {
        this.session = session;
    }
    
    public List<Recipe> findAllRecipes() {
        return this.recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            this.recipeRepository.save(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void updateRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            this.recipeRepository.save(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            this.recipeRepository.delete(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public Recipe getRecipeById(long id)
    {
        Recipe selectedRecipe;
        Transaction tx = session.beginTransaction();
        try {
            selectedRecipe = this.recipeRepository.findOne(id);
            tx.commit();
            if (selectedRecipe == null) {
                return null;
            } else {
                return selectedRecipe;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }
    
    public Recipe getRecipeByName(String name) {
        Recipe selectedRecipe;
        Transaction tx = session.beginTransaction();
        try {
            selectedRecipe = this.recipeRepository.findByName(name);
            tx.commit();
            if (selectedRecipe == null) {
                return null;
            } else {
                return selectedRecipe;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }
}
