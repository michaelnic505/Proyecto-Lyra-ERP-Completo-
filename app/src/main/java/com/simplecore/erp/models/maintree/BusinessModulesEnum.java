package com.simplecore.erp.models.maintree;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum BusinessModulesEnum {

    L0("L0"), //	Core
    L1("L1"), //	Logistic
    L2("L2"), //	Controlling
    L3("L3"), //	Human Resources
    L4("L4"), //	Business Intelligence
    L5("L5"), //	System

    L1_1("L1.1"), //	Commercial Sales
    L1_2("L1.2"), //	Material Management
    L1_3("L1.3"), //	Plant Maintenance
    L1_4("L1.4"), //	Production
    L1_5("L1.5"), //	Projects Management

    L2_1("L2.1"), //	Areas
    L2_2("L2.2"), //	Cost Management
    L2_3("L2.3"), //	Financial Acounting
    L2_4("L2.4"), //	Master data
    L2_5("L2.5"), //	Societies
    L2_6("L2.6"), //	Treasury Management

    L3_1("L3.1"), //	Certifications
    L3_2("L3.2"), //	Employee Management
    L3_3("L3.3"), //	Master data
    L3_4("L3.4"), //	Payroll Analysis
    L3_5("L3.5"), //	Payroll Management
    L3_6("L3.6"), //	Performance Evaluations
    L3_7("L3.7"), //	Time Management
    L3_8("L3.8"), //	Training Plans

    L4_1("L4.1"), //	Analysis
    L4_2("L4.2"), //	Reports

    L5_1("L5.1"), //	Access
    L5_2("L5.2"), //	Users
    L5_3("L5.3"), //	Master Data

    L1_1_1("L1.1.1"), //	Customer Management
    L1_1_2("L1.1.2"), //	Master data
    L1_1_3("L1.1.3"), //	Sales And Billing

    L1_2_1("L1.2.1"), //	Inventory
    L1_2_2("L1.2.2"), //	Purchasing and vendor

    L1_3_1("L1.3.1"), //	Counters
    L1_3_2("L1.3.2"), //	Equipments
    L1_3_3("L1.3.3"), //	Failures Management
    L1_3_4("L1.3.4"), //	Locations
    L1_3_5("L1.3.5"), //	Routine Worksheets
    L1_3_6("L1.3.6"), //	Strategies
    L1_3_7("L1.3.7"), //	Work Orders
    L1_3_8("L1.3.8"), //	Work Requets

    L1_4_1("L1.4.1"), //	Planning
    L1_4_2("L1.4.2"), //	Quality
    L1_4_3("L1.4.3"), //	Master Data

    L1_5_1("L1.5.1"), //	Management
    L1_5_2("L1.5.2"), //	Analysis
    L1_5_3("L1.5.3"), //	Master Data

    L2_2_1("L2.2.1"), //	Cost Analysis
    L2_2_2("L2.2.2"), //	Cost Centers
    L2_2_3("L2.2.3"), //	Cost Orders

    L2_3_1("L2.3.1"), //	Financial Statements
    L2_3_2("L2.3.2"), //	General Accounts
    L2_3_3("L2.3.3"), //	Journal Entries

    L2_6_1("L2.6.1"), //	Bank Accounts
    L2_6_2("L2.6.2"), //	Bank Reconciliation
    L2_6_3("L2.6.3"), //	Cash Flow
    L2_6_4("L2.6.4"), //	Liquidity Report
    L1_1_1_1("L1.1.1.1"), //	Clients
    L1_1_1_2("L1.1.1.2"), //	Customer Analysis

    L1_1_3_1("L1.1.3.1"), //	Billing
    L1_1_3_2("L1.1.3.2"), //	Sales Orders

    L1_2_1_1("L1.2.1.1"), //	Materials
    L1_2_1_2("L1.2.1.2"), //	Warehouses

    L1_2_2_1("L1.2.2.1"), //	Purchase Orders
    L1_2_2_2("L1.2.2.2"), //	Supplier Requests
    L1_2_2_3("L1.2.2.3"), //	Suppliers

    L1_4_1_1("L1.4.1.1"), //	Production Orders
    L1_4_1_2("L1.4.1.2"), //	Production Tracking
    L1_4_1_3("L1.4.1.3"), //	Resources Planning

    L1_4_2_1("L1.4.2.1"), //	Inspection
    L1_4_2_2("L1.4.2.2"),//	Non Conformities

    C01("C01"), //	Create Client
    C02("C02"), //	Modify Client
    C03("C03"), //	View Client
    C04("C04"), //	Client List
    CA1("CA1"), //	Analyze Customer
    MD1("MD1"), //	Objects
    B01("B01"), //	Issue Invoices
    B02("B02"), //	Correct Invoices
    B03("B03"), //	Accounts Receivable
    B04("B04"), //	Billing Analysis
    B05("B05"), //	Reports
    S01("S01"), //	Create Sales Order
    S02("S02"), //	Order Status
    S03("S03"), //	Order List
    S04("S04"), //	Inventory
    M01("M01"), //	Create Material
    M02("M02"), //	Modify Material
    M03("M03"), //	View Material
    M04("M04"), //	Material List
    W01("W01"), //	Create Warehouse
    W02("W02"), //	Modify Warehouse
    W03("W03"), //	View Warehouse
    W04("W04"), //	Warehouse Analysis
    P01("P01"), //	Create Purchase Order
    P02("P02"), //	Modify Purchase Order
    P03("P03"), //	View Purchase Order
    P04("P04"), //	Authorize Orders
    P05("P05"), //	Purchase Analysis
    SR1("SR1"), //	Create Purchase Request
    SR2("SR2"), //	Modify Purchase Request
    SR3("SR3"), //	View Requests
    S11("S11"), //	Create Supplier
    S12("S12"), //	Modify Supplier
    S13("S13"), //	View Supplier
    C11("C11"), //	Create Counter
    C12("C12"), //	Modify Counter
    C13("C13"), //	View Counter
    C14("C14"), //	Input Measurement Data
    C15("C15"), //	Modify Measured Document
    E01("E01"), //	Create Equipment
    E02("E02"), //	Modify Equipment
    E03("E03"), //	View Equipment
    E04("E04"), //	Equipment List
    F01("F01"), //	New Root Cause Analysis
    F02("F02"), //	Analysis Status
    F03("F03"), //	Action Plans
    F04("F04"), //	Equipment Analysis
    L01("L01"), //	Create Physical Location
    L02("L02"), //	Modify Physical Location
    L03("L03"), //	View Physical Location
    L04("L04"), //	Location List
    R01("R01"), //	Create Routine Sheet
    R02("R02"), //	Modify Routine Sheet
    R03("R03"), //	View Routine Sheet
    R04("R04"), //	Procedures
    E21("E21"), //	Create Maintenance Strategy
    E22("E22"), //	Modify Strategy
    E23("E23"), //	View Strategy
    E24("E24"), //	Strategy List
    W11("W11"), //	Create Work Order
    W12("W12"), //	Modify Work Order
    W13("W13"), //	View Work Order
    W14("W14"), //	Approve Work Order
    W15("W15"), //	Approve by List
    W16("W16"), //	Cost Settlement
    WR1("WR1"), //	Create Work Request
    WR2("WR2"), //	Modify Work Request
    WR3("WR3"), //	View Work Request
    P11("P11"), //	Create Production Order
    P12("P12"), //	Correct Production Order
    P13("P13"), //	View Production Order
    P14("P14"), //	Production Order List
    P15("P15"), //	Reports
    PT1("PT1"), //	Production Status
    PT2("PT2"), //	Update Orders
    PT3("PT3"), //	Efficiency Analysis
    PT4("PT4"), //	Performance
    PT5("PT5"), //	Production Downtime
    RP1("RP1"), //	Material Requirement Calculation
    RP2("RP2"), //	Available Stock
    RP3("RP3"), //	Generate Purchase Order
    RP4("RP4"), //	Reserve Materials
    RP5("RP5"), //	Assign Labor
    RP6("RP6"), //	Record Worked Hours
    I01("I01"), //	Inspection Records
    I02("I02"), //	Evaluation Criteria
    I03("I03"), //	Defect Detection
    I04("I04"), //	Result Analysis
    NC1("NC1"), //	Non-Conformity Records
    NC2("NC2"), //	Corrective Actions
    POBJ("POBJ"), //	Production Objects
    A01("A01"), //	Create Company Area
    A02("A02"), //	Modify Area
    A03("A03"), //	View Area
    A04("A04"), //	Area List
    C21("C21"), //	Product Costing
    C22("C22"), //	Cost Comparison
    C23("C23"), //	Deviation Identification
    C24("C24"), //	Profitability Report
    C25("C25"), //	Cost Optimization
    C31("C31"), //	Create Cost Center
    C32("C32"), //	Modify Cost Center
    C33("C33"), //	Cost Centers List
    CO1("CO1"), //	Create Cost Order
    CO2("CO2"), //	Assign Costs to Orders
    CO3("CO3"), //	Order Costing
    CO4("CO4"), //	Estimated vs Actual Costs
    CO5("CO5"), //	Reports
    F11("F11"), //	Income Statement
    F12("F12"), //	Balance Sheet
    F13("F13"), //	Cash Flow
    F14("F14"), //	Reports
    F15("F15"), //	Financial Analysis
    G01("G01"), //	Chart of Accounts
    G02("G02"), //	Transaction Record
    G03("G03"), //	Accounting Adjustments
    G04("G04"), //	Bank Reconciliation
    G05("G05"), //	Balance Reports
    J01("J01"), //	Create Journal Entries
    J02("J02"), //	Modify and Adjust Entries
    J03("J03"), //	Entry Date and Period
    J04("J04"), //	Journal Entry Reports
    COBJ("COBJ"), //	Controlling Objects
    S21("S21"), //	Create Society
    S22("S22"), //	Modify Society
    S23("S23"), //	View Society
    B11("B11"), //	Bank Account Registration
    B12("B12"), //	Bank Transaction Records
    B13("B13"), //	Bank Reconciliation
    B14("B14"), //	Account Statement
    CR31("CR31"), //	Certification Records
    CR32("CR32"), //	Expiration Dates
    CR33("CR33"), //	Certificate Updates
    EM1("EM1"), //	Employee Registration
    EM2("EM2"), //	Assign Roles and Responsibilities
    EM3("EM3"), //	Attendance and Schedule Management
    EM4("EM4"), //	Performance Evaluations
    EM5("EM5"), //	Contract and Benefit Management
    HROJ("HROJ"), //	Human Resources Objects
    PA1("PA1"), //	Salary Analysis
    PA2("PA2"), //	Deductions and Benefits Calculation
    PA3("PA3"), //	Payroll Cost Reports
    PA4("PA4"), //	Payroll vs Budget Comparison
    PA5("PA5"), //	Salary Distribution Analysis
    PM1("PM1"), //	Payroll Registration
    PM2("PM2"), //	Automatic Salary Calculation
    PM3("PM3"), //	Payroll Slip Generation
    PM4("PM4"), //	Payment Scheduling
    PM5("PM5"), //	Compliance with Regulations
    PM6("PM6"), //	Payroll Reports
    PE1("PE1"), //	Create Evaluations
    PE2("PE2"), //	Evaluation Criteria
    PE3("PE3"), //	Performance Tracking
    PE4("PE4"), //	360° Feedback
    PE5("PE5"), //	Performance Reports
    PE6("PE6"), //	Goal Setting
    T01("T01"), //	Schedule Management
    T02("T02"), //	Attendance Records
    T03("T03"), //	Vacation and Absence Requests
    T04("T04"), //	Overtime Tracking
    T05("T05"), //	Worked Time Reports
    T06("T06"), //	Labor Law Compliance
    TP1("TP1"), //	Create Training Plans
    TP2("TP2"), //	Course Scheduling
    TP3("TP3"), //	Training Attendance Records
    TP4("TP4"), //	Progress Tracking
    TP5("TP5"), //	Training Effectiveness Evaluation
    TP6("TP6"), //	Training Reports
    AC01("AC01"), //	Role and Permission Management
    AC02("AC02"), //	Transaction Assignment
    AC03("AC03"), //	Access Auditing
    AC04("AC04"), //	Access Period Management
    A05("A05"), //	Unauthorized Access Alerts
    A06("A06"), //	Permission Modification
    U01("U01"), //	User Registration
    U02("U02"), //	Password Management
    U03("U03"), //	User Status Management
    U04("U04"), //	User Activity Logging
    U05("U05"),	//	User Event Notifications
    SYSOBJ("SYSOBJ");	// System Objects

    private final String key;

    private BusinessModulesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
