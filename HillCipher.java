import java.io.*; 
import java.util.*;
import java.util.ArrayList; 
import java.io.FileWriter;  
import java.io.IOException;  
/*
The purpose of this is program is to take a plain-text and a key matrix in 
a file and multiplicate them and give out the cipher-text in number form. 
*/


public class HillCipher {
   
    public static ArrayList<Integer> plaintoarray(File text) {
        ArrayList<Integer> plain_num = new ArrayList<>();
        
            int i = 0;
            try (Scanner scanner = new Scanner(text)) {
                
                while(scanner.hasNext()){
                    plain_num.add(scanner.nextInt());
                }
                
            
        } catch (Exception ex) {  
            System.out.println(ex.getMessage());  

    };
    return plain_num;
};
    public static void usingBufferedWritter(ArrayList<Integer>msg  , String x) throws IOException
    {              
        File file = new File("/Users/mohamedshakra/Desktop/Security/" + x);
        FileWriter writer = new FileWriter(file); 
        int i = 0;
        try{
            file.createNewFile();
            while(i < msg.size()){   
                writer.write(msg.get(i).toString());
                writer.write(" ");
            i++;
            } 
            writer.close();
        }
        catch(Exception e){
            System.out.print(e);   // prints the characters one by one
        }
        
    }

    public static ArrayList<ArrayList<Integer>> keytomatrix(File fil) {
        ArrayList<ArrayList<Integer> > tall 
            = new ArrayList<ArrayList<Integer> >(); 
    
        try { 
            Scanner scanner = new Scanner(fil);
            int i = 0;
            int ii= 0;
            int inf;
            while(scanner.hasNextInt()&& ii<3)
            {   

                tall.add(new ArrayList<Integer>()); 
                while(i <3){
                inf = scanner.nextInt();
                if(isinrange(inf)){
                tall.get(ii).add(inf);
                i++;
                }
                else{System.out.println("The input is not in range of 0 to 25");
                break;
                }
                }
                ii++;
                i=0; 

            } 
            
            
        } catch (Exception ex) {  
            System.out.println(ex.getMessage());  
        }

    
    return tall;
    };
    public static void setRadix(String radix) throws NumberFormatException,Exception{
        try {
          int rad = Integer.parseInt(radix);
          if(rad!=26){
            throw new Exception("Only radix-value 26 is supported");
          }
        } catch(NumberFormatException e) {
          throw new NumberFormatException("Radix has to be an integer");
        }
    }
    public static void setBlockSize(String block_size) throws NumberFormatException,Exception{
        try {
          int block= Integer.parseInt(block_size);
          if(block!=3){
            throw new Exception("Only block-size value 3 is supported!");
          }
        } catch(NumberFormatException e) {
          throw new NumberFormatException("Block-size has to be an integer");
        }
    }
  

    public static ArrayList<Integer> multmatrix(ArrayList<ArrayList<Integer>> m , ArrayList<Integer>p, String radix, String blocksize) throws NumberFormatException{
        
        ArrayList<Integer> a = new ArrayList<Integer>();
        int x = 0;
        int len = 0;
        int rad = Integer.parseInt(radix);
        int block = Integer.parseInt(blocksize);

        while(x<(p.size()-1)){
        for(int i = 0 ; i < block  ; i++){
            int sum = 0;
            int ii = 0;
            for(ii = 0; ii < block ; ii++){
                sum += (m.get(i).get(ii) * p.get(x+ii));
            }
            a.add(sum%rad);
        }
        x = x + 3;
    }
      return a;
};
private static boolean isSquareMatrix(File matrixFile){
    Scanner readScanner = null;
    int counter = 0;
    int temp;
    try {
        readScanner = new Scanner(matrixFile);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    while (readScanner.hasNextInt()) {
        temp = readScanner.nextInt();
        counter++;
    }
    int sqrt = (int) Math.sqrt(counter);
    if (sqrt*sqrt == counter) {
        return true;
    }
    return false;
};
    public static boolean isinrange(int x){
        if((0<=x) && (x<26)){return true;}
        else{return false;}
    }


    public static void main(String[] args) throws IOException , FileNotFoundException,NumberFormatException , Exception{
       
           /*
    if (args.length != 5) {
            System.out.println("Wrong argument format,"
            + "should be <radix> <blocksize> <keyfile> <plainfile> <cipherfile>.");
    } else {*/
        File key = new File("encoded.txt");
        keytomatrix(key);
        /*
        setBlockSize(args[0]);
            setRadix(args[1]);
        //File key = new File(args[2]);
        File text = new File(args[3]);
        if(!key.isFile()){System.out.println("key is not a file!");}
        else if(!isSquareMatrix(key)){ System.out.println("KeyFile is not a squarematrix");}
        else if(!text.isFile()){System.out.println("plaintext is not a file!");}
        else{  
        ArrayList<Integer> x = new ArrayList<>();
             x = multmatrix(keytomatrix(key),plaintoarray(text),"26","3");
               usingBufferedWritter(x,args[4]);
        }*/
    //}
}
}