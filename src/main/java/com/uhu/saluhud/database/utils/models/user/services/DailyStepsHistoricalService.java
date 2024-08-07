package com.uhu.saluhud.database.utils.models.user.services;

import com.uhu.saluhud.database.utils.models.user.repositories.DailyStepsHistoricalRepository;
import com.uhu.saluhud.database.utils.models.user.DailyStepsHistorical;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class DailyStepsHistoricalService {

    @Autowired
    private DailyStepsHistoricalRepository dailyStepsHistoricalRepository;

    private static final Logger logger = Logger.getLogger(DailyStepsHistoricalService.class.getName());

    /**
     * Guarda un nuevo historico de pasos diarios.
     * @param dailyStepsHistorical El historico de pasos diarios a guardar.
     */
    @Transactional
    public void saveDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical) {
        this.dailyStepsHistoricalRepository.save(dailyStepsHistorical);
    }

    /**
     * Actualiza un historico de pasos diarios existente.
     * @param dailyStepsHistorical El historico de pasos diarios a actualizar.
     */
    @Transactional
    public void updateDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical) {
        try {
            Optional<DailyStepsHistorical> result;

            result = this.dailyStepsHistoricalRepository.findById(dailyStepsHistorical.getId());

            if (result.get() != null) {
                result.get().setUser(dailyStepsHistorical.getUser());
                result.get().setEntries(dailyStepsHistorical.getEntries());

                this.dailyStepsHistoricalRepository.save(result.get());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating DailyStepsHistoricalEntry", e);
            throw e; 
        }
    }

    /**
     * Elimina una entrada de pasos diarios por su ID.
     * @param dailyStepsHistorical El historico de pasos diarios a eliminar.
     */
    @Transactional
    public void deleteDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical) {
        try {
            this.dailyStepsHistoricalRepository.delete(dailyStepsHistorical);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Encuentra todos los historiales de pasos diarios.
     *
     * @return Lista de todos los historiales de pasos diarios.
     */
    public List<DailyStepsHistorical> findAll() {
        return dailyStepsHistoricalRepository.findAll();
    }

    /**
     * Encuentra un historial de pasos diarios por su ID.
     *
     * @param id El ID del historial de pasos diarios.
     * @return El historial de pasos diarios, si se encuentra.
     */
    public Optional<DailyStepsHistorical> findById(Long id) {
        return dailyStepsHistoricalRepository.findById(id);
    }

    /**
     * Encuentra todos los historiales de un usuario específico.
     *
     * @param userId El ID del usuario.
     * @return Lista de historiales de pasos diarios del usuario.
     */
    public List<DailyStepsHistorical> findAllByUserId(Long userId) {
        return dailyStepsHistoricalRepository.findAllByUserId(userId);
    }

    /**
     * Encuentra historiales con un número mínimo de entradas.
     *
     * @param minEntries El número mínimo de entradas.
     * @return Lista de historiales de pasos diarios con al menos minEntries
     * entradas.
     */
    public List<DailyStepsHistorical> findAllWithMinEntries(int minEntries) {
        return dailyStepsHistoricalRepository.findAllWithMinEntries(minEntries);
    }

    /**
     * Encuentra todos los historiales junto con sus entradas para un usuario
     * específico.
     *
     * @param userId El ID del usuario.
     * @return Lista de historiales de pasos diarios del usuario con sus
     * entradas.
     */
    public List<DailyStepsHistorical> findAllWithEntriesByUserId(Long userId) {
        return dailyStepsHistoricalRepository.findAllWithEntriesByUserId(userId);
    }

    /**
     * Cuenta el número de historiales de pasos diarios para un usuario
     * específico.
     *
     * @param userId El ID del usuario.
     * @return El número de historiales de pasos diarios del usuario.
     */
    public int countByUserId(Long userId) {
        return dailyStepsHistoricalRepository.countByUserId(userId);
    }
}
