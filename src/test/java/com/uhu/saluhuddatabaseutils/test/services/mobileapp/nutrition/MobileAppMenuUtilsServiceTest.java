package com.uhu.saluhuddatabaseutils.test.services.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDay;
import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDayRecipe;
import com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition.MobileAppMenuUtilsService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class MobileAppMenuUtilsServiceTest 
{
    private MobileAppMenuUtilsService mobileAppMenuUtilsService = new MobileAppMenuUtilsService();
    
    @Test
    public void test_recipe_from_8_to_9_15_overlaps()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(9, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(9, 15));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(8, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(9, 15));
        
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_8_to_9_00_does_NOT_overlap()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(9, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(9, 15));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(8, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(9, 0));
        
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_8_to_8_45_does_NOT_overlap()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(9, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(9, 15));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(8, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(8, 45));
        
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_11_15_to_11_30_does_overlap()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(11, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(11, 30));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(11, 15));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(11, 30));
        
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_11_15_to_11_45_does_overlap()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(11, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(11, 30));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(11, 15));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(11, 45));
        
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_11_15_to_11_30_does_NOT_overlap()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(11, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(11, 15));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(11, 15));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(11, 30));
        
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_10_00_to_10_15_does_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(10, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(10, 15));
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_9_00_to_9_30_does_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(9, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(9, 30));
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_14_15_to_14_45_does_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(14, 15));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(14, 45));
        
        Assertions.assertFalse(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_8_00_to_9_00_does_NOT_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(8, 0));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(9, 0));
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_9_15_to_10_00_does_NOT_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(9, 15));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(10, 0));
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_10_45_to_11_45_does_NOT_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(10, 45));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(11, 45));
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    @Test
    public void test_recipe_from_19_30_to_20_30_does_NOT_overlap_within_list()
    {   
        MenuDay menuDayMock = getMenuDayMock();
        
        MenuDayRecipe newMenuDayRecipeMock = new MenuDayRecipe();
        newMenuDayRecipeMock.setStartTime(LocalTime.of(19, 30));
        newMenuDayRecipeMock.setEndTime(LocalTime.of(20, 30));
        
        Assertions.assertTrue(mobileAppMenuUtilsService.menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(menuDayMock, newMenuDayRecipeMock));
    }
    
    private static MenuDay getMenuDayMock()
    {
        List<MenuDayRecipe> menuDayRecipesMock = new ArrayList<>();
        
        MenuDayRecipe menuDayRecipeMock1 = new MenuDayRecipe();
        menuDayRecipeMock1.setStartTime(LocalTime.of(9, 0));
        menuDayRecipeMock1.setEndTime(LocalTime.of(9, 15));
        
        menuDayRecipesMock.add(menuDayRecipeMock1);
        
        MenuDayRecipe menuDayRecipeMock2 = new MenuDayRecipe();
        menuDayRecipeMock2.setStartTime(LocalTime.of(10, 0));
        menuDayRecipeMock2.setEndTime(LocalTime.of(10, 30));
        
        menuDayRecipesMock.add(menuDayRecipeMock2);
        
        MenuDayRecipe menuDayRecipeMock3 = new MenuDayRecipe();
        menuDayRecipeMock3.setStartTime(LocalTime.of(14, 30));
        menuDayRecipeMock3.setEndTime(LocalTime.of(15, 0));
        
        menuDayRecipesMock.add(menuDayRecipeMock3);
        
        MenuDay menuDayMock = new MenuDay();
        menuDayMock.setMenuDayRecipes(menuDayRecipesMock);
        
        return menuDayMock;
    }
}
