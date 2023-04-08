package com.orejita.games.Enums;

public enum GameStatus {
    NOT_PLAYED("not_played"),
    STARTED("started"),
    PLAYING("playing"),
    PLAYED("played"),
    DROPED("droped"),
    FINISHED("finished"),
    REPLAYING("replaying");

    private String status;

    private GameStatus(String status) {
        this.status = status;
    }

}
