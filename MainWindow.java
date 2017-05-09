/**
 * Main Window for E-Assist App
 * Initializes all internal applications (CalPlanner, UnitConverter etc...)
 * Calls all necessary methods located in other classes to perform required actions
 * Created by Zane Cook on 5/15/2015.
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MainWindow extends JFrame{

/*VARIABLES/////////////////////////////////////////////////////////////////////////////////////////////////////////*/

   // Main Window Variables***************************************************************************
    Window window;

    JPanel mainPanel;
    JPanel menuPanel;

    JMenuBar menuBar;

    //Start Window menu
    JMenu applicationMenu;
    JMenu helpMenu;

    // Start Window menu items
    JMenuItem aboutMenuItem;
    JMenuItem calPlannerMenuItem;
    JMenuItem unitConverterMenuItem;

    //Splash Screen Label
    JLabel splashScreen;

    private String versionNumber = "1.0.2";



    //Unit Converter Variables***********************************************************************

    //Unit Converter menu
    JMenu conversionTypeMenu;
    JMenu ucApplicationMenu;

    // Unit Converter menu items
    JMenuItem linearMenuItem;
    JMenuItem volumeMenuItem;
    JMenuItem massMenuItem;
    JMenuItem temperatureMenuItem;
    JTextField ucInputField;
    JTextArea ucOutputField;
    JTextArea conversionFormulaTextArea;
    JLabel titleLabel;
    JLabel inputLabel;
    JLabel answerLabel;
    JLabel fromUnitLabel; // used as a designation by the input box
    JLabel toUnitLabel; // used as a designation by the result output box
    JLabel conversionFormulaLabel;
    JLabel fromLabel;
    JLabel toLabel;
    JLabel equalsLabel;
    JLabel precisionLabel;
    JButton ucCalculateButton;
    JButton ucSwapButton;
    JComboBox fromComboBox;
    JComboBox toComboBox;
    JComboBox precisionComboBox;
    Font equalLabelFont = new Font("Serif",Font.BOLD,18);
    Font titleLabelFont = new Font("Serif",Font.BOLD,20);

    String [] precision = {"0","0.1","0.12","0.123","0.1234","0.12345","0.123456"};




    //instantiated here so unitConverterOnChange can be used in the UnitConverterOnClick class
    //as well as the MainWindow class in the createUnitConverter() method
    UnitConverterOnChange unitConverterOnChange = new UnitConverterOnChange();


    //Cal Planner Variables****************************************************************************

    //Cal Planner menu
    JMenu planTypeMenu;
    JMenu cpApplicationMenu;

    // Cal Planner menu items
    JMenuItem leadTimeMenuItem;
    JMenuItem workDayMenuItem;

    // Cal Planner Components
    JLabel startDateLabel;
    JLabel endDateLabel;
    JLabel resultLabel;
    JLabel cpTitleLabel;
    JLabel workDayComboBoxLabel;
    JLabel leadTimeWeeksLabel;
    JLabel leadTimeDaysLabel;
    JTextField startDateInput;
    JTextField endDateInput;
    JTextField leadTimeWeeksInput;
    JTextField leadTimeDaysInput;
    JComboBox workDayComboBox;
    JButton cpCalculateButton;
    JButton stepButton;
    JRadioButton fromStartDateRadioButton;
    JRadioButton fromEndDateRadioButton;

    // Cal Planner Common Variables
    String workDaysTitleText = "Work Days Calculator";
    String leadTimeTitleText = "Lead Time Calculator";
    String leadTimeResults = "";
    private boolean enterKeyPressed = false;



    Font headerFont = new Font("Serif",Font.BOLD,14);
    Font cpResultsFont = new Font("Serif", Font.BOLD,18);


/*END OF VARIABLES////////////////////////////////////////////////////////////////////////////////////////////////*/


/*____________________________________________________________________________________________________________________*/



