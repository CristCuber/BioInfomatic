/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalsequence;

import java.util.*;
/**
 *
 * @author tanaichaikraveephan
 */
public class GlobalSequence {

    /**
     */
    
    public static int[] maxX = new int[100];
    public static int[] maxY = new int[100];
    public static int a = 0; 
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter First Sequence: ");
        String sOne = scan.next();
        
        System.out.print("Enter Second Sequence: ");
        String sTwo = scan.next();
        
        System.out.print("Enter Score of Match: ");
        float match = scan.nextFloat();
        
        System.out.print("Enter Score of Mismatch: ");
        float mismatch = scan.nextFloat();
        
        System.out.print("Enter Score of Gap: ");
        float gap = scan.nextFloat();
        
        int sol = sOne.length();
        int stl = sTwo.length();
        
        String asw1 = "";
        String asw2 = "";
        
        //Initialize
        float table[][] = new float[stl + 1][sol + 1];
        table[0][0] = 0;
        //for(int i = 1; i < sol + 1; i++) table[0][i] = table[0][i - 1] + gap;
        //for(int j = 1; j < stl + 1; j++) table[j][0] = table[j - 1][0] + gap;
        
        Path select[][] = new Path[stl + 1][sol + 1];
        for(int i = 0; i < stl + 1; i++) {
            for(int j = 0; j < sol + 1; j++) {
                select[i][j] = new Path();
            }
        }
        
        //System.out.println("s1: " + sOne + " leaght: " + sol);
        //System.out.println("s2: " + sTwo+ " lenght: " + stl);
        
        /*for(int i = 0; i < stl + 1; i++) {
            for(int j = 0; j < sol + 1; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }*/
        
        //Fill Table
        for(int i = 1; i < stl + 1; i++) {
            for(int j = 1; j < sol + 1; j++) {
                float sumMatchOrMis;
                float sumGapTop;
                float sumGapLeft;
                
                //System.out.println("i = " + i + " " + "j = " + j);
                
                if(sOne.charAt(j - 1) == sTwo.charAt(i - 1)) sumMatchOrMis = table[i - 1][j - 1] + match;
                else sumMatchOrMis = table[i - 1][j - 1] + mismatch;
                
                if(sumMatchOrMis<0)sumMatchOrMis=0;
                
                sumGapTop = table[i - 1][j] + gap;
                sumGapLeft = table[i][j - 1] + gap;
                
                if(sumMatchOrMis > sumGapTop) {
                    if(sumMatchOrMis > sumGapLeft) { //topleft max
                        table[i][j] = sumMatchOrMis;
                        select[i][j].setTopleft(true);
                    }
                    else if(sumMatchOrMis < sumGapLeft) { //left max
                        table[i][j] = sumGapLeft;
                        select[i][j].setLeft(true);
                    }
                    else if(sumMatchOrMis == sumGapLeft) { //topleft && left max
                        table[i][j] = sumGapLeft;
                        select[i][j].setLeft(true);
                        select[i][j].setTopleft(true);
                    }
                }
                else if(sumMatchOrMis < sumGapTop) {
                    if(sumGapTop > sumGapLeft) { //top max
                        table[i][j] = sumGapTop;
                        select[i][j].setTop(true);
                    }
                    else if(sumGapTop < sumGapLeft) { //left max
                        table[i][j] = sumGapLeft;
                        select[i][j].setLeft(true);
                    }
                    else if(sumGapTop == sumGapLeft) { //top && left max 
                        table[i][j] = sumGapTop;
                        select[i][j].setTop(true);
                        select[i][j].setLeft(true);
                    }
                }
                else if(sumMatchOrMis == sumGapTop) {
                    if(sumMatchOrMis > sumGapLeft) { //top && topleft
                        table[i][j] = sumGapTop;
                        select[i][j].setTopleft(true);
                        select[i][j].setTop(true);
                    }
                    else if(sumMatchOrMis < sumGapLeft) { //left max
                        table[i][j] = sumGapLeft;
                        select[i][j].setLeft(true);
                    }
                    else if(sumMatchOrMis == sumGapLeft) { //top && left && topleft max
                        table[i][j] = sumGapLeft;
                        select[i][j].setTopleft(true);
                        select[i][j].setTop(true);
                        select[i][j].setLeft(true);
                    }
                }
            }
        }
        
