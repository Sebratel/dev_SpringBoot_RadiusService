package br.com.sebratel.radius.repository.radius;

import br.com.sebratel.radius.model.RadiusContract;
import br.com.sebratel.radius.repository.radius.projections.FirstAuthenticationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstAuthenticationRepository extends JpaRepository<RadiusContract, Long> {

    @Query(value = """
        WITH CTE AS (
            SELECT 
                rc.username AS pppoe, 
                rc.acctstarttime AS authentication,
                RANK() OVER (PARTITION BY rc.username ORDER BY rc.acctstarttime ASC) AS rank
            FROM public.radacct_convidado rc
            WHERE DATE(rc.acctstarttime) > '2025-04-01'
        )
        SELECT 
            pppoe,
            authentication
        FROM CTE
        WHERE rank = 1
        """, nativeQuery = true)
    List<FirstAuthenticationProjection> findFirstAuthentications();
}