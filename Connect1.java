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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Connect1 {
       
    protected ArrayList<Adresa> list_adresa = new ArrayList<Adresa>();
    protected String myDriver = "org.gjt.mm.mysql.Driver";
    protected String myUrl = "jdbc:mysql://localhost:3306/proiect_practica";

    public void loaddata1()
    {
        try
        {
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "");
          // create our mysql database connection
          // our SQL SELECT query. 
          // if you only need a few columns, specify them by name instead of using "*"
          String query = "SELECT * FROM adresa";

          // create the java statement
          Statement st = conn.createStatement();

          // execute the query, and get a java resultset
          ResultSet rs = st.executeQuery(query);

          // iterate through the java resultset
          while (rs.next())
          {
            Adresa x=new Adresa();
            x.setStrada(rs.getString("Strada"));
            x.setBloc(rs.getString("Bloc"));
            x.setScara(rs.getInt("Scara"));
            x.setEtaj(rs.getInt("Etaj"));
            x.setApartament(rs.getInt("Apartament"));
            list_adresa.add(x);
          }
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void afisare1_txt(String filenamefrom, String filenameto)
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
            NodeList nList = doc.getElementsByTagName("adresa");
            writer.printf("\n----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               writer.printf("\n\nCurrent Element :" 
                  + nNode.getNodeName());
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  writer.printf("\nStrada : " 
                     + eElement
                     .getElementsByTagName("Strada")
                     .item(0)
                     .getTextContent());
                   writer.printf("\nBloc : " 
                     + eElement
                     .getElementsByTagName("Bloc")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nScara : " 
                  + eElement
                     .getElementsByTagName("Scara")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nEtaj : " 
                  + eElement
                     .getElementsByTagName("Etaj")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nApartament : " 
                  + eElement
                     .getElementsByTagName("Apartament")
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

    public void afisare1_xml(String filename)
    {
        try{
            int i;
            DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = 
               dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("adresa");
            doc.appendChild(rootElement);
            for(i=0;i<list_adresa.size();i++){
                Element adresa = doc.createElement("adresa");
                rootElement.appendChild(adresa);
                // Strada element
                Element Strada = doc.createElement("Strada");
                Strada.appendChild(
                doc.createTextNode(list_adresa.get(i).getStrada()));
                adresa.appendChild(Strada);
                // Bloc element
                Element Bloc = doc.createElement("Bloc");
                Bloc.appendChild(
                doc.createTextNode(list_adresa.get(i).getBloc()));
                adresa.appendChild(Bloc);
                // Scara element
                Element Scara = doc.createElement("Scara");
                Scara.appendChild(
                doc.createTextNode(Integer.toString(list_adresa.get(i).getScara())));
                adresa.appendChild(Scara);
                // Etaj element
                Element Etaj = doc.createElement("Etaj");
                Etaj.appendChild(
                doc.createTextNode(Integer.toString(list_adresa.get(i).getEtaj())));
                adresa.appendChild(Etaj);
                // Apartament element
                Element Apartament = doc.createElement("Apartament");
                Apartament.appendChild(
                doc.createTextNode(Integer.toString(list_adresa.get(i).getApartament())));
                adresa.appendChild(Apartament);
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

    private String String(String titlul) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private String Integer(int numar_Raft) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}