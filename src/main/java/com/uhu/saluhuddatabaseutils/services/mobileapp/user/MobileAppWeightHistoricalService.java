package com.uhu.saluhuddatabaseutils.services.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
import com.uhu.saluhuddatabaseutils.models.user.WeightHistorical;
import com.uhu.saluhuddatabaseutils.models.user.WeightHistoricalEntry;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppWeightHistoricalEntryRepository;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppWeightHistoricalRepository;
import com.uhu.saluhuddatabaseutils.service.historical.WeightHistoricalBusinessLogicService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
//By default all methods will have readonly = true since most methods will be read-only
@Transactional(readOnly = true, transactionManager = "saluhudMobileAppTransactionManager")
public class MobileAppWeightHistoricalService
{
    @Autowired
    private MobileAppWeightHistoricalRepository mobileAppWeightHistoricalRepository;
    
    @Autowired
    private MobileAppWeightHistoricalEntryRepository mobileAppWeightHistoricalEntryRepository;
    
    @Autowired
    private WeightHistoricalBusinessLogicService weightHistoricalBusinessLogicService;
    
    public Optional<WeightHistorical> findWeightHistoricalByUsername(String username)
    {
        return this.mobileAppWeightHistoricalRepository.findByUsername(username);
    }
    
    public List<WeightHistoricalEntry> findEntriesByDateRange(Long weightHistoricalId, LocalDate startDate, LocalDate endDate)
    {
        return this.mobileAppWeightHistoricalEntryRepository.findByDateRange(weightHistoricalId, startDate, endDate);
    }
    
    public boolean existsEntryByDate(Long weightHistoricalId, LocalDate date)
    {
        return this.mobileAppWeightHistoricalEntryRepository.existsByEntryDate(date, weightHistoricalId);
    }
    
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public WeightHistoricalEntry registerNewEntry(Long weightHistoricalId, LocalDate date, 
            Double weight, Double height, Integer kilocaloriesObjective) throws Exception
    {
        Optional<WeightHistorical> historicalOptional = this.mobileAppWeightHistoricalRepository.findById(weightHistoricalId);
        
        if(historicalOptional.isEmpty())
        {
            throw new Exception("There is no weight historical with the specified id");
        }
        
        WeightHistoricalEntry entry = this.weightHistoricalBusinessLogicService
                .buildEntry(historicalOptional.get(), date, weight, height, kilocaloriesObjective);
        
        return this.mobileAppWeightHistoricalEntryRepository.save(entry);
    }
}
