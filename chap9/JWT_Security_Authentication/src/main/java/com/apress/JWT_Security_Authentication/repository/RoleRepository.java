package com.apress.JWT_Security_Authentication.repository;

import com.apress.JWT_Security_Authentication.models.Role;
import com.apress.JWT_Security_Authentication.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(RoleName roleName);


}
