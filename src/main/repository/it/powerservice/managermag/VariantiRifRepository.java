package it.powerservice.managermag;

import it.powerservice.managermag.keys.VariantiRifPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantiRifRepository extends JpaRepository<VariantiRif, VariantiRifPKId> {
    @Query("SELECT vr FROM VariantiRif vr")
    List<VariantiRif> getVariantiRif();
}
