package com.uhu.saluhuddatabaseutils.services.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppDailyStepsHistoricalEntryRepository;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppDailyStepsHistoricalRepository;
import com.uhu.saluhuddatabaseutils.service.historical.DailyStepsHistoricalBusinessLogicService;
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
public class MobileAppDailyStepsHistoricalService
{
    @Autowired
    private MobileAppDailyStepsHistoricalRepository mobileAppDailyStepsHistoricalRepository;
    
    @Autowired
    private MobileAppDailyStepsHistoricalEntryRepository mobileAppDailyStepsHistoricalEntryRepository;
    
    @Autowired
    private DailyStepsHistoricalBusinessLogicService dailyStepsHistoricalBusinessLogicService;
    
    public Optional<DailyStepsHistorical> findDailyStepsHistoricalByUsername(String username)
    {
        return this.mobileAppDailyStepsHistoricalRepository.findByUsername(username);
    }
    
    public List<DailyStepsHistoricalEntry> findEntriesByDateRange(Long dailyStepsHistoricalId, LocalDate startDate, LocalDate endDate)
    {
        return this.mobileAppDailyStepsHistoricalEntryRepository.findByDateRange(dailyStepsHistoricalId, startDate, endDate);
    }
    
    public boolean existsEntryByDate(Long dailyStepsHistoricalId, LocalDate date)
    {
        return this.mobileAppDailyStepsHistoricalEntryRepository.existsByEntryDate(date, dailyStepsHistoricalId);
    }
    
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public DailyStepsHistoricalEntry registerNewEntry(Long dailyStepsHistoricalId, Integer numberOfSteps, LocalDate date, 
            Integer targetNumberOfSteps) throws Exception
    {
        Optional<DailyStepsHistorical> historicalOptional = this.mobileAppDailyStepsHistoricalRepository.findById(dailyStepsHistoricalId);
        
        if(historicalOptional.isEmpty())
        {
            throw new Exception("There is no daily steps historical with the specified id");
        }
        
        DailyStepsHistoricalEntry entry = this.dailyStepsHistoricalBusinessLogicService
                .buildEntry(historicalOptional.get(), numberOfSteps, date, targetNumberOfSteps);
        
        return this.mobileAppDailyStepsHistoricalEntryRepository.save(entry);
    }
}
