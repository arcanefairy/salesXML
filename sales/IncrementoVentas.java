import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

public class IncrementoVentas {
    public static void main(String[] args) {
        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = factory.newDocumentBuilder();

            // Paso 2: Parsear el archivo "sales.xml" y obtener el documento DOM
            Document documento = constructor.parse(new File("sales.xml"));

            // Paso 3: Obtener la lista de elementos <sale_record>
            NodeList registroVentas = documento.getElementsByTagName("sale_record");

            // Paso 4: Solicitar el porcentaje al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese un porcentaje entre 5% y 15%: ");
            double porcentaje = scanner.nextDouble();

            if (porcentaje < 5 || porcentaje > 15) {
                System.out.println("El porcentaje debe estar entre 5% y 15%.");
                return;
            }

            // Paso 5: Recorrer los registros de ventas y aplicar el incremento
            for (int i = 0; i < registroVentas.getLength(); i++) {
                Element registroVenta = (Element) registroVentas.item(i);
                Element elementoVentas = (Element) registroVentas.getElementsByTagName("sales").item(0);

                double ventas = Double.parseDouble(elementoVentas.getTextContent());
                double nuevoSales = sales * (1 + porcentaje / 100);

                elementoVentas.setTextContent(String.format("%.2f", nuevoSales));
            }

            // Paso 6: Guardar el nuevo documento XML en "new_sales.xml"
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("new_sales.xml"));
            transformer.transform(source, result);

            System.out.println("El archivo 'new_sales.xml' se ha generado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
