/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import dataBaseConnection.DataBaseConnection;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALLEXPA
 */
public class View_DashBoard extends javax.swing.JFrame {

    /**
     * Creates new form View_DashBoard
     */
    public View_DashBoard() {
        initComponents();
        setLocationRelativeTo(null);
        studentTable();
//        getClassTocomboFromStudentTable();
    }
    DataBaseConnection con = new DataBaseConnection();
    PreparedStatement ps;
    ResultSet rs;

    public java.sql.Date dateMethod(java.util.Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

//          public void getClassTocomboFromStudentTable() {
//        
//        String sql = "select classs from school";        
//       sd_class_combo.removeAllItems();
//        try {
//            ps = con.getCon().prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String classs = rs.getString("classs");
//
//                  sd_class_combo.addItem(classs);               
//                }            
//            rs.close();
//            ps.close();
//            con.getCon().close();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    student admition table -------------------------------------------------
    String[] addmidionDataShow = {"Session", "Batch_id", "Class", "Full_name", "Father_name", "Mother_name", "gender", "age"};

    public void studentTable() {

        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(addmidionDataShow);
        s_Table.setModel(modelTable);

        String sql = "Select * from school";

        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int session = rs.getInt("Session_year");
                String batch = rs.getString("batch_id");
                String classs = rs.getString("classs");
                String name = rs.getString("full_name");
                String father = rs.getString("fathers_name");
                String mother = rs.getString("mothers_name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                modelTable.addRow(new Object[]{session, batch, classs, name, father, mother, gender, age});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    full_name=?,fathers_name=?,fathers_phone=?,mothers_name=?,mothers_phone=?,"
//                + "date_of_birth=?, age=?,gender=?,religion=?,nationality=?,email=?,blood_group=?,"
//                + "birth_certificate=?,present_Address=?,permanent_Address=?
    public void getDateFromDataBaseSetViewForm() {
        System.out.println("1");

        String sql = "select * from school where Session_year=? and batch_id=? and classs=? and student_Roll=?";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as needed

            ps = con.getCon().prepareStatement(sql);

            System.out.println("2");
            int year = Integer.parseInt(sd_session.getText().toString().trim());
            ps.setInt(1, year);
            
//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, sd_batchId.getText().toString().trim());
            ps.setString(3, sd_class_combo.getSelectedItem().toString().trim());
            ps.setString(4, sd_studentID.getText().toString().trim());

            rs = ps.executeQuery();

            System.out.println("5");

            while (rs.next()) {
                System.out.println("9");
                sd_fullName.setText(rs.getString("full_name"));
                sd_st_name.setText(rs.getString("full_name"));
                sd_class_view.setText(rs.getString("classs"));
                sd_fatherName.setText(rs.getString("fathers_name"));
                sd_Father_phone.setText(rs.getString("fathers_phone"));
                sd_motherName.setText(rs.getString("mothers_name"));
                sd_motherPhone.setText(rs.getString("mothers_phone"));
                java.util.Date dateOfBirth = rs.getDate("date_of_birth");
                String formattedDateOfBirth = dateFormat.format(dateOfBirth);
                sd_Dob.setText(formattedDateOfBirth);
//                sd_Dob.setText(rs.getDate("date_of_birth"));
                int age = rs.getInt("age");
                sd_Age.setText(Integer.toString(age));
                sd_gender_combo.setText(rs.getString("gender"));
                sd_religion.setText(rs.getString("religion"));
                sd_nationality.setText(rs.getString("nationality"));
                sd_email.setText(rs.getString("email"));
                sd_blood.setText(rs.getString("blood_group"));
                sd_birth_cer.setText(rs.getString("birth_certificate"));
                sd_presentA.setText(rs.getString("present_Address"));
                sd_parmanentA.setText(rs.getString("permanent_Address"));
//                sd_session.setText(rs.getString("Session_year"));
//                sd_batchId.setText(rs.getString("batch_id"));
//                sd_class_combo.setSelectedItem(rs.getString("classs"));
//                sd_studentID.setText(rs.getString("student_Roll"));

                // Set other text fields in a similar manner
            }
            rs.close();
            ps.close();
            con.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "No matching record found.");
        }

    }

    public void AdmisionReset() {
        // Assuming these are your instance variables
        s_session.setText(null); // Reset the session year to a default value or the current year
        s_Batch_id.setText(null); // Reset batch ID to an empty string
        s_Class_combo.setSelectedIndex(0); // Reset class combo to the first item
        s_student_id.setText(null); // Reset student ID to an empty string
        s_student_first.setText(null); // Reset student first name to an empty string
        s_Student_last.setText(null); // Reset student last name to an empty string
        s_father_N.setText(null); // Reset father's name to an empty string
        s_F_phone.setText(null); // Reset father's phone to an empty string
        s_Mother_N.setText(null); // Reset mother's name to an empty string
        s_M_phone.setText(null); // Reset mother's phone to an empty string
        s_dateOfBirth.setDate(null); // Reset date of birth to null
        s_age.setText(null); // Reset age to an empty string
        s_gender_combo.setSelectedIndex(0); // Reset gender combo to the first item
        s_religion.setText(null); // Reset religion to an empty string
        s_National.setText(null); // Reset nationality to an empty string
        s_email.setText(null); // Reset email to an empty string
        s_BloodGroup.setSelectedIndex(0); // Reset blood group combo to the first item
        s_birth_certificate.setText(null); // Reset birth certificate to an empty string
        s_presentAddress.setText(null); // Reset present address to an empty string
        s_parmanent.setText(null); // Reset permanent address to an empty string
    }
    
    public void StudentDetailsReset() {
    sd_fullName.setText("");
    sd_st_name.setText("");
    sd_class_view.setText("");
    sd_fatherName.setText("");
    sd_Father_phone.setText("");
    sd_motherName.setText("");
    sd_motherPhone.setText("");
    sd_Dob.setText("");
    sd_Age.setText("");
    sd_gender_combo.setText("");
    sd_religion.setText("");
    sd_nationality.setText("");
    sd_email.setText("");
    sd_blood.setText("");
    sd_birth_cer.setText("");
    sd_presentA.setText("");
    sd_parmanentA.setText("");
    sd_session.setText("");
    sd_class_combo.setSelectedIndex(0);
    sd_batchId.setText("");
    sd_studentID.setText("");
    
}
    
//    print panal method-----------------------------------------------------------------
    
//    public static void printPanel(JPanel panel) {
//        PrinterJob printerJob = PrinterJob.getPrinterJob();
//        printerJob.setJobName("Panel Print");
//
//        printerJob.setPrintable(new Printable() {
//            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
//                if (pageIndex > 0) {
//                    return Printable.NO_SUCH_PAGE;
//                }
//
//                Graphics2D g2d = (Graphics2D) graphics;
//                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//
//                panel.printAll(g2d);
//
//                return Printable.PAGE_EXISTS;
//            }
//        });
//
//        if (printerJob.printDialog()) {
//            try {
//                printerJob.print();
//            } catch (PrinterException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanal = new javax.swing.JPanel();
        headPanal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        optionPanal = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnStudent = new javax.swing.JButton();
        btnTeacher = new javax.swing.JButton();
        btnExam = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        exitTab = new javax.swing.JButton();
        btnStaff1 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        bodyPanal = new javax.swing.JPanel();
        mainTab = new javax.swing.JTabbedPane();
        HomeTab = new javax.swing.JTabbedPane();
        homePanal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        StudentTab = new javax.swing.JTabbedPane();
        St_Home = new javax.swing.JTabbedPane();
        St_All = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        st_1 = new javax.swing.JPanel();
        St_btn_addmition = new javax.swing.JButton();
        st_2 = new javax.swing.JPanel();
        St_btn_Deatils = new javax.swing.JButton();
        st_3 = new javax.swing.JPanel();
        st_btn_result_in = new javax.swing.JButton();
        St_4 = new javax.swing.JPanel();
        st_btn_result_out = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        St_Admition = new javax.swing.JTabbedPane();
        st_admision_main_pan = new javax.swing.JPanel();
        st_add = new javax.swing.JPanel();
        Table_Student = new javax.swing.JScrollPane();
        s_Table = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_Save_ = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_AdmiosionReset_ = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        s_Batch_id = new javax.swing.JTextField();
        s_student_id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        s_email = new javax.swing.JTextField();
        s_Class_combo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        s_student_first = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        s_Student_last = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        s_father_N = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        s_birth_certificate = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        s_National = new javax.swing.JTextField();
        s_dateOfBirth = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        s_BloodGroup = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        s_parmanent = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        s_presentAddress = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        s_Mother_N = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        s_M_phone = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        s_F_phone = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        s_gender_combo = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        s_religion = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        s_age = new javax.swing.JTextField();
        s_session = new javax.swing.JTextField();
        St_deatils = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        sd_class_combo = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        sd_studentID = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        sd_batchId = new javax.swing.JTextField();
        btn_sd_View_ = new javax.swing.JButton();
        sd_session = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        sd_birth_cer = new javax.swing.JLabel();
        sd_fullName = new javax.swing.JLabel();
        sd_fatherName = new javax.swing.JLabel();
        sd_Father_phone = new javax.swing.JLabel();
        sd_motherName = new javax.swing.JLabel();
        sd_motherPhone = new javax.swing.JLabel();
        sd_Dob = new javax.swing.JLabel();
        sd_Age = new javax.swing.JLabel();
        sd_religion = new javax.swing.JLabel();
        sd_nationality = new javax.swing.JLabel();
        sd_blood = new javax.swing.JLabel();
        sd_email = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sd_parmanentA = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        sd_presentA = new javax.swing.JTextArea();
        sd_class_view = new javax.swing.JLabel();
        sd_st_name = new javax.swing.JLabel();
        sd_gender_combo = new javax.swing.JLabel();
        btn_sd_delete_ = new javax.swing.JButton();
        btn_sd_View1_ = new javax.swing.JButton();
        btn_sd_update_ = new javax.swing.JButton();
        btn_sd_Reset = new javax.swing.JButton();
        jLabel115 = new javax.swing.JLabel();
        St_Result_input = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        St_Result_Print = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableGrade = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        TeacherTab = new javax.swing.JTabbedPane();
        Te_home = new javax.swing.JTabbedPane();
        te_All = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        te_btn_report = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        te_btn_rutines = new javax.swing.JButton();
        te_btn_add = new javax.swing.JButton();
        Te_add = new javax.swing.JTabbedPane();
        Te_ClassRutine = new javax.swing.JTabbedPane();
        Te_Full_Report = new javax.swing.JTabbedPane();
        Exam_Seduel = new javax.swing.JTabbedPane();
        ex_Home = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        ex_date_inputTab = new javax.swing.JTabbedPane();
        jButton11 = new javax.swing.JButton();
        ex_viewTab = new javax.swing.JTabbedPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        ex_admitCardTab = new javax.swing.JTabbedPane();
        jComboBox2 = new javax.swing.JComboBox<>();
        PaymentTab = new javax.swing.JTabbedPane();
        Pay_home = new javax.swing.JTabbedPane();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        Pay_TeachersTab = new javax.swing.JTabbedPane();
        jPanel40 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        Pay_StaffTab = new javax.swing.JTabbedPane();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        Pay_StudentTab = new javax.swing.JTabbedPane();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel103 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField22 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        Staff = new javax.swing.JTabbedPane();
        Staff_Home = new javax.swing.JTabbedPane();
        Staff_Add = new javax.swing.JTabbedPane();
        Staff_view = new javax.swing.JTabbedPane();
        ex = new javax.swing.JTabbedPane();
        ex1 = new javax.swing.JTabbedPane();
        ex2 = new javax.swing.JTabbedPane();
        ex3 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headPanal.setBackground(new java.awt.Color(255, 153, 153));
        headPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LALBAG MODEL SCHOOL AND COLLAGE , DHAKA-1212");
        headPanal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/empty-blackboard (2).jpg"))); // NOI18N
        headPanal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        mainPanal.add(headPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        optionPanal.setBackground(new java.awt.Color(153, 255, 204));
        optionPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/home (1)_1.png"))); // NOI18N
        btnHome.setText("  HOME");
        btnHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        optionPanal.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 160, 40));

