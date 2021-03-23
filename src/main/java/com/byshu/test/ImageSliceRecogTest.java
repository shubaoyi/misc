package com.byshu.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageSliceRecogTest {

    public static void main(String[] args) throws IOException {
        BufferedImage sliceImg = ImageIO.read(new File("D:/slice_1.png"));
        BufferedImage slideBg = ImageIO.read(new File("D:/slide_bg_1.png"));

        int maxX = sliceImg.getWidth();
        int maxY = sliceImg.getHeight();

        List<Coordinate> borderCodins = new ArrayList<>();
        // 获取缺口图片的边缘像素坐标集合
        for (int x = 1; x < maxX - 1; x++) {
            for (int y = 1; y < maxY - 1; y++) {
                // 获得处在图片四个边框上且有颜色的像素点
                if (x - 1 == 0 && sliceImg.getRGB(x - 1, y) != 0) {
                    borderCodins.add(new Coordinate(x - 1, y));
                }
                if (x + 1 == maxX - 1 && sliceImg.getRGB(x + 1, y) != 0) {
                    borderCodins.add(new Coordinate(x + 1, y));
                }
                if (y - 1 == 0 && sliceImg.getRGB(x, y - 1) != 0) {
                    borderCodins.add(new Coordinate(x, y - 1));
                }
                if (y + 1== maxY - 1 && sliceImg.getRGB(x, y + 1) != 0) {
                    borderCodins.add(new Coordinate(x, y + 1));
                }

                // 如果当前像素点的上下左右四个方向中任意一个方向的相邻像素点颜色值为0 则该像素点处在边缘上
                int curr = sliceImg.getRGB(x, y);
                int top = sliceImg.getRGB(x, y - 1);
                int bottom = sliceImg.getRGB(x, y + 1);
                int left = sliceImg.getRGB(x - 1, y);
                int right = sliceImg.getRGB(x + 1, y);
                if (curr != 0 && (top == 0 || bottom == 0 || left == 0 || right == 0)) {
                    borderCodins.add(new Coordinate(x, y));
                }
            }
        }

        //System.out.println(borderCodins.size());
        /*for (Coordinate o : borderCodins) {
            System.out.println(o.getX() + "," + o.getY());
        }

        List<Coordinate> newCodins1  = getBorderCodinsInSlideBg(borderCodins, 334, 114, 25, 25);
        for (Coordinate o : newCodins1) {
            System.out.println(o.getX() + "," + o.getY());
        }
        if (1 == 1) return;*/

        /**
         * 以当前坐标为中心点，按缺口图片的边缘路径在背景图上选择像素点
         * 如果超过80%的像素点的颜色与其外相邻的像素点色差大于某个阈值
         * 则判定该中心点大概率就是背景图中缺口区域的中心点
         */
        int bgMaxX = slideBg.getWidth();
        int bgMaxY = slideBg.getHeight();
        // 色差阈值
        int chromatism = 5000000;
        // 起始中心点
        int sliceHalfWidth = maxX  / 2;
        int sliceHalfHeight = maxY  / 2;
        int startX = sliceHalfWidth - 1;
        int startY = sliceHalfHeight - 1;
        // 中心点集合
        List<Coordinate> result = new ArrayList<>();

        for (int i = startX + 1; i < bgMaxX - sliceHalfWidth - 1; i++) { // 初始值加一 避免边框的像素点在获取周围像素时下标越界
            for (int j = startY + 1; j < bgMaxY - sliceHalfHeight - 1; j++) {
                List<Coordinate> newCodins = getBorderCodinsInSlideBg(borderCodins, i, j, startX, startY);
                int count = 0;
                for (Coordinate e : newCodins) {
                    int curr = slideBg.getRGB(e.getX(), e.getY());
                    int top = slideBg.getRGB(e.getX(), e.getY() - 1);
                    int bottom = slideBg.getRGB(e.getX(), e.getY() + 1);
                    int left = slideBg.getRGB(e.getX() - 1, e.getY());
                    int right = slideBg.getRGB(e.getX() + 1, e.getY());
                    if (Math.abs(top - curr) > chromatism || Math.abs(bottom - curr) > chromatism
                            || Math.abs(left - curr) > chromatism || Math.abs(right - curr) > chromatism) {
                        count++;
                    }
                }
                if (count > borderCodins.size() * 0.8) { // 调整比例阈值 最理想的就是result中只有一个元素 即最精准的中心点坐标
                    result.add(new Coordinate(i, j));
                }
            }
        }

        for (Coordinate a : result) {
            System.out.println("中心点坐标 : " + a.getX() + ", " + a.getY());
        }
    }

    public static List<Coordinate> getBorderCodinsInSlideBg(List<Coordinate> borderCodins, int currX, int currY,
                                                    int startX, int startY) {
        List<Coordinate> result = new ArrayList<>();
        for (Coordinate e : borderCodins) {
            result.add(new Coordinate(currX - startX + e.getX(), currY - startY + e.getY()));
        }
        return result;
    }


    /**
     * @desc 获取sRGB中的RGB值（sRGB的高8位为alpha值）
     * @param sRGB
     * @return
     */
    public static int getRealRGB(int sRGB) {
        if (sRGB < 0) { // 补码先转为原码
            int fan = sRGB - 1;
            int yuan = fan ^ (Integer.MIN_VALUE + 1);
            return yuan & ((1 << 24) -1); // 高8位置为0
        } else {
            return sRGB & ((1 << 24) -1);
        }
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
