package keksdose.fwkib.modules.commands.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import keksdose.keksirc.message.Message;

public class BrotiQuiz implements Function<Message, String> {

  @Override
  public String apply(Message event) {
    ExecutorService service = Executors.newSingleThreadExecutor();
    String message = event.getContent();

    service.execute(() -> {
      try {
        int index = message.indexOf("secs");
        int wait = Integer.parseInt((message.subSequence(index - 3, index).toString()).trim());
        TimeUnit.SECONDS.sleep(new Random().nextInt(wait / 2) + wait / 2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int num = 0;
      if (message.contains("b) ")) {
        num++;
      }
      if (message.contains("c) ")) {
        num++;
      }
      if (message.contains("d) ")) {
        num++;
      }
      if (num == 0) {
        return;
      }
      num = new SecureRandom().nextInt(num);
      char letter = 'a';
      letter = (char) (letter + num);
      event.answerNoPrefix(String.valueOf(letter));
    });
    return "yeah punkte";

  }

}
