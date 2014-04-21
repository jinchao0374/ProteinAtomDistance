/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanwang
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.biojava.bio.structure.Chain;
import org.biojava.bio.structure.Structure;
import org.biojava.bio.structure.StructureException;
import org.biojava.bio.structure.io.FileParsingParameters;
import org.biojava.bio.structure.io.PDBFileReader;


public class CalInteractions {
    
    enum InteractionType
    {
        CBeta8A,
        Atom6A,
        MiniAtom55A;
    }
    
    class Interaction
    {
        public String chainA;
        public String chainB;
        public int posInChainA;
        public int posInChainB;
        public int AANameOfChainA;
        public int AANameOfChainB;
        InteractionType IAtype;
        float distant;
    }
    
    public CalInteractions()
    {
        struct = null;
    }
    
    
    
    public List<Interaction> calInteractionsInSingleChain(String pathToPDBFile)
    {
        boolean bSucc = loadPDBStructure(pathToPDBFile);
        if(!bSucc)
            return null;
        
        List<Chain> chains = getChains();
        if(chains.isEmpty())
            return null;
        
        List<Interaction> lstInteract = new ArrayList<Interaction>();
        for(Chain chain: chains)
        {
            chain.getSeqResGroups("amino");
        }
        
        return lstInteract;
    }
    
    private boolean loadPDBStructure(String pathToPDBFile)
    {
        PDBFileReader pdbreader = new PDBFileReader();
        struct = null;
        // configure the parameters of file parsing
         FileParsingParameters params = new FileParsingParameters();

        // parse the C-alpha atoms only, default = false
        params.setParseCAOnly(false);
        // align the SEQRES and ATOM records, default = true   
        // slows the parsing speed slightly down, so if speed matters turn it off.
        params.setAlignSeqRes(true);
        // the parser can read the secondary structure
        // assignment from the PDB file header and add it to the amino acids
        params.setParseSecStruc(true);
        pdbreader.setFileParsingParameters(params);
        // download missing PDB files automatically from EBI ftp server, default = false
        pdbreader.setAutoFetch(false);
        
        try{
            struct = pdbreader.getStructure(pathToPDBFile);
            System.out.println(struct);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        
        if(struct == null)
            return false;
        return true;
    }
    
    private List<Chain> getChains()
    {
        if (struct == null)
            return null;
        
        return struct.getChains();
    }
    
    
    private Structure struct;
}
