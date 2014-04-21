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
            for(int i=0; i<num-1; i++)
            {
                Group gi = groups.get(i);   //get the amino acid in the position i
                double[] giCrods = getCbCords(gi);
                for(int j=i+1; j<=num-1; j++)
                {
                    Group gj = groups.get(j); //get the amino acid in the position j
                    double[] gjCrods = getCbCords(gj);
                    double dist = calDistance(giCrods, gjCrods);
                    //System.out.println("\t" + giCrods + "\t" + gjCrods + "\t" );
                    System.out.println("\t\t" + i + "\t" + j + "\t" +Double.toString(dist) );
                }
            }
            
            /*for(Group group: groups)
            {
                System.out.println("\t" + Integer.toString(icount) + "\t" + group.getType() + "\t" + group.getPDBName());
                List<Atom> atoms = group.getAtoms();
                for(Atom atom: atoms)
                {
                    System.out.println("\t\t" + atom.getFullName() + "\t" + Double.toString(atom.getX()) + "\t" + Double.toString(atom.getY())+ "\t" + Double.toString(atom.getZ()));
                }
                icount++;
            }*/
        }
        
    }
    
    public double[] getCbCords(Group group)
    {
        //Here is an example!
        //create a array with double type
        double[] result = new double[3];
        
        //get all atoms from paramere group
        List<Atom> atoms = group.getAtoms();
        
        double X,Y,Z;
        String ca=" CA ";
        String cb=" CB ";
        for (Atom at : atoms)
        {
           //System.out.println("caresult"+at.getFullName()+"caresult");
            String str=at.getFullName();
            if(ca.equals(str))
            {
                result[0]=at.getX();
                result[1]=at.getY();
                result[2]=at.getZ();//System.out.println("caresult"+result[0]);
                
                //return result;
            } 
            if(cb.equals(str))
            {
                //get the X,Y,Z to result
                result[0]=at.getX();
                result[1]=at.getY();
                result[2]=at.getZ();
                
                //return result;
            }
            //System.out.println("cbresult"+ "\t" + result[0] + "\t" + result[1] + "\t" + result[2]);
        }
        
        return result;
    }
    
    public double calDistance(double[] g1, double[] g2)
    {
        double result = -1;
        
        //calculating result = ....
        // System.out.println(g1[0]+ "\t" + g2[0]+ "\t" + g1[1]+ "\t" +  g2[1]+ "\t" + g1[2]+ "\t" +g2[2]+ "\t" );
        result = Math.sqrt( (g1[0] - g2[0])*(g1[0] - g2[0])+(g1[1] - g2[1])*(g1[1] - g2[1])+(g1[2] - g2[2])*(g1[2] - g2[2]) ) ;
       
         //System.out.println("g1"+g1[0]);
        
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
