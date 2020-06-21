import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.text.DecimalFormat;
/*import static java.lang.Math.sin;
import static java.lang.Math.log;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;*/
import static java.lang.Math.*;

public class Okno extends JFrame
{
    private static final int WIDTH = 414;
    private static final int HEIGHT = 314;
    private JTextField textFieldX; private JTextField textFieldY; private JTextField textFieldZ;
    private JTextField textFieldXEX;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    int XEXid = 1;
    private Double sum = 0.0;
    private Double mem = new Double(0);
    public Double calculate1(Double x, Double y, Double z)
    { //return (sin(y*y*acos(-1)) + log(y*y))/(sin(z*z*acos(-1)) + sin(x) + log(z*z) + x*x + exp(cos(z*x)));
        return x + y + z;
    }
    public Double calculate2(Double x, Double y, Double z)
    { //return pow((y + x*x*x), 1/z)/log(z);
        return x*y*z;
    }
    private void addRadioButton(String buttonName, final int formulaId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
                                 {
                                     public void actionPerformed(ActionEvent ev)
                                     {
                                         Okno.this.formulaId = formulaId;
                                     }
                                 }
        );
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public Okno()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected( radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder( BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        JLabel XEX = new JLabel ("XEX: ");
        textFieldXEX = new JTextField("XEXE", 8);
        textFieldXEX.setMaximumSize(textFieldXEX.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder( BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize( textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalStrut(50));
        hboxResult.add(XEX);
        hboxResult.add(textFieldXEX);
        hboxResult.add(Box.createHorizontalStrut(78));
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener()
                                     { public void actionPerformed(ActionEvent ev)
                                     { try {
                                         Double x = Double.parseDouble(textFieldX.getText());
                                         Double y = Double.parseDouble(textFieldY.getText());
                                         Double z = Double.parseDouble(textFieldZ.getText());
                                         Double result;
                                         if (formulaId==1)
                                             result = calculate1(x, y, z);
                                         else
                                             result = calculate2(x, y, z);
                                         sum += result;
                                         DecimalFormat dec = new DecimalFormat("#0.0000");
                                         dec.format(sum);
                                         textFieldResult.setText(sum.toString());
                                     }
                                     catch (NumberFormatException ex)
                                     { JOptionPane.showMessageDialog(Okno.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE); }
                                     }
                                     }
        );

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                //textFieldX.setText("0");
                //textFieldY.setText("0");
                //textFieldZ.setText("0");
                textFieldResult.setText("0");
                sum = 0.0;
            }
        });

        JButton buttonXEX = new JButton();
        buttonXEX.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev)
        {
            if ( (XEXid%2) == 1)
            textFieldXEX.setText("ЪЁЬ");
            else textFieldXEX.setText("XEXE");
            XEXid++;
        }
        }
        );

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());

        hboxButtons.add(Box.createHorizontalGlue());
        Box hboxXEX = Box.createHorizontalBox();
        hboxXEX.add(Box.createHorizontalStrut(179));
        hboxXEX.add(buttonXEX);
        hboxXEX.add(Box.createHorizontalGlue());
        hboxButtons.setBorder( BorderFactory.createLineBorder(Color.GREEN));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxXEX);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        Okno frame = new Okno();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
