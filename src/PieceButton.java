import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.EventListener;

public class PieceButton extends JButton{

    private PieceType myPieceType;

    private boolean isButtonSelected;
    private String position;

    public PieceButton(PieceType pieceType, String position){
        this.myPieceType = pieceType;
        this.position = position;
        this.isButtonSelected = false;
        this.setPreferredSize(new Dimension(40, 40));


        if (myPieceType == PieceType.EMPTY) {
            URL image = BoardPanel.class.getClassLoader().getResource("emptytoken.png");

            ImageIcon  pieceImage = new ImageIcon(image);
            pieceImage.setImage(pieceImage.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(pieceImage);
        }
        else if (myPieceType == PieceType.RED){
            URL image = BoardPanel.class.getClassLoader().getResource("redtoken.png");
            ImageIcon  pieceImage = new ImageIcon(image);
            pieceImage.setImage(pieceImage.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(pieceImage);
        }

        else if (myPieceType == PieceType.BLUE) {
            URL image = BoardPanel.class.getClassLoader().getResource("bluetoken.png");
            ImageIcon  pieceImage = new ImageIcon(image);
            pieceImage.setImage(pieceImage.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(pieceImage);
        }


    }

    public void setPosition(String newPosition){
        position = newPosition;
    }

    public String getPosition() {
        return  position;
    }

    public void setIsButtonSelected(boolean selected){
        isButtonSelected = selected;
    }

    public boolean getIsButtonSelected() {
        return isButtonSelected;
    }

    public PieceType getPieceType() {
        return this.myPieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.myPieceType = pieceType;
    }




}
