package com.SchoolManagementSystem.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    TEACHER_READ("teacher:read"),
    TEACHER_UPDATE("teacher:read"),
    TEACHER_CREATE("teacher:read"),
    TEACHER_DELETE("teacher:read"),
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:read"),
    STUDENT_CREATE("student:read"),
    STUDENT_DELETE("student:read")
    ;

    @Getter
    private final String permission;

}
