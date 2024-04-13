package com.example.AniTripper.models.enums;

public enum EducationLevel {
    Expert(1), Amateur(2), Beginner(3);

    private int value;

    EducationLevel(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
