/**
 * UnitConverter Class
 * contains all of the methods and constants necessary to do unit conversion in the MainWindow.
 * Created by Zane Cook on 5/15/2015.
 */

import javax.swing.*;


public class UnitConverter {

    /*VARIABLE DECLARATIONS********************************************************************************************/

     /* Constructor Variables**************************************************************/
     double fromValue; // User input value
     String toValue;  // Results returned to user
     String fromUnits;
     String toUnits;
     String conversionType;
     String selectedPrecision ;
     /*END CONSTRUCTOR VARIABLES************************************************************/



     /*Linear Variables*********************************************************************
     Converting all standard measures to inches
     Converting all metric measures to millimeters
     to simplify calculations / conversion
     ***************************************************************************************/

    //Standard
    private static final int INCHES = 1;
    private static final int FEET = INCHES * 12;
    private static final int MILES = FEET * 5280;

    //Metric
    private static final int MM = 1;
    private static final int METER = MM * 1000;
    private static final int KM = METER * 1000;

    //Inch to MM conversion
    private static final double INCH_TO_MM_CONVERSION = 25.4;
     /*END LINEAR VARIABLES**********************************************************************/



    /*Volume Variables************************************************************************
    "Gallons","Liters","Milliliters","Cubic Inches","Cubic Centimeters","Cubic Millimeters"
    converting all standard measures to cubic inches
    converting all metric measures to cubic millimeters
    to simplify calculations / comparisons*/

    //Standard
    private static final int CUBIC_INCHES = 1;
    private static final int GALLONS = CUBIC_INCHES * 231;

    //Metric
    private static final int CUBIC_MM = 1;
    private static final int CUBIC_CM = CUBIC_MM * 1000;
    private static final int MILLILITER = CUBIC_MM * 1000;
    private static final int LITER = MILLILITER * 1000;

    //Conversion Factor
    private static final double CUBIC_INCH_TO_CUBIC_MM_CONVERSION = 25.4*25.4*25.4;
    /*END OF VOLUME VARIABLES******************************************************************/



    /*Mass Variables***************************************************************************
    //"Tons","Pounds","Ounces","Tonnes","Kilograms","Grams"
    //Converting all standard measures to ounces
    //Converting all metric measures to grams
    // to simplify calculations / comparisons*/

    //Standard
    private static final int OUNCE = 1;
    private static final int POUND = OUNCE * 16;
    private static final int TON = POUND * 2000;

    //Metric
    private static final int GRAM = 1;
    private static final int KILOGRAM = GRAM *1000;
    private static final int TONNE = KILOGRAM * 1000;

    //Conversion Factor
    private static final double OUNCE_TO_GRAM_CONVERSION =  28.349523125;
    /*END OF MASS VARIABLES***********************************************************/



    /*Temperature Variables**************************************************************
    //"Fahrenheit", "Celsius","Kelvin"
    //Converting Kelvin to Fahrenheit requires conversion to Celsius First and visa versa*/

    private boolean isFromFahrenheit = false;
    private boolean isToFahrenheit = false;
    private boolean isFromCelsius = false;
    private boolean isToCelsius = false;
    private boolean isFromKelvin = false;
    private boolean isToKelvin = false;

    private String tempConversionFormula = "";
    /*END OF TEMPERATURE VARIABLES*********************************************************/

    /*________________________________________________________________________________________________________________*/


    /*METHODS**********************************************************************************************************/

    //Constructor for UnitConverter Class

    public UnitConverter(String fromValue, String fromUnits, String toUnits, String conversionType, String selectedPrecision){

        try {
            this.fromValue = Double.parseDouble(fromValue);
            this.fromUnits = fromUnits;
            this.toUnits = toUnits;
            this.conversionType = conversionType;
            this.selectedPrecision = selectedPrecision;


        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Input must be a number ","Input Error",JOptionPane.ERROR_MESSAGE);
        }

    }


    public String convertUnits(){

        try {

            if (conversionType.equals("Linear")) {
                toValue = convertLinear();
                getFormula(toValue); // get the formula to return to the Conversion Formula Text Area
            }

            else if (conversionType.equals("Volume")){
                toValue = convertVolume();
                getFormula(toValue);
            }

            else if (conversionType.equals("Mass")){
                toValue = convertMass();
                getFormula(toValue);
            }

            else if (conversionType.equals("Temperature")){
                toValue = convertTemperature(fromValue);
                getFormula(toValue);
            }

            return toValue;
        }

        catch (Exception e){

            return "";
        }


    }


