package br.com.bill.of.sale.repository;

import br.com.bill.of.sale.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.cnpj = :cnpj")
    User findByCNPJ(@Param("cnpj") String cnpj);

    @Query(value = "select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

}
