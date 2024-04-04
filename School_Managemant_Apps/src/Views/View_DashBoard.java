/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template */
package Views;

import com.mysql.cj.xdevapi.UpdateStatement;
import dataBaseConnection.DataBaseConnection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.net.InterfaceAddress;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;

public class View_DashBoard extends javax.swing.JFrame {

    private Object browseImageFile;

    public View_DashBoard() {
        initComponents();
        setLocationRelativeTo(null);
        studentTable();
        resultTable();
        AllMethods();
        studentPaymentTable();
        teacherTable();
        teacherssalarytable();
        all_st_lable.setVisible(true);
        one_st_lable.setVisible(false);
//        getClassTocomboFromStudentTable();
    }

    public void AllMethods() {
        TotalStudentReport_6();
        TotalStudentReport_7();
        TotalStudentReport_8();
        TotalStudentReport_9();
        TotalStudentReport_10();
        TotalPassReport_6();
        TotalFailReport_6();
        TotalPassReport_7();
        TotalFailReport_7();
        TotalPassReport_8();
        TotalFailReport_8();
        TotalPassReport_9();
        TotalFailReport_9();
        TotalPassReport_10();
        TotalFailReport_10();
        TotalTeacherReport_Male();
        TotalTeacherReport_FeMale();
        TotalStaffReport_Male();
        TotalStaffReport_FeMale();
        todayFeeReport();
        todayTotalCollectionReport();
        monthlyTotalCollection();
    }
    DataBaseConnection con = new DataBaseConnection();
    PreparedStatement ps;
    ResultSet rs;
    private String selectedImagePath;

