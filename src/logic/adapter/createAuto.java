package adapter;

import exception.AutoException;

import java.io.IOException;

public interface createAuto {
   public void BuildAuto(String fileName) throws IOException, AutoException;
   public void PrintAuto(String modelName);
   public void ManuallyBuildAuto();
}
