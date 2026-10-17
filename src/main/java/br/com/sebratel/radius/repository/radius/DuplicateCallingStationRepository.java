package br.com.sebratel.radius.repository.radius;

import br.com.sebratel.radius.model.RadiusContract;
import br.com.sebratel.radius.repository.radius.projections.DuplicateCallingStationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuplicateCallingStationRepository extends JpaRepository<RadiusContract, Long> {

    @Query(value = """
        SELECT 
            username, 
            COUNT(DISTINCT callingstationid) AS uniqueCallingstationCount 
        FROM public.radacct_convidado 
        WHERE acctstoptime IS NULL 
        GROUP BY username 
        HAVING COUNT(DISTINCT callingstationid) > 1 
        ORDER BY username ASC
        """, nativeQuery = true)
    List<DuplicateCallingStationProjection> findDuplicateCallingStations();
}