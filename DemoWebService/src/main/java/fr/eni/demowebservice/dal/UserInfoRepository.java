package fr.eni.demowebservice.dal;

import fr.eni.demowebservice.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {



}
