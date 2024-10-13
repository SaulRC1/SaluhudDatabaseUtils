package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalEntryRepository;
import jakarta.validation.Valid;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class SaluhudAdminDailyStepsHistoricalEntryService {

    @Autowired
    private SaluhudAdminDailyStepsHistoricalEntryRepository dailyStepsHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminDailyStepsHistoricalEntryService.class.getName());

    /**
     * Guarda una nueva entrada de pasos diarios.
     *
     * @param dailyStepsHistoricalEntry La entrada de pasos diarios a guardar.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void saveDailyStepsHistoricalEntry(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry) {
        this.dailyStepsHistoricalEntryRepository.save(dailyStepsHistoricalEntry);
    }

    /**
     * Actualiza una entrada de pasos diarios existente.
     *
     * @param dailyStepsHistoricalEntry La entrada de pasos diarios a
     * actualizar.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void updateDailyStepsHistoricalEntry(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry) {
        try {
            Optional<DailyStepsHistoricalEntry> result;
            result = this.dailyStepsHistoricalEntryRepository.findById(dailyStepsHistoricalEntry.getId());

            if (result.isPresent()) {
                DailyStepsHistoricalEntry existingDailyStepsEntry = result.get();
                existingDailyStepsEntry.setDailyStepsHistorical(dailyStepsHistoricalEntry.getDailyStepsHistorical());
                existingDailyStepsEntry.setDoneSteps(dailyStepsHistoricalEntry.getDoneSteps());
                existingDailyStepsEntry.setEntryDate(dailyStepsHistoricalEntry.getEntryDate());
                existingDailyStepsEntry.setKiloCaloriesBurned(dailyStepsHistoricalEntry.getKiloCaloriesBurned());
                existingDailyStepsEntry.setStepEvaluation(dailyStepsHistoricalEntry.getStepEvaluation());

                this.dailyStepsHistoricalEntryRepository.save(existingDailyStepsEntry);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating DailyStepsHistoricalEntry", e);
            throw e; // Re-throw the exception to trigger rollback
        }
    }

    /**
     * Elimina una entrada de pasos diarios.
     *
     * @param dailyStepsHistoricalEntry La entrada del historico de pasos
     * diarios a eliminar.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void deleteDailyStepsHistorical(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry) {
        try {
            if (this.dailyStepsHistoricalEntryRepository.existsById(dailyStepsHistoricalEntry.getId())) {
                this.dailyStepsHistoricalEntryRepository.delete(dailyStepsHistoricalEntry);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Encuentra entradas por un rango de fechas.
     *
     * @param startDate La fecha de inicio.
     * @param endDate La fecha de fin.
     * @return Lista de entradas en el rango de fechas especificado.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByDateRange(LocalDate startDate, LocalDate endDate) {
        return dailyStepsHistoricalEntryRepository.findEntriesByDateRange(startDate, endDate);
    }

    /**
     * Encuentra entradas por evaluación de pasos.
     *
     * @param evaluation La evaluación de pasos.
     * @return Lista de entradas con la evaluación especificada.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByStepEvaluation(@Valid HistoricalEvaluation evaluation) {
        return dailyStepsHistoricalEntryRepository.findEntriesByStepEvaluation(evaluation);
    }

    /**
     * Encuentra el total de pasos en un rango de fechas.
     *
     * @param startDate La fecha de inicio.
     * @param endDate La fecha de fin.
     * @return El total de pasos en el rango de fechas especificado.
     */
    public int findTotalStepsInDateRange(LocalDate startDate, LocalDate endDate) {
        return dailyStepsHistoricalEntryRepository.findTotalStepsInDateRange(startDate, endDate);
    }

    /**
     * Encuentra el total de calorías quemadas en un rango de fechas.
     *
     * @param startDate La fecha de inicio.
     * @param endDate La fecha de fin.
     * @return El total de calorías quemadas en el rango de fechas especificado.
     */
    public double findTotalCaloriesBurnedInDateRange(LocalDate startDate, LocalDate endDate) {
        return dailyStepsHistoricalEntryRepository.findTotalCaloriesBurnedInDateRange(startDate, endDate);
    }

    /**
     * Encuentra entradas por ID de historial de pasos diarios.
     *
     * @param historicalId El ID del historial de pasos diarios.
     * @return Lista de entradas para el historial especificado.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByDailyStepsHistoricalId(long historicalId) {
        return dailyStepsHistoricalEntryRepository.findEntriesByDailyStepsHistoricalId(historicalId);
    }

    /**
     * Encuentra entradas con un número de pasos mayor a un valor especificado.
     *
     * @param steps El número de pasos mínimo.
     * @return Lista de entradas con un número de pasos mayor al valor
     * especificado.
     */
    public List<DailyStepsHistoricalEntry> findEntriesWithStepsGreaterThan(int steps) {
        return dailyStepsHistoricalEntryRepository.findEntriesWithStepsGreaterThan(steps);
    }

    /**
     * Encuentra todas las entradas históricas de pasos diarios por nombre de
     * usuario.
     *
     * @param username El nombre de usuario.
     * @return Lista de entradas históricas de pasos diarios para el usuario
     * especificado.
     */
    public List<DailyStepsHistoricalEntry> findAllByUserUsername(String username) {
        return dailyStepsHistoricalEntryRepository.findAllByUserUsername(username);
    }
}