    public java.sql.Date dateMethod(java.util.Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    LocalDate currentDate = LocalDate.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

    public Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Parsing failed: " + e.getMessage());
            return null; // Handle the exception by returning null
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
    String[] addmidionDataShow = {"Session", "Batch_id", "Class", "Roll", "Full_name", "Father_name", "Mother_name", "F_phone",
        "M_phone", "gender", "age", "Dob", "birth_c", "Blood_g", "email", "nation", "religion", "present", "parmanent", "admisionDate"};

    public void studentTable() {
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(addmidionDataShow);
        s_Table.setModel(modelTable);
        String sql = "Select * from school  where catagory like 'S%'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int session = rs.getInt("Session_year");
                String batch = rs.getString("batch_id");
                String classs = rs.getString("classs");
                int roll = rs.getInt("student_Roll");
                String name = rs.getString("full_name");
                String father = rs.getString("fathers_name");
                String mother = rs.getString("mothers_name");
                String fatherP = rs.getString("fa_te_phone");
                String motherP = rs.getString("mothers_phone");
                String dob = rs.getString("date_of_birth");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String nation = rs.getString("nationality");
                String religion = rs.getString("religion");
                String blood = rs.getString("blood_group");
                String birth = rs.getString("birth_certificate");
                String present = rs.getString("present_Address");
                String parmanent = rs.getString("permanent_Address");
                String admition = rs.getString("joiningdate");
                modelTable.addRow(new Object[]{session, batch, classs, roll, name, father, mother, fatherP, motherP, gender, age, dob, birth, blood, email,
                    nation, religion, present, parmanent, admition});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String[] teacherTableDataShow = {"ID", "full_name", "father_name", "mother_name", "contact_number", "dateOf_birth", "gender", "age", "religion",
        "nationality", "email", "blood", "nationalId", "presentAdd", "parmanentAdd", "designation", "joiningDate", "qulification", "catagory"};

    public void teacherTable() {
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(teacherTableDataShow);
        t_table.setModel(modelTable);

        String sql = "Select * from school  where catagory like 'T%'"
                + "or catagory like 'SF%'";

        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("full_name");
                String father = rs.getString("fathers_name");
                String mother = rs.getString("mothers_name");
                String contact = rs.getString("fa_te_phone");
                String dob = rs.getString("date_of_birth");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String riligion = rs.getString("religion");
                String nation = rs.getString("nationality");
                String email = rs.getString("email");
                String blood = rs.getString("blood_group");
                int naid = rs.getInt("birth_certificate");
                String present = rs.getString("present_Address");
                String parmanent = rs.getString("permanent_Address");
                String deg = rs.getString("designation");
                String join = rs.getString("joiningdate");
                String qualifi = rs.getString("qualification");
                String catagory = rs.getString("catagory");

//                if(rs.getString("catagory")=="T"){
//                String catagory="Teacher";
//                
//                }
//                else if(rs.getString("catagory")=="SF"){
//                String catagory="STAFF";
//                
//                }
                modelTable.addRow(new Object[]{id, fullname, father, mother, contact, dob, gender, age, riligion, nation, email, blood, naid, present, parmanent, deg, join, qualifi, catagory});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDateFromDataBaseSetViewForm() {
        String sql = "select * from school where Session_year=? and batch_id=? and classs=? and student_Roll=?";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as needed
            ps = con.getCon().prepareStatement(sql);
            int year = Integer.parseInt(sd_session.getText().toString().trim());
            ps.setInt(1, year);
//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, sd_batchId.getText().toString().trim());
            ps.setString(3, sd_class_combo.getSelectedItem().toString().trim());
            ps.setString(4, sd_studentID.getText().toString().trim());
            rs = ps.executeQuery();
            while (rs.next()) {
                sd_fullName.setText(rs.getString("full_name"));
                sd_st_name.setText(rs.getString("full_name"));
                sd_class_view.setText(rs.getString("classs"));
                sd_fatherName.setText(rs.getString("fathers_name"));
                sd_Father_phone.setText(rs.getString("fa_te_phone"));
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
                
                
                 String picture_path = rs.getString("picture");
                 
                 
                 System.out.println("Image Path: " + picture_path);
                 ImageIcon imageIcon2=new ImageIcon(picture_path);
            Image image2=imageIcon2.getImage().getScaledInstance(sd_image.getWidth(), sd_image.getHeight(), Image.SCALE_SMOOTH);
            sd_image.setIcon(new ImageIcon(image2));
////

//         
            }
            rs.close();
            ps.close();
            con.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "No matching record found.");
        }
    }

    public void studentAdmisionReset() {
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
        s_admisiondate.setDate(null); // Reset date of birth to null
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

    public void resultReset() {
        r_Serial.setText("");
        r_session.setText("");
        r_batch_id.setText("");
        r_class.setSelectedIndex(0);
        r_St_Roll.setText("");
        r_bangla.setText("");
        r_english.setText("");
        r_math.setText("");
        r_scince.setText("");
        r_social.setText("");
        r_islam.setText("");
        r_total.setText("");
        r_avarage.setText("");
        r_gpa.setText("");
        r_grade.setText("");
        r_passFail.setText("");
        r_examSelect.setSelectedIndex(0);
    }

    public void resultShowReset() {
        rs_bangla.setText("");
        rs_english.setText("");
        rs_math.setText("");
        rs_scince.setText("");
        rs_social.setText("");
        rs_islam.setText("");
        rs_total.setText("");
        rs_gpa.setText("");
        rs_PassFail.setText("");
        rsg_bangla.setText("");
        rsg_english.setText("");
        rsg_math.setText("");
        rsg_scince.setText("");
        rsg_social.setText("");
        rsg_islam.setText("");
        rsg_total.setText("");
        rsg_gpa.setText("");
        rs_catagory.setText("");
        rs_roll.setText("");
        rs_name.setText("");
        rs_batch.setText("");
        rs_session.setText("");
        rs_class.setText("");
        rs_input_class.setSelectedIndex(0);  // Assuming rs_input_class is a JComboBox
        rs_input_batch.setText("");
        rs_input_roll.setText("");
        rs_input_session.setText("");
    }

    public void teacherAddReset() {
        t_Id.setText("");
        t_sf_catagory.setSelectedIndex(0);
        t_teacher_first1.setText("");
        t_teacher_last1.setText("");
        t_father_N1.setText("");
        t_contact_number.setText("");
        t_Mother_N1.setText("");
        t_dateOfBirth1.setDate(null);
        t_age1.setText("");
        t_gender_combo1.setSelectedIndex(0);
        t_religion1.setText("");
        t_National1.setText("");
        t_email1.setText("");
        t_BloodGroup1.setSelectedIndex(0);
        t_national_id.setText("");
        t_presentAddress1.setText("");
        t_parmanent1.setText("");
        t_qualification.setSelectedIndex(0);
        t_designationcombo1.setSelectedIndex(0);
        t_joiningDate.setDate(null);
        t_search.setText("");
    }

    public void StudentPaymentReset() {
        // Reset text fields
        pay_st_id.setText("");
        pay_st_session.setText("");
        pay_st_batch.setText("");
        pay_st_Roll.setText("");
        pay_st_FeeAmount.setText("");
        pay_st_exam_fee.setText("");
        pay_st_admision.setText("");
        // Reset combo box
        pay_st_class_combo.setSelectedIndex(0);
        pay_st_catagory.setSelectedIndex(0);
        // Reset date picker (assuming pay_st_Date is a JDatePicker)
        pay_st_Date.setDate(null);

        pay_st_Mon_jan.setSelected(false);
        pay_st_Mon_feb.setSelected(false);
        pay_st_Mon_mar.setSelected(false);
        pay_st_Mon_apr.setSelected(false);
        pay_st_Mon_may.setSelected(false);
        pay_st_Mon_june.setSelected(false);
        pay_st_Mon_july.setSelected(false);
        pay_st_Mon_aug.setSelected(false);
        pay_st_Mon_sep.setSelected(false);
        pay_st_Mon_Oct.setSelected(false);
        pay_st_Mon_nov.setSelected(false);
        pay_st_Mon_dec.setSelected(false);

        pay_st_TotalFee.setText("");
    }

    public void teachersalaryReset() {
        pay_te_id.setText("");
        pay_te_name.setText("");
        pay_te_salary.setText("");
        pay_te_bonus.setText("");
        pay_te_Total.setText(""); // Reset combo box
        pay_te_descript.setText("");
        pay_te_date.setDate(null);
        pay_te_mon_jan.setSelected(false);
        pay_te_mon_feb.setSelected(false);
        pay_te_mon_mar.setSelected(false);
        pay_te_mon_apr.setSelected(false);
        pay_te_mon_may.setSelected(false);
        pay_te_mon_june.setSelected(false);
        pay_te_mon_july.setSelected(false);
        pay_te_mon_aug.setSelected(false);
        pay_te_mon_sep.setSelected(false);
        pay_te_mon_oct.setSelected(false);
        pay_te_mon_nov.setSelected(false);
        pay_te_mon_dec.setSelected(false);
    }

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
        btnClassRutine = new javax.swing.JButton();
        jLabel237 = new javax.swing.JLabel();
        bodyPanal = new javax.swing.JPanel();
        mainTab = new javax.swing.JTabbedPane();
        HomeTab = new javax.swing.JTabbedPane();
        homePanal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        d_10_fail = new javax.swing.JLabel();
        d_6_pass = new javax.swing.JLabel();
        d_6_fail = new javax.swing.JLabel();
        d_7_pass = new javax.swing.JLabel();
        d_8_pass = new javax.swing.JLabel();
        d_7_fail = new javax.swing.JLabel();
        d_8_fail = new javax.swing.JLabel();
        d_9_pass = new javax.swing.JLabel();
        d_9_fail = new javax.swing.JLabel();
        d_10_pass = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        d_te_Female = new javax.swing.JLabel();
        d_te_male = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        d_sf_male = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        d_sf_Female = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        d_class_10 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        d_class_6 = new javax.swing.JLabel();
        d_class_7 = new javax.swing.JLabel();
        d_class_8 = new javax.swing.JLabel();
        d_class_9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        d_monthlyCollection = new javax.swing.JLabel();
        d_todaysFee = new javax.swing.JLabel();
        d_todayTotalCollection = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        d_total_pay = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        d_te_pay = new javax.swing.JLabel();
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
        s_Image_lable = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btn_Image_set_ = new javax.swing.JButton();
        btn_Save_ = new javax.swing.JButton();
        btn_s_Update_ = new javax.swing.JButton();
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
        s_admisiondate = new com.toedter.calendar.JDateChooser();
        s_catagory = new javax.swing.JLabel();
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
        Student_details_panal = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        sd_image = new javax.swing.JLabel();
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
        btn_sd_Reset = new javax.swing.JButton();
        jLabel115 = new javax.swing.JLabel();
        btn_sd_Print_ = new javax.swing.JButton();
        St_Result_input = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        r_session = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        r_St_Roll = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        r_batch_id = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        r_class = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableResultInput = new javax.swing.JTable();
        r_date = new com.toedter.calendar.JDateChooser();
        r_Serial = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        btn_R_save_ = new javax.swing.JButton();
        btn_R_Reset = new javax.swing.JButton();
        btn_R_update = new javax.swing.JButton();
        btn_R_Delete = new javax.swing.JButton();
        btn_R_RollBack_ = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        r_bangla = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        r_examSelect = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        r_english = new javax.swing.JTextField();
        r_math = new javax.swing.JTextField();
        r_scince = new javax.swing.JTextField();
        r_social = new javax.swing.JTextField();
        r_islam = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        r_total = new javax.swing.JTextField();
        r_avarage = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        r_gpa = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        r_grade = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        r_passFail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        St_Result_Print = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        rs_input_session = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        rs_input_roll = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        rs_input_batch = new javax.swing.JTextField();
        btn_rs_View_ = new javax.swing.JButton();
        rs_input_class = new javax.swing.JComboBox<>();
        Result_sheet_panal = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        rsg_gpa = new javax.swing.JLabel();
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
        rs_bangla = new javax.swing.JLabel();
        rs_english = new javax.swing.JLabel();
        rs_math = new javax.swing.JLabel();
        rs_scince = new javax.swing.JLabel();
        rs_social = new javax.swing.JLabel();
        rs_islam = new javax.swing.JLabel();
        rs_total = new javax.swing.JLabel();
        rs_gpa = new javax.swing.JLabel();
        rsg_bangla = new javax.swing.JLabel();
        rsg_english = new javax.swing.JLabel();
        rsg_math = new javax.swing.JLabel();
        rsg_scince = new javax.swing.JLabel();
        rsg_social = new javax.swing.JLabel();
        rsg_islam = new javax.swing.JLabel();
        rsg_total = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        rs_name = new javax.swing.JLabel();
        rs_catagory = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        rs_session = new javax.swing.JLabel();
        rs_roll = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        rs_class = new javax.swing.JLabel();
        rs_batch = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        rs_PassFail = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        btn_rs_Reset_ = new javax.swing.JButton();
        btn_Result_sheet_panal_Print_ = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        TeacherTab = new javax.swing.JTabbedPane();
        Te_home = new javax.swing.JTabbedPane();
        te_All = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        te_btn_report = new javax.swing.JButton();
        jLabel131 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        te_btn_rutines = new javax.swing.JButton();
        te_btn_add = new javax.swing.JButton();
        Te_add = new javax.swing.JTabbedPane();
        st_add1 = new javax.swing.JPanel();
        Table_Student1 = new javax.swing.JScrollPane();
        t_table = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        te_Image_set_lable = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btn_te_Image_set_ = new javax.swing.JButton();
        btn_Save_1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_AdmiosionReset_1 = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        t_Id = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        t_email1 = new javax.swing.JTextField();
        t_designationcombo1 = new javax.swing.JComboBox<>();
        jLabel134 = new javax.swing.JLabel();
        t_teacher_first1 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        t_teacher_last1 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        t_father_N1 = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        t_national_id = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        t_National1 = new javax.swing.JTextField();
        t_dateOfBirth1 = new com.toedter.calendar.JDateChooser();
        jLabel139 = new javax.swing.JLabel();
        t_BloodGroup1 = new javax.swing.JComboBox<>();
        jLabel140 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        t_parmanent1 = new javax.swing.JTextArea();
        jLabel141 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        t_presentAddress1 = new javax.swing.JTextArea();
        jLabel142 = new javax.swing.JLabel();
        t_Mother_N1 = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        t_contact_number = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        t_gender_combo1 = new javax.swing.JComboBox<>();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        t_religion1 = new javax.swing.JTextField();
        jLabel149 = new javax.swing.JLabel();
        t_age1 = new javax.swing.JTextField();
        jLabel150 = new javax.swing.JLabel();
        t_qualification = new javax.swing.JComboBox<>();
        t_joiningDate = new com.toedter.calendar.JDateChooser();
        sf_ctagory = new javax.swing.JLabel();
        t_sf_catagory = new javax.swing.JComboBox<>();
        t_ctagory = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        t_search = new javax.swing.JTextField();
        btn_t_ta_search_ = new javax.swing.JButton();
        jLabel151 = new javax.swing.JLabel();
        Te_ClassRutine = new javax.swing.JTabbedPane();
        Te_Full_Report = new javax.swing.JTabbedPane();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel152 = new javax.swing.JLabel();
        btn_td_View_ = new javax.swing.JButton();
        td_id = new javax.swing.JTextField();
        btn_td_Update = new javax.swing.JButton();
        btn_td_delete_ = new javax.swing.JButton();
        btn_td_Print_ = new javax.swing.JButton();
        btn_td_Reset = new javax.swing.JButton();
        jPanel57 = new javax.swing.JPanel();
        Student_details_panal1 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        Degination = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        td_national_id = new javax.swing.JLabel();
        td_fullName1 = new javax.swing.JLabel();
        td_fatherName = new javax.swing.JLabel();
        td_JoiningDate = new javax.swing.JLabel();
        td_motherName = new javax.swing.JLabel();
        td_ContactNumber = new javax.swing.JLabel();
        td_Dob = new javax.swing.JLabel();
        td_Age = new javax.swing.JLabel();
        td_religion = new javax.swing.JLabel();
        td_nationality = new javax.swing.JLabel();
        td_blood = new javax.swing.JLabel();
        td_email = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        td_parmanentA = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        td_presentA = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        TeacherDeatilsTable = new javax.swing.JTable();
        td_Designation_view = new javax.swing.JLabel();
        td_te_name = new javax.swing.JLabel();
        td_gender_combo = new javax.swing.JLabel();
        Exam_Seduel = new javax.swing.JTabbedPane();
        ex_Home = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        btn_admit = new javax.swing.JButton();
        btn_exam_in = new javax.swing.JButton();
        btn_exam_out = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        ex_date_inputTab = new javax.swing.JTabbedPane();
        jPanel71 = new javax.swing.JPanel();
        jPanel72 = new javax.swing.JPanel();
        jPanel79 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jLabel196 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jPanel81 = new javax.swing.JPanel();
        jLabel202 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        ex_viewTab = new javax.swing.JTabbedPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        ex_admitCardTab = new javax.swing.JTabbedPane();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        btn_ads_Reset_ = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        admit_cardPanal = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        ad_session = new javax.swing.JLabel();
        ad_name = new javax.swing.JLabel();
        ad_father = new javax.swing.JLabel();
        ad_class = new javax.swing.JLabel();
        ad_roll = new javax.swing.JLabel();
        ad_batch = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        btn_ads_view_ = new javax.swing.JButton();
        jLabel171 = new javax.swing.JLabel();
        ads_roll = new javax.swing.JTextField();
        jLabel181 = new javax.swing.JLabel();
        ads_class = new javax.swing.JComboBox<>();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        ads_session = new javax.swing.JTextField();
        ads_batch = new javax.swing.JTextField();
        PaymentTab = new javax.swing.JTabbedPane();
        Pay_home = new javax.swing.JTabbedPane();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        btn_pay_st_ = new javax.swing.JButton();
        btn_pay_te = new javax.swing.JButton();
        Pay_TeachersTab = new javax.swing.JTabbedPane();
        jPanel40 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        teachersTable = new javax.swing.JTable();
        teacher_recipt = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        pay_te_date = new com.toedter.calendar.JDateChooser();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        pay_te_bonus = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        pay_te_id = new javax.swing.JTextField();
        jLabel187 = new javax.swing.JLabel();
        pay_te_mon_dec = new javax.swing.JCheckBox();
        pay_te_mon_jan = new javax.swing.JCheckBox();
        pay_te_mon_july = new javax.swing.JCheckBox();
        pay_te_mon_feb = new javax.swing.JCheckBox();
        pay_te_mon_aug = new javax.swing.JCheckBox();
        pay_te_mon_mar = new javax.swing.JCheckBox();
        pay_te_mon_sep = new javax.swing.JCheckBox();
        pay_te_mon_apr = new javax.swing.JCheckBox();
        pay_te_mon_oct = new javax.swing.JCheckBox();
        pay_te_mon_may = new javax.swing.JCheckBox();
        pay_te_mon_nov = new javax.swing.JCheckBox();
        pay_te_mon_june = new javax.swing.JCheckBox();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        pay_te_Total = new javax.swing.JTextField();
        pay_te_name = new javax.swing.JTextField();
        jLabel190 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        pay_te_descript = new javax.swing.JTextArea();
        jLabel191 = new javax.swing.JLabel();
        pay_te_salary = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        btn_save_te_pay_ = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        Pay_StudentTab = new javax.swing.JTabbedPane();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        paymentStudentTable = new javax.swing.JTable();
        jPanel53 = new javax.swing.JPanel();
        pay_st_Date = new com.toedter.calendar.JDateChooser();
        jLabel103 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        pay_st_Roll = new javax.swing.JTextField();
        pay_st_id = new javax.swing.JTextField();
        pay_st_TotalFee = new javax.swing.JTextField();
        pay_st_class_combo = new javax.swing.JComboBox<>();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        pay_st_catagory = new javax.swing.JComboBox<>();
        pay_st_batch = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        pay_st_session = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        pay_st_admision = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        pay_st_exam_fee = new javax.swing.JTextField();
        pay_st_Mon_july = new javax.swing.JCheckBox();
        pay_st_Mon_jan = new javax.swing.JCheckBox();
        pay_st_Mon_feb = new javax.swing.JCheckBox();
        pay_st_Mon_aug = new javax.swing.JCheckBox();
        pay_st_Mon_mar = new javax.swing.JCheckBox();
        pay_st_Mon_sep = new javax.swing.JCheckBox();
        pay_st_Mon_may = new javax.swing.JCheckBox();
        pay_st_Mon_nov = new javax.swing.JCheckBox();
        pay_st_Mon_apr = new javax.swing.JCheckBox();
        pay_st_Mon_dec = new javax.swing.JCheckBox();
        pay_st_Mon_june = new javax.swing.JCheckBox();
        pay_st_Mon_Oct = new javax.swing.JCheckBox();
        jLabel128 = new javax.swing.JLabel();
        pay_st_FeeAmount = new javax.swing.JTextField();
        btn_st_pay_print = new javax.swing.JButton();
        btn_st_pay_AddCurt_ = new javax.swing.JButton();
        btn_st_pay_save_ = new javax.swing.JButton();
        btn_st_pay_reset = new javax.swing.JButton();
        btn_st_pay_update = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        Payment_student_recipt = new javax.swing.JTextArea();
        all_st_lable = new javax.swing.JLabel();
        Btn_AllStudent = new javax.swing.JButton();
        Btn_OneStudent = new javax.swing.JButton();
        one_st_lable = new javax.swing.JLabel();
        ex1 = new javax.swing.JTabbedPane();
        ex2 = new javax.swing.JTabbedPane();
        ex3 = new javax.swing.JTabbedPane();
        ClassRutineTab = new javax.swing.JTabbedPane();
        rutineHomeTab = new javax.swing.JTabbedPane();
        jPanel76 = new javax.swing.JPanel();
        jPanel77 = new javax.swing.JPanel();
        btn_rutine_home_V = new javax.swing.JButton();
        btn_rutine_home_m = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inputRutineTab = new javax.swing.JTabbedPane();
        jPanel49 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        R_session = new javax.swing.JTextField();
        jLabel238 = new javax.swing.JLabel();
        R_day = new javax.swing.JComboBox<>();
        R_class = new javax.swing.JComboBox<>();
        jLabel239 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        jLabel248 = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        R_pre7_sub = new javax.swing.JComboBox<>();
        R_pre7_tea = new javax.swing.JComboBox<>();
        R_pre2_sub = new javax.swing.JComboBox<>();
        R_pre3_sub = new javax.swing.JComboBox<>();
        R_pre4_sub = new javax.swing.JComboBox<>();
        R_pre5_sub = new javax.swing.JComboBox<>();
        R_pre6_sub = new javax.swing.JComboBox<>();
        R_pre1_sub = new javax.swing.JComboBox<>();
        R_pre7_time = new javax.swing.JTextField();
        R_pre1_time = new javax.swing.JTextField();
        R_pre2_time = new javax.swing.JTextField();
        R_pre3_time = new javax.swing.JTextField();
        R_pre4_time = new javax.swing.JTextField();
        R_pre5_time = new javax.swing.JTextField();
        R_pre6_time = new javax.swing.JTextField();
        R_pre1_tea = new javax.swing.JComboBox<>();
        R_pre2_tea = new javax.swing.JComboBox<>();
        R_pre3_tea = new javax.swing.JComboBox<>();
        R_pre4_tea = new javax.swing.JComboBox<>();
        R_pre5_tea = new javax.swing.JComboBox<>();
        R_pre6_tea = new javax.swing.JComboBox<>();
        classRutineView = new javax.swing.JTabbedPane();
        jPanel62 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel216 = new javax.swing.JLabel();
        p_sessoion = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel217 = new javax.swing.JLabel();
        p__class = new javax.swing.JComboBox<>();
        btn_Rutine_print_ = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        RutinePanal = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jLabel195 = new javax.swing.JLabel();
        P5_s_sun = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        P1_s_sun = new javax.swing.JLabel();
        P2_s_sun = new javax.swing.JLabel();
        P3_s_sun = new javax.swing.JLabel();
        P4_s_sun = new javax.swing.JLabel();
        P7_s_sun = new javax.swing.JLabel();
        P6_s_sun = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel257 = new javax.swing.JLabel();
        jLabel258 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        p1_time = new javax.swing.JLabel();
        p2_time = new javax.swing.JLabel();
        p3_time = new javax.swing.JLabel();
        p4_time = new javax.swing.JLabel();
        p5_time = new javax.swing.JLabel();
        p6_time = new javax.swing.JLabel();
        p7_time = new javax.swing.JLabel();
        P1_s_mon = new javax.swing.JLabel();
        P2_s_mon = new javax.swing.JLabel();
        P3_s_mon = new javax.swing.JLabel();
        P4_s_mon = new javax.swing.JLabel();
        P5_s_mon = new javax.swing.JLabel();
        P6_s_mon = new javax.swing.JLabel();
        P7_s_mon = new javax.swing.JLabel();
        P1_s_tues = new javax.swing.JLabel();
        P2_s_tues = new javax.swing.JLabel();
        P3_s_thus = new javax.swing.JLabel();
        P4_s_thus = new javax.swing.JLabel();
        P5_s_thus = new javax.swing.JLabel();
        P6_s_thus = new javax.swing.JLabel();
        P7_s_thus = new javax.swing.JLabel();
        P1_s_wed = new javax.swing.JLabel();
        P2_s_wed = new javax.swing.JLabel();
        P3_s_wed = new javax.swing.JLabel();
        P4_s_wed = new javax.swing.JLabel();
        P5_s_wed = new javax.swing.JLabel();
        P6_s_wed = new javax.swing.JLabel();
        P7_s_wed = new javax.swing.JLabel();
        P1_s_th = new javax.swing.JLabel();
        P2_s_th = new javax.swing.JLabel();
        P3_s_th = new javax.swing.JLabel();
        P4_s_th = new javax.swing.JLabel();
        P5_s_th = new javax.swing.JLabel();
        P6_s_th = new javax.swing.JLabel();
        P7_s_th = new javax.swing.JLabel();
        p_Class_show = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();

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
        jLabel1.setText("LALBAG MODEL SCHOOL AND COLLAGE, DHAKA-1213");
        headPanal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/empty-blackboard (2).jpg"))); // NOI18N
        headPanal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        mainPanal.add(headPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 90));

        optionPanal.setBackground(new java.awt.Color(153, 255, 204));
        optionPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setBackground(new java.awt.Color(204, 255, 204));
        btnHome.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/home.png"))); // NOI18N
        btnHome.setText("HOME");
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHome.setMaximumSize(new java.awt.Dimension(131, 31));
        btnHome.setMinimumSize(new java.awt.Dimension(131, 31));
        btnHome.setPreferredSize(new java.awt.Dimension(131, 31));
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
        optionPanal.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 160, 40));

        btnStudent.setBackground(new java.awt.Color(204, 255, 204));
        btnStudent.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/graduated.png"))); // NOI18N
        btnStudent.setText("STUDENT");
        btnStudent.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStudent.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStudentMouseExited(evt);
            }
        });
        optionPanal.add(btnStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 160, 40));

        btnTeacher.setBackground(new java.awt.Color(204, 255, 204));
        btnTeacher.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        btnTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/teacherupdate (2).png"))); // NOI18N
        btnTeacher.setText("TEACHER & Staff");
        btnTeacher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTeacher.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTeacherMouseExited(evt);
            }
        });
        btnTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTeacherActionPerformed(evt);
            }
        });
        optionPanal.add(btnTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 160, 40));

        btnExam.setBackground(new java.awt.Color(204, 255, 204));
        btnExam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnExam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/exam.png"))); // NOI18N
        btnExam.setText("EXAM Rutine");
        btnExam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExamMouseExited(evt);
            }
        });
        btnExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExamActionPerformed(evt);
            }
        });
        optionPanal.add(btnExam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 160, 40));

        btnPayment.setBackground(new java.awt.Color(204, 255, 204));
        btnPayment.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/bill.png"))); // NOI18N
        btnPayment.setText("PAYMENT");
        btnPayment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPayment.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPaymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPaymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPaymentMouseExited(evt);
            }
        });
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        optionPanal.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 160, 40));

        exitTab.setBackground(new java.awt.Color(204, 255, 204));
        exitTab.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        exitTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/cross.png"))); // NOI18N
        exitTab.setText("Exit");
        exitTab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exitTab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        exitTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTabMouseExited(evt);
            }
        });
        optionPanal.add(exitTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 160, 40));

        btnStaff1.setBackground(new java.awt.Color(204, 255, 204));
        btnStaff1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnStaff1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/staffs.png"))); // NOI18N
        btnStaff1.setText("STAFF");
        btnStaff1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStaff1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnStaff1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaff1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStaff1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStaff1MouseExited(evt);
            }
        });
        optionPanal.add(btnStaff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 160, 40));

        btnClassRutine.setBackground(new java.awt.Color(204, 255, 204));
        btnClassRutine.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        btnClassRutine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/exam.png"))); // NOI18N
        btnClassRutine.setText("Class Rutine");
        btnClassRutine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClassRutine.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClassRutine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClassRutineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClassRutineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClassRutineMouseExited(evt);
            }
        });
        optionPanal.add(btnClassRutine, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 160, 40));

        jLabel237.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/optionPanal.jpg"))); // NOI18N
        optionPanal.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 560));

        mainPanal.add(optionPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 180, 560));

        bodyPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("Result Summary");
        jLabel86.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 30));

        jLabel87.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel87.setText("Class - 6");
        jPanel5.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 20));

        jLabel88.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel88.setText("Class - 7");
        jPanel5.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, 20));

        jLabel89.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel89.setText("Class - 8");
        jPanel5.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, 20));

        jLabel90.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel90.setText("Class - 9");
        jPanel5.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, 20));

        jLabel91.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel91.setText("Class - 10");
        jPanel5.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 20));

        jLabel92.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel92.setText("Fail");
        jPanel5.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 40, -1));

        jLabel93.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel93.setText("Pass");
        jPanel5.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 50, -1));

        d_10_fail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_10_fail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_10_fail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_10_fail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 50, 20));

        d_6_pass.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_6_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_6_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_6_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 50, 20));

        d_6_fail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_6_fail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_6_fail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_6_fail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, 20));

        d_7_pass.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_7_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_7_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_7_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 50, 20));

        d_8_pass.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_8_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_8_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_8_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 50, 20));

        d_7_fail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_7_fail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_7_fail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_7_fail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 50, 20));

        d_8_fail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_8_fail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_8_fail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_8_fail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 50, 20));

        d_9_pass.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_9_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_9_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_9_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 50, 20));

        d_9_fail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_9_fail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_9_fail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_9_fail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 50, 20));

        d_10_pass.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_10_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_10_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(d_10_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 50, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, 230, 210));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("Teacher Count");
        jLabel102.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 30));

        jLabel192.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel192.setText("Male");
        jPanel6.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jLabel203.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel203.setText("Female");
        jPanel6.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 20));

        d_te_Female.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_te_Female.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_te_Female.setText("0");
        d_te_Female.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(d_te_Female, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 100, 20));

        d_te_male.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_te_male.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_te_male.setText("0");
        d_te_male.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(d_te_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 100, 20));

        jLabel204.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel204.setText("Staff Count");
        jPanel6.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 120, 20));

        jLabel205.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel205.setText("Male");
        jPanel6.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 90, 20));

        d_sf_male.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_sf_male.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_sf_male.setText("0");
        d_sf_male.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(d_sf_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 100, 20));

        jLabel206.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel206.setText("Female");
        jPanel6.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 90, 20));

        d_sf_Female.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_sf_Female.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_sf_Female.setText("0");
        d_sf_Female.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(d_sf_Female, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 100, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 230, 200));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Student Count");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 30));

        jLabel65.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel65.setText("Class - 10");
        jPanel7.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 20));

        d_class_10.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_class_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_class_10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(d_class_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 90, 20));

        jLabel69.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel69.setText("Class - 7");
        jPanel7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 20));

        jLabel72.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel72.setText("Class - 8");
        jPanel7.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, 20));

        jLabel84.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel84.setText("Class - 9");
        jPanel7.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, 20));

        jLabel85.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel85.setText("Class - 6");
        jPanel7.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 20));

        d_class_6.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_class_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_class_6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(d_class_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 90, 20));

        d_class_7.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_class_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_class_7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(d_class_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 90, 20));

        d_class_8.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_class_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_class_8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(d_class_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 90, 20));

        d_class_9.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_class_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d_class_9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(d_class_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 90, 20));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 230, 200));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel95.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Collection");
        jLabel95.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 30));

        jLabel97.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel97.setText("Today Total Collection");
        jPanel9.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel98.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel98.setText("Today Fee ");
        jPanel9.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 20));

        jLabel127.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel127.setText("Monthly Total collection");
        jPanel9.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 240, 20));

        d_monthlyCollection.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_monthlyCollection.setText("00.00");
        d_monthlyCollection.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(d_monthlyCollection, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, 30));

        d_todaysFee.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_todaysFee.setText("00.00");
        d_todaysFee.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(d_todaysFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, 30));

        d_todayTotalCollection.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_todayTotalCollection.setText("00.00");
        d_todayTotalCollection.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(d_todayTotalCollection, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 220, 30));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 250, 200));

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel129.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("Salary Pay");
        jLabel129.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 30));

        jLabel130.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel130.setText("Monthly Pay");
        jPanel10.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 210, 30));

        d_total_pay.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_total_pay.setText("00.00");
        d_total_pay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(d_total_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 200, 40));

        jLabel218.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel218.setText("Today's Pay");
        jPanel10.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, -1));

        d_te_pay.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        d_te_pay.setText("00.00");
        d_te_pay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(d_te_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 200, 40));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 230, 210));

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_dashboard.jpg"))); // NOI18N
        jPanel1.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 990, 590));

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

        St_btn_addmition.setBackground(new java.awt.Color(204, 204, 255));
        St_btn_addmition.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        St_btn_addmition.setText("ADMITION");
        St_btn_addmition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                St_btn_addmitionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                St_btn_addmitionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                St_btn_addmitionMouseExited(evt);
            }
        });
        st_1.add(St_btn_addmition, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jPanel13.add(st_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 170, 90));

        st_2.setBackground(new java.awt.Color(204, 255, 204));
        st_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        st_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        St_btn_Deatils.setBackground(new java.awt.Color(204, 204, 255));
        St_btn_Deatils.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        St_btn_Deatils.setText("Information");
        St_btn_Deatils.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                St_btn_DeatilsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                St_btn_DeatilsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                St_btn_DeatilsMouseExited(evt);
            }
        });
        st_2.add(St_btn_Deatils, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jPanel13.add(st_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 170, 90));

        st_3.setBackground(new java.awt.Color(204, 255, 204));
        st_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        st_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_btn_result_in.setBackground(new java.awt.Color(204, 204, 255));
        st_btn_result_in.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        st_btn_result_in.setText("RESULT INPUT");
        st_btn_result_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                st_btn_result_inMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                st_btn_result_inMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                st_btn_result_inMouseExited(evt);
            }
        });
        st_3.add(st_btn_result_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jPanel13.add(st_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 170, 90));

        St_4.setBackground(new java.awt.Color(204, 255, 204));
        St_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        St_4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        st_btn_result_out.setBackground(new java.awt.Color(204, 204, 255));
        st_btn_result_out.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        st_btn_result_out.setText("REAULT SHOW");
        st_btn_result_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                st_btn_result_outMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                st_btn_result_outMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                st_btn_result_outMouseExited(evt);
            }
        });
        St_4.add(st_btn_result_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jPanel13.add(St_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 170, 90));

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
        s_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                s_TableMouseClicked(evt);
            }
        });
        Table_Student.setViewportView(s_Table);

        st_add.add(Table_Student, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 407, 930, 150));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_Image_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel14.add(s_Image_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 140));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/delete.png"))); // NOI18N
        jButton1.setText("Delete");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel11.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 160, 30));

        btn_Image_set_.setBackground(new java.awt.Color(204, 255, 204));
        btn_Image_set_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_Image_set_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/internet.png"))); // NOI18N
        btn_Image_set_.setText("Browse");
        btn_Image_set_.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Image_set_.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_Image_set_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Image_set_MouseClicked(evt);
            }
        });
        jPanel11.add(btn_Image_set_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, 30));

        btn_Save_.setBackground(new java.awt.Color(204, 255, 204));
        btn_Save_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_Save_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/saves.png"))); // NOI18N
        btn_Save_.setText("Save");
        btn_Save_.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Save_.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_Save_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Save_MouseClicked(evt);
            }
        });
        jPanel11.add(btn_Save_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 30));

        btn_s_Update_.setBackground(new java.awt.Color(204, 255, 204));
        btn_s_Update_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_s_Update_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/changes (1).png"))); // NOI18N
        btn_s_Update_.setText("Update");
        btn_s_Update_.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_s_Update_.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_s_Update_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_s_Update_MouseClicked(evt);
            }
        });
        jPanel11.add(btn_s_Update_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 160, 30));

        btn_AdmiosionReset_.setBackground(new java.awt.Color(204, 255, 204));
        btn_AdmiosionReset_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_AdmiosionReset_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/undo-arrow.png"))); // NOI18N
        btn_AdmiosionReset_.setText("Reset");
        btn_AdmiosionReset_.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_AdmiosionReset_.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
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
        jPanel12.add(s_admisiondate, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 150, -1));

        s_catagory.setText("S");
        jPanel12.add(s_catagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 20, -1));

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

        sd_studentID.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jPanel16.add(sd_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 120, 30));

        jLabel51.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Batch id");
        jPanel16.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 80, 30));

        sd_batchId.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jPanel16.add(sd_batchId, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        btn_sd_View_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_View_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/view.png"))); // NOI18N
        btn_sd_View_.setText("View");
        btn_sd_View_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_View_MouseClicked(evt);
            }
        });
        jPanel16.add(btn_sd_View_, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 110, 30));

        sd_session.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        sd_session.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sd_sessionMouseClicked(evt);
            }
        });
        jPanel16.add(sd_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 90, 30));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 60));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Student_details_panal.setBackground(new java.awt.Color(255, 255, 255));
        Student_details_panal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 12))); // NOI18N
        Student_details_panal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sd_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel19.add(sd_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 170));

        Student_details_panal.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 170));

        jLabel32.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel32.setText("Birth Cer.No");
        Student_details_panal.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 110, 30));

        jLabel33.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel33.setText("Full Name");
        Student_details_panal.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 140, 30));

        jLabel34.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel34.setText("Father's Name");
        Student_details_panal.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 130, 30));

        jLabel35.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel35.setText("Mother's Name");
        Student_details_panal.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 140, 30));

        jLabel36.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel36.setText("Phone Number");
        Student_details_panal.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 140, 30));

        jLabel38.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel38.setText("Date Of Birth");
        Student_details_panal.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 140, 30));

        jLabel39.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel39.setText("Age");
        Student_details_panal.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 140, 30));

        jLabel40.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel40.setText("Religion");
        Student_details_panal.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 80, 30));

        jLabel41.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel41.setText("Nationality");
        Student_details_panal.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, 30));

        jLabel45.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel45.setText("Phone Number");
        Student_details_panal.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 140, 30));

        jLabel46.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel46.setText("Email");
        Student_details_panal.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 50, 30));

        jLabel47.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel47.setText("Present Addess");
        Student_details_panal.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 140, 30));

        jLabel48.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel48.setText("Parmanent Address");
        Student_details_panal.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, 30));

        jLabel49.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel49.setText("Blood Group");
        Student_details_panal.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 110, 30));

        sd_birth_cer.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_birth_cer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_birth_cer, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 200, 30));

        sd_fullName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_fullName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_fullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 190, 30));

        sd_fatherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_fatherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_fatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 190, 30));

        sd_Father_phone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Father_phone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_Father_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 190, 30));

        sd_motherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_motherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_motherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 190, 30));

        sd_motherPhone.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_motherPhone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_motherPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 190, 30));

        sd_Dob.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Dob.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_Dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 190, 30));

        sd_Age.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_Age.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_Age, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 190, 30));

        sd_religion.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_religion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_religion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 200, 30));

        sd_nationality.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_nationality.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_nationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 200, 30));

        sd_blood.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_blood.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_blood, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 200, 30));

        sd_email.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal.add(sd_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 200, 30));

        sd_parmanentA.setColumns(20);
        sd_parmanentA.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_parmanentA.setRows(5);
        sd_parmanentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(sd_parmanentA);

        Student_details_panal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 570, 30));

        sd_presentA.setColumns(20);
        sd_presentA.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        sd_presentA.setRows(5);
        sd_presentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane4.setViewportView(sd_presentA);

        Student_details_panal.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 570, 30));

        jPanel17.add(Student_details_panal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 940, 390));

        sd_class_view.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        sd_class_view.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sd_class_view.setText("00");
        jPanel17.add(sd_class_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 100, 40));

        sd_st_name.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        sd_st_name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sd_st_name.setText("Name Here");
        jPanel17.add(sd_st_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 40));

        sd_gender_combo.setFont(new java.awt.Font("Lucida Fax", 1, 22)); // NOI18N
        sd_gender_combo.setForeground(new java.awt.Color(255, 51, 51));
        sd_gender_combo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sd_gender_combo.setText("Gender");
        jPanel17.add(sd_gender_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, 40));

        btn_sd_delete_.setBackground(new java.awt.Color(204, 255, 204));
        btn_sd_delete_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_delete_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/delete.png"))); // NOI18N
        btn_sd_delete_.setText("Delete");
        btn_sd_delete_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_delete_MouseClicked(evt);
            }
        });
        jPanel17.add(btn_sd_delete_, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 220, 40));

        btn_sd_View1_.setBackground(new java.awt.Color(204, 255, 204));
        btn_sd_View1_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_View1_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/view.png"))); // NOI18N
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

        btn_sd_Reset.setBackground(new java.awt.Color(204, 255, 204));
        btn_sd_Reset.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/undo-arrow.png"))); // NOI18N
        btn_sd_Reset.setText("Reset");
        btn_sd_Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_ResetMouseClicked(evt);
            }
        });
        jPanel17.add(btn_sd_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 230, 40));

        jLabel115.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel115.setText("Class-");
        jPanel17.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, 40));

        btn_sd_Print_.setBackground(new java.awt.Color(204, 255, 204));
        btn_sd_Print_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_sd_Print_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/saves.png"))); // NOI18N
        btn_sd_Print_.setText("Print");
        btn_sd_Print_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sd_Print_MouseClicked(evt);
            }
        });
        btn_sd_Print_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sd_Print_ActionPerformed(evt);
            }
        });
        jPanel17.add(btn_sd_Print_, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 230, 40));

        jPanel15.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 970, 500));

        St_deatils.addTab("tab1", jPanel15);

        StudentTab.addTab("tab2", St_deatils);

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("Session");
        jPanel25.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 90, 40));

        r_session.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_session.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        r_session.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        r_session.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                r_sessionKeyReleased(evt);
            }
        });
        jPanel25.add(r_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 150, 40));

        jLabel53.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel53.setText("Student Roll");
        jPanel25.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 120, 40));

        r_St_Roll.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_St_Roll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        r_St_Roll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel25.add(r_St_Roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 120, 40));

        jLabel54.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("Batch Id");
        jPanel25.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 90, 40));

        r_batch_id.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_batch_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        r_batch_id.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel25.add(r_batch_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 120, 40));

        jLabel55.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Class");
        jPanel25.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 50, 40));

        r_class.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        r_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select class", "6", "7", "8", "9", "10" }));
        r_class.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel25.add(r_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 130, 40));

        jPanel21.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 970, 40));

        jPanel26.setBackground(new java.awt.Color(204, 204, 204));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 30, 470));

        tableResultInput.setModel(new javax.swing.table.DefaultTableModel(
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
        tableResultInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResultInputMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableResultInput);

        jPanel21.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 880, 180));

        r_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel21.add(r_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 160, 30));

        r_Serial.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        r_Serial.setText("0000");
        jPanel21.add(r_Serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 70, 20));

        jLabel118.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel118.setText("S.L");
        jPanel21.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 40, 20));

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action button", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel54.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_R_save_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_R_save_.setText("Save");
        btn_R_save_.setBorder(new javax.swing.border.MatteBorder(null));
        btn_R_save_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_R_save_MouseClicked(evt);
            }
        });
        jPanel54.add(btn_R_save_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 30));

        btn_R_Reset.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_R_Reset.setText("Reset");
        btn_R_Reset.setBorder(new javax.swing.border.MatteBorder(null));
        btn_R_Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_R_ResetMouseClicked(evt);
            }
        });
        jPanel54.add(btn_R_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        btn_R_update.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_R_update.setText("Update");
        btn_R_update.setBorder(new javax.swing.border.MatteBorder(null));
        btn_R_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_R_updateMouseClicked(evt);
            }
        });
        btn_R_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_R_updateActionPerformed(evt);
            }
        });
        jPanel54.add(btn_R_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, 30));

        btn_R_Delete.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_R_Delete.setText("Delete");
        btn_R_Delete.setBorder(new javax.swing.border.MatteBorder(null));
        btn_R_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_R_DeleteMouseClicked(evt);
            }
        });
        jPanel54.add(btn_R_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, 30));

        btn_R_RollBack_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_R_RollBack_.setText("Roll Back");
        btn_R_RollBack_.setBorder(new javax.swing.border.MatteBorder(null));
        btn_R_RollBack_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_R_RollBack_MouseClicked(evt);
            }
        });
        jPanel54.add(btn_R_RollBack_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 140, 30));

        jPanel21.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 160, 220));

        jLabel117.setFont(new java.awt.Font("Engravers MT", 1, 18)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 102, 102));
        jLabel117.setText("Student  Result   Table");
        jPanel21.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 370, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rasult Input", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        r_bangla.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_bangla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_bangla.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_bangla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_banglaActionPerformed(evt);
            }
        });
        jPanel8.add(r_bangla, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 160, 30));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel5.setText("Bangla");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

        jLabel116.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel116.setText("Exam Catagory");
        jPanel8.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 150, 30));

        r_examSelect.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        r_examSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Exam", "Half_Yearly", "Final" }));
        jPanel8.add(r_examSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 160, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel10.setText("English");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 30));

        r_english.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_english.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_english.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_english.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_englishActionPerformed(evt);
            }
        });
        jPanel8.add(r_english, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 160, 30));

        r_math.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_math.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_math.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_math.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_mathActionPerformed(evt);
            }
        });
        jPanel8.add(r_math, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 160, 30));

        r_scince.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_scince.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_scince.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_scince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_scinceActionPerformed(evt);
            }
        });
        jPanel8.add(r_scince, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 160, 30));

        r_social.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_social.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_social.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_social.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_socialActionPerformed(evt);
            }
        });
        jPanel8.add(r_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 160, 30));

        r_islam.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_islam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_islam.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_islam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                r_islamFocusLost(evt);
            }
        });
        r_islam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_islamActionPerformed(evt);
            }
        });
        jPanel8.add(r_islam, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 160, 30));

        jLabel12.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel12.setText("Genarel Math");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 30));

        jLabel30.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel30.setText("Genarel Scince");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 30));

        jLabel31.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel31.setText("Social Scince");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 30));

        jLabel37.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel37.setText("Islam");
        jPanel8.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 140, 30));

        jLabel50.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel50.setText("Total Number");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 140, 30));

        r_total.setEditable(false);
        r_total.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_total.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_totalActionPerformed(evt);
            }
        });
        jPanel8.add(r_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 160, 30));

        r_avarage.setEditable(false);
        r_avarage.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_avarage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_avarage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_avarage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_avarageActionPerformed(evt);
            }
        });
        jPanel8.add(r_avarage, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 160, 30));

        jLabel56.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel56.setText("Avarage number");
        jPanel8.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 160, 30));

        r_gpa.setEditable(false);
        r_gpa.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_gpa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_gpa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_gpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_gpaActionPerformed(evt);
            }
        });
        jPanel8.add(r_gpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 160, 30));

        jLabel57.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel57.setText("G.P.A");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 160, 30));

        r_grade.setEditable(false);
        r_grade.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_grade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_grade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_gradeActionPerformed(evt);
            }
        });
        jPanel8.add(r_grade, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 160, 30));

        jLabel68.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel68.setText("Grade");
        jPanel8.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 160, 30));

        r_passFail.setEditable(false);
        r_passFail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        r_passFail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        r_passFail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        r_passFail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_passFailActionPerformed(evt);
            }
        });
        jPanel8.add(r_passFail, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 160, 30));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel4.setText("Result");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 160, 30));

        jPanel21.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 710, 250));

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

        rs_input_session.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        rs_input_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rs_input_sessionActionPerformed(evt);
            }
        });
        jPanel24.add(rs_input_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 90, 30));

        jLabel59.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel59.setText("Student Roll");
        jPanel24.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        rs_input_roll.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        rs_input_roll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rs_input_rollActionPerformed(evt);
            }
        });
        jPanel24.add(rs_input_roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 90, 30));

        jLabel60.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel60.setText("Batch id");
        jPanel24.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 80, 30));

        jLabel61.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel61.setText("Class");
        jPanel24.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 50, 30));

        rs_input_batch.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        rs_input_batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rs_input_batchActionPerformed(evt);
            }
        });
        jPanel24.add(rs_input_batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 90, 30));

        btn_rs_View_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_rs_View_.setText("View");
        btn_rs_View_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rs_View_MouseClicked(evt);
            }
        });
        jPanel24.add(btn_rs_View_, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 110, 30));

        rs_input_class.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_input_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "6", "7", "8", "9", "10" }));
        jPanel24.add(rs_input_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 90, 30));

        jPanel23.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 970, 50));

        Result_sheet_panal.setBackground(new java.awt.Color(255, 255, 255));
        Result_sheet_panal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reult Sheet", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        Result_sheet_panal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rsg_gpa.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_gpa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_gpa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_gpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 100, 20));

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

        rs_bangla.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_bangla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_bangla.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rs_bangla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                rs_banglaPropertyChange(evt);
            }
        });
        jPanel35.add(rs_bangla, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 100, 20));

        rs_english.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_english.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_english.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_english, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, 20));

        rs_math.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_math.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_math.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_math, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 100, 20));

        rs_scince.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_scince.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_scince.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_scince, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 100, 20));

        rs_social.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_social.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_social.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 100, 20));

        rs_islam.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_islam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_islam.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_islam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 100, 20));

        rs_total.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_total.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 100, 20));

        rs_gpa.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_gpa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rs_gpa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rs_gpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 100, 20));

        rsg_bangla.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_bangla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_bangla.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_bangla, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 100, 20));

        rsg_english.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_english.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_english.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_english, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, 20));

        rsg_math.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_math.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_math.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_math, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 100, 20));

        rsg_scince.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_scince.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_scince.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_scince, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 100, 20));

        rsg_social.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_social.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_social.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 100, 20));

        rsg_islam.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_islam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_islam.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_islam, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 100, 20));

        rsg_total.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rsg_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rsg_total.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel35.add(rsg_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 100, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/Grede_3.png"))); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel35.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, 150));

        jLabel123.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setText("Author Signature");
        jPanel35.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 240, 20));

        jLabel124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_rsz_sign-removebg-preview.png"))); // NOI18N
        jPanel35.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 100, 80));

        Result_sheet_panal.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 790, 290));

        jLabel62.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel62.setText("Lalbag Model School and Collage ");
        Result_sheet_panal.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 340, 20));

        jLabel63.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("lalbag, Dhaka-1212");
        Result_sheet_panal.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 310, -1));

        rs_name.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        rs_name.setText("xxx");
        Result_sheet_panal.add(rs_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 160, 30));

        rs_catagory.setBackground(new java.awt.Color(255, 255, 255));
        rs_catagory.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_catagory.setText("xxx");
        Result_sheet_panal.add(rs_catagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 100, 30));

        jLabel66.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel66.setText("Roll No");
        Result_sheet_panal.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 20));

        jLabel67.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel67.setText("Result");
        Result_sheet_panal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 120, 30));

        rs_session.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_session.setText("xxx");
        Result_sheet_panal.add(rs_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 80, 30));

        rs_roll.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_roll.setText("xxx");
        Result_sheet_panal.add(rs_roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 70, 20));

        jLabel70.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel70.setText("Student Name");
        Result_sheet_panal.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, 120, 30));

        jLabel71.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel71.setText("Exam Catagory");
        Result_sheet_panal.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 120, 30));

        jLabel119.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel119.setText("Class");
        Result_sheet_panal.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 120, 20));

        jLabel120.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel120.setText("Batch Id");
        Result_sheet_panal.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 120, 30));

        rs_class.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_class.setText("xxx");
        Result_sheet_panal.add(rs_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 80, 20));

        rs_batch.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        rs_batch.setText("xxx");
        Result_sheet_panal.add(rs_batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 80, 30));

        jLabel94.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel94.setText("Session");
        Result_sheet_panal.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 120, 30));

        rs_PassFail.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        rs_PassFail.setForeground(new java.awt.Color(255, 51, 51));
        rs_PassFail.setText("xxx");
        Result_sheet_panal.add(rs_PassFail, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 80, 30));

        jPanel23.add(Result_sheet_panal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 810, 420));

        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 40, 310));

        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_rs_Reset_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_rs_Reset_.setText("Reset");
        btn_rs_Reset_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rs_Reset_MouseClicked(evt);
            }
        });
        jPanel33.add(btn_rs_Reset_, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 110, 30));

        btn_Result_sheet_panal_Print_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_Result_sheet_panal_Print_.setText("Print");
        btn_Result_sheet_panal_Print_.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btn_Result_sheet_panal_Print_MouseDragged(evt);
            }
        });
        btn_Result_sheet_panal_Print_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Result_sheet_panal_Print_MouseClicked(evt);
            }
        });
        jPanel33.add(btn_Result_sheet_panal_Print_, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 110, 30));

        jPanel23.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 970, 50));

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, 310));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        St_Result_Print.addTab("tab1", jPanel22);

        StudentTab.addTab("tab4", St_Result_Print);

        mainTab.addTab("tab2", StudentTab);

        te_All.setBackground(new java.awt.Color(204, 255, 204));
        te_All.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        te_btn_report.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        te_btn_report.setText("Teacher's and Staff Information View");
        te_btn_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_reportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                te_btn_reportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                te_btn_reportMouseExited(evt);
            }
        });
        jPanel2.add(te_btn_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 860, 50));

        jLabel131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/teacherHome (2).jpg"))); // NOI18N
        jPanel2.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 850, 180));

        te_All.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 850, 230));

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/treacherHome1.jpg"))); // NOI18N
        jPanel3.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 180));

        te_All.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 390, 180));

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_rutine.png"))); // NOI18N
        jPanel4.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 180));

        te_All.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 400, 180));

        te_btn_rutines.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        te_btn_rutines.setText("Class Rutine");
        te_btn_rutines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_rutinesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                te_btn_rutinesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                te_btn_rutinesMouseExited(evt);
            }
        });
        te_btn_rutines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                te_btn_rutinesActionPerformed(evt);
            }
        });
        te_All.add(te_btn_rutines, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 400, 50));

        te_btn_add.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        te_btn_add.setText("Teacher and Staff Add information");
        te_btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                te_btn_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                te_btn_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                te_btn_addMouseExited(evt);
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

        st_add1.setBackground(new java.awt.Color(255, 255, 255));
        st_add1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        t_table.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        t_table.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_table.setModel(new javax.swing.table.DefaultTableModel(
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
        t_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_tableMouseClicked(evt);
            }
        });
        Table_Student1.setViewportView(t_table);

        st_add1.add(Table_Student1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 397, 930, 160));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        te_Image_set_lable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel31.add(te_Image_set_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        jPanel18.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 140));

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel18.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 160, 30));

        btn_te_Image_set_.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_te_Image_set_.setText("Browse");
        btn_te_Image_set_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_te_Image_set_MouseClicked(evt);
            }
        });
        jPanel18.add(btn_te_Image_set_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, 30));

        btn_Save_1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_Save_1.setText("Save");
        btn_Save_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Save_1MouseClicked(evt);
            }
        });
        jPanel18.add(btn_Save_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 30));

        jButton5.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jButton5.setText("Update");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel18.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 160, 30));

        btn_AdmiosionReset_1.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        btn_AdmiosionReset_1.setText("Reset");
        btn_AdmiosionReset_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AdmiosionReset_1MouseClicked(evt);
            }
        });
        jPanel18.add(btn_AdmiosionReset_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 160, 30));

        st_add1.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 180, 370));

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TEACHER & STAFF ADD FORM", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel52.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setText("Catagory");
        jPanel52.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, -1));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel132.setText("Relision");
        jPanel52.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 120, 30));

        t_Id.setEditable(false);
        t_Id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 150, -1));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel133.setText("ID");
        jPanel52.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, -1));

        t_email1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_email1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_email1ActionPerformed(evt);
            }
        });
        jPanel52.add(t_email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 300, 30));

        t_designationcombo1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        t_designationcombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select deg--", "Principal", "Vice Principal", "Assistan Teacher", "Custodian", "Sports coach", "School bus driver", "Academic coordinator", "Academic adviser", "Registrar", "Guidance counselor", "Superintendent", "Athletic director", " " }));
        jPanel52.add(t_designationcombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 140, -1));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel134.setText("First Name");
        jPanel52.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, 20));

        t_teacher_first1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_teacher_first1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 150, -1));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel135.setText("Parmanent Address");
        jPanel52.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 30));

        t_teacher_last1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_teacher_last1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_teacher_last1ActionPerformed(evt);
            }
        });
        jPanel52.add(t_teacher_last1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 140, -1));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel136.setText("Fathers Name");
        jPanel52.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, 20));

        t_father_N1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_father_N1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 150, -1));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel137.setText("Contact Number");
        jPanel52.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 120, 20));

        t_national_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_national_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_national_idActionPerformed(evt);
            }
        });
        jPanel52.add(t_national_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 150, -1));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel138.setText("Mothers Name");
        jPanel52.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 20));

        t_National1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_National1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 150, -1));
        jPanel52.add(t_dateOfBirth1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 150, -1));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel139.setText("Qualification");
        jPanel52.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 130, 20));

        t_BloodGroup1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        t_BloodGroup1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Select Blood Group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        jPanel52.add(t_BloodGroup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 150, -1));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel140.setText("Age");
        jPanel52.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 90, 20));

        t_parmanent1.setColumns(20);
        t_parmanent1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_parmanent1.setRows(5);
        jScrollPane10.setViewportView(t_parmanent1);

        jPanel52.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 530, 30));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel141.setText("Present Address");
        jPanel52.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 140, 30));

        t_presentAddress1.setColumns(20);
        t_presentAddress1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_presentAddress1.setRows(5);
        jScrollPane11.setViewportView(t_presentAddress1);

        jPanel52.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 530, 30));

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel142.setText("Date of Birth");
        jPanel52.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 20));

        t_Mother_N1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_Mother_N1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, -1));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel143.setText("E-mail Address");
        jPanel52.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 120, 30));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setText("Blood Group");
        jPanel52.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 150, 20));

        t_contact_number.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_contact_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_contact_numberActionPerformed(evt);
            }
        });
        jPanel52.add(t_contact_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 140, -1));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel145.setText("National ID Nummber");
        jPanel52.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 150, 20));

        t_gender_combo1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        t_gender_combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Gender --", "Male", "Female" }));
        jPanel52.add(t_gender_combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 140, 30));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel146.setText("Joining Date");
        jPanel52.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 110, 20));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setText("Last Name");
        jPanel52.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 110, 20));

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel148.setText("Gender");
        jPanel52.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 130, 30));

        t_religion1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_religion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_religion1ActionPerformed(evt);
            }
        });
        jPanel52.add(t_religion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 140, 30));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel149.setText("Nationality");
        jPanel52.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 20));

        t_age1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel52.add(t_age1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 150, -1));

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel150.setText("Designation");
        jPanel52.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 110, -1));

        t_qualification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "M.B.A", "B.B.A", "H.S.C", "S.S.C" }));
        jPanel52.add(t_qualification, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 140, -1));
        jPanel52.add(t_joiningDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 140, -1));

        sf_ctagory.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        sf_ctagory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sf_ctagory.setText("SF");
        jPanel52.add(sf_ctagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 30, 20));

        t_sf_catagory.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        t_sf_catagory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One", "Teacher", "Staff" }));
        jPanel52.add(t_sf_catagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, -1));

        t_ctagory.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        t_ctagory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t_ctagory.setText("T");
        jPanel52.add(t_ctagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 30, 20));

        st_add1.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 730, 350));

        jLabel110.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 51, 51));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("Teacher and Staff Information Table");
        st_add1.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 367, 380, 30));

        t_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_searchActionPerformed(evt);
            }
        });
        t_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_searchKeyReleased(evt);
            }
        });
        st_add1.add(t_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 70, -1));

        btn_t_ta_search_.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_t_ta_search_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/search.png"))); // NOI18N
        btn_t_ta_search_.setText("Search");
        btn_t_ta_search_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_t_ta_search_MouseClicked(evt);
            }
        });
        st_add1.add(btn_t_ta_search_, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, 20));

        jLabel151.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel151.setText("ID");
        st_add1.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 370, 30, 20));

        Te_add.addTab("tab1", st_add1);

        TeacherTab.addTab("tab1", Te_add);
        TeacherTab.addTab("tab2", Te_ClassRutine);

        jPanel55.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel56.setBackground(new java.awt.Color(204, 204, 204));
        jPanel56.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel152.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel152.setText("Teacher or Staff Id");
        jPanel56.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, 40));

        btn_td_View_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_td_View_.setText("View");
        btn_td_View_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_td_View_MouseClicked(evt);
            }
        });
        jPanel56.add(btn_td_View_, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 40));

        td_id.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        td_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                td_idMouseClicked(evt);
            }
        });
        jPanel56.add(td_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, 40));

        btn_td_Update.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_td_Update.setText("Update");
        btn_td_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_td_UpdateMouseClicked(evt);
            }
        });
        jPanel56.add(btn_td_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 140, 40));

        btn_td_delete_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_td_delete_.setText("Delete");
        btn_td_delete_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_td_delete_MouseClicked(evt);
            }
        });
        jPanel56.add(btn_td_delete_, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 9, 120, 40));

        btn_td_Print_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_td_Print_.setText("Print");
        btn_td_Print_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_td_Print_MouseClicked(evt);
            }
        });
        btn_td_Print_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_td_Print_ActionPerformed(evt);
            }
        });
        jPanel56.add(btn_td_Print_, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 130, 40));

        btn_td_Reset.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_td_Reset.setText("Reset");
        btn_td_Reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_td_ResetMouseClicked(evt);
            }
        });
        jPanel56.add(btn_td_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 140, 40));

        jPanel55.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 60));

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));
        jPanel57.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Student_details_panal1.setBackground(new java.awt.Color(255, 255, 255));
        Student_details_panal1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teacher's And Staff Information", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 12))); // NOI18N
        Student_details_panal1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel58.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel155.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/st_1.png"))); // NOI18N
        jPanel58.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 170));

        Student_details_panal1.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 170));

        jLabel156.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel156.setText("National Id No.");
        Student_details_panal1.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 130, 30));

        jLabel157.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel157.setText("Full Name");
        Student_details_panal1.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 140, 30));

        jLabel158.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel158.setText("Father's Name");
        Student_details_panal1.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 130, 30));

        jLabel159.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel159.setText("Mother's Name");
        Student_details_panal1.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 140, 30));

        jLabel160.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel160.setText("Contact Number");
        Student_details_panal1.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 140, 30));

        jLabel161.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel161.setText("Date Of Birth");
        Student_details_panal1.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 140, 30));

        jLabel162.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel162.setText("Age");
        Student_details_panal1.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 110, 30));

        jLabel163.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel163.setText("Religion");
        Student_details_panal1.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 80, 30));

        jLabel164.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel164.setText("Nationality");
        Student_details_panal1.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, 30));

        Degination.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        Degination.setText("Joining Date");
        Student_details_panal1.add(Degination, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 140, 30));

        jLabel166.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel166.setText("Email");
        Student_details_panal1.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 50, 30));

        jLabel167.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel167.setText("Present Addess");
        Student_details_panal1.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 140, 30));

        jLabel168.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel168.setText("Parmanent Address");
        Student_details_panal1.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, 30));

        jLabel169.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel169.setText("Blood Group");
        Student_details_panal1.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 110, 30));

        td_national_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_national_id.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_national_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 200, 30));

        td_fullName1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_fullName1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_fullName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 190, 30));

        td_fatherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_fatherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_fatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 190, 30));

        td_JoiningDate.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_JoiningDate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_JoiningDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 190, 30));

        td_motherName.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_motherName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_motherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 190, 30));

        td_ContactNumber.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_ContactNumber.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_ContactNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 190, 30));

        td_Dob.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_Dob.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_Dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 190, 30));

        td_Age.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_Age.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_Age, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 200, 20));

        td_religion.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_religion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_religion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 200, 30));

        td_nationality.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_nationality.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_nationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 200, 30));

        td_blood.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_blood.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_blood, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 200, 30));

        td_email.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Student_details_panal1.add(td_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 200, 30));

        td_parmanentA.setColumns(20);
        td_parmanentA.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_parmanentA.setRows(5);
        td_parmanentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane12.setViewportView(td_parmanentA);

        Student_details_panal1.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 550, 30));

        td_presentA.setColumns(20);
        td_presentA.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        td_presentA.setRows(5);
        td_presentA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane13.setViewportView(td_presentA);

        Student_details_panal1.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 550, 30));

        TeacherDeatilsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(TeacherDeatilsTable);

        Student_details_panal1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 880, 110));

        jPanel57.add(Student_details_panal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 940, 450));

        td_Designation_view.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        td_Designation_view.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        td_Designation_view.setText("Designation");
        jPanel57.add(td_Designation_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 140, 50));

        td_te_name.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        td_te_name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        td_te_name.setText("Name Here");
        jPanel57.add(td_te_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 50));

        td_gender_combo.setFont(new java.awt.Font("Lucida Fax", 1, 22)); // NOI18N
        td_gender_combo.setForeground(new java.awt.Color(255, 51, 51));
        td_gender_combo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        td_gender_combo.setText("Gender");
        jPanel57.add(td_gender_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, 50));

        jPanel55.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 970, 500));

        Te_Full_Report.addTab("tab1", jPanel55);

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

        btn_admit.setBackground(new java.awt.Color(204, 255, 204));
        btn_admit.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_admit.setText("Admit Card Print");
        btn_admit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_admitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_admitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_admitMouseExited(evt);
            }
        });
        jPanel28.add(btn_admit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 210, 40));

        btn_exam_in.setBackground(new java.awt.Color(204, 255, 204));
        btn_exam_in.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_exam_in.setText("Exam Date input");
        btn_exam_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exam_inMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exam_inMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exam_inMouseExited(evt);
            }
        });
        jPanel28.add(btn_exam_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 230, 40));

        btn_exam_out.setBackground(new java.awt.Color(204, 255, 204));
        btn_exam_out.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_exam_out.setText("Exam Date View");
        btn_exam_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exam_outMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exam_outMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exam_outMouseExited(evt);
            }
        });
        jPanel28.add(btn_exam_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 230, 40));

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/exam home_pic.jpg"))); // NOI18N
        jPanel28.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 800, 370));

        jPanel27.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 990, 570));

        ex_Home.addTab("tab1", jPanel27);

        Exam_Seduel.addTab("tab3", ex_Home);

        jPanel71.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel72.setBackground(new java.awt.Color(255, 255, 255));
        jPanel72.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel79.setBackground(new java.awt.Color(204, 255, 204));
        jPanel79.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel79.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel72.add(jPanel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 940, 50));

        jPanel80.setBackground(new java.awt.Color(204, 255, 255));
        jPanel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel80.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel196.setBackground(new java.awt.Color(204, 255, 204));
        jLabel196.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel196.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel196.setText("Date");
        jLabel196.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel80.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 30));
        jPanel80.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 290, 30));
        jPanel80.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 290, 30));
        jPanel80.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 290, 30));
        jPanel80.add(jDateChooser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 290, 30));
        jPanel80.add(jDateChooser6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 290, 30));
        jPanel80.add(jDateChooser7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 290, 30));

        jPanel72.add(jPanel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 300, 310));

        jPanel81.setBackground(new java.awt.Color(204, 255, 204));
        jPanel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel202.setBackground(new java.awt.Color(204, 255, 204));
        jLabel202.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel202.setText("Class : 6");
        jLabel202.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 30));

        jComboBox4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 300, 30));

        jComboBox5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 300, 30));

        jComboBox6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 300, 30));

        jComboBox7.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 300, 30));

        jComboBox8.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 300, 30));

        jComboBox9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ban 1st Paper", "Ban 2nd Paper", "Eng 1st Paper", "Eng 2nd Paper", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "General Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jComboBox9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel81.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 300, 30));

        jPanel72.add(jPanel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 320, 310));

        jPanel71.add(jPanel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        ex_date_inputTab.addTab("tab1", jPanel71);

        Exam_Seduel.addTab("tab1", ex_date_inputTab);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ex_viewTab.addTab("tab1", jComboBox1);

        Exam_Seduel.addTab("tab2", ex_viewTab);

        jPanel59.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel61.setBackground(new java.awt.Color(204, 255, 204));
        jPanel61.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ads_Reset_.setBackground(new java.awt.Color(255, 255, 204));
        btn_ads_Reset_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_ads_Reset_.setText("Reset");
        btn_ads_Reset_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ads_Reset_MouseClicked(evt);
            }
        });
        jPanel61.add(btn_ads_Reset_, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 120, 30));

        jButton7.setBackground(new java.awt.Color(255, 255, 204));
        jButton7.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton7.setText("Print");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel61.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 120, 30));

        jPanel60.add(jPanel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 970, 50));

        admit_cardPanal.setBackground(new java.awt.Color(255, 255, 153));
        admit_cardPanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel64.setBackground(new java.awt.Color(255, 255, 204));
        jPanel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel64.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel153.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("Admit Card");
        jLabel153.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 250, 30));

        jLabel154.setFont(new java.awt.Font("Lucida Fax", 1, 20)); // NOI18N
        jLabel154.setText("Lalbag Model School And Collage");
        jPanel64.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 360, 30));

        jLabel165.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("www.lalbagSchoolandCollage.com");
        jPanel64.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 360, 30));

        jLabel170.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel170.setText("lalbag, Dhaka-1213");
        jPanel64.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 360, 30));

        jLabel172.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel172.setText("Student Name");
        jPanel64.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 170, 30));

        jLabel173.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel173.setText("Father's Name");
        jPanel64.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, 30));

        jLabel174.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel174.setText("Class");
        jPanel64.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 170, 30));

        jLabel175.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel175.setText("Roll no.");
        jPanel64.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 110, 30));

        jLabel176.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel176.setText("Batch id");
        jPanel64.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 100, 30));

        jLabel177.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel177.setText("Session");
        jPanel64.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 110, 30));

        jLabel178.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel178.setText("Account's officer Signature");
        jPanel64.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 210, -1));

        jLabel179.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel179.setText("Principal's Signature");
        jPanel64.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 230, -1));

        jLabel180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_rsz_sign-removebg-preview.png"))); // NOI18N
        jPanel64.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 110, 70));

        ad_session.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_session.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 170, 30));

        ad_name.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_name.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 170, 30));

        ad_father.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_father.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_father, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 170, 30));

        ad_class.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_class.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 170, 30));

        ad_roll.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_roll.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 170, 30));

        ad_batch.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ad_batch.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel64.add(ad_batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 170, 30));

        admit_cardPanal.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 730, 340));

        jPanel60.add(admit_cardPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 790, 390));

        jPanel63.setBackground(new java.awt.Color(204, 255, 204));
        jPanel63.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ads_view_.setBackground(new java.awt.Color(255, 255, 204));
        btn_ads_view_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_ads_view_.setText("VIEW");
        btn_ads_view_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ads_view_MouseClicked(evt);
            }
        });
        jPanel63.add(btn_ads_view_, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 120, 30));

        jLabel171.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel171.setText("Class");
        jPanel63.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 50, 50));

        ads_roll.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ads_roll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ads_rollActionPerformed(evt);
            }
        });
        jPanel63.add(ads_roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 100, 30));

        jLabel181.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel181.setText("Roll No");
        jPanel63.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 70, 50));

        ads_class.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        ads_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select Class", "6", "7", "8", "9", "10" }));
        jPanel63.add(ads_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 130, 30));

        jLabel182.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel182.setText("Session");
        jPanel63.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 80, 50));

        jLabel183.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel183.setText("Batch id");
        jPanel63.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 80, 50));

        ads_session.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ads_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ads_sessionActionPerformed(evt);
            }
        });
        jPanel63.add(ads_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 100, 30));

        ads_batch.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ads_batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ads_batchActionPerformed(evt);
            }
        });
        jPanel63.add(ads_batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 100, 30));

        jPanel60.add(jPanel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 970, 50));

        jPanel59.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        ex_admitCardTab.addTab("tab1", jPanel59);

        Exam_Seduel.addTab("tab4", ex_admitCardTab);

        mainTab.addTab("tab4", Exam_Seduel);

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel37.setBackground(new java.awt.Color(204, 255, 204));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_payment_student.jpg"))); // NOI18N
        jLabel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel38.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 170));

        jPanel37.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 810, 170));

        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_payment_staff.jpg"))); // NOI18N
        jLabel104.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel39.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 400, 190));

        jLabel207.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_payments.png"))); // NOI18N
        jLabel207.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel39.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 190));

        jPanel37.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 800, 190));

        btn_pay_st_.setBackground(new java.awt.Color(204, 255, 204));
        btn_pay_st_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_pay_st_.setText("STUDENT PAYMENT");
        btn_pay_st_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btn_pay_st_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pay_st_MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pay_st_MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pay_st_MouseExited(evt);
            }
        });
        jPanel37.add(btn_pay_st_, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 810, 40));

        btn_pay_te.setBackground(new java.awt.Color(204, 255, 204));
        btn_pay_te.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_pay_te.setText("TEACHER PAYMENT");
        btn_pay_te.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btn_pay_te.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pay_teMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pay_teMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pay_teMouseExited(evt);
            }
        });
        jPanel37.add(btn_pay_te, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 800, 40));

        jPanel36.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        Pay_home.addTab("tab1", jPanel36);

        PaymentTab.addTab("tab4", Pay_home);

        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        teachersTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(teachersTable);

        jPanel43.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 940, 130));

        teacher_recipt.setBackground(new java.awt.Color(255, 255, 255));
        teacher_recipt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Teacher and Staff Recipt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        teacher_recipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel43.add(teacher_recipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 330, 390));

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Teacher and Staff Payment ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel45.add(pay_te_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 150, -1));

        jLabel184.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel184.setText("Date");
        jPanel45.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 60, 20));

        jLabel185.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel185.setText("Discription");
        jPanel45.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 120, 30));

        pay_te_bonus.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel45.add(pay_te_bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 430, 30));

        jLabel186.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel186.setText("ID");
        jPanel45.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 30));

        pay_te_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pay_te_idKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pay_te_idKeyReleased(evt);
            }
        });
        jPanel45.add(pay_te_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 200, 30));

        jLabel187.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel187.setText("Total pay");
        jPanel45.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 30));

        pay_te_mon_dec.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_dec.setText("Dec");
        jPanel45.add(pay_te_mon_dec, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 50, 30));

        pay_te_mon_jan.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_jan.setText("Jan");
        jPanel45.add(pay_te_mon_jan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 50, 30));

        pay_te_mon_july.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_july.setText("Jul");
        jPanel45.add(pay_te_mon_july, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 50, 30));

        pay_te_mon_feb.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_feb.setText("Feb");
        jPanel45.add(pay_te_mon_feb, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 50, 30));

        pay_te_mon_aug.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_aug.setText("Aug");
        jPanel45.add(pay_te_mon_aug, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 60, 30));

        pay_te_mon_mar.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_mar.setText("Mar");
        jPanel45.add(pay_te_mon_mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 60, 30));

        pay_te_mon_sep.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_sep.setText("Sep");
        jPanel45.add(pay_te_mon_sep, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 50, 30));

        pay_te_mon_apr.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_apr.setText("Apr");
        jPanel45.add(pay_te_mon_apr, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 60, 30));

        pay_te_mon_oct.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_oct.setText("Oct");
        jPanel45.add(pay_te_mon_oct, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 50, 30));

        pay_te_mon_may.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_may.setText("May");
        jPanel45.add(pay_te_mon_may, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 60, 30));

        pay_te_mon_nov.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_nov.setText("Nov");
        jPanel45.add(pay_te_mon_nov, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 60, 30));

        pay_te_mon_june.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_mon_june.setText("Jun");
        jPanel45.add(pay_te_mon_june, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 50, 30));

        jLabel188.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel188.setText("Pay Month");
        jPanel45.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 30));

        jLabel189.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel189.setText("Salary");
        jPanel45.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, 30));

        pay_te_Total.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_te_Total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pay_te_TotalMouseClicked(evt);
            }
        });
        jPanel45.add(pay_te_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 430, 30));

        pay_te_name.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel45.add(pay_te_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 200, 30));

        jLabel190.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel190.setText("Name");
        jPanel45.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, 30));

        pay_te_descript.setColumns(20);
        pay_te_descript.setRows(5);
        jScrollPane14.setViewportView(pay_te_descript);

        jPanel45.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 430, 30));

        jLabel191.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel191.setText("Bonus");
        jPanel45.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 110, 30));

        pay_te_salary.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jPanel45.add(pay_te_salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 430, 30));

        jPanel43.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 350));

        jButton16.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton16.setText("Print");
        jPanel43.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 100, 30));

        jButton17.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton17.setText("Add to cart");
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton17MouseEntered(evt);
            }
        });
        jPanel43.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 30));

        btn_save_te_pay_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_save_te_pay_.setText("Save");
        btn_save_te_pay_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_save_te_pay_MouseClicked(evt);
            }
        });
        jPanel43.add(btn_save_te_pay_, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 100, 30));

        jButton19.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton19.setText("Reset");
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
        });
        jPanel43.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 30));

        jButton29.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton29.setText("Update");
        jPanel43.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 110, 30));

        jPanel40.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        Pay_TeachersTab.addTab("tab1", jPanel40);

        PaymentTab.addTab("tab1", Pay_TeachersTab);

        jPanel50.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paymentStudentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(paymentStudentTable);

        jPanel51.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 940, 130));

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Student Payment ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pay_st_Date.setBackground(new java.awt.Color(204, 255, 204));
        jPanel53.add(pay_st_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 150, 30));

        jLabel103.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel103.setText("Date");
        jPanel53.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 110, 30));

        jLabel106.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel106.setText("Payment Id");
        jPanel53.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 30));

        jLabel107.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel107.setText("Batch Id");
        jPanel53.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 30));

        jLabel108.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel108.setText("Class");
        jPanel53.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 30));

        jLabel109.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel109.setText("Student Roll");
        jPanel53.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 100, 30));

        pay_st_Roll.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Roll.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pay_st_RollFocusLost(evt);
            }
        });
        pay_st_Roll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_RollActionPerformed(evt);
            }
        });
        pay_st_Roll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pay_st_RollKeyReleased(evt);
            }
        });
        jPanel53.add(pay_st_Roll, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 130, 30));

        pay_st_id.setEditable(false);
        pay_st_id.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_idActionPerformed(evt);
            }
        });
        jPanel53.add(pay_st_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 130, 30));

        pay_st_TotalFee.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        pay_st_TotalFee.setForeground(new java.awt.Color(255, 102, 102));
        pay_st_TotalFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_TotalFeeActionPerformed(evt);
            }
        });
        jPanel53.add(pay_st_TotalFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 430, 30));

        pay_st_class_combo.setBackground(new java.awt.Color(204, 255, 204));
        pay_st_class_combo.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        pay_st_class_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select class", "6", "7", "8", "9", "10" }));
        jPanel53.add(pay_st_class_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 130, 30));

        jLabel111.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel111.setText("Pay Month");
        jPanel53.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 110, 30));

        jLabel112.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel112.setText("Monthly Fee");
        jPanel53.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 110, 30));

        pay_st_catagory.setBackground(new java.awt.Color(204, 255, 204));
        pay_st_catagory.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        pay_st_catagory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----------", "Half Yearly", "Final" }));
        jPanel53.add(pay_st_catagory, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 150, 30));

        pay_st_batch.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_batchActionPerformed(evt);
            }
        });
        jPanel53.add(pay_st_batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 130, 30));

        jLabel121.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel121.setText("Session");
        jPanel53.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 30));

        pay_st_session.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_sessionActionPerformed(evt);
            }
        });
        jPanel53.add(pay_st_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 130, 30));

        jLabel122.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel122.setText("Exam Catagory");
        jPanel53.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 110, 30));

        pay_st_admision.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_admision.setText("0");
        jPanel53.add(pay_st_admision, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 150, 30));

        jLabel125.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel125.setText("Total Payment");
        jPanel53.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 30));

        jLabel126.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel126.setText("Exam Fee");
        jPanel53.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 110, 30));

        pay_st_exam_fee.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_exam_fee.setText("0");
        jPanel53.add(pay_st_exam_fee, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 150, 30));

        pay_st_Mon_july.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_july.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_july.setText("Jul");
        jPanel53.add(pay_st_Mon_july, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 60, 30));

        pay_st_Mon_jan.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_jan.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_jan.setText("Jan");
        jPanel53.add(pay_st_Mon_jan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 60, 30));

        pay_st_Mon_feb.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_feb.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_feb.setText("Feb");
        jPanel53.add(pay_st_Mon_feb, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 60, 30));

        pay_st_Mon_aug.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_aug.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_aug.setText("Aug");
        jPanel53.add(pay_st_Mon_aug, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 60, 30));

        pay_st_Mon_mar.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_mar.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_mar.setText("Mar");
        jPanel53.add(pay_st_Mon_mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 60, 30));

        pay_st_Mon_sep.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_sep.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_sep.setText("Sep");
        jPanel53.add(pay_st_Mon_sep, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 60, 30));

        pay_st_Mon_may.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_may.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_may.setText("May");
        jPanel53.add(pay_st_Mon_may, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 60, 30));

        pay_st_Mon_nov.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_nov.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_nov.setText("Nov");
        jPanel53.add(pay_st_Mon_nov, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 60, 30));

        pay_st_Mon_apr.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_apr.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_apr.setText("Apr");
        jPanel53.add(pay_st_Mon_apr, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 60, 30));

        pay_st_Mon_dec.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_dec.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_dec.setText("Dec");
        jPanel53.add(pay_st_Mon_dec, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 60, 30));

        pay_st_Mon_june.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_june.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_june.setText("June");
        jPanel53.add(pay_st_Mon_june, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 60, 30));

        pay_st_Mon_Oct.setBackground(new java.awt.Color(255, 255, 255));
        pay_st_Mon_Oct.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_Mon_Oct.setText("Oct");
        jPanel53.add(pay_st_Mon_Oct, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 60, 30));

        jLabel128.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel128.setText("Admision Fee");
        jPanel53.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 110, 30));

        pay_st_FeeAmount.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        pay_st_FeeAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pay_st_FeeAmountFocusLost(evt);
            }
        });
        pay_st_FeeAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_st_FeeAmountActionPerformed(evt);
            }
        });
        jPanel53.add(pay_st_FeeAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 430, 30));

        jPanel51.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 590, 350));

        btn_st_pay_print.setBackground(new java.awt.Color(204, 255, 204));
        btn_st_pay_print.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_st_pay_print.setText("Print");
        btn_st_pay_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_st_pay_printMouseClicked(evt);
            }
        });
        jPanel51.add(btn_st_pay_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, 90, 30));

        btn_st_pay_AddCurt_.setBackground(new java.awt.Color(204, 255, 204));
        btn_st_pay_AddCurt_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_st_pay_AddCurt_.setText("Add to cart");
        btn_st_pay_AddCurt_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_st_pay_AddCurt_MouseClicked(evt);
            }
        });
        btn_st_pay_AddCurt_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_st_pay_AddCurt_ActionPerformed(evt);
            }
        });
        jPanel51.add(btn_st_pay_AddCurt_, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 150, 30));

        btn_st_pay_save_.setBackground(new java.awt.Color(204, 255, 204));
        btn_st_pay_save_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_st_pay_save_.setText("Save");
        btn_st_pay_save_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_st_pay_save_MouseClicked(evt);
            }
        });
        jPanel51.add(btn_st_pay_save_, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 100, 30));

        btn_st_pay_reset.setBackground(new java.awt.Color(204, 255, 204));
        btn_st_pay_reset.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_st_pay_reset.setText("Reset");
        btn_st_pay_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_st_pay_resetMouseClicked(evt);
            }
        });
        jPanel51.add(btn_st_pay_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 100, 30));

        btn_st_pay_update.setBackground(new java.awt.Color(204, 255, 204));
        btn_st_pay_update.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_st_pay_update.setText("Update");
        jPanel51.add(btn_st_pay_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 110, 30));

        Payment_student_recipt.setColumns(20);
        Payment_student_recipt.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        Payment_student_recipt.setRows(5);
        Payment_student_recipt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Student Recepit", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N
        jScrollPane6.setViewportView(Payment_student_recipt);

        jPanel51.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 340, 390));

        all_st_lable.setBackground(new java.awt.Color(255, 255, 255));
        all_st_lable.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        all_st_lable.setForeground(new java.awt.Color(255, 102, 51));
        all_st_lable.setText("All Student Pay Table");
        jPanel51.add(all_st_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 170, 20));

        Btn_AllStudent.setBackground(new java.awt.Color(204, 204, 255));
        Btn_AllStudent.setText("All Student table");
        Btn_AllStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_AllStudentMouseClicked(evt);
            }
        });
        Btn_AllStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AllStudentActionPerformed(evt);
            }
        });
        jPanel51.add(Btn_AllStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 120, 20));

        Btn_OneStudent.setBackground(new java.awt.Color(204, 204, 255));
        Btn_OneStudent.setText("1 Student table");
        Btn_OneStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_OneStudentMouseClicked(evt);
            }
        });
        jPanel51.add(Btn_OneStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 110, 20));

        one_st_lable.setBackground(new java.awt.Color(255, 255, 255));
        one_st_lable.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        one_st_lable.setForeground(new java.awt.Color(255, 102, 51));
        one_st_lable.setText("One Student Pay Table");
        jPanel51.add(one_st_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 170, 20));

        jPanel50.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        Pay_StudentTab.addTab("tab1", jPanel50);

        PaymentTab.addTab("tab3", Pay_StudentTab);

        mainTab.addTab("tab5", PaymentTab);
        mainTab.addTab("tab8", ex1);
        mainTab.addTab("tab9", ex2);
        mainTab.addTab("tab10", ex3);

        jPanel76.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel77.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_rutine_home_V.setBackground(new java.awt.Color(204, 255, 204));
        btn_rutine_home_V.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_rutine_home_V.setText("View Class Rutine");
        btn_rutine_home_V.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rutine_home_VMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rutine_home_VMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_rutine_home_VMouseExited(evt);
            }
        });
        jPanel77.add(btn_rutine_home_V, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 240, 100));

        btn_rutine_home_m.setBackground(new java.awt.Color(204, 255, 204));
        btn_rutine_home_m.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_rutine_home_m.setText("Make A Class Rutine ");
        btn_rutine_home_m.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rutine_home_mMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rutine_home_mMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_rutine_home_mMouseExited(evt);
            }
        });
        jPanel77.add(btn_rutine_home_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 240, 110));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assate/rsz_book-1822474_1280.jpg"))); // NOI18N
        jPanel77.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 560));

        jPanel76.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        rutineHomeTab.addTab("tab1", jPanel76);

        ClassRutineTab.addTab("tab3", rutineHomeTab);

        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel75.setBackground(new java.awt.Color(255, 204, 204));
        jPanel75.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel78.setBackground(new java.awt.Color(255, 153, 153));
        jPanel78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel78.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel100.setText("Class");
        jPanel78.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 70, 30));
        jPanel78.add(R_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, 30));

        jLabel238.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel238.setText("Session");
        jPanel78.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 80, 30));

        R_day.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        R_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sunday", "Monday", "tuesday", "wednessday", "thursday" }));
        jPanel78.add(R_day, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 110, 30));

        R_class.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        R_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8", "9", "10" }));
        jPanel78.add(R_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 110, 30));

        jLabel239.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel239.setText("Day");
        jPanel78.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 40, 30));

        jButton26.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton26.setText("Update");
        jPanel78.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 90, 30));

        jButton27.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton27.setText("save");
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });
        jPanel78.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 90, 30));

        jButton30.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jButton30.setText("Reset");
        jPanel78.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 90, 30));

        jPanel75.add(jPanel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 50));

        jLabel240.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel240.setText("Period : 7");
        jLabel240.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 130, 30));

        jLabel241.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel241.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel241.setText("Time set");
        jLabel241.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 220, 30));

        jLabel242.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel242.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel242.setText("Period : 1");
        jLabel242.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel242, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 130, 30));

        jLabel243.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel243.setText("Period : 2");
        jLabel243.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel243, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 130, 30));

        jLabel244.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel244.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel244.setText("Period : 3");
        jLabel244.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 130, 30));

        jLabel245.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel245.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel245.setText("Period : 4");
        jLabel245.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel245, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 130, 30));

        jLabel246.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel246.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel246.setText("Period : 5");
        jLabel246.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 130, 30));

        jLabel247.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel247.setText("Period : 6");
        jLabel247.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel247, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 130, 30));

        jLabel248.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel248.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel248.setText("Period No.");
        jLabel248.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel248, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 130, 30));

        jLabel249.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel249.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel249.setText("Select Subject");
        jLabel249.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 210, 30));

        jLabel250.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel250.setText("Select Teacher");
        jLabel250.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel75.add(jLabel250, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 210, 30));

        R_pre7_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre7_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre7_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 210, 30));

        R_pre7_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre7_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre7_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, 210, 30));

        R_pre2_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre2_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre2_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 210, 30));

        R_pre3_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre3_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre3_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 210, 30));

        R_pre4_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre4_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre4_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 210, 30));

        R_pre5_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre5_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre5_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 210, 30));

        R_pre6_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre6_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre6_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 210, 30));

        R_pre1_sub.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre1_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banngla 1st ", "Bangla 2nd ", "English 1st ", "English 2nd ", "Math", "S.Scince", "Religion", "ICT", "Accounting", "Finance", "B. Entre", "Physics", "Chemistry", "Biology", "Higher Math", "Agri. Studies", "G. Science", "Ban. and G. St", "Geography", "Civic & Citi.", "Economics" }));
        jPanel75.add(R_pre1_sub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 210, 30));

        R_pre7_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre7_time.setText("10:30-11:15 am");
        jPanel75.add(R_pre7_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 220, 30));

        R_pre1_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre1_time.setText("10:30-11:15 am");
        jPanel75.add(R_pre1_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 220, 30));

        R_pre2_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre2_time.setText("10:30 -11:15 am");
        jPanel75.add(R_pre2_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 220, 30));

        R_pre3_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre3_time.setText("10:30-11:15 am");
        R_pre3_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R_pre3_timeActionPerformed(evt);
            }
        });
        jPanel75.add(R_pre3_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 220, 30));

        R_pre4_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre4_time.setText("10:30-11:15 am");
        jPanel75.add(R_pre4_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 220, 30));

        R_pre5_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre5_time.setText("10:30-11:15 am");
        jPanel75.add(R_pre5_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, 220, 30));

        R_pre6_time.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        R_pre6_time.setText("10:30-11:15 am");
        R_pre6_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R_pre6_timeActionPerformed(evt);
            }
        });
        jPanel75.add(R_pre6_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 220, 30));

        R_pre1_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre1_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre1_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 210, 30));

        R_pre2_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre2_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre2_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 210, 30));

        R_pre3_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre3_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre3_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 210, 30));

        R_pre4_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre4_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre4_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 210, 30));

        R_pre5_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre5_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre5_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 210, 30));

        R_pre6_tea.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        R_pre6_tea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------------", "Rajib Hasan", "Sobuj islam", "Tusar sheik", "Hasan Hamid", "Limon borno", "Mamun Khan" }));
        jPanel75.add(R_pre6_tea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 210, 30));

        jPanel49.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 560));

        inputRutineTab.addTab("tab1", jPanel49);

        ClassRutineTab.addTab("tab1", inputRutineTab);

        jPanel62.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel65.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel66.setBackground(new java.awt.Color(204, 255, 204));
        jPanel66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel66.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel216.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel216.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel216.setText("Class");
        jPanel66.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 70, 60));

        p_sessoion.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jPanel66.add(p_sessoion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 110, 40));

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton3.setText("Scarch");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel66.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 110, 40));

        jLabel217.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel217.setText("Session");
        jPanel66.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 60));

        p__class.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        p__class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8", "9", "10" }));
        jPanel66.add(p__class, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 120, 40));

        btn_Rutine_print_.setBackground(new java.awt.Color(255, 153, 153));
        btn_Rutine_print_.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        btn_Rutine_print_.setText("Print");
        btn_Rutine_print_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Rutine_print_MouseClicked(evt);
            }
        });
        btn_Rutine_print_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Rutine_print_ActionPerformed(evt);
            }
        });
        jPanel66.add(btn_Rutine_print_, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 100, 40));

        jButton12.setBackground(new java.awt.Color(255, 153, 153));
        jButton12.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jButton12.setText("Reset");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jPanel66.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 100, 40));

        jPanel65.add(jPanel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 60));

        RutinePanal.setBackground(new java.awt.Color(255, 153, 153));
        RutinePanal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RutinePanal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel68.setBackground(new java.awt.Color(204, 255, 204));
        jPanel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel68.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel69.setBackground(new java.awt.Color(255, 255, 255));
        jPanel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel195.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel195.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel195.setText("ThursDay");
        jLabel195.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 90, 50));

        P5_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P5_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5_s_sun.setText("sub");
        P5_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P5_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 100, 50));

        jLabel197.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel197.setText("SunDay");
        jLabel197.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, 50));

        jLabel198.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel198.setText("MonDay");
        jLabel198.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 90, 50));

        jLabel199.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel199.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel199.setText("TuesDay");
        jLabel199.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 90, 50));

        jLabel200.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel200.setText("WednessDay");
        jLabel200.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 90, 50));

        jLabel201.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel201.setText("Day");
        jLabel201.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 50));

        P1_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P1_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1_s_sun.setText("sub");
        P1_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P1_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 90, 50));

        P2_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P2_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2_s_sun.setText("sub");
        P2_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P2_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 90, 50));

        P3_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P3_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3_s_sun.setText("sub");
        P3_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P3_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 90, 50));

        P4_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P4_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4_s_sun.setText("sub");
        P4_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P4_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 90, 50));

        P7_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P7_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7_s_sun.setText("sub");
        P7_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P7_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 100, 50));

        P6_s_sun.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P6_s_sun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6_s_sun.setText("sub");
        P6_s_sun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P6_s_sun, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 100, 50));

        jPanel70.setBackground(new java.awt.Color(255, 255, 255));
        jPanel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel70.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel193.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel193.setText("N");
        jPanel70.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 40, 30));

        jLabel194.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel194.setText("T");
        jPanel70.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 40, 30));

        jLabel208.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel208.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel208.setText("I");
        jPanel70.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 40, 30));

        jLabel209.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel209.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel209.setText("F");
        jPanel70.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 40, 30));

        jLabel210.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel210.setText("F");
        jPanel70.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 40, 30));

        jLabel211.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel211.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel211.setText("I");
        jPanel70.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 40, 30));

        jPanel69.add(jPanel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 40, 300));

        jLabel257.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel257.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel257.setText("1st period");
        jLabel257.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel257, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 90, 20));

        jLabel258.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel258.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel258.setText("2nd period");
        jLabel258.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel258, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 90, 20));

        jLabel259.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel259.setText("3rd period");
        jLabel259.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 90, 20));

        jLabel260.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel260.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel260.setText("4th period");
        jLabel260.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 90, 20));

        jLabel261.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel261.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel261.setText("5th period");
        jLabel261.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 100, 20));

        jLabel262.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel262.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel262.setText("6th period");
        jLabel262.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 100, 20));

        jLabel263.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel263.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel263.setText("7th period");
        jLabel263.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 100, 20));

        p1_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p1_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1_time.setText("time");
        p1_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p1_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 90, 30));

        p2_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p2_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2_time.setText("time");
        p2_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p2_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 90, 30));

        p3_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p3_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3_time.setText("time");
        p3_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p3_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 90, 30));

        p4_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p4_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4_time.setText("time");
        p4_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p4_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 90, 30));

        p5_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p5_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p5_time.setText("time");
        p5_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p5_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 100, 30));

        p6_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p6_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p6_time.setText("time");
        p6_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p6_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, 30));

        p7_time.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        p7_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p7_time.setText("time");
        p7_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(p7_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 100, 30));

        P1_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P1_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1_s_mon.setText("sub");
        P1_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P1_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 90, 50));

        P2_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P2_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2_s_mon.setText("sub");
        P2_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P2_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 90, 50));

        P3_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P3_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3_s_mon.setText("sub");
        P3_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P3_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 90, 50));

        P4_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P4_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4_s_mon.setText("sub");
        P4_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P4_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 90, 50));

        P5_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P5_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5_s_mon.setText("sub");
        P5_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P5_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 100, 50));

        P6_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P6_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6_s_mon.setText("sub");
        P6_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P6_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 100, 50));

        P7_s_mon.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P7_s_mon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7_s_mon.setText("sub");
        P7_s_mon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P7_s_mon, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 100, 50));

        P1_s_tues.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P1_s_tues.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1_s_tues.setText("sub");
        P1_s_tues.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P1_s_tues, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 90, 50));

        P2_s_tues.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P2_s_tues.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2_s_tues.setText("sub");
        P2_s_tues.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P2_s_tues, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 90, 50));

        P3_s_thus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P3_s_thus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3_s_thus.setText("sub");
        P3_s_thus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P3_s_thus, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 90, 50));

        P4_s_thus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P4_s_thus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4_s_thus.setText("sub");
        P4_s_thus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P4_s_thus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 90, 50));

        P5_s_thus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P5_s_thus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5_s_thus.setText("sub");
        P5_s_thus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P5_s_thus, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 100, 50));

        P6_s_thus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P6_s_thus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6_s_thus.setText("sub");
        P6_s_thus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P6_s_thus, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 100, 50));

        P7_s_thus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P7_s_thus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7_s_thus.setText("sub");
        P7_s_thus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P7_s_thus, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 100, 50));

        P1_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P1_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1_s_wed.setText("sub");
        P1_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P1_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 90, 50));

        P2_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P2_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2_s_wed.setText("sub");
        P2_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P2_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 90, 50));

        P3_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P3_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3_s_wed.setText("sub");
        P3_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P3_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 90, 50));

        P4_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P4_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4_s_wed.setText("sub");
        P4_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P4_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 90, 50));

        P5_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P5_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5_s_wed.setText("sub");
        P5_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P5_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 100, 50));

        P6_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P6_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6_s_wed.setText("sub");
        P6_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P6_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 100, 50));

        P7_s_wed.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P7_s_wed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7_s_wed.setText("sub");
        P7_s_wed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P7_s_wed, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 100, 50));

        P1_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P1_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P1_s_th.setText("sub");
        P1_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P1_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 90, 50));

        P2_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P2_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P2_s_th.setText("sub");
        P2_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P2_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 90, 50));

        P3_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P3_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P3_s_th.setText("sub");
        P3_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P3_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 90, 50));

        P4_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P4_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P4_s_th.setText("sub");
        P4_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P4_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 90, 50));

        P5_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P5_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P5_s_th.setText("sub");
        P5_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P5_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 100, 50));

        P6_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P6_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P6_s_th.setText("sub");
        P6_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P6_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 100, 50));

        P7_s_th.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        P7_s_th.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P7_s_th.setText("sub");
        P7_s_th.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel69.add(P7_s_th, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 100, 50));

        jPanel68.add(jPanel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 790, 300));

        p_Class_show.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        p_Class_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel68.add(p_Class_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 50, 20));

        jLabel213.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel213.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel213.setText("Lalbag Model School And Collage");
        jPanel68.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 30));

        jLabel214.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel214.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel214.setText("lalbag, Dhaka-1213");
        jPanel68.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 330, 20));

        jLabel215.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel215.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel215.setText("Class Rutine");
        jPanel68.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 40));

        RutinePanal.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 850, 370));

        jPanel65.add(RutinePanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 910, 430));

        jPanel62.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 970, 560));

        classRutineView.addTab("tab1", jPanel62);

        ClassRutineTab.addTab("tab3", classRutineView);

        mainTab.addTab("tab7", ClassRutineTab);

        bodyPanal.add(mainTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -90, 990, 660));

        mainPanal.add(bodyPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 970, 560));

        getContentPane().add(mainPanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        mainTab.setSelectedIndex(0);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
        btnHome.setBackground(new Color(52, 152, 219));
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseClicked
        mainTab.setSelectedIndex(1);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnStudentMouseClicked

    private void btnTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTeacherMouseClicked
        mainTab.setSelectedIndex(2);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);

    }//GEN-LAST:event_btnTeacherMouseClicked

    private void btnExamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseClicked
        mainTab.setSelectedIndex(3);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
        Exam_Seduel.setSelectedIndex(0);
    }//GEN-LAST:event_btnExamMouseClicked

    private void btnPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseClicked
        mainTab.setSelectedIndex(4);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
        PaymentTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnPaymentMouseClicked

    private void exitTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTabMouseClicked
        dispose();
    }//GEN-LAST:event_exitTabMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:
        btnHome.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        // TODO add your handling code here:
        btnHome.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnHomeMouseExited

    private void st_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_1MouseEntered
        // TODO add your handling code here:
