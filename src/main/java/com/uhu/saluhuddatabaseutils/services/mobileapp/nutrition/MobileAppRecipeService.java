package com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.localization.NutritionLocaleProvider;
import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import com.uhu.saluhuddatabaseutils.models.nutrition.AllergenicEnum;
import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition.MobileAppRecipeRepository;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
//By default all methods will have readonly = true since most methods will be read-only
@Transactional(readOnly = true, transactionManager = "saluhudMobileAppTransactionManager")
public class MobileAppRecipeService
{
    @Autowired
    private MobileAppRecipeRepository mobileAppRecipeRepository;
    
    @Autowired
    private NutritionLocaleProvider nutritionLocaleProvider;

    public List<Recipe> findAll()
    {
        return this.mobileAppRecipeRepository.findAll();
    }

    public List<Recipe> findRecipesPaginated(int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Recipe> recipesPaginated = this.mobileAppRecipeRepository.findAll(pageable);

        if(recipesPaginated == null)
        {
            return Collections.emptyList();
        }

        return recipesPaginated.toList();
    }

    public List<Recipe> findRecipesPaginatedWithFilters(int page, int pageSize,
            int minimumKilocalories, int maximumKilocalories, List<AllergenicEnum> excludedAllergenics,
            String filterRecipeName, Locale locale)
    {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Recipe> recipesPaginated = this.mobileAppRecipeRepository.findAllWithFilters(minimumKilocalories, maximumKilocalories, pageable);

        if(recipesPaginated == null)
        {
            return Collections.emptyList();
        }

        if((excludedAllergenics == null || excludedAllergenics.isEmpty()) 
                && (filterRecipeName == null || filterRecipeName.trim().isBlank()))
        {
            return recipesPaginated.toList();
        }

        List<Recipe> recipes = recipesPaginated.toList();

        if(filterRecipeName == null || filterRecipeName.trim().isBlank())
        {
            return filterOnlyAllergenics(recipes, excludedAllergenics);
        }

        return filterByNameAndAllergenics(recipes, filterRecipeName, excludedAllergenics, locale);
    }

    private List<Recipe> filterOnlyAllergenics(List<Recipe> recipes, List<AllergenicEnum> excludedAllergenics)
    {
        List<Recipe> filteredRecipes = recipes.stream().filter(recipe ->
        {
            Set<Allergenic> recipeAllergenics = recipe.getAllergenics();

            if(recipeAllergenics == null || recipeAllergenics.isEmpty())
            {
                return true;
            }

            for (Allergenic recipeAllergenic : recipeAllergenics)
            {
                for (AllergenicEnum excludedAllergenic : excludedAllergenics)
                {
                    if(recipeAllergenic.getId() == excludedAllergenic.getAllergenicCode())
                    {
                        return false;
                    }
                }
            }

            return true;
        }).toList();

        return filteredRecipes;
    }

    private List<Recipe> filterByNameAndAllergenics(List<Recipe> recipes, String filterRecipeName,
            List<AllergenicEnum> excludedAllergenics, Locale locale)
    {
        List<Recipe> filteredRecipes = recipes.stream().filter(recipe ->
        {
            Set<Allergenic> recipeAllergenics = recipe.getAllergenics();

            if (recipeAllergenics == null || recipeAllergenics.isEmpty())
            {
                String recipeTranslatedName = nutritionLocaleProvider.getTranslation(recipe.getName(), 
                        NutritionLocaleProvider.RECIPES_TRANSLATION_BUNDLE_PREFIX, locale);
                
                return recipeTranslatedName.trim().toLowerCase(locale).contains(filterRecipeName.trim().toLowerCase(locale));
            }

            for (Allergenic recipeAllergenic : recipeAllergenics)
            {
                for (AllergenicEnum excludedAllergenic : excludedAllergenics)
                {
                    if (recipeAllergenic.getId() == excludedAllergenic.getAllergenicCode())
                    {
                        return false;
                    }
                }
            }

            String recipeTranslatedName = nutritionLocaleProvider.getTranslation(recipe.getName(), 
                        NutritionLocaleProvider.RECIPES_TRANSLATION_BUNDLE_PREFIX, locale);
                
            return recipeTranslatedName.trim().toLowerCase(locale).contains(filterRecipeName.trim().toLowerCase(locale));
        }).toList();

        return filteredRecipes;
    }
    
    public Optional<Recipe> findById(Long id)
    {
        return this.mobileAppRecipeRepository.findById(id);
    }
    
    public List<Recipe> findMostKaloricRecipesPaginated(int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Recipe> recipesPaginated = this.mobileAppRecipeRepository.findMostKaloricRecipes(pageable);

        if(recipesPaginated == null)
        {
            return Collections.emptyList();
        }

        return recipesPaginated.toList();
    }
    
    public List<Recipe> findLessKaloricRecipesPaginated(int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Recipe> recipesPaginated = this.mobileAppRecipeRepository.findLessKaloricRecipes(pageable);

        if(recipesPaginated == null)
        {
            return Collections.emptyList();
        }

        return recipesPaginated.toList();
    }
}
