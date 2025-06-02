package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "menu_day_recipe")
public class MenuDayRecipe 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name= "start_time")
    private LocalTime startTime;
    
    @Column(name= "end_time")
    private LocalTime endTime;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_day_id", nullable = false)
    private MenuDay menuDay;
    
    @Version
    @Column(name = "entity_version")
    private Long version;

    public MenuDayRecipe()
    {
    }

    public MenuDayRecipe(LocalTime startTime, LocalTime endTime, 
            Recipe recipe, MenuDay menuDay)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.recipe = recipe;
        this.menuDay = menuDay;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public LocalTime getEndTime()
    {
        return endTime;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public MenuDay getMenuDay()
    {
        return menuDay;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime)
    {
        this.endTime = endTime;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public void setMenuDay(MenuDay menuDay)
    {
        this.menuDay = menuDay;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.startTime);
        hash = 79 * hash + Objects.hashCode(this.endTime);
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
        final MenuDayRecipe other = (MenuDayRecipe) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime))
        {
            return false;
        }
        return Objects.equals(this.endTime, other.endTime);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuDayRecipe{");
        sb.append("id=").append(id);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", recipe=").append(recipe);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }

}
