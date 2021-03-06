package keksdose.fwkib.modules.listener;

import keksdose.fwkib.modules.commands.util.BrotiQuiz;
import keksdose.fwkib.modules.eventbus.MessageConsumer;
import keksdose.keksirc.message.Message;

/**
 * Reads all incoming messages and inserts quotes if needed.
 */
@MessageConsumer()

public class BrotiQuizListener extends AbstractListener {



  @Override
  public boolean handle(final Message message) {
    if (message.getContent().contains("secs") && message.getNick().equals("fakebroti")
        || message.getNick().equals("fwkib") && message.getContent().contains("zeit: ")) {
      new BrotiQuiz().apply(message);
    }
    return false;
  }
}

