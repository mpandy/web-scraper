package com.linkresearchtools.pandi.io.ui;

import com.linkresearchtools.pandi.io.process.IProcessor;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Browser extends JFrame implements ActionListener
{
    private JLabel label = new JLabel("Url:");
    private JTextField urlTextField = new JTextField();
    private JButton extractButton = new JButton("Extract Data");
    private JTextArea result = new JTextArea();
    private IProcessor processor;

    public Browser(String title, IProcessor iProcessor) throws HeadlessException {

        processor = iProcessor;
        setTitle(title);
        setSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        urlTextField.setBorder(new LineBorder(Color.BLUE));
        urlTextField.setPreferredSize(new Dimension( 500, 24) );
        urlTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        result.setSize( new Dimension( 500, 300) );
        result.setFont(new Font("Tahoma", Font.PLAIN, 15));
        result.setLineWrap(true);
        extractButton.addActionListener(this);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(label);
        northPanel.add(urlTextField);
        northPanel.add(extractButton);

        JScrollPane resultPane = new JScrollPane(result);
        resultPane.setSize(new Dimension( 500, 1000));
        resultPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        resultPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Container c = getContentPane();
        c.add(northPanel, BorderLayout.NORTH);
        c.add(resultPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        result.setText(processor.readUrl(urlTextField.getText()));
    }
}