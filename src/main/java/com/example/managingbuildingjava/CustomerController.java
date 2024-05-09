package com.example.managingbuildingjava;

import BUS.*;
import DAO.ServiceTicketDAO;
import DTO.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    private static CustomerController instance;
    public static CustomerController getInstance() {
        if (instance == null) {
            instance = new CustomerController();
        }
        return instance;
    }

    private static String ID;

    public void setID (String ID){
        CustomerController.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public TextField TxtField__P1__search;
    public ComboBox comboBox__P1__1;
    public Label txtField__P1__1;
    public TextField TxtField__P2__search;
    public TextField TxtField__P4__search;
    public Button bnt__P1__search;
    public TextField TxtField__P3__search;
    @FXML
    public TableView<MonthlyRentBill> table__P3__1 = new TableView<>();
    @FXML
    TableColumn<MonthlyRentBill, String> monthlyRentBillIdColumn = new TableColumn<MonthlyRentBill, String >();
    @FXML
    TableColumn<MonthlyRentBill, LocalDate> dateColumn = new TableColumn<MonthlyRentBill, LocalDate>();
    @FXML
    TableColumn<MonthlyRentBill, Integer> repaymentPeriodColumn  = new TableColumn<MonthlyRentBill, Integer>();
    @FXML
    TableColumn<MonthlyRentBill, Double> totalPaymentColumn = new TableColumn<MonthlyRentBill, Double>();
    @FXML
    TableColumn<MonthlyRentBill, String> statusColumn = new TableColumn<>();
    private volatile boolean stop = false;
    private volatile Thread thread;
    int dem =0;
    @FXML
    private BorderPane bp = new BorderPane();
    @FXML
    private Pane mp = new Pane();
    @FXML
    private TextField TxtField__P1__1;
    @FXML
    private Button bnt__P1__add;

    @FXML
    private void page0 (MouseEvent event) throws IOException{
        stop = false;
        TimeNow();
        bp.setCenter(mp);
        loadPage0();
    }
    @FXML
    private void page1 (MouseEvent event) throws IOException {
        loadPage("Customer-view-Page1");
    }
    @FXML
    private void page2 (MouseEvent event) throws IOException {
        loadPage("Customer-view-Page2");
    }
    @FXML
    private void page3 (MouseEvent event) throws IOException {
        loadPage("Customer-view-Page3");
    }
    @FXML
    private Label time;
    private void loadPage(String page) throws IOException {
//        stop = true;
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + ".fxml")));
        bp.setCenter(root);
    }
    private void TimeNow(){
        thread = new Thread(()->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format((new Date()));
                Platform.runLater(()->{
                    if (time!=null) time.setText(timenow);
                });
                dem++;
//                System.out.println(dem);
            }
        });
        thread.start();
    }
    @FXML
    void Close_Clicked(MouseEvent event){
        stop = true;
    }
    @FXML
    private Label monthlyBillLabel = new Label();
    @FXML
    private Label statusOfMonthlyBills = new Label();
    @FXML
    private PieChart pieChart = new PieChart();
    @FXML
    private BarChart barChart;
    @FXML
    private Label fullname = new Label();
    @FXML
    private Label dob = new Label();
    @FXML
    private Label phone = new Label();
    @FXML
    private Label gender = new Label();
    @FXML
    private Label CCCD = new Label();
    //Table Page 0
    @FXML
    Label termLabel = new Label();
    @FXML
    private Label deposiLabel = new Label();
    @FXML
    private Label rentLabel = new Label();
    @FXML
    public TableView<Cohabitant> cohabitantTable = new TableView<>();
    @FXML
    TableColumn<Cohabitant, String>  nameCol = new TableColumn<Cohabitant, String>("Họ & Tên");
    @FXML
    TableColumn<Cohabitant, String>  phoneCol = new TableColumn<Cohabitant, String>();
    @FXML
    TableColumn<Cohabitant, LocalDate>  dobCol = new TableColumn<Cohabitant, LocalDate>();
    @FXML
    TableColumn<Cohabitant, String>  genderCol = new TableColumn<Cohabitant, String>();
    //Page 1
    @FXML
    private CheckBox parkingRegis = new CheckBox();
    @FXML
    private CheckBox playGroundRegis = new CheckBox();
    @FXML
    private CheckBox poolRegis = new CheckBox();
    @FXML
    private CheckBox gymRegis = new CheckBox();
    @FXML
    private CheckBox internetRegis = new CheckBox();
    @FXML
    private TextField noteGyms = new TextField();
    @FXML
    private TextField noteInternets = new TextField();
    @FXML
    private TextField noteParkings = new TextField();
    @FXML
    private TextField notePlayGrounds = new TextField();
    @FXML
    private TextField notePools = new TextField();
    @FXML
    TableColumn<ServiceUsuage, String> nameSerCol = new TableColumn<>();
    @FXML
    TableColumn<ServiceUsuage, String> noteSersCol = new TableColumn<>();
    @FXML
    TableColumn<ServiceUsuage, String> priceSerCol = new TableColumn<>();
    @FXML
    TableColumn<ServiceUsuage, String> regisSerCol = new TableColumn<>();

    @FXML
    private ComboBox<String> comboBox__P1__21 = new ComboBox<>();
    @FXML
    private DatePicker selectSersDate = new DatePicker();
    @FXML
    TableColumn<String[], String>quantitySersOldCol = new TableColumn<>();
    @FXML
    TableColumn<String[], String>nameSerOldCol = new TableColumn<>();
    @FXML
    TableColumn<String[], String> priceSerOldCol = new TableColumn<>();
    @FXML
    TableColumn<String[], String> regisSerOldCol = new TableColumn<>();
    @FXML
    TableView<ServiceUsuage> registeredSerTable = new TableView<>();
    @FXML
    TableView<String[]> registeredSerOldTable = new TableView<>();


    public void loadPage0(){

        setMonthlyBillLabel();
        updateInfor();
        updatePieChart();
        setTabelLeaseAgreement();
        setTableCohabitant();

    }
    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void regisMobile(){
        ServiceBUS.getInstance().setCombox(comboBox__P1__21);

        LocalDate dateRegis = selectSersDate.getValue();
        
    }

    void updateTableNewRegisServ(){

        nameSerCol.setCellValueFactory(new PropertyValueFactory<ServiceUsuage, String>("name"));
        noteSersCol.setCellValueFactory(new PropertyValueFactory<ServiceUsuage, String>("note"));
        priceSerCol.setCellValueFactory(new PropertyValueFactory<ServiceUsuage, String>("totalAmount"));
        regisSerCol.setCellValueFactory(new PropertyValueFactory<ServiceUsuage, String>("date"));

        ServiceTicketBUS.getInstance().setTableRegisServ(registeredSerTable);
    }

    @FXML
    void regisFixed() {
        LocalDate currentDate = selectSersDate.getValue();
        if (!parkingRegis.isSelected() && !playGroundRegis.isSelected() && !poolRegis.isSelected() && !gymRegis.isSelected() && !internetRegis.isSelected()){
            showAlert("Lỗi", "Vui lòng tích vào ô đăng ký.", Alert.AlertType.ERROR);
        }
        else{
            if(parkingRegis.isSelected()){
                ServiceTicketBUS.getInstance().regisFixedServ("SERV3",noteParkings.getText(), currentDate);
                updateTableNewRegisServ();
            }
            if(playGroundRegis.isSelected()){
                ServiceTicketBUS.getInstance().regisFixedServ("SERV11",notePlayGrounds.getText(), currentDate);
                updateTableNewRegisServ();

            }
            if(poolRegis.isSelected()){
                ServiceTicketBUS.getInstance().regisFixedServ("SERV9",notePools.getText(), currentDate);
                updateTableNewRegisServ();

            }
            if(gymRegis.isSelected()){
                ServiceTicketBUS.getInstance().regisFixedServ("SERV5",noteGyms.getText(), currentDate);
                updateTableNewRegisServ();

            }
            if(internetRegis.isSelected()){
                ServiceTicketBUS.getInstance().regisFixedServ("SERV4",noteInternets.getText(), currentDate);
                updateTableNewRegisServ();

            }
        }
        internetRegis.setSelected(false);
        parkingRegis.setSelected(false);
        playGroundRegis.setSelected(false);
        poolRegis.setSelected(false);
        gymRegis.setSelected(false);

        noteInternets.setText(null);
        notePools.setText(null);
        noteGyms.setText(null);
        notePlayGrounds.setText(null);
        noteParkings.setText(null);

    }

    void setTableCohabitant(){
        // Thiết lập font cho các cột
        String font = "Times New Roman";
        nameCol.setStyle("-fx-font-family: '" + font + "';");
        phoneCol.setStyle("-fx-font-family: '" + font + "';");
        dobCol.setStyle("-fx-font-family: '" + font + "';");
        genderCol.setStyle("-fx-font-family: '" + font + "';");


        nameCol.setCellValueFactory(new PropertyValueFactory<Cohabitant, String>("fullName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Cohabitant, String>("phoneNumber"));
        dobCol.setCellValueFactory(new PropertyValueFactory<Cohabitant, LocalDate>("dateOfBirthDay"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Cohabitant, String>("gender"));

        ObservableList<Cohabitant> data = FXCollections.observableArrayList(CohabitantBUS.getInstance().getCohabitantsWithTenantId(this.ID));
        cohabitantTable.setItems(data);


    }

    void setTabelLeaseAgreement(){
        ObservableList<LeaseAgreement> data = FXCollections.observableArrayList(LeaseAgreementBUS.getInstance().getLeaseAgreementsWithTenantId(this.ID));

        LeaseAgreementBUS.getInstance().updateTabelLeaseAgreement(data, termLabel, deposiLabel, rentLabel);
    }

    void updatePieChart() {
        try {
            MonthlyRentBillBUS.getInstance().updatePiechart(pieChart,this.ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateInfor(){
        try{
            TenantBUS.getInstance().setInfor(fullname, phone,dob,gender,CCCD,this.ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void setMonthlyBillLabel() {
        try{
            MonthlyRentBillBUS.getInstance().updateMonthlyBill(monthlyBillLabel, statusOfMonthlyBills, this.ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void setTableMonthlyRentBill(){
        monthlyRentBillIdColumn.setCellValueFactory(new PropertyValueFactory<MonthlyRentBill, String>("monthlyRentBillID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<MonthlyRentBill, LocalDate>("date"));
        repaymentPeriodColumn.setCellValueFactory(new PropertyValueFactory<MonthlyRentBill, Integer>("repaymentPeriod"));
        totalPaymentColumn.setCellValueFactory(new PropertyValueFactory<MonthlyRentBill, Double>("totalPayment"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<MonthlyRentBill, String>("status"));

        ObservableList<MonthlyRentBill> data = FXCollections.observableArrayList(MonthlyRentBillBUS.getInstance().getMonthlyRentBillsWithTenantId(this.ID));
        table__P3__1.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Page 0
        loadPage0();

        //Page 1
        updateTableNewRegisServ();
        regisMobile();

        //Page 2
        setTableMonthlyRentBill();
    }
}