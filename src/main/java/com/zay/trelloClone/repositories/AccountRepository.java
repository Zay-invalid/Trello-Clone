package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