/*METHODS/////////////////////////////////////////////////////////////////////////////////////////////////////////*/


    /*Main Window and Application GUI Methods***********************************************/


    public  MainWindow(){

        createMainWindow();

    }


    //initializes all components in the main window
    private void createMainWindow(){

        MainWindowOnClick mainWindowOnClick = new MainWindowOnClick();

        window =  new Window("E-Assist",400,400);
        window.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        setIcon();
        Container contentPane = window.getContentPane();


        mainPanel = new JPanel();
        menuPanel = new JPanel();
        menuBar = new JMenuBar();
        applicationMenu = new JMenu("Application");
        helpMenu = new JMenu("Help");
        calPlannerMenuItem = new JMenuItem("CalPlanner");
        calPlannerMenuItem.addActionListener(mainWindowOnClick);
        unitConverterMenuItem = new JMenuItem("Unit Converter");
        unitConverterMenuItem.addActionListener(mainWindowOnClick);
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(mainWindowOnClick);

        contentPane.add(mainPanel);

        addSplashScreen(); // adds the E-Assist Logo

        window.setJMenuBar(menuBar);

        menuBar.add(applicationMenu, LEFT_ALIGNMENT);
        menuBar.add(helpMenu);
        applicationMenu.add(calPlannerMenuItem);
        applicationMenu.add(unitConverterMenuItem);
        helpMenu.add(aboutMenuItem);

       window.setResizable(false);
       window.setVisible(true);

    }


    //Sets image icon for the window
    private void setIcon(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(window.getClass().getResource("/Window Icon.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        window.setIconImage(image);
    }


    //Adds the E-Assist Logo as a splash screen on the main window.
    private void addSplashScreen(){

        BufferedImage image = null;
        try {
            image = ImageIO.read(window.getClass().getResource("/Splash Screen Icon.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        splashScreen = new JLabel(new ImageIcon(image));
        mainPanel.add(splashScreen);


    }

    //Refreshes the window to redraw components
    private void refreshWindow(){

        window.setVisible(false);

        try{
            Thread.sleep(140);
        }
        catch (Exception e){

            System.out.println(e);
        }

        window.setVisible(true);
    }

    /*End of Main Window and Application GUI Methods******************************************************/


    /*Unit Converter Methods********************************************************************************/

    //initializes all components in the unit converter window
    private void createUnitConverter(){

        UnitConverterOnClick unitConverterOnClick = new UnitConverterOnClick();


        window.setTitle("E-Assist (Unit Converter)");

        //Removing any existing components
        menuBar.removeAll();
        mainPanel.removeAll();

        //Adding Unit Conversion Menus
        menuBar.add(ucApplicationMenu = new JMenu("Application"));
        menuBar.add(conversionTypeMenu = new JMenu("Conversion Type"));
        menuBar.add(helpMenu);

        //Adding Conversion Type Menu Items
        conversionTypeMenu.add(linearMenuItem = new JMenuItem("Linear"));
        conversionTypeMenu.add(volumeMenuItem = new JMenuItem("Volume"));
        conversionTypeMenu.add(massMenuItem = new JMenuItem("Mass"));
        conversionTypeMenu.add(temperatureMenuItem = new JMenuItem("Temperature"));

        //Adding Unit Conversion Application Menu Items
        ucApplicationMenu.add(calPlannerMenuItem);

        //Adding Help Menu Items
        helpMenu.add(aboutMenuItem);


        mainPanel.setLayout(null);



        // adding all components (labels, text fields, buttons, and combo boxes)
        mainPanel.add(titleLabel = new JLabel("Unit Conversion"));
        mainPanel.add(fromComboBox = new JComboBox());
        mainPanel.add(toComboBox = new JComboBox());
        mainPanel.add(fromLabel = new JLabel("From"));
        mainPanel.add(toLabel = new JLabel("To"));

        mainPanel.add(precisionLabel = new JLabel("Precision"));
        mainPanel.add(precisionComboBox = new JComboBox(precision));

        mainPanel.add(inputLabel = new JLabel("Enter Amount"));
        mainPanel.add(ucInputField = new JTextField());
        mainPanel.add(fromUnitLabel = new JLabel("From Units"));

        mainPanel.add(equalsLabel = new JLabel("="));

        mainPanel.add(answerLabel = new JLabel("Result"));
        mainPanel.add(ucOutputField= new JTextArea());
        mainPanel.add(toUnitLabel = new JLabel("To Units"));

        mainPanel.add(conversionFormulaLabel = new JLabel("Conversion Formula:"));
        mainPanel.add(conversionFormulaTextArea = new JTextArea());

        mainPanel.add(ucCalculateButton = new JButton("Calculate"));
        mainPanel.add(ucSwapButton = new JButton("Swap Units"));


        // setting position and size (x pos, y pos, width, height)

        titleLabel.setBounds(0,5,400,20);
        fromLabel.setBounds(5,35,50,20);
        fromComboBox.setBounds(40,38,150,20);
        toLabel.setBounds(200,35,50,20);
        toComboBox.setBounds(220,38,150,20);

        precisionLabel.setBounds(45,75,100,20);
        precisionComboBox.setBounds(120,75,100,20);

        inputLabel.setBounds(20,110,100,20);
        answerLabel.setBounds(60,155,75,20);
        ucInputField.setBounds(120,113,150,20);
        ucOutputField.setBounds(120,158,150,20);
        fromUnitLabel.setBounds(290,110,75,20);
        equalsLabel.setBounds(190,125,75,40);
        toUnitLabel.setBounds(290,155,75,20);

        conversionFormulaLabel.setBounds(130,200,200,20);
        conversionFormulaTextArea.setBounds(0,225,400,60);

        ucCalculateButton.setBounds(250, 300, 100, 35);
        ucSwapButton.setBounds(50, 300, 100, 35);


        //setting component properties
        ucOutputField.setEditable(false);
        conversionFormulaTextArea.setEditable(false);
        equalsLabel.setFont(equalLabelFont);
        titleLabel.setFont(titleLabelFont);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        conversionFormulaTextArea.setFont(new Font("Arial", Font.BOLD,12));




        precisionComboBox.setBackground(Color.WHITE);
        conversionFormulaTextArea.setBackground(null);





        //Adding Action Listeners
        linearMenuItem.addActionListener(unitConverterOnClick);
        volumeMenuItem.addActionListener(unitConverterOnClick);
        massMenuItem.addActionListener(unitConverterOnClick);
        temperatureMenuItem.addActionListener(unitConverterOnClick);
        ucCalculateButton.addActionListener(unitConverterOnClick);
        ucSwapButton.addActionListener(unitConverterOnClick);

        //Adding Item Listeners
        fromComboBox.addItemListener(unitConverterOnChange);
        toComboBox.addItemListener(unitConverterOnChange);
        precisionComboBox.addItemListener(unitConverterOnChange);

        //Adding Key Listeners Anonymous inner class
        ucInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                //NOT USED

            } // not used

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER){

                    ucCalculateResults();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                //NOT USED
            } // not used
        }); //calculates results if the user clicks enter


        // Refreshing Window. The components will not redraw without this.
      refreshWindow();

    }


    /* Calculate Results for Unit Converter
     controls all results calculations for unit conversion. is called by the unitConverterOnClick object
     and the anonymous inner class for when a user presses enter  in the ucInputField*/
    private void ucCalculateResults(){

        // Extracts conversion type from the title label. e.g. Linear gets extracted from "Linear Conversion"
        //It stores it in the conversionType variable and passes it to the UnitConverter constructor
        // This is used in the UnitConverter class to determine which conversion method to use.
        String conversionType = titleLabel.getText();
        int spaceDelimiter = conversionType.indexOf(" ");
        conversionType = conversionType.substring(0,spaceDelimiter);

        //Construction instantiation of UnitConverter Class.
        UnitConverter unitConverter = new UnitConverter(ucInputField.getText(),""+fromComboBox.getSelectedItem(),""+toComboBox.getSelectedItem(),conversionType,""+precisionComboBox.getSelectedItem());

        String answer = unitConverter.convertUnits(); //gets the converted value of the passed in vales
        String formula = unitConverter.getFormula(answer);// gets the conversion formula used to convert the passed in values

        //Sets the decimal precision e.g. 32,000.0 or 32,000.01 etc...
        String selectedFormat = "%,"+unitConverter.setPrecision("" + precisionComboBox.getSelectedItem())+"f";

        try {
            ucOutputField.setText(String.format(selectedFormat, Double.parseDouble(answer)));
            conversionFormulaTextArea.setText(formula);
            unitConverter = null;
        }
        catch (Exception e){

            ucOutputField.setText("");
            conversionFormulaTextArea.setText("");

            System.out.println("Exception at ucCalculateResults Method");
        }


    }

    /*End of Unit Converter Methods*************************************************************************/


    /*CalPlanner Methods*************************************************************************************/

    //initializes all components in the CalPlanner window
    private void createCalPlanner(){

        CalPlannerOnClick calPlannerOnClick = new CalPlannerOnClick();
        CalPlannerOnTab calPlannerOnTab = new CalPlannerOnTab();
        CalPlannerOnEnter calPlannerOnEnter = new CalPlannerOnEnter();

        window.setTitle("E-Assist (CalPlanner)");

        //Removing any existing components
        menuBar.removeAll();
        mainPanel.removeAll();

        //Adding Menu Items
        menuBar.add(cpApplicationMenu = new JMenu("Application"));
        cpApplicationMenu.add(unitConverterMenuItem);

        menuBar.add(planTypeMenu = new JMenu("Plan Type"));
        planTypeMenu.add(leadTimeMenuItem = new JMenuItem("Lead Time Planner"));
        planTypeMenu.add(workDayMenuItem = new JMenuItem("Work Day Planner"));

        menuBar.add(helpMenu);


        //Adding Components

        mainPanel.setLayout(null);


        mainPanel.add(cpTitleLabel = new JLabel("Date Calculator"));
        mainPanel.add(startDateLabel = new JLabel("Starting Date"));
        mainPanel.add(startDateInput = new JTextField());
        mainPanel.add(endDateLabel = new JLabel("Ending Date"));
        mainPanel.add(endDateInput = new JTextField());


        mainPanel.add(resultLabel = new JLabel("Result"));

        mainPanel.add(fromStartDateRadioButton = new JRadioButton("From Start Date"));
        mainPanel.add(fromEndDateRadioButton = new JRadioButton("From End Date"));

        mainPanel.add(stepButton = new JButton("Step"));
        mainPanel.add(cpCalculateButton = new JButton("Calculate"));


        //Setting position of components in window
        cpTitleLabel.setBounds(0,5,400,20);

        startDateLabel.setBounds(0,50,200,20);
        endDateLabel.setBounds(200,50,200,20);
        startDateInput.setBounds(60,75,85,25);
        endDateInput.setBounds(260,75,85,25);

        resultLabel.setBounds(0,200,400,25);

        fromStartDateRadioButton.setBounds(150,240,200,20);
        fromEndDateRadioButton.setBounds(150,270,200,20);

        stepButton.setBounds(50, 300, 100, 35);
        cpCalculateButton.setBounds(153, 300, 100, 35);

        //setting alignment and font
        cpTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cpTitleLabel.setFont(titleLabelFont);

        startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startDateLabel.setFont(headerFont);

        endDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endDateLabel.setFont(headerFont);

        startDateInput.setHorizontalAlignment(SwingConstants.CENTER);
        endDateInput.setHorizontalAlignment(SwingConstants.CENTER);

        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(cpResultsFont);

        //Adding Action Listeners
        leadTimeMenuItem.addActionListener(calPlannerOnClick);
        workDayMenuItem.addActionListener(calPlannerOnClick);
        fromStartDateRadioButton.addActionListener(calPlannerOnClick);
        fromEndDateRadioButton.addActionListener(calPlannerOnClick);
        stepButton.addActionListener(calPlannerOnClick);
        stepButton.addKeyListener(calPlannerOnEnter);
        cpCalculateButton.addActionListener(calPlannerOnClick);
        cpCalculateButton.addKeyListener(calPlannerOnEnter);

        startDateInput.addFocusListener(calPlannerOnTab);
        startDateInput.addKeyListener(calPlannerOnEnter);

        endDateInput.addFocusListener(calPlannerOnTab);
        endDateInput.addKeyListener(calPlannerOnEnter);

        //Starts the window in Lead Time Calculator
        createLeadTimeCalculator();

    }

    //Sets up the GUI for Lead Time Calculator
    private void createLeadTimeCalculator(){

        //Instantiating Action Listeners
        CalPlannerOnTab calPlannerOnTab = new CalPlannerOnTab();
        CalPlannerOnEnter calPlannerOnEnter = new CalPlannerOnEnter();

        //If you are not switching from work day planner throws an error.
        //WorkDay combo box and label don't exist yet.
        try{
            mainPanel.remove(workDayComboBox);
            mainPanel.remove(workDayComboBoxLabel);
        }
        catch (Exception e){

            System.out.println(e);
        }

        //If you are not already in the lead time calculator application create components.
        //Otherwise it doubles them up and clutters the UI when you switch to work day planner
        if (!cpTitleLabel.getText().equals(leadTimeTitleText)) {

            cpTitleLabel.setText(leadTimeTitleText);

            mainPanel.add(leadTimeWeeksLabel = new JLabel("Weeks"));
            mainPanel.add(leadTimeWeeksInput = new JTextField("0"));

            mainPanel.add(leadTimeDaysLabel = new JLabel("Days"));
            mainPanel.add(leadTimeDaysInput = new JTextField("0"));


            //Only switching the visibility of these components
            //so the action listener doesn't have to be reset
            stepButton.setVisible(true);
            fromStartDateRadioButton.setVisible(true);
            fromEndDateRadioButton.setVisible(true);

            //Setting position in the window
            leadTimeWeeksLabel.setBounds(140, 125, 50, 25);
            leadTimeWeeksInput.setBounds(140, 150, 50, 25);

            leadTimeDaysLabel.setBounds(210, 125, 50, 25);
            leadTimeDaysInput.setBounds(210, 150, 50, 25);

            cpCalculateButton.setBounds(250, 300, 100, 35);

            //setting font and alignment
            leadTimeWeeksLabel.setFont(headerFont);
            leadTimeWeeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leadTimeWeeksInput.setHorizontalAlignment(SwingConstants.CENTER);

            leadTimeDaysLabel.setFont(headerFont);
            leadTimeDaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leadTimeDaysInput.setHorizontalAlignment(SwingConstants.CENTER);

            //removes any result from workday planner if switching from there
            resultLabel.setText("Result");

            //initializes the lead time calculator to be from start date
            fromStartDateRadioButton.setSelected(true);
            leadTimeFromStartDate();

            //Adding action listeners
            leadTimeWeeksInput.addFocusListener(calPlannerOnTab);
            leadTimeWeeksInput.addKeyListener(calPlannerOnEnter);

            leadTimeDaysInput.addFocusListener(calPlannerOnTab);
            leadTimeDaysInput.addKeyListener(calPlannerOnEnter);

            //initializes the cursor to be in the start date input box
            startDateInput.grabFocus();

            //Refreshes window. Components will not redraw without it
            refreshWindow();
        }
    }

    //Sets up the GUI for Work Day Calculator
    private void createWorkDayCalculator(){

        //Instantiates action listener class
        CalPlannerOnChange calPlannerOnChange = new CalPlannerOnChange();

        //If you are not switching from lead time planner
        // lead time components do not exist yet
        try{

            mainPanel.remove(leadTimeWeeksLabel);
            mainPanel.remove(leadTimeWeeksInput);
            mainPanel.remove(leadTimeDaysLabel);
            mainPanel.remove(leadTimeDaysInput);

        }

        catch(Exception ex){
            System.out.println(ex);

        }

        //If you are not already in the work day planner create components
        //Otherwise multiple components are made and it clutters the screen
        //when switching to lead time calculator
        if(!cpTitleLabel.getText().equals(workDaysTitleText)) {

            //only setting these components visibility to false
            //to keep from having to reset their action listeners
            fromStartDateRadioButton.setVisible(false);
            fromEndDateRadioButton.setVisible(false);
            stepButton.setVisible(false);

            //array for combo box work day selections
            String[] workDays = {"4", "5", "6", "7"};

            cpTitleLabel.setText(workDaysTitleText);

            //adding components
            mainPanel.add(workDayComboBox = new JComboBox(workDays));
            mainPanel.add(workDayComboBoxLabel = new JLabel("No. of Workdays"));

            //setting position in the window
            workDayComboBoxLabel.setBounds(0, 125, 400, 20);
            workDayComboBox.setBounds(165, 150, 75, 20);

            cpCalculateButton.setBounds(153, 300, 100, 35);

            //Sets combobox color to white
            workDayComboBox.setBackground(Color.WHITE);


            //enables both date input boxes and sets the focus to start date
            startDateInput.setEnabled(true);
            startDateInput.setBackground(Color.WHITE);
            startDateInput.grabFocus();
            endDateInput.setEnabled(true);
            endDateInput.setBackground(Color.WHITE);

            //Setting font and alignment
            workDayComboBoxLabel.setHorizontalAlignment(SwingConstants.CENTER);
            workDayComboBoxLabel.setFont(headerFont);

            //clears any result from lead time planner if swtiching from it
            resultLabel.setText("Result");

            //adding action listeners
            workDayComboBox.addItemListener(calPlannerOnChange);

            //refreshes window. components won't redraw without this
            refreshWindow();
        }

    }

    //Calculates results using the CalPlanner class
    private void cpCalculateResults(){

        //if you are in work day planner
        if (cpTitleLabel.getText().equals(workDaysTitleText)) {

            //constructor for cal planner. Initializes start date and end date in CalPlanner
            CalPlanner calPlanner = new CalPlanner(startDateInput.getText(), endDateInput.getText());

            //Uses getWorkDays from CalPlanner to determine number of work days within
            //a given date range and returns it to the result string

               String result = calPlanner.getWorkDays("" + workDayComboBox.getSelectedItem());

            startDateInput.setText(calPlanner.GetFormattedInputDate(startDateInput.getText(),"Start"));
            endDateInput.setText(calPlanner.GetFormattedInputDate(endDateInput.getText(),"End"));

            //If there is an input error getWorkDays returns an empty string
            //if the result string was not returned empty display the results
            if (!result.equals("")){

                resultLabel.setText(result + " Working Days Available");
            }

        }

        //if you are in the lead time calculator
        else if(cpTitleLabel.getText().equals(leadTimeTitleText)){

            String startDate ="";

            //determines whether to get input from start date or end date
            if (fromStartDateRadioButton.isSelected()){
                startDate = startDateInput.getText();
            }
            else if (fromEndDateRadioButton.isSelected()){
                startDate = endDateInput.getText();
            }

            //CalPlanner constructor initializes start date, lead time weeks, and lead time days
            CalPlanner calPlanner = new CalPlanner(startDate,leadTimeWeeksInput.getText(),leadTimeDaysInput.getText());

            //determines whether to add or subtract from the given start date
            //using CalPlanners getLeadTimeFromStartDate or getLeadTimeFromEndDate methods
            if(fromStartDateRadioButton.isSelected()) {
                leadTimeResults = calPlanner.getLeadTimeFromStartDate();
                startDateInput.setText(calPlanner.GetFormattedInputDate(startDateInput.getText(),"SingleInput"));
            }

            else if (fromEndDateRadioButton.isSelected()){
                leadTimeResults = calPlanner.getLeadTimeFromEndDate();
                endDateInput.setText(calPlanner.GetFormattedInputDate(endDateInput.getText(),"SingleInput"));
            }

            //getLeadTimeFromStartDate and getLeadTimeFromEndDate return an empty string
            //if there is an error. if the returned string is not empty display results
            if(!leadTimeResults.equals("")){

                resultLabel.setText("Date will be " + leadTimeResults);
            }
            else {
                if(fromStartDateRadioButton.isSelected()){
                    startDateInput.selectAll();
                    enterKeyPressed = false;
                }
                else if (fromEndDateRadioButton.isSelected()){
                    endDateInput.selectAll();
                    enterKeyPressed = false;
                }
            }

        }
    }

    private String cpParseInput(String date, String singleInputStartOrEnd){
        CalPlanner calPlannerParseOnly =  new CalPlanner();

        String formattedDate = "";

        switch (singleInputStartOrEnd){

            case ("Single Input"):{
                formattedDate = calPlannerParseOnly.GetFormattedInputDate(date,"SingleInput");
            }
            break;

            case ("Start"):{
                formattedDate = calPlannerParseOnly.GetFormattedInputDate(date,"Start");
            }
            break;

            case ("End"):{
              formattedDate= calPlannerParseOnly.GetFormattedInputDate(date,"End");
            }

        }

        return formattedDate;
    }

    //Allows you to use the result of a lead time calculation
    //as the input for another lead time calculation
    private void stepLeadTime(){

        //if there is a lead time result to use for input
        //place that result in the start date or end date input box
        //depending on which mode you are in
        if(!leadTimeResults.equals("")){

            if(fromStartDateRadioButton.isSelected()) {
                startDateInput.setText(leadTimeResults);
            }

            else if (fromEndDateRadioButton.isSelected()){
                endDateInput.setText(leadTimeResults);
            }

            //sets focus back to the lead time weeks input box so
            //you can immediately enter another leadtime
            leadTimeWeeksInput.grabFocus();

        }

    }

    //enables start date input box and disables end date input box
    //initializes the lead time weeks and days to zero
    //sets focus to start date
    private void leadTimeFromStartDate(){

        fromEndDateRadioButton.setSelected(false);
        endDateInput.setText("");
        endDateInput.setEnabled(false);
        endDateInput.setBackground(Color.LIGHT_GRAY);

        startDateInput.setEnabled(true);
        startDateInput.setBackground(Color.WHITE);
        startDateInput.grabFocus();

        leadTimeWeeksInput.setText("0");
        leadTimeDaysInput.setText("0");

    }

    //enables end date input box and disables start date input box
    //initializes the lead time weeks and days to zero
    //sets focus to end date
    private void leadTimeFromEndDate(){

        fromStartDateRadioButton.setSelected(false);
        startDateInput.setText("");
        startDateInput.setEnabled(false);
        startDateInput.setBackground(Color.LIGHT_GRAY);

        endDateInput.setEnabled(true);
        endDateInput.setBackground(Color.WHITE);
        endDateInput.grabFocus();

        leadTimeWeeksInput.setText("0");
        leadTimeDaysInput.setText("0");
    }


    /*End of CalPlanner Methods*******************************************************************************/


/*END OF METHODS/////////////////////////////////////////////////////////////////////////////////////////////////////*/


/*___________________________________________________________________________________________________________________*/



/*INTERNAL ACTION CLASSES////////////////////////////////////////////////////////////////////////////////////////////*/

    /*Unit Converter Internal Action Classes****************************************************************/

    //controls what happens when the user selects a menu item in the main startup window
    private class MainWindowOnClick implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == calPlannerMenuItem){
                createCalPlanner();
            }

            else if (e.getSource()== unitConverterMenuItem){

                createUnitConverter();
            }

            else if (e.getSource()== aboutMenuItem){
                JOptionPane.showMessageDialog(null," E-Assist Version "+ versionNumber +" \n A multi application tool to"+
                        " assist with everyday calculations / conversions.","About E-Assist", JOptionPane.PLAIN_MESSAGE);
            }

        }


    } // End of MainWindowOnClick Class


    //controls what happens when the user selects a conversion type or clicks calculate / cancel
    private class UnitConverterOnClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==linearMenuItem){
                addLinearElements();
                clearText();


                //set precision back to whole numbers
                precisionComboBox.setSelectedItem(precisionComboBox.getItemAt(0));
            }

            else if (e.getSource() == volumeMenuItem){

                addVolumeElements();
                clearText();

                //set precision back to whole numbers
                precisionComboBox.setSelectedItem(precisionComboBox.getItemAt(0));
            }
            else if (e.getSource() == massMenuItem){

                addMassElements();
                clearText();

                //set precision back to whole numbers
                precisionComboBox.setSelectedItem(precisionComboBox.getItemAt(0));
            }

            else if (e.getSource() == temperatureMenuItem){

                addTemperatureElements();
                clearText();

                //set precision back to whole numbers
                precisionComboBox.setSelectedItem(precisionComboBox.getItemAt(0));
            }

            else if (e.getSource()==ucCalculateButton){

                if (!ucInputField.getText().equals("")) {
                    ucCalculateResults();
                }
            }

            else if (e.getSource() == ucSwapButton){
                //Swaps From and To Units
                JComboBox temp = new JComboBox();
                temp.addItem(fromComboBox.getSelectedItem());
                fromComboBox.setSelectedItem(toComboBox.getSelectedItem());
                toComboBox.setSelectedItem((temp.getItemAt(0)));
            }


        }

        //adds the linear units into the to / from combo boxes
        private void addLinearElements(){

            //If unit converter is not already set up for linear
            if (!titleLabel.getText().equals("Linear Conversion")) {

                String [] values = {"Miles","Feet","Inches","Kilometers","Meters","Millimeters"};
                mainPanel.remove(fromComboBox);
                mainPanel.remove(toComboBox);
                mainPanel.add(fromComboBox = new JComboBox(values));
                mainPanel.add(toComboBox = new JComboBox(values));
                fromComboBox.addItemListener(unitConverterOnChange);
                toComboBox.addItemListener(unitConverterOnChange);

                fromComboBox.setBounds(40, 38, 150, 20);
                toComboBox.setBounds(220, 38, 150, 20);

                fromComboBox.setBackground(Color.WHITE);
                toComboBox.setBackground(Color.WHITE);

                titleLabel.setText("Linear Conversion");
                fromUnitLabel.setText("" + fromComboBox.getSelectedItem());
                toUnitLabel.setText("" + toComboBox.getSelectedItem());
            }
           // window.setVisible(false);
          //  window.setVisible(true);

        }


        //adds the volume units into the to / from combo boxes
        private void addVolumeElements(){

            //If unit converter is not already set up for volume
            if(!titleLabel.getText().equals("Volume Conversion")) {

                String[] values = {"Gallons", "Liters", "Milliliters", "Cubic Inches", "Cubic Centimeters", "Cubic Millimeters"};
                mainPanel.remove(fromComboBox);
                mainPanel.remove(toComboBox);
                mainPanel.add(fromComboBox = new JComboBox(values));
                mainPanel.add(toComboBox = new JComboBox(values));
                fromComboBox.addItemListener(unitConverterOnChange);
                toComboBox.addItemListener(unitConverterOnChange);


                fromComboBox.setBounds(40, 38, 150, 20);
                toComboBox.setBounds(220, 38, 150, 20);

                fromComboBox.setBackground(Color.WHITE);
                toComboBox.setBackground(Color.WHITE);

                titleLabel.setText("Volume Conversion");
                fromUnitLabel.setText("" + fromComboBox.getSelectedItem());
                toUnitLabel.setText("" + toComboBox.getSelectedItem());
            }
           // window.setVisible(false);
          //  window.setVisible(true);
        }


        //adds the mass units into the to / from combo boxes
        private void addMassElements(){

            //If unit converter is not already set up for mass
            if (!titleLabel.getText().equals("Mass Conversion")) {

                String[] values = {"Tons", "Pounds", "Ounces", "Tonnes", "Kilograms", "Grams"};
                mainPanel.remove(fromComboBox);
                mainPanel.remove(toComboBox);
                mainPanel.add(fromComboBox = new JComboBox(values));
                mainPanel.add(toComboBox = new JComboBox(values));
                fromComboBox.addItemListener(unitConverterOnChange);
                toComboBox.addItemListener(unitConverterOnChange);


                fromComboBox.setBounds(40, 38, 150, 20);
                toComboBox.setBounds(220, 38, 150, 20);

                fromComboBox.setBackground(Color.WHITE);
                toComboBox.setBackground(Color.WHITE);

                titleLabel.setText("Mass Conversion");
                fromUnitLabel.setText("" + fromComboBox.getSelectedItem());
                toUnitLabel.setText("" + toComboBox.getSelectedItem());

            }

            //window.setVisible(false);
           // window.setVisible(true);
        }


        //adds the temperature units to the to / from combo boxes
        private void addTemperatureElements(){

            //If unit converter is not already set up for temperature
            if (!titleLabel.getText().equals("Temperature Conversion")) {

                String[] values = {"Fahrenheit", "Celsius", "Kelvin"};
                mainPanel.remove(fromComboBox);
                mainPanel.remove(toComboBox);
                mainPanel.add(fromComboBox = new JComboBox(values));
                mainPanel.add(toComboBox = new JComboBox(values));
                fromComboBox.addItemListener(unitConverterOnChange);
                toComboBox.addItemListener(unitConverterOnChange);


                fromComboBox.setBounds(40, 38, 150, 20);
                toComboBox.setBounds(220, 38, 150, 20);
                fromComboBox.setBackground(Color.WHITE);
                toComboBox.setBackground(Color.WHITE);

                titleLabel.setText("Temperature Conversion");
                fromUnitLabel.setText("" + fromComboBox.getSelectedItem());
                toUnitLabel.setText("" + toComboBox.getSelectedItem());

            }
           // window.setVisible(false);
           // window.setVisible(true);
        }


        //clears text from the input field,output field, and conversion formula field
        //used when the user switches conversion types e.g. linear to mass.
        private void clearText(){
            ucInputField.setText("");
            ucOutputField.setText("");
            conversionFormulaTextArea.setText("");
        }


    } // End of UnitConverterOnClick Class


    //changes the from and to unit labels that are by the input boxes when the user selects a from / to unit
    private class UnitConverterOnChange implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
             if (e.getSource()==fromComboBox){

                fromUnitLabel.setText(""+fromComboBox.getSelectedItem());

                 if(!ucInputField.getText().equals("")){
                     ucCalculateResults();
                 }

            }

            else if (e.getSource() == toComboBox){
                toUnitLabel.setText(""+toComboBox.getSelectedItem());

                 if(!ucInputField.getText().equals("")){
                     ucCalculateResults();
                 }
            }

            else if (e.getSource() == precisionComboBox){

                 if(!ucInputField.getText().equals("")) {
                     ucCalculateResults();
                 }
             }
        }

    } // End of UnitConverterOnChange class

    /*End of Unit Converter Internal Action Classes****************************************************/


    /*CalPlanner Internal Action Classes***************************************************************/

    private class CalPlannerOnClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == leadTimeMenuItem){

              createLeadTimeCalculator();

            }

            else if (e.getSource() == workDayMenuItem){

               createWorkDayCalculator();
            }

            else if (e.getSource() == cpCalculateButton){
                cpCalculateResults();
            }

            else if (e.getSource() == stepButton){
                stepLeadTime();
            }

            else if (e.getSource() == fromStartDateRadioButton){
              leadTimeFromStartDate();
            }

            else if (e.getSource() == fromEndDateRadioButton){
              leadTimeFromEndDate();
            }



        }


    } // End of CalPlannerOnClick Class

    //Selects all text in the input boxes that a user tabs to.
    //This allows them to immediately overwrite the contents of the input box
    private class CalPlannerOnTab implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {

            if(e.getSource() == leadTimeWeeksInput){

                leadTimeWeeksInput.selectAll();
            }

            else if (e.getSource() == leadTimeDaysInput){

                leadTimeDaysInput.selectAll();
            }

            else if (e.getSource() == startDateInput){

                startDateInput.selectAll();

            }

            else if (e.getSource() == endDateInput){

                endDateInput.selectAll();
            }

        }

        @Override
        public void focusLost(FocusEvent e) {

            //Formats Input Date as user tabs from one input box to another
            //cpParseInput only parses the input and does no calculations on the input



                if (e.getSource() == startDateInput && !startDateInput.getText().equals("")&& !enterKeyPressed) {
                    String formattedDate = cpParseInput(startDateInput.getText(), "Start");
                    startDateInput.setText(formattedDate);
                    if (formattedDate.equals("")){
                        startDateInput.grabFocus();
                    }
                } else if (e.getSource() == endDateInput && !endDateInput.getText().equals("") && !enterKeyPressed) {
                    String formattedDate = cpParseInput(endDateInput.getText(), "End");
                    endDateInput.setText(formattedDate);
                    if(formattedDate.equals("")){
                        endDateInput.grabFocus();
                    }
                }




        }
    } // End of CalPlannerOnTab Class

    //Allows the user to calculate results by clicking enter
    //while in any of the input boxes, stepButton, or calculateButton
    private class CalPlannerOnEnter implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                enterKeyPressed = true;//Keeps from parsing the data again when focus is lost in the input box
                cpCalculateResults();

                //sends focus to stepButton if it doesn't already have it and you are in the
                //lead time calculator. this allows you the user to immediately step the results
                if (cpTitleLabel.getText().equals(leadTimeTitleText)&& !stepButton.hasFocus()){

                    if (fromStartDateRadioButton.isSelected()&& !startDateInput.getText().equals("")||fromEndDateRadioButton.isSelected()&& !endDateInput.getText().equals("")){
                        stepButton.grabFocus();
                    }
                }

                //if the step button already had focus. sends the focus to lead time weeks input.
                //this allows the user to immediately enter another lead time value.
                else if (cpTitleLabel.getText().equals(leadTimeTitleText)&& stepButton.hasFocus()){
                    stepLeadTime();
                    leadTimeWeeksInput.grabFocus();
                }

                enterKeyPressed = false; //resets so the tab button will parse data again when focus is lost in the input box
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {


        }
    } // End of CalPlannerOnEnter Class

    //Calculates results when the work day amount is changed in work day calculator.
    private class CalPlannerOnChange implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getSource() == workDayComboBox){

                if (!startDateInput.getText().equals("") && !endDateInput.getText().equals("")) {
                    cpCalculateResults();
                }
            }

        }

    } // End of CalPlannerOnChange Class


    /*End of CalPlanner Internal Action Classes********************************************************/


/*END OF INTERNAL ACTION CLASSES////////////////////////////////////////////////////////////////////////////////////*/



} //End of MainWindow Class
