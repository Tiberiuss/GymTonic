package com.gym.GymTonic.model;

public enum Muscle {
    BICEPS,
    FOREARMS,
    SHOULDERS,
    TRICEPS,
    QUADS,
    GLUTES,
    LATS,
    MID_BACK,
    LOWER_BACK,
    HAMSTRINGS,
    CHEST,
    ABDOMINALS,
    OBLIQUES,
    TRAPS,
    CALVES;

    public static Muscle fromString(String s) {
        return Muscle.valueOf(s.trim().replace(" ", "_"));
    }
}
