package com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Menu;
import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDay;
import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDayRecipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.WeekDayEnum;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class MobileAppMenuUtilsService 
{
    public boolean menuContainsMenuDay(Menu menu, WeekDayEnum weekDayEnum)
    {
        List<MenuDay> menuDays = menu.getMenuDays();

        for (MenuDay menuDay : menuDays)
        {
            if (menuDay.getWeekDay() == weekDayEnum)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean menuDayRecipeDoesNotOverlapOtherRecipeTimeFrame(MenuDay menuDay, MenuDayRecipe newMenuDayRecipe)
    {
        List<MenuDayRecipe> menuDayRecipes = menuDay.getMenuDayRecipes();

        //Sort by start time ascending
        menuDayRecipes.sort(Comparator.comparing(MenuDayRecipe::getStartTime));
        
        //In this loop, all recipes coming from the original menu day
        //are sorted by the one that starts the sooner
        for (MenuDayRecipe menuDayRecipe : menuDayRecipes)
        {
            //If both recipes start at the same time then they will surely overlap
            //within each other's timeframe
            if(menuDayRecipe.getStartTime().equals(newMenuDayRecipe.getStartTime()))
            {
                return false;
            }
            
            //If the recipe starts after the new recipe then check if the new
            //recipe ends after the recipe that we are checking against's start
            //time, if that's the case they surely overlap.
            if(menuDayRecipe.getStartTime().isAfter(newMenuDayRecipe.getStartTime()))
            {
                if(newMenuDayRecipe.getEndTime().isAfter(menuDayRecipe.getStartTime()))
                {
                    return false;
                }
            }
            
            //If the recipe stars before the new recipe then check if the recipe
            //ends after the new recipe start time. If that's the case then they
            //are overlaping
            else if(menuDayRecipe.getStartTime().isBefore(newMenuDayRecipe.getStartTime()))
            {
                if(menuDayRecipe.getEndTime().isAfter(newMenuDayRecipe.getStartTime()))
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Optional<MenuDayRecipe> getUpcomingRecipeForMenu(Menu menu)
    {
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        DayOfWeek currentDayOfWeek = currentDateTime.getDayOfWeek();
        
        final WeekDayEnum currentWeekDay = WeekDayEnum.fromDayOfWeek(currentDayOfWeek);
        
        LocalTime currentTime = currentDateTime.toLocalTime();
        
        Optional<MenuDay> menuDayOptional = menu.getMenuDays().stream().filter(day -> day.getWeekDay() == currentWeekDay).findFirst();
        
        if(menuDayOptional.isEmpty())
        {
            return Optional.empty();
        }
        
        MenuDay menuDay = menuDayOptional.get();
        
        Optional<MenuDayRecipe> upcomingRecipeOptional 
                = menuDay.getMenuDayRecipes().stream().filter(menuDayRecipe -> 
                        menuDayRecipe.getStartTime().isBefore(currentTime) 
                                || menuDayRecipe.getEndTime().isAfter(currentTime)).findFirst();
        
        return upcomingRecipeOptional;
    }
}
