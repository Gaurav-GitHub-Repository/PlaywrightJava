package UITest;

import java.io.IOException;

public interface NewEventBase {

    public void verifyEventCreate(String fillTitle, String fillDescription, String selectCategory, String fillCity, String fillVenue) throws IOException;

}
