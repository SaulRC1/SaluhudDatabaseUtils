package com.uhu.saluhuddatabaseutils.models.nutrition;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum AllergenicEnum
{
    GLUTEN(1),
    CRUSTACEAN(2),
    EGG(3),
    FISH(4),
    PEANUT(5),
    SOYA_BEAN(6),
    CELERY(7),
    MUSTARD(8),
    SESAME_SEED(9),
    SULFUR_DIOXIDE_AND_SULPHITES(10),
    LUPIN_BEAN(11),
    NUTS(12),
    MOLLUSK(13),
    LACTOSE(14);
    
    private final int allergenicCode;

    private AllergenicEnum(int allergenicCode)
    {
        this.allergenicCode = allergenicCode;
    }

    public int getAllergenicCode()
    {
        return allergenicCode;
    }
    
    public static AllergenicEnum fromAllergenicCode(int allergenicCode)
    {
        switch(allergenicCode)
        {
            case 1:
                return GLUTEN;
            case 2:
                return CRUSTACEAN;
            case 3:
                return EGG;
            case 4:
                return FISH;
            case 5:
                return PEANUT;
            case 6:
                return SOYA_BEAN;
            case 7:
                return CELERY;
            case 8:
                return MUSTARD;
            case 9:
                return SESAME_SEED;
            case 10:
                return SULFUR_DIOXIDE_AND_SULPHITES;
            case 11:
                return LUPIN_BEAN;
            case 12:
                return NUTS;
            case 13:
                return MOLLUSK;
            case 14:
                return LACTOSE;
            default:
                return null;
        }
    }

    @Override
    public String toString()
    {
        return "AllergenicEnum{" + "allergenicCode=" + allergenicCode + '}';
    }
}
