package exception;
import util.FileIO;
import java.io.IOException;
public class AutoException extends Exception{
   private int errorno;
   public AutoException(int erroNo) {
      super();
      this.errorno = erroNo;
   }
   public AutoException() {
      super();
   }
   //getters
   public int getErrorno() {
      return this.errorno;
   }
   //method for fixing
   public void fix(int errno) throws IOException, AutoException {
      Fix1to100 f1 = new Fix1to100();
      switch(errno)
      {
         case 1: //checked
            f1.FileNameNotFound(errno); //fixed
            break;
         case 2: //checked
            f1.OptionSetNameNotFound(errno); //handled
            break;
         case 3: //checked
            f1.PriceIsInvalid(errno); //handled
            break;
         case 4: //checked
            f1.MissingOptionSetOptions(errno); //handled
            break;
         case 5: //checked
            f1.EmptyFileUsed(errno); //fixed
            break;
         default:
            break;
      }
   }
}