        btnStudent.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/graduated.png"))); // NOI18N
        btnStudent.setText("STUDENT");
        btnStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentMouseClicked(evt);
            }
        });
        btnStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentActionPerformed(evt);
            }
        });
        optionPanal.add(btnStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 160, 40));

        btnTeacher.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/teaching.png"))); // NOI18N
        btnTeacher.setText("  TEACHER");
        btnTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTeacherMouseClicked(evt);
            }
        });
        optionPanal.add(btnTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 40));

        btnExam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/exam.png"))); // NOI18N
        btnExam.setText("    EXAM");
        btnExam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExamMouseClicked(evt);
            }
        });
        optionPanal.add(btnExam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 160, 40));

        btnPayment.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/bill.png"))); // NOI18N
        btnPayment.setText(" PAYMENT");
        btnPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPaymentMouseClicked(evt);
            }
        });
        optionPanal.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 160, 40));

        exitTab.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        exitTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/teamwork.png"))); // NOI18N
        exitTab.setText("Exit");
        exitTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTabMouseClicked(evt);
            }
        });
        optionPanal.add(exitTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 160, 40));

        btnStaff1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnStaff1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/teamwork.png"))); // NOI18N
        btnStaff1.setText("  STAFF");
        btnStaff1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaff1MouseClicked(evt);
            }
        });
        optionPanal.add(btnStaff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 160, 40));

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/optionPanal.jpg"))); // NOI18N
        optionPanal.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 560));

        mainPanal.add(optionPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, 560));

        bodyPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 230, 80));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, 230, 90));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 230, 80));

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 230, 80));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 230, 80));

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 230, 80));

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_dashboard.jpg"))); // NOI18N
        jPanel1.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1000, 570));

        homePanal.addTab("tab1", jPanel1);

        HomeTab.addTab("tab1", homePanal);

        mainTab.addTab("tab1", HomeTab);

        St_All.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_1.setBackground(new java.awt.Color(204, 255, 204));
        st_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        st_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                st_1MouseEntered(evt);
            }
        });
        st_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        St_btn_addmition.setBackground(new java.awt.Color(204, 204, 204));
        St_btn_addmition.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        St_btn_addmition.setText("ADMITION");
        St_btn_addmition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                St_btn_addmitionMouseClicked(evt);
            }
        });
        st_1.add(St_btn_addmition, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 70));

        jPanel13.add(st_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 160, 90));

        st_2.setBackground(new java.awt.Color(204, 255, 204));
        st_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        st_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        St_btn_Deatils.setBackground(new java.awt.Color(204, 204, 204));
        St_btn_Deatils.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        St_btn_Deatils.setText("Information");
        St_btn_Deatils.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                St_btn_DeatilsMouseClicked(evt);
            }
        });
        st_2.add(St_btn_Deatils, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 70));

        jPanel13.add(st_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 160, 90));

        st_3.setBackground(new java.awt.Color(204, 255, 204));
        st_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        st_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_btn_result_in.setBackground(new java.awt.Color(204, 204, 204));
        st_btn_result_in.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        st_btn_result_in.setText("RESULT INPUT");
        st_btn_result_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                st_btn_result_inMouseClicked(evt);
            }
        });
        st_3.add(st_btn_result_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 70));

        jPanel13.add(st_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 160, 90));

        St_4.setBackground(new java.awt.Color(204, 255, 204));
        St_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        St_4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_btn_result_out.setBackground(new java.awt.Color(204, 204, 204));
        st_btn_result_out.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        st_btn_result_out.setText("REAULT SHOW");
        st_btn_result_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                st_btn_result_outMouseClicked(evt);
            }
        });
        St_4.add(st_btn_result_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 70));

        jPanel13.add(St_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 160, 90));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/studentdashboards (2).jpg"))); // NOI18N
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 570));

        St_All.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 570));

        St_Home.addTab("tab1", St_All);

        StudentTab.addTab("tab5", St_Home);

        St_Admition.setBackground(new java.awt.Color(255, 255, 255));

        st_admision_main_pan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_add.setBackground(new java.awt.Color(255, 255, 255));
        st_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_Table.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        s_Table.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Student Id", "Student Name", "Father Name", "Age", "Gender", "Phone no.", "Date of Birth"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Student.setViewportView(s_Table);

        st_add.add(Table_Student, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 407, 930, 150));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel14.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 140));

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 140));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton1.setText("Delete");
        jPanel11.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 160, 30));

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton2.setText("Browse");
        jPanel11.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, 30));

        btn_Save_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_Save_.setText("Save");
        btn_Save_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Save_MouseClicked(evt);
            }
        });
        jPanel11.add(btn_Save_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 30));

        jButton4.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton4.setText("Edit");
        jPanel11.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 160, 30));

        btn_AdmiosionReset_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_AdmiosionReset_.setText("Reset");
        btn_AdmiosionReset_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AdmiosionReset_MouseClicked(evt);
            }
        });
        jPanel11.add(btn_AdmiosionReset_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 160, 30));

        st_add.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 180, 380));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP), "STUDENT ADMISION FORM", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Batch id");
        jPanel12.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Admision Class");
        jPanel12.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 110, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Relision");
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 120, 20));

        s_Batch_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_Batch_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 150, -1));

        s_student_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_student_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Student id");
        jPanel12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, -1));

        s_email.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_emailActionPerformed(evt);
            }
        });
        jPanel12.add(s_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, 150, -1));

        s_Class_combo.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        s_Class_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Class --", "6", "7", "8", "9", "10", " " }));
        jPanel12.add(s_Class_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 140, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("First Name");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, 20));

        s_student_first.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_student_first, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Parmanent Address");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 20));

        s_Student_last.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_Student_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_Student_lastActionPerformed(evt);
            }
        });
        jPanel12.add(s_Student_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 140, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Fathers Name");
        jPanel12.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, 20));

        s_father_N.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_father_N, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 150, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Father Phone No");
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 120, 20));

        s_birth_certificate.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_birth_certificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_birth_certificateActionPerformed(evt);
            }
        });
        jPanel12.add(s_birth_certificate, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 150, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Mothers Name");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 20));

        s_National.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_National, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 150, -1));
        jPanel12.add(s_dateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 150, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Mothers Phone No");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 130, 20));

        s_BloodGroup.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        s_BloodGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Select Blood Group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        jPanel12.add(s_BloodGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 150, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Age");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 90, 20));

        s_parmanent.setColumns(20);
        s_parmanent.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_parmanent.setRows(5);
        jScrollPane2.setViewportView(s_parmanent);

        jPanel12.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 530, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Present Address");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 140, 20));

        s_presentAddress.setColumns(20);
        s_presentAddress.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_presentAddress.setRows(5);
        jScrollPane3.setViewportView(s_presentAddress);

        jPanel12.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 530, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Date of Birth");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 20));

        s_Mother_N.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_Mother_N, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("E-mail Address");
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 150, 20));

        s_M_phone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_M_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_M_phoneActionPerformed(evt);
            }
        });
        jPanel12.add(s_M_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 140, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Student Blood Group");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 150, 20));

        s_F_phone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_F_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_F_phoneActionPerformed(evt);
            }
        });
        jPanel12.add(s_F_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 140, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Birth Certificate No:");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 150, 20));

        s_gender_combo.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        s_gender_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Gender --", "Male", "Female" }));
        jPanel12.add(s_gender_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 140, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Session Year");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 110, 20));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Last Name");
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 110, 20));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Gender");
        jPanel12.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 130, 20));

        s_religion.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        s_religion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_religionActionPerformed(evt);
            }
        });
        jPanel12.add(s_religion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 140, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Nationality");
        jPanel12.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 20));

        s_age.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 150, -1));

        s_session.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel12.add(s_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 140, -1));

        st_add.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 730, 380));

        st_admision_main_pan.add(st_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 570));

        St_Admition.addTab("tab1", st_admision_main_pan);

        StudentTab.addTab("tab1", St_Admition);

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("student id");
        jPanel16.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 100, 30));

        jLabel43.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Session Year");
        jPanel16.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        sd_class_combo.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_class_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select class", "6", "7", "8", "9", "10" }));
        jPanel16.add(sd_class_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 120, 30));

        jLabel44.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Class");
        jPanel16.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 50, 30));
        jPanel16.add(sd_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 120, 30));

        jLabel51.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Batch id");
        jPanel16.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 80, 30));
        jPanel16.add(sd_batchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        btn_sd_View_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_View_.setText("View");
        jPanel16.add(btn_sd_View_, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 110, 30));
        jPanel16.add(sd_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 90, 30));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 60));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 12))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel19.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 170));

        jPanel18.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 170));

        jLabel32.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel32.setText("Birth Cer.No");
        jPanel18.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 110, 30));

        jLabel33.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel33.setText("Full Name");
        jPanel18.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 140, 30));

        jLabel34.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel34.setText("Father's Name");
        jPanel18.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 130, 30));

        jLabel35.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel35.setText("Mother's Name");
        jPanel18.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 140, 30));

        jLabel36.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel36.setText("Phone Number");
        jPanel18.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 140, 30));

        jLabel38.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel38.setText("Date Of Birth");
        jPanel18.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 140, 30));

        jLabel39.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel39.setText("Age");
        jPanel18.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 140, 30));

        jLabel40.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel40.setText("Religion");
        jPanel18.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 80, 30));

        jLabel41.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel41.setText("Nationality");
        jPanel18.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, 30));

        jLabel45.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel45.setText("Phone Number");
        jPanel18.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 140, 30));

        jLabel46.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel46.setText("Email");
        jPanel18.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 50, 30));

        jLabel47.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel47.setText("Present Addess");
        jPanel18.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 140, 30));

        jLabel48.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel48.setText("Parmanent Address");
        jPanel18.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, 30));

        jLabel49.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel49.setText("Blood Group");
        jPanel18.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 110, 30));

        sd_birth_cer.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_birth_cer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_birth_cer, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 200, 30));

        sd_fullName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_fullName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_fullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 190, 30));

        sd_fatherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_fatherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_fatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 190, 30));

        sd_Father_phone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Father_phone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_Father_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 190, 30));

        sd_motherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_motherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_motherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 190, 30));

        sd_motherPhone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_motherPhone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_motherPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 190, 30));

        sd_Dob.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Dob.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_Dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 190, 30));

        sd_Age.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Age.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_Age, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 190, 30));

        sd_religion.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_religion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_religion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 200, 30));

        sd_nationality.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_nationality.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_nationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 200, 30));

        sd_blood.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_blood.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_blood, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 200, 30));

        sd_email.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.add(sd_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 200, 30));

        sd_parmanentA.setColumns(20);
        sd_parmanentA.setRows(5);
        sd_parmanentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(sd_parmanentA);

        jPanel18.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 570, 30));

        sd_presentA.setColumns(20);
        sd_presentA.setRows(5);
        sd_presentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane4.setViewportView(sd_presentA);

        jPanel18.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 570, 30));

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 940, 390));

        sd_class_view.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        sd_class_view.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sd_class_view.setText("10");
        jPanel17.add(sd_class_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 100, 40));

        sd_st_name.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        sd_st_name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sd_st_name.setText("Md Faysal");
        jPanel17.add(sd_st_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 40));

        sd_gender_combo.setFont(new java.awt.Font("Lucida Fax", 1, 22)); // NOI18N
        sd_gender_combo.setForeground(new java.awt.Color(255, 51, 51));
        sd_gender_combo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sd_gender_combo.setText("Male");
        jPanel17.add(sd_gender_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, 40));

        btn_sd_delete_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_delete_.setText("Delete");
        jPanel17.add(btn_sd_delete_, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 220, 40));

        btn_sd_View1_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_View1_.setText("View");
        btn_sd_View1_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_View1_MouseClicked(evt);
            }
        });
        btn_sd_View1_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sd_View1_ActionPerformed(evt);
            }
        });
        jPanel17.add(btn_sd_View1_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 230, 40));

        btn_sd_update_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_update_.setText("Update");
        btn_sd_update_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sd_update_ActionPerformed(evt);
            }
        });
        jPanel17.add(btn_sd_update_, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 230, 40));

        btn_sd_Reset.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_Reset.setText("Reset");
        btn_sd_Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_ResetMouseClicked(evt);
            }
        });
        jPanel17.add(btn_sd_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 230, 40));

        jLabel115.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel115.setText("Class-");
        jPanel17.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, 40));

        jPanel15.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 970, 500));

        St_deatils.addTab("tab1", jPanel15);

        StudentTab.addTab("tab2", St_deatils);

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("Session");
        jPanel25.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 90, 40));

        jTextField9.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 90, 40));

        jLabel53.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel53.setText("Student Roll");
        jPanel25.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 120, 40));

        jTextField10.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 80, 40));

        jLabel54.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("Batch Id");
        jPanel25.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 90, 40));

        jTextField11.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 80, 40));

        jLabel55.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Class");
        jPanel25.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 70, 40));

        jTextField12.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 80, 40));

        jPanel21.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 970, 40));

        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 30, 420));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel4.setText("Grade");
        jPanel21.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 160, 30));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel5.setText("Bangla");
        jPanel21.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 140, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel10.setText("English");
        jPanel21.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, 30));

        jLabel12.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel12.setText("Genarel Math");
        jPanel21.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 140, 30));

        jLabel30.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel30.setText("Genarel Scince");
        jPanel21.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 150, 30));

        jLabel31.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel31.setText("Social Scince");
        jPanel21.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 140, 30));

        jLabel37.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel37.setText("Islam");
        jPanel21.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 140, 30));

        jLabel50.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel50.setText("Total Number");
        jPanel21.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 140, 30));

        jTextField1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 160, 30));

        jTextField2.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 160, 30));

        jTextField3.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 160, 30));

        jTextField4.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 160, 30));

        jTextField5.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, 30));

        jTextField6.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 160, 30));

        jTextField7.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 160, 30));

        jTextField8.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 160, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        jPanel21.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 540, 390));

        jLabel56.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel56.setText("Avarage number");
        jPanel21.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 160, 30));

        jTextField13.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 160, 30));

        jLabel57.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel57.setText("G.P.A");
        jPanel21.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 160, 30));

        jTextField14.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel21.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 160, 30));

        jButton3.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel21.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 490, 110, 40));

        jButton5.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton5.setText("Save");
        jButton5.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel21.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 120, 40));

        jButton6.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton6.setText("Reset");
        jButton6.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel21.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 110, 40));

        jButton7.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton7.setText("Update");
        jButton7.setBorder(new javax.swing.border.MatteBorder(null));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 120, 40));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        St_Result_input.addTab("tab1", jPanel20);

        StudentTab.addTab("tab3", St_Result_input);

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel58.setText("Session");
        jPanel24.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 30));

        jTextField15.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel24.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 90, 30));

        jLabel59.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel59.setText("Student Roll");
        jPanel24.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        jTextField16.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel24.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 30));

        jLabel60.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel60.setText("Batch id");
        jPanel24.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 80, 30));

        jLabel61.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel61.setText("Class");
        jPanel24.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 50, 30));

        jTextField17.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel24.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 90, 30));

        jTextField18.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel24.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 90, 30));

        jButton12.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton12.setText("View");
        jPanel24.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 110, 30));

        jPanel23.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 970, 50));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reult Sheet", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel72.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel72.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 100, 20));

        jLabel73.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel73.setText("Subject Name");
        jPanel35.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 20));

        jLabel74.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel74.setText("Number");
        jPanel35.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 80, 20));

        jLabel75.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel75.setText("Grade");
        jPanel35.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 70, 20));

        jLabel76.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel76.setText("Bangla");
        jPanel35.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 20));

        jLabel77.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel77.setText("Islam");
        jPanel35.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 20));

        jLabel78.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel78.setText("English");
        jPanel35.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 20));

        jLabel79.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel79.setText("Genarel Math");
        jPanel35.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, 20));

        jLabel80.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel80.setText("Genarel Scince");
        jPanel35.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 110, 20));

        jLabel81.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel81.setText("Social Scince");
        jPanel35.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 20));

        jLabel82.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel82.setText("Total Mark");
        jPanel35.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 100, 20));

        jLabel83.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel83.setText("G.P.A");
        jPanel35.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 100, 20));

        jLabel84.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel84.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 100, 20));

        jLabel85.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel85.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, 20));

        jLabel86.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel86.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 100, 20));

        jLabel87.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel87.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 100, 20));

        jLabel88.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel88.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 100, 20));

        jLabel89.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel89.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 100, 20));

        jLabel90.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel90.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 100, 20));

        jLabel91.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel91.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 100, 20));

        jLabel92.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel92.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 100, 20));

        jLabel93.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel93.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, 20));

        jLabel94.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel94.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 100, 20));

        jLabel95.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel95.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 100, 20));

        jLabel96.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel96.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 100, 20));

        jLabel97.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel97.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 100, 20));

        jLabel98.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel98.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 100, 20));

        tableGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "G.P.A", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableGrade);
        if (tableGrade.getColumnModel().getColumnCount() > 0) {
            tableGrade.getColumnModel().getColumn(0).setResizable(false);
            tableGrade.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel35.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 160, 120));

        jPanel31.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 790, 300));

        jLabel62.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel62.setText("Lalbag Model School and Collage ");
        jPanel31.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 400, 20));

        jLabel63.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("lalbag, Dhaka-1212");
        jPanel31.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 310, -1));

        jLabel64.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel64.setText("Md Faysal Hossain");
        jPanel31.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 160, 20));

        jLabel65.setBackground(new java.awt.Color(255, 255, 255));
        jLabel65.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 102, 102));
        jLabel65.setText("Final");
        jPanel31.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 97, 110, 20));

        jLabel66.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel66.setText("Roll No");
        jPanel31.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, 20));

        jLabel67.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel67.setText("Class");
        jPanel31.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 120, 20));

        jLabel68.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel68.setText("10");
        jPanel31.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 120, 20));

        jLabel69.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel69.setText("01");
        jPanel31.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 120, 20));

        jLabel70.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel70.setText("Student Name");
        jPanel31.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 120, -1));

        jLabel71.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel71.setText("Exam Catagory");
        jPanel31.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 120, 30));

        jPanel23.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 810, 430));

        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 30, 310));

        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 970, 40));

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 30, 310));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        St_Result_Print.addTab("tab1", jPanel22);

        StudentTab.addTab("tab4", St_Result_Print);

        mainTab.addTab("tab2", StudentTab);

        te_All.setBackground(new java.awt.Color(204, 255, 204));
        te_All.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        te_btn_report.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        te_btn_report.setText("Teacher Deatil's ");
        te_btn_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_reportMouseClicked(evt);
            }
        });
        jPanel2.add(te_btn_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 860, 50));

        te_All.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 860, 230));

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_personal_informations.jpg"))); // NOI18N
        jPanel3.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 180));

        te_All.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 390, 180));

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_1paymennt_teacher.jpg"))); // NOI18N
        jPanel4.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 180));

        te_All.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 400, 180));

        te_btn_rutines.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        te_btn_rutines.setText("Class Rutine");
        te_btn_rutines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_rutinesMouseClicked(evt);
            }
        });
        te_btn_rutines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                te_btn_rutinesActionPerformed(evt);
            }
        });
        te_All.add(te_btn_rutines, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 400, 50));

        te_btn_add.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        te_btn_add.setText("Personal Infomation");
        te_btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_addMouseClicked(evt);
            }
        });
        te_btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                te_btn_addActionPerformed(evt);
            }
        });
        te_All.add(te_btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 390, 50));

        Te_home.addTab("tab1", te_All);

        TeacherTab.addTab("tab4", Te_home);
        TeacherTab.addTab("tab1", Te_add);
        TeacherTab.addTab("tab2", Te_ClassRutine);
        TeacherTab.addTab("tab3", Te_Full_Report);

        mainTab.addTab("tab3", TeacherTab);

        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(204, 204, 204));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 990, 50));

        jPanel30.setBackground(new java.awt.Color(204, 204, 204));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 40, 450));

        jButton8.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton8.setText("Admit Card Print");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jPanel28.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 210, 40));

        jButton9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton9.setText("Exam Date input");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jPanel28.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 230, 40));

        jButton10.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton10.setText("Exam Date View");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jPanel28.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 230, 40));

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/exam home_pic.jpg"))); // NOI18N
        jPanel28.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 800, 370));

        jPanel27.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 990, 570));

        ex_Home.addTab("tab1", jPanel27);

        Exam_Seduel.addTab("tab3", ex_Home);

        jButton11.setText("jButton11");
        ex_date_inputTab.addTab("tab1", jButton11);

        Exam_Seduel.addTab("tab1", ex_date_inputTab);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ex_viewTab.addTab("tab1", jComboBox1);

        Exam_Seduel.addTab("tab2", ex_viewTab);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ex_admitCardTab.addTab("tab1", jComboBox2);

        Exam_Seduel.addTab("tab4", ex_admitCardTab);

        mainTab.addTab("tab4", Exam_Seduel);

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel37.setBackground(new java.awt.Color(204, 255, 204));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_payment_student.jpg"))); // NOI18N
        jLabel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel38.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 170));

        jPanel37.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 820, 170));

        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_1paymennt_teacher.jpg"))); // NOI18N
        jLabel104.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel39.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 190));

        jPanel37.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 370, 190));

        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel41.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 370, 190));

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_payment_staff.jpg"))); // NOI18N
        jLabel102.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel41.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 190));

        jPanel37.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 370, 190));

        jButton13.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton13.setText("STUDENT PAYMENT");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jPanel37.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 820, 40));

        jButton14.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton14.setText("TEACHER PAYMENT");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jPanel37.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 370, 40));

        jButton15.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton15.setText("STAFF PAYMENT");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jPanel37.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 370, 40));

        jPanel36.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        Pay_home.addTab("tab1", jPanel36);

        PaymentTab.addTab("tab4", Pay_home);

        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable2);

        jPanel43.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 940, 130));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Teacher Recipt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel43.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 330, 390));

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Teacher Payment ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel45.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 150, -1));

        jPanel43.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 350));

        jButton16.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton16.setText("Print");
        jPanel43.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 100, 30));

        jButton17.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton17.setText("Add to cart");
        jPanel43.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 30));

        jButton18.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton18.setText("Done");
        jPanel43.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 100, 30));

        jButton19.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton19.setText("Reset");
        jPanel43.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 30));

        jButton29.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton29.setText("Update");
        jPanel43.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 110, 30));

        jPanel40.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        Pay_TeachersTab.addTab("tab1", jPanel40);

        PaymentTab.addTab("tab1", Pay_TeachersTab);

        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable3);

        jPanel47.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 940, 130));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Staff Recipt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel47.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 330, 390));

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Staff Payment ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel49.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 150, -1));

        jPanel47.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 350));

        jButton20.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton20.setText("Print");
        jPanel47.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 100, 30));

        jButton21.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton21.setText("Add to cart");
        jPanel47.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 30));

        jButton22.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton22.setText("Done");
        jPanel47.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 100, 30));

        jButton23.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton23.setText("Reset");
        jPanel47.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 30));

        jButton28.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton28.setText("Update");
        jPanel47.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 110, 30));

        jPanel46.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        Pay_StaffTab.addTab("tab1", jPanel46);

        PaymentTab.addTab("tab2", Pay_StaffTab);

        jPanel50.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable4);

        jPanel51.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 940, 130));

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Staff Recipt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel52.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel51.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 330, 390));

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Student Payment ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel53.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 150, 30));

        jLabel103.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel103.setText("Exam fee");
        jPanel53.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 110, 30));

        jLabel106.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel106.setText("Session");
        jPanel53.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 30));

        jLabel107.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel107.setText("Batch Id");
        jPanel53.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, 30));

        jLabel108.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel108.setText("Class");
        jPanel53.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 30));

        jLabel109.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel109.setText("Student Roll");
        jPanel53.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 30));

        jLabel110.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel110.setText("Current Month");
        jPanel53.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 30));

        jTextField19.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jPanel53.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 120, 30));

        jTextField20.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });
        jPanel53.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 120, 30));

        jTextField21.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });
        jPanel53.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 120, 30));

        jComboBox3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select month", "January", "February", "March", "April", "May", "June", "July", "August", "september", "October", "November", "December" }));
        jPanel53.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 120, 30));

        jComboBox4.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select class", "6", "7", "8", "9", "10" }));
        jPanel53.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, 30));

        jLabel111.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel111.setText("Due Month");
        jPanel53.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 110, 30));

        jLabel112.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel112.setText("Admision fee");
        jPanel53.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 110, 30));

        jComboBox5.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select exam", "Half Yearly", "Final" }));
        jPanel53.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 120, 30));

        jTextField22.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        jPanel53.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 120, 30));

        jComboBox6.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel53.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 120, 30));

        jPanel51.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 350));

        jButton24.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton24.setText("Print");
        jPanel51.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 100, 30));

        jButton25.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton25.setText("Add to cart");
        jPanel51.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 30));

        jButton26.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton26.setText("Done");
        jPanel51.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 100, 30));

        jButton27.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton27.setText("Reset");
        jPanel51.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 30));

        jButton30.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton30.setText("Update");
        jPanel51.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 110, 30));

        jPanel50.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        Pay_StudentTab.addTab("tab1", jPanel50);

        PaymentTab.addTab("tab3", Pay_StudentTab);

        mainTab.addTab("tab5", PaymentTab);

        Staff.addTab("tab3", Staff_Home);
        Staff.addTab("tab1", Staff_Add);
        Staff.addTab("tab2", Staff_view);

        mainTab.addTab("tab6", Staff);
        mainTab.addTab("tab7", ex);
        mainTab.addTab("tab8", ex1);
        mainTab.addTab("tab9", ex2);
        mainTab.addTab("tab10", ex3);

        bodyPanal.add(mainTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -90, 990, 660));

        mainPanal.add(bodyPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 970, 560));

        getContentPane().add(mainPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(0);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(1);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);

    }//GEN-LAST:event_btnStudentMouseClicked

    private void btnTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTeacherMouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(2);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);

    }//GEN-LAST:event_btnTeacherMouseClicked

    private void btnExamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(3);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
        Exam_Seduel.setSelectedIndex(0);
    }//GEN-LAST:event_btnExamMouseClicked

    private void btnPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(4);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
        PaymentTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnPaymentMouseClicked

    private void exitTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTabMouseClicked
        // TODO add your handling code here:

        dispose();
    }//GEN-LAST:event_exitTabMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:
        btnHome.setBackground(Color.GREEN);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        // TODO add your handling code here:
        btnHome.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_btnHomeMouseExited

    private void st_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_1MouseEntered
        // TODO add your handling code here:
