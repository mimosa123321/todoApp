import java.awt.*;
import java.awt.event.*;


public class CardLayoutExample extends Frame implements ActionListener
{
    Panel main,pan1,pan2;
    TextField name_Text;
    TextField age_Text;
    Button first=new Button("first");
    Button second=new Button("second");
    Panel butt=new Panel();
    CardLayout cardLayout=new CardLayout();
    Choice choiceBox;

    public CardLayoutExample()
    {
        butt.add(first);
        butt.add(second);
        Label name=new Label("Name");
        name_Text=new TextField();
        Label age=new Label("Age");
        age_Text=new TextField();
        pan1=new Panel();
        pan1.setLayout(new GridLayout(2,2));
        pan1.add(name);
        pan1.add(name_Text);
        pan1.add(age);
        pan1.add(age_Text);
        choiceBox=new Choice();
        choiceBox.addItem("x1");
        choiceBox.addItem("x2");
        choiceBox.addItem("x3");
        choiceBox.addItem("x4");
        pan2=new Panel();
        pan2.add(choiceBox);
        setListeners();
        setLayout(new BorderLayout());
        main=new Panel();
        main.setLayout(cardLayout);
        main.add(pan1,"first");
        main.add(pan2,"second");
        add(butt,BorderLayout.SOUTH);
        add(main,BorderLayout.CENTER);

    }
    private void setListeners()
    {
        first.addActionListener(this);
        second.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==first)
        {
            cardLayout.next(main);
            cardLayout.show(main,"first");
        }
        else
        {
            cardLayout.next(main);
            cardLayout.show(main,"second");
        }
    }
    public static void main(String arghs[])
    {
        CardLayoutExample c=new CardLayoutExample();
        c.setVisible(true);
        c.setSize(200,200);
    }
}