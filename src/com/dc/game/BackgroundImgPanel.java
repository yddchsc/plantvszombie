package com.dc.game;

import javax.swing.ImageIcon;
import javax.swing.JPanel;  

import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Toolkit;  
import java.awt.Image;  
  
public class BackgroundImgPanel extends JPanel{  
    private int width = 0;  
    private int height = 0;  
    private String imgPath = ""; 
      
    public BackgroundImgPanel(int _width,int _height,String _imgPath){  
        width = _width;  
        height = _height;  
        imgPath = _imgPath;  
        setSize(width,height);
        setVisible(true);  
    }  
      
    public BackgroundImgPanel(double _width,double _height,String _imgPath){  
        width = (int)_width;  
        height = (int)_height;  
        imgPath = _imgPath;  
        setSize(width,height);  
        setVisible(true);  
    }  
  
    @Override  
    public void paintComponent(Graphics gs) {  
        Graphics2D g = (Graphics2D) gs;  
        super.paintComponent(g);  
        //画背景图片 
        Image image = (new ImageIcon(getClass().getResource(imgPath))).getImage();
        gs.drawImage(image, 0, 0,width,height, this);
    }  
}  