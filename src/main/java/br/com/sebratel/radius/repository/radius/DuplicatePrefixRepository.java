package br.com.sebratel.radius.repository.radius;

import br.com.sebratel.radius.model.RadiusContract;
import br.com.sebratel.radius.repository.radius.projections.DuplicatePrefixProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuplicatePrefixRepository extends JpaRepository<RadiusContract, Long> {

    @Query(value = """
        SELECT 
            username, 
            COUNT(DISTINCT LEFT(callingstationid, 10)) AS countCallingstation 
        FROM public.radacct_convidado 
        WHERE acctstoptime IS NULL 
        AND acctinputoctets <> 0 
        GROUP BY username 
        HAVING COUNT(DISTINCT LEFT(callingstationid, 10)) > 1 
        ORDER BY username ASC
        """, nativeQuery = true)
    List<DuplicatePrefixProjection> findDuplicatePrefixes();
}