//         .setBackground(Color.GREEN);

    }//GEN-LAST:event_st_1MouseEntered

    private void St_btn_addmitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_addmitionMouseClicked
        // TODO add your handling code here:
        StudentTab.setSelectedIndex(1);
    }//GEN-LAST:event_St_btn_addmitionMouseClicked

    private void St_btn_DeatilsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_DeatilsMouseClicked
        // TODO add your handling code here:
        StudentTab.setSelectedIndex(2);
    }//GEN-LAST:event_St_btn_DeatilsMouseClicked

    private void st_btn_result_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_inMouseClicked
        // TODO add your handling code here:
        StudentTab.setSelectedIndex(3);
    }//GEN-LAST:event_st_btn_result_inMouseClicked

    private void st_btn_result_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_outMouseClicked
        // TODO add your handling code here:
        StudentTab.setSelectedIndex(4);
    }//GEN-LAST:event_st_btn_result_outMouseClicked

    private void te_btn_rutinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_te_btn_rutinesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_te_btn_rutinesActionPerformed

    private void te_btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_addMouseClicked
        // TODO add your handling code here:
        TeacherTab.setSelectedIndex(1);
    }//GEN-LAST:event_te_btn_addMouseClicked

    private void te_btn_rutinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_rutinesMouseClicked
        // TODO add your handling code here:
        TeacherTab.setSelectedIndex(2);
    }//GEN-LAST:event_te_btn_rutinesMouseClicked

    private void te_btn_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_reportMouseClicked
        // TODO add your handling code here:
        TeacherTab.setSelectedIndex(3);
    }//GEN-LAST:event_te_btn_reportMouseClicked

    private void btnStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStudentActionPerformed

    private void s_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_emailActionPerformed

    private void s_Student_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_Student_lastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_Student_lastActionPerformed

    private void s_birth_certificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_birth_certificateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_birth_certificateActionPerformed

    private void s_M_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_M_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_M_phoneActionPerformed

    private void s_F_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_F_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_F_phoneActionPerformed

    private void s_religionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_religionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_religionActionPerformed

    private void btnStaff1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaff1MouseClicked
        // TODO add your handling code here:
        mainTab.setSelectedIndex(5);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnStaff1MouseClicked


    private void btn_Save_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Save_MouseClicked
        // TODO add your handling code here:       
        String sql = "insert into School ( Session_year,batch_id,classs,student_Roll,full_name,fathers_name,"
                + "fathers_phone,mothers_name,mothers_phone,date_of_birth,age,gender,religion,nationality,"
                + "email,blood_group,birth_certificate,present_Address,permanent_Address)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String fullName = s_student_first.getText().trim() + " " + s_Student_last.getText().toString().trim();

        try {

            ps = con.getCon().prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(s_session.getText().toString().trim()));
            ps.setString(2, s_Batch_id.getText().toString().trim());
            ps.setString(3, s_Class_combo.getSelectedItem().toString().trim());
            ps.setString(4, s_student_id.getText().toString().trim());
            ps.setString(5, fullName);
            ps.setString(6, s_father_N.getText().toString().trim());
            ps.setString(7, s_F_phone.getText().toString().trim());
            ps.setString(8, s_Mother_N.getText().toString().trim());
            ps.setString(9, s_M_phone.getText().toString().trim());
            ps.setDate(10, dateMethod(s_dateOfBirth.getDate()));
            ps.setInt(11, Integer.parseInt(s_age.getText().toString().trim()));
            ps.setString(12, s_gender_combo.getSelectedItem().toString().trim());
            ps.setString(13, s_religion.getText().toString().trim());
            ps.setString(14, s_National.getText().toString().trim());
            ps.setString(15, s_email.getText().toString().trim());
            ps.setString(16, s_BloodGroup.getSelectedItem().toString().trim());
            ps.setInt(17, Integer.parseInt(s_birth_certificate.getText().toString().trim()));
            ps.setString(18, s_presentAddress.getText().toString().trim());
            ps.setString(19, s_parmanent.getText().toString().trim());

            ps.executeUpdate();
            ps.close();
            con.getCon().close();

            JOptionPane.showMessageDialog(rootPane, "data is save");
            AdmisionReset();
            studentTable();
