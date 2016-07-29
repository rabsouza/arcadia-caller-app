package br.com.battista.arcadiacaller.util;

import br.com.battista.arcadiacaller.R;

public class CardUtils {

    private CardUtils() {
    }

    public static int getCardCostRes(Integer cost) {
        int resCost = R.drawable.cost_none;
        if (cost != null) {
            switch (cost) {
                case 0:
                    resCost = R.drawable.cost_0;
                    break;
                case 1:
                    resCost = R.drawable.cost_1;
                    break;
                case 2:
                    resCost = R.drawable.cost_2;
                    break;
                case 3:
                    resCost = R.drawable.cost_3;
                    break;
                case 4:
                    resCost = R.drawable.cost_4;
                    break;
                case 5:
                    resCost = R.drawable.cost_5;
                    break;
                case 6:
                    resCost = R.drawable.cost_6;
                    break;
                case 7:
                    resCost = R.drawable.cost_7;
                    break;
                case 8:
                    resCost = R.drawable.cost_8;
                    break;
                case 9:
                    resCost = R.drawable.cost_9;
                    break;
                default:
                    resCost = R.drawable.cost_0;
            }
        }
        return resCost;
    }

}
