
package studentmgsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class conntroller {
    adminlogin adlogin= new adminlogin();
    adminvew adview= new adminvew();
    dbConnection connect;
    studentlogin studentlog = new studentlogin();
    lecturerlogin leclogin= new lecturerlogin();
    lecturerview lecview= new lecturerview();
    person ps = new person();
    student registration= new student();
    studentview studeview =new studentview();
    model model = new model();


    conntroller(adminlogin adlogin,adminvew adview, dbConnection connect, studentlogin studentlog,lecturerlogin leclogin,lecturerview lecview,person ps,studentview studeview, student registration,model model){
        try {
            this.connect = new dbConnection();
            this.adlogin= adlogin;
            this.studentlog= studentlog;
            this.leclogin= leclogin;
            this.lecview=lecview;
            this.ps=ps;
            this.studeview= studeview;
            this.registration= registration;
            this.model= model;
           
            this.adview= adview;
            
            this.adlogin.addadminlistener(new adminlog());
             this.adlogin.canceladminlogin(new cancelloginbtn());
            
            this.studentlog.addstudelistener(new studentLogin());
            this.studentlog.cancelstudelogin(new cancelstudde());
            
            this.adview.addprogramreglistener(new programmeReg());
            this.adview.addcorsereglistener(new courseReg());
             this.adview.addLecReglistener(new lecturerReg());
             this.adview.adddeleteLecReglistener(new deleteLec());
             this.adview.adddreqLecReglistener(new lectEditing1());
              this.registration.addStudeReglistener(new studentRegistration());
              this.adview.addsetLecReglistener(new updatelecturers());
              this.adview.studentupdatelistener(new updateStudent());
              this.adview.deleteStudentlistener(new deletestudent());
              this.adview.requeststudelistener( new studentEditing());
             //setting the programs
               
            this.registration.addprogramsListener(new setCourses());
            this.registration.backtoLogin(new backToLogin());
            
            this.studentlog.addstudentReglistener(new backTostudentreg());
            
            this.leclogin.addlecloginlistener(new lecLOgin());
             this.leclogin.addleclogincancel(new leclogincancel());
            this.lecview.addmarkslistener(new marksInsertion());
         
           this.ps.lec(new personbtns());
           this.ps.student(new studentClas());
           this.ps.admin(new adminClass());
        } catch (SQLException ex) {
            Logger.getLogger(conntroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conntroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //creating the adminlogin class
    
     public class personbtns implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
                JButton click = (JButton) evt.getSource();
             // if(click== studentlog.)
          ps.dispose();
          leclogin.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
     public class studentClas implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
          ps.dispose();
          studentlog.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
     public class adminClass implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
          ps.dispose();
          adlogin.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
     public class cancelloginbtn implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
                
          adlogin.dispose();
          ps.setVisible(true);
      
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
      public class cancelstudde implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
                
          studentlog.dispose();
          
        ps.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
    
      public class leclogincancel implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
                
          leclogin.dispose();
          
        ps.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
    public class adminlog implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               String username=adlogin.username();
              String password= adlogin.password();
              connect.dbadminlogin("admin","username", "password", username, password);
             
              if(connect.loginStatus==true){
                  adlogin.dispose();
                  adview.setVisible(true);
              }
              else {
                    JOptionPane.showMessageDialog(null,"password not matched");
                }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of admin login
    int STAFF_NO;
            public class lecLOgin implements ActionListener{
            public void actionPerformed(ActionEvent evt) {
                try{


                  STAFF_NO= leclogin.getstaffNo();
                 int password= leclogin.getpassword();
                   connect.lecLogin(STAFF_NO, password);

                  if(connect.loginStatus==true){
                      leclogin.dispose();
                      lecview.setVisible(true);
                  }
                  else {
                        JOptionPane.showMessageDialog(null,"password not matched");
                    }

                }
                catch(Exception exe){
                    exe.printStackTrace();
                }
            }

        }//end of admin login
        //creating the studentlogin class
        String REGNO= studentlog.username();
    public class studentLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt) {
            try{
              //setting the corses when the student hits the login button
               
              String password= studentlog.password();
              REGNO= studentlog.username();
              connect.dbadminlogin("student","regno", "password", REGNO, password);
              if(connect.loginStatus==true){
                  studentlog.dispose();
                  studeview.setVisible(true);
              }
              else {
                    JOptionPane.showMessageDialog(null,"password not matched");
                }
              
              //setting the courses also for the student to see the results
          connect.seeCorses(REGNO);
                studeview.setCourse("");
               for(String course : connect.corseArray){
                    studeview.setCourse(" \n"+course);
               } 
              //showing the courses when the student logs in
        
                connect.gettingMarks(REGNO);
               Integer sum=0;
               for( int i=0;i<connect.marksArray.length;i++){
                   sum += connect.marksArray[i];
               }
               sum= sum/connect.marksArraylength;
                
             double marks =sum;
             String grade = model.grading(marks);
             String promotion = model.getpromotion(marks);
             connect.marksInsertion2(REGNO, grade, promotion);
   
         studeview.setResults("agggregate score "+ marks +"\n");
        String GRADE= "grade::  "+grade +" \n";
        studeview.setResults(GRADE);
             String promo ="promotion_status:: "+promotion +" \n";
           studeview.setResults(promo);  
          //end of appending the results to results text area
          
          
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of studentlogin
    
    public class programmeReg implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt) {
            try{
             int program_id= adview.programId();
             String program_name= adview.programName();
             connect.programeInsertion(program_id, program_name);
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"porgram inserted");
             }
             else{
                 JOptionPane.showMessageDialog(null,"program insertion failed");
             }
              
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of program registration
    
    public class courseReg implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
             int program_id= adview.programid();
             String corse_name = adview.corseName();
             connect.courseInsertion(program_id, corse_name);
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"COURSE INSERTED");
             }
             else{
                 JOptionPane.showMessageDialog(null,"INSERTION FAILED");
             }
              
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of program registration
    
    //creating lecturer registration class 
    public class lecturerReg implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
             String fname= adview.lfname();
             String sname= adview.lsname();
             int staffNo= adview.lstaffNo();
             int phoneNo = adview.lphonenumber();
             String password = adview.lpassword();
              String pass = adview.lFirstpassword();
              if(pass.equals(password)==true){
                   connect.lecturerRegistartion(fname, sname, staffNo, phoneNo, password);
              }
              else{
                  JOptionPane.showMessageDialog(null,"password did not match");
              }
            
             
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"LEC REGISTERED ");
             }
             else{
                 JOptionPane.showMessageDialog(null,"REG FAILED");
             }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of lecturer registration
   public class setCourses implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
                 try{
                         int selectedProgram=0;
            //setting the corses according to the program selected;
            if(e.getStateChange() == ItemEvent.SELECTED){
               selectedProgram = registration.getProgramSelected();
            }
        
             connect.fetchCorses(selectedProgram);
             if(registration.getcorsearea().equals("")==true){
                for(int i=0; i< connect.courseslength; i++ ){
                   String text = connect.corseArray[i];
                  
                   registration.setCourse( " \n"+text.toString());
                   selectedProgram =0;
               }  
             }
             else{
                registration.deletingcorsearea();
             }
              
              
               
           
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
        
    }//end of program registration   
    
    public class studentRegistration implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
            String fname= registration.sfname();
            String sname= registration.sname();
            String regno = registration.sRegNo();
            int phoneNo=  registration.sphonenumber();
            int program_id= registration.getProgramSelected();
            String trialpass= registration.password(); 
            String password= registration.spassword(); 
            String course_confirmation_status= registration.getcourseConfirmation();
             
               if(trialpass.equals(password)==true){
                 connect.studentRegInsert(fname, sname, regno, phoneNo, program_id, password,course_confirmation_status); 
                 registration.cleartext();
              }
              else{
                  JOptionPane.showMessageDialog(null,"password did not match");
              }
            
             
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"STUDENT REGISTERED");
             }
             else{
                 JOptionPane.showMessageDialog(null,"program insertion failed");
             }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of program registration
    
     public class backToLogin implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
          registration.dispose();
          studentlog.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
     public class backTostudentreg implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
           for(String element : connect.programsArray){
                   registration.setPrograms(element);
            }
          studentlog.dispose();
          registration.setVisible(true);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
    
     public class marksInsertion implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               String regno = lecview.sRegNo();
               int course_id = lecview.getCorseId();
               int marks = lecview.getmarks();
                connect.marksInsertion(regno, course_id, marks, STAFF_NO);
               if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"MARKS ENTERED");
             }
             else{
                 JOptionPane.showMessageDialog(null,"");
             }
          
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
   
     public class deleteLec implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               //setting the corses according to the program selected;
             String no=JOptionPane.showInputDialog("ENTER STAFF NO ");
               int  staff = Integer.parseInt(no);  
               connect.deletingLecturer(staff);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     int  STAFF_ID;
      public class lectEditing1 implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               String no=JOptionPane.showInputDialog("ENTER LECTURER ID");
               STAFF_ID = Integer.parseInt(no);  
              connect.gettingStaffForEditing(STAFF_ID);
               for(String fname : connect.fnameArray){
                   adview.lsetfname(fname);
               }
               for(String sname : connect.snameArray){
                   adview.lsetsname(sname);
               }
                for(int staff_no : connect.staff_noArray){
                    String sno= ""+staff_no;
                   adview.lsetstaffNo(sno);
               }
               for(int pho : connect.phoneArray){
                    String phone= ""+pho;
                   adview.lsetphonenumber(phone);
               }
               for(int pass : connect.passwordArray){
                    String password= ""+pass;
                   adview.lsetpassword(password);
               }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
        //creating lecturer registration class 
    public class updatelecturers implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt) {
            try{
             String fname= adview.lfname();
             String sname= adview.lsname();
             int staffNo= adview.lstaffNo();
             int phoneNo = adview.lphonenumber();
             String password = adview.lpassword();
              String pass = adview.lFirstpassword();
               
             connect.staffUpdate(STAFF_ID, fname, sname, staffNo, phoneNo, password);
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"LECTURER DETAILS UPDATED");
                 adview.setlecfields("");
             }
             else{
                 JOptionPane.showMessageDialog(null,"UPDATE FAILED");
             }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of lecturer registration 
    int  STUDENT_ID;
     public class studentEditing implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               String no=JOptionPane.showInputDialog("ENTER STUDENT ID");
              STUDENT_ID= Integer.parseInt(no);  
              connect.gettingStudentDetails(STUDENT_ID);
               for(String fname : connect.fnameArray){
                   adview.ssetfname(fname);
               }
               for(String sname : connect.snameArray){
                   adview.ssetsname(sname);
               }
                for(String regno : connect.regnoArray){
                    String sno= ""+regno;
                   adview.ssetregno(sno);
               }
               for(int pho : connect.phoneArray){
                    String phone= ""+pho;
                   adview.ssetphonenumber(phone);
               }
               for(int pass : connect.passwordArray){
                    String password= ""+pass;
                   adview.ssetpassword(password);
               }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration
     
     
      public class updateStudent implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
            String fname= adview.sfname();
            String sname= adview.sname();
            String regno = adview.sRegNo();
            int phoneNo=  adview.sphonenumber();
            int program_id= adview.getProgramSelected();
            
            String password= adview.password(); 
          
             connect.studentUpdate(fname, sname, regno, phoneNo, program_id, password, STUDENT_ID);
            
            
             if(connect.proinsertionStatus==true){
                 JOptionPane.showMessageDialog(null,"STUDENT DETAILS UPDATED");
                 adview.settingstudentFieldstonull("");
             }
             else{
                 JOptionPane.showMessageDialog(null,"UPDATE FAILED");
             }
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        }
        
    }//end of program registration 
     
     public class deletestudent implements ActionListener{
        public void actionPerformed(ActionEvent evt) {
            try{
               //setting the corses according to the program selected;
             String REGNO=JOptionPane.showInputDialog("ENTER STUDENT REGISTRATION NUMBER ");
            
               connect.deletingStudent(REGNO);
            }
            catch(Exception exe){
                exe.printStackTrace();
            }
        } 
    }//end of program registration 
}
