package sample.Repository;

import sample.Database.Database;
import sample.Model.Evidence;
import sample.Model.F1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class F1Repository {
    private List<F1> f1List;

    public F1Repository(List<F1> f1List) {
        this.f1List = f1List;
    }

    public F1Repository() {
    }

    public List<F1> getF1List() {
        return f1List;
    }

    public void setF1List(List<F1> f1List) {
        this.f1List = f1List;
    }

    public void addF1(){

    }

//    public F1 getLastF1(){
//        String sql = "SELECT * FROM Table ORDER BY ID DESC LIMIT 1";
//        F1 f1 = new F1();
//        try {
//            Statement sm = Database.getInstance().connect().createStatement();
//            ResultSet resultSet = sm.executeQuery(sql);
//            while (resultSet.next()){
//                int f1Id = resultSet.getInt("F1ID");
//                String f1Name = resultSet.getString("F1Name");
//                String f1Description = resultSet.getString("F1Description");
//                String f0Name = resultSet.getString("F0Name");
//                f1 = new F1(f1Id, f1Name, f1Description, f0Name);
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        } finally {
//            Database.getInstance().disconnect();
//            return f1;
//        }
//    }

}
