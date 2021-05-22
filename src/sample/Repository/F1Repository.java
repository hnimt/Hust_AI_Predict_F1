package sample.Repository;

import sample.Database.Database;
import sample.Model.Evidence;
import sample.Model.F1;

import java.sql.PreparedStatement;
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

    public static F1 findF1ById(int id){
        String sql = "SELECT * FROM `F1` WHERE F1ID = ?";
        F1 f1 = null;
        try {
            PreparedStatement ps = Database.getInstance().connect().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int f1Id = resultSet.getInt("F1ID");
                String f1Name = resultSet.getString("F1Name");
                String f1Des = resultSet.getString("F1Description");
                String f0Name = resultSet.getString("F0Name");
                f1 = new F1(f1Id, f1Name, f1Des, f0Name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return f1;
        }
    }

}
