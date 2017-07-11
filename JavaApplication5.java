public class JavaApplication5
{
    public static void main(String[] args) {
        Connect control = new Connect();
        control.loaddata();
        control.afisare_xml("Connect.xml");
        control.afisare_txt("Connect.xml","Connect.txt");
        
        Connect1 control1 = new Connect1();
        control1.loaddata1();
        control1.afisare1_xml("Connect1.xml");
        control1.afisare1_txt("Connect1.xml","Connect1.txt");
        
        Connect2 control2 = new Connect2();
        control2.loaddata2();
        control2.afisare2_xml("Connect2.xml");
        control2.afisare2_txt("Connect2.xml","Connect2.txt");
        
        Connect3 control3 = new Connect3();
        control3.loaddata3();
        control3.afisare3_xml("Connect3.xml");
        control3.afisare3_txt("Connect3.xml","Connect3.txt");
    }
}
