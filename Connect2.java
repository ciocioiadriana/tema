
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


public class Connect2 {
    
    protected ArrayList<Angajat> list_angajat = new ArrayList<Angajat>();
    protected String myDriver = "org.gjt.mm.mysql.Driver";
    protected String myUrl = "jdbc:mysql://localhost:3306/proiect_practica";

    public void loaddata2()
    {
        try
        {
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "");
          // create our mysql database connection
          // our SQL SELECT query. 
          // if you only need a few columns, specify them by name instead of using "*"
          String query = "SELECT * FROM angajat";

          // create the java statement
          Statement st = conn.createStatement();

          // execute the query, and get a java resultset
          ResultSet rs = st.executeQuery(query);

          // iterate through the java resultset
          while (rs.next())
          {
            Angajat x=new Angajat();
            x.setNume(rs.getString("Nume"));
            x.setPrenume(rs.getString("Prenume"));
            x.setDepartament(rs.getString("Departament"));
            x.setVarsta(rs.getInt("Varsta"));
            x.setSalariu(rs.getDouble("Salariu"));
            list_angajat.add(x);
          }
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void afisare2_txt(String filenamefrom, String filenameto)
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
            NodeList nList = doc.getElementsByTagName("angajat");
            writer.printf("\n----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               writer.printf("\n\nCurrent Element :" 
                  + nNode.getNodeName());
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
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
                  writer.printf("\nDepartament : " 
                  + eElement
                     .getElementsByTagName("Departament")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nVarsta : " 
                  + eElement
                     .getElementsByTagName("Varsta")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nSalariu : " 
                  + eElement
                     .getElementsByTagName("Salariu")
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

    public void afisare2_xml(String filename)
    {
        try{
            int i;
            DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = 
               dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("angajat");
            doc.appendChild(rootElement);
            for(i=0;i<list_angajat.size();i++){
                Element angajat = doc.createElement("angajat");
                rootElement.appendChild(angajat);
                // Nume element
                Element Nume = doc.createElement("Nume");
                Nume.appendChild(
                doc.createTextNode(list_angajat.get(i).getNume()));
                angajat.appendChild(Nume);
                // Prenume element
                Element Prenume = doc.createElement("Prenume");
                Prenume.appendChild(
                doc.createTextNode(list_angajat.get(i).getPrenume()));
                angajat.appendChild(Prenume);
                // Departament element
                Element Departament = doc.createElement("Departament");
                Departament.appendChild(
                doc.createTextNode(list_angajat.get(i).getDepartament()));
                angajat.appendChild(Departament);
                // Varsta element
                Element Varsta = doc.createElement("Varsta");
                Varsta.appendChild(
                doc.createTextNode(Integer.toString(list_angajat.get(i).getVarsta())));
                angajat.appendChild(Varsta);
                // Salariu element
                Element Salariu = doc.createElement("Salariu");
                Salariu.appendChild(
                doc.createTextNode(Double.toString(list_angajat.get(i).getSalariu())));
                angajat.appendChild(Salariu);
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
