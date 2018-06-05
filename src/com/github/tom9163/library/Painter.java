package com.github.tom9163.library;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.github.tom9163.library.setbackground.OUTPUT_PATH;

public class Painter {
    public static void paint() {
        Examination exam = ExaminationInformation.getExaminations().get(0);
        Examination gaoKao = new Examination("高考", "20190607");

        BufferedImage image = null;
        File pictureFile = new File("src/res/origin.png");
        if (pictureFile.canRead()) {
            try {
                Image src = ImageIO.read(pictureFile);
                int width = ((BufferedImage) src).getWidth();
                int height = ((BufferedImage) src).getHeight();
                image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphic = image.createGraphics();
                // 创建图像对象完毕

                graphic.drawImage(src, 0, 0, width, height, null);
                graphic.setColor(Color.WHITE);
                graphic.setFont(new Font("黑体", Font.BOLD, 48));
                graphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
                // 设置画笔完毕

                //下次大考名称
                paintName(graphic, exam.getName(), width, height, -30, 98);
                paintDate(graphic, exam.getDaysBetweenString(), width, height, 200, 110);
                paintDate(graphic, gaoKao.getDaysBetweenString(), width, height, 92, -115);

                graphic.dispose();
                // 绘图完毕

                ImageIO.write(image, "PNG", new File(OUTPUT_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.print("File can not read!");
            System.exit(1);
        }
    }

    private static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if ((text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }

    private static void paintName(Graphics2D graphic, String text, int width, int height, int x, int y) {
        graphic.drawString(text, (width - (getLength(text) * 48))
                / 2 + x, (height - 48) / 2 + y);
    }

    private static void paintDate(Graphics2D graphic, String text, int width, int height, int x, int y) {
        graphic.drawString(text, (width - (getLength(text) * 72))
                / 2 + x, (height - 72) / 2 + y);
    }
}