     //"Miles","Feet","Inches","Kilometers","Meters","Millimeters"
     private String convertLinear(){

         double from = 0;
         double to = 0;
         boolean isFromStandard = false;
         boolean isToStandard = false;

         switch (fromUnits){
             case "Miles":
                 from = MILES;
                 isFromStandard = true;
                 break;
             case "Feet":
                 from = FEET;
                 isFromStandard = true;
                 break;
             case "Inches":
                 from = INCHES;
                 isFromStandard = true;
                 break;
             case "Kilometers":
                 from = KM;
                 isFromStandard = false;
                 break;
             case "Meters":
                 from = METER;
                 isFromStandard = false;
                 break;
             case "Millimeters":
                 from = MM;
                 isFromStandard = false;
         }

         switch (toUnits){
             case "Miles":
                 to = MILES;
                 isToStandard = true;
                 break;
             case "Feet":
                 to = FEET;
                 isToStandard = true;
                 break;
             case "Inches":
                 to = INCHES;
                 isToStandard = true;
                 break;
             case "Kilometers":
                 to = KM;
                 isToStandard = false;
                 break;
             case "Meters":
                 to = METER;
                 isToStandard = false;
                 break;
             case "Millimeters":
                 to = MM;
                 isToStandard = false;
         }


         if (isFromStandard == true && isToStandard == false){
             from = from *INCH_TO_MM_CONVERSION;
         }
         else if (isFromStandard == false && isToStandard == true){
             from = from / INCH_TO_MM_CONVERSION;
         }



         String answer = ""+ from / to * fromValue;

         return answer;
     }


    //"Gallons","Liters","Milliliters","Cubic Inches","Cubic Centimeters","Cubic Millimeters"
    private String convertVolume(){

        double from = 0;
        double to = 0;
        boolean isFromStandard = false;
        boolean isToStandard = false;

        switch (fromUnits){
            case "Gallons":
                from = GALLONS;
                isFromStandard = true;
                break;
            case "Cubic Inches":
                from = CUBIC_INCHES;
                isFromStandard = true;
                break;
            case "Liters":
                from = LITER;
                isFromStandard = false;
                break;
            case "Milliliters":
                from = MILLILITER;
                isFromStandard = false;
                break;
            case "Cubic Centimeters":
                from = CUBIC_CM;
                isFromStandard = false;
                break;
            case "Cubic Millimeters":
                from = CUBIC_MM;
                isFromStandard = false;
        }

        switch (toUnits){
            case "Gallons":
                to = GALLONS;
                isToStandard = true;
                break;
            case "Cubic Inches":
                to = CUBIC_INCHES;
                isToStandard = true;
                break;
            case "Liters":
                to = LITER;
                isToStandard = false;
                break;
            case "Milliliters":
                to = MILLILITER;
                isToStandard = false;
                break;
            case "Cubic Centimeters":
                to = CUBIC_CM;
                isToStandard = false;
                break;
            case "Cubic Millimeters":
                to = CUBIC_MM;
                isToStandard = false;
        }


        if (isFromStandard == true && isToStandard == false){
            from = from *CUBIC_INCH_TO_CUBIC_MM_CONVERSION;
        }
        else if (isFromStandard == false && isToStandard == true){
            from = from / CUBIC_INCH_TO_CUBIC_MM_CONVERSION;
        }


        String answer = ""+ from / to * fromValue;

        return answer;
    }


    //"Tons","Pounds","Ounces","Tonnes","Kilograms","Grams"
    private String convertMass(){

        double from = 0;
        double to = 0;
        boolean isFromStandard = false;
        boolean isToStandard = false;

        switch (fromUnits){
            case "Tons":
                from = TON;
                isFromStandard = true;
                break;
            case "Pounds":
                from = POUND;
                isFromStandard = true;
                break;
            case "Ounces":
                from = OUNCE;
                isFromStandard = true;
                break;
            case "Tonnes":
                from = TONNE;
                isFromStandard = false;
                break;
            case "Kilograms":
                from = KILOGRAM;
                isFromStandard = false;
                break;
            case "Grams":
                from = GRAM;
                isFromStandard = false;
        }

        switch (toUnits){
            case "Tons":
                to = TON;
                isToStandard = true;
                break;
            case "Pounds":
                to = POUND;
                isToStandard = true;
                break;
            case "Ounces":
                to = OUNCE;
                isToStandard = true;
                break;
            case "Tonnes":
                to = TONNE;
                isToStandard = false;
                break;
            case "Kilograms":
                to = KILOGRAM;
                isToStandard = false;
                break;
            case "Grams":
                to = GRAM;
                isToStandard = false;
        }


        if (isFromStandard == true && isToStandard == false){
            from = from * OUNCE_TO_GRAM_CONVERSION;
        }
        else if (isFromStandard == false && isToStandard == true){
            from = from / OUNCE_TO_GRAM_CONVERSION;
        }


        String answer = ""+ from / to * fromValue;

        return answer;

    }