//         .setBackground(Color.GREEN);

    }//GEN-LAST:event_st_1MouseEntered

    private void St_btn_addmitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_addmitionMouseClicked
        StudentTab.setSelectedIndex(1);
    }//GEN-LAST:event_St_btn_addmitionMouseClicked

    private void St_btn_DeatilsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_DeatilsMouseClicked
        StudentTab.setSelectedIndex(2);
    }//GEN-LAST:event_St_btn_DeatilsMouseClicked

    private void st_btn_result_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_inMouseClicked
        StudentTab.setSelectedIndex(3);
        InvisiableResultInput();
    }//GEN-LAST:event_st_btn_result_inMouseClicked

    private void st_btn_result_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_outMouseClicked
        StudentTab.setSelectedIndex(4);
    }//GEN-LAST:event_st_btn_result_outMouseClicked

    private void te_btn_rutinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_te_btn_rutinesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_te_btn_rutinesActionPerformed

    private void te_btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_addMouseClicked
        TeacherTab.setSelectedIndex(1);
    }//GEN-LAST:event_te_btn_addMouseClicked

    private void te_btn_rutinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_rutinesMouseClicked
        TeacherTab.setSelectedIndex(2);
    }//GEN-LAST:event_te_btn_rutinesMouseClicked

    private void te_btn_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_reportMouseClicked
        TeacherTab.setSelectedIndex(3);
    }//GEN-LAST:event_te_btn_reportMouseClicked

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
        mainTab.setSelectedIndex(2);
        StudentTab.setSelectedIndex(0);
        TeacherTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnStaff1MouseClicked


    private void btn_Save_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Save_MouseClicked
        String sql = "insert into School ( Session_year,batch_id,classs,student_Roll,full_name,fathers_name,"
                + "fa_te_phone,mothers_name,mothers_phone,date_of_birth,age,gender,religion,nationality,"
                + "email,blood_group,birth_certificate,present_Address,permanent_Address,catagory,designation,qualification,joiningdate,picture)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.setString(20, s_catagory.getText().toString().trim());
            ps.setString(21, "***");
            ps.setString(22, "***");
            ps.setDate(23, dateMethod(s_admisiondate.getDate()));
