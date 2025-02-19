package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistorical;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalRepository;
import jakarta.validation.Valid;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class SaluhudAdminDailyStepsHistoricalService {

    @Autowired
    private SaluhudAdminDailyStepsHistoricalRepository dailyStepsHistoricalRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminDailyStepsHistoricalService.class.getName());

    /**
     * Guarda un nuevo historico de pasos diarios.
     *
     * @param dailyStepsHistorical El historico de pasos diarios a guardar.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical) {
        this.dailyStepsHistoricalRepository.save(dailyStepsHistorical);
    }

    /**
     * Actualiza un historico de pasos diarios existente.
     *
     * @param dailyStepsHistorical El historico de pasos diarios a actualizar.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical) {
        try {
            Optional<DailyStepsHistorical> result;

            result = this.dailyStepsHistoricalRepository.findById(dailyStepsHistorical.getId());

            if (result.isPresent()) {
                DailyStepsHistorical existingDailySteps = result.get();
                existingDailySteps.setUser(dailyStepsHistorical.getUser());
                existingDailySteps.setEntries(dailyStepsHistorical.getEntries());

                this.dailyStepsHistoricalRepository.save(existingDailySteps);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Elimina una entrada de pasos diarios por su ID.
     *
     * @param dailyStepsHistorical El historico de pasos diarios a eliminar.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical) {
        try {
            if (this.dailyStepsHistoricalRepository.existsById(dailyStepsHistorical.getId())) {
                this.dailyStepsHistoricalRepository.delete(dailyStepsHistorical);
            }

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
    public Optional<DailyStepsHistorical> findById(long id) {
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