    //"Fahrenheit", "Celsius","Kelvin"
    private String convertTemperature(double fromTemperature){

        String answer = ""; // returned by method
        double dAnswer = 0;

        switch (fromUnits) {

            case "Fahrenheit": {
                isFromFahrenheit = true;
                break;
            }

            case "Celsius": {
                isFromCelsius = true;
                break;
            }

            case "Kelvin":{
                isFromKelvin = true;
                break;
            }


        }

        switch (toUnits){

            case "Fahrenheit":{
                isToFahrenheit = true;
                break;
            }

            case "Celsius":{
                isToCelsius = true;
                break;
            }

            case "Kelvin":{
                isToKelvin = true;
                break;
            }

        }

        if (isFromCelsius == true && isToFahrenheit == true) {
            dAnswer = fromTemperature *1.8  + 32;
            answer = "" + dAnswer;
            tempConversionFormula = "F = C x 1.8 + 32";
        }

        else if (isFromCelsius == true && isToKelvin == true){

            dAnswer = fromTemperature + 273.15;
            answer = "" + dAnswer;
            tempConversionFormula = "K = C + 273.15";
        }

        else if (isFromFahrenheit == true && isToCelsius == true){

            dAnswer = (fromTemperature-32)/1.8;
            answer = "" + dAnswer;
            tempConversionFormula = "C = (F - 32) / 1.8";
        }

        else if (isFromFahrenheit == true && isToKelvin == true){
            dAnswer = (fromTemperature-32)/1.8 + 273.15;
            answer = "" + dAnswer;
            tempConversionFormula = "K = [(F - 32) / 1.8] + 273.15";
        }

        else if (isFromKelvin == true && isToCelsius == true){
            dAnswer = (fromTemperature - 273.15);
            answer = "" + dAnswer;
            tempConversionFormula = "C = K - 273.15";
        }

        else if (isFromKelvin == true && isToFahrenheit == true){
            dAnswer = (fromTemperature - 273.15)*1.8 +32;
            answer = ""+ dAnswer;
            tempConversionFormula = "F = (K - 273.15) x 1.8 + 32";
        }

        else {
            answer = "" + fromTemperature;
            tempConversionFormula = "1 Degree " + fromUnits + "= 1 Degree "  + toUnits;
        }


        return answer;
    }


     public String getFormula(String convertedValue){

         String singleUnit;
         String formattedValue;

        try {

            if (conversionType.equals("Temperature")){
                return tempConversionFormula;
             }

             else {

                 if (fromUnits.equals("Feet")) {
                     singleUnit = "Foot";
                 } else if (fromUnits.equals("Inches")) {
                     singleUnit = "Inch";
                 } else if (fromUnits.equals("Cubic Inches")) {
                     singleUnit = "Cubic Inch";
                 } else {
                     singleUnit = fromUnits.substring(0, fromUnits.length() - 1);
                 }

                  //formats to comma separator and two decimal precision i.e. 32,000.00
                  formattedValue = String.format("%," + setPrecision(selectedPrecision) + "f", Double.parseDouble(convertedValue) / fromValue);

                  return "1 " + singleUnit + " = " + formattedValue + " " + toUnits;

                 }

         }

         catch (Exception e) {
            System.out.println("Exception at getFormula Method");
             return "";
         }

     }


    //gets the precision from the precisionComboBox and uses it to set the precision
    //in the String.Format methods.
    public String setPrecision(String selectedPrecision){


        String precision = "";

        switch (selectedPrecision){

            case "0":{
                precision = ".0";
                break;
            }
            case "0.1":{
                precision = ".1";
                break;
            }

            case "0.12":{
                precision = ".2";
                break;
            }

            case "0.123":{
                precision = ".3";
                break;
            }

            case "0.1234":{
                precision = ".4";
                break;
            }

            case "0.12345":{
                precision = ".5";
                break;
            }

            case "0.123456":{
                precision = ".6";
            }
        }

        return precision;

    }


}