//            ps.setString(24, s_Image_lable.getText());
            ps.setString(24, selectedImagePath);
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "data is save");
            studentAdmisionReset();
            studentTable();
            AllMethods();
//            TotalStudentReport_6();
//            TotalStudentReport_7();
//            TotalStudentReport_8();
//            TotalStudentReport_9();
//            TotalStudentReport_10();
//            getClassTocomboFromStudentTable();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data is not save");
        }
    }//GEN-LAST:event_btn_Save_MouseClicked

    private void btn_AdmiosionReset_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AdmiosionReset_MouseClicked
        studentAdmisionReset();
    }//GEN-LAST:event_btn_AdmiosionReset_MouseClicked

    private void btn_sd_View1_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sd_View1_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sd_View1_ActionPerformed

    private void btn_sd_Print_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sd_Print_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sd_Print_ActionPerformed

    private void btn_sd_View1_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_View1_MouseClicked
        getDateFromDataBaseSetViewForm();
    }//GEN-LAST:event_btn_sd_View1_MouseClicked

    private void r_gradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_gradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_gradeActionPerformed

    private void r_banglaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_banglaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_banglaActionPerformed

    private void r_englishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_englishActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_englishActionPerformed

    private void r_mathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_mathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_mathActionPerformed

    private void r_scinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_scinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_scinceActionPerformed

    private void r_socialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_socialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_socialActionPerformed

    private void r_islamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_islamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_islamActionPerformed

    private void r_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_totalActionPerformed

    private void r_avarageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_avarageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_avarageActionPerformed

    private void r_gpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_gpaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_gpaActionPerformed

    private void btn_R_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_R_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_R_updateActionPerformed

    private void btn_exam_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_inMouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(1);
    }//GEN-LAST:event_btn_exam_inMouseClicked

    private void btn_exam_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_outMouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(2);
    }//GEN-LAST:event_btn_exam_outMouseClicked

    private void btn_admitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admitMouseClicked
        // TODO add your handling code here:
        Exam_Seduel.setSelectedIndex(3);
    }//GEN-LAST:event_btn_admitMouseClicked

    private void rs_input_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rs_input_sessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rs_input_sessionActionPerformed

    private void rs_input_rollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rs_input_rollActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rs_input_rollActionPerformed

    private void rs_input_batchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rs_input_batchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rs_input_batchActionPerformed

    private void btn_pay_teMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_teMouseClicked
        // TODO add your handling code here:
        PaymentTab.setSelectedIndex(1);
    }//GEN-LAST:event_btn_pay_teMouseClicked

    private void btn_pay_st_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_st_MouseClicked
        // TODO add your handling code here:
        PaymentTab.setSelectedIndex(2);
    }//GEN-LAST:event_btn_pay_st_MouseClicked

    private void te_btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_te_btn_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_te_btn_addActionPerformed

    private void btn_sd_ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_ResetMouseClicked
        // TODO add your handling code here:
        StudentDetailsReset();
    }//GEN-LAST:event_btn_sd_ResetMouseClicked
    public int totalNumber() {

        int bangla = Integer.parseInt(r_bangla.getText().trim());
        int english = Integer.parseInt(r_english.getText().trim());
        int math = Integer.parseInt(r_math.getText().trim());
        int scince = Integer.parseInt(r_scince.getText().trim());
        int social = Integer.parseInt(r_social.getText().trim());
        int islam = Integer.parseInt(r_islam.getText().trim());
        int totalNumberResult = bangla + english + math + scince + social + islam;
        return totalNumberResult;
    }

    public float avarageNumberResult() {
        float avarageNumver = ((float) totalNumber() / 6);
        return avarageNumver;
    }
    private void btn_R_save_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_R_save_MouseClicked
        // TODO add your handling code here:
        String sql = "INSERT INTO resultsheet (Session_year, batch_id, classs, student_Roll, bangla, english, Math, Scince,"
                + " social, islam, totalMark, avarageMark, g_p_a, grade,passFail, examcatagory) VALUES (?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";;
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(r_session.getText().toString().trim()));
            ps.setString(2, r_batch_id.getText().toString().trim());
            ps.setString(3, r_class.getSelectedItem().toString().trim());
            ps.setString(4, r_St_Roll.getText().toString().trim());
            ps.setInt(5, Integer.parseInt(r_bangla.getText().toString().trim()));
            ps.setInt(6, Integer.parseInt(r_english.getText().toString().trim()));
            ps.setInt(7, Integer.parseInt(r_math.getText().toString().trim()));
            ps.setInt(8, Integer.parseInt(r_scince.getText().toString().trim()));
            ps.setInt(9, Integer.parseInt(r_social.getText().toString().trim()));
            ps.setInt(10, Integer.parseInt(r_islam.getText().toString().trim()));
            ps.setInt(11, Integer.parseInt(totalNumber() + ""));
            ps.setFloat(12, Float.parseFloat(avarageNumberResult() + ""));
            ps.setFloat(13, Float.parseFloat(r_gpa.getText().toString().trim()));
            ps.setString(14, r_grade.getText().toString().trim());
            ps.setString(15, r_passFail.getText().toString().trim());
            ps.setString(16, r_examSelect.getSelectedItem().toString().trim());
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "data is save");
            resultTable();
            resultReset();
            AllMethods();
            InvisiableResultInput();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data is not save");
        }
    }//GEN-LAST:event_btn_R_save_MouseClicked

