package ru.bellintegrator.practice.userdocs.dao;

import ru.bellintegrator.practice.userdocs.model.UserDoc;

import java.util.List;

public interface UserDocDAO {

    Integer deleteUserDocs(Long userId);
    UserDoc getDocsByUser(Long userId);
}
