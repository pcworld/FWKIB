
package keksdose.fwkib.modules.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import keksdose.fwkib.modules.Command;

public class SmartMensa implements Command {

    @Override
    public String apply(String message) {

        try {

            String[] command = { "./smartMensaNNscript.sh" };
            ProcessBuilder builder;
            builder = new ProcessBuilder(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(builder.start().getInputStream()));
            String s;
            List<String> returnvalue = new ArrayList<>();
            (reader.lines()).filter(v -> !v.isBlank()).forEach(returnvalue::add);

            if (returnvalue.size() != 4) {
                System.out.println(returnvalue.toString());
                return "";

            }
            String date;
            if (StringUtils.isNumeric(message.trim())) {
                date = LocalDate.now().plusDays(Integer.parseInt(message.trim()))
                        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.GERMANY));
            } else {
                date = LocalDate.now()
                        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.GERMANY));
            }
            SecureRandom random = new SecureRandom();
            StringBuilder mensa = new StringBuilder();
            DecimalFormat df = new DecimalFormat("#.0");
            Iterator<Double> doubleStream = random.doubles(2, 6).distinct().boxed().iterator();
            mensa.append(date + " ");
            mensa.append("Linie 1: " + returnvalue.get(0) + " " + df.format(doubleStream.next()) + "0 " + "\u20ac ");
            mensa.append("Linie 2: " + returnvalue.get(1) + " " + df.format(doubleStream.next()) + "0 " + "\u20ac ");
            mensa.append("Linie 3: " + returnvalue.get(2) + " " + df.format(doubleStream.next()) + "0 " + "\u20ac ");
            mensa.append("Linie 4/5: " + returnvalue.get(3) + " " + df.format(doubleStream.next()) + "0 " + "\u20ac ");
            return StringUtils.normalizeSpace(mensa.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

}