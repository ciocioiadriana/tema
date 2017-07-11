
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adriana
 */
public class Connect3 {
    
     protected ArrayList<Student> list_student = new ArrayList<Student>();
    protected String myDriver = "org.gjt.mm.mysql.Driver";
    protected String myUrl = "jdbc:mysql://localhost:3306/proiect_practica";

    public void loaddata3()
    {
        try
        {
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "");
          // create our mysql database connection
          // our SQL SELECT query. 
          // if you only need a few columns, specify them by name instead of using "*"
          String query = "SELECT * FROM student";

          // create the java statement
          Statement st = conn.createStatement();

          // execute the query, and get a java resultset
          ResultSet rs = st.executeQuery(query);

          // iterate through the java resultset
          while (rs.next())
          {
            Student x=new Student();
            x.setID(rs.getInt("ID"));
            x.setNume(rs.getString("Nume"));
            x.setPrenume(rs.getString("Prenume"));
            x.setGrupa(rs.getString("Grupa"));
            x.setMedie(rs.getDouble("Medie"));
            list_student.add(x);
          }
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void afisare3_txt(String filenamefrom, String filenameto)
    {
        int i;
        try{	
            File inputFile = new File(filenamefrom);
            PrintWriter writer = new PrintWriter(filenameto, "UTF-8"); 
            DocumentBuilderFactory dbFactory 
               = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            writer.printf("Root element :" 
               + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            writer.printf("\n----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               writer.printf("\n\nCurrent Element :" 
                  + nNode.getNodeName());
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  writer.printf("\nID : " 
                     + eElement
                     .getElementsByTagName("ID")
                     .item(0)
                     .getTextContent());
                   writer.printf("\nNume : " 
                     + eElement
                     .getElementsByTagName("Nume")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nPrenume : " 
                  + eElement
                     .getElementsByTagName("Prenume")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nGrupa : " 
                  + eElement
                     .getElementsByTagName("Grupa")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nMedie : " 
                  + eElement
                     .getElementsByTagName("Medie")
                     .item(0)
                     .getTextContent());
            }
         }
            writer.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void afisare3_xml(String filename)
    {
        try{
            int i;
            DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = 
               dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("student");
            doc.appendChild(rootElement);
            for(i=0;i<list_student.size();i++){
                Element student = doc.createElement("student");
                rootElement.appendChild(student);
                // ID element
                Element ID = doc.createElement("ID");
                ID.appendChild(
                doc.createTextNode(Integer.toString(list_student.get(i).getID())));
                student.appendChild(ID);
                // Nume element
                Element Nume = doc.createElement("Nume");
                Nume.appendChild(
                doc.createTextNode(list_student.get(i).getNume()));
                student.appendChild(Nume);
                // Prenume element
                Element Prenume = doc.createElement("Prenume");
                Prenume.appendChild(
                doc.createTextNode(list_student.get(i).getPrenume()));
                student.appendChild(Prenume);
                // Grupa element
                Element Grupa = doc.createElement("Grupa");
                Grupa.appendChild(
                doc.createTextNode(list_student.get(i).getGrupa()));
                student.appendChild(Grupa);
                // Medie_Raft element
                Element Medie = doc.createElement("Medie");
                Medie.appendChild(
                doc.createTextNode(Double.toString(list_student.get(i).getMedie())));
                student.appendChild(Medie);
            }
            // write the content into xml file
            TransformerFactory transformerFactory =
            TransformerFactory.newInstance();
            Transformer transformer =
            transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result =
            new StreamResult(new File(filename));
            transformer.transform(source, result);
        }
        catch (Exception e){
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }
    
}
