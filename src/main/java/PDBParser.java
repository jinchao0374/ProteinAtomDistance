/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanwang
 */
import java.io.IOException;
import java.util.List;
import org.biojava.bio.structure.Atom;
import org.biojava.bio.structure.Chain;
import org.biojava.bio.structure.Group;
import org.biojava.bio.structure.Structure;
import org.biojava.bio.structure.StructureException;
import org.biojava.bio.structure.io.PDBFileReader;



public class PDBParser {
    
      
    public void Test(String pathToPDBFile) throws StructureException
    {
        boolean bSucc = false;
        bSucc = load(pathToPDBFile);
        if(!bSucc)
            return;
        
        List<Chain> chains = getChains();
        if(chains == null)
            return;
        
        for(Chain chain: chains)
        {
            System.out.println();
            System.out.println(chain.getChainID());
            List<Group> groups = getGroups(chain.getChainID());
            int icount = 1;
            
            int num = groups.size();
            for(int i=0; i<=num-1; i++)
            {
                Group gi = groups.get(i);   //get the amino acid in the position i
                double[] giCrods = getCbCords(gi);
                for(int j=i+1; j<=num; j++)
                {
                    Group gj = groups.get(j); //get the amino acid in the position j
                    double[] gjCrods = getCbCords(gj);
                    double dist = calDistance(giCrods, gjCrods);
                }
            }
            
            for(Group group: groups)
            {
                System.out.println("\t" + Integer.toString(icount) + "\t" + group.getType() + "\t" + group.getPDBName());
                List<Atom> atoms = group.getAtoms();
                for(Atom atom: atoms)
                {
                    System.out.println("\t\t" + atom.getFullName() + "\t" + Double.toString(atom.getX()) + "\t" + Double.toString(atom.getY())+ "\t" + Double.toString(atom.getZ()));
                }
                icount++;
            }
        }
        
    }
    
    public double[] getCbCords(Group group)
    {
        //create a array with double type
        //double[] result = new Array();
        
        //get all atoms from paramere group
        //e.g. List<Atom> atoms = group.getAtoms();
        
        double X,Y,Z;
        for (Atom at : atoms)
        {
            if(at.getName() == "CA")
            {
                //get the X,Y,Z to result
            }
            if(at.getName() == "CB")
            {
                //get the X,Y,Z to result
            }
            
        }
        
        return result;
    }
    
    public double calDistance(double[] g1, double[] g2)
    {
        double result = -1;
        
        //calculating result = ....
        
        return result;
    }
    
    
    public boolean load(String pathToPDBFile)
    {
        struct = null;
        struct = loadStructure(pathToPDBFile);
        if (struct != null)
            return true;
        return false;
    }
    
    private Structure loadStructure(String pathToPDBFile){
        PDBFileReader pdbreader = new PDBFileReader();
        Structure structure = null;
        try{
                structure = pdbreader.getStructure(pathToPDBFile);
                System.out.println(structure);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return structure;
    }

    public Chain getChain(String chainID) throws StructureException
    {
        Chain chain = null;
        if (struct == null)
            return chain;
        
        chain = struct.getChainByPDB(chainID);
        
        return chain;
    }
    
    public List<Chain> getChains()
    {
        if (struct == null)
            return null;
        
        return struct.getChains();
    }
    
    public List<Group> getGroups(String chainID) throws StructureException
    {
        if (struct == null)
            return null;
        Chain chain = struct.getChainByPDB(chainID);
        return chain.getAtomGroups("amino");
    }
    
    private Structure struct;
}
