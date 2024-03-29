package views;

import settings.ColorPalette;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

/**
 * Panel for the user to specify the functions he wants to display
 */
public class EvalPanel extends JPanel {

    private JButton btnSubmit;
    private JTextField txtFunction;
    private ActionListener submitListener;

    public EvalPanel(ActionListener submitListener) {
        this.submitListener = submitListener;
        this.setBackground(ColorPalette.getMainColor());
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnSubmit = new JButton("Submit");
        txtFunction = new JTextField();

        JPanel inputFunction = new JPanel();
        inputFunction.setLayout(new GridBagLayout());
        inputFunction.setBackground(ColorPalette.getMainColor());
        GridBagConstraints constraint = new GridBagConstraints();

        btnSubmit.setBackground(ColorPalette.getButtonsColor());
        btnSubmit.setUI((ButtonUI) MetalButtonUI.createUI(btnSubmit)); // pour mettre la couleur de fond du bouton
        txtFunction.setPreferredSize(new Dimension(200, 24));
        txtFunction.setBackground(ColorPalette.getLabelForegroundColor());

        constraint.ipadx = 5;
        constraint.ipady = 5;
        constraint.gridx = 0;
        constraint.gridy = 0;

        // Add the label "f(x) =" to inputFunction before the JTextField
        JLabel label = new JLabel("f(x) = ");
        inputFunction.add(label, constraint);

        constraint.gridx = 1;
        inputFunction.add(txtFunction, constraint);

        this.add(inputFunction);
        this.add(btnSubmit);

        ActionListener submitClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnSubmit)
                    actionBtnSubmit();
            }
        };

        btnSubmit.addActionListener(submitClick);

    }

    // Listener for click on submit
    public void actionBtnSubmit() {
        String expression = this.txtFunction.getText();
        if (!expression.isEmpty()) {
            if (submitListener != null) {
                submitListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Submit"));
            }
        }
    }

    public String getExpression() {
        String expression = this.txtFunction.getText();
        return expression;
    }

    public void clearFunction() {
        txtFunction.setText("");
    }
}
