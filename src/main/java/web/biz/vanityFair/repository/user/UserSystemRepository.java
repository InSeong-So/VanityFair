package web.biz.vanityFair.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import web.biz.vanityFair.domain.user.UserSystem;

public interface UserSystemRepository extends JpaRepository<UserSystem, Long>
{
    // 유저 아이디 존재여부 확인
}
