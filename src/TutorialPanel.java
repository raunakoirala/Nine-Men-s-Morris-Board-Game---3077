import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.*;

public class TutorialPanel extends JPanel {
    private Board board;

    private Image tutorialImage;

    private Game game;

    public TutorialPanel(Board tuteboard, Game newGame) {
        board = tuteboard;
        game = newGame;
        setPreferredSize(new Dimension(500, 500));
        URL image = TutorialPanel.class.getClassLoader().getResource("emptyGrid.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        tutorialImage = tutImg.getImage();
        //set image of empty board
    }
    public void placeTokenImage(){
        URL image = TutorialPanel.class.getClassLoader().getResource("placed_piece.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        //set image of placed piece
    }
    public void moveToB(){
        URL image = TutorialPanel.class.getClassLoader().getResource("movedB.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        // set image to moved piece
    }

    public void moveToJ(){
        URL image = TutorialPanel.class.getClassLoader().getResource("movedJ.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        // set image moved piece to j
    }

    public void showMill(){
        URL image = TutorialPanel.class.getClassLoader().getResource("showMill.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        // set image to mill scenario pre-mill
    }

    public void makeMill(){
        URL image = TutorialPanel.class.getClassLoader().getResource("makeMill.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        // set image to mill scenario post-mill
    }

    public void removePiece(){
        URL image = TutorialPanel.class.getClassLoader().getResource("removePiece.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        //set image to removed piece at j
    }
    public void tutComplete(){
        URL image = TutorialPanel.class.getClassLoader().getResource("tutorialComplete.png");
        ImageIcon tutImg = new ImageIcon(image);
        tutImg.setImage(tutImg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        tutorialImage = tutImg.getImage();
        repaint();
        // set image to complete tutorial
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tutorialImage, 0, 0, null);
    }
}