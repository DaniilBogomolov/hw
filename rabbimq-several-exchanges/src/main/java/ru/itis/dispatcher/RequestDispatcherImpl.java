package ru.itis.dispatcher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.controllers.*;

@Component
@AllArgsConstructor
public class RequestDispatcherImpl implements RequestDispatcher {

    private ExitController exitController;
    private SignInController signInController;
    private HelpController helpController;
    private SendController sendController;
    private ReceiveController receiveController;

    @Override
    public void doResolve(String command) {
        if (command.equals("Exit")) {
            exitController.exit();
        } else if (command.startsWith("SignIn:")) {
            signInController.signIn(command);
        } else if (command.startsWith("Send")) {
            sendController.sendRequest(command);
        } else if (command.startsWith("Error: ")) {
            helpController.processError(command);
        } else if(command.startsWith("Get")) {
            receiveController.sendRequest(command);
        } else {
            helpController.processHelp();
        }
    }
}
