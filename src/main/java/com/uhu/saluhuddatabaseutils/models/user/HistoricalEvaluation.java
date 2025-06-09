package com.uhu.saluhuddatabaseutils.models.user;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public enum HistoricalEvaluation
{
    EXCELLENT("EXCELLENT"), 
    WELL("WELL"), 
    MINIMUM("MINIMUM"), 
    FAILED("FAILED");
    
    private final String evaluationName;

    private HistoricalEvaluation(String evaluationName)
    {
        this.evaluationName = evaluationName;
    }

    public String getEvaluationName()
    {
        return evaluationName;
    }
    
    public static HistoricalEvaluation fromEvaluationName(String evaluationName)
    {
        for (HistoricalEvaluation value : HistoricalEvaluation.values())
        {
            if(value.getEvaluationName().equals(evaluationName))
            {
                return value;
            }
        }
        
        return null;
    }
}
