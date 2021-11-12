package functions.doc;

import functions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TabulatedFunctionDoc implements TabulatedFunction{
    private TabulatedFunction tabulatedFunction;
    private String fileName; // название файла
    private boolean modified; // изменялся ли документ с последнего сохранения
    private boolean fileNameAssigned; // назначено ли имя файла для документа

    public TabulatedFunctionDoc() {
        this.tabulatedFunction = null;
        this.fileName = null;
        this.modified = false;
        this.fileNameAssigned = false;
    }

    public boolean isModified() {
        return modified;
    }

    public boolean isFileNameAssigned() {
        return fileNameAssigned;
    }

    public void newFunction(double leftX, double rightX, int pointsCount) {
        tabulatedFunction = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        modified = true;
    }

    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount) {
        tabulatedFunction = TabulatedFunctions.tabulate(function, leftX, rightX, pointsCount);
        modified = true;
    }

    public void saveFunctionAs(String fileName) {
        JSONObject points = new JSONObject();
        JSONArray jsonArray;
        for (int i = 0; i < tabulatedFunction.getPointsCount(); i++) {
            jsonArray = new JSONArray();
            jsonArray.add(tabulatedFunction.getPointX(i));
            jsonArray.add(tabulatedFunction.getPointY(i));
            points.put("point" + Integer.toString(i), jsonArray);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("length", tabulatedFunction.getPointsCount());
        jsonObject.put("points", points);
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toJSONString());
            file.flush();
            this.fileName = fileName;
            fileNameAssigned = true;
            modified = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFunction(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONObject points = (JSONObject) jsonObject.get("points");
            JSONArray point;

            tabulatedFunction = new LinkedListTabulatedFunction();
            for (int i = 0; i < (long) jsonObject.get("length"); i++) {
                point = (JSONArray) points.get("point" + Integer.toString(i));
                tabulatedFunction.addPoint(new FunctionPoint((double) point.get(0), (double) point.get(1)));
            }
            this.fileName = fileName;
            fileNameAssigned = true;
            modified = false;
        } catch (IOException | ParseException | InappropriateFunctionPointException e) {
            e.printStackTrace();
        }
    }

    public void saveFunction(){
        if (!fileNameAssigned){
            throw new RuntimeException("fileNameIsNotAssigned");
        }
        saveFunctionAs(fileName);
        modified = false;
    }

    @Override
    public double getLeftDomainBorder() {
        return tabulatedFunction.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return tabulatedFunction.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) {
        return tabulatedFunction.getFunctionValue(x);
    }

    @Override
    public int getPointsCount() {
        return tabulatedFunction.getPointsCount();
    }

    @Override
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPoint(index);
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        tabulatedFunction.setPoint(index, point);
        modified = true;
    }

    @Override
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPointX(index);
    }

    @Override
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        tabulatedFunction.setPointX(index, x);
        modified = true;
    }

    @Override
    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPointY(index);
    }

    @Override
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        tabulatedFunction.setPointY(index, y);
        modified = true;
    }

    @Override
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        tabulatedFunction.deletePoint(index);
        modified = true;
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        tabulatedFunction.addPoint(point);
        modified = true;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {  // НЕ ЯСНО ЧТО ОН НАС ХОТЯТ, ТУТ МОЖНО ИМЕТЬ В ВИДУ СОВСЕМ ДРУГОЕ
        TabulatedFunctionDoc cloneTabulatedFunctionDoc = new TabulatedFunctionDoc();
        cloneTabulatedFunctionDoc.tabulatedFunction = (TabulatedFunction) this.tabulatedFunction.clone();
        cloneTabulatedFunctionDoc.fileName = this.fileName; //??
        cloneTabulatedFunctionDoc.modified = this.modified; //??
        cloneTabulatedFunctionDoc.fileNameAssigned = this.fileNameAssigned; //??
        return cloneTabulatedFunctionDoc;
    }

    @Override
    public int hashCode() {  // НЕ ЯСНО ЧТО ОН НАС ХОТЯТ, ТУТ МОЖНО ИМЕТЬ В ВИДУ СОВСЕМ ДРУГОЕ
        return tabulatedFunction.hashCode() + fileName.hashCode() + Boolean.hashCode(modified) + Boolean.hashCode(fileNameAssigned);
    }

    @Override
    public boolean equals(Object obj) {  // НЕ ЯСНО ЧТО ОН НАС ХОТЯТ, ТУТ МОЖНО ИМЕТЬ В ВИДУ СОВСЕМ ДРУГОЕ
        if (obj instanceof TabulatedFunctionDoc){
            TabulatedFunctionDoc doc = (TabulatedFunctionDoc) obj;
            if (this.tabulatedFunction.equals(doc.tabulatedFunction) && this.fileName.equals(doc.fileName) && this.modified == doc.modified && this.fileNameAssigned == doc.fileNameAssigned){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return tabulatedFunction.toString();
    }
}
