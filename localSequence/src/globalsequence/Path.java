/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalsequence;

/**
 *
 * @author tanaichaikraveephan
 */
public class Path {
    private boolean top;
    private boolean left;
    private boolean topleft;
    
    Path(boolean t, boolean l, boolean tl) {
        top = t;
        left = l;
        topleft = tl;
    }
    
    Path() {
        top = false;
        left = false;
        topleft = false;
    }
    
    public void setTop(boolean t) {
        top = t;
    }
    
    public void setLeft(boolean l) {
        left = l;
    }
    
    public void setTopleft(boolean tl) {
        topleft = tl;
    }
    
    public boolean canTop() {
        return top;
    }
    
    public boolean canLeft() {
        return left;
    }
    
    public boolean canTopleft() {
        return topleft;
    }
}