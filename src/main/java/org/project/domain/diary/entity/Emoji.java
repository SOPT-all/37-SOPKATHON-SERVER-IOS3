package org.project.domain.diary.entity;

public enum Emoji {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    Five("five");

    private final String emoji;
    Emoji(String emoji) {
        this.emoji = emoji;
    }

    public static Emoji of(Emoji emoji) {
        switch (emoji) {
            case ONE:
                return ONE;
            case TWO:
                return TWO;
            case THREE:
                return THREE;
            case FOUR:
                return FOUR;
            case Five:
                return Five;
        }
        throw new IllegalArgumentException("이모지 값이 잘못되었습니다.");
    }
}