//         result input panal ar jonno GPA number method----------------------------------------------
    public float GPAResult() {
        int bangla = Integer.parseInt(r_bangla.getText().toString().trim());
        int english = Integer.parseInt(r_english.getText().toString().trim());
        int math = Integer.parseInt(r_math.getText().toString().trim());
        int science = Integer.parseInt(r_scince.getText().toString().trim());
        int social = Integer.parseInt(r_social.getText().toString().trim());
        int islam = Integer.parseInt(r_islam.getText().toString().trim());
        int[] subjects = {bangla, english, math, science, social, islam};
        float[] gradePoints = {5.0f, 4.0f, 3.5f, 3.0f, 2.0f, 1.0f, 0.0f};
        float totalGradePoints = 0.0f;
        int totalSubjects = subjects.length;
        for (int i = 0; i < totalSubjects; i++) {
            int marks = subjects[i];
            if (marks >= 80) {
                totalGradePoints += gradePoints[0];
            } else if (marks >= 70) {
                totalGradePoints += gradePoints[1];
            } else if (marks >= 60) {
                totalGradePoints += gradePoints[2];
            } else if (marks >= 50) {
                totalGradePoints += gradePoints[3];
            } else if (marks >= 40) {
                totalGradePoints += gradePoints[4];
            } else if (marks >= 33) {
                totalGradePoints += gradePoints[5];
            } else {
                totalGradePoints += gradePoints[6];
            }
        }
        float averageGradePoint = totalGradePoints / totalSubjects;
        return averageGradePoint;
    }
//result input panal ar jonno GREDE number method----------------------------------------------

    public String GredeResult() {
        String grede = "";
        if (GPAResult() >= 5) {
            grede = "A+";
        } else if (GPAResult() >= 4) {
            grede = "A";
        } else if (GPAResult() >= 3.5) {
            grede = "A-";
        } else if (GPAResult() >= 3) {
            grede = "B";
        } else if (GPAResult() >= 2) {
            grede = "c";
        } else if (GPAResult() >= 1) {
            grede = "D";
        } else if (GPAResult() >= 0) {
            grede = "F";
        }
        return grede;
    }

    //resultShow panal ar jonno GREDE number method----------------------------------------------
    public String GredeTotalResultShow(String point) {
        float points = 0.0f;
        if (point.isEmpty()) {
            points = 0.0f;
        } else {
            points = Float.parseFloat(point);
        }
        String grede = "";
        if (points >= 5) {
            grede = "A+";
        } else if (points >= 4) {
            grede = "A";
        } else if (points >= 3.5) {
            grede = "A-";
        } else if (points >= 3) {
            grede = "B";
        } else if (points >= 2) {
            grede = "c";
        } else if (points >= 1) {
            grede = "D";
        } else if (points >= 0) {
            grede = "F";
        }
        return grede;
    }

    public String getGrade(String marks) {
        int mark = 0;
        if (marks.isEmpty()) {
            mark = 0;
        } else {
            mark = Integer.parseInt(marks);
        }
        String grede = "";
        if (mark >= 80) {
            grede = "A+";
        } else if (mark >= 70) {
            grede = "A";
        } else if (mark >= 60) {
            grede = "A-";
        } else if (mark >= 50) {
            grede = "B";
        } else if (mark >= 40) {
            grede = "c";
        } else if (mark >= 33) {
            grede = "D";
        } else if (mark >= 0) {
            grede = "F";
        }
        return grede;
    }

    public void PassFaildResult() {
        int bangla = Integer.parseInt(r_bangla.getText().toString().trim());
        int english = Integer.parseInt(r_english.getText().toString().trim());
        int math = Integer.parseInt(r_math.getText().toString().trim());
        int science = Integer.parseInt(r_scince.getText().toString().trim());
        int social = Integer.parseInt(r_social.getText().toString().trim());
        int islam = Integer.parseInt(r_islam.getText().toString().trim());
        if (bangla > 32 && english > 32 && math > 32 && science > 32 && social > 32 && islam > 32) {
            r_gpa.setText(GPAResult() + "");
            r_grade.setText(GredeResult());
            r_passFail.setText("Pass");
        } else {
            r_gpa.setText(0.00 + "");
            r_grade.setText("F");
            r_passFail.setText("Fail");
        }
    }
    private void r_islamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_r_islamFocusLost
        // TODO add your handling code here:
        r_total.setText(totalNumber() + "");
        r_avarage.setText(avarageNumberResult() + "");
        PassFaildResult();
    }//GEN-LAST:event_r_islamFocusLost
    String[] ResultDataShow = {"serial_No", "Session_year", "batch_id", "Class", "Roll", "bangla", "english", "math", "scince",
        "social", "islam", "total", "avarage", "GPA", "Grade", "Result ", "catagory"};

    public void resultTable() {
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(ResultDataShow);
        tableResultInput.setModel(modelTable);
        String sql = "Select * from resultSheet";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int serial = rs.getInt("serial_No");
                int session = rs.getInt("Session_year");
                String batch = rs.getString("batch_id");
                String classs = rs.getString("classs");
                int roll = rs.getInt("student_Roll");
                int bangla = rs.getInt("bangla");
                int english = rs.getInt("english");
                int math = rs.getInt("math");
                int scince = rs.getInt("scince");
                int social = rs.getInt("social");
                int islam = rs.getInt("islam");
                int totalMark = rs.getInt("totalMark");
                float avarage = rs.getFloat("avarageMark");
                float gpa = rs.getFloat("g_p_a");
                String passFail = rs.getString("passFail");
                String grede = rs.getString("grade");
                String catagpry = rs.getString("examCatagory");

                modelTable.addRow(new Object[]{serial, session, batch, classs, roll, bangla, english, math, scince, social, islam, totalMark, avarage, gpa, grede, passFail, catagpry});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void tableResultInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResultInputMouseClicked
        // TODO add your handling code here:
        int row = tableResultInput.getSelectedRow();

        String serial = tableResultInput.getModel().getValueAt(row, 0).toString();
        String Session = tableResultInput.getModel().getValueAt(row, 1).toString();
        String batch = tableResultInput.getModel().getValueAt(row, 2).toString();
        String classs = tableResultInput.getModel().getValueAt(row, 3).toString();
        String roll = tableResultInput.getModel().getValueAt(row, 4).toString();
        String bangla = tableResultInput.getModel().getValueAt(row, 5).toString();
        String english = tableResultInput.getModel().getValueAt(row, 6).toString();
        String math = tableResultInput.getModel().getValueAt(row, 7).toString();
        String scince = tableResultInput.getModel().getValueAt(row, 8).toString();
        String social = tableResultInput.getModel().getValueAt(row, 9).toString();
        String islam = tableResultInput.getModel().getValueAt(row, 10).toString();
        String total = tableResultInput.getModel().getValueAt(row, 11).toString();
        String avarage = tableResultInput.getModel().getValueAt(row, 12).toString();
        String gpa = tableResultInput.getModel().getValueAt(row, 13).toString();
        String grade = tableResultInput.getModel().getValueAt(row, 14).toString();
        String passfail = tableResultInput.getModel().getValueAt(row, 15).toString();
        String catagory = tableResultInput.getModel().getValueAt(row, 16).toString();

        r_Serial.setText(serial);
        r_session.setText(Session);
        r_batch_id.setText(batch);
        r_class.setSelectedItem(classs);
        r_St_Roll.setText(roll);
        r_bangla.setText(bangla);
        r_english.setText(english);
        r_math.setText(math);
        r_scince.setText(scince);
        r_social.setText(social);
        r_islam.setText(islam);
        r_total.setText(total);
        r_avarage.setText(avarage);
        r_gpa.setText(gpa);
        r_grade.setText(grade);
        r_passFail.setText(passfail);
        r_examSelect.setSelectedItem(catagory);
    }//GEN-LAST:event_tableResultInputMouseClicked

    private void btn_R_ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_R_ResetMouseClicked
        // TODO add your handling code here:
        resultReset();
    }//GEN-LAST:event_btn_R_ResetMouseClicked

    private void btn_R_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_R_DeleteMouseClicked
        // TODO add your handling code here:
        String sql = "Delete FROM resultsheet WHERE serial_No=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, r_St_Roll.getText().toString().trim()); // Use student_Roll as the identifier for the WHERE clause

            int rowsDeleted = ps.executeUpdate();
            ps.close();
            con.getCon().close();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data is deleted");
                resultTable();
                // Optionally, you can reset the input fields after deletion
                resultReset();
                AllMethods();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No records found for deletion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data deletion failed");
        }
    }//GEN-LAST:event_btn_R_DeleteMouseClicked

    private void btn_R_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_R_updateMouseClicked
        // TODO add your handling code here:
        String sql = "UPDATE resultsheet SET Session_year=?, batch_id=?, classs=?,"
                + "student_Roll=?,  bangla=?, english=?, Math=?, Scince=?, social=?, islam=?, totalMark=?, "
                + "avarageMark=?, g_p_a=?, grade=?,passFail=?, examcatagory=? WHERE serial_No=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(r_session.getText().toString().trim()));
            ps.setString(2, r_batch_id.getText().toString().trim());
            ps.setString(3, r_class.getSelectedItem().toString().trim());
            ps.setString(4, r_St_Roll.getText().toString().trim());
            ps.setInt(5, Integer.parseInt(r_bangla.getText().toString().trim()));
            ps.setInt(6, Integer.parseInt(r_english.getText().toString().trim()));
            ps.setInt(7, Integer.parseInt(r_math.getText().toString().trim()));
            ps.setInt(8, Integer.parseInt(r_scince.getText().toString().trim()));
            ps.setInt(9, Integer.parseInt(r_social.getText().toString().trim()));
            ps.setInt(10, Integer.parseInt(r_islam.getText().toString().trim()));
            ps.setInt(11, Integer.parseInt(totalNumber() + ""));
            ps.setFloat(12, Float.parseFloat(avarageNumberResult() + ""));
            ps.setFloat(13, Float.parseFloat(r_gpa.getText().toString().trim()));
            ps.setString(14, r_grade.getText().toString().trim());
            ps.setString(15, r_passFail.getText().toString().trim());
            ps.setString(16, r_examSelect.getSelectedItem().toString().trim());
            ps.setInt(17, Integer.parseInt(r_Serial.getText().toString().trim()));
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data is updated");
            resultTable();
            resultTable();
            AllMethods();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data update failed");
        }
    }//GEN-LAST:event_btn_R_updateMouseClicked

    private void sd_sessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sd_sessionMouseClicked
        // TODO add your handling code here:
        StudentDetailsReset();
    }//GEN-LAST:event_sd_sessionMouseClicked
   
    private void btn_Image_set_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Image_set_MouseClicked
        // TODO add your handling code here:
           JFileChooser browseImageFile = new JFileChooser();

    // for filtering image extension
    FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
    browseImageFile.addChoosableFileFilter(fnef);

    int showOpenDialogue = browseImageFile.showOpenDialog(null);

    if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
        File selectedImageFile = browseImageFile.getSelectedFile();
        selectedImagePath = selectedImageFile.getAbsolutePath();
        JOptionPane.showMessageDialog(null, selectedImagePath);

        // for displaying image on jlabel
        ImageIcon li = new ImageIcon(selectedImagePath);

        // for resize image in jlabel
        Image image = li.getImage().getScaledInstance(s_Image_lable.getWidth(), s_Image_lable.getHeight(), Image.SCALE_SMOOTH);

        s_Image_lable.setIcon(new ImageIcon(image));
    }
      
    }//GEN-LAST:event_btn_Image_set_MouseClicked
//One by one student result show Query resultsheet show------------------------------------

    public void getDataFormResultSheet() {
        String sql = "select * from resultsheet where Session_year=? and batch_id=?"
                + " and classs=? and student_Roll=?";
        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as needed

            ps = con.getCon().prepareStatement(sql);
            int year = Integer.parseInt(rs_input_session.getText().toString().trim());
            ps.setInt(1, year);
//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, rs_input_batch.getText().toString().trim());
            ps.setString(3, rs_input_class.getSelectedItem().toString().trim());
            ps.setString(4, rs_input_roll.getText().toString().trim());
            rs = ps.executeQuery();
            while (rs.next()) {
                rs_bangla.setText(rs.getString("bangla"));
                rs_english.setText(rs.getString("english"));
                rs_math.setText(rs.getString("Math"));
                rs_scince.setText(rs.getString("scince"));
                rs_social.setText(rs.getString("social"));
                rs_islam.setText(rs.getString("islam"));
                rs_total.setText(rs.getString("totalMark"));
                rs_gpa.setText(rs.getString("g_p_a"));
                rs_PassFail.setText(rs.getString("passFail"));
                rsg_total.setText(rs.getString("passFail"));
                rs_catagory.setText(rs.getString("examCatagory"));

                String bangla_grade = getGrade(rs.getString("bangla"));
                String english_grade = getGrade(rs.getString("english"));
                String math_grade = getGrade(rs.getString("Math"));
                String scince_grade = getGrade(rs.getString("scince"));
                String social_grade = getGrade(rs.getString("social"));
                String islam_grade = getGrade(rs.getString("islam"));
                String GPa_grade = GredeTotalResultShow(rs.getString("g_p_a"));

                rsg_bangla.setText(bangla_grade);
                rsg_english.setText(english_grade);
                rsg_math.setText(math_grade);
                rsg_scince.setText(scince_grade);
                rsg_social.setText(social_grade);
                rsg_islam.setText(islam_grade);
                rsg_gpa.setText(GPa_grade);
            }
            rs.close();
            ps.close();
            con.getCon().close();
            rs_roll.setText(rs_input_roll.getText());
            rs_batch.setText(rs_input_batch.getText());
            rs_class.setText(rs_input_class.getSelectedItem().toString());
            rs_session.setText(rs_input_session.getText());
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "No matching record found.");
        }
    }

    //One by one student name result show Query school table show------------------------------------
    public void getDataformSchoolTableOnlyName() {

        String sql = "select full_name from school where Session_year=? and batch_id=? and classs=? and student_Roll=?";

        try {
            ps = con.getCon().prepareStatement(sql);

            System.out.println("2");
            int year = Integer.parseInt(rs_input_session.getText().toString().trim());
            ps.setInt(1, year);

//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, rs_input_batch.getText().toString().trim());
            ps.setString(3, rs_input_class.getSelectedItem().toString().trim());
            ps.setString(4, rs_input_roll.getText().toString().trim());
            rs = ps.executeQuery();
            while (rs.next()) {
                rs_name.setText(rs.getString("full_name"));
            }
            rs.close();
            ps.close();
            con.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_rs_View_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rs_View_MouseClicked
        String mark = rs_bangla.getText();        // TODO add your handling code here:
        getDataFormResultSheet();
        getDataformSchoolTableOnlyName();
        getGrade(mark);
    }//GEN-LAST:event_btn_rs_View_MouseClicked
    private void btn_rs_Reset_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rs_Reset_MouseClicked
        // TODO add your handling code here:
        resultShowReset();
    }//GEN-LAST:event_btn_rs_Reset_MouseClicked
    private void rs_banglaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rs_banglaPropertyChange
        // TODO add your handling code here:
        String mark = rs_bangla.getText();

        if (mark.isEmpty()) {
            rs_bangla.setText("");

        } else {
            getGrade(mark);
        }
    }//GEN-LAST:event_rs_banglaPropertyChange
    private void btn_Result_sheet_panal_Print_MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Result_sheet_panal_Print_MouseDragged
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                Result_sheet_panal.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_Result_sheet_panal_Print_MouseDragged
    private void btn_Result_sheet_panal_Print_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Result_sheet_panal_Print_MouseClicked
        // TODO add your handling code here:

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                Result_sheet_panal.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_Result_sheet_panal_Print_MouseClicked
    private void btn_sd_Print_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_Print_MouseClicked
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                Student_details_panal.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_sd_Print_MouseClicked
    private void btn_sd_View_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_View_MouseClicked
        // TODO add your handling code here:
        getDateFromDataBaseSetViewForm();

    }//GEN-LAST:event_btn_sd_View_MouseClicked
    private void btn_st_pay_printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_st_pay_printMouseClicked
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");
        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.68, 0.70);
                Payment_student_recipt.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_st_pay_printMouseClicked
    int totalPayment = 0;
    String month = "";

    public int totalFee() {
        totalPayment = 0;
        List<String> months = Arrays.asList("jan", "feb", "mar", "apr", "may", "june", "july", "aug", "sep", "oct", "nov", "decm");
        for (int i = 0; i <= 11; i++) {
            month = months.get(i);
            if (totalFeeSwich(month)) {
                System.out.println(i);
//                    ps.setInt(i, Integer.parseInt(pay_st_FeeAmount.getText().toString().trim())); // Insert specific amount if checkbox is selected
                totalPayment += Integer.parseInt(pay_st_FeeAmount.getText().toString().trim());
            } else {
                totalPayment += 00; // Insert default value (500) if checkbox is not selected
            }
        }
        return totalPayment;
    }

    public int TotalPayment() {
        int exmafee = Integer.parseInt(pay_st_exam_fee.getText().toString().trim());
        int admitionFee = Integer.parseInt(pay_st_admision.getText().toString().trim());
        int totalpayments = totalFee() + exmafee + admitionFee;
        return totalpayments;
    }
    private void btn_st_pay_save_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_st_pay_save_MouseClicked

        List<String> months = Arrays.asList("jan", "feb", "mar", "apr", "may", "june", "july", "aug", "sep", "oct", "nov", "decm");

        String sql = "INSERT INTO payment (session, batch_id, class, roll, "
                + "jan, feb, mar, apr, may, june, july, aug, sep, oct, nov,decm,examPayment, admisionFee, totalPay, examCatagory, date) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.getCon().prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(pay_st_session.getText().toString().trim()));
            ps.setString(2, pay_st_batch.getText().toString().trim());
            ps.setString(3, pay_st_class_combo.getSelectedItem().toString().trim());
            ps.setInt(4, Integer.parseInt(pay_st_Roll.getText().toString().trim()));

            for (int i = 5; i <= 16; i++) {
                month = months.get(i - 5);
                if (isChecked(month)) {
                    ps.setInt(i, Integer.parseInt(pay_st_FeeAmount.getText().toString().trim())); // Insert specific amount if checkbox is selected
//                    totalPayment += Integer.parseInt(pay_st_FeeAmount.getText().toString().trim());
                } else {
                    ps.setInt(i, 00); // Insert default value (500) if checkbox is not selected
                }
            }
            ps.setInt(17, Integer.parseInt(pay_st_exam_fee.getText().toString().trim()));
            ps.setInt(18, Integer.parseInt(pay_st_admision.getText().toString().trim()));
            ps.setInt(19, Integer.parseInt(pay_st_TotalFee.getText().toString().trim()));

            ps.setString(20, pay_st_catagory.getSelectedItem().toString());
            // Set other parameters here...
            ps.setDate(21, dateMethod(pay_st_Date.getDate()));

            ps.executeUpdate();
            ps.close();
            con.getCon().close();

            JOptionPane.showMessageDialog(rootPane, "Data is saved");
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data is not saved");
        }
        studentPaymentTable();
        StudentPaymentReset();
         AllMethods();
        all_st_lable.setVisible(true);
        one_st_lable.setVisible(false);
    }//GEN-LAST:event_btn_st_pay_save_MouseClicked
    private boolean isChecked(String month) {
        System.out.println("swich");
        switch (month) {
            case "jan":
                return pay_st_Mon_jan.isSelected();

            case "feb":
                return pay_st_Mon_feb.isSelected();
            case "mar":
                return pay_st_Mon_mar.isSelected();
            case "apr":
                return pay_st_Mon_apr.isSelected();
            case "may":
                return pay_st_Mon_may.isSelected();
            case "june":
                return pay_st_Mon_june.isSelected();
            // Add more cases for other months if needed
            case "july":
                return pay_st_Mon_july.isSelected();
            // Add more cases for other months if needed
            case "aug":
                return pay_st_Mon_aug.isSelected();
            // Add more cases for other months if needed
            case "sep":
                return pay_st_Mon_sep.isSelected();
            // Add more cases for other months if needed
            case "oct":
                return pay_st_Mon_Oct.isSelected();
            // Add more cases for other months if needed
            case "nov":
                return pay_st_Mon_nov.isSelected();
            // Add more cases for other months if needed
            case "decm":
                return pay_st_Mon_dec.isSelected();
            // Add more cases for other months if needed
            default:
                return false;
        }
    }

    private boolean totalFeeSwich(String month) {
        switch (month) {
            case "jan":
                return pay_st_Mon_jan.isSelected();
            case "feb":
                return pay_st_Mon_feb.isSelected();
            case "mar":
                return pay_st_Mon_mar.isSelected();
            case "apr":
                return pay_st_Mon_apr.isSelected();
            case "may":
                return pay_st_Mon_may.isSelected();
            case "june":
                return pay_st_Mon_june.isSelected();
            // Add more cases for other months if needed
            case "july":
                return pay_st_Mon_july.isSelected();
            // Add more cases for other months if needed
            case "aug":
                return pay_st_Mon_aug.isSelected();
            // Add more cases for other months if needed
            case "sep":
                return pay_st_Mon_sep.isSelected();
            // Add more cases for other months if needed
            case "oct":
                return pay_st_Mon_Oct.isSelected();
            // Add more cases for other months if needed
            case "nov":
                return pay_st_Mon_nov.isSelected();
            // Add more cases for other months if needed
            case "decm":
                return pay_st_Mon_dec.isSelected();
            // Add more cases for other months if needed
            default:
                return false;
        }
    }
    String[] StudentPaymentDataShow = {"pay_id", "Session", "Batch_id", "Class", "Roll", "Jan", "Feb", "Mar", "Apr", "May", "Jane", "July", "Aug", "sep",
        "Oct", "Nov", "Dec", "examPay", "admisionFee", "totalPay", "ex_catagory", "date"};

    public void studentPaymentTable() {

        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(StudentPaymentDataShow);
        paymentStudentTable.setModel(modelTable);

        String sql = "Select * from payment";

        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int pay_id = rs.getInt("payment_id");
                int session = rs.getInt("session");
                String batch = rs.getString("batch_id");
                String classs = rs.getString("class");
                int roll = rs.getInt("roll");
                int jan = rs.getInt("jan");
                int feb = rs.getInt("feb");
                int mar = rs.getInt("mar");
                int apr = rs.getInt("apr");
                int may = rs.getInt("may");
                int june = rs.getInt("june");
                int july = rs.getInt("july");
                int aug = rs.getInt("aug");
                int sep = rs.getInt("sep");
                int oct = rs.getInt("oct");
                int nov = rs.getInt("nov");
                int decm = rs.getInt("decm");
                int examPay = rs.getInt("examPayment");
                int admisionFree = rs.getInt("admisionFee");
                int totalpay = rs.getInt("totalpay");
                String catagory = rs.getString("examcatagory");
                String date = rs.getString("date");

                modelTable.addRow(new Object[]{pay_id, session, batch, classs, roll, jan, feb, mar, apr, may, june, july, aug,
                    sep, oct, nov, decm, examPay, admisionFree, totalpay, catagory, date});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void pay_st_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_sessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_sessionActionPerformed

    private void pay_st_batchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_batchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_batchActionPerformed

    private void pay_st_TotalFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_TotalFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_TotalFeeActionPerformed

    private void pay_st_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_idActionPerformed

    private void pay_st_RollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_RollActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_RollActionPerformed

    private void btn_sd_delete_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sd_delete_MouseClicked

        String sql = "Delete FROM school WHERE Session_year=? and batch_id=? and classs=? and student_Roll=?";

        try {
            ps = con.getCon().prepareStatement(sql);

            int year = Integer.parseInt(sd_session.getText().toString().trim());
            ps.setInt(1, year);

            ps.setString(2, sd_batchId.getText().toString().trim());
            ps.setString(3, sd_class_combo.getSelectedItem().toString().trim());
            ps.setString(4, sd_studentID.getText().toString().trim());

            int rowsDeleted = ps.executeUpdate();
            ps.close();
            con.getCon().close();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data is deleted");
                resultTable();
                // Optionally, you can reset the input fields after deletion
                resultReset();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No records found for deletion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data deletion failed");
        }
        StudentDetailsReset();
        TotalStudentReport_6();
        TotalStudentReport_7();
        TotalStudentReport_8();
        TotalStudentReport_9();
        TotalStudentReport_10();
    }//GEN-LAST:event_btn_sd_delete_MouseClicked

    private void btnTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTeacherActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnStudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseEntered
        // TODO add your handling code here:
        btnStudent.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnStudentMouseEntered

    private void btnStudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseExited
        // TODO add your handling code here:
        btnStudent.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnStudentMouseExited

    private void btnTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTeacherMouseEntered
        // TODO add your handling code here:
        btnTeacher.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnTeacherMouseEntered

    private void btnTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTeacherMouseExited
        // TODO add your handling code here:
        btnTeacher.setBackground(new Color(204, 255, 204));

    }//GEN-LAST:event_btnTeacherMouseExited

    private void btnExamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseEntered
        // TODO add your handling code here:
        btnExam.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnExamMouseEntered

    private void btnExamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseExited
        // TODO add your handling code here:
        btnExam.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnExamMouseExited

    private void btnPaymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseEntered
        // TODO add your handling code here:
        btnPayment.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnPaymentMouseEntered

    private void btnPaymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentMouseExited
        // TODO add your handling code here:
        btnPayment.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnPaymentMouseExited

    private void btnStaff1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaff1MouseEntered
        // TODO add your handling code here:
        btnStaff1.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnStaff1MouseEntered

    private void btnStaff1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaff1MouseExited
        // TODO add your handling code here:
        btnStaff1.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnStaff1MouseExited

    private void exitTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTabMouseEntered
        // TODO add your handling code here:
        exitTab.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_exitTabMouseEntered

    private void exitTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTabMouseExited
        // TODO add your handling code here:
        exitTab.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_exitTabMouseExited

    private void St_btn_addmitionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_addmitionMouseEntered
        // TODO add your handling code here:
        St_btn_addmition.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_St_btn_addmitionMouseEntered

    private void St_btn_addmitionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_addmitionMouseExited
        // TODO add your handling code here:
        St_btn_addmition.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_St_btn_addmitionMouseExited

    private void St_btn_DeatilsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_DeatilsMouseEntered
        // TODO add your handling code here:
        St_btn_Deatils.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_St_btn_DeatilsMouseEntered

    private void St_btn_DeatilsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_St_btn_DeatilsMouseExited
        // TODO add your handling code here:
        St_btn_Deatils.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_St_btn_DeatilsMouseExited

    private void st_btn_result_inMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_inMouseEntered
        // TODO add your handling code here:
        st_btn_result_in.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_st_btn_result_inMouseEntered

    private void st_btn_result_inMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_inMouseExited
        // TODO add your handling code here:
        st_btn_result_in.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_st_btn_result_inMouseExited

    private void st_btn_result_outMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_outMouseEntered
        // TODO add your handling code here:
        st_btn_result_out.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_st_btn_result_outMouseEntered

    private void st_btn_result_outMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_st_btn_result_outMouseExited
        // TODO add your handling code here:
        st_btn_result_out.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_st_btn_result_outMouseExited

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:

        r_total.setText(totalNumber() + "");
        r_avarage.setText(avarageNumberResult() + "");
