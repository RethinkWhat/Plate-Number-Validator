package platevalidator.frontend;

import utilities.Stylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

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

        // will not focus on text field immediately
        SwingUtilities.invokeLater(() -> {
            pnlCards.getComponent(0).requestFocusInWindow();
        });
    }

    class ValidatorPanel extends JPanel {
        private JTextField txtPlateNumber;
        private JButton btnValidate;
        private JLabel lblTitle1;
        private JLabel lblTitle2;
        private JLabel lblInvalid;

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

            txtPlateNumber = style.createPlateFieldRounded("Enter Plate Number", style.lightGray, style.gray, 17);
            txtPlateNumber.setHorizontalAlignment(JTextField.CENTER);
            txtPlateNumber.setPreferredSize(new Dimension(150, 40));
            gbc.gridy = 3;
            gbc.ipady = 40;
            add(txtPlateNumber, gbc);

            txtPlateNumber.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // This will clear when you start typing
                    if (txtPlateNumber.getText().equals("Enter Plate Number")) {
                        txtPlateNumber.setText("");
                        txtPlateNumber.setForeground(style.black);
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    // If the text is empty, it will set it back to "Enter Plate Number"
                    if (txtPlateNumber.getText().isEmpty()) {
                        txtPlateNumber.setText("Enter Plate Number");
                        txtPlateNumber.setForeground(style.gray);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // Prevent deletion if the current text is "Enter Plate Number"
                    if (txtPlateNumber.getText().equals("Enter Plate Number") && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        e.consume();
                    }
                }
            });

            lblInvalid = style.createLblP("", style.red);
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            add(lblInvalid, gbc);

            btnValidate = style.createBtnRounded("VALIDATE", style.celadon, style.white, 10);
            btnValidate.setPreferredSize(new Dimension(150, 20));
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(btnValidate, gbc);

            btnValidate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String plateNumber = txtPlateNumber.getText();
                    ValidPanel validPanel = (ValidPanel) pnlCards.getComponent(1);
                    validPanel.updatePlateNumber(plateNumber);
                    CardLayout cardLayout = (CardLayout) pnlCards.getLayout();
                    cardLayout.show(pnlCards, "valid");
                }
            });
        }
    }


    class ValidPanel extends JPanel {

        private JPanel pnlPlateNum;
        private JPanel pnlDetails;
        private JPanel pnlButton;
        private JLabel lblPlateNumberInput;

        public ValidPanel() {

            setLayout(new BorderLayout());
            setBackground(style.pickledBluewood);

            pnlPlateNum = new JPanel();
            pnlPlateNum.setPreferredSize(new Dimension(800, 120));
            pnlPlateNum.setBackground(style.pickledBluewood);

            JPanel containerPlateNum = style.createPnlRounded(250, 90, style.white, style.pickledBluewood);
            containerPlateNum.setBorder(new LineBorder(style.black, 10));
            containerPlateNum.setBorder(new EmptyBorder(10, 20, 20, 20));
            containerPlateNum.setLayout(new BorderLayout());
            lblPlateNumberInput = style.createLblPlate("", style.black);
            lblPlateNumberInput.setHorizontalAlignment(SwingConstants.CENTER);
            lblPlateNumberInput.setVerticalAlignment(SwingConstants.CENTER);
            containerPlateNum.add(lblPlateNumberInput, BorderLayout.CENTER);

            pnlPlateNum.add(containerPlateNum);

            pnlDetails = new JPanel();
            pnlDetails.setPreferredSize(new Dimension(800, 400));
            pnlDetails.setBackground(style.pickledBluewood);

            JPanel container = style.createPnlRounded(700, 350, style.white, style.pickledBluewood);
            container.setBorder(new EmptyBorder(20, 20, 20, 20));
            container.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Label when plate is valid
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel lblPlateIsValid = style.createLblH2("The entered plate number is VALID.", style.celadon);
            container.add(lblPlateIsValid, gbc);
            gbc.gridwidth = 1;

            // Vehicle Type
            gbc.gridy = 2;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicleType = style.createLblH2("Vehicle Type:", style.black);
            container.add(lblVehicleType, gbc);
            gbc.gridx = 1;
            JLabel lblVehicleTypeDesc = style.createLblP("Four Wheeled Vehicle", style.black);
            container.add(lblVehicleTypeDesc, gbc);

            // Classification
            gbc.gridy = 3;
            gbc.gridx = 0;
            JLabel lblClassification = style.createLblH2("Classification:", style.black);
            container.add(lblClassification, gbc);
            gbc.gridx = 1;
            JLabel lblClassificationDesc = style.createLblP("Hybrid Vehicle", style.black);
            container.add(lblClassificationDesc, gbc);

            // Region
            gbc.gridy = 4;
            gbc.gridx = 0;
            JLabel lblRegion = style.createLblH2("Region:", style.black);
            container.add(lblRegion, gbc);
            gbc.gridx = 1;
            JLabel lblRegionDesc = style.createLblP("National Capital Region (NCR)", style.black);
            container.add(lblRegionDesc, gbc);

            // Registration Date
            gbc.gridy = 5;
            gbc.gridx = 0;
            JLabel lblRegistrationDate = style.createLblH2("Registration Date:", style.black);
            container.add(lblRegistrationDate, gbc);
            gbc.gridx = 1;
            JLabel lblRegistrationDateDesc = style.createLblP("April, 8th to 14th", style.black);
            container.add(lblRegistrationDateDesc, gbc);


            pnlDetails.add(container);

            pnlButton = new JPanel();
            pnlButton.setBackground(style.pickledBluewood);
            pnlButton.setPreferredSize(new Dimension(800, 80));

            JButton btnClose = style.createBtnRounded("CLOSE", style.deepSkyBlue, style.white, 10);
            pnlButton.add(btnClose);
            btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(pnlCards, "validator");
                }
            });

            add(pnlPlateNum, BorderLayout.NORTH);
            add(pnlDetails, BorderLayout.CENTER);
            add(pnlButton, BorderLayout.SOUTH);

        }

        public void updatePlateNumber(String plateNumber) {
            lblPlateNumberInput.setText(plateNumber);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlateValidatorView view = new PlateValidatorView();
        });
    }
}
