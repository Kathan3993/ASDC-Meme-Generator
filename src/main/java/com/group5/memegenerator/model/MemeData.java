package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeDAO;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class MemeData implements IMemeData {

    @Getter
    private String memePicture;

    private String topColour;

    private String bottomColour;

    @Getter
    @Setter
    private String topText;

    @Getter
    @Setter
    private String bottomText;

    @Getter
    @Setter
    private IMemeDAO memeDao;

    @Getter
    @Setter
    private float fontSize;

    @Getter
    @Setter
    private int topXCoordinate;

    @Getter
    @Setter
    private int topYCoordinate;

    @Getter
    @Setter
    private int bottomXCoordinate;

    @Getter
    @Setter
    private int bottomYCoordinate;

    public MemeData(String memePicture, String topColour, String bottomColour,
                    String topText, String bottomText,
                    float fontSize, int topXCoordinate, int topYCoordinate, int bottomXCoordinate, int bottomYCoordinate) {
        this.memePicture = memePicture;
        this.topColour = topColour;
        this.bottomColour = bottomColour;
        this.topText = topText;
        this.bottomText = bottomText;

        this.fontSize = fontSize;
        this.topXCoordinate = topXCoordinate;
        this.topYCoordinate = topYCoordinate;

        this.bottomXCoordinate = bottomXCoordinate;
        this.bottomYCoordinate = bottomYCoordinate;
    }

    public MemeData() {
    }

    public MemeData(IMemeDAO memeDao) {
        this.memeDao = memeDao;
    }

    public Color getTopColour() {
        switch (topColour) {
            case "black": {
                return Color.BLACK;
            }
            default: {
                return Color.WHITE;
            }
        }
    }

    public Color getBottomColour() {
        switch (bottomColour) {
            case "black": {
                return Color.BLACK;
            }
            default: {
                return Color.WHITE;
            }
        }
    }

    public String addText() {
        byte[] imageBytes = Base64.getDecoder().decode(getMemePicture().split(",")[1]);
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

            Graphics graphics = bufferedImage.getGraphics();
            graphics.setFont(graphics.getFont().deriveFont(getFontSize()));
            graphics.setColor(getTopColour());
            graphics.drawString(topText, getTopXCoordinate(), getTopYCoordinate());
            graphics.setColor(getBottomColour());
            graphics.drawString(bottomText, getBottomXCoordinate(), getBottomYCoordinate());

            graphics.setColor(Color.BLACK);
            int maxX = bufferedImage.getWidth() - 60;
            int maxY = bufferedImage.getHeight() - 10;
            graphics.drawString("\u00a9 MG", maxX, maxY);

            graphics.dispose();
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOS);
            byte[] modifiedImage = byteArrayOS.toByteArray();
            byte[] finalImage = Base64.getEncoder().encode(modifiedImage);
            return "data:image/jpeg;base64," + new String(finalImage);
        } catch (Exception e) {
            return null;
        }
    }

    public DatabaseResponse saveMeme(String username, String memeCategory, String memeId, String memeImage) {
        if (username == "") {
            return DatabaseResponse.ERROR;
        }
        return memeDao.saveMeme(username, memeCategory, memeId, memeImage);
    }
}
