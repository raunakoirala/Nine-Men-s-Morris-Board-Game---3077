import javax.swing.*;
import java.awt.*;

public class PiecePanel extends JPanel {
    public PiecePanel(Piece piece) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        if (piece.getType() == PieceType.BLUE) {
            label.setText("*");
        } else if (piece.getType() == PieceType.RED) {
            label.setText("&");
        }
        add(label);
    }
}