        /*for(int i = 0; i < stl + 1; i++) { //print score
            for(int j = 0; j < sol + 1; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        
        for(int i = 0; i < stl + 1; i++) { //print path
            for(int j = 0; j < sol + 1; j++) {
                if(select[i][j].canLeft()) System.out.print("t");
                else System.out.print("f");
                
                if(select[i][j].canTop()) System.out.print("t");
                else System.out.print("f");
                
                if(select[i][j].canTopleft()) System.out.print("t");
                else System.out.print("f");
                
                System.out.print(" ");
            }
            System.out.println();
        }*/
        float realMax =  findMax(table,stl,sol);
        maxTimes(table, realMax, stl, sol);
        
        for(int i = a-1; i > 0; i--) {   
            printSol(table ,asw1, asw2, maxX[a], maxY[a], select, sOne, sTwo); //print solution
        }    
        //System.out.println("score: " + table[stl][sol]);
    }
    
    static void printSol(float[][] table ,String sol1, String sol2, int x, int y, Path[][] p, String seq1, String seq2) {
        if(table[x][y] != 0) {
        
        if(p[x][y].canTopleft()) {
            String tmp1 = Character.toString(seq1.charAt(y - 1)).concat(sol1);
            String tmp2 = Character.toString(seq2.charAt(x - 1)).concat(sol2);
            printSol(table ,tmp1, tmp2, x - 1, y - 1, p, seq1, seq2);
        }
        
        if(p[x][y].canTop()) {
            String tmp1 = Character.toString('-').concat(sol1);
            String tmp2 = Character.toString(seq2.charAt(x - 1)).concat(sol2);
            printSol(table ,tmp1, tmp2, x - 1, y, p, seq1, seq2);
        }
        
        if(p[x][y].canLeft()) {
            String tmp1 = Character.toString(seq1.charAt(y - 1)).concat(sol1);
            String tmp2 = Character.toString('-').concat(sol2);
            printSol(table, tmp1, tmp2, x, y - 1, p, seq1, seq2);
        }
        
        //if(!p[x][y].canLeft() && !p[x][y].canTop() && !p[x][y].canTopleft()) {
            /*if(x != 0 || y != 0) {
                if(y != 0) {
                    sol1 = Character.toString(seq1.charAt(y - 1)).concat(sol1);
                    sol2 = Character.toString('-').concat(sol2);
                    printSol(sol1, sol2, x, y - 1, p, seq1, seq2);
                }
                else if(x != 0) {
                    sol1 = Character.toString('-').concat(sol1);
                    sol2 = Character.toString(seq2.charAt(x - 1)).concat(sol2);
                    printSol(sol1, sol2, x - 1, y, p, seq1, seq2);
                }
            }*/
            if(table[x-1][y-1] == 0) {
                System.out.println("seq1: " + sol1);
                System.out.println("seq2: " + sol2);
            }
        //}
        }
        //System.out.println(x + " " + y);
    }
    
    static float findMax(float table[][],int stl,int sol){
        
        float maxvalue = 0;
        
        for(int i = 0;i < stl + 1;i++){
            for(int j = 0;j < sol + 1;j++){
                if(table[i][j] > maxvalue){
                    maxvalue = table[i][j];
                }
            }
        }
        
        return maxvalue;

    }
    
    static void maxTimes(float table[][], float max, int stl, int sol) {
        for(int i = 0;i < stl + 1;i++){
            for(int j = 0;j < sol + 1;j++){
                if(table[i][j] == max){
                    maxX[a] = i;
                    maxY[a] = j;
                    a++;
                }
            }
        }
    }
    
}