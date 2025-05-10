package com.uhu.saluhuddatabaseutils.models.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Converter(autoApply = false)
public class BiologicalSexConverter implements AttributeConverter<BiologicalSex, String>
{

    @Override
    public String convertToDatabaseColumn(BiologicalSex biologicalSex)
    {
        return biologicalSex != null ? biologicalSex.getSexName() : null;
    }

    @Override
    public BiologicalSex convertToEntityAttribute(String sexName)
    {
        return sexName != null ? BiologicalSex.fromSexName(sexName) : null;
    }
    
}
