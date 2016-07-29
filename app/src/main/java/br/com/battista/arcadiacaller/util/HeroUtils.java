package br.com.battista.arcadiacaller.util;

import android.support.annotation.NonNull;

import br.com.battista.arcadiacaller.R;

public class HeroUtils {

    private HeroUtils() {}

    public static int getHeroLifeRes(@NonNull Integer life) {
        int resLife = R.drawable.life_0;
        switch (life) {
            case 1:
                resLife = R.drawable.life_1;
                break;
            case 2:
                resLife = R.drawable.life_2;
                break;
            case 3:
                resLife = R.drawable.life_3;
                break;
            case 4:
                resLife = R.drawable.life_4;
                break;
            case 5:
                resLife = R.drawable.life_5;
                break;
            case 6:
                resLife = R.drawable.life_6;
                break;
            default:
                resLife = R.drawable.life_0;
        }
        return resLife;
    }

    public static int getHeroDefenseRes(@NonNull Integer defense) {
        int resDefense = R.drawable.defense_0;
        switch (defense) {
            case 1:
                resDefense = R.drawable.defense_1;
                break;
            case 2:
                resDefense = R.drawable.defense_2;
                break;
            case 3:
                resDefense = R.drawable.defense_3;
                break;
            case 4:
                resDefense = R.drawable.defense_4;
                break;
            case 5:
                resDefense = R.drawable.defense_5;
                break;
            case 6:
                resDefense = R.drawable.defense_6;
                break;
            default:
                resDefense = R.drawable.defense_0;
        }
        return resDefense;
    }

}
