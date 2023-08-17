package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents the existent allergenics present in food.
 */
@Entity
public class Allergenic
{
    @Id
    private long id;
    
    @Column
    private String name;
}