//        r_gpa.setText(GPAResult() + "");
//        r_grade.setText(GredeResult());

        PassFaildResult();


    }//GEN-LAST:event_jPanel21MouseClicked

    private void r_passFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_passFailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_passFailActionPerformed

    private void btn_R_RollBack_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_R_RollBack_MouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_R_RollBack_MouseClicked

    //    this mathod can query school table and if school table has data then the result input field are  visiable---------------

    private void r_sessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_r_sessionKeyReleased
        String sql = "select full_name from school where Session_year=? and batch_id=? and classs=? and student_Roll=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            System.out.println("2");
            int year = Integer.parseInt(r_session.getText().toString().trim());
            ps.setInt(1, year);

//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, r_batch_id.getText().toString().trim());
            ps.setString(3, r_class.getSelectedItem().toString().trim());
            ps.setString(4, r_St_Roll.getText().toString().trim());
            rs = ps.executeQuery();
            if (rs.next()) {
                visiableResultInput();

            } else {
                InvisiableResultInput();
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_r_sessionKeyReleased

    private void pay_st_FeeAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_st_FeeAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pay_st_FeeAmountActionPerformed

    private void btn_st_pay_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_st_pay_resetMouseClicked
        // TODO add your handling code here:
        StudentPaymentReset();
    }//GEN-LAST:event_btn_st_pay_resetMouseClicked

    String[] OneStudentPaymentDataShow = {"pay_id", "Session", "Batch_id", "Class", "Roll", "Jan", "Feb", "Mar", "Apr", "May", "Jane", "July", "Aug", "sep",
        "Oct", "Nov", "Dec", "examPay", "admisionFee", "totalPay", "ex_catagory", "date"};

    public void OneStudentPaymentTable() {

        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(StudentPaymentDataShow);
        paymentStudentTable.setModel(modelTable);

        String sql = "select * from payment where session=? and batch_id=? and class=? and roll=?";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as needed

            ps = con.getCon().prepareStatement(sql);

            System.out.println("2");
            int year = Integer.parseInt(pay_st_session.getText().toString().trim());
            ps.setInt(1, year);

//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, pay_st_batch.getText().toString().trim());
            ps.setString(3, pay_st_class_combo.getSelectedItem().toString().trim());
            ps.setString(4, pay_st_Roll.getText().toString().trim());

            rs = ps.executeQuery();

            while (rs.next()) {
                int pay_id = rs.getInt("payment_id");
                int session = rs.getInt("session");
                String batch = rs.getString("batch_id");
                String classs = rs.getString("class");
                int roll = rs.getInt("roll");
                int jan = rs.getInt("jan");
                int feb = rs.getInt("feb");
                int mar = rs.getInt("mar");
                int apr = rs.getInt("apr");
                int may = rs.getInt("may");
                int june = rs.getInt("june");
                int july = rs.getInt("july");
                int aug = rs.getInt("aug");
                int sep = rs.getInt("sep");
                int oct = rs.getInt("oct");
                int nov = rs.getInt("nov");
                int decm = rs.getInt("decm");
                int examPay = rs.getInt("examPayment");
                int admisionFree = rs.getInt("admisionFee");
                int totalpay = rs.getInt("totalpay");
                String catagory = rs.getString("examcatagory");
                String date = rs.getString("date");

                modelTable.addRow(new Object[]{pay_id, session, batch, classs, roll, jan, feb, mar, apr, may, june, july, aug,
                    sep, oct, nov, decm, examPay, admisionFree, totalpay, catagory, date});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void pay_st_RollFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pay_st_RollFocusLost
        // TODO add your handling code here:
        OneStudentPaymentTable();
        all_st_lable.setVisible(false);
        one_st_lable.setVisible(true);

    }//GEN-LAST:event_pay_st_RollFocusLost

    private void Btn_OneStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_OneStudentMouseClicked
        // TODO add your handling code here:
        OneStudentPaymentTable();
        all_st_lable.setVisible(false);
        one_st_lable.setVisible(true);

    }//GEN-LAST:event_Btn_OneStudentMouseClicked

    private void Btn_AllStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_AllStudentMouseClicked
        // TODO add your handling code here:
        studentPaymentTable();
        all_st_lable.setVisible(true);
        one_st_lable.setVisible(false);
    }//GEN-LAST:event_Btn_AllStudentMouseClicked

    private void Btn_AllStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AllStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_AllStudentActionPerformed

    private void btn_st_pay_AddCurt_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_st_pay_AddCurt_ActionPerformed
        // TODO add your handling code here:

        Payment_student_recipt.append("\tStudent Copy\n"
                + "   LALBAG MODEL SCHOOL AND COLLAGE\n"
                + "       DHAKA-1213, phone :+2514558\n\n"
                + "Session  :" + pay_st_session.getText() + "\t          batch id  :" + pay_st_batch.getText() + "\n"
                + "Class      :" + pay_st_class_combo.getSelectedItem() + "\t          Roll No   :" + pay_st_Roll.getText() + "\n"
                //                + "Date :" + pay_st_Date.getDate().toString() + "\n\n"
                + "      Descript\t\tTk\n"
                + "-----------------------------------------------------------------\n"
                + "Admision Fee\t\t" + pay_st_admision.getText() + "\n"
                + "Monthly Fee\t\t" + TotalPayment() + "\n"
                + "Exam Fee\t\t" + pay_st_exam_fee.getText() + "\n"
                + "                                  -------------------------------\n"
                + "Total Payment\t\t" + pay_st_TotalFee.getText() + "\n"
        );
    }//GEN-LAST:event_btn_st_pay_AddCurt_ActionPerformed

    private void btn_st_pay_AddCurt_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_st_pay_AddCurt_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_st_pay_AddCurt_MouseClicked

    private void pay_st_RollKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pay_st_RollKeyReleased
        // TODO add your handling code here:

        OneStudentPaymentTable();
        all_st_lable.setVisible(false);
        one_st_lable.setVisible(true);
    }//GEN-LAST:event_pay_st_RollKeyReleased

    private void pay_st_FeeAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pay_st_FeeAmountFocusLost
        // TODO add your handling code here:
        pay_st_TotalFee.setText(TotalPayment() + "");
    }//GEN-LAST:event_pay_st_FeeAmountFocusLost

    private void btn_te_Image_set_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_te_Image_set_MouseClicked
        // TODO add your handling code here:
        JFileChooser browseImageFile = new JFileChooser();

        // for filtering image extension
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);

        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            String selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, selectedImagePath);

            // for displaying image on jlabel
            ImageIcon li = new ImageIcon(selectedImagePath);

            // for resize image in jlabel
            Image image = li.getImage().getScaledInstance(te_Image_set_lable.getWidth(), te_Image_set_lable.getHeight(), Image.SCALE_SMOOTH);

            btn_te_Image_set_.setIcon(new ImageIcon(image));
        }


    }//GEN-LAST:event_btn_te_Image_set_MouseClicked

    private void btn_Save_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Save_1MouseClicked
        // TODO add your handling code here:
        String sql = "insert into School ( full_name,fathers_name,"
                + "fa_te_phone,mothers_name,date_of_birth,age,gender,religion,nationality,"
                + "email,blood_group,birth_certificate,present_Address,permanent_Address,catagory,qualification,"
                + "joiningdate,designation,Session_year,batch_id,classs,Student_Roll,picture)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String fullName = t_teacher_first1.getText().trim() + " " + t_teacher_last1.getText().toString().trim();

        try {

            ps = con.getCon().prepareStatement(sql);

            ps.setString(1, fullName);
            ps.setString(2, t_father_N1.getText().toString().trim());
            ps.setString(3, t_contact_number.getText().toString().trim());
            ps.setString(4, t_Mother_N1.getText().toString().trim());
            ps.setDate(5, dateMethod(t_dateOfBirth1.getDate()));

            ps.setInt(6, Integer.parseInt(t_age1.getText().toString().trim()));
            ps.setString(7, t_gender_combo1.getSelectedItem().toString().trim());
            ps.setString(8, t_religion1.getText().toString().trim());
            ps.setString(9, t_National1.getText().toString().trim());
            ps.setString(10, t_email1.getText().toString().trim());
            ps.setString(11, t_BloodGroup1.getSelectedItem().toString().trim());
            ps.setInt(12, Integer.parseInt(t_national_id.getText().toString().trim()));
            ps.setString(13, t_presentAddress1.getText().toString().trim());
            ps.setString(14, t_parmanent1.getText().toString().trim());

            if (t_sf_catagory.getSelectedIndex() == 1) {
                ps.setString(15, t_ctagory.getText().toString().trim());

            } else if (t_sf_catagory.getSelectedIndex() == 2) {
                ps.setString(15, sf_ctagory.getText().toString().trim());

            } else {
                JOptionPane.showMessageDialog(rootPane, "Plese select Catagory one");
                t_sf_catagory.requestFocus();
            }
//            ps.setString(15, t_sf_catagory.getSelectedItem().toString().trim());
            ps.setString(16, t_qualification.getSelectedItem().toString().trim());

            ps.setDate(17, dateMethod(t_joiningDate.getDate()));

            ps.setString(18, t_designationcombo1.getSelectedItem().toString().trim());

            ps.setInt(19, 0);
            ps.setString(20, "***");
            ps.setString(21, "***");
            ps.setInt(22, 0);
            ps.setString(23, te_Image_set_lable.getText());
            ps.executeUpdate();
            ps.close();
            con.getCon().close();

            JOptionPane.showMessageDialog(rootPane, "data is save");

            teacherTable();
            teacherAddReset();
             AllMethods();

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data is not save");
        }


    }//GEN-LAST:event_btn_Save_1MouseClicked

    private void btn_AdmiosionReset_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AdmiosionReset_1MouseClicked
        // TODO add your handling code here:

        teacherAddReset();
        teacherTable();
    }//GEN-LAST:event_btn_AdmiosionReset_1MouseClicked

    private void t_email1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_email1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_email1ActionPerformed

    private void t_teacher_last1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_teacher_last1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_teacher_last1ActionPerformed

    private void t_national_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_national_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_national_idActionPerformed

    private void t_contact_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_contact_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_contact_numberActionPerformed

    private void t_religion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_religion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_religion1ActionPerformed

    private void btn_td_Print_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_td_Print_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_td_Print_ActionPerformed

    private void btn_td_Print_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_td_Print_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_td_Print_MouseClicked

    private void btn_td_delete_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_td_delete_MouseClicked
        // TODO add your handling code here:      
        DeleteTeacherRecord();
    }//GEN-LAST:event_btn_td_delete_MouseClicked

    private void btn_td_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_td_UpdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_td_UpdateMouseClicked

    private void td_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_td_idMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_td_idMouseClicked

    private void btn_td_View_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_td_View_MouseClicked
        // TODO add your handling code here:

        String sql = "select * from school where id=?";

        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, td_id.getText().toString().trim());
            rs = ps.executeQuery();
            while (rs.next()) {
                td_te_name.setText(rs.getString("full_name"));
                td_gender_combo.setText(rs.getString("gender"));
                td_Designation_view.setText(rs.getString("designation"));
                td_fullName1.setText(rs.getString("full_name"));
                td_fatherName.setText(rs.getString("fathers_name"));
                td_motherName.setText(rs.getString("mothers_name"));
                td_ContactNumber.setText(rs.getString("fa_te_phone"));
                td_Dob.setText(rs.getString("date_of_birth"));
                td_JoiningDate.setText(rs.getString("joiningdate"));
                td_presentA.setText(rs.getString("present_Address"));
                td_parmanentA.setText(rs.getString("permanent_Address"));
                td_Age.setText(rs.getString("age"));
                td_email.setText(rs.getString("email"));
                td_nationality.setText(rs.getString("nationality"));
                td_national_id.setText(rs.getString("birth_certificate"));
                td_blood.setText(rs.getString("blood_group"));
                td_religion.setText(rs.getString("religion"));
            }
            rs.close();
            ps.close();
            con.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_td_View_MouseClicked

    private void btn_td_ResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_td_ResetMouseClicked
        // TODO add your handling code here:             
        td_id.setText(null);
        td_te_name.setText(null);
        td_gender_combo.setText(null);
        td_Designation_view.setText(null);
        td_fullName1.setText(null);
        td_fatherName.setText(null);
        td_motherName.setText(null);
        td_ContactNumber.setText(null);
        td_Dob.setText(null);
        td_JoiningDate.setText(null);
        td_presentA.setText(null);
        td_parmanentA.setText(null);
        td_Age.setText(null);
        td_email.setText(null);
        td_nationality.setText(null);
        td_national_id.setText(null);
        td_blood.setText(null);
        td_religion.setText(null);

    }//GEN-LAST:event_btn_td_ResetMouseClicked

    public void DeleteTeacherRecord() {
        String deleteSql = "DELETE FROM School WHERE id = ? and catagory like 'T%' or  catagory like 'SF%'";
        try {
            PreparedStatement deleteStatement = con.getCon().prepareStatement(deleteSql);
            deleteStatement.setInt(1, Integer.parseInt(t_Id.getText().toString()));
            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record with ID " + t_Id + " has been deleted successfully.");
            } else {
                System.out.println("No record found with ID " + t_Id + ".");
            }
            deleteStatement.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data is  Delete");

            teacherTable();
            teacherAddReset();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Data is not Delete");
            // Handle any SQL exceptions that may occur during the deletion process
        }
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        DeleteTeacherRecord();

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        String sql = "UPDATE School SET full_name=?, fathers_name=?, fa_te_phone=?, mothers_name=?, "
                + "date_of_birth=?, age=?, gender=?, religion=?, nationality=?, email=?, blood_group=?, birth_certificate=?, "
                + "present_Address=?, permanent_Address=?, catagory=?, qualification=?, designation=?, joiningdate=? WHERE id=?";
        String fullName = t_teacher_first1.getText().trim() + " " + t_teacher_last1.getText().toString().trim();

        try {
            PreparedStatement ps = con.getCon().prepareStatement(sql);

            ps.setString(1, fullName);
            ps.setString(2, t_father_N1.getText().toString().trim());
            ps.setString(3, t_contact_number.getText().toString().trim());
            ps.setString(4, t_Mother_N1.getText().toString().trim());
            ps.setDate(5, dateMethod(t_dateOfBirth1.getDate()));

            ps.setInt(6, Integer.parseInt(t_age1.getText().toString().trim()));
            ps.setString(7, t_gender_combo1.getSelectedItem().toString().trim());
            ps.setString(8, t_religion1.getText().toString().trim());
            ps.setString(9, t_National1.getText().toString().trim());
            ps.setString(10, t_email1.getText().toString().trim());
            ps.setString(11, t_BloodGroup1.getSelectedItem().toString().trim());
            ps.setInt(12, Integer.parseInt(t_national_id.getText().toString().trim()));
            ps.setString(13, t_presentAddress1.getText().toString().trim());
            ps.setString(14, t_parmanent1.getText().toString().trim());
            ps.setString(15, t_sf_catagory.getSelectedItem().toString());
            ps.setString(16, t_qualification.getSelectedItem().toString().trim());
            ps.setString(17, t_designationcombo1.getSelectedItem().toString().trim());
            ps.setDate(18, dateMethod(t_joiningDate.getDate()));
            ps.setInt(19, Integer.parseInt(t_Id.getText().toString().trim()));

//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Record with ID " + t_Id + " has been updated successfully.");
//            } else {
//                System.out.println("No record found with ID " + t_Id + ".");
//            }
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            teacherTable();
            teacherAddReset();
             AllMethods();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions that may occur during the update process
        }


    }//GEN-LAST:event_jButton5MouseClicked

    private void t_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_searchActionPerformed

    public void teacherStaffScarch() {
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(teacherTableDataShow);
        t_table.setModel(modelTable);

        String sql = "Select * from school  where id=? and catagory like 'T%' or catagory like 'SF%' ";

        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, t_search.getText().toString().trim());
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("full_name");
                String father = rs.getString("fathers_name");
                String mother = rs.getString("mothers_name");
                String contact = rs.getString("fa_te_phone");
                String dob = rs.getString("date_of_birth");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String riligion = rs.getString("religion");
                String nation = rs.getString("nationality");
                String email = rs.getString("email");
                String blood = rs.getString("blood_group");
                int naid = rs.getInt("birth_certificate");
                String present = rs.getString("present_Address");
                String parmanent = rs.getString("permanent_Address");
                String deg = rs.getString("designation");
                String join = rs.getString("joiningdate");
                String qualifi = rs.getString("qualification");
                String catagory = rs.getString("catagory");
                modelTable.addRow(new Object[]{id, fullname, father, mother, contact, dob, gender, age, riligion, nation, email, blood, naid, present, parmanent, deg, join, qualifi, catagory});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void btn_t_ta_search_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_t_ta_search_MouseClicked
        // TODO add your handling code here:
        teacherStaffScarch();


    }//GEN-LAST:event_btn_t_ta_search_MouseClicked

    private void t_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_tableMouseClicked
        // TODO add your handling code here:
        int row = t_table.getSelectedRow();

        String id = t_table.getModel().getValueAt(row, 0).toString();
        String name = t_table.getModel().getValueAt(row, 1).toString();
        String father = t_table.getModel().getValueAt(row, 2).toString();
        String mother = t_table.getModel().getValueAt(row, 3).toString();
        String phone = t_table.getModel().getValueAt(row, 4).toString();
        String dob = t_table.getModel().getValueAt(row, 5).toString();
        String gender = t_table.getModel().getValueAt(row, 6).toString();
        String age = t_table.getModel().getValueAt(row, 7).toString();
        String religion = t_table.getModel().getValueAt(row, 8).toString();
        String nalional = t_table.getModel().getValueAt(row, 9).toString();
        String email = t_table.getModel().getValueAt(row, 10).toString();
        String blood = t_table.getModel().getValueAt(row, 11).toString();
        String n_id = t_table.getModel().getValueAt(row, 12).toString();
        String present = t_table.getModel().getValueAt(row, 13).toString();
        String parmanent = t_table.getModel().getValueAt(row, 14).toString();
        String designa = t_table.getModel().getValueAt(row, 15).toString();
        String joinDate = t_table.getModel().getValueAt(row, 16).toString();
        String quality = t_table.getModel().getValueAt(row, 17).toString();
        String catagory = t_table.getModel().getValueAt(row, 18).toString();

