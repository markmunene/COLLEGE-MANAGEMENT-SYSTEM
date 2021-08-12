
package studentmgsystem;


import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


public class dbConnection  {
    
    
    PreparedStatement preparedStatement;
   boolean  loginStatus;
   boolean corseloadStatus;
    boolean  proinsertionStatus;
      Connection conn;
      String [] programsArray ={};
      String [] corseArray ={};
      Integer [] marksArray ={};
       
      String [] fnameArray ={};
       String [] snameArray ={};
       Integer [] staff_noArray ={};
      Integer [] phoneArray ={};
      Integer [] passwordArray ={};
      
      String [] regnoArray ={};
      
      Integer marksArraylength;
    List<String> Stringlist= new ArrayList<String>();
    List<String> corseList= new ArrayList<String>();
    List<Integer> markslist= new ArrayList<Integer>();
    
    List<String> gettingfname= new ArrayList<String>();
     List<String> gettingsname= new ArrayList<String>();
     List<Integer> gettingstaff_no= new ArrayList<Integer>();
      List<Integer> gettingphone= new ArrayList<Integer>();
       List<Integer> gettingpass= new ArrayList<Integer>();
     
       List<String> gettngregno= new ArrayList<String>();
  Integer courseslength;
    public dbConnection()  throws SQLException , ClassNotFoundException  {
       
           Class.forName("com.mysql.jdbc.Driver"); /*Loading Driver class for JDBC*/
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentManagement","shaz","Vinnesh23");
       
        //loading the programs form the database
        String sql = "select program_name from programs";
        PreparedStatement program = conn.prepareStatement(sql);
        ResultSet rt= program.executeQuery();
        while(rt.next()){
         String   pr =rt.getString("program_name");
         Stringlist.add(pr);
        }
        programsArray= Stringlist.toArray(new String[]{});
        
        
    }
    
    public boolean dbadminlogin(String table,String colname,String colpassword,String uname, String pass) 
    {
        try{
      
            String sql = "Select* from " + table + " where " +colname+ " = ? and " +colpassword+ " = ? ";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,uname);
                pst.setString(2,pass);
                 ResultSet rs;
                 rs= pst.executeQuery();
                if (rs.next()) {
                 loginStatus= true;
                } else {
                    loginStatus= false;
                }
                  
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return loginStatus;
            }//end of studentlogin
    
