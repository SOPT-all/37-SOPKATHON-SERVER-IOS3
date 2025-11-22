package org.project.domain.user.dto.response;

import org.project.domain.user.entity.User;

public record UserResponse(
        Long id,
        String name
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName()
        );
    }
}
