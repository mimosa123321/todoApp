import java.awt.*;

public class Simple extends java.applet.Applet {

    public void init() {

        Button      button      = new Button( "Quit" ) ;
        Checkbox    checkbox    = new Checkbox( "Test" ) ;

                Choice      choice      = new Choice() ;
        // fill the Choice
        choice.addItem( "Clinton" ) ;
        choice.addItem( "Dole" ) ;
        choice.addItem( "Perot" ) ;
        choice.addItem( "Browne" ) ;
        choice.addItem( "Nader" ) ;

        Label       label       = new Label( "This is a label" ) ;

        List        list        = new List( 5, false ) ;
        // fill the List
        list.addItem( "Clinton" ) ;
        list.addItem( "Dole" ) ;
        list.addItem( "Perot" ) ;
        list.addItem( "Browne" ) ;
        list.addItem( "Nader" ) ;

        Scrollbar   scrollbar   = new Scrollbar( Scrollbar.HORIZONTAL ) ;

        // add the controls to the default layout
        add( button ) ;
        add( checkbox ) ;
        add( choice ) ;
        add( label ) ;
        add( list ) ;
        add( scrollbar ) ;

    }

}