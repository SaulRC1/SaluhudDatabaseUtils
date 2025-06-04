package com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Menu;
import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDay;
import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDayRecipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.WeekDayEnum;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition.MobileAppMenuDayRecipeRepository;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition.MobileAppMenuDayRepository;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition.MobileAppMenuRepository;
import com.uhu.saluhuddatabaseutils.services.mobileapp.user.MobileAppSaluhudUserService;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
//By default all methods will have readonly = true since most methods will be read-only
@Transactional(readOnly = true, transactionManager = "saluhudMobileAppTransactionManager")
public class MobileAppMenuService 
{
    @Autowired
    private MobileAppMenuRepository mobileAppMenuRepository;
    
    @Autowired
    private MobileAppMenuDayRepository mobileAppMenuDayRepository;
    
    @Autowired
    private MobileAppMenuDayRecipeRepository mobileAppMenuDayRecipeRepository;
    
    @Autowired
    private MobileAppSaluhudUserService mobileAppSaluhudUserService;
    
    public List<Menu> findAll()
    {
        return this.mobileAppMenuRepository.findAll();
    }
    
    public Optional<Menu> findMenuById(long menuId)
    {
        return this.mobileAppMenuRepository.findById(menuId);
    }
    
    public List<Menu> findByUsername(String username)
    {
        List<Menu> menus = this.mobileAppMenuRepository.findByUsername(username);
        
        if(menus == null || menus.isEmpty())
        {
            return Collections.emptyList();
        }
        
        return menus;
    }
    
    public List<MenuDay> findMenuDaysByMenuId(long menuId)
    {
        Optional<Menu> menuOptional = this.mobileAppMenuRepository.findById(menuId);
        
        if(menuOptional.isEmpty())
        {
            return Collections.emptyList();
        }
        
        Menu menu = menuOptional.get();
        
        if(menu.getMenuDays() == null || menu.getMenuDays().isEmpty())
        {
            return Collections.emptyList();
        }
        
        return menu.getMenuDays();
    }
    
    public Optional<MenuDay> findMenuDayById(long menuDayId)
    {
        return this.mobileAppMenuDayRepository.findById(menuDayId);
    }
    
    public boolean existsMenuByNameForUsername(String menuName, String username)
    {
        return this.mobileAppMenuRepository.existsByNameForUsername(username, menuName);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public Menu saveMenuForUser(@Valid Menu menu, String username)
    {
        Optional<SaluhudUser> saluhudUserOptional = this.mobileAppSaluhudUserService.findByUsername(username);
        
        return saluhudUserOptional.map(user -> 
        {
           menu.setUserId(user.getId());
           return mobileAppMenuRepository.save(menu);
        })
        .orElseThrow(() -> new RuntimeException("User does not exist"));
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public Menu saveMenuDayForMenu(@Valid Menu menu, WeekDayEnum weekDay)
    {
        MenuDay menuDay = new MenuDay();
        menuDay.setMenu(menu);
        menuDay.setWeekDay(weekDay);
        menuDay.setMenuDayRecipes(null);
        
        menu.addMenuDay(menuDay);
        
        return this.mobileAppMenuRepository.save(menu);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public MenuDay saveMenuDayRecipeForMenuDay(@Valid MenuDay menuDay, MenuDayRecipe menuDayRecipe)
    {
        menuDay.addMenuDayRecipe(menuDayRecipe);
        
        return this.mobileAppMenuDayRepository.save(menuDay);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public MenuDay removeRecipeFromMenuDay(@Valid MenuDay menuDay, MenuDayRecipe menuDayRecipe)
    {
        menuDay.removeMenuDayRecipe(menuDayRecipe);
        
        return this.mobileAppMenuDayRepository.save(menuDay);
    }
    
    public Optional<MenuDayRecipe> findMenuDayRecipeById(Long id)
    {
        return this.mobileAppMenuDayRecipeRepository.findById(id);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public Menu removeMenuDayFromMenu(@Valid Menu menu, @Valid MenuDay menuDay)
    {
        menu.removeMenuDay(menuDay);
        
        return this.mobileAppMenuRepository.save(menu);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public void removeMenu(@Valid Menu menu)
    {
        this.mobileAppMenuRepository.delete(menu);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public void updateMenu(@Valid Menu menu)
    {
        this.mobileAppMenuRepository.save(menu);
    }
    
    @Transactional(readOnly = false, transactionManager = "saluhudMobileAppTransactionManager")
    public void setMenuAsFavouriteForUser(String username, Long menuId)
    {
        List<Menu> userMenus = this.findByUsername(username);
        
        if(userMenus.isEmpty())
        {
            return;
        }
        
        for (Menu userMenu : userMenus)
        {
            if(userMenu.getId() == menuId)
            {
                userMenu.setFavourite(true);
            }
            else
            {
                userMenu.setFavourite(false);
            }
        }
        
        this.mobileAppMenuRepository.saveAll(userMenus);
    }
    
    public Optional<Menu> findFavouriteMenuForUser(String username)
    {
        List<Menu> menuList = this.findByUsername(username);
        
        for (Menu menu : menuList)
        {
            if(menu.isFavourite())
            {
                return Optional.of(menu);
            }
        }
        
        return Optional.empty();
    }
}