//        String date = tableSales.getModel().getValueAt(row, 10).toString();
        t_Id.setText(id);

        t_teacher_first1.setText(name);
        t_father_N1.setText(father);
        t_Mother_N1.setText(mother);
        t_contact_number.setText(phone);
        t_dateOfBirth1.setDate(convertStringToDate(dob));
        t_gender_combo1.setSelectedItem(gender);
        t_age1.setText(age);
        t_religion1.setText(religion);
        t_National1.setText(nalional);
        t_email1.setText(email);
        t_BloodGroup1.setSelectedItem(blood);
        t_national_id.setText(n_id);
        t_presentAddress1.setText(present);
        t_parmanent1.setText(parmanent);
        t_designationcombo1.setSelectedItem(designa);
        t_joiningDate.setDate(convertStringToDate(joinDate));
        t_qualification.setSelectedItem(quality);
        sf_ctagory.setText(catagory);


    }//GEN-LAST:event_t_tableMouseClicked

    private void ads_rollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ads_rollActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ads_rollActionPerformed

    private void ads_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ads_sessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ads_sessionActionPerformed

    private void ads_batchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ads_batchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ads_batchActionPerformed

    private void btn_ads_view_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ads_view_MouseClicked
        // TODO add your handling code here:
        String sql = "select full_name,fathers_name, batch_id,classs,student_Roll,Session_year from school where Session_year=? and batch_id=? and classs=? and student_Roll=?";

        try {
            // Customize the date format as needed

            ps = con.getCon().prepareStatement(sql);

            System.out.println("2");
            int year = Integer.parseInt(ads_session.getText().toString().trim());
            ps.setInt(1, year);

//            ps.setInt(1, Integer.parseInt("Session_year"));
            ps.setString(2, ads_batch.getText().toString().trim());
            ps.setString(3, ads_class.getSelectedItem().toString().trim());
            ps.setString(4, ads_roll.getText().toString().trim());

            rs = ps.executeQuery();

            while (rs.next()) {
                ad_name.setText(rs.getString("full_name"));
                ad_class.setText(rs.getString("classs"));
                ad_roll.setText(rs.getString("student_Roll"));
                ad_batch.setText(rs.getString("batch_id"));
                ad_session.setText(rs.getString("Session_year"));
                ad_father.setText(rs.getString("fathers_name"));

            }

            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btn_ads_view_MouseClicked

    private void btn_ads_Reset_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ads_Reset_MouseClicked
        // TODO add your handling code here:
        ad_name.setText("");
        ad_class.setText("");
        ad_roll.setText("");
        ad_batch.setText("");
        ad_session.setText("");
        ad_father.setText("");
        ads_session.setText("");
        ads_batch.setText("");
        ads_roll.setText("");
        ads_class.setSelectedIndex(0);


    }//GEN-LAST:event_btn_ads_Reset_MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.75, 0.60);
                admit_cardPanal.print(g2);
                return Printable.PAGE_EXISTS;
            }

        });

        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton7MouseClicked

    String salary = "";
    int totalSalary = 0;
    int bonus = 0;

    public int totalsalarys() {
        totalSalary = 0;
        List<String> months = Arrays.asList("jan", "feb", "mar", "apr", "may", "june", "july", "aug", "sep", "oct", "nov", "decm");
        for (int i = 0; i <= 11; i++) {
            salary = months.get(i);
            if (totalsalarySwich(salary)) {
                System.out.println(i);
//                    ps.setInt(i, Integer.parseInt(pay_st_FeeAmount.getText().toString().trim())); // Insert specific amount if checkbox is selected
                totalSalary += Integer.parseInt(pay_te_salary.getText().toString().trim());

            } else {
                totalSalary += 00; // Insert default value (500) if checkbox is not selected

            }
        }
        return totalSalary;

    }

    public int TotalsalaryTecher() {

        int bonus = Integer.parseInt(pay_te_bonus.getText().toString().trim());

        int totalsalaryTeacher = totalsalarys() + bonus;
        return totalsalaryTeacher;
    }

    private boolean teacherChecked(String month) {
        System.out.println("swich");
        switch (month) {
            case "jan":
                return pay_te_mon_jan.isSelected();

            case "feb":
                return pay_te_mon_feb.isSelected();

            case "mar":
                return pay_te_mon_mar.isSelected();
            case "apr":
                return pay_te_mon_apr.isSelected();
            case "may":
                return pay_te_mon_may.isSelected();
            case "june":
                return pay_te_mon_june.isSelected();
            // Add more cases for other months if needed
            case "july":
                return pay_te_mon_july.isSelected();
            // Add more cases for other months if needed
            case "aug":
                return pay_te_mon_aug.isSelected();
            // Add more cases for other months if needed
            case "sep":
                return pay_te_mon_sep.isSelected();
            // Add more cases for other months if needed
            case "oct":
                return pay_te_mon_oct.isSelected();
            // Add more cases for other months if needed
            case "nov":
                return pay_te_mon_nov.isSelected();
            // Add more cases for other months if needed
            case "decm":
                return pay_te_mon_dec.isSelected();
            // Add more cases for other months if needed
            default:
                return false;
        }
    }

    private boolean totalsalarySwich(String month) {
        switch (month) {
            case "jan":
                return pay_te_mon_jan.isSelected();
            case "feb":
                return pay_te_mon_feb.isSelected();
            case "mar":
                return pay_st_Mon_mar.isSelected();
            case "apr":
                return pay_st_Mon_apr.isSelected();
            case "may":
                return pay_st_Mon_may.isSelected();
            case "june":
                return pay_te_mon_june.isSelected();
            // Add more cases for other months if needed
            case "july":
                return pay_te_mon_july.isSelected();
            // Add more cases for other months if needed
            case "aug":
                return pay_te_mon_aug.isSelected();
            // Add more cases for other months if needed
            case "sep":
                return pay_te_mon_sep.isSelected();
            // Add more cases for other months if needed
            case "oct":
                return pay_te_mon_oct.isSelected();
            // Add more cases for other months if needed
            case "nov":
                return pay_te_mon_nov.isSelected();
            // Add more cases for other months if needed
            case "decm":
                return pay_te_mon_dec.isSelected();
            // Add more cases for other months if needed
            default:
                return false;
        }

    }

    String[] teachersalaryDataShow = {"pay_id", "teacher_id", "Name", "Jan", "Feb", "Mar", "Apr", "May", "Jane", "July", "Aug", "sep",
        "Oct", "Nov", "Dec", "bonus", "totalPay", "discription", "date"};

    public void teacherssalarytable() {

        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(teachersalaryDataShow);
        teachersTable.setModel(modelTable);

        String sql = "Select * from payment where class like 'T%'";

        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int pay_id = rs.getInt("payment_id");
                int te_id = rs.getInt("teacher_staff_id");
                String name = rs.getString("Name");
                int jan = rs.getInt("jan");
                int feb = rs.getInt("feb");
                int mar = rs.getInt("mar");
                int apr = rs.getInt("apr");
                int may = rs.getInt("may");
                int june = rs.getInt("june");
                int july = rs.getInt("july");
                int aug = rs.getInt("aug");
                int sep = rs.getInt("sep");
                int oct = rs.getInt("oct");
                int nov = rs.getInt("nov");
                int decm = rs.getInt("decm");

                int bonus = rs.getInt("bonus");
                int totalpay = rs.getInt("totalpay");
                String discript = rs.getString("examcatagory");
                String date = rs.getString("date");

                modelTable.addRow(new Object[]{pay_id, te_id, name, jan, feb, mar, apr, may, june, july, aug,
                    sep, oct, nov, decm, bonus, totalpay, discript, date});
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btn_save_te_pay_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_save_te_pay_MouseClicked
        // TODO add your handling code here:

        List<String> months = Arrays.asList("jan", "feb", "mar", "apr", "may", "june", "july", "aug", "sep", "oct", "nov", "decm");

        String sql = "INSERT INTO payment (session, batch_id, class, roll, "
                + "teacher_staff_id, Name,jan, feb, mar, apr, may, june, july, aug, sep, oct, nov,decm,examPayment, admisionFee,bonus, totalPay, examCatagory, date) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, 00);
            ps.setString(2, "***");
            ps.setString(3, "T");
            ps.setInt(4, 00);
            ps.setInt(5, Integer.parseInt(pay_te_id.getText().toString()));
            ps.setString(6, pay_te_name.getText().toString());

//            on the above month and months are two variable are use swich case ------------------------------------
            for (int i = 7; i <= 18; i++) {
                salary = months.get(i - 7);
                if (teacherChecked(salary)) {
                    ps.setInt(i, Integer.parseInt(pay_te_salary.getText().toString().trim())); // Insert specific amount if checkbox is selected
//                    totalPayment += Integer.parseInt(pay_st_FeeAmount.getText().toString().trim());
                } else {
                    ps.setInt(i, 00); // Insert default value (500) if checkbox is not selected
                }
            }
            ps.setInt(19, 00);
            ps.setInt(20, 00);

            ps.setInt(21, Integer.parseInt(pay_te_bonus.getText().toString().trim()));
            ps.setInt(22, Integer.parseInt(pay_te_Total.getText().toString().trim()));

            ps.setString(23, pay_te_descript.getText().toString());
            // Set other parameters here...
            ps.setDate(24, dateMethod(pay_te_date.getDate()));

            ps.executeUpdate();
            ps.close();
            con.getCon().close();

            JOptionPane.showMessageDialog(rootPane, "Data is saved");
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data is not saved");
        }
        teacherssalarytable();
        teachersalaryReset();
         AllMethods();
//        all_st_lable.setVisible(true);
//        one_st_lable.setVisible(false);
    }//GEN-LAST:event_btn_save_te_pay_MouseClicked

    private void pay_te_TotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pay_te_TotalMouseClicked
        // TODO add your handling code here:
        pay_te_Total.setText(TotalsalaryTecher() + "");
//        int total=totalSalary;
//        int bon=bonus;
//        total=Integer.parseInt(pay_te_Total.toString());
//        bon=Integer.parseInt(pay_te_bonus.toString());

    }//GEN-LAST:event_pay_te_TotalMouseClicked

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked

        teachersalaryReset();
    }//GEN-LAST:event_jButton19MouseClicked

    private void pay_te_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pay_te_idKeyReleased
        String sql = "select full_name from school where catagory like 'T%' and id=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(pay_te_id.getText().toString().trim()));
            rs = ps.executeQuery();
            while (rs.next()) {
                pay_te_name.setText(rs.getString("full_name"));
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pay_te_idKeyReleased


    private void pay_te_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pay_te_idKeyPressed
        pay_te_name.setText("");
    }//GEN-LAST:event_pay_te_idKeyPressed

    private void btnClassRutineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClassRutineMouseClicked
        mainTab.setSelectedIndex(8);
        ClassRutineTab.setSelectedIndex(0);

    }//GEN-LAST:event_btnClassRutineMouseClicked

    private void btnClassRutineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClassRutineMouseEntered
        btnClassRutine.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btnClassRutineMouseEntered

    private void btnClassRutineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClassRutineMouseExited
        btnClassRutine.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btnClassRutineMouseExited

    private void btnExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExamActionPerformed

    }//GEN-LAST:event_btnExamActionPerformed

    private void btn_rutine_home_mMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_mMouseClicked
        ClassRutineTab.setSelectedIndex(1);
    }//GEN-LAST:event_btn_rutine_home_mMouseClicked

    private void btn_rutine_home_VMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_VMouseClicked
        // TODO add your handling code here:   
        ClassRutineTab.setSelectedIndex(2);
    }//GEN-LAST:event_btn_rutine_home_VMouseClicked

    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
        String sql = "INSERT INTO classrutine (session, classs,day, pre_1_sub, pre_1_tea, pre_1_time,pre_2_sub, pre_2_tea,"
                + " pre_2_time,pre_3_sub, pre_3_tea, pre_3_time,pre_4_sub, pre_4_tea, pre_4_time,pre_5_sub, pre_5_tea, "
                + "pre_5_time,pre_6_sub, pre_6_tea, pre_6_time,pre_7_sub, pre_7_tea, pre_7_time)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(R_session.getText().toString().trim()));
            ps.setString(2, R_class.getSelectedItem().toString());
            ps.setString(3, R_day.getSelectedItem().toString());
            ps.setString(4, R_pre1_sub.getSelectedItem().toString());
            ps.setString(5, R_pre1_tea.getSelectedItem().toString());
            ps.setString(6, R_pre1_time.getText().toString());
            ps.setString(7, R_pre2_sub.getSelectedItem().toString());
            ps.setString(8, R_pre2_tea.getSelectedItem().toString());
            ps.setString(9, R_pre2_time.getText().toString());
            ps.setString(10, R_pre3_sub.getSelectedItem().toString());
            ps.setString(11, R_pre3_tea.getSelectedItem().toString());
            ps.setString(12, R_pre3_time.getText().toString());
            ps.setString(13, R_pre4_sub.getSelectedItem().toString());
            ps.setString(14, R_pre4_tea.getSelectedItem().toString());
            ps.setString(15, R_pre4_time.getText().toString());
            ps.setString(16, R_pre5_sub.getSelectedItem().toString());
            ps.setString(17, R_pre5_tea.getSelectedItem().toString());
            ps.setString(18, R_pre5_time.getText().toString());
            ps.setString(19, R_pre6_sub.getSelectedItem().toString());
            ps.setString(20, R_pre6_tea.getSelectedItem().toString());
            ps.setString(21, R_pre6_time.getText().toString());
            ps.setString(22, R_pre7_sub.getSelectedItem().toString());
            ps.setString(23, R_pre7_tea.getSelectedItem().toString());
            ps.setString(24, R_pre7_time.getText().toString());
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
//            RutineInputReset();
            JOptionPane.showMessageDialog(rootPane, "Data is Save");
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Data is not Save");
        }
    }//GEN-LAST:event_jButton27MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        String sql = "select * from classrutine where session=? and classs=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(p_sessoion.getText().toString()));
            ps.setString(2, p__class.getSelectedItem().toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("day").toString().equalsIgnoreCase("sunday")) {
                    P1_s_sun.setText(rs.getString("pre_1_sub"));
                    P2_s_sun.setText(rs.getString("pre_2_sub"));
                    P3_s_sun.setText(rs.getString("pre_3_sub"));
                    P4_s_sun.setText(rs.getString("pre_4_sub"));
                    P5_s_sun.setText(rs.getString("pre_5_sub"));
                    P6_s_sun.setText(rs.getString("pre_6_sub"));
                    P7_s_sun.setText(rs.getString("pre_7_sub"));

                    p1_time.setText(rs.getString("pre_1_time"));
                    p2_time.setText(rs.getString("pre_2_time"));
                    p3_time.setText(rs.getString("pre_3_time"));
                    p4_time.setText(rs.getString("pre_4_time"));
                    p5_time.setText(rs.getString("pre_5_time"));
                    p6_time.setText(rs.getString("pre_6_time"));
                    p7_time.setText(rs.getString("pre_7_time"));

                }
                if (rs.getString("day").toString().equalsIgnoreCase("Monday")) {
                    P1_s_mon.setText(rs.getString("pre_1_sub"));
                    P2_s_mon.setText(rs.getString("pre_2_sub"));
                    P3_s_mon.setText(rs.getString("pre_3_sub"));
                    P4_s_mon.setText(rs.getString("pre_4_sub"));
                    P5_s_mon.setText(rs.getString("pre_5_sub"));
                    P6_s_mon.setText(rs.getString("pre_6_sub"));
                    P7_s_mon.setText(rs.getString("pre_7_sub"));
                }
                if (rs.getString("day").toString().equalsIgnoreCase("Tuesday")) {
                    P1_s_tues.setText(rs.getString("pre_1_sub"));
                    P2_s_tues.setText(rs.getString("pre_2_sub"));
                    P3_s_thus.setText(rs.getString("pre_3_sub"));
                    P4_s_thus.setText(rs.getString("pre_4_sub"));
                    P5_s_thus.setText(rs.getString("pre_5_sub"));
                    P6_s_thus.setText(rs.getString("pre_6_sub"));
                    P7_s_thus.setText(rs.getString("pre_7_sub"));
                }
                if (rs.getString("day").toString().equalsIgnoreCase("wednessday")) {
                    P1_s_wed.setText(rs.getString("pre_1_sub"));
                    P2_s_wed.setText(rs.getString("pre_2_sub"));
                    P3_s_wed.setText(rs.getString("pre_3_sub"));
                    P4_s_wed.setText(rs.getString("pre_4_sub"));
                    P5_s_wed.setText(rs.getString("pre_5_sub"));
                    P6_s_wed.setText(rs.getString("pre_6_sub"));
                    P7_s_wed.setText(rs.getString("pre_7_sub"));
                }
                if (rs.getString("day").toString().equalsIgnoreCase("thursday")) {
                    P1_s_th.setText(rs.getString("pre_1_sub"));
                    P2_s_th.setText(rs.getString("pre_2_sub"));
                    P3_s_th.setText(rs.getString("pre_3_sub"));
                    P4_s_th.setText(rs.getString("pre_4_sub"));
                    P5_s_th.setText(rs.getString("pre_5_sub"));
                    P6_s_th.setText(rs.getString("pre_6_sub"));
                    P7_s_th.setText(rs.getString("pre_7_sub"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data are not found");
        }
        p_Class_show.setText(p__class.getSelectedItem().toString());

    }//GEN-LAST:event_jButton3MouseClicked

    private void btn_Rutine_print_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Rutine_print_MouseClicked
        // TODO add your handling code here:
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");
        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.67, 0.80);
                RutinePanal.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }


    }//GEN-LAST:event_btn_Rutine_print_MouseClicked

    public void RutineResetMethod() {
        P1_s_sun.setText("sub");
        P2_s_sun.setText("sub");
        P3_s_sun.setText("sub");
        P4_s_sun.setText("sub");
        P5_s_sun.setText("sub");
        P6_s_sun.setText("sub");
        P7_s_sun.setText("sub");

        P1_s_mon.setText("sub");
        P2_s_mon.setText("sub");
        P3_s_mon.setText("sub");
        P4_s_mon.setText("sub");
        P5_s_mon.setText("sub");
        P6_s_mon.setText("sub");
        P7_s_mon.setText("sub");

        P1_s_tues.setText("sub");
        P2_s_tues.setText("sub");
        P3_s_thus.setText("sub");
        P4_s_thus.setText("sub");
        P5_s_thus.setText("sub");
        P6_s_thus.setText("sub");
        P7_s_thus.setText("sub");

        P1_s_wed.setText("sub");
        P2_s_wed.setText("sub");
        P3_s_wed.setText("sub");
        P4_s_wed.setText("sub");
        P5_s_wed.setText("sub");
        P6_s_wed.setText("sub");
        P7_s_wed.setText("sub");

        P1_s_th.setText("sub");
        P2_s_th.setText("sub");
        P3_s_th.setText("sub");
        P4_s_th.setText("sub");
        P5_s_th.setText("sub");
        P6_s_th.setText("sub");
        P7_s_th.setText("sub");

        p1_time.setText("time");
        p2_time.setText("time");
        p3_time.setText("time");
        p4_time.setText("time");
        p5_time.setText("time");
        p6_time.setText("time");
        p7_time.setText("time");

        p_sessoion.setText("");
        p_Class_show.setText("xxx");
        p__class.setSelectedIndex(0);
    }


    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
        RutineResetMethod();
    }//GEN-LAST:event_jButton12MouseClicked

    private void btn_Rutine_print_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Rutine_print_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Rutine_print_ActionPerformed

    private void s_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s_TableMouseClicked
        // TODO add your handling code here:
        int row = s_Table.getSelectedRow();

        String session = s_Table.getModel().getValueAt(row, 0).toString();
        String batch = s_Table.getModel().getValueAt(row, 1).toString();
        String classs = s_Table.getModel().getValueAt(row, 2).toString();
        String roll = s_Table.getModel().getValueAt(row, 3).toString();
        String fullname = s_Table.getModel().getValueAt(row, 4).toString();
        String father = s_Table.getModel().getValueAt(row, 5).toString();
        String maother = s_Table.getModel().getValueAt(row, 6).toString();
        String fatherP = s_Table.getModel().getValueAt(row, 7).toString();
        String motherP = s_Table.getModel().getValueAt(row, 8).toString();
        String gender = s_Table.getModel().getValueAt(row, 9).toString();
        String age = s_Table.getModel().getValueAt(row, 10).toString();
        String dob = s_Table.getModel().getValueAt(row, 11).toString();
        String birth = s_Table.getModel().getValueAt(row, 12).toString();
        String blood = s_Table.getModel().getValueAt(row, 13).toString();
        String email = s_Table.getModel().getValueAt(row, 14).toString();
        String nation = s_Table.getModel().getValueAt(row, 15).toString();
        String religion = s_Table.getModel().getValueAt(row, 16).toString();
        String present = s_Table.getModel().getValueAt(row, 17).toString();
        String parmanent = s_Table.getModel().getValueAt(row, 18).toString();
        String admision = s_Table.getModel().getValueAt(row, 19).toString();

        s_session.setText(session);
        s_Batch_id.setText(batch);
        s_Class_combo.setSelectedItem(classs);
        s_student_id.setText(roll);
        s_student_first.setText(fullname);
        s_father_N.setText(father);
        s_Mother_N.setText(maother);
        s_F_phone.setText(fatherP);
        s_M_phone.setText(motherP);
        s_gender_combo.setSelectedItem(gender);
        s_age.setText(age);
        s_email.setText(email);
        s_presentAddress.setText(present);
        s_parmanent.setText(parmanent);
        s_National.setText(nation);
        s_religion.setText(religion);
        s_BloodGroup.setSelectedItem(blood);
        s_birth_certificate.setText(birth);
        s_admisiondate.setDate(convertStringToDate(admision));
        s_dateOfBirth.setDate(convertStringToDate(dob));


    }//GEN-LAST:event_s_TableMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String sql = "delete from school where Session_year=? and classs=? and batch_id=? and student_Roll=?";

        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(s_session.getText().toString().trim()));
            ps.setString(2, s_Class_combo.getSelectedItem().toString());
            ps.setString(3, s_Batch_id.getText().toString());
            ps.setInt(4, Integer.parseInt(s_student_id.getText().toString()));

            int rowsDeleted = ps.executeUpdate();
            ps.close();
            con.getCon().close();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data is deleted");
                resultTable();
                // Optionally, you can reset the input fields after deletion
                resultReset();
                AllMethods();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No records found for deletion");
            }
            studentTable();
            studentAdmisionReset();
//            TotalStudentReport_6();
//            TotalStudentReport_7();
//            TotalStudentReport_8();
//            TotalStudentReport_9();
//            TotalStudentReport_10();
                AllMethods();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1MouseClicked

    private void btn_s_Update_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_s_Update_MouseClicked
        // TODO add your handling code here:
        //,designation=?,qualification=?
        String sql = "update  School set Session_year=?,batch_id=?,classs=?,student_Roll=?,full_name=?,fathers_name=?,"
                + "fa_te_phone=?,mothers_name=?,mothers_phone=?,date_of_birth=?,age=?,gender=?,religion=?,nationality=?,"
                + "email=?,blood_group=?,birth_certificate=?,present_Address=?,permanent_Address=?,catagory=?,joiningdate=?";

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
            ps.setString(20, "S");
//            ps.setString(21, "***");
//            ps.setString(22, "***");
            ps.setDate(21, dateMethod(s_admisiondate.getDate()));
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "data is update");
            studentAdmisionReset();
            studentTable();
            AllMethods();
//            TotalStudentReport_6();
//            TotalStudentReport_7();
//            TotalStudentReport_8();
//            TotalStudentReport_9();
//            TotalStudentReport_10();
//            getClassTocomboFromStudentTable();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "data is not update");
        }


    }//GEN-LAST:event_btn_s_Update_MouseClicked

    private void t_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_searchKeyReleased
        // TODO add your handling code here:

        if (t_search.getText().isEmpty()) {
            teacherTable();

        } else {
            teacherStaffScarch();

        }

    }//GEN-LAST:event_t_searchKeyReleased

    private void R_pre3_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R_pre3_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R_pre3_timeActionPerformed

    private void R_pre6_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R_pre6_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R_pre6_timeActionPerformed

    private void btn_rutine_home_mMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_mMouseEntered
        // TODO add your handling code here:

        btn_rutine_home_m.setBackground(new Color(102, 102, 255));

    }//GEN-LAST:event_btn_rutine_home_mMouseEntered

    private void btn_rutine_home_mMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_mMouseExited
        // TODO add your handling code here:
        btn_rutine_home_m.setBackground(new Color(204, 255, 204));

    }//GEN-LAST:event_btn_rutine_home_mMouseExited

    private void btn_rutine_home_VMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_VMouseEntered
        // TODO add your handling code here:
        btn_rutine_home_V.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_rutine_home_VMouseEntered

    private void btn_rutine_home_VMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rutine_home_VMouseExited
        // TODO add your handling code here:
        btn_rutine_home_V.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_rutine_home_VMouseExited

    private void btn_pay_st_MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_st_MouseEntered
        // TODO add your handling code here:
        btn_pay_st_.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_pay_st_MouseEntered

    private void btn_pay_st_MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_st_MouseExited
        // TODO add your handling code here:
        btn_pay_st_.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_pay_st_MouseExited

    private void btn_pay_teMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_teMouseEntered
        // TODO add your handling code here:
        btn_pay_te.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_pay_teMouseEntered

    private void btn_pay_teMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pay_teMouseExited
        // TODO add your handling code here:
        btn_pay_te.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_pay_teMouseExited

    private void btn_exam_inMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_inMouseEntered
        // TODO add your handling code here:
        btn_exam_in.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_exam_inMouseEntered

    private void btn_exam_inMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_inMouseExited
        // TODO add your handling code here:
        btn_exam_in.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_exam_inMouseExited

    private void btn_exam_outMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_outMouseEntered
        // TODO add your handling code here:
        btn_exam_out.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_exam_outMouseEntered

    private void btn_exam_outMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exam_outMouseExited
        // TODO add your handling code here:
        btn_exam_out.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_exam_outMouseExited

    private void btn_admitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admitMouseEntered
        // TODO add your handling code here:
        btn_admit.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_btn_admitMouseEntered

    private void btn_admitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admitMouseExited
        // TODO add your handling code here:
        btn_admit.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_btn_admitMouseExited

    private void te_btn_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_addMouseEntered
        // TODO add your handling code here:
         te_btn_add.setBackground(new Color(102, 102, 255));
        
    }//GEN-LAST:event_te_btn_addMouseEntered

    private void te_btn_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_addMouseExited
        // TODO add your handling code here:
        te_btn_add.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_te_btn_addMouseExited

    private void te_btn_rutinesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_rutinesMouseEntered
        // TODO add your handling code here:
         te_btn_rutines.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_te_btn_rutinesMouseEntered

    private void te_btn_rutinesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_rutinesMouseExited
        // TODO add your handling code here:
         te_btn_rutines.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_te_btn_rutinesMouseExited

    private void te_btn_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_reportMouseEntered
        // TODO add your handling code here:
           te_btn_report.setBackground(new Color(102, 102, 255));
    }//GEN-LAST:event_te_btn_reportMouseEntered

    private void te_btn_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_te_btn_reportMouseExited
        // TODO add your handling code here:
         te_btn_report.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_te_btn_reportMouseExited

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
        
