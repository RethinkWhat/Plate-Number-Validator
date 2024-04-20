package platevalidator.frontend;

import utilities.Stylesheet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlateValidatorView extends JFrame {

    private CardLayout cardLayout;
    private JPanel pnlCards;
    private Stylesheet style = new Stylesheet();

    public PlateValidatorView() {
        super("Plate Number Validator");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        pnlCards = new JPanel(cardLayout = new CardLayout());
        pnlCards.add(new ValidatorPanel(), "validator");
        pnlCards.add(new ValidPanel(), "valid");

        contentPane.add(pnlCards, BorderLayout.CENTER);

        this.pack();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    class ValidatorPanel extends JPanel {
        private JTextField txtPlateNumber;
        private JButton btnValidate;
        private JLabel lblTitle1;
        private JLabel lblTitle2;
        private JLabel lblErrorMessage;

        public ValidatorPanel() {
            setBackground(style.pickledBluewood);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            lblTitle1 = style.createLblH1("PLATE NUMBER", Color.WHITE);
            lblTitle1.setFont(lblTitle1.getFont().deriveFont(Font.BOLD, 50));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5, 0, 5, 0);
            add(lblTitle1, gbc);

            lblTitle2 = style.createLblH1("VALIDATOR", Color.WHITE);
            lblTitle2.setFont(lblTitle2.getFont().deriveFont(Font.BOLD, 50));
            gbc.gridy = 1;
            add(lblTitle2, gbc);

            txtPlateNumber = style.createPlateFieldRounded("Enter Plate Number", style.lightGray, style.black, 17);
            txtPlateNumber.setHorizontalAlignment(JTextField.CENTER);
            txtPlateNumber.setPreferredSize(new Dimension(150, 40));
            gbc.gridy = 3;
            gbc.ipady = 40;
            add(txtPlateNumber, gbc);

            lblErrorMessage = style.createLblP("", style.red);
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            add(lblErrorMessage, gbc);

            btnValidate = style.createBtnRounded("VALIDATE", style.celadon, style.white, 10);
            btnValidate.setPreferredSize(new Dimension(150, 20));
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(btnValidate, gbc);
        }
    }



    class ValidPanel extends JPanel {
        public ValidPanel() {
            setBackground(style.celadon);


        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateValidatorView view = new PlateValidatorView();
        });
    }
}
