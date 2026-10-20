package UITest;

import java.io.IOException;

public interface ConfirmedBookingBase {

    public void verifyTickets(int numberOfTickets) throws IOException;

    public void confirmBooking(int tickets, String name, String email, String phoneNumber) throws IOException;
    
}
