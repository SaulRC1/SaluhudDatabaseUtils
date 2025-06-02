package com.uhu.saluhuddatabaseutils.models.nutrition;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "menu")
public class Menu 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;
    
    @Version
    @Column(name = "entity_version")
    private Long version;
    
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MenuDay> menuDays;
    
    @Column(name = "user_id", nullable = false)
    private long userId;
    
    @Column(name = "favourite", nullable = false)
    private boolean favourite;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public List<MenuDay> getMenuDays()
    {
        return menuDays;
    }

    public void setMenuDays(List<MenuDay> menuDays)
    {
        this.menuDays = menuDays;
    }
    
    public void addMenuDay(MenuDay menuDay)
    {
        if(this.menuDays == null)
        {
            this.menuDays = new ArrayList<>();
        }
        
        menuDay.setMenu(this);
        this.menuDays.add(menuDay);
    }
    
    public void removeMenuDay(MenuDay menuDay)
    {
        if(this.menuDays == null || this.menuDays.isEmpty())
        {
            return;
        }
        
        this.menuDays.remove(menuDay);
        menuDay.setMenu(null);
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public boolean isFavourite()
    {
        return favourite;
    }

    public void setFavourite(boolean favourite)
    {
        this.favourite = favourite;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + (int) (this.userId ^ (this.userId >>> 32));
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
        final Menu other = (Menu) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.userId != other.userId)
        {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", version=").append(version);
        sb.append(", menuDays=").append(menuDays);
        sb.append(", userId=").append(userId);
        sb.append(", favourite=").append(favourite);
        sb.append('}');
        return sb.toString();
    }
}
