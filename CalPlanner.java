import javax.swing.*;
import java.time.Year;
import java.util.Calendar;
import java.util.StringJoiner;


/**
 * CalPlanner Class
 * Contains all date calculation methods
 *
 * Created by Zane on 5/30/2015.
 *
 * Version 1.0.2 Updated to allow inferred year from entering only month and day.
 * Cleaned up code and added methods to handle redundant code. ZC 5/7/17
 */

 class CalPlanner {

    //ParseStartDate() Variables. Used to set the calendar objects date
    private int fromYear;
    private int fromMonth;
    private int fromDay;

    //ParseEndDate() Variables. Used to set the calendar objects date
    private int toYear;
    private int toMonth;
    private int toDay;

    //Used to determine if the input error dialog has show already
    private boolean hasDialogDisplayed;


    // lead time calculator variables
    private int leadTimeWeeks;
    private int leadTimeDays;

    // Date Parsing Variables
    private int[] parsedMonthDayYear = new int[3];
    private int[] inferredYearMonthDayYear = new int[3];
    private String formattedInputDate = "";
    private String formattedEndInputDate = "";
    private String formattedStartInputDate = "";


    CalPlanner (){

    }
    //Constructor for workday calculator
     CalPlanner (String fromDate, String toDate){

        //Load fromMonth, fromDay, fromYear variables

        parseStartDate(fromDate);

        //Load toDate, toMonth, toYear, Variables

        parseEndDate(toDate);

    }

    //Overloaded constructor for lead time calculator
     CalPlanner (String fromDate, String leadTimeWeeks, String leadTimeDays){


        // Load fromMonth, fromDay, fromYear variables
        parseStartDate(fromDate);

        try{
            this.leadTimeWeeks = Integer.parseInt(leadTimeWeeks);
            this.leadTimeDays = Integer.parseInt(leadTimeDays);
        }

        catch (Exception e){

            JOptionPane.showMessageDialog(null,"Invalid Input:\n Please Enter Whole Numbers for Lead Time");
        }
    }

    //Calculates the number of workdays between a given range of dates
    //Excepts the number of workdays per  week as a parameter
     String getWorkDays(String workDays){

        //if there were not any errors when parsing the dates
        if (fromYear >0 && fromMonth> 0 && fromDay >0 && toYear >0 && toMonth>0 & toDay>0 ) {

            //Creates Calendar objects and initializes them to the system date and time.
            Calendar fromCalendar = Calendar.getInstance();
            Calendar toCalendar = Calendar.getInstance();

            //Calendar objects months start at 0. i.e. Jan would be month 0, Feb month 1 etc...
            //User input will be in the normal calendar sense i.e. Jan would be month 1 etc...
            //So fromMonth has to have 1 subtracted from it to set the calendar object to the correct  date

            fromCalendar.set(fromYear, fromMonth-1, fromDay);
            toCalendar.set(toYear, toMonth-1, toDay);

            long workDayCount = 0;

            switch (workDays) {

                //Four work days per week
                case "4": {

                    //getTimeInMillis() allows comparative operations
                    //while fromDate is less than toDate
                     while (fromCalendar.getTimeInMillis()<= toCalendar.getTimeInMillis()) {

                         //If the day of the week is not Friday, Saturday, or Sunday add zero to the counter
                        if (fromCalendar.get(Calendar.DAY_OF_WEEK) == 1 || fromCalendar.get(Calendar.DAY_OF_WEEK) == 6 || fromCalendar.get(Calendar.DAY_OF_WEEK) == 7) {
                            workDayCount += 0;

                            //If the day of the week is Mon, Tues, Wed, or Thurs: Add 1 to the counter
                        } else {
                            workDayCount++;
                        }

                        //Increment the date. This ensures only dates between the original startDate and EndDate are evaluated
                        fromCalendar.add(Calendar.DATE, 1);
                    }

                    break;
                }

                //Five Workdays per Week
                case "5": {

                    while (fromCalendar.getTimeInMillis()<= toCalendar.getTimeInMillis()) {

                        //If the weekday is Saturday or Sunday add 0 to the counter else add 1
                        if (fromCalendar.get(Calendar.DAY_OF_WEEK) == 1 || fromCalendar.get(Calendar.DAY_OF_WEEK) == 7) {
                            workDayCount += 0;
                        }

                        else {
                            workDayCount++;
                        }

                        fromCalendar.add(Calendar.DATE, 1);
                    }

                    break;
                }

                //Six workdays per week
                case "6": {

                    while (fromCalendar.getTimeInMillis()<= toCalendar.getTimeInMillis()) {

                        //If the day of the week is Sunday add 0 to the counter. else add 1
                        if (fromCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
                            workDayCount += 0;
                        }

                        else {
                            workDayCount++;
                        }

                        fromCalendar.add(Calendar.DATE, 1);
                    }

                    break;
                }

                //Seven workdays per week
                case "7": {


                    //Count all days between original startdate and end date.
                    while (fromCalendar.getTimeInMillis() <= toCalendar.getTimeInMillis()){
                        workDayCount++;
                        fromCalendar.add(Calendar.DATE, 1);
                    }

                    break;
                }
            }

            //Returns number of workdays between set range of dates based on workdays per week as a String
            return "" + workDayCount;
        }

        //if there was an error when parsing dates: Returns empty String
        else{

            return "";

        }

    }

    // Calculates what the date will be from a given startDate using a given lead time set by the constructor
     String getLeadTimeFromStartDate(){

        String returnDate;

        //Converts leadTimeWeeks into days and adds it to leadTimeDays
        int totalLeadTime = (leadTimeWeeks*7)+leadTimeDays;

        //Creates Calendar object and initializes it to the system date and time.
        Calendar calendar = Calendar.getInstance();

        //Calendar objects months start at 0. i.e. Jan would be month 0, Feb month 1 etc...
        //User input will be in the normal calendar sense i.e. Jan would be month 1 etc...
        //So fromMonth has to have 1 subtracted from it to set the calendar object to the correct  date
        //This must be done in the set method for the calendar object because I use fromMonth in later if statements
        calendar.set(fromYear,fromMonth-1,fromDay);


        //Add given lead time to start date
        calendar.add(Calendar.DATE,totalLeadTime);

        //Formats date and returns it as a String
        returnDate = "" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);

        //If there were no errors when parsing the dates return the calculated date
        if (fromYear >0 && fromMonth>0 && fromDay>0) {
            return returnDate;
        }

        //else if there were errors when parsing dates return an empty string
        else {
            return "";
        }
    }

    //Calculates what the date will be using a given lead time and a given end date (deadline)
     String getLeadTimeFromEndDate(){

        String returnDate;

        //Converts leadTimeWeeks into days and adds it to leadTimeDays
        int totalLeadTime = (leadTimeWeeks*7)+leadTimeDays;

        // sets lead time to negative to remove time from calendar
        totalLeadTime *= -1;

        //Creates Calendar object and initializes it to the system date and time.
        Calendar calendar = Calendar.getInstance();

        //Calendar objects months start at 0. i.e. Jan would be month 0, Feb month 1 etc...
        //User input will be in the normal calendar sense i.e. Jan would be month 1 etc...
        //So fromMonth has to have 1 subtracted from it to set the calendar object to the correct  date
        //This must be done in the set method for the calendar object because I use fromMonth in later if statements
        calendar.set(fromYear,fromMonth-1,fromDay);

        //Add given lead time to end date. Since the lead time is negative, it subtracts the lead time.
        calendar.add(Calendar.DATE,totalLeadTime);

        //Formats date and returns it as a String
        returnDate = "" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);

        //If there were no errors when parsing the dates return the calculated date
        if (fromYear >0 && fromMonth>0 && fromDay>0) {
            return returnDate;
        }

        //else if there were errors when parsing dates return an empty string
        else {
            return "";
        }
    }

    //Takes the user input date and parses it into fromYear, fromMonth, and fromDay
    //Sets those variables to 0 if there was any error in user input such as too many digits
    //Or alphabetical characters entered instead of numerical characters
    private void parseStartDate(String date){

        ParseDate(date);
        fromMonth = parsedMonthDayYear[0];
        fromDay = parsedMonthDayYear[1];
        fromYear = parsedMonthDayYear[2];

        for (int i = 0; i< 3; i++){
            parsedMonthDayYear[i]=0;
        }

        formattedStartInputDate = formattedInputDate;

    }

    //Takes the user input date and parses it into toYear, toMonth, and toDay
    //Sets those variables to 0 if there was any error in user input such as too many digits
    //Or alphabetical characters entered instead of numerical characters
    private void parseEndDate(String date){

        ParseDate(date);
        toMonth = parsedMonthDayYear[0];
        toDay = parsedMonthDayYear[1];
        toYear = parsedMonthDayYear[2];

        for (int i = 0; i < 3; i++){
            parsedMonthDayYear[i]=0;
        }
        formattedEndInputDate = formattedInputDate;
    }

    // If the user doesn't enter a year explicitly assume they want the current year
    private void InferCurrentYear(String [] parsedDate) {

                //Try to parse array elements as integers if it fails the user entered non-numeric characters
                try {
                    inferredYearMonthDayYear[0] = Integer.parseInt(parsedDate[0]);
                    inferredYearMonthDayYear[1] = Integer.parseInt(parsedDate[1]);
                    inferredYearMonthDayYear[2] = Year.now().getValue();
                }

                //if the user input non-numeric characters, such as Jan, prompts them with a dialog describing
                //the correct input format
                catch (Exception e) {

                    if (!hasDialogDisplayed) {
                        JOptionPane.showMessageDialog(null, "Input Must Be in Number Date Format\n MM/DD/YYYY");
                        hasDialogDisplayed = true;
                    }

                }
        }

    private void ParseDate(String date){

        //User input gets stored in an array using / as a delimiter
        String [] parsedDate = date.split("/");
        int month =0;
        int day =0;
        int year =0;

        if (parsedDate.length == 2){

            InferCurrentYear(parsedDate);
            month = inferredYearMonthDayYear[0];
            day = inferredYearMonthDayYear[1];
            year = inferredYearMonthDayYear[2];

            for (int i=0; i<3; i++){
                inferredYearMonthDayYear[i]=0;
            }

        }

        //If there are not two or three distinct elements in the array the user must not have separated
        //Month, Day, and Year using a / (mm/dd/yyy).
        //Trying to use a - as a delimiter in case the user entered the date as mm-dd-yyyy
        else if (parsedDate.length < 2 ){

            parsedDate = date.split("-");

            //If the array still does not have three elements the user must have entered the date in the wrong format
            //prompts with dialog box detailing the correct format to use. Sets hasDialogDisplayed to true to prevent
            //the dialog from appearing twice, once for each textField startDate and endDate
            if(parsedDate.length < 2){
                if (!hasDialogDisplayed ) {
                    JOptionPane.showMessageDialog(null, "Invalid Input.\n Enter Date in MM/DD/YYYY Format");
                    hasDialogDisplayed = true;
                }
            }

            //else if the array has two or three elements the user entered the correct delimiter.
            //If the array only has two elements, the user must have entered only the Month and Day.
            //Infer they are wanting to use the current year.
            else if (parsedDate.length == 2){

                InferCurrentYear(parsedDate);
                month = inferredYearMonthDayYear[0];
                day = inferredYearMonthDayYear[1];
                year = inferredYearMonthDayYear[2];

                for (int i=0; i<3; i++){
                    inferredYearMonthDayYear[i]=0;
                }
            }
            else{
                //Try to parse array elements as integers if it fails the user entered non-numeric characters
                try{
                    month = Integer.parseInt(parsedDate[0]);
                    day = Integer.parseInt(parsedDate[1]);

                    //If the user enters the date in the format mm/dd/yy sets the date explicitly to this century
                    //i.e. 1/5/15 will be set to 1/5/2015 to keep it from being interpreted as 1/1/1915
                    if (parsedDate[2].length()==2){
                        year = Integer.parseInt(parsedDate[2]) +2000;
                    }

                    //if the user input the date as mm/dd/yyyy proceed with attempting to parse the integer.
                    else if (parsedDate[2].length() == 4) {
                        year = Integer.parseInt(parsedDate[2]);
                    }

                    //if the user entered any other format for year set fromYear to 0 to prevent any other calculations
                    //from taking place on it and show a dialog describing the correct format. set hasDialogDisplayed
                    //to true to prevent the dialog from appearing twice, once for each textField, startDate and endDate
                    else{
                        year = 0;
                        if (!hasDialogDisplayed) {
                            JOptionPane.showMessageDialog(null, "Invalid Input.\n Enter Date in MM/DD/YYYY Format");
                            hasDialogDisplayed = true;
                        }
                    }
                }

                //if the user input non-numeric characters, such as Jan, prompts them with a dialog describing
                //the correct input format
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Input Must Be in Number Date Format\n MM/DD/YYYY");
                }
            }

        }


        // else if the user input the date using the / delimiter correctly
        // proceed with trying to parse the date from the array elements
        else{

            //Try to parse array elements as integers if it fails the user entered non-numeric characters
            try{
                month = Integer.parseInt(parsedDate[0]);
                day = Integer.parseInt(parsedDate[1]);

                //If the user enters the date in the format mm/dd/yy sets the date explicitly to this century
                //i.e. 1/5/15 will be set to 1/5/2015 to keep it from being interpreted as 1/1/1915
                if (parsedDate[2].length()==2){
                    year = Integer.parseInt(parsedDate[2]) +2000;
                }

                //if the user input the date as mm/dd/yyyy proceed with attempting to parse the integer.
                else if (parsedDate[2].length() == 4) {
                    year = Integer.parseInt(parsedDate[2]);
                }

                //if the user entered any other format for year set fromYear to 0 to prevent any other calculations
                //from taking place on it and show a dialog describing the correct format. set hasDialogDisplayed
                //to true to prevent the dialog from appearing twice, once for each textField, startDate and endDate
                else{
                    year = 0;
                    if (!hasDialogDisplayed) {
                        JOptionPane.showMessageDialog(null, "Invalid Input.\n Enter Date in MM/DD/YYYY Format");
                        hasDialogDisplayed = true;
                    }
                }

            }

            //if the user input non-numeric characters, such as Jan, prompts them with a dialog describing
            //the correct input format
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Input Must Be in Number Date Format\n MM/DD/YYYY");
            }
        }

        parsedMonthDayYear[0] = month;
        parsedMonthDayYear[1]= day;
        parsedMonthDayYear[2]= year;

        if (parsedMonthDayYear[0]>0) {
            formattedInputDate = "" + parsedMonthDayYear[0] + "/" + parsedMonthDayYear[1] + "/" + parsedMonthDayYear[2];
        }
        else {
            formattedInputDate = "";
        }

    }

    String GetFormattedInputDate(String rawText,String singleInputStartOrEnd){
        if (singleInputStartOrEnd.equals("SingleInput")) {
             parseStartDate(rawText);
             return formattedInputDate;
        }
        else if (singleInputStartOrEnd.equals("Start")){
            parseStartDate(rawText);
            return formattedStartInputDate;
        }

        else if (singleInputStartOrEnd.equals("End")){
            parseEndDate(rawText);
            return formattedEndInputDate;
        }

        else
            return "";
    }


}//End of CalPlanner Class

