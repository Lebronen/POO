public class triple{
    public int[] nbr;

    public triple(int a,int b,int c){
        int[] tab=new int[3];
        nbr=tab;
        nbr[0]=a;
        nbr[1]=b;
        nbr[2]=c;
    }
    public int somme(){
        return nbr[0]+nbr[1]+nbr[2];
    }
    public boolean equalTriple(triple a){
        
        if(a.nbr[0]==this.nbr[0] && a.nbr[1]==this.nbr[1] && a.nbr[2]==this.nbr[2]){
            return true;
        }else{
            return false;
        }
    }

}