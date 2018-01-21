package system.xml;

import com.google.gson.annotations.SerializedName;
import model.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class Employees {
    @XmlElement(name = "employee")
    @SerializedName("employee")
    private List<Employee> employeesList = new ArrayList<>();

    public Employees() {}

    public Employees(List<Employee> employeeList) {
        this.employeesList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeesList;
    }

}
