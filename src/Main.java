import functions.*;
import functions.basic.Cos;
import functions.basic.Exp;
import functions.basic.Log;
import functions.basic.Sin;
import functions.doc.TabulatedFunctionDoc;
import functions.meta.Composition;
import org.json.simple.*;

import java.io.*;

public class Main {
    public static void main(String[] args){
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(0,10,10);
        for (int i = 0; i < arrayTabulatedFunction.getPointsCount(); i++) {
            arrayTabulatedFunction.setPointY(i, Math.log(arrayTabulatedFunction.getPointX(i)));
        }

        TabulatedFunctionDoc a = new TabulatedFunctionDoc();
        a.newFunction(0, 10, 5);
        a.saveFunctionAs("2.json");
        a.loadFunction("2.json");


        /*
        JSONObject points = new JSONObject();
        for (int i = 0; i < arrayTabulatedFunction.getPointsCount(); i++) {
            points.put(arrayTabulatedFunction.getPointX(i), arrayTabulatedFunction.getPointY(i));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("points", points);
        try (FileWriter file = new FileWriter("test.json")){
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }
}