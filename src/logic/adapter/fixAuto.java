package adapter;

import exception.AutoException;

import java.io.IOException;

public interface fixAuto {
   public void fix(int error) throws AutoException, IOException;
}
