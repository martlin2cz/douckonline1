/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.tools;

import java.util.List;
import java.util.Map;
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
public class RelationsTest {

    private static final Relations relations = new Relations();

    public RelationsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	relations.addRelation(List.class, String.class, "item");

	relations.addRelation(List.class, Integer.class, "int_item");
	relations.addRelation(List.class, Double.class, "double_item");

	relations.addRelation(Map.class, String.class, "key");
	relations.addRelation(Map.class, Object.class, "value");

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHasRelation() {
	assertTrue(relations.hasRelation(List.class, String.class, "item"));
	assertTrue(relations.hasRelation(List.class, Integer.class, "int_item"));
	assertTrue(relations.hasRelation(List.class, Double.class, "double_item"));
	assertTrue(relations.hasRelation(Map.class, String.class, "key"));
	assertTrue(relations.hasRelation(Map.class, Object.class, "value"));

	assertFalse(relations.hasRelation(List.class, List.class, "something"));
	assertFalse(relations.hasRelation(List.class, String.class, "not_an_item"));
    }

    @Test
    public void testGet() {
	assertEquals("item", relations.get(List.class, String.class));
	assertEquals("int_item", relations.get(List.class, Integer.class));
	assertEquals("key", relations.get(Map.class, String.class));

	assertEquals(null, relations.get(List.class, List.class));
    }

}
