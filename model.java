
package studentmgsystem;


public class model {
    
   String grade;
   String promotion;
    public String grading (double marks){
       if(marks >= 70 && marks <100){
          grade ="A";
          
        }
       else if (marks >= 60 && marks < 70){
           grade= "B";
       }
       else if (marks >=50 && marks < 60){
           grade= "C";
       }
       else if (marks >=40 && marks < 50){
           grade= "D";
       }
        else if (marks >=0 && marks < 40){
           grade= "F";
       }
        else {
           grade= "null"; 
        }
        return grade;
    }
    
      

    public String getpromotion (double marks){
       if(marks >=70 && marks <100){
          promotion ="promoted";
          
        }
       else if (marks >=60 && marks <70){
           promotion= "promoted";
       }
       else if (marks >=50 && marks <60){
           promotion= "promoted";
       }
       else if (marks >=40 && marks <50){
           promotion= "demoted";
       }
        else if (marks >=0 && marks <40){
           promotion= "demoted";
       }
        else {
           promotion= "demoted"; 
        }
        return promotion;
    }
}
