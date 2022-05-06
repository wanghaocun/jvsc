package com.example.designpatterns.StructuralPatterns.AdapterPattern;

/**
 * @author wanghaocun
 * @since 2022-05-06
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }

}
