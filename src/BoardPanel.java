import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BoardPanel extends JPanel {
    private Board board;
    private PieceButton[] pieceButtons;

    private Image boardGridImage;

    private Game game;

    private String selectedPosition;
    public BoardPanel(Board newBoard, Game newGame) {


        board = newBoard;
        game = newGame;
        selectedPosition = null;
        setPreferredSize(new Dimension(500, 500));
        URL image = BoardPanel.class.getClassLoader().getResource("boardgrid.png");
        ImageIcon img = new ImageIcon(image);
        img.setImage(img.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));

        boardGridImage = img.getImage();

        pieceButtons = new PieceButton[24];


        for(int i = 0; i < pieceButtons.length; i++){
            String position = Character.toString((char) ('A' + i));
            Piece piece = newBoard.getPiece(position);

            pieceButtons[i] = new PieceButton(piece.getType(),position);
            pieceButtons[i].setPreferredSize(new Dimension(40, 40));
            pieceButtons[i].setBorderPainted(false);
            pieceButtons[i].setContentAreaFilled(false);
            pieceButtons[i].setFocusPainted(false);
            pieceButtons[i].setOpaque(false);
            pieceButtons[i].setPosition(position);
            pieceButtons[i].setPieceType(piece.getType());

            pieceButtons[i].addActionListener(e -> {
                handleButtonClick(position);
            });


            add(pieceButtons[i]);

        }


        pieceButtons[0].setBounds(40, 40, 40, 40);
        pieceButtons[1].setBounds(232, 40, 40, 40);
        pieceButtons[2].setBounds(420, 40, 40, 40);

        pieceButtons[3].setBounds(100, 100, 40, 40);
        pieceButtons[4].setBounds(232, 100, 40, 40);
        pieceButtons[5].setBounds(364, 100, 40, 40);

        pieceButtons[6].setBounds(155, 155, 40, 40);
        pieceButtons[7].setBounds(232, 155, 40, 40);
        pieceButtons[8].setBounds(309, 155, 40, 40);

        pieceButtons[9].setBounds(40, 232, 40, 40);
        pieceButtons[10].setBounds(100, 232, 40, 40);
        pieceButtons[11].setBounds(155, 232, 40, 40);

        pieceButtons[12].setBounds(309, 232, 40, 40);
        pieceButtons[13].setBounds(364, 232, 40, 40);
        pieceButtons[14].setBounds(420, 232, 40, 40);

        pieceButtons[15].setBounds(155, 309, 40, 40);
        pieceButtons[16].setBounds(232, 309, 40, 40);
        pieceButtons[17].setBounds(309, 309, 40, 40);

        pieceButtons[18].setBounds(100, 364, 40, 40);
        pieceButtons[19].setBounds(232, 364, 40, 40);
        pieceButtons[20].setBounds(364, 364, 40, 40);

        pieceButtons[21].setBounds(40, 420, 40, 40);
        pieceButtons[22].setBounds(232, 420, 40, 40);
        pieceButtons[23].setBounds(420, 420, 40, 40);



    }

    public String getSelectedPosition() {
        return selectedPosition;
    }


    private void handleButtonClick(String position)
    {
        System.out.println("Button clicked: " + position);
        selectedPosition = position;
        //game.handleButtonClick(position);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardGridImage, 0, 0, null);
    }
}

