package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.nutrition.DAO.IngredientDAO;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudIngredientsTest
{

    @Test
    public void testIngredientCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();

        try ( Session session = adminSessionFactory.openSession())
        {
            IngredientDAO ingredientDAO = new IngredientDAO(session);

            Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
            Ingredient carneDeRes = new Ingredient("Carne de Res", 250, 26, 0, 17);
            Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);
            Ingredient lechuga = new Ingredient("Lechuga", 5, 1, 2, 0);

            ingredientDAO.saveIngredient(harina);
            ingredientDAO.saveIngredient(carneDeRes);
            ingredientDAO.saveIngredient(huevo);
            ingredientDAO.saveIngredient(lechuga);
            
            Ingredient ingredientSelectedById = ingredientDAO.getIngredientById(harina.getId());
            assertEquals(ingredientSelectedById.getName(), "Harina");

            Ingredient ingredientSelectedByName = ingredientDAO.getIngredientByName("Huevo");
            assertEquals(ingredientSelectedByName.getName(), "Huevo");

            harina.setCarbohydrates_amount(75);
            ingredientDAO.updateIngredient(harina);
            ingredientDAO.deleteIngredient(lechuga);
            
            adminBootstrapper.closeSessionFactory();
        }
    }
}
