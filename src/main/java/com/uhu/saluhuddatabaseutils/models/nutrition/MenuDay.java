package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "menu_day")
public class MenuDay 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "week_day", nullable = false)
    @Convert(converter = WeekDayEnumConverter.class)
    private WeekDayEnum weekDay;
    
    @Version
    @Column(name = "entity_version")
    private Long version;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Menu menu;
    
    @OneToMany(mappedBy = "menuDay", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MenuDayRecipe> menuDayRecipes;

    public long getId()
    {
        return id;
    }

    public WeekDayEnum getWeekDay()
    {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay)
    {
        this.weekDay = weekDay;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public List<MenuDayRecipe> getMenuDayRecipes()
    {
        return menuDayRecipes;
    }

    public void setMenuDayRecipes(List<MenuDayRecipe> menuDayRecipes)
    {
        this.menuDayRecipes = menuDayRecipes;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.weekDay);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final MenuDay other = (MenuDay) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return this.weekDay == other.weekDay;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuDay{");
        sb.append("id=").append(id);
        sb.append(", weekDay=").append(weekDay);
        sb.append(", version=").append(version);
        sb.append(", menuDayRecipes=").append(menuDayRecipes);
        sb.append('}');
        return sb.toString();
    }
    
    public void addMenuDayRecipe(MenuDayRecipe menuDayRecipe)
    {
        if(this.menuDayRecipes == null || this.menuDayRecipes.isEmpty())
        {
            this.menuDayRecipes = new ArrayList<>();
        }
        
        this.menuDayRecipes.add(menuDayRecipe);
    }
    
    public void removeMenuDayRecipe(MenuDayRecipe menuDayRecipe)
    {
        if(this.menuDayRecipes == null || this.menuDayRecipes.isEmpty())
        {
            return;
        }
        
        this.menuDayRecipes.remove(menuDayRecipe);
    }
}
