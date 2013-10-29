import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.util.ArrayList;

public class Menu extends GameObject{

    private Font font;
    private FontMetrics metrics;
    
    private String title;
    
    private int selected;
    
    private ArrayList m_items;
    
    private String opt_cont = "Continue";
    private String opt_newgame = "New Game";
    private String opt_options = "Options";
    private String opt_exit = "Exit";

    public Menu(int objectID) {
        
        OID = objectID;
        TYPE = "MENU";
        NAME = "Main Menu";
        
        title = "!SUPER-PONG";
        
        selected = 0;
        
        m_items = new ArrayList();
        
        m_items.add(opt_cont);
        m_items.add(opt_newgame);
        m_items.add(opt_options);
        m_items.add(opt_exit);
        
        VISIBLE = true;
        
    }
    
    public void draw(Graphics2D g, MainFrame f, Canvas c) {
        
        int spacing = 30;
        
        // Setup a bunch of stuff for drawing text
        g.setFont(new Font("default", Font.BOLD, 26));
        
        font = g.getFont();
        metrics = g.getFontMetrics(font);
        
        int txt_width = metrics.stringWidth(title);
        int txt_height = metrics.getHeight();
        
        int x = (f.getWidth() / 2) - (txt_width / 2);
        int y = (f.getHeight() / 2) - (txt_height / 2) - 30;
        
        g.setColor(Color.orange);
        g.drawString(title, x , y);
        
        // change the font size
        //g.setFont(new Font("default", Font.BOLD, 16));
        
        // Indent the text underneath the title
        x += 15;
        
        for(int o = 0; o < m_items.size(); o ++ ) {
            
            y += spacing;
            
            if(o == selected) {
                
                g.setColor(Color.white);
                g.setFont(new Font("default", Font.BOLD, 20));
                
            }
            else {
                
                g.setColor(Color.gray);
                g.setFont(new Font("default", Font.BOLD, 16));
                
            }
            
            String item = (String)m_items.get(o);
            
            g.drawString(item, x , y);
            
        }
        
        
    }
    
    public void nextMenuOpt() {
        
        if(selected == m_items.size() - 1) selected = 0;
        else selected++;
        
    }
    
    public void prevMenuOpt() {
        
        if(selected == 0) selected = m_items.size() - 1;
        else selected--;
        
    }
    
    public String getSelected() {
        
        return (String)m_items.get(selected);
        
    }

}
