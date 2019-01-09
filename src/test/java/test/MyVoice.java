package test;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;

public class MyVoice {

    public static void main(String[] args) throws Exception {
        FileInputStream fileau = new FileInputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\Tauam01.wav");
        AudioStream as = new AudioStream(fileau);
        AudioPlayer.player.start(as);

    }
}
