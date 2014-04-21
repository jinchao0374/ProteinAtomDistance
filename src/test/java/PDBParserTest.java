/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import junit.framework.TestCase;
import org.biojava.bio.structure.Chain;

/**
 *
 * @author hanwang
 */
public class PDBParserTest extends TestCase {
    
    public PDBParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of Test method, of class PDBParser.
     */
    public void testTest() throws Exception {
        System.out.println("Test");
        String pathToPDBFile = "D:\\workspace\\beta\\1A0S.pdb";
        PDBParser instance = new PDBParser();
        instance.Test(pathToPDBFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class PDBParser.
     */
    public void testLoad() {
        System.out.println("load");
        String pathToPDBFile = "";
        PDBParser instance = new PDBParser();
        boolean expResult = false;
        boolean result = instance.load(pathToPDBFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChain method, of class PDBParser.
     */
    public void testGetChain() throws Exception {
        System.out.println("getChain");
        String chainID = "";
        PDBParser instance = new PDBParser();
        Chain expResult = null;
        Chain result = instance.getChain(chainID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChains method, of class PDBParser.
     */
    public void testGetChains() {
        System.out.println("getChains");
        PDBParser instance = new PDBParser();
        List expResult = null;
        List result = instance.getChains();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroups method, of class PDBParser.
     */
    public void testGetGroups() throws Exception {
        System.out.println("getGroups");
        String chainID = "";
        PDBParser instance = new PDBParser();
        List expResult = null;
        List result = instance.getGroups(chainID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