     public boolean lecLogin(int staff_no, int password) 
    {
        try{
      
            String sql = "select * from lecturer where staff_no = ? and password = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,staff_no);
                pst.setInt(2,password);
                 ResultSet rs;
                 rs= pst.executeQuery();
                if (rs.next()) {
                 loginStatus= true;
                } else {
                    loginStatus= false;
                }
                  
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return loginStatus;
            }//end of studentlogin
    
    
    //inserting programs
     public boolean programeInsertion(int program_id, String programe_name) 
    {
        try{
        
            String sql = "insert into programs (program_id,program_name) values(?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,program_id);
                pst.setString(2,programe_name);
              
                pst.executeUpdate(); 
                proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion

           public boolean courseInsertion(int program_id, String corse_name) 
    {
        try{
        
            String sql = "insert into courses (corse_name,program_id) values(?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,corse_name);
                pst.setInt(2,program_id);
               
                pst.executeUpdate(); 
                proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion
    
          public boolean lecturerRegistartion(String fname, String sname,int staffNo,int phoneNo,String password) 
    {
        try{
        
            String sql = "insert into lecturer (fname,sname,staff_no,phone,password) values(?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,fname);
                pst.setString(2,sname);
                pst.setInt(3,staffNo);
                pst.setInt(4,phoneNo);
               pst.setString(5,password);
              
                pst.executeUpdate(); 
                proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion
           
       public boolean fetchCorses(int program_id) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "select corse_name from courses where program_id = ?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setInt(1,program_id);
        ResultSet corseresult= corse.executeQuery();
        while(corseresult.next()){
         String   cr =corseresult.getString("corse_name");
         corseList.add(cr);
         
        }
        corseloadStatus= true;
         corseArray = corseList.toArray(new String[]{});
         courseslength =  corseArray.length;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion    
        public boolean seeCorses(String regno) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "select corse_name from courses inner join "
                + " student on courses.program_id = student.program_id where regno = ?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1,regno);
        ResultSet corseresult= corse.executeQuery();
        while(corseresult.next()){
         String   cr =corseresult.getString("corse_name");
         corseList.add(cr);
         
        }
        corseloadStatus= true;
         corseArray = corseList.toArray(new String[]{});
         courseslength =  corseArray.length;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion   
        
        public boolean studentRegInsert(String fname, String sname,String regno,int phoneNo,int program_id,String password,String course_confirmation_status)
    {
        try{
        
            String sql = "insert into student (fname,sname,regno,phone,program_id,password,course_confirmation_status) values(?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,fname);
                pst.setString(2,sname);
                pst.setString(3,regno);
                pst.setInt(4,phoneNo);
                pst.setInt(5,program_id);
               pst.setString(6,password);
              
               pst.setString(7,course_confirmation_status);
                pst.executeUpdate(); 
                proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion
        
        
         public boolean marksInsertion(String regno,int corse_id, int marks, int staff_no) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "insert into results (regno,course_id,points,staff_no) values(?,?,?,?)";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1, regno);
        corse.setInt(2, corse_id);
        corse.setInt(3,marks);
        corse.setInt(4,staff_no);
        
        corse.executeUpdate();
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
         
           public boolean gettingMarks(String regno) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "select points from results where regno = ?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1,regno);
        ResultSet mark= corse.executeQuery();
        while(mark.next()){
         String   cr =mark.getString("points");
         int ma= Integer.parseInt(cr);
         markslist.add(ma);
         
        }
        corseloadStatus= true;
         marksArray = markslist.toArray(new Integer[]{});
         marksArraylength= marksArray.length;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
           
            public boolean marksInsertion2(String regno,String grade, String promotion) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "update  student set grade=? ,promotion_status =? where  regno = ? ";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1, grade);
       corse.setString(2, promotion);
        corse.setString(3, regno);
        corse.executeUpdate();
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
            
          public boolean gettingStaffForEditing(int lec_id) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "select fname,sname,Staff_no,phone,password from lecturer where lec_id =?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setInt(1, lec_id);
        ResultSet mark= corse.executeQuery();
        while(mark.next()){
         String   fname =mark.getString("fname");
         String   sname =mark.getString("sname");
        int staff_no   =mark.getInt("staff_no");
        int phone = mark.getInt("phone");
        int pass =  mark.getInt("password");
         
         gettingfname.add(fname);
         gettingsname.add(sname);
         gettingstaff_no.add(staff_no);
         gettingphone.add(phone);
         gettingpass.add(pass);
        }
       
         fnameArray = gettingfname.toArray(new String[]{});
         snameArray = gettingsname.toArray(new String[]{});
         
         staff_noArray = gettingstaff_no.toArray(new Integer[]{});
         phoneArray = gettingphone.toArray(new Integer[]{});
         passwordArray = gettingpass.toArray(new Integer[]{});
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
          
      public boolean staffUpdate(int lec_id,String fname,String sname, int staff_no, int phone,String pass) 
    {
        try
        {
        //fetching the courses from the database
        String sql1 = "update  lecturer set fname = ? ,sname = ?,staff_no = ? ,phone = ? ,password = ? where  lec_id = ? ";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1, fname);
       corse.setString(2, sname);
        corse.setInt(3, staff_no);
        corse.setInt(4, phone);
        corse.setString(5, pass);
        corse.setInt(6, lec_id);
        corse.executeUpdate();
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion  
      
      public boolean deletingLecturer(int staff_no) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "delete from lecturer where staff_no = ?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setInt(1,staff_no);
        corse.executeUpdate();
       proinsertionStatus = true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
      
       public boolean deletingStudent(String Regno) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "delete from student where regno = ?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1,Regno);
        corse.executeUpdate();
       proinsertionStatus = true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion  
       
        public boolean studentUpdate(String fname,String sname, String regno, int phone,int program_id,String pass,int student_id ) 
    {
        try
        {
        //fetching the courses from the database
        String sql1 = "update  student set fname = ? ,sname = ?,regno = ? ,phone = ? ,program_id = ?,password = ? where  student_id = ? ";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setString(1, fname);
       corse.setString(2, sname);
        corse.setString(3, regno);
        corse.setInt(4, phone);
        corse.setInt(5, program_id);
        corse.setString(6, pass);
         corse.setInt(7, student_id);
        corse.executeUpdate();
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion  
        
          public boolean gettingStudentDetails(int Student_id) 
    {
        try{
        //fetching the courses from the database
        String sql1 = "select fname,sname,regno,phone,program_id,password from student where Student_id =?";
        PreparedStatement corse = conn.prepareStatement(sql1);
        corse.setInt(1, Student_id);
        ResultSet mark= corse.executeQuery();
        while(mark.next()){
         String   fname =mark.getString("fname");
         String   sname =mark.getString("sname");
       String regno   =mark.getString("regno");
        int phone = mark.getInt("phone");
        // int pro = mark.getInt("program_id");
        int pass =  mark.getInt("password");
         
         gettingfname.add(fname);
         gettingsname.add(sname);
         gettngregno.add(regno);
         gettingphone.add(phone);
         gettingpass.add(pass);
        
        }
       
         fnameArray = gettingfname.toArray(new String[]{});
         snameArray = gettingsname.toArray(new String[]{});
         
         regnoArray = gettngregno.toArray(new String[]{});
         staff_noArray = gettingstaff_no.toArray(new Integer[]{});
         phoneArray = gettingphone.toArray(new Integer[]{});
         passwordArray = gettingpass.toArray(new Integer[]{});
         
         proinsertionStatus=true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
            proinsertionStatus=false;
        }
        return proinsertionStatus;
            }//end of send of program insertion 
    }
    

