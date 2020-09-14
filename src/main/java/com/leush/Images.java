package com.leush;

import java.util.Arrays;
import java.util.List;

public interface Images {
    String CONGRATS = "image/congratulations.png";
    String OK = "image/ok.png";
    String MINESWEEPER = "image/saper.png";//todo rename image
    String LOSE_SMILE = "image/smile_lose.jpg";
    String PLAY_SMILE = "image/smile_play.jpg";
    String WIN_SMILE = "image/smile_win.jpg";

    static List<String> getImageNames(){
        return Arrays.asList(CONGRATS, OK, MINESWEEPER, LOSE_SMILE, PLAY_SMILE, WIN_SMILE);
    }
}
