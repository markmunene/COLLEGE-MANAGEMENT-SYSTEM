
package studentmgsystem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class StudentMgSystem {

        public static void main(String args[]){
        
    SwingUtilities.invokeLater(new Runnable(){
        //this is the main method where the program starts its execution
        public void run() {
            try {
                //these are objects of the various classes in this program
                adminlogin adlogin= new adminlogin();
                adminvew adview= new adminvew();
                dbConnection connect= new dbConnection();
                studentlogin studentlog = new studentlogin();
                lecturerlogin leclogin= new lecturerlogin();
                lecturerview lecview= new lecturerview();
                person ps = new person();
                studentview studeview =new studentview();
                student registration= new student();
                model model = new model();
                conntroller control= new conntroller(adlogin,adview,connect,studentlog,leclogin,lecview,ps,studeview,registration,model);
                ps.setVisible(true);
                
                //To change body of generated methods, choose Tools | Templates.
            } catch (SQLException ex) {
                Logger.getLogger(StudentMgSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StudentMgSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
        
    }
}
