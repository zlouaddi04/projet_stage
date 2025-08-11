package project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.model.Transaction;

@Repository
@Service
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
