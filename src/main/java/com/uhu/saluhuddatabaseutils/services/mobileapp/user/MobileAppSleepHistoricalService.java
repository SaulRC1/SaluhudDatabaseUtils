package com.uhu.saluhuddatabaseutils.services.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.SleepHistorical;
import com.uhu.saluhuddatabaseutils.models.user.SleepHistoricalEntry;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppSleepHistoricalEntryRepository;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppSleepHistoricalRepository;
import com.uhu.saluhuddatabaseutils.service.historical.SleepHistoricalBusinessLogicService;
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
public class MobileAppSleepHistoricalService
{
    @Autowired
    private MobileAppSleepHistoricalRepository mobileAppSleepHistoricalRepository;
    
    @Autowired
    private MobileAppSleepHistoricalEntryRepository mobileAppSleepHistoricalEntryRepository;
    
    @Autowired
    private SleepHistoricalBusinessLogicService sleepHistoricalBusinessLogicService;
    
    public Optional<SleepHistorical> findSleepHistoricalByUsername(String username)
    {
        return this.mobileAppSleepHistoricalRepository.findByUsername(username);
    }
    
    public List<SleepHistoricalEntry> findEntriesByDateRange(Long sleepHistoricalId, LocalDate startDate, LocalDate endDate)
    {
        return this.mobileAppSleepHistoricalEntryRepository.findByDateRange(sleepHistoricalId, startDate, endDate);
    }
    
    public boolean existsEntryByDate(Long sleepHistoricalId, LocalDate date)
    {
        return this.mobileAppSleepHistoricalEntryRepository.existsByEntryDate(date, sleepHistoricalId);
    }
    
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public SleepHistoricalEntry registerNewEntry(Long sleepHistoricalId, LocalDate date, 
            Integer hoursSlept, Integer minutesSlept) throws Exception
    {
        Optional<SleepHistorical> historicalOptional = this.mobileAppSleepHistoricalRepository.findById(sleepHistoricalId);
        
        if(historicalOptional.isEmpty())
        {
            throw new Exception("There is no sleep historical with the specified id");
        }
        
        SleepHistoricalEntry entry = this.sleepHistoricalBusinessLogicService
                .buildEntry(historicalOptional.get(), date, hoursSlept, minutesSlept);
        
        return this.mobileAppSleepHistoricalEntryRepository.save(entry);
    }
}
