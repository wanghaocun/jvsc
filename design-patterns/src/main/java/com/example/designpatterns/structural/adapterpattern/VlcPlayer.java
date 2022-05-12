package com.example.designpatterns.structural.adapterpattern;

/**
 * @author wanghaocun
 * @since 2022-05-06
 */
public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // 什么也不做
    }

}
