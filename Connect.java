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


public class Connect {
       
    protected ArrayList<Biblioteca> list_biblioteca = new ArrayList<Biblioteca>();
    protected String myDriver = "org.gjt.mm.mysql.Driver";
    protected String myUrl = "jdbc:mysql://localhost:3306/proiect_practica";

    public void loaddata()
    {
        try
        {
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "");
          // create our mysql database connection
          // our SQL SELECT query. 
          // if you only need a few columns, specify them by name instead of using "*"
          String query = "SELECT * FROM biblioteca";

          // create the java statement
          Statement st = conn.createStatement();

          // execute the query, and get a java resultset
          ResultSet rs = st.executeQuery(query);

          // iterate through the java resultset
          while (rs.next())
          {
            Biblioteca x=new Biblioteca();
            x.setAutor(rs.getString("Autor"));
            x.setTitlul(rs.getString("Titlul"));
            x.setCategoria(rs.getString("Categoria"));
            x.setEditura(rs.getString("Editura"));
            x.setNumar_Raft(rs.getInt("Numar_Raft"));
            list_biblioteca.add(x);
          }
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void afisare_txt(String filenamefrom, String filenameto)
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
            NodeList nList = doc.getElementsByTagName("biblioteca");
            writer.printf("\n----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               writer.printf("\n\nCurrent Element :" 
                  + nNode.getNodeName());
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  writer.printf("\nTitlul : " 
                     + eElement
                     .getElementsByTagName("Titlul")
                     .item(0)
                     .getTextContent());
                   writer.printf("\nAutor : " 
                     + eElement
                     .getElementsByTagName("Autor")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nCategoria : " 
                  + eElement
                     .getElementsByTagName("Categoria")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nEditura : " 
                  + eElement
                     .getElementsByTagName("Editura")
                     .item(0)
                     .getTextContent());
                  writer.printf("\nNumar_Raft : " 
                  + eElement
                     .getElementsByTagName("Numar_Raft")
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

    public void afisare_xml(String filename)
    {
        try{
            int i;
            DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = 
               dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("biblioteca");
            doc.appendChild(rootElement);
            for(i=0;i<list_biblioteca.size();i++){
                Element biblioteca = doc.createElement("biblioteca");
                rootElement.appendChild(biblioteca);
                // Titlul element
                Element Titlul = doc.createElement("Titlul");
                Titlul.appendChild(
                doc.createTextNode(list_biblioteca.get(i).getTitlul()));
                biblioteca.appendChild(Titlul);
                // Autor element
                Element Autor = doc.createElement("Autor");
                Autor.appendChild(
                doc.createTextNode(list_biblioteca.get(i).getAutor()));
                biblioteca.appendChild(Autor);
                // Categorie element
                Element Categoria = doc.createElement("Categoria");
                Categoria.appendChild(
                doc.createTextNode(list_biblioteca.get(i).getCategoria()));
                biblioteca.appendChild(Categoria);
                // Editura element
                Element Editura = doc.createElement("Editura");
                Editura.appendChild(
                doc.createTextNode(list_biblioteca.get(i).getEditura()));
                biblioteca.appendChild(Editura);
                // Numar_Raft element
                Element Numar_Raft = doc.createElement("Numar_Raft");
                Numar_Raft.appendChild(
                doc.createTextNode(Integer.toString(list_biblioteca.get(i).getNumar_Raft())));
                biblioteca.appendChild(Numar_Raft);
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