package com.orejita.games.Enums;

public enum GameFavorite {
    YES(1),
    NO(0);

    private Integer favorite;

    private GameFavorite(int favorite) {
        this.favorite = favorite;
    }

}
