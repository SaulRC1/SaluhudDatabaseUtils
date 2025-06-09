package com.uhu.saluhuddatabaseutils.models.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Converter(autoApply = false)
public class HistoricalEvaluationConverter implements AttributeConverter<HistoricalEvaluation, String>
{

    @Override
    public String convertToDatabaseColumn(HistoricalEvaluation historicalEvaluation)
    {
        return historicalEvaluation != null ? historicalEvaluation.getEvaluationName() : null;
    }

    @Override
    public HistoricalEvaluation convertToEntityAttribute(String evaluationName)
    {
        return evaluationName != null ? HistoricalEvaluation.fromEvaluationName(evaluationName) : null;
    }

}
