/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viterbi;

import java.io.*;
import java.util.*;

/**
 *
 * @author tanaichaikraveephan
 */
public class Viterbi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> state = new ArrayList<>();
        int nState = 0;
        int nSymbol;
        ArrayList<String> sym = new ArrayList<>();
        String seq = null;
        
        String path1 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/states.txt";
        File file1 = new File(path1);
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file1));
            
            String line;
            
            while((line = bf.readLine()) != null) {
                state.add(line);
            }
            
            nState = state.size();
            
            bf.close();
            
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        String path2 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/symbols.txt";
        File file2 = new File(path2);
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file2));
            
            String line;
            
            while((line = bf.readLine()) != null) {
                sym.add(line);
            }
            
            bf.close();
            
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        nSymbol = sym.size();
        
        /*for(int i = 0; i < sym.size(); i++) {
            System.out.println(sym.get(i));
        }*/
        
        float[] probFromStart = new float[nState];
        
        String path3 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/starting.txt";
        File file3 = new File(path3);
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file3));
            
            String line;
            int index = 0;
            
            while((line = bf.readLine()) != null) {
                probFromStart[index] = Float.parseFloat(line);
                index++;
            }
            
            bf.close();
            
        }
        catch(IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        /*for(int i = 0; i < probFromStart.length; i++) {
            System.out.println(probFromStart[i]);
        }*/
        
        float[][] tranProb = new float[nState][nState];
        
        String path4 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/transition.txt";
        File file4 = new File(path4);
        
        try {
            Scanner scan1 = new Scanner(file4);
            
            while(scan1.hasNextFloat()) {
                for(int i = 0; i < tranProb.length; i++) {
                    for(int j = 0; j < tranProb.length; j++) {
                        tranProb[i][j] = scan1.nextFloat();
                    }
                }
            }
            
            scan1.close();
            
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        /*for(int i = 0; i < tranProb.length; i++) {
            for(int j = 0; j < tranProb.length; j++) {
                System.out.print(tranProb[i][j] + " ");
            }
            System.out.println();
        }*/
        float[][] emiProb = new float[nState][nSymbol];
        
        String path5 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/emission.txt";
        File file5 = new File(path5);
        
        //System.out.println(nSymbol + " " + nState);
        
        try {
            Scanner scan2 = new Scanner(file5);
            
            //System.out.println("check");
            
            while(scan2.hasNextFloat()) {
                //System.out.println("check");
                for(int i = 0; i < nState; i++) {
                    for(int j = 0; j < nSymbol; j++) {
                        emiProb[i][j] = scan2.nextFloat();
                        //System.out.println(i + " " + j);
                    }
                }
            }
            
            scan2.close();
            
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        /*for(int i = 0; i < emiProb.length; i++) {
            for(int j = 0; j < emiProb.length; j++) {
                System.out.print(emiProb[i][j] + " ");
            }
            System.out.println();
        }*/
        
        String path6 = "/Users/tanaichaikraveephan/Desktop/Java/BioInfo/sequence.txt";
        File file6 = new File(path6);
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file6));
            
            String line;

            while((line = bf.readLine()) != null) {
                seq = line;
            }

            bf.close();
            
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        //System.out.println(seq);
        
        float[] probToEnd = new float[nState];
        for(int i = 0; i < probToEnd.length; i++) {
            probToEnd[i] = 1;
        }
        
        /*for(int i = 0; i < probToEnd.length; i++) {
            System.out.print(probToEnd[i] + " ");
        }*/
        
        //System.out.println(tranProb[1][1]);
        
        for(int i = 0; i < tranProb.length; i++) {
            for(int j = 0; j < tranProb.length; j++) {
                probToEnd[i] = probToEnd[i] - tranProb[i][j];
                //System.out.print(probToEnd[i] + " ");
            }
            if(probToEnd[i] < 0) probToEnd[i] = 0;
            //System.out.println();
        }
        
        System.out.println("States");
        for(int i = 0; i < state.size(); i++) {
            System.out.print(state.get(i) + " ");
        }
        System.out.println();
        
        System.out.println("Symbols");
        for(int i = 0; i < sym.size(); i++) {
            System.out.print(sym.get(i) + " ");
        }
        System.out.println();
        
        System.out.println("startProbs");
        for(int i = 0; i < probFromStart.length; i++) {
            System.out.print(probFromStart[i] + " ");
        }
        System.out.println();
        
        System.out.println("endProbs");
        for(int i = 0; i < probToEnd.length; i++) {
            System.out.print(probToEnd[i] + " ");
        }
        System.out.println();
        
        System.out.println("transProbs");
        for(int i = 0; i < tranProb.length; i++) {
            for(int j = 0; j < tranProb.length; j++) {
                System.out.print(tranProb[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("emissionProbs");
        for(int i = 0; i < nState; i++) {
            for(int j = 0; j < nSymbol; j++) {
                System.out.print(emiProb[i][j] + " ");
            }
            System.out.println();
        }
        
        float[][] allTranProb = new float[nState + 2][nState + 2];
        allTranProb[0][0] = 0; allTranProb[allTranProb.length - 1][allTranProb.length - 1] = 0;
        for(int i = 1; i < allTranProb.length - 1; i++) allTranProb[0][i] = probFromStart[i - 1];
        for(int i = 1; i < allTranProb.length - 1; i++) allTranProb[i][allTranProb.length - 1] = probToEnd[i - 1];
        
        for(int i = 1; i < nState + 1; i++) {
            for(int j = 1; j < nState + 1; j++) {
                allTranProb[i][j] = tranProb[i - 1][j - 1];
            }
        }
        
        System.out.println("fullTranProb");
        for(int i = 0; i < allTranProb.length; i++) {
            for(int j = 0; j < allTranProb.length; j++) {
                System.out.print(allTranProb[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("sequence");
        //for(int i = 0; i < charSeq.length; i++) System.out.print(charSeq[i] + " ");
        System.out.println(seq);
        
        float[][] v = new float[nState + 1][seq.length() + 1];
        int[][] ptr = new int[nState][seq.length()];
        
        //for(int i = 1; i < nState + 1; i++) v[i][0] = 0;
        //for(int i = 0; i < seq.length() + 1; i++) v[0][i] = 0;
        for(int i = 0; i < nState + 1; i++) {
            for(int j = 0; j < seq.length() + 1; j++) {
                v[i][j] = 0;
            }
        }
        v[0][0] = 1;
        
        for(int i = 0; i < nState; i++) {
            for(int j = 0; j < seq.length(); j++) {
                ptr[i][j] = 0;
            }
        }
        
        for(int j = 1; j < seq.length() + 1; j++) {
            for(int i = 1; i < nState + 1; i++) {
                int index = checkChar(seq, sym, j - 1);
                //System.out.println(index);
                
                v[i][j] = emiProb[i - 1][index] * maxalkvl(allTranProb, v, nState, i, j);
                ptr[i - 1][j - 1] = ptrtk(allTranProb, v, nState, i, j);
                //System.out.println(v[i][j]);
            }
        }
        
        System.out.println("vTable");
        for(int i = 0; i < nState + 1; i++) {
            for(int j = 0; j < seq.length() + 1; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("ptrTable");
        for(int i = 0; i < nState; i++) {
            for(int j = 0; j < seq.length(); j++) {
                System.out.print(ptr[i][j] + " ");
            }
            System.out.println();
        }
        
        double P = findP(v, probToEnd, nState, seq);
        System.out.println("P = " + P);
        
        int PiT = findPiT(v, probToEnd, nState, seq);
        System.out.println("PiT = " + PiT);
        
        System.out.print("Trace Back Path: ");
        
        ArrayList TBPsort = new ArrayList<>();
        TBP(ptr, PiT, seq.length() - 1, TBPsort);
        
        /*for(int i = 0; i < TBPsort.size(); i++) {
            System.out.print(TBPsort.get(i) + " ");
        }
        System.out.println();*/
        
        for(int i = TBPsort.size() - 2; i >= 0; i--) {
            System.out.print(TBPsort.get(i) + " ");
        }
        System.out.println(PiT);
    }
    
    static float maxalkvl(float[][] allTranProb, float[][] v, int nState, int k, int t) {
        float result = Float.MIN_VALUE;
        float tmp;
        
        for(int i = 0; i < nState + 1; i++) {
            //System.out.println(v[0][0]);
            tmp = allTranProb[i][k] * v[i][t - 1];
            //if(i > 0) System.out.print(tmp + " ");
            
            //if(t == 6 && k == 1) System.out.print(tmp + " ");
            
            if(tmp > result) {
                result = tmp;
                //System.out.println(result);
            }
        }
        
        return result;
    }
    
    static int checkChar(String seq, ArrayList sym, int j) {
        int index = 1;
        
        for(int i = 0; i < sym.size(); i++) {
            if(sym.get(i).equals(Character.toString(seq.charAt(j)))) {
                //System.out.println("check");
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    static int ptrtk(float[][] allTranProb, float[][] v, int nState, int k, int t) {
        float result = Float.MIN_VALUE;
        float tmp;
        int x = 0;
        
        for(int i = 0; i < nState + 1; i++) {
            //System.out.println(v[0][0]);
            tmp = allTranProb[i][k] * v[i][t - 1];
            //System.out.print(tmp + " ");
            
            if(tmp > result) {
                result = tmp;
                x = i;
                //System.out.println(tmp);
            }
        }
        
        return x;
    }
    
    static float findP(float[][] v, float[] probToEnd, int nState, String seq) {
        float result = Float.MIN_VALUE;
        float tmp;
        
        for(int i = 1; i < nState + 1; i++) {
            tmp = v[i][seq.length()] * probToEnd[i - 1];
            
            if(tmp > result) result = tmp;
        }
        
        return result;
    }
    
    static int findPiT(float[][] v, float[] probToEnd, int nState, String seq) {
        float result = Float.MIN_VALUE;
        float tmp;
        int x = 0;
        
        for(int i = 1; i < nState + 1; i++) {
            tmp = v[i][seq.length()] * probToEnd[i - 1];
            
            if(tmp > result) {
                x = i;
                result = tmp;
            }
        }
        
        return x;
    }
    
    static void TBP(int[][] ptr, int x, int y, ArrayList TBPsort) {
        if(y < 0) return; 
        
        //System.out.print(x + " " + y);
        TBPsort.add(ptr[x - 1][y]);
        
        TBP(ptr, ptr[x - 1][y], y - 1, TBPsort);
    }
}