package UITest;

import java.io.IOException;

public interface DashboardBase {

    public void verifyEvent() throws IOException;

    public void verifyBookNowButton() throws IOException;

    public void bookEvent() throws IOException;
}
