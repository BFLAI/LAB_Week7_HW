import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FileGUI {
    public JFrame frame; //variable parts
    JPanel panelRight = new JPanel();
    JPanel panelLeft = new JPanel();
    JPanel panelRightTop = new JPanel();
    JPanel panelRightBottom = new JPanel();
    JPanel panelLeftTop = new JPanel();
    JPanel panelLeftBottom = new JPanel();
    JPanel panelRightTopA = new JPanel();
    JPanel panelRightTopB = new JPanel();
    JPanel panelRightTopC = new JPanel();
    JPanel panelRightTopD = new JPanel();
    JPanel panelLeftTopA = new JPanel();
    JPanel panelLeftTopB = new JPanel();
    JPanel panelLeftTopC = new JPanel();
    JPanel panelLeftTopD = new JPanel();

    JTextArea ar1 = new JTextArea(10, 30);
    JTextArea ar2 = new JTextArea(10, 30);

    String[] res;
    public void outputWindow() {

        frame = new JFrame("LAB_7_HW"); //output window part
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1400);

        panelRight.setSize(600, 900);
        panelLeft.setSize(600, 900);

        JLabel label1 = new JLabel("File Name:"); //button part
        JTextField tf1 = new JTextField(30);
        JButton button1 = new JButton("Click to read from file");
        JLabel label3 = new JLabel("Previous file's lines:");

        button1.addActionListener(new ActionListener() //button function
        {
            public void actionPerformed(ActionEvent e)
            {
                String fileName = FileGUI.class.getResource("") + tf1.getText();
                fileName = fileName.split(":")[1];
                BufferedReader buf = null;
                String line = "";
                String csvSplit = ",";
                res = new String[5];
                try {
                    buf = new BufferedReader(new FileReader(fileName));
                    int i = 0;
                    while((line = buf.readLine()) != null && i < 5){
                        res[i] = "";
                        String toAppend = "";
                        String[] temp = line.split(csvSplit);
                        for (int j = 0; j < temp.length && j < 3; j++) {
                            if (j==0) {
                                res[i] += temp[j];
                                toAppend += temp[j];
                            } else {
                                res[i] += "," + temp[j];
                                toAppend += "," + temp[j];
                            }
                        }
                        ar1.append(line+"\r\n");
                        ar2.append(res[i]+"\r\n");
                        i++;
                    }
                    ar1.setWrapStyleWord(true);
                    frame.setVisible(true);

                } catch(Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        JLabel label2 = new JLabel("File Name to write:"); //input part
        JTextField tf2 = new JTextField(30);
        JButton button2 = new JButton("Write to file");
        JLabel label4 = new JLabel("New file's lines:");

        button2.addActionListener(new ActionListener() //button function
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    String fileName = tf2.getText();
                    File fout = new File(fileName);
                    FileOutputStream fos = new FileOutputStream(fout);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    for (int i = 0; i < res.length; i++) {
                        bw.write(res[i]);
                        bw.newLine();
                    }
                    bw.close();
                    System.out.println("Valid Output");
                } catch(Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        panelRightTopA.add(label2); //frame setting
        panelRightTopA.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));
        panelRightTopB.add(tf2);
        panelRightTopB.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelRightTopC.add(button2);
        panelRightTopC.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelRightTopD.add(label4);
        panelRightTopD.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 15));
        panelRightTop.setLayout(new GridLayout(4,1, 10, 10));
        panelRightTop.add(panelRightTopA);
        panelRightTop.add(panelRightTopB);
        panelRightTop.add(panelRightTopC);
        panelRightTop.add(panelRightTopD);

        panelLeftTopA.add(label1);//frame setting
        panelLeftTopA.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));
        panelLeftTopB.add(tf1);
        panelLeftTopB.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelLeftTopC.add(button1);
        panelLeftTopC.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelLeftTopD.add(label3);
        panelLeftTopD.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 15));
        panelLeftTop.setLayout(new GridLayout(4,1, 10, 10));
        panelLeftTop.add(panelLeftTopA);
        panelLeftTop.add(panelLeftTopB);
        panelLeftTop.add(panelLeftTopC);
        panelLeftTop.add(panelLeftTopD);
        panelLeft.add(panelLeftTop, BorderLayout.NORTH);
        panelLeft.add(panelLeftBottom, BorderLayout.SOUTH);

        panelRight.add(panelRightTop, BorderLayout.NORTH);
        panelRight.add(panelRightBottom, BorderLayout.SOUTH);

        ar1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 25));
        ar2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 25));
        panelLeftBottom.add(ar1);
        panelRightBottom.add(ar2);

        frame.setLayout(new GridLayout(1,2, 20, 20));
        frame.add(panelRight);
        frame.add(panelLeft);
        frame.setVisible(true);
    }

}