//              teacher_recipt.append("\tTeacher Copy\n"
//                + "   LALBAG MODEL SCHOOL AND COLLAGE\n"
//                + "       DHAKA-1213, phone :+2514558\n\n"
//                + "ID  :" +pay_te_id.getText() + "\t          batch id  :" + pay_st_batch.getText() + "\n"
//                + "Class      :" + pay_st_class_combo.getSelectedItem() + "\t          Roll No   :" + pay_st_Roll.getText() + "\n"
//                //                + "Date :" + pay_st_Date.getDate().toString() + "\n\n"
//                + "      Descript\t\tTk\n"
//                + "-----------------------------------------------------------------\n"
//                + "Admision Fee\t\t" + pay_st_admision.getText() + "\n"
//                + "Monthly Fee\t\t" + TotalPayment() + "\n"
//                + "Exam Fee\t\t" + pay_st_exam_fee.getText() + "\n"
//                + "                                  -------------------------------\n"
//                + "Total Payment\t\t" + pay_st_TotalFee.getText() + "\n"
//        );
    }//GEN-LAST:event_jButton17MouseClicked

    private void jButton17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17MouseEntered
    public void todayFeeReport() {

        String sql = "select sum(jan+feb+mar+apr+may+june+July+aug+sep+oct+nov+decm)As Total from payment where date=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setDate(1, sqlDate);

            rs = ps.executeQuery();

            while (rs.next()) {
                Float totalPrice = rs.getFloat("Total");
                d_todaysFee.setText(totalPrice + " tk");
            }

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void todayTotalCollectionReport() {

        String sql = "select sum(jan+feb+mar+apr+may+june+July+aug+sep+oct+nov+decm+exampayment+admisionFee)As Total from payment where date=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setDate(1, sqlDate);

            rs = ps.executeQuery();

            while (rs.next()) {
                Float totalPrice = rs.getFloat("Total");
                d_todayTotalCollection.setText(totalPrice + " tk");
            }

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       public void monthlyTotalCollection() {
      
        
//        System.out.println(sqlDate.toString().substring(0, 8));
       String sql = "select sum(jan+feb+mar+apr+may+june+July+aug+sep+oct+nov+decm+exampayment+admisionFee)As Total from payment where class not like 'T%' and date like ?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, sqlDate.toString().substring(0, 8)+"%");

            rs = ps.executeQuery();
            
            while(rs.next()){
                Float totalPrice=rs.getFloat("Total");
                d_monthlyCollection.setText(totalPrice+ " tk");                
            }


        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
        public void todaysalaryPay() {

            String sql = "select sum(jan+feb+mar+apr+may+june+July+aug+sep+oct+nov+decm+bonus)As Total from payment where class like 'T%' and date like ?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setDate(1, sqlDate);

            rs = ps.executeQuery();

            while (rs.next()) {
                Float totalPrice = rs.getFloat("Total");
                d_total_pay.setText(totalPrice + " tk");
            }

        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         public void MonthlySalaryPay() {
      
        
//        System.out.println(sqlDate.toString().substring(0, 8));
       String sql = "select sum(jan+feb+mar+apr+may+june+July+aug+sep+oct+nov+decm+bonus)As Total from payment where class like 'T%' and date like ?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, sqlDate.toString().substring(0, 8)+"%");

            rs = ps.executeQuery();
            
            while(rs.next()){
                Float totalPrice=rs.getFloat("Total");
                d_total_pay.setText(totalPrice+ " tk");                
            }


        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void TotalTeacherReport_Male() {
        String sql = "select count(*) from school where catagory like 'T%' and gender like 'Male%'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int teacher_male = rs.getInt("count(*)");
                d_te_male.setText(teacher_male + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalTeacherReport_FeMale() {
        String sql = "select count(*) from school where catagory like 'T%' and gender like 'Female%'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int teacher_female = rs.getInt("count(*)");
                d_te_Female.setText(teacher_female + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void TotalStaffReport_Male() {
        String sql = "select count(*) from school where catagory like 'SF%' and gender like 'Male%'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int staff_male = rs.getInt("count(*)");
                d_sf_male.setText(staff_male + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalStaffReport_FeMale() {
        String sql = "select count(*) from school where catagory like 'SF%' and gender like 'Female%'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int staff_female = rs.getInt("count(*)");
                d_sf_Female.setText(staff_female + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //dash board ar student number show korar jonno---------------------------------------
    public void TotalStudentReport_6() {
        String sql = "select count(*) from school where classs like 6";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_6 = rs.getInt("count(*)");
                d_class_6.setText(class_6 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalStudentReport_7() {
        String sql = "select count(*) from school where classs like 7";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_7 = rs.getInt("count(*)");
                d_class_7.setText(class_7 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalStudentReport_8() {
        String sql = "select count(*) from school where classs like 8";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_8 = rs.getInt("count(*)");
                d_class_8.setText(class_8 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalStudentReport_9() {
        String sql = "select count(*) from school where classs like 9";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_9 = rs.getInt("count(*)");
                d_class_9.setText(class_9 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalStudentReport_10() {
        String sql = "select count(*) from school where classs like 10";
        try {
            ps = con.getCon().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int class_10 = rs.getInt("count(*)");
                d_class_10.setText(class_10 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalPassReport_6() {
        String sql = "select count(*) from resultsheet where classs=6 and grade Not like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_6 = rs.getInt("count(*)");
                d_6_pass.setText(class_6 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalFailReport_6() {
        String sql = "select count(*) from resultsheet where classs=6 and grade like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_6 = rs.getInt("count(*)");
                d_6_fail.setText(class_6 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalPassReport_7() {
        String sql = "select count(*) from resultsheet where classs=7 and grade Not like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_7 = rs.getInt("count(*)");
                d_7_pass.setText(class_7 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalFailReport_7() {
        String sql = "select count(*) from resultsheet where classs=7 and grade like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_7 = rs.getInt("count(*)");
                d_7_fail.setText(class_7 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalPassReport_8() {

        String sql = "select count(*) from resultsheet where classs=8 and grade Not like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_8 = rs.getInt("count(*)");
                d_8_pass.setText(class_8 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalFailReport_8() {
        String sql = "select count(*) from resultsheet where classs=8 and grade like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_8 = rs.getInt("count(*)");
                d_8_fail.setText(class_8 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalPassReport_9() {

        String sql = "select count(*) from resultsheet where classs=9 and grade Not like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_9 = rs.getInt("count(*)");
                d_9_pass.setText(class_9 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalFailReport_9() {
        String sql = "select count(*) from resultsheet where classs=9 and grade like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_9 = rs.getInt("count(*)");
                d_9_fail.setText(class_9 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalPassReport_10() {
        String sql = "select count(*) from resultsheet where classs=10 and grade Not like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_10 = rs.getInt("count(*)");
                d_10_pass.setText(class_10 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TotalFailReport_10() {
        String sql = "select count(*) from resultsheet where classs=10 and grade like 'F'";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int class_10 = rs.getInt("count(*)");
                d_10_fail.setText(class_10 + "");
            }
            rs.close();
            ps.close();
            con.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(View_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InvisiableResultInput() {
        r_bangla.setVisible(false);
        r_english.setVisible(false);
        r_math.setVisible(false);
        r_scince.setVisible(false);
        r_social.setVisible(false);
        r_islam.setVisible(false);
    }

    public void visiableResultInput() {
        r_bangla.setVisible(true);
        r_english.setVisible(true);
        r_math.setVisible(true);
        r_scince.setVisible(true);
        r_social.setVisible(true);
        r_islam.setVisible(true);
    }

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
    private javax.swing.JButton Btn_AllStudent;
    private javax.swing.JButton Btn_OneStudent;
    private javax.swing.JTabbedPane ClassRutineTab;
    private javax.swing.JLabel Degination;
    private javax.swing.JTabbedPane Exam_Seduel;
    private javax.swing.JTabbedPane HomeTab;
    private javax.swing.JLabel P1_s_mon;
    private javax.swing.JLabel P1_s_sun;
    private javax.swing.JLabel P1_s_th;
    private javax.swing.JLabel P1_s_tues;
    private javax.swing.JLabel P1_s_wed;
    private javax.swing.JLabel P2_s_mon;
    private javax.swing.JLabel P2_s_sun;
    private javax.swing.JLabel P2_s_th;
    private javax.swing.JLabel P2_s_tues;
    private javax.swing.JLabel P2_s_wed;
    private javax.swing.JLabel P3_s_mon;
    private javax.swing.JLabel P3_s_sun;
    private javax.swing.JLabel P3_s_th;
    private javax.swing.JLabel P3_s_thus;
    private javax.swing.JLabel P3_s_wed;
    private javax.swing.JLabel P4_s_mon;
    private javax.swing.JLabel P4_s_sun;
    private javax.swing.JLabel P4_s_th;
    private javax.swing.JLabel P4_s_thus;
    private javax.swing.JLabel P4_s_wed;
    private javax.swing.JLabel P5_s_mon;
    private javax.swing.JLabel P5_s_sun;
    private javax.swing.JLabel P5_s_th;
    private javax.swing.JLabel P5_s_thus;
    private javax.swing.JLabel P5_s_wed;
    private javax.swing.JLabel P6_s_mon;
    private javax.swing.JLabel P6_s_sun;
    private javax.swing.JLabel P6_s_th;
    private javax.swing.JLabel P6_s_thus;
    private javax.swing.JLabel P6_s_wed;
    private javax.swing.JLabel P7_s_mon;
    private javax.swing.JLabel P7_s_sun;
    private javax.swing.JLabel P7_s_th;
    private javax.swing.JLabel P7_s_thus;
    private javax.swing.JLabel P7_s_wed;
    private javax.swing.JTabbedPane Pay_StudentTab;
    private javax.swing.JTabbedPane Pay_TeachersTab;
    private javax.swing.JTabbedPane Pay_home;
    private javax.swing.JTabbedPane PaymentTab;
    private javax.swing.JTextArea Payment_student_recipt;
    private javax.swing.JComboBox<String> R_class;
    private javax.swing.JComboBox<String> R_day;
    private javax.swing.JComboBox<String> R_pre1_sub;
    private javax.swing.JComboBox<String> R_pre1_tea;
    private javax.swing.JTextField R_pre1_time;
    private javax.swing.JComboBox<String> R_pre2_sub;
    private javax.swing.JComboBox<String> R_pre2_tea;
    private javax.swing.JTextField R_pre2_time;
    private javax.swing.JComboBox<String> R_pre3_sub;
    private javax.swing.JComboBox<String> R_pre3_tea;
    private javax.swing.JTextField R_pre3_time;
    private javax.swing.JComboBox<String> R_pre4_sub;
    private javax.swing.JComboBox<String> R_pre4_tea;
    private javax.swing.JTextField R_pre4_time;
    private javax.swing.JComboBox<String> R_pre5_sub;
    private javax.swing.JComboBox<String> R_pre5_tea;
    private javax.swing.JTextField R_pre5_time;
    private javax.swing.JComboBox<String> R_pre6_sub;
    private javax.swing.JComboBox<String> R_pre6_tea;
    private javax.swing.JTextField R_pre6_time;
    private javax.swing.JComboBox<String> R_pre7_sub;
    private javax.swing.JComboBox<String> R_pre7_tea;
    private javax.swing.JTextField R_pre7_time;
    private javax.swing.JTextField R_session;
    private javax.swing.JPanel Result_sheet_panal;
    private javax.swing.JPanel RutinePanal;
    private javax.swing.JPanel St_4;
    private javax.swing.JTabbedPane St_Admition;
    private javax.swing.JPanel St_All;
    private javax.swing.JTabbedPane St_Home;
    private javax.swing.JTabbedPane St_Result_Print;
    private javax.swing.JTabbedPane St_Result_input;
    private javax.swing.JButton St_btn_Deatils;
    private javax.swing.JButton St_btn_addmition;
    private javax.swing.JTabbedPane St_deatils;
    private javax.swing.JTabbedPane StudentTab;
    private javax.swing.JPanel Student_details_panal;
    private javax.swing.JPanel Student_details_panal1;
    private javax.swing.JScrollPane Table_Student;
    private javax.swing.JScrollPane Table_Student1;
    private javax.swing.JTabbedPane Te_ClassRutine;
    private javax.swing.JTabbedPane Te_Full_Report;
    private javax.swing.JTabbedPane Te_add;
    private javax.swing.JTabbedPane Te_home;
    private javax.swing.JTable TeacherDeatilsTable;
    private javax.swing.JTabbedPane TeacherTab;
    private javax.swing.JLabel ad_batch;
    private javax.swing.JLabel ad_class;
    private javax.swing.JLabel ad_father;
    private javax.swing.JLabel ad_name;
    private javax.swing.JLabel ad_roll;
    private javax.swing.JLabel ad_session;
    private javax.swing.JPanel admit_cardPanal;
    private javax.swing.JTextField ads_batch;
    private javax.swing.JComboBox<String> ads_class;
    private javax.swing.JTextField ads_roll;
    private javax.swing.JTextField ads_session;
    private javax.swing.JLabel all_st_lable;
    private javax.swing.JPanel bodyPanal;
    private javax.swing.JButton btnClassRutine;
    private javax.swing.JButton btnExam;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnStaff1;
    private javax.swing.JButton btnStudent;
    private javax.swing.JButton btnTeacher;
    private javax.swing.JButton btn_AdmiosionReset_;
    private javax.swing.JButton btn_AdmiosionReset_1;
    private javax.swing.JButton btn_Image_set_;
    private javax.swing.JButton btn_R_Delete;
    private javax.swing.JButton btn_R_Reset;
    private javax.swing.JButton btn_R_RollBack_;
    private javax.swing.JButton btn_R_save_;
    private javax.swing.JButton btn_R_update;
    private javax.swing.JButton btn_Result_sheet_panal_Print_;
    private javax.swing.JButton btn_Rutine_print_;
    private javax.swing.JButton btn_Save_;
    private javax.swing.JButton btn_Save_1;
    private javax.swing.JButton btn_admit;
    private javax.swing.JButton btn_ads_Reset_;
    private javax.swing.JButton btn_ads_view_;
    private javax.swing.JButton btn_exam_in;
    private javax.swing.JButton btn_exam_out;
    private javax.swing.JButton btn_pay_st_;
    private javax.swing.JButton btn_pay_te;
    private javax.swing.JButton btn_rs_Reset_;
    private javax.swing.JButton btn_rs_View_;
    private javax.swing.JButton btn_rutine_home_V;
    private javax.swing.JButton btn_rutine_home_m;
    private javax.swing.JButton btn_s_Update_;
    private javax.swing.JButton btn_save_te_pay_;
    private javax.swing.JButton btn_sd_Print_;
    private javax.swing.JButton btn_sd_Reset;
    private javax.swing.JButton btn_sd_View1_;
    private javax.swing.JButton btn_sd_View_;
    private javax.swing.JButton btn_sd_delete_;
    private javax.swing.JButton btn_st_pay_AddCurt_;
    private javax.swing.JButton btn_st_pay_print;
    private javax.swing.JButton btn_st_pay_reset;
    private javax.swing.JButton btn_st_pay_save_;
    private javax.swing.JButton btn_st_pay_update;
    private javax.swing.JButton btn_t_ta_search_;
    private javax.swing.JButton btn_td_Print_;
    private javax.swing.JButton btn_td_Reset;
    private javax.swing.JButton btn_td_Update;
    private javax.swing.JButton btn_td_View_;
    private javax.swing.JButton btn_td_delete_;
    private javax.swing.JButton btn_te_Image_set_;
    private javax.swing.JTabbedPane classRutineView;
    private javax.swing.JLabel d_10_fail;
    private javax.swing.JLabel d_10_pass;
    private javax.swing.JLabel d_6_fail;
    private javax.swing.JLabel d_6_pass;
    private javax.swing.JLabel d_7_fail;
    private javax.swing.JLabel d_7_pass;
    private javax.swing.JLabel d_8_fail;
    private javax.swing.JLabel d_8_pass;
    private javax.swing.JLabel d_9_fail;
    private javax.swing.JLabel d_9_pass;
    private javax.swing.JLabel d_class_10;
    private javax.swing.JLabel d_class_6;
    private javax.swing.JLabel d_class_7;
    private javax.swing.JLabel d_class_8;
    private javax.swing.JLabel d_class_9;
    private javax.swing.JLabel d_monthlyCollection;
    private javax.swing.JLabel d_sf_Female;
    private javax.swing.JLabel d_sf_male;
    private javax.swing.JLabel d_te_Female;
    private javax.swing.JLabel d_te_male;
    private javax.swing.JLabel d_te_pay;
    private javax.swing.JLabel d_todayTotalCollection;
    private javax.swing.JLabel d_todaysFee;
    private javax.swing.JLabel d_total_pay;
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
    private javax.swing.JTabbedPane inputRutineTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
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
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
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
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel mainPanal;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JLabel one_st_lable;
    private javax.swing.JPanel optionPanal;
    private javax.swing.JLabel p1_time;
    private javax.swing.JLabel p2_time;
    private javax.swing.JLabel p3_time;
    private javax.swing.JLabel p4_time;
    private javax.swing.JLabel p5_time;
    private javax.swing.JLabel p6_time;
    private javax.swing.JLabel p7_time;
    private javax.swing.JLabel p_Class_show;
    private javax.swing.JComboBox<String> p__class;
    private javax.swing.JTextField p_sessoion;
    private com.toedter.calendar.JDateChooser pay_st_Date;
    private javax.swing.JTextField pay_st_FeeAmount;
    private javax.swing.JCheckBox pay_st_Mon_Oct;
    private javax.swing.JCheckBox pay_st_Mon_apr;
    private javax.swing.JCheckBox pay_st_Mon_aug;
    private javax.swing.JCheckBox pay_st_Mon_dec;
    private javax.swing.JCheckBox pay_st_Mon_feb;
    private javax.swing.JCheckBox pay_st_Mon_jan;
    private javax.swing.JCheckBox pay_st_Mon_july;
    private javax.swing.JCheckBox pay_st_Mon_june;
    private javax.swing.JCheckBox pay_st_Mon_mar;
    private javax.swing.JCheckBox pay_st_Mon_may;
    private javax.swing.JCheckBox pay_st_Mon_nov;
    private javax.swing.JCheckBox pay_st_Mon_sep;
    private javax.swing.JTextField pay_st_Roll;
    private javax.swing.JTextField pay_st_TotalFee;
    private javax.swing.JTextField pay_st_admision;
    private javax.swing.JTextField pay_st_batch;
    private javax.swing.JComboBox<String> pay_st_catagory;
    private javax.swing.JComboBox<String> pay_st_class_combo;
    private javax.swing.JTextField pay_st_exam_fee;
    private javax.swing.JTextField pay_st_id;
    private javax.swing.JTextField pay_st_session;
    private javax.swing.JTextField pay_te_Total;
    private javax.swing.JTextField pay_te_bonus;
    private com.toedter.calendar.JDateChooser pay_te_date;
    private javax.swing.JTextArea pay_te_descript;
    private javax.swing.JTextField pay_te_id;
    private javax.swing.JCheckBox pay_te_mon_apr;
    private javax.swing.JCheckBox pay_te_mon_aug;
    private javax.swing.JCheckBox pay_te_mon_dec;
    private javax.swing.JCheckBox pay_te_mon_feb;
    private javax.swing.JCheckBox pay_te_mon_jan;
    private javax.swing.JCheckBox pay_te_mon_july;
    private javax.swing.JCheckBox pay_te_mon_june;
    private javax.swing.JCheckBox pay_te_mon_mar;
    private javax.swing.JCheckBox pay_te_mon_may;
    private javax.swing.JCheckBox pay_te_mon_nov;
    private javax.swing.JCheckBox pay_te_mon_oct;
    private javax.swing.JCheckBox pay_te_mon_sep;
    private javax.swing.JTextField pay_te_name;
    private javax.swing.JTextField pay_te_salary;
    private javax.swing.JTable paymentStudentTable;
    private javax.swing.JLabel r_Serial;
    private javax.swing.JTextField r_St_Roll;
    private javax.swing.JTextField r_avarage;
    private javax.swing.JTextField r_bangla;
    private javax.swing.JTextField r_batch_id;
    private javax.swing.JComboBox<String> r_class;
    private com.toedter.calendar.JDateChooser r_date;
    private javax.swing.JTextField r_english;
    private javax.swing.JComboBox<String> r_examSelect;
    private javax.swing.JTextField r_gpa;
    private javax.swing.JTextField r_grade;
    private javax.swing.JTextField r_islam;
    private javax.swing.JTextField r_math;
    private javax.swing.JTextField r_passFail;
    private javax.swing.JTextField r_scince;
    private javax.swing.JTextField r_session;
    private javax.swing.JTextField r_social;
    private javax.swing.JTextField r_total;
    private javax.swing.JLabel rs_PassFail;
    private javax.swing.JLabel rs_bangla;
    private javax.swing.JLabel rs_batch;
    private javax.swing.JLabel rs_catagory;
    private javax.swing.JLabel rs_class;
    private javax.swing.JLabel rs_english;
    private javax.swing.JLabel rs_gpa;
    private javax.swing.JTextField rs_input_batch;
    private javax.swing.JComboBox<String> rs_input_class;
    private javax.swing.JTextField rs_input_roll;
    private javax.swing.JTextField rs_input_session;
    private javax.swing.JLabel rs_islam;
    private javax.swing.JLabel rs_math;
    private javax.swing.JLabel rs_name;
    private javax.swing.JLabel rs_roll;
    private javax.swing.JLabel rs_scince;
    private javax.swing.JLabel rs_session;
    private javax.swing.JLabel rs_social;
    private javax.swing.JLabel rs_total;
    private javax.swing.JLabel rsg_bangla;
    private javax.swing.JLabel rsg_english;
    private javax.swing.JLabel rsg_gpa;
    private javax.swing.JLabel rsg_islam;
    private javax.swing.JLabel rsg_math;
    private javax.swing.JLabel rsg_scince;
    private javax.swing.JLabel rsg_social;
    private javax.swing.JLabel rsg_total;
    private javax.swing.JTabbedPane rutineHomeTab;
    private javax.swing.JTextField s_Batch_id;
    private javax.swing.JComboBox<String> s_BloodGroup;
    private javax.swing.JComboBox<String> s_Class_combo;
    private javax.swing.JTextField s_F_phone;
    private javax.swing.JLabel s_Image_lable;
    private javax.swing.JTextField s_M_phone;
    private javax.swing.JTextField s_Mother_N;
    private javax.swing.JTextField s_National;
    private javax.swing.JTextField s_Student_last;
    private javax.swing.JTable s_Table;
    private com.toedter.calendar.JDateChooser s_admisiondate;
    private javax.swing.JTextField s_age;
    private javax.swing.JTextField s_birth_certificate;
    private javax.swing.JLabel s_catagory;
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
    private javax.swing.JLabel sd_image;
    private javax.swing.JLabel sd_motherName;
    private javax.swing.JLabel sd_motherPhone;
    private javax.swing.JLabel sd_nationality;
    private javax.swing.JTextArea sd_parmanentA;
    private javax.swing.JTextArea sd_presentA;
    private javax.swing.JLabel sd_religion;
    private javax.swing.JTextField sd_session;
    private javax.swing.JLabel sd_st_name;
    private javax.swing.JTextField sd_studentID;
    private javax.swing.JLabel sf_ctagory;
    private javax.swing.JPanel st_1;
    private javax.swing.JPanel st_2;
    private javax.swing.JPanel st_3;
    private javax.swing.JPanel st_add;
    private javax.swing.JPanel st_add1;
    private javax.swing.JPanel st_admision_main_pan;
    private javax.swing.JButton st_btn_result_in;
    private javax.swing.JButton st_btn_result_out;
    private javax.swing.JComboBox<String> t_BloodGroup1;
    private javax.swing.JTextField t_Id;
    private javax.swing.JTextField t_Mother_N1;
    private javax.swing.JTextField t_National1;
    private javax.swing.JTextField t_age1;
    private javax.swing.JTextField t_contact_number;
    private javax.swing.JLabel t_ctagory;
    private com.toedter.calendar.JDateChooser t_dateOfBirth1;
    private javax.swing.JComboBox<String> t_designationcombo1;
    private javax.swing.JTextField t_email1;
    private javax.swing.JTextField t_father_N1;
    private javax.swing.JComboBox<String> t_gender_combo1;
    private com.toedter.calendar.JDateChooser t_joiningDate;
    private javax.swing.JTextField t_national_id;
    private javax.swing.JTextArea t_parmanent1;
    private javax.swing.JTextArea t_presentAddress1;
    private javax.swing.JComboBox<String> t_qualification;
    private javax.swing.JTextField t_religion1;
    private javax.swing.JTextField t_search;
    private javax.swing.JComboBox<String> t_sf_catagory;
    private javax.swing.JTable t_table;
    private javax.swing.JTextField t_teacher_first1;
    private javax.swing.JTextField t_teacher_last1;
    private javax.swing.JTable tableResultInput;
    private javax.swing.JLabel td_Age;
    private javax.swing.JLabel td_ContactNumber;
    private javax.swing.JLabel td_Designation_view;
    private javax.swing.JLabel td_Dob;
    private javax.swing.JLabel td_JoiningDate;
    private javax.swing.JLabel td_blood;
    private javax.swing.JLabel td_email;
    private javax.swing.JLabel td_fatherName;
    private javax.swing.JLabel td_fullName1;
    private javax.swing.JLabel td_gender_combo;
    private javax.swing.JTextField td_id;
    private javax.swing.JLabel td_motherName;
    private javax.swing.JLabel td_national_id;
    private javax.swing.JLabel td_nationality;
    private javax.swing.JTextArea td_parmanentA;
    private javax.swing.JTextArea td_presentA;
    private javax.swing.JLabel td_religion;
    private javax.swing.JLabel td_te_name;
    private javax.swing.JPanel te_All;
    private javax.swing.JLabel te_Image_set_lable;
    private javax.swing.JButton te_btn_add;
    private javax.swing.JButton te_btn_report;
    private javax.swing.JButton te_btn_rutines;
    private javax.swing.JPanel teacher_recipt;
    private javax.swing.JTable teachersTable;
    // End of variables declaration//GEN-END:variables

}