//            getClassTocomboFromStudentTable();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data is not save");
        }
    }//GEN-LAST:event_btn_Save_MouseClicked

    private void btn_AdmiosionReset_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AdmiosionReset_MouseClicked
        // TODO add your handling code here:
        AdmisionReset();
    }//GEN-LAST:event_btn_AdmiosionReset_MouseClicked

    private void btn_sd_View1_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sd_View1_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sd_View1_ActionPerformed

    private void btn_sd_update_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sd_update_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sd_update_ActionPerformed

    private void btn_sd_View1_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_View1_MouseClicked
        // TODO add your handling code here:
        getDateFromDataBaseSetViewForm();
    }//GEN-LAST:event_btn_sd_View1_MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(1);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(2);
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(3);
    }//GEN-LAST:event_jButton8MouseClicked

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
        PaymentTab.setSelectedIndex(1);
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
        PaymentTab.setSelectedIndex(2);
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        // TODO add your handling code here:
        PaymentTab.setSelectedIndex(3);
    }//GEN-LAST:event_jButton13MouseClicked

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void te_btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_te_btn_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_te_btn_addActionPerformed

    private void btn_sd_ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_ResetMouseClicked
        // TODO add your handling code here:
        StudentDetailsReset();
    }//GEN-LAST:event_btn_sd_ResetMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_DashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Exam_Seduel;
    private javax.swing.JTabbedPane HomeTab;
    private javax.swing.JTabbedPane Pay_StaffTab;
    private javax.swing.JTabbedPane Pay_StudentTab;
    private javax.swing.JTabbedPane Pay_TeachersTab;
    private javax.swing.JTabbedPane Pay_home;
    private javax.swing.JTabbedPane PaymentTab;
    private javax.swing.JPanel St_4;
    private javax.swing.JTabbedPane St_Admition;
    private javax.swing.JPanel St_All;
    private javax.swing.JTabbedPane St_Home;
    private javax.swing.JTabbedPane St_Result_Print;
    private javax.swing.JTabbedPane St_Result_input;
    private javax.swing.JButton St_btn_Deatils;
    private javax.swing.JButton St_btn_addmition;
    private javax.swing.JTabbedPane St_deatils;
    private javax.swing.JTabbedPane Staff;
    private javax.swing.JTabbedPane Staff_Add;
    private javax.swing.JTabbedPane Staff_Home;
    private javax.swing.JTabbedPane Staff_view;
    private javax.swing.JTabbedPane StudentTab;
    private javax.swing.JScrollPane Table_Student;
    private javax.swing.JTabbedPane Te_ClassRutine;
    private javax.swing.JTabbedPane Te_Full_Report;
    private javax.swing.JTabbedPane Te_add;
    private javax.swing.JTabbedPane Te_home;
    private javax.swing.JTabbedPane TeacherTab;
    private javax.swing.JPanel bodyPanal;
    private javax.swing.JButton btnExam;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnStaff1;
    private javax.swing.JButton btnStudent;
    private javax.swing.JButton btnTeacher;
    private javax.swing.JButton btn_AdmiosionReset_;
    private javax.swing.JButton btn_Save_;
    private javax.swing.JButton btn_sd_Reset;
    private javax.swing.JButton btn_sd_View1_;
    private javax.swing.JButton btn_sd_View_;
    private javax.swing.JButton btn_sd_delete_;
    private javax.swing.JButton btn_sd_update_;
    private javax.swing.JTabbedPane ex;
    private javax.swing.JTabbedPane ex1;
    private javax.swing.JTabbedPane ex2;
    private javax.swing.JTabbedPane ex3;
    private javax.swing.JTabbedPane ex_Home;
    private javax.swing.JTabbedPane ex_admitCardTab;
    private javax.swing.JTabbedPane ex_date_inputTab;
    private javax.swing.JTabbedPane ex_viewTab;
    private javax.swing.JButton exitTab;
    private javax.swing.JPanel headPanal;
    private javax.swing.JTabbedPane homePanal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel mainPanal;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JPanel optionPanal;
    private javax.swing.JTextField s_Batch_id;
    private javax.swing.JComboBox<String> s_BloodGroup;
    private javax.swing.JComboBox<String> s_Class_combo;
    private javax.swing.JTextField s_F_phone;
    private javax.swing.JTextField s_M_phone;
    private javax.swing.JTextField s_Mother_N;
    private javax.swing.JTextField s_National;
    private javax.swing.JTextField s_Student_last;
    private javax.swing.JTable s_Table;
    private javax.swing.JTextField s_age;
    private javax.swing.JTextField s_birth_certificate;
    private com.toedter.calendar.JDateChooser s_dateOfBirth;
    private javax.swing.JTextField s_email;
    private javax.swing.JTextField s_father_N;
    private javax.swing.JComboBox<String> s_gender_combo;
    private javax.swing.JTextArea s_parmanent;
    private javax.swing.JTextArea s_presentAddress;
    private javax.swing.JTextField s_religion;
    private javax.swing.JTextField s_session;
    private javax.swing.JTextField s_student_first;
    private javax.swing.JTextField s_student_id;
    private javax.swing.JLabel sd_Age;
    private javax.swing.JLabel sd_Dob;
    private javax.swing.JLabel sd_Father_phone;
    private javax.swing.JTextField sd_batchId;
    private javax.swing.JLabel sd_birth_cer;
    private javax.swing.JLabel sd_blood;
    private javax.swing.JComboBox<String> sd_class_combo;
    private javax.swing.JLabel sd_class_view;
    private javax.swing.JLabel sd_email;
    private javax.swing.JLabel sd_fatherName;
    private javax.swing.JLabel sd_fullName;
    private javax.swing.JLabel sd_gender_combo;
    private javax.swing.JLabel sd_motherName;
    private javax.swing.JLabel sd_motherPhone;
    private javax.swing.JLabel sd_nationality;
    private javax.swing.JTextArea sd_parmanentA;
    private javax.swing.JTextArea sd_presentA;
    private javax.swing.JLabel sd_religion;
    private javax.swing.JTextField sd_session;
    private javax.swing.JLabel sd_st_name;
    private javax.swing.JTextField sd_studentID;
    private javax.swing.JPanel st_1;
    private javax.swing.JPanel st_2;
    private javax.swing.JPanel st_3;
    private javax.swing.JPanel st_add;
    private javax.swing.JPanel st_admision_main_pan;
    private javax.swing.JButton st_btn_result_in;
    private javax.swing.JButton st_btn_result_out;
    private javax.swing.JTable tableGrade;
    private javax.swing.JPanel te_All;
    private javax.swing.JButton te_btn_add;
    private javax.swing.JButton te_btn_report;
    private javax.swing.JButton te_btn_rutines;
    // End of variables declaration//GEN-END:variables
}
