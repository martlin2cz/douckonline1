package cz.martlin.douckonline.business.tools;

import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class SimpleQueryTest {
    
//<editor-fold defaultstate="collapsed" desc="from">
    @Test
    public void testPrimitive() {
	String expected0 = "SELECT jButton FROM JButton jButton";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
    
    @Test
    public void testJoined() {
	String expected0 = "SELECT jFrame FROM JFrame jFrame INNER JOIN JMenu jMenu ON jFrame.menu = jMenu";
	
	SimpleQuery query0 = new SimpleQuery(relations(), JFrame.class, JMenu.class );
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
     @Test
    public void testMultiJoined() {
	String expected0 = 
		"SELECT jFrame FROM JFrame jFrame INNER JOIN JPanel jPanel ON jFrame.root_pane = jPanel INNER JOIN JLabel jLabel ON jPanel.label = jLabel";
	
	SimpleQuery query0 = new SimpleQuery(relations(), JFrame.class, JPanel.class, JLabel.class);
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="order">
    
    @Test
    public void testWithOrder() {
	String expected0 = "SELECT jButton FROM JButton jButton ORDER BY jButton.height, jButton.width";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addOrder("height", true);
	query0.addOrder("jButton.width", false);
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="where">
    @Test
    public void testWithWhereCondition() {
	String expected0 = "SELECT jButton FROM JButton jButton WHERE jButton.height > 10";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addWhereCondition("jButton.height > 10");
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }

    @Test
    public void testWithWhereAttribute() {
	String expected0 = "SELECT jButton FROM JButton jButton WHERE jButton.height = :height AND jButton.width = :width";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addWhereAttribute("height");
	query0.addWhereAttribute("width");
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
    @Test
    public void testWithWhereVariable() {
	String expected0 = "SELECT jButton FROM JButton jButton WHERE jButton.height = :size AND jButton.width = :size";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addWhereVariable("height", true, "size");
	query0.addWhereVariable("width", true, "size");	
	
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
      @Test
    public void testWithWhereValue() {
	String expected0 = "SELECT jButton FROM JButton jButton WHERE jButton.height = 20 AND jButton.width = 10";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addWhereValue("height", true, 20);
	query0.addWhereValue("width", true, 10);	
	
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
      @Test
    public void testWithWhereWhatever() {
	String expected0 = "SELECT jButton FROM JButton jButton WHERE jButton.height > 10 AND jButton.width <= 20";
	
	SimpleQuery query0 = new SimpleQuery(JButton.class);
	query0.addWhereWhatever("height", true, "> 10");
	query0.addWhereWhatever("width", true, "<= 20");	
	
	String actual0 = query0.toJPQL();
	
	assertEquals(expected0, actual0);
    }
    
//</editor-fold>
 

    private Relations relations() {
	Relations relations = new Relations();
	
	relations.addRelation(JFrame.class, JPanel.class, "root_pane");
	relations.addRelation(JFrame.class, JMenu.class, "menu");
	relations.addRelation(JPanel.class, JButton.class, "button");
	relations.addRelation(JPanel.class, JLabel.class, "label");
	relations.addRelation(JButton.class, String.class, "text");
	relations.addRelation(JButton.class, Image.class, "icon");
	
	//etc.
	return relations;
    }
    
}
