package management;

public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private int limite;

    public Chronometre(int limite) {
        //intialisation
        this.limite = limite ;
    }
    
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return System.currentTimeMillis()-begin;
    }
 
    public long getMilliseconds() {
        return System.currentTimeMillis()-begin;
    }
 
    public int getSeconds() {
        return (int) ((System.currentTimeMillis() - begin) / 1000.0);
    }
 
    public double getMinutes() {
        return (System.currentTimeMillis() - begin) / 60000.0;
    }
 
    public double getHours() {
        return (System.currentTimeMillis() - begin) / 3600000.0;
    }
    
    /*
    * Method to know if it remains time.
    */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((end-current)/1000.0);
        return (timeSpent>0);
    }
     
}
