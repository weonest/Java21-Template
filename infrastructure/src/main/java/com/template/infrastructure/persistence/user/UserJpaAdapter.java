package com.template.infrastructure.persistence.user;

import ai.han.support.exception.ErrorCode;
import com.github.f4b6a3.ulid.Ulid;
import com.template.domain.user.entity.User;
import com.template.domain.user.repository.UserRepository;
import com.template.infrastructure.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public User save(User user) {
        return jpaRepository.save(user);
    }

    public List<User> getAll() {
        return jpaRepository.findAll();
    }

    public User getByUserId(Ulid userId) {
        return jpaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ENTITY,
                        String.format("User(id: %s)를 찾을 수 없습니다.", userId)));
    }

}
