package Work.srp;

 interface Employee {
    default int getSalary() {
        return 0;
    }
 }
