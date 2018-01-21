package system.xml;

import java.io.File;

public interface EntityMarshaller<T> {
    void marshal(T entity, File file);
}
