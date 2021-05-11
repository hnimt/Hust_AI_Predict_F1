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



}
