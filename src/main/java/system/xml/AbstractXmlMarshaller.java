package system.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public abstract class AbstractXmlMarshaller<T> implements EntityMarshaller<T> {

    @Override
    public void marshal(T entities, File file) {
        try {
            JAXBContext jContext = JAXBContext.newInstance(entities.getClass());
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(entities, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
