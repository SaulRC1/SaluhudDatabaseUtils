package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.AllergenicDAO;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.IngredientDAO;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.RecipeDAO;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.RecipeElaborationStepDAO;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudRecipeElaborationStepsTest
{

    @Test
    public void testRecipeElaborationStepsTestCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            RecipeElaborationStepDAO recipeElaborationStepDAO = new RecipeElaborationStepDAO(session);
            RecipeDAO recipeDAO = new RecipeDAO(session);
            IngredientDAO ingredientDAO = new IngredientDAO(session);
            AllergenicDAO allergenicDAO = new AllergenicDAO(session);
                        
            List<RecipeElaborationStep> elaborationSteps = new ArrayList<>();
            List<Ingredient> ingredients = new ArrayList<>();
            Set<Allergenic> allergenics = new HashSet<>();

            Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
            Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);
            Allergenic leche = new Allergenic("Leche");
            RecipeElaborationStep batirHuevos = new RecipeElaborationStep("Batir los huevos bien, a mano o con la batidora durante 5 min", 1); 
            
            ingredientDAO.saveIngredient(harina);
            ingredientDAO.saveIngredient(huevo);
            allergenicDAO.saveAllergenic(leche);
                        
            ingredients.add(huevo);
            ingredients.add(harina);
            allergenics.add(leche);
            elaborationSteps.add(batirHuevos);
            
            Recipe Bizcocho = new Recipe("Bizcocho", "Bizcocho de limón al horno", "Huevos, harina, sal, azucar, agua, aceite", ingredients, allergenics, elaborationSteps);
                    
            recipeElaborationStepDAO.saveRecipeElaborationStep(batirHuevos);
            recipeDAO.saveRecipe(Bizcocho);     
            
            adminBootstrapper.closeSessionFactory();
        }
    }
}
