package Panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

import Settings.OptionalSettings;

/**
 * Panel for the user to specify the functions he wants to display
 */
public class EvalPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected JButton btnSubmit;
    protected JTextField txtFunction;

    public EvalPanel() {
        this.setBackground(OptionalSettings.getMainColor());
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnSubmit = new JButton("Submit");
        txtFunction = new JTextField();

        JPanel fonctions_input = new JPanel();
        fonctions_input.setLayout(new GridBagLayout());
        fonctions_input.setBackground(OptionalSettings.getMainColor());
        GridBagConstraints constraint = new GridBagConstraints();

        btnSubmit.setBackground(OptionalSettings.getButtonsColor());
        btnSubmit.setUI((ButtonUI) MetalButtonUI.createUI(btnSubmit)); // pour mettre la couleur de fond du bouton
        txtFunction.setPreferredSize(new Dimension(200, 24));
        txtFunction.setBackground(OptionalSettings.getLabelForegroundColor());

        constraint.ipadx = 5;
        constraint.ipady = 5;
        constraint.gridx = 0;
        constraint.gridy = 0;

        // Add the label "f(x) =" to fonctions_input before the JTextField
        JLabel label = new JLabel("f(x) = ");
        fonctions_input.add(label, constraint);

        constraint.gridx = 1;
        fonctions_input.add(txtFunction, constraint);

        this.add(fonctions_input);
        this.add(btnSubmit);

        ActionListener action_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnSubmit)
                    actionBtnSubmit();
            }
        };

        btnSubmit.addActionListener(action_listener);

    }

    // Fonction appelée quand on appui sur le bouton éval
    public void actionBtnSubmit() {
        String expression = this.txtFunction.getText();

        if (!expression.isEmpty()) {
            System.out.println(expression);
        }
    }
}