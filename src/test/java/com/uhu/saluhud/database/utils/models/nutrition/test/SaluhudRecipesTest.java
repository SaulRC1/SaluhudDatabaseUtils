package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.IngredientDAO;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudRecipesTest
{

    /**
     *
     */
    @Test
    public void testRecipesIngredientCRUD()
    {

        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        try (SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory())
        {
            IngredientDAO ingredientDAO = new IngredientDAO(adminSessionFactory);
            Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
            Ingredient carneDeRes = new Ingredient("Carne de Res", 250, 26, 0, 17);
            Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);
            Ingredient lechuga = new Ingredient("Lechuga", 5, 1, 2, 0);
            ingredientDAO.saveIngredient(harina);
            ingredientDAO.saveIngredient(carneDeRes);
            ingredientDAO.saveIngredient(huevo);
            ingredientDAO.saveIngredient(lechuga);
            adminBootstrapper.closeSessionFactory();
        }
    }

    /**
     *
     */
    @Test
    public void testRecipesAllergenicCRUD()
    {

    }

    /**
     *
     */
    @Test
    public void testRecipesElaborationStepCRUD()
    {

    }
}
