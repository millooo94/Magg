package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositiRepository extends JpaRepository<Depositi, Long> {
    @Query("SELECT d FROM Depositi d")
    List<Depositi> getDepositi();